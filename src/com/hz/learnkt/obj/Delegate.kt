package com.hz.learnkt.obj

/** 委托
 * Created by hezhao on 2018-06-15 12:04
 */

// 委托实现
interface BaseA {
    fun print()
}
class BaseAImpl(val x: Int) : BaseA {
    override fun print() { println(x) }
}
// DerivedA 的超类型列表中的 by-子句表示 b 将会在 Derived 中内部存储， 并且编译器将生成转发给 b 的所有 Base 的方法。
class DerivedA(b: BaseA) : BaseA by b


// 覆盖由委托实现的接口成员
// 覆盖符合预期：编译器会使用 override 覆盖的实现而不是委托对象中的。如果将 override fun printMessage() { print("abc") } 添加到 DerivedB，那么当调用 print 时程序会输出“abc”而不是“10”：
interface BaseB {
    fun printMessage()
    fun printMessageLine()
}
class BaseBImpl(val x: Int) : BaseB {
    override fun printMessage() { println(x) }
    override fun printMessageLine() { println(x) }
}
class DerivedB(b: BaseB) : BaseB by b {
    // 会调用这个实现
    override fun printMessage() { println("abc") }
}


// 但请注意，以这种方式重写的成员不会在委托对象的成员中调用 ，委托对象的成员只能访问其自身对接口成员实现：
interface BaseC {
    val message: String
    fun print()
}
class BaseCImpl(val x: Int) : BaseC {
    override val message = "BaseImpl: x = $x"
    override fun print() { println(message) }
}
class DerivedC(b: BaseC) : BaseC by b {
    // 在 b 的 `print` 实现中不会访问到这个属性
    override val message = "Message of Derived"
}



fun main(args: Array<String>) {
    val b1 = BaseAImpl(10)
    DerivedA(b1).print()


    println("---------------------------------")
    val b2 = BaseBImpl(10)
    DerivedB(b2).printMessage()
    DerivedB(b2).printMessageLine()


    println("---------------------------------")
    val b = BaseCImpl(10)
    val derived = DerivedC(b)
    derived.print()
    println(derived.message)
}

