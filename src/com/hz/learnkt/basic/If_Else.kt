package com.hz.learnkt.basic

/** 选择结构
 * Created by hezhao on 2018-06-12 11:07
 */

fun main(args: Array<String>) {
    println(chosse("星期六"))
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
        if(keyword.contains("六") || keyword.contains("日")|| keyword.contains("天"))
            return "周末 去玩吧"
    }

    return "不知道星期几了"
}