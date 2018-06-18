package com.hz.learnkt.other

import com.hz.learnkt.other.html.*

/** 类型安全的构建器
 * @Author hezhao
 * @Time   2018-06-18 18:48
 * @Description 无
 * @Version V 1.0
 */

fun result(args: Array<String>) =
        html {
            head {
                title {+"XML encoding with Kotlin"}
            }
            body {
                h1 {+"XML encoding with Kotlin"}
                p  {+"this format can be used as an alternative markup to XML"}

                // 一个具有属性和文本内容的元素
                a(href = "http://kotlinlang.org") {+"Kotlin"}

                // 混合的内容
                p {
                    +"This is some"
                    b {+"mixed"}
                    +"text. For more see the"
                    a(href = "http://kotlinlang.org") {+"Kotlin"}
                    +"project"
                }
                p {+"some text"}

                // 以下代码生成的内容
                p {
                    for (arg in args)
                        +arg
                }
            }
        }


fun main(args: Array<String>) {
    val array = arrayOf(
            "你好",
            "Kotlin"
    )
    val result = result(array)
    println(result)
}