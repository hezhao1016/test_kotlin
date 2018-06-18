package com.hz.learnkt.other

/** Set
 * Created by hezhao on 2018-06-18 17:03
 */

fun main(args: Array<String>) {
    // 只读list
    val set = setOf("a", "b", "c", "c")

    println(set)
    println(set.size)
    println(set.count())

    // 转换成可变Set
    var list2 = set.toMutableSet()

    // 创建可变list
    val mutableSet = mutableSetOf(1, 2, 3)
    mutableSet.add(234)

    // 使用 in 运算符来判断集合内是否包含某实例：
    var items = setOf("小米","魅族","华为")
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
    for(i in items){
        println(i)
    }

    // 空Set
    mutableSetOf<String>()

    // 扩展方法
    println("--------------------------------")
    val items2 = setOf(1, 2, 3, 4)

    // 复制列表项
    val toSet = items2.toSet()

    items2.first() // 1
    items2.last() // 4
    items2.filter { it % 2 == 0 }   // 返回 [2, 4]

    val rwSet = mutableSetOf(1, 2, 3)
    rwSet.requireNoNulls()        // 返回 [1, 2, 3]
    if (rwSet.none { it > 6 }) println("No items above 6")  // 输出“No items above 6”
    val item = rwSet.firstOrNull()

    items2.filter { x -> x in 2..3 }
    items2.filter {it > 1}

    val results = items2.map { it * it }
    println(results)

    val result = items2.reduce { x, y -> x + y }
    println(result)

    // 其他Set
    hashSetOf<Int>()
    linkedSetOf<Int>()
    sortedSetOf<Int>()
    emptySet<Int>()
}