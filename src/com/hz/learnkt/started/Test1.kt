package com.oppo.demo.test

import java.io.File

/**
 * Created by hezhao on 2017-07-20 16:04.
 * Hello world
 */

/**
 * 程序入口
 */
fun main(args: Array<String>) {
    //输出字符 代码结尾不需要写分号
    println("Hello world")

    //只读变量
    val name = "小明" //自动推断出类型
    val age:Int = 22 // 明确类型
    val email:String //如果没有初始值 则需要明确类型
    email = "hezhao_java@163.com"

    //可变变量
    var score = 90.5
    score += 2

    //变量类型
    var b1:String   //字符串
    var b2:Char     //字符
    var b3:Int      //整型
    var b4:Double   //双浮点
    var b5:Float    //浮点数
    var b6:Byte     //字节
    var b7:Long     //长整型
    var b8:Boolean  //布尔

    //变量也是一样
    var str:String? = returnNull()

    if(str == null){
        return
    }

    // 在空检测后，str 会自动转换为非空值
    println(str.length)



    "Convert this to camelcase".spaceToCamelCase()



    val files = File("Test").listFiles()


}

// 创建单例
object PM {
    val name:String = "Name"
}

/**
 * 定义函数
 */
fun sum(a:Int,b:Int):Int{
    return a+b
}

/**
 * 函数可以简写
 * 另外 if 可以作为表达式
 */
fun sub(a:Float,b:Float):Float = if(a > b) a-b else b-a

//返回空值
fun returnNull():String?{
    return null //不能赋null值
}

//扩展函数
fun String.spaceToCamelCase() {


}

