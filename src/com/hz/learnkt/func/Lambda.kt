package com.hz.learnkt.func


/** 高阶函数与 lambda 表达式
 * @Author hezhao
 * @Time   2018-06-17 23:53
 * @Description 无
 * @Version V 1.0
 */

// 高阶函数
// 将函数用作参数或返回值
fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}


// 如需将函数类型指定为可空，请使用圆括号：((Int, Int) -> Int)?
// 可以通过使用类型别名给函数类型起一个别称：
typealias ClickHandler = (Int, String) -> Unit


// 匿名函数
val nimingfun = fun(s: String): Int { return s.toIntOrNull() ?: 0 }


// 使用实现函数类型接口的自定义类的实例：
class IntTransformer: (Int) -> Int {
    override operator fun invoke(x: Int): Int = TODO()
}
val intFunction: (Int) -> Int = IntTransformer()


// Lambda 表达式语法
fun testLambda() {
    val items = listOf(1, 2, 3, 4, 5)

    // Lambda 表达式的完整语法
    val sum1 = { x: Int, y: Int -> x + y }
    // 如果我们把所有可选标注都留下，看起来如下:
    val sum2: (Int, Int) -> Int = { x, y -> x + y }

    // 将 lambda 表达式传给最后一个参数
    // 在 Kotlin 中有一个约定：如果函数的最后一个参数接受函数，那么作为相应参数传入的 lambda 表达式可以放在圆括号之外：
    val product = items.fold(1) { acc, e -> acc * e }

    // 如果该 lambda 表达式是调用时唯一的参数，那么圆括号可以完全省略：
    run { println("...") }

    // it：单个参数的隐式名称
    val subItems = items.filter { it % 2 == 0 } // 这个字面值是“(it: Int) -> Boolean”类型的
    println(subItems)

    // 从 lambda 表达式中返回一个值
    // 我们可以使用限定的返回语法从 lambda 显式返回一个值。 否则，将隐式返回最后一个表达式的值。
    // 因此，以下两个片段是等价的：
    items.filter {
        it > 0
    }

    items.filter {
        return@filter it > 0
    }

    // 这一约定连同在圆括号外传递 lambda 表达式一起支持 LINQ-风格 的代码：
    val strings = arrayOf("a", "ab", "abc", "abcd", "abcde")
    val subStrings = strings.filter { it.length == 5 }.sortedBy { it }.map { it.toUpperCase() }
    println(subStrings)

    // 下划线用于未使用的变量（自 1.1 起）
    val map = mapOf("book" to "书", "water" to "水", "sun" to "阳光")
    map.forEach { _, value -> println("$value!") }

}


// 匿名函数
fun testNiMing() {
    val items = listOf(1, 2, 3, 4, 5)

    val demo1 = fun(x: Int, y: Int): Int = x + y

    val demo2 = fun(x: Int, y: Int): Int {
        return x + y
    }

    // 参数和返回类型的指定方式与常规函数相同，除了能够从上下文推断出的参数类型可以省略：
    val subItems = items.filter(fun(item) = item > 0)
    println(subItems)

    // Lambda表达式与匿名函数之间的另一个区别是非局部返回的行为。
    // 一个不带标签的 return 语句总是在用 fun 关键字声明的函数中返回。
    // 这意味着 lambda 表达式中的 return 将从包含它的函数返回，而匿名函数中的 return 将从匿名函数自身返回。
}


// 闭包
fun testClosure() {
    val items = listOf(1, 2, 3, 4, 5)

    // Lambda 表达式或者匿名函数（以及局部函数和对象表达式） 可以访问其 闭包 ，即在外部作用域中声明的变量。 与 Java 不同的是可以修改闭包中捕获的变量：
    var sum = 0
    items.filter { it > 0 }.forEach {
        sum += it
    }
    println(sum)
}


fun main(args: Array<String>) {
    val items = listOf(1, 2, 3, 4, 5)

    // Lambdas 表达式是花括号括起来的代码块。
    var str = items.fold(0, {
        // 如果一个 lambda 表达式有参数，前面是参数，后跟“->”
        acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        println("result = $result")
        // lambda 表达式中的最后一个表达式是返回值：
        result
    })
    println(str)

    // lambda 表达式的参数类型是可选的，如果能够推断出来的话：
    val joinedToString = items.fold("Elements:", { acc, i -> "$acc $i" })
    println(joinedToString)

    // 函数引用也可以用于高阶函数调用：
    val product = items.fold(1, Int::times)
    println(product)


    // 带与不带接收者的函数类型非字面值可以互换，其中接收者可以替代第一个参数，反之亦然。例如，(A, B) -> C 类型的值可以传给或赋值给期待 A.(B) -> C 的地方，反之亦然：
    println("------------------------------")
    val repeat: String.(Int) -> String = { times -> repeat(times) }
    val twoParameters: (String, Int) -> String = repeat // OK
    fun runTransformation(f: (String, Int) -> String): String {
        return f("hello", 3)
    }
    val result = runTransformation(repeat) // OK
    println(result)


    // 函数类型实例调用
    // 函数类型的值可以通过其 invoke(……) 操作符调用：f.invoke(x) 或者直接 f(x)。
    println("------------------------------")
    val stringPlus: (String, String) -> String = String::plus
    val intPlus: Int.(Int) -> Int = Int::plus

    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("Hello, ", "world!"))

    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3)) // 类扩展调用

    println("--------------lambda----------------")
    testLambda()

    println("----------------niming--------------")
    testNiMing()

    println("----------------closure--------------")
    testClosure()


    // 带有接收者的函数字面值
    println("------------------------------")
    val sum1: Int.(Int) -> Int = { other -> plus(other) }

    // 匿名函数语法
    val sum2 = fun Int.(other: Int): Int = this + other

    // 当接收者类型可以从上下文推断时，lambda 表达式可以用作带接收者的函数字面值。
    class HTML {
        fun body() { /*……*/ }
    }
    fun html(init: HTML.() -> Unit): HTML {
        val html = HTML()  // 创建接收者对象
        html.init()        // 将该接收者对象传给该 lambda
        return html
    }
    html {       // 带接收者的 lambda 由此开始
        body()   // 调用该接收者对象的一个方法
    }
}