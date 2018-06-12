package com.hz.learnkt.basic

/** 基本类型：数字、字符、布尔值、数组与字符串
 * Created by hezhao on 2018-06-12 16:37
 */

fun main(args: Array<String>) {
    testNumber()
    testChar()
    testBoolean()
    testArray()
    testString()
}

/**
 * 数字
 */
fun testNumber() {
    println("-----------------------------testNumber-----------------------------")

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

    print(creditCardNumber)
    print(hexBytes)
    print(bytes)


    // 表示方式
    // 在 Java 平台数字是物理存储为 JVM 的原生类型，除非我们需要一个可空的引用（如 Int?）或泛型。 后者情况下会把数字装箱。
    // 注意数字装箱不必保留同一性:
    val a: Int = 10000
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println("${a == a} ") // 输出“true”
    println("${a === a} ") // 输出“true”
    println("${boxedA == anotherBoxedA} ") // 输出“true”
    println("${boxedA === anotherBoxedA} ") // ！！！输出“false”！！！


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
    println("-----------------------------testChar-----------------------------")

    // 字符用 Char 类型表示。它们不能直接当作数字
    // 可以显式把字符转换为 Int 数字：
    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt() // 显式转换为数字
    }

    var c:Char = '8'
    var i = decimalDigitValue(c)
    println(i)

    // 当需要可空引用时，像数字、字符会被装箱。装箱操作不会保留同一性。
}

/**
 * 布尔
 */
fun testBoolean() {
    println("-----------------------------testBoolean-----------------------------")

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
 * 数组
 */
fun testArray() {
    println("-----------------------------testArray-----------------------------")

    // 数组在 Kotlin 中使用 Array 类来表示，它定义了 get 和 set 函数（按照运算符重载约定这会转变为 []）和 size 属性，以及一些其他有用的成员函数：
    // 可以使用库函数 arrayOf() 来创建一个数组并初始化元素值
    val array = arrayOf(1, 2, 3)
    array.iterator().forEach { print("$it ") }
    println()

    // 或者用接受数组大小和一个函数参数的 Array 构造函数
    val asc = Array(5, { i -> (i * i).toString() })  // 创建一个 Array<String> 初始化为 ["0", "1", "4", "9", "16"]
    asc.iterator().forEach { print("$it ") }
    println()

    // 如果想创建一个长度指定，每个值都为null的数组，就要用kotlin.arrayOfNulls()函数;
    val arrayOfNulls:Array<String?> = arrayOfNulls(2)
    arrayOfNulls.iterator().forEach { print("$it ") }
    println()

    // 创建一个空数组
    val emptyArray:Array<String?> = emptyArray()
    emptyArray.iterator().forEach { print("$it ") }
    println()

    // 注意: 与 Java 不同的是，Kotlin 中数组是不型变的（invariant）。这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any>，以防止可能的运行时失败（但是你可以使用 Array<out Any>, 参见类型投影）。

    // Kotlin 也有无装箱开销的专门的类来表示原生类型数组: ByteArray、 CharArray 、 ShortArray、IntArray、LongArray、FloatArray、DoubleArray、BooleanArray 等等。
    // 这些类和 Array 并没有继承关系，但是它们有同样的方法属性集。它们也都有相应的工厂方法:
    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]
    x.iterator().forEach { print("$it ") }
    println()
}

/**
 * 字符串
 */
fun testString() {
    println("-----------------------------testString-----------------------------")

    // 字符串用 String 类型表示。字符串是不可变的。 字符串的元素——字符可以使用索引运算符访问: s[i]。 可以用 for 循环迭代字符串:
    val str = "世界和平"
    for (c in str) {
        print("$c ")
    }
    println()

    // 可以用 + 操作符连接字符串。这也适用于连接字符串与其他类型的值， 只要表达式中的第一个元素是字符串
    // 请注意，在大多数情况下，优先使用字符串模板或原始字符串而不是字符串连接。
    val s = "abc" + 1
    println(s + "def")

    // 原始字符串 使用三个引号（"""）分界符括起来，内部没有转义并且可以包含换行和任何其他字符
    var text = """
    for (c in "foo")
        print(c)
"""
    println(text)

    // 可以通过 trimMargin() 函数去除前导空格
    // 默认 | 用作边界前缀，但你可以选择其他字符并作为参数传入，比如 trimMargin(">")。
    text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()
    println(text)

    // 字符串模板
    // 字符串可以包含模板表达式 ，即一些小段代码，会求值并把结果合并到字符串中。 模板表达式以美元符（$）开头，由一个简单的名字构成:
    val i = 10
    println("i = $i") // 输出“i = 10”

    // 或者用花括号括起来的任意表达式:
    val s1 = "abc"
    println("$s1.length is ${s1.length}") // 输出“abc.length is 3”


    // 原始字符串和转义字符串内部都支持模板。 如果你需要在原始字符串中表示字面值 $ 字符（它不支持反斜杠转义），你可以用下列语法：
    val price = """
${'$'}9.99
"""
    println(price)


    // 可变字符串
    val sbuffer = StringBuffer()  // 这里直接使用的Java.lang.StringBuffer
    sbuffer.append("Hello")
    sbuffer.append(" HaHa")
    sbuffer.append(" ...")
    println(sbuffer.toString())

    val sbuilder = StringBuilder()
    sbuilder.append("Hello")
    sbuilder.append(" HaHa")
    sbuilder.append(" ...")
    println(sbuilder.toString())
}
