package my.demo

/**
 * Created by hezhao on 2017-07-19 19:13.
 */

//实体类
data class Book (val name:String,val email : String)



fun main(args: Array<String>) {
    var book = Book("","")

    filterList()
}

fun foo(a:Int = 1,b:Int = 2) = a+b


fun filterList(){
    //只读
    val list = listOf(1, 2, 3)
    list.filter { x -> x > 1 && x < 3}
    list.filter {it > 1}

    var list2 = list.toMutableList()
    list2.add(3,22)
    list2[3] = 33
    list2.set(3,345)

    println("My Name is HeZhao${list[3]}")

    //使用区间

    for (i in 1..100) {  }  // 闭区间：包含 100
    for (i in 1 until 100) {  } // 半开区间：不包含 100
    for (x in 2..10 step 2) {  }
    for (x in 10 downTo 1) {  }
    if (list[0] in 1..10) {  }

    //在kotlin中，map是只读的，而mutableMap是var类型的
    val map = mapOf<String,Int>("x" to 1,"y" to 2,"z" to 3)

    val sb = StringBuffer()
    sb.append("Hello")
    sb.append(" HaHa")
    sb.append(" ...")

    val l1 = list[0]
    val l_1 = list.get(0)
    var count = list.count()
    val size = list.size

    val m1 = map["x"]
    val m_1 = map.get("x")

    //可修改的map
    val mutableMapOf = mutableMapOf("x" to 1, "y" to 2, "z" to 3)
    mutableMapOf["sd"] = 11

    val mutableListOf = mutableListOf(1, 2, 2)
    mutableListOf.add(234)



}



