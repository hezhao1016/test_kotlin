//包的声明，可选，目录与包的结构无需匹配：源代码可以在文件系统的任意位置。
package com.hz.learnkt.started

// 导入包
import java.io.File

/**
 * 基本语法
 *
 * Kotlin 1.2 中文文档：http://www.kotlincn.net/docs/reference/
 *
 * Created by hezhao on 2017-07-20 16:04.
 */


/**
 * 程序入口
 */
fun main(args: Array<String>) {
    // 输出字符 代码结尾不需要写分号
    println("Hello world")


    // 调用函数
    println("-----------------------调用函数---------------------------")
    println(sum(1, 2))
    println(multiply(1, 2))
    println(sub(1F, 2F))
    println(printSum(1, 2))
    printSum2(1, 2)
    val r = div(8.0, 3.0)
    println(r)
    div(8.0, 3)
    println(maxOf(10, 9))
    println(PM.name)
    println(PI)


    // 只读变量
    println("-----------------------变量---------------------------")
    val age:Int = 22  // 明确类型
    val name = "小明" // 自动推断出类型
    val email:String  // 如果没有初始值 则需要明确类型
    email = "hezhao_java@163.com"
    println(email)


    // 可变变量
    var height:Double = 176.0  // 明确 `Double` 类型
    var score = 90.5F  // 自动推断出 `Float` 类型
    score += 2
    println(score)


    // 变量类型
    var b1:String   // 字符串
    var b2:Char     // 字符
    var b3:Int      // 整型
    var b4:Double   // 双浮点
    var b5:Float    // 浮点数
    var b6:Byte     // 字节
    var b7:Long     // 长整型
    var b8:Boolean  // 布尔


    // 这是一个行注释
    /* 这是一个多行的
       块注释。 */


    // 使用字符串模板
    println("-------------------------字符串模板-------------------------")
    var a = 1
    // 模板中的简单名称：
    val s1 = "a is $a"
    println(s1)

    a = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)


    // 使用条件表达式
    println("------------------------条件表达式--------------------------")
    val result = if (a == 2) "==" else "!="
    println(result)


    // 使用可空值及 null 检测
    // 当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空
    println("------------------------可空值及 null 检测--------------------------")
    var str:String? = returnNull()  // 可空变量

    // 直接使用 `str.length` 会导致编译错误，因为他们可能为 null
    // println(str.length)

    if(str != null){
        // 在空检测后，str 会自动转换为非空值
        println(str.length)
    } else {
        println("str is null")
    }


    // 使用类型检测及自动类型转换
    // is 运算符检测一个表达式是否某类型的一个实例。 如果一个不可变的局部变量或属性已经判断出为某类型，那么检测后的分支中可以直接当作该类型使用，无需显式转换
    println("-------------------------类型检测及自动类型转换-------------------------")
    println(getStringLength(1))
    println(getStringLength("abc"))
    println(getStringLength2(true))
    println(getStringLength2("abc"))
    println(getStringLength3({}))
    println(getStringLength3("abc"))


    // 使用扩展函数
    println("----------------------扩展函数----------------------------")
    val nstr = "Horace".spaceToCamelCase()
    println(nstr)


    // 获取目录下的所有文件
    println("------------------------文件--------------------------")
    val files = File(".").listFiles()
    files.forEach { println("  -> " + it.name) }


    // 使用 for 循环
    println("------------------------for 循环--------------------------")
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }

    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }


    // 使用 while 循环
    println("--------------------------while 循环------------------------")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }


    // 使用 when 表达式
    println("--------------------------when 表达式------------------------")
    fun describe(obj: Any): String =
            when (obj) {
                1          -> "One"
                "Hello"    -> "Greeting"
                is Long    -> "Long"
                !is String -> "Not a string"
                else       -> "Unknown"
            }
    println(describe("Hello"))


    // 使用区间（range）
    println("--------------------------区间（range）------------------------")
    // 使用 in 运算符来检测某个数字是否在指定区间内
    val x = 10
    val y = 9
    if (x in 1..y+1) {
        println("fits in range")
    }

    // 检测某个数字是否在指定区间外
    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range too")
    }

    // 区间迭代
    for (x in 1..5) {
        print("$x ")
    }
    println()

    // 或数列迭代
    for (x in 1..10 step 2) {
        print("$x ")
    }
    println()

    for (x in 9 downTo 0 step 3) {
        print("$x ")
    }
    println()


    // 使用集合
    println("--------------------------集合------------------------")
    // 对集合进行迭代
    for (item in items) {
        println(item)
    }

    // 使用 in 运算符来判断集合内是否包含某实例
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    // 使用 lambda 表达式来过滤（filter）和映射（map）集合
    val fruits = listOf<String>("orange", "apple", "grape", "ask")
    fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }


    // 创建基本类及其实例
    println("--------------------------类和对象------------------------")
    val book = Book("吴承恩", "西游记") // 不需要“new”关键字
    println(book)


    // 操作符
    println("--------------------------操作符------------------------")
    var stu = Student()
    stu.test1()
}


/**
 * 定义函数
 */
fun sum(a:Int, b:Int):Int{
    return a + b
}

/**
 * 将表达式作为函数体、返回值类型自动推断的函数
 */
fun multiply(a: Int, b: Int) = a * b

/**
 * if 可以作为表达式
 */
fun sub(a:Float, b:Float):Float = if(a > b) a-b else b-a

/**
 * 返回空值
 */
fun returnNull():String?{
    return null //不能赋null值
}

/**
 * 函数返回无意义的值
 */
fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

/**
 * Unit 返回类型可以省略
 * 推荐用法：如果函数返回 Unit，那么应该省略返回类型
 */
fun printSum2(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

/**
 * 除法
 */
fun div(a:Double,b:Double):Double{
    println("div of $a and $b is ${a/b}")
    return a/b
}

// 重载 无返回值
fun div(a:Double,b:Int){
    println("div of $a and $b is ${a/b}")
}

// 最大值
fun maxOf(a: Int, b: Int) = if (a > b) a else b

/**
 * 扩展函数
 */
fun String.spaceToCamelCase(): String{
    return "Hello " + this
}

/**
 * 创建单例
 */
object PM {
    val name:String = "Name"
}

// 顶层变量
val PI = 3.14
var x = 0


// 使用类型检测及自动类型转换
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
    }

    // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
    return null
}

fun getStringLength2(obj: Any): Int? {
    if (obj !is String) return null

    // `obj` 在这一分支自动转换为 `String`
    return obj.length
}

fun getStringLength3(obj: Any): Int? {
    // `obj` 在 `&&` 右边自动转换成 `String` 类型
    if (obj is String && obj.length > 0) {
        return obj.length
    }

    return null
}

// 实体类
data class Book (val author:String, val subject:String)

// 操作符
class Student{

    fun test1(){
        var i:Double? = getInfo()?.toDouble()
//         if(i != null)
        i?.let { println( i * 2) }

        // 可为null类型
        var s:String? = null
//         var l = if (s!=null) s.length else 0

        // 安全调用操作符
//         var l =s?.length
        s?.let{println("---  "+it.length)}

        //艾维斯操作符
//         val l = s?.length ?: -1

        //不为null 返回长度 否则抛出异常
//         var l = s!!.length

        //类型转换  如不成功给个 null
        val aInt: Int? = s as? Int
    }

    fun getInfo():Int?{
//        return null
        return 3
    }

}