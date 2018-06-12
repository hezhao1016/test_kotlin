package com.hz.learnkt.basic

/** 选择结构
 * Created by hezhao on 2018-06-12 11:07
 */

fun main(args: Array<String>) {
    // 常规if用法
    println(chosse("星期六"))

    val a = 2
    val b = 1

    // 在 Kotlin 中，if是一个表达式，即它会返回一个值。 因此就不需要三元运算符（条件 ? 然后 : 否则）
    var max = if (a > b) a else b

    // if的分支可以是代码块，最后的表达式作为该块的值
    max = if (a > b) {
        println("Choose a")
        a
    } else {
        println("Choose b")
        b
    }

    println(max)

    // 注意：如果使用 if 作为表达式而不是语句（例如：返回它的值或者把它赋给变量），该表达式需要有 else 分支。
}

fun chosse(keyword:String):String{
    if (keyword.equals("星期一")){
        return "周末就结束了，快起来上班啊！"
    }else if(keyword.equals("星期二")){
        return "快起来上班啊！"
    }else if(keyword.equals("星期三")){
        return "快起来上班啊！"
    }else if(keyword.equals("星期四")){
        return "快起来上班啊！"
    }else if(keyword.equals("星期五")){
        return "快起来上班啊！"
    }else{
        if(keyword.contains("六") || keyword.contains("日") || keyword.contains("天"))
            return "周末 去玩吧"
    }

    return "不知道星期几了"
}