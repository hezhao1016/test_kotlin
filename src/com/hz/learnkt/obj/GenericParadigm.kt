package com.hz.learnkt.obj

import java.util.ArrayList



/** 泛型
 * Created by hezhao on 2018-06-14 18:04
 */

// 定义
class Box<T>(t: T) {
    var value = t
}


// 声明处型变




fun main(args: Array<String>) {
    // 一般来说，要创建这样类的实例，我们需要提供类型参数
    val box1: Box<Int> = Box<Int>(1)
    println(box1.value)

    // 但是如果类型参数可以推断出来，例如从构造函数的参数或者从其他途径，允许省略类型参数：
    val box2 = Box(1) // 1 具有类型 Int，所以编译器知道我们说的是 Box<Int>。
    println(box2.value)

}