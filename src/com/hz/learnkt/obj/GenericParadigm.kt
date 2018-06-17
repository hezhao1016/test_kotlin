package com.hz.learnkt.obj

/** 泛型
 * Created by hezhao on 2018-06-14 18:04
 */

// 定义
class Box<T>(t: T) {
    var value = t
}


// 声明处型变
// 标注 Source 的类型参数 T 来确保它仅从 Source<T> 成员中返回（生产），并从不被消费。
// 一般原则是：当一个类 C 的类型参数 T 被声明为 out 时，它就只能出现在 C 的成员的输出-位置，但回报是 C<Base> 可以安全地作为 C<Derived>的超类。
// 简而言之，他们说类 C 是在参数 T 上是协变的，或者说 T 是一个协变的类型参数。 你可以认为 C 是 T 的生产者，而不是 T 的消费者。
// out修饰符称为型变注解，并且由于它在类型参数声明处提供，所以我们讲声明处型变。 这与 Java 的使用处型变相反，其类型用途通配符使得类型协变。
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // 这个没问题，因为 T 是一个 out-参数
    // ……
}


// 除了 out，Kotlin 又补充了一个型变注释：in。它使得一个类型参数逆变：只可以被消费而不可以被生产。
// 逆变类型的一个很好的例子是 Comparable：
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
    val y: Comparable<Double> = x // OK！
}


// 使用处型变：类型投影
// fun copy(from: Array<Any>, to: Array<Any>) {
fun copy(from: Array<out Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}

// 这里发生的事情称为类型投影：我们说from不仅仅是一个数组，而是一个受限制的（投影的）数组：我们只可以调用返回类型为类型参数 T 的方法，如上，这意味着我们只能调用 get()。
// 这就是我们的使用处型变的用法，并且是对应于 Java 的 Array<? extends Object>、 但使用更简单些的方式。


// 也可以使用 in 投影一个类型
// Array<in String> 对应于 Java 的 Array<? super String>，也就是说，你可以传递一个 CharSequence 数组或一个 Object 数组给 fill() 函数。
fun fill(dest: Array<in String>, value: String) {
    // ……
}

fun demo2(){
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
    // copy(ints, any) // 错误：期望 (Array<Any>, Array<Any>)
    copy(ints, any)

    fill(any, "")  // 并不会报错
}


// 泛型函数, 类型参数要放在函数名称之前
fun <T> singletonList(item: T): List<T> {
    return listOf(item)
}

fun <T> T.basicToString() : String {  // 扩展函数
    return "basic - ${this}"
}


// 泛型约束
/*
能够替换给定类型参数的所有可能类型的集合可以由泛型约束限制。
最常见的约束类型是与 Java 的 extends 关键字对应的 上界：

fun <T : Comparable<T>> sort(list: List<T>) {
    // ……
}

冒号之后指定的类型是上界：只有 Comparable<T> 的子类型可以替代 T。 例如：
sort(listOf(1, 2, 3)) // OK。Int 是 Comparable<Int> 的子类型
sort(listOf(HashMap<Int, String>())) // 错误：HashMap<Int, String> 不是 Comparable<HashMap<Int, String>> 的子类型

默认的上界（如果没有声明）是 Any?。在尖括号中只能指定一个上界。 如果同一类型参数需要多个上界，我们需要一个单独的 where-子句：
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}
*/


fun main(args: Array<String>) {
    // 一般来说，要创建这样类的实例，我们需要提供类型参数
    val box1: Box<Int> = Box<Int>(1)
    println(box1.value)

    // 但是如果类型参数可以推断出来，例如从构造函数的参数或者从其他途径，允许省略类型参数：
    val box2 = Box(1) // 1 具有类型 Int，所以编译器知道我们说的是 Box<Int>。
    println(box2.value)


    // 泛型函数
    val l = singletonList<Int>(1)
    val l2 = singletonList(1) // 可以省略能够从上下文中推断出来的类型参数
    println(l)
    println(l.basicToString())

}