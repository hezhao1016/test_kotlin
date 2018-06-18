package com.hz.learnkt.other

/** Map 键值对
 * Created by hezhao on 2018-06-12 11:03
 */

fun main(args: Array<String>) {
    //在kotlin中，map是只读的，而mutableMap是var类型的
    val map = mapOf<String,Int>("x" to 1, "y" to 2, "z" to 3)

    println(map)
    println(map["x"])
    println(map.get("X"))
    println(map.size)
    println(map.count())

    // 转换成可变map
    var map2 = map.toMutableMap()
    map2.put("汉", 4)
    map2["汉"] = 5
    map2.put("汉", 6)
    println("the number is ${map2["汉"]}")

    // 创建可修改的map
    val mutableMap = mutableMapOf("x" to 1, "y" to 2, "z" to 3)
    mutableMap["sd"] = 11

    // 遍历集合
    println("------------------------")
    for((k, v) in map){
        println("$k to $v")
    }

    // 空Map
    mutableMapOf<String, String>()

    // 其他Map
    hashMapOf<String, String>()
    linkedMapOf<String, String>()
    sortedMapOf<String, String>()
    emptyMap<String, String>()
}