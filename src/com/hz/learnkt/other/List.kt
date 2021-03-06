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

    // 转换成可变list
    var list2 = list.toMutableList()
    list2.add(3,22)
    list2[3] = 33
    list2.set(3,345)
    println("the number is ${list2[3]}")

    // 创建可变list
    val mutableList = mutableListOf(1, 2, 3)
    mutableList.add(234)

    // 使用 in 运算符来判断集合内是否包含某实例：
    var items = listOf("小米","魅族","华为")
    when {
        "小米" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    // 使用 lambda 表达式来过滤（filter）和映射（map）集合：
    items
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach(::println)

    // 遍历集合
    println("------------------------")
    for(i in list){
        println(i)
    }

    // 空List
    mutableListOf<Int>()

    // 扩展方法
    println("--------------------------------")
    val items2 = listOf(1, 2, 3, 4)

    // 复制列表项
    val toList = items2.toList()

    items2.first() // 1
    items2.last() // 4
    items2.filter { it % 2 == 0 }   // 返回 [2, 4]

    val rwList = mutableListOf(1, 2, 3)
    rwList.requireNoNulls()        // 返回 [1, 2, 3]
    if (rwList.none { it > 6 }) println("No items above 6")  // 输出“No items above 6”
    val item = rwList.firstOrNull()

    items2.filter { x -> x > 1 && x < 3}
    items2.filter {it > 1}

    val results = items2.map { it * it }
    println(results)

    val result = items2.reduce { x, y -> x + y }
    println(result)

    // 其他List
    arrayListOf<Int>()
    emptyList<Int>()
}