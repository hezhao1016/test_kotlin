package com.hz.learnkt.obj

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/** 委托属性
 * Created by hezhao on 2018-06-15 12:18
 */

// 有一些常见的属性类型，虽然我们可以在每次需要的时候手动实现它们， 但是如果能够为大家把他们只实现一次并放入一个库会更好。例如包括：
//  —延迟属性（lazy properties）: 其值只在首次访问时计算；
//  —可观察属性（observable properties）: 监听器会收到有关此属性变更的通知；
//  —把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中。
//
// 为了涵盖这些（以及其他）情况，Kotlin 支持 委托属性:

class Example {
    // 属性对应的 get()（和 set()）会被委托给Delegate的 getValue() 和 setValue() 方法
    var p: String by Delegate()
}

class Delegate {
    // 第一个参数是读出 p 的对象、第二个参数保存了对 p 自身的描述
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    // 前两个参数相同，第三个参数保存将要被赋予的值
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}


////// 标准委托

// 延迟属性 Lazy
// lazy() 是接受一个 lambda 并返回一个 Lazy <T> 实例的函数，返回的实例可以作为实现延迟属性的委托： 第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果， 后续调用 get() 只是返回记录的结果。
// 默认情况下，对于 lazy 属性的求值是同步锁的（synchronized）：该值只在一个线程中计算，并且所有线程会看到相同的值。
val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}


// 可观察属性 Observable
class User3 {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
}
// 如果你想能够截获一个赋值并“否决”它，就使用 vetoable() 取代 observable()。 在属性被赋新值生效之前会调用传递给 vetoable 的处理程序。


// 把属性储存在映射中
// 一个常见的用例是在一个映射（map）里存储属性的值。 这经常出现在像解析 JSON 或者做其他“动态”事情的应用中。 在这种情况下，你可以使用映射实例自身作为委托来实现委托属性。
class User4(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

// 这也适用于 var 属性，如果把只读的 Map 换成 MutableMap 的话：
class MutableUser(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int     by map
}


// 局部委托属性（自 1.1 起）
// 你可以将局部变量声明为委托属性。 例如，你可以使一个局部变量惰性初始化：
fun example(computeFoo: () -> Foo) {
    val memoizedFoo by lazy(computeFoo)

    /*if (someCondition && memoizedFoo.isValid()) {
        memoizedFoo.doSomething()
    }*/

    // memoizedFoo 变量只会在第一次访问时计算。 如果 someCondition 失败，那么该变量根本不会计算。
}


/*属性委托要求:

这里我们总结了委托对象的要求。

对于一个只读属性（即 val 声明的），委托必须提供一个名为 getValue 的函数，该函数接受以下参数：
    thisRef —— 必须与 属性所有者 类型（对于扩展属性——指被扩展的类型）相同或者是它的超类型；
    property —— 必须是类型 KProperty<*> 或其超类型。

这个函数必须返回与属性相同的类型（或其子类型）。

对于一个可变属性（即 var 声明的），委托必须额外提供一个名为 setValue 的函数，该函数接受以下参数：
    thisRef —— 同 getValue()；
    property —— 同 getValue()；
    new value —— 必须和属性同类型或者是它的超类型。

getValue() 或/和 setValue() 函数可以通过委托类的成员函数提供或者由扩展函数提供。 当你需要委托属性到原本未提供的这些函数的对象时后者会更便利。 两函数都需要用 operator 关键字来进行标记。

委托类可以实现包含所需 operator 方法的 ReadOnlyProperty 或 ReadWriteProperty 接口之一。*/



fun main(args: Array<String>) {
    val e = Example()
    // 调用 Delegate 中的 getValue() 函数
    println(e.p) // Example@33a17727, thank you for delegating ‘p’ to me!

    // 调用 setValue() 函数
    e.p = "NEW" // NEW has been assigned to ‘p’ in Example@33a17727.


    // 延迟属性 Lazy
    println(lazyValue)
    println(lazyValue)
//    computed!
//    Hello
//    Hello


    // 可观察属性 Observable
    val user1 = User3()
    user1.name = "first"
    user1.name = "second"
//    <no name> -> first
//    first -> second


    // 属性映射
    val user2 = User4(mapOf(
            "name" to "John Doe",
            "age"  to 25
    ))
    // 委托属性会从这个映射中取值（通过字符串键——属性的名称）：
    println(user2.name) // Prints "John Doe"
    println(user2.age)  // Prints 25

}
