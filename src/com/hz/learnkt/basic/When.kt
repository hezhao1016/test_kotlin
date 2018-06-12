package com.hz.learnkt.basic

/** when的用法
 * Created by hezhao on 2018-06-12 12:21
 */

fun main(args: Array<String>) {
    testWhen()
    testWhen("Hello")

    // when 后面可以不跟具体变量
    println("------------------------------------")
    val x = 1
    val y = 2
    when {
        x <= 0 -> println("小于 0")
        x == 1 && y == 2  -> println("x等于1且y等于2")
        else -> println("。。。")
    }

    // when表达式
    val obj:Any = 1
    val str = when (obj) {
        is Int -> "is int"
        is String -> "is String"
        else   -> "other type"
    }
    println(str)

}

fun testWhen(obj:Any):String =
        when (obj) {
            1          -> "One"
            "Hello"    -> "Greeting"
            is Long    -> "Long"
            !is String -> "Not a string"
            else       -> "Unknown"
        }

fun testWhen(){
    var i:Any = 0
    when (i) {
        1 -> print("x == 1")
        2 -> {
            print("x == 2")
        }
        3, 4 -> print("x == 0 or x == 1")
        5..10 -> print("x in [5,10]")
        "string" -> print("x == string")
        else -> {
            print("else")
        }
    }
}