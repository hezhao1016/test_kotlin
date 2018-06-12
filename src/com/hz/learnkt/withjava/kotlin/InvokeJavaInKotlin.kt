package com.hz.learnkt.withjava.kotlin

import com.hz.learnkt.withjava.java.Emp

/** Kotlin 中调用Java
 * Created by hezhao on 2017-07-18 19:48.
 */

fun main(args: Array<String>) {
    // 调用Java类
    var emp = Emp("张三")
    emp.name = "李四"

    println(emp.sayHello())

    // 使用Java ArrayList
    val source = ArrayList<Int>()
    source.add(1)
    source.add(2)
    source.add(3)
    source.add(4)
    source.add(5)
    demo(source)
    println(source)
}

fun demo(source: List<Int>) {
    val list = ArrayList<Int>()
    // “for”-循环用于 Java 集合：
    for (item in source) {
        list.add(item)
    }
    // 操作符约定同样有效：
    for (i in 0..source.size - 1) {
        list[i] = source[i] // 调用 get 和 set
    }
}