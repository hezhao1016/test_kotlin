package com.hz.learnkt.other

import com.hz.learnkt.other.html.B
import java.io.File

/** 类型别名
 * @Author hezhao
 * @Time   2018-06-18 18:31
 * @Description 无
 * @Version V 1.0
 */

// 类型别名为现有类型提供替代名称。 如果类型名称太长，你可以另外引入较短的名称，并使用新的名称替代原类型名。

// 它有助于缩短较长的泛型类型。 例如，通常缩减集合类型是很有吸引力的：

// typealias NodeSet = Set<Network.Node>
typealias FileTable<K> = MutableMap<K, MutableList<File>>


// 你可以为函数类型提供另外的别名：

typealias MyHandler = (Int, String, Any) -> Unit
typealias Predicate<T> = (T) -> Boolean


// 你可以为内部类和嵌套类创建新名称：
class AA {
    inner class Inner
}
class BB {
    inner class Inner
}

typealias AAInner = AA.Inner
typealias BBInner = BB.Inner


// 类型别名不会引入新类型。 它们等效于相应的底层类型。
// 当你在代码中添加 typealias Predicate<T> 并使用 Predicate<Int> 时，Kotlin 编译器总是把它扩展为 (Int) -> Boolean。
// 因此，当你需要泛型函数类型时，你可以传递该类型的变量，反之亦然：

fun foo(p: Predicate<Int>) = p(42)

fun main(args: Array<String>) {
    val f: (Int) -> Boolean = { it > 0 }
    println(foo(f)) // 输出 "true"

    val p: Predicate<Int> = { it > 0 }
    println(listOf(1, -2).filter(p)) // 输出 "[1]"
}