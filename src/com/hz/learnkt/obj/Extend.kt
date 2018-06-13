package com.hz.learnkt.obj

/** 继承
 * Created by hezhao on 2018-06-13 08:58
 */

// 在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类
// 注意：Any 并不是 java.lang.Object；尤其是，它除了 equals()、hashCode()和toString()外没有任何成员。

// 使用 : 继承

// 注意：
// 1.默认情况下，在 Kotlin 中所有的类都是 final。类上的 open 标注与 Java 中 final 相反，它允许其他类从这个类继承。
// 2.默认情况下，在 Kotlin 中所有的函数都是 final。 open标注的函数表示可以覆盖,override标注表示用子类的函数覆盖父类同名函数。
//   如果函数没有标注 open，则子类中不允许定义相同签名的函数，不论加不加 override。在一个 final 类中（没有用 open 标注的类），开放成员是禁止的。
//   标记为 override 的成员本身是开放的，也就是说，它可以在子类中覆盖。如果你想禁止再次覆盖，使用 final 关键字：例如 final override fun v() { }
// 3.属性覆盖与方法覆盖类似；在超类中声明然后在派生类中重新声明的属性必须以 override 开头，并且它们必须具有兼容的类型。每个声明的属性可以由具有初始化器的属性或者具有 getter 方法的属性覆盖。你可以用一个 var 属性覆盖一个 val 属性，但反之则不行。

// 如果派生类有一个主构造函数，其基类型可以（并且必须） 用基类的主构造函数参数就地初始化。
// 如果类没有主构造函数，那么每个次构造函数必须使用 super 关键字初始化其基类型，或委托给另一个构造函数做到这一点。 注意，在这种情况下，不同的次构造函数可以调用基类型的不同的构造函数

open class Animal(var name:String) {

    init { println("Initializing Animal") }

    open val size: Int =
            name.length.also { println("Initializing size in Animal: $it") }

    open fun sayHello() {
        println("我是动物[$name]")
    }
}

class Dog(
    name: String,
    val age: Int
) : Animal(name.capitalize().also { println("Argument for Animal: $it") }) {

    init { println("Initializing Dog") }

    override val size: Int =
            (super.size + age).also { println("Initializing size in Dog: $it") }

    // 覆盖方法
    override fun sayHello() {
        println("我是小狗[$name], 今年${age}岁")
    }
}

fun main(args: Array<String>) {
    val animal = Animal("小A")
    val dog = Dog("大黄", 3)

    animal.sayHello()
    dog.sayHello()

    println(dog is Animal)
}


// 调用超类实现
open class Foo {
    open fun f() { println("Foo.f()") }
    open val x: Int get() = 1
}

class Bar : Foo() {
    override fun f() {
        super.f()
        println("Bar.f()")
    }

    // 覆盖属性
    override val x: Int get() = super.x + 1
}


// 抽象类
// 类和其中的某些成员可以声明为 abstract。 抽象成员在本类中可以不用实现。 需要注意的是，我们并不需要用 open 标注一个抽象类或者函数——因为这不言而喻。
// 可以用一个抽象成员覆盖一个非抽象的开放成员
open class Base {
    open fun f() {}
}

abstract class Derived : Base() {
    override abstract fun f()
}

class Baby : Derived() {
    override fun f() {  }
}