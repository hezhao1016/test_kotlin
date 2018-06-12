package com.hz.learnkt.basic

/** 基本类型：数字、字符、布尔值、数组与字符串
 * Created by hezhao on 2018-06-12 16:37
 */

fun main(args: Array<String>) {


}

/**
 * 数字
 */
fun testNumber() {
    // 数字没有隐式拓宽转换（如 Java 中 int 可以隐式转换为long）
    /* Kotlin 提供了如下的内置类型来表示数字（与 Java 很相近）：
        Double	64
        Float	32
        Long	64
        Int	32
        Short	16
        Byte	8
    */

    /*字面常量
    数值常量字面值有以下几种:
    - 十进制: 123
        - Long 类型用大写 L 标记: 123L
    - 十六进制: 0x0F
    - 二进制: 0b00001011
    - 注意: 不支持八进制

    Kotlin 同样支持浮点数的常规表示方法:
    - 默认 double：123.5、123.5e10
    - Float 用 f 或者 F 标记: 123.5f
    */

    // 数字字面值中的下划线（自 1.1 起），使数字常量更易读：
    val oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010


    // 表示方式
    // 在 Java 平台数字是物理存储为 JVM 的原生类型，除非我们需要一个可空的引用（如 Int?）或泛型。 后者情况下会把数字装箱。
    // 注意数字装箱不必保留同一性:
    val a: Int = 10000
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    print("${a == a} ") // 输出“true”
    print("${a === a} ") // 输出“true”
    print("${boxedA == anotherBoxedA} ") // 输出“true”
    print("${boxedA === anotherBoxedA} ") // ！！！输出“false”！！！


    // 显式转换
    // 由于不同的表示方式，较小类型并不是较大类型的子类型。因此较小的类型不能隐式转换为较大的类型。
    // 可以显式转换来拓宽数字:
    val b: Byte = 1
//    val i: Int = b // 错误
    val i: Int = b.toInt() // OK: 显式拓宽

    /*支持如下的转换:
    - toByte(): Byte
    - toShort(): Short
    - toInt(): Int
    - toLong(): Long
    - toFloat(): Float
    - toDouble(): Double
    - toChar(): Char
    */

    // 缺乏隐式类型转换并不显著，因为类型会从上下文推断出来，而算术运算会有重载做适当转换，例如：
    val l = 1L + 3 // Long + Int => Long


    /*浮点数比较
    本节讨论的浮点数操作如下：
    - 相等性检测：a == b 与 a != b
    - 比较操作符：a < b、 a > b、 a <= b、 a >= b
    - 区间实例以及区间检测：a..b、 x in a..b、 x !in a..b

    当其中的操作数 a 与 b 都是静态已知的 Float 或 Double 或者它们对应的可空类型（声明为该类型，或者推断为该类型，或者智能类型转换的结果是该类型），两数字所形成的操作或者区间遵循 IEEE 754 浮点运算标准。

    然而，为了支持泛型场景并提供全序支持，当这些操作符并非静态类型为浮点数（例如是 Any、 Comparable<……>、 类型参数）时，这些操作使用为 Float 与 Double 实现的不符合标准的 equals 与 compareTo，这会出现：
    - 认为 NaN 与其自身相等
    - 认为 NaN 比包括正无穷大（POSITIVE_INFINITY）在内的任何其他元素都大
    - 认为 -0.0 小于 0.0
    */
}

/**
 * 字符
 */
fun testChar() {
    // 字符用 Char 类型表示。它们不能直接当作数字
    // 可以显式把字符转换为 Int 数字：
    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt() // 显式转换为数字
    }

    var c:Char = 'A'
    var i = decimalDigitValue(c)
    println(i)

    // 当需要可空引用时，像数字、字符会被装箱。装箱操作不会保留同一性。
}

/**
 * 布尔
 */
fun testBoolean() {
    /*布尔用 Boolean 类型表示，它有两个值：true 和 false。
    若需要可空引用布尔会被装箱。
    内置的布尔运算有：
        || – 短路逻辑或
        && – 短路逻辑与
        ! - 逻辑非
    */
    var flag1:Boolean = true
    var flag2:Boolean = false

    println(flag1)
    println(flag2)
}

/**
 * 字符串
 */
fun testString() {
    // 可变字符串
    val sbuffer = StringBuffer()  // 这里直接使用的Java.lang.StringBuffer
    sbuffer.append("Hello")
    sbuffer.append(" HaHa")
    sbuffer.append(" ...")
    println(sbuffer.toString())

    val sbuilder = StringBuilder()
    sbuffer.append("Hello")
    sbuffer.append(" HaHa")
    sbuffer.append(" ...")
    println(sbuilder.toString())
}
