package com.hz.learnkt.obj

/** 嵌套类与内部类
 * @Author hezhao
 * @Time   2018-06-13 23:50
 * @Description 无
 * @Version V 1.0
 */

// 类可以嵌套在其他类中：
class Outer1 {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
}

val demo1 = Outer1.Nested().foo() // == 2


// 内部类
// 类可以标记为 inner 以便能够访问外部类的成员。内部类会带有一个对外部类的对象的引用：
class Outer2 {
    private val bar: Int = 1
    var v = "成员属性"

    /**嵌套内部类**/
    inner class Inner {
        fun foo() = bar // 访问外部类成员

        fun innerTest() {
            var o = this@Outer2 //获取外部类的成员变量
            println("内部类可以引用外部类的成员，例如：" + o.v)
        }
    }
}

val demo2 = Outer2().Inner().foo() // == 1
val demo3 = Outer2().Inner().innerTest()


// 匿名内部类
// 使用对象表达式创建匿名内部类实例：
class Test {
    var v = "成员属性"

    fun setInterFace(test: TestInterFace) {
        test.test()
    }
}

/**
 * 定义接口
 */
interface TestInterFace {
    fun test()
}

fun main(args: Array<String>) {
    var test = Test()

    /**
     * 采用对象表达式来创建接口对象，即匿名内部类的实例。
     */
    test.setInterFace(object : TestInterFace {
        override fun test() {
            println("对象表达式创建匿名内部类的实例")
        }
    })
}