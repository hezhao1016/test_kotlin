package com.hz.learnkt.basic

/** 循环
 * Created by hezhao on 2018-06-12 11:34
 */

fun main(args: Array<String>) {
    println("----------------testFor----------------")
    testFor()

    println("----------------testWhile----------------")
    testWhile()
}


// for 循环可以对任何提供迭代器（iterator）的对象进行遍历，这相当于像 C# 这样的语言中的 foreach 循环。
fun testFor(){
    var items = listOf("小米","魅族","华为")

    // 遍历集合
    for (item in items){
        print("$item ")
    }
    println()

    // 简单循环体, items.indices获取集合的所有下标
    for (i in items.indices) print("${items[i]} ")
    println()

    // 同时遍历下标和元素
    for ((index, value) in items.withIndex()){
        println("index is $index value is $value")
    }

    // 遍历可空集合
    val listOf: List<String?> = listOf<String?>("aaa", null)
    listOf.forEach {
        println(it?.length)
    }

    // for 循环指定次数
    // 区间迭代:
    for (x in 1..5) {
        print("$x ")
    }
    println()

    // 或数列迭代：
    for (x in 1..10 step 2) {
        print("$x ")
    }
    println()

    for (x in 9 downTo 0 step 3) {
        print("$x ")
    }
    println()
}

fun testWhile(){
    // while
    var size = 10
    var i = 0
    while(i < size) {
        print("${i++} ")
    }
    println()

    // do...while
    i = 0
    do {
        i++
        if(i % 2 == 1) continue
        print("$i ")
        if(i >= 10) break
    } while(true)
    println()
}