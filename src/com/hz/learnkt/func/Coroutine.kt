package com.hz.learnkt.func

import kotlin.coroutines.experimental.SequenceBuilder
import kotlin.coroutines.experimental.buildSequence

/** 协程
 * @Author hezhao
 * @Time   2018-06-18 15:35
 * @Description 无
 * @Version V 1.0
 */

// 一些 API 启动长时间运行的操作（例如网络 IO、文件 IO、CPU 或 GPU 密集型任务等），并要求调用者阻塞直到它们完成。
// 协程提供了一种避免阻塞线程并用更廉价、更可控的操作替代线程阻塞的方法：协程挂起。
//
// 调用标记有特殊修饰符 suspend 的函数时，会发生挂起


// 一种廉价构建惰性序列的方法
val fibonacciSeq = buildSequence() {
    var a = 0
    var b = 1

    yield(1)           // 1

    while (true) {
        yield(a + b)   // 2

        val tmp = a + b
        a = b
        b = tmp
        print(tmp.toString() + " ") // 3
    }
}


// 为了演示这样一个序列的真正惰性，让我们在调用 buildSequence() 内部输出一些调试信息:
val lazySeq = buildSequence {
    print("START ")
    for (i in 1..5) {
        yield(i)
        print("STEP ")
    }
    print("END")
}


// 为了一次产生值的集合（或序列），可以使用 yieldAll() 函数：
val lazySeq2 = buildSequence {
    yield(0)
    yieldAll(1..10)
}


// buildIterator() 的工作方式类似于 buildSequence()，但返回一个惰性迭代器。
// 可以通过为 SequenceBuilder 类写挂起扩展（带有上文描述的 @RestrictsSuspension 注解）来为 buildSequence() 添加自定义生产逻辑（custom yielding logic）：
suspend fun SequenceBuilder<Int>.yieldIfOdd(x: Int) {
    if (x % 2 != 0) yield(x)
}

val lazySeq3 = buildSequence {
    for (i in 1..10) yieldIfOdd(i)
}


fun main(args: Array<String>) {
    println("----------------------------------")
    println(fibonacciSeq.take(10).toList()) // 4
    println()

    // 输出序列的前三个元素
    println("----------------------------------")
    lazySeq.take(3).forEach { print("$it ") }
    println()

    println("----------------------------------")
    lazySeq2.forEach { print("$it ") }
    println()

    println("----------------------------------")
    lazySeq3.forEach { print("$it ") }
    println()
}
