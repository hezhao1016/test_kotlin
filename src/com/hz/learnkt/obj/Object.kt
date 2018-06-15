package com.hz.learnkt.obj

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

/** 对象表达式与对象声明
 *
 * 有时候，我们需要创建一个对某个类做了轻微改动的类的对象，而不用为之显式声明新的子类。 Java 用匿名内部类 处理这种情况。 Kotlin 用对象表达式和对象声明对这个概念稍微概括了下。
 *
 * Created by hezhao on 2018-06-15 09:17
 */

////// 对象表达式
// 要创建一个继承自某个（或某些）类型的匿名类的对象，我们会这么写：
/*window.addMouseListener(object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {
        // ……
    }

    override fun mouseEntered(e: MouseEvent) {
        // ……
    }
})*/

// 如果超类型有一个构造函数，则必须传递适当的构造函数参数给它。 多个超类型可以由跟在冒号后面的逗号分隔的列表指定：
open class A1(x: Int) {
    public open val y: Int = x
}

interface B1 {/*……*/}

val ab: A1 = object : A1(1), B1 {
    override val y = 15
}

// 任何时候，如果我们只需要“一个对象而已”，并不需要特殊超类型，那么我们可以简单地写：
fun foo1() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
        fun demo(){
            //...
        }
    }
    print(adHoc.x + adHoc.y)
    adHoc.demo()
}

// 请注意，匿名对象可以用作只在本地和私有作用域中声明的类型。如果你使用匿名对象作为公有函数的返回类型或者用作公有属性的类型，那么该函数或属性的实际类型会是匿名对象声明的超类型，如果你没有声明任何超类型，就会是 Any。在匿名对象中添加的成员将无法访问。
class C4 {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // 没问题
        // val x2 = publicFoo().x  // 错误：未能解析的引用“x”
    }
}


// 就像 Java 匿名内部类一样，对象表达式中的代码可以访问来自包含它的作用域的变量。 （与 Java 不同的是，这不仅限于 final 变量。）
class C5 {
    var clickCount = 0
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        fun demo() {
            clickCount++
        }
    }
    fun bar() {
        foo().demo()
    }
}


////// 对象声明
// 单例模式
object Resource {
    val name = "Name"

    var age: Int
        get() = this.age
        set(value) {
            this.age = value
        }
    fun registerDataProvider() {
        // ……
    }
}

// 单例模式的超类型
object DefaultListener : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {
        // ……
    }

    override fun mouseEntered(e: MouseEvent) {
        // ……
    }
}

// 注意：对象声明不能在局部作用域（即直接嵌套在函数内部），但是它们可以嵌套到其他对象声明或非内部类中。


// 伴生对象
class MyClass2 {
    companion object Factory {
        fun create(): MyClass2 = MyClass2()
    }
}

// 可以省略伴生对象的名称，在这种情况下将使用名称 companion
class MyClass3 {
    companion object {
        fun create(): MyClass3 = MyClass3()
    }
}
val x = MyClass3.Companion


// 请注意，即使伴生对象的成员看起来像其他语言的静态成员，在运行时他们仍然是真实对象的实例成员，而且，例如还可以实现接口：
interface Factory<T> {
    fun create(): T
}

class MyClass4 {
    companion object : Factory<MyClass4> {
        override fun create(): MyClass4 = MyClass4()
    }
}


fun main(args: Array<String>) {
    // 使用单例对象
    println(Resource.name)
    Resource.registerDataProvider()

    // 使用类名作为限定符来调用伴生对象
    MyClass2.create()

    val factory:Factory<MyClass4> = MyClass4.Companion
    val myClass4 = factory.create()

}


//对象表达式和对象声明之间有一个重要的语义差别：
//
//- 对象表达式是在使用他们的地方立即执行（及初始化）的；
//- 对象声明是在第一次被访问到时延迟初始化的；
//- 伴生对象的初始化是在相应的类被加载（解析）时，与 Java 静态初始化器的语义相匹配。