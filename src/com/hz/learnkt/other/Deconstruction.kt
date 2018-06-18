package com.hz.learnkt.other

/** 解构声明
 * @Author hezhao
 * @Time   2018-06-18 15:54
 * @Description 无
 * @Version V 1.0
 */

class Person{
    operator fun component1():String{
        return "Jack"
    }
    operator fun component2():Int{
        return 22
    }
}


// 数据类自动声明 componentN() 函数，所以这里可以用解构声明。
data class Result(val result: Int, val status: String)

// 从函数中返回两个变量
fun function(): Result {
    // 各种计算...
    return Result(1, "OK")
}


fun main(args: Array<String>) {
    val person = Person()
    val (name, age) = person
    println(name)
    println(age)

    // 一个解构声明会被编译成以下代码：
    // val name = person.component1()
    // val age = person.component2()


    // 现在，使用该函数：
    val (result, status) = function()
    println(result)
    println(status)


    // 解构声明和映射
    val map = mapOf("book" to "书", "water" to "水", "sun" to "阳光")
    for ((key, value) in map) {
        // 使用该 key、value 做些事情
        println("$key to $value")
    }
    // 标准库提供了函数 component1() 和 component2()

    // 下划线用于未使用的变量（自 1.1 起）
    for ((_, value) in map) {
        // ...
    }


    // 在 lambda 表达式中解构（自 1.1 起）
    map.mapValues { entry -> "${entry.value}!" }
    map.mapValues { (key, value) -> "$value!" }

    // 注意声明两个参数和声明一个解构对来取代单个参数之间的区别：
    /*
    { a //-> …… } // 一个参数
    { a, b //-> …… } // 两个参数
    { (a, b) //-> …… } // 一个解构对
    { (a, b), c //-> …… } // 一个解构对以及其他参数

    如果解构的参数中的一个组件未使用，那么可以将其替换为下划线，以避免编造其名称：
    map.mapValues { (_, value) -> "$value!" }

    你可以指定整个解构的参数的类型或者分别指定特定组件的类型：
    map.mapValues { (_, value): Map.Entry<Int, String> -> "$value!" }
    map.mapValues { (_, value: String) -> "$value!" }*/
}