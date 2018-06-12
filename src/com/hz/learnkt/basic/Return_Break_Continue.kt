package com.hz.learnkt.basic

/** 返回和跳转
 * @Author hezhao
 * @Time   2018-06-13 1:04
 * @Description 无
 * @Version V 1.0
 */

// Kotlin 有三种结构化跳转表达式：
//  return。默认从最直接包围它的函数或者匿名函数返回。
//  break。终止最直接包围它的循环。
//  continue。继续下一次最直接包围它的循环。

// 所有这些表达式都可以用作更大表达式的一部分：
fun hello(name:String?){
    val s = name ?: return
    println(s)
}

fun main(args: Array<String>) {
    hello(null)
    hello("Jack")

    // Break 与 Continue 标签
    // 在 Kotlin 中任何表达式都可以用标签（label）来标记。 标签的格式为标识符后跟 @ 符号，例如：abc@、fooBar@都是有效的标签（参见语法）。 要为一个表达式加标签，我们只要在其前加标签即可。
    loop@ for (i in 1..100) {
        if (i % 2 == 0) continue@loop
        if (i >= 10) break@loop
        print("$i ")
    }
    println()

    // 标签处返回
    // 最重要的一个用途就是从 lambda 表达式中返回
    fun foo() {
        listOf(1, 2, 3, 4, 5).forEach lit@{
            if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
            print(it)
        }
        print(" done with explicit label\n")
    }
    foo()

    // 使用隐式标签更方便
    fun foo2() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
            print(it)
        }
        print(" done with implicit label\n")
    }
    foo2()

    // 或者，我们用一个匿名函数替代 lambda 表达式。 匿名函数内部的 return 语句将从该匿名函数自身返回
    fun foo3() {
        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
            if (value == 3) return  // 局部返回到匿名函数的调用者，即 forEach 循环
            print(value)
        })
        print(" done with anonymous function\n")
    }
    foo3()

    // 请注意，前文三个示例中使用的局部返回类似于在常规循环中使用 continue。并没有 break 的直接等价形式，不过可以通过增加另一层嵌套 lambda 表达式并从其中非局部返回来模拟：
    fun foo4() {
        run loop@{
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@loop // 从传入 run 的 lambda 表达式非局部返回
                print(it)
            }
        }
        print(" done with nested loop\n")
    }
    foo4()


    // 当要返一个回值的时候，解析器优先选用标签限制的 return，即
    // return@a 1
    // 意为“从标签 @a 返回 1”，而不是“返回一个标签标注的表达式 (@a 1)”。

}