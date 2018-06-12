package com.hz.learnkt.basic

/** 循环
 * Created by hezhao on 2018-06-12 11:34
 */

fun main(args: Array<String>) {
    // do...while
    var flag = 0
    do {
        println("好好学习，天天向上")
        flag ++
    }while(flag < 10)

    println("--------------------------------")
    testFor()

    println("--------------------------------")
    testWhile()
}


fun testFor(){
    var items = listOf("小米","魅族","华为")
    for (item in items){
        println(item)
    }

    for (i in items.indices)    println(items[i])

    for ((index,value) in items.withIndex()){
        println("index is $index value is $value")
    }

    val listOf: List<String?> = listOf<String?>("aaa", null)
    listOf.forEach {
        println(it?.length)
    }

    // for 循环指定次数
    // 区间迭代:
    for (x in 1..5) {
        print(x)
    }

    // 或数列迭代：
    for (x in 1..10 step 2) {
        print(x)
    }
    for (x in 9 downTo 0 step 3) {
        print(x)
    }

    // 使用 in 运算符来判断集合内是否包含某实例：
    when {
        "小米" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    //使用 lambda 表达式来过滤（filter）和映射（map）集合：
    items
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach(::println)

}

fun testWhile(){
    var size = 10
    var i = 0
    while(i < size){
        println(i++)
    }

    i = 0
    do{
        println("a-- $i")
        i++
        if(i > 3)   break
    }while(true)

}