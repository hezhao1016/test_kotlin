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

    if (list[0] !in 10..20) {
        println("不存在")
    }



    // 一些实用函数
    println("------------------------------------------------")

    // rangeTo()
    val rangeTo = 1.rangeTo(5)
    println(rangeTo)

    // downTo()
    var downTo = 10.downTo(1)
    downTo = 10 downTo 1
    println(downTo)

    // reversed() 返回反转后的数列
    val reversed = rangeTo.reversed()
    println(reversed)

    // step() 步长
    // 步长（step）值必须始终为正，因此该函数不会更改迭代的方向
    // 请注意，返回数列的 last 值可能与原始数列的 last 值不同，以便保持不变式 (last - first) % step == 0 成立。这里是一个例子：
    (1..12 step 2).last == 11  // 值为 [1, 3, 5, 7, 9, 11] 的数列
    (1..12 step 3).last == 10  // 值为 [1, 4, 7, 10] 的数列
    (1..12 step 4).last == 9   // 值为 [1, 5, 9] 的数列

}