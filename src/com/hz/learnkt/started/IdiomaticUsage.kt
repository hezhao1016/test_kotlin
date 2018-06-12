package com.hz.learnkt.started

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/** 习惯用法
 * 一些在 Kotlin 中广泛使用的语法习惯
 *
 * Created by hezhao on 2018-06-12 15:17
 */

// 创建 DTOs（POJOs/POCOs）
data class Customer(val name: String, val email: String)

// 函数的默认参数
fun foo(a: Int = 0, b: String = "") {  }

// 创建单例
object Resource {
    val name = "Name"
}

fun main(args: Array<String>) {

    // 过滤 list
    val list1 = listOf(-1, 1, 2, 3)
    val positives1 = list1.filter { x -> x > 0 }

    // 或者可以更短
    val positives2 = list1.filter { it > 0 }

    // String 内插
    val name = "张三"
    println("Name $name")

    // 类型判断
    val obj:Any = 1
    when (obj) {
        is Int -> println("is int")
        is String -> println("is String")
        else   -> println("other type")
    }

    // 遍历 map / pair型list
    val map1 = mapOf("x" to 1,"y" to 2,"z" to 3)
    for ((k, v) in map1) {  // k、v 可以改成任意名字。
        println("$k -> $v")
    }

    // 使用区间
    for (i in 1..100) {
        // ……
    }
    // 闭区间：包含 100
    for (i in 1 until 100) {
        // ……
    }
    // 半开区间：不包含 100
    for (x in 2..10 step 2) {
        // ……
    }
    for (x in 10 downTo 1) {
        // ……
    }
    if (x in 1..10) {
        // ……
    }


    // 只读 list
    val list = listOf("a", "b", "c")

    // 只读 map
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)

    // 可变 map
    val mutableMap = mutableMapOf("x" to 1, "y" to 2, "z" to 3)
    println(mutableMap["key"])
    mutableMap["C"] = 4

    // 延迟属性
    val p: String by lazy {
        // 计算该字符串
        "lazied"
    }

    // 扩展函数
    fun String.spaceToCamelCase1() {
        // ……
    }
    "Convert this to camelcase".spaceToCamelCase()


    // If not null 缩写
    val files1 = File("Test").listFiles()
    println(files1?.size)

    // If not null and else 缩写
    val files2 = File("Test").listFiles()
    println(files2?.size ?: "empty")

    // if null 执行一个语句
    val values:Map<String, String>? = null
    val email = values?.get("email") ?: throw IllegalStateException("Email is missing!")

    // 在可能会空的集合中取第一元素
    val emails:List<String>? = null // 可能会是空集合
    val mainEmail = emails?.firstOrNull() ?: ""

    // if not null 执行代码
    val value1 = ""
    value1?.let {
        // 代码会执行到此处, 假如data不为null
    }

    // 映射可空值（如果非空的话）
    val value2 = ""
    val mapped = value2?.let { { it.toUpperCase() } } ?: "default-value"

    // 返回 when 表达式
    fun transform1(color: String): Int {
        return when (color) {
            "Red" -> 0
            "Green" -> 1
            "Blue" -> 2
            else -> throw IllegalArgumentException("Invalid color param value")
        }
    }

    // “try /catch”表达式
    fun test() {
        val result = try {
            // ...
        } catch (e: ArithmeticException) {
            throw IllegalStateException(e)
        }

        // 使用 result
    }

    // “if” 表达式
    fun foo(param: Int) {
        val result = if (param == 1) {
            "one"
        } else if (param == 2) {
            "two"
        } else {
            "three"
        }
    }

    // 返回类型为 Unit 的方法的 Builder 风格用法
    fun arrayOfMinusOnes(size: Int): IntArray {
        return IntArray(size).apply { fill(-1) }
    }

    // 单表达式函数
    fun theAnswer1() = 42
    //等价于
    fun theAnswer2(): Int {
        return 42
    }

    // 单表达式函数与其它惯用法一起使用能简化代码，例如和 when 表达式一起使用：
    fun transform(color: String): Int = when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }

    //对一个对象实例调用多个方法 （with）
    class Turtle {
        fun penDown(){  }
        fun penUp(){  }
        fun turn(degrees: Double){  }
        fun forward(pixels: Double){  }
    }

    val myTurtle = Turtle()
    with(myTurtle) {
        // 画一个 100 像素的正方形
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }

    // Java 7 的 try with resources
    val stream = Files.newInputStream(Paths.get("/files/a.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }

    // 对于需要泛型信息的泛型函数的适宜形式
//  public final class Gson {
//     ……
//     public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
//     ……

//    inline fun <reified T : Any> Gson.fromJson(json: JsonElement): T = this.fromJson(json, T::class.java)

    // 使用可空布尔
    val b: Boolean? = null
    if (b == true) {
        // ……
    } else {
        // `b` 是 false 或者 null
    }

}