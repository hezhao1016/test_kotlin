package com.hz.learnkt.other

/** range 区间
 * Created by hezhao on 2018-06-12 10:55
 */

fun main(args: Array<String>) {
    //使用区间
    val list = listOf(1, 2, 3)

    // 闭区间：包含 100
    println("------------------------------------------------")
    for (i in 1..100) {
        print("$i ")
    }
    println()

    // 半开区间：不包含 100
    println("------------------------------------------------")
    for (i in 1 until 100) {
        print("$i ")
    }
    println()

    // 增量步长为2
    println("------------------------------------------------")
    for (x in 2..10 step 2) {
        print("$x ")
    }
    println()

    // 向下区间
    println("------------------------------------------------")
    for (x in 10 downTo 1) {
        print("$x ")
    }
    println()


    // 判断是否在区间内
    println("------------------------------------------------")
    if (list[0] in 1..10) {
        println(list[0])
    }
}