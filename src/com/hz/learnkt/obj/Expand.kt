package com.hz.learnkt.obj

/** 扩展
 *
 * Kotlin 同 C# 和 Gosu 类似，能够扩展一个类的新功能而无需继承该类或使用像装饰者这样的任何类型的设计模式。 这通过叫做 扩展 的特殊声明完成。Kotlin 支持 扩展函数 和 扩展属性。
 *
 * @Author hezhao
 * @Time   2018-06-13 23:04
 * @Description 无
 * @Version V 1.0
 */


// 为 MutableList<Int> 添加一个swap 函数：
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}

// 当然，这个函数对任何 MutableList<T> 起作用，我们可以泛化它：
fun <T> MutableList<T>.swapT(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}


// 扩展是静态解析的
// 扩展函数是静态分发的，即他们不是根据接收者类型的虚方法。 这意味着调用的扩展函数是由函数调用所在的表达式的类型来决定的， 而不是由表达式运行时求值结果决定的
// 这个例子会输出 "c"，因为调用的扩展函数只取决于参数 c 的声明类型，该类型是 C 类。
open class C1

class D1: C1()

fun C1.foo() = "c"

fun D1.foo() = "d"

fun printFoo(c: C1) {
    println(c.foo())
}


// 如果一个类定义有一个成员函数和一个扩展函数，而这两个函数又有相同的接收者类型、相同的名字并且都适用给定的参数，这种情况总是取成员函数。
class C2 {
    fun foo() { println("member") }
}

fun C2.foo() { println("extension") }

// 扩展函数重载
fun C2.foo(i: Int) { println("extension with $i") }


// 可空接收者
// 注意可以为可空的接收者类型定义扩展。这样的扩展可以在对象变量上调用， 即使其值为 null，并且可以在函数体内检测 this == null，这能让你在没有检测 null 的时候调用 Kotlin 中的toString()：检测发生在扩展函数的内部。
fun Any?.toString(): String {
    if (this == null) return "null"
    // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
    // 解析为 Any 类的成员函数
    return toString()
}


// 扩展属性
// 注意：由于扩展没有实际的将成员插入类中，因此对扩展属性来说幕后字段是无效的。这就是为什么扩展属性不能有初始化器。他们的行为只能由显式提供的 getters/setters 定义。
val <T> List<T>.lastIndex: Int
    get() = size - 1


// 伴生对象的扩展
class MyClass {
    companion object { }  // 将被称为 "Companion"
}

fun MyClass.Companion.foo() {
    // ……
}

// 就像伴生对象的其他普通成员，只需用类名作为限定符去调用他们
// MyClass.foo()


// 扩展声明为成员
// 在一个类内部你可以为另一个类声明扩展。在这样的扩展内部，有多个 隐式接收者 —— 其中的对象成员可以无需通过限定符访问。扩展声明所在的类的实例称为 分发接收者，扩展方法调用所在的接收者类型的实例称为 扩展接收者 。
class D2 {
    fun bar() {
        println("bar")
    }
}

class C3 {
    fun baz() {
        println("baz")
    }

    fun D2.foo() {
        bar()   // 调用 D.bar
        baz()   // 调用 C.baz

        // 对于分发接收者和扩展接收者的成员名字冲突的情况，扩展接收者优先。要引用分发接收者的成员你可以使用 限定的 this 语法。
        println(toString())          // 调用 D.toString()
        println(this@C3.toString())  // 调用 C.toString()
    }

    fun caller(d: D2) {
        d.foo()   // 调用扩展函数
    }
}



fun main(args: Array<String>) {
    // 使用扩展函数

    val l = mutableListOf(1, 2, 3)
    l.swap(0, 2) // “swap()”内部的“this”得到“l”的值
    println(l)

    l.swapT(0, 2)
    println(l)

    printFoo(D1()) // c

    C2().foo() // member
    C2().foo(1) // extension with 1

    println("---------------------")
    C3().caller(D2())
}