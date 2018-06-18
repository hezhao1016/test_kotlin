package com.hz.learnkt.func

/** 函数
 * @Author hezhao
 * @Time   2018-06-17 11:53
 * @Description 无
 * @Version V 1.0
 */

// 函数声明
fun double(x: Int): Int {
    return 2 * x
}

// 如果一个函数不返回任何有用的值，它的返回类型是 Unit。Unit 返回类型声明是可选的


// 默认参数
fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) {
    // ……
}

// 覆盖方法总是使用与基类型方法相同的默认参数值。 当覆盖一个带有默认参数值的方法时，必须从签名中省略默认参数值：
open class A {
    open fun foo(i: Int = 10) { /*……*/ }
}
class B : A() {
    override fun foo(i: Int) { /*……*/ }  // 不能有默认值
}

// 如果一个默认参数在一个无默认值的参数之前，那么该默认值只能通过使用命名参数调用该函数来使用：
fun foo(bar: Int = 0, baz: Int) { /* …… */ }

// 不过如果最后一个 lambda 表达式参数从括号外传给函数函数调用，那么允许默认参数不传值：
fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) { /* …… */ }


// 命名参数
fun reformat(str: String,
             normalizeCase: Boolean = true,
             upperCaseFirstLetter: Boolean = true,
             divideByCamelHumps: Boolean = false,
             wordSeparator: Char = ' ') {
    /*……*/
}


// 可变数量的参数（Varargs）
// 函数的参数（通常是最后一个）可以用 vararg 修饰符标记：
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}


// 单表达式函数
fun max(x: Int, y: Int): Int = if (x > y) x else y
// 自动推断出返回值类型
fun max(x: Double, y: Double) = if (x > y) x else y


// 中缀表示法
// 标有 infix 关键字的函数也可以使用中缀表示法（忽略该调用的点与圆括号）调用。中缀函数必须满足以下要求：
// - 它们必须是成员函数或扩展函数；
// - 它们必须只有一个参数；
// - 其参数不得接受可变数量的参数且不能有默认值。
infix fun Int.add(x: Int): Int {
    return this + x
}
// 中缀函数调用的优先级低于算术操作符、类型转换以及 rangeTo 操作符
// 另一方面，中缀函数调用的优先级高于布尔操作符 && 与 ||、is- 与 in- 检测以及其他一些操作符。

// 请注意，中缀函数总是要求指定接收者与参数。当使用中缀表示法在当前接收者上调用方法时，需要显式使用 this；不能像常规方法调用那样省略。这是确保非模糊解析所必需的。
class MyStringCollection {
    infix fun add(s: String) { /* …… */ }

    fun build() {
        add("abc")       // 正确
        this add "abc"   // 正确
        // add "abc"        // 错误：必须指定接收者
    }
}


// Kotlin 中函数可以在文件顶层声明，这意味着你不需要像一些语言如 Java、C# 或 Scala 那样创建一个类来保存一个函数。
// Kotlin 支持局部函数，即一个函数在另一个函数内部：
// 局部函数可以访问外部函数（即闭包）的局部变量，所以在上例中，visited 可以是局部变量：


// 尾递归函数
// Kotlin 支持一种称为尾递归的函数式编程风格。 这允许一些通常用循环写的算法改用递归函数来写，而无堆栈溢出的风险。
// 当一个函数用 tailrec 修饰符标记并满足所需的形式时，编译器会优化该递归，留下一个快速而高效的基于循环的版本：

// 计算余弦的不动点（fixpoint of cosine）,重复地从 1.0 开始调用 Math.cos，直到结果不再改变，产生0.7390851332151607的结果
tailrec fun findFixPoint(x: Double = 1.0): Double
        = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))

// 相当于:
private fun findFixPoint(): Double {
    var x = 1.0
    while (true) {
        val y = Math.cos(x)
        if (x == y) return x
        x = y
    }
}


fun main(args: Array<String>) {
    foo(baz = 1) // 使用默认值 bar = 0

    foo(1) { println("hello") } // 使用默认值 baz = 1
    foo { println("hello") }    // 使用两个默认值 bar = 0 与 baz = 1

    // 命名参数
    val str = ""
    reformat(str)
    reformat(str, wordSeparator = '_')
    reformat(str,
            normalizeCase = true,
            upperCaseFirstLetter = true,
            divideByCamelHumps = false,
            wordSeparator = '_'
    )
    // 当一个函数调用混用位置参数与命名参数时，所有位置参数都要放在第一个命名参数之前。例如，允许调用 f(1, y = 2) 但不允许 f(x = 1, 2)。

    // 可以通过使用星号(伸展)操作符将可变数量参数（vararg） 以命名形式传入：
    asList(ts = *arrayOf("a", "b", "c"))
    asList("a")
    val list = asList("a", "b", "c")
    println(list)
    // 请注意，在调用 Java 函数时不能使用命名参数语法，因为 Java 字节码并不总是保留函数参数的名称。

    // 用中缀表示法调用该函数
    println(1 add 2)
    // 等同于这样
    println(1.add(2))

    println(findFixPoint())
    println(findFixPoint(1.0))
}