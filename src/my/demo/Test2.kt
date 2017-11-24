package my.demo

/**
 * Created by hezhao on 2017-07-19 14:26.
 */
fun main(args: Array<String>) {
//    div(2.2,1.12)

    var a:Int = 1
    var b = 2
    var c:Int
    c = 3

    //行注释
    var x = 5
    x +=1

    /*
    * 块注释
    * */

    //var 可变变量 val 常量
    val s1 = "a is $a";

    a = 2

    val s2 = "${s1.replace("is","was")},but now is $a"

    println(s1)
    println(s2)

    val a2 = a

    println(a == a2)

    println(maxOf(1,2))

    var stu = Student()
    stu.test1()

    testFor()

    testWhile()

}

/**
 * 除法
 */
fun div(a:Double,b:Double):Double{
    println("div of $a and $b is ${a/b}")
    return a/b
}

//重载 无返回值
fun div(a:Double,b:Int){
    println("div of $a and $b is ${a/b}")
}

fun maxOf(a: Int, b: Int) = if (a > b) a else b

fun testFor(){
    var items = listOf("小米","魅族","华为")
    for (item in items){
        println(item)
    }

    for (i in items.indices)    println(items[i])

    for ((index,value) in items.withIndex()){
        println("index is $index value is $value")
    }

    val listOf: List<String?> = listOf<String?>("aaa", null)
    listOf.forEach {
        println(it?.length)
    }

    //for 循环指定次数
//    区间迭代:
    for (x in 1..5) {
        print(x)
    }

//    或数列迭代：
    for (x in 1..10 step 2) {
        print(x)
    }
    for (x in 9 downTo 0 step 3) {
        print(x)
    }

//使用 in 运算符来判断集合内是否包含某实例：
    when {
        "小米" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }

    //使用 lambda 表达式来过滤（filter）和映射（map）集合：
    items
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach(::println)

}

fun testWhile(){
    var size = 10
    var i = 0
    while(i < size){
        println(i++)
    }

    i = 0
    do{
        println("a-- "+i)
        i++;
        if(i > 3)   break
    }while(true)

}

fun testWhen(obj:Any):String =
when(obj){
    1   ->  "一"
    "Hello"    -> "Greeting"
    is Long    -> "Long"
    !is String -> "Not a string"
    else       -> "Unknown"
}

fun testWhen(){
    var i:Any = 0
    when (i) {
        1 -> print("x == 1")
        2 -> {
            print("x == 2")
        }
        3, 4 -> print("x == 0 or x == 1")
        5..10 -> print("x in [5,10]")
        "string" -> print("x == string")
        else -> {
            print("else")
        }
    }


}



class Student{


     fun test1(){
        var i:Double? = getInfo()?.toDouble()
//         if(i != null)
        i?.let { println( i * 2) }

         //可为null类型
         var s:String? = null
//         var l = if (s!=null) s.length else 0;
         //安全调用操作符
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