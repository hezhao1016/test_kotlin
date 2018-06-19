package com.hz.learnkt.other

import com.hz.learnkt.obj.Animal
import com.hz.learnkt.obj.MyClass
import com.hz.learnkt.withjava.java.Emp

/** 反射
 * Created by hezhao on 2018-06-19 09:21
 */

fun main(args: Array<String>) {
    // 类引用
    val kclazz = MyClass::class // 该引用是 KClass 类型的值

    // Kotlin 类引用与 Java 类引用不同。要获得 Java 类引用， 请在 KClass 实例上使用 .java 属性
    val jclazz = Emp::class.java

    println(kclazz)
    println(jclazz)


    // 绑定的类引用（自 1.1 起）
    val animal: Animal = Animal("小A")
    assert(animal is Animal) { "animal: ${animal::class.qualifiedName}" }


    // 函数引用
    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))


    // 函数组合
    fun length(s: String) = s.length

    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")

    println(strings.filter(oddLength))


    // 属性引用
    println(::x.get())
    println(::x.name)

    ::y.set(2)
    println(y)

    val prop = Z::p
    println(prop.get(Z(1)))

    println(String::lastChar.get("abc"))

}

fun isOdd(x: Int) = x % 2 != 0

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}

val x = 1
var y = 1

val String.lastChar: Char
    get() = this[length - 1]

class Z(val p: Int)