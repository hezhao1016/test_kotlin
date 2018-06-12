package com.hz.learnkt.other

/** List 列表
 * Created by hezhao on 2018-06-12 11:03
 */

fun main(args: Array<String>) {
    // 只读list
    val list = listOf<Int>(1, 2, 3)

    println(list)
    println(list[0])
    println(list.get(0))
    println(list.size)
    println(list.count())

    list.filter { x -> x > 1 && x < 3}
    list.filter {it > 1}

    // 转换成可变list
    var list2 = list.toMutableList()
    list2.add(3,22)
    list2[3] = 33
    list2.set(3,345)
    println("the number is ${list2[3]}")

    // 创建可变list
    val mutableList = mutableListOf(1, 2, 2)
    mutableList.add(234)
}