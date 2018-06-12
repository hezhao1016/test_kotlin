package my.demo

/**
 * Created by hezhao on 2017-07-18 19:48.
 */

fun main(args: Array<String>) {
    println("Hello,Kotlin!")

    println(sum(1,2))

    //调用Java类
    var emp = Emp("张三")
    emp.name = "李四"

    println(emp.sayHello())

    println(chosse("星期六"))

    var flag = 0
    do {
        println("好好学习，天天向上")
        flag ++
    }while(flag < 10)

}

fun sum(a:Int,b:Int):Int{
    return a+b
}

fun sub(a: Int, b: Int) = a - b

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

