package com.hz.learnkt.func

import java.util.concurrent.locks.Lock

/** 内联函数
 * @Author hezhao
 * @Time   2018-06-18 14:10
 * @Description 无
 * @Version V 1.0
 */

// 使用高阶函数会带来一些运行时的效率损失：每一个函数都是一个对象，并且会捕获一个闭包。
// 即那些在函数体内会访问到的变量。 内存分配（对于函数对象和类）和虚拟调用会引入运行时间开销。
// 但是在许多情况下通过内联化 lambda 表达式可以消除这类的开销。
// 下述函数是这种情况的很好的例子。即 lock() 函数可以很容易地在调用处内联。

inline fun <T> lock(lock: Lock, body: () -> T): T {
    // ……
    return body.invoke()
}

// inline 修饰符影响函数本身和传给它的 lambda 表达式：所有这些都将内联到调用处。
// 内联可能导致生成的代码增加；不过如果我们使用得当（即避免内联过大函数），性能上会有所提升，尤其是在循环中的“超多态（megamorphic）”调用处。


// 禁用内联
// 如果你只想被（作为参数）传给一个内联函数的 lamda 表达式中只有一些被内联，你可以用 noinline 修饰符标记一些函数参数：
inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
    // ……
}


// 非局部返回
// 在 Kotlin 中，我们可以只使用一个正常的、非限定的 return 来退出一个命名或匿名函数。
// 这意味着要退出一个 lambda 表达式，我们必须使用一个标签，并且在 lambda 表达式内部禁止使用裸 return，因为 lambda 表达式不能使包含它的函数返回：
// 这种返回（位于 lambda 表达式中，但退出包含它的函数）称为非局部返回。 我们习惯了在循环中用这种结构，其内联函数通常包含：
fun hasZeros(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) return true // 从 hasZeros 返回
    }
    return false
}

// 请注意，一些内联函数可能调用传给它们的不是直接来自函数体、而是来自另一个执行上下文的 lambda 表达式参数，例如来自局部对象或嵌套函数。在这种情况下，该 lambda 表达式中也不允许非局部控制流。为了标识这种情况，该 lambda 表达式参数需要用 crossinline 修饰符标记:
inline fun f(crossinline body: () -> Unit) {
    val f = object: Runnable {
        override fun run() = body()
    }
    // ……
}

// break 和 continue 在内联的 lambda 表达式中还不可用


// 具体化的类型参数
/*inline fun <reified T> TreeNode.findParentOfType(): T? {
    var p = parent
    while (p != null && p !is T) {
        p = p.parent
    }
    return p as T?
}*/

inline fun <reified T> membersOf() = T::class.members

fun main(s: Array<String>) {
    println(membersOf<StringBuilder>().joinToString("\n"))
}


// 内联属性（自 1.1 起）
// inline 修饰符可用于没有幕后字段的属性的访问器。 你可以标注独立的属性访问器：
class Example {
    val name: String
        inline get() = ""

    var age:Int
        get() = 0
        inline set(v) { /*……*/ }

    inline var sex: Boolean
        get() = false
        set(v) { /*……*/ }
}