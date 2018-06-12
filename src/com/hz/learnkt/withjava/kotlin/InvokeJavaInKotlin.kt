package com.hz.learnkt.withjava.kotlin

import com.hz.learnkt.withjava.java.Emp

/** Kotlin 中调用Java
 * Created by hezhao on 2017-07-18 19:48.
 */

fun main(args: Array<String>) {
    // 调用Java类
    // 遵循 Java 约定的 getter 和 setter 的方法（名称以 get 开头的无参数方法和以 set 开头的单参数方法）在 Kotlin 中表示为属性。
    // 请注意，如果 Java 类只有一个 setter，它在 Kotlin 中不会作为属性可见，因为 Kotlin 目前不支持只写（set-only）属性。
    var emp = Emp("张三")
    println(emp.name)
    emp.name = "李四"
    println(emp.sayHello())

    // 使用Java ArrayList
    val source = ArrayList<Int>()
    source.add(1)
    source.add(2)
    source.add(3)
    demo(source)
}

fun demo(source: List<Int>) {
    val list = ArrayList<Int>()
    // “for”-循环用于 Java 集合：
    for (item in source) {
        list.add(item * item)
    }
    // 操作符约定同样有效：
    for (i in 0..source.size - 1) {
        list[i] = source[i] * source[i] // 调用 get 和 set
    }

    println(list)
}


/*
返回 void 的方法
如果一个 Java 方法返回 void，那么从 Kotlin 调用时中返回 Unit。 万一有人使用其返回值，它将由 Kotlin 编译器在调用处赋值， 因为该值本身是预先知道的（是 Unit）。

将 Kotlin 中是关键字的 Java 标识符进行转义
一些 Kotlin 关键字在 Java 中是有效标识符：in、 object、 is 等等。 如果一个 Java 库使用了 Kotlin 关键字作为方法，你仍然可以通过反引号（`）字符转义它来调用该方法：
foo.`is`(bar)

已映射类型
Kotlin 特殊处理一部分 Java 类型。这样的类型不是“按原样”从 Java 加载，而是 映射 到相应的 Kotlin 类型。 映射只发生在编译期间，运行时表示保持不变。 Java 的原生类型映射到相应的 Kotlin 类型（请记住平台类型）：
Java 类型       Kotlin 类型
byte            kotlin.Byte
short	        kotlin.Short
int	            kotlin.Int
long	        kotlin.Long
char	        kotlin.Char
float	        kotlin.Float
double	        kotlin.Double
boolean	        kotlin.Boolean

一些非原生的内置类型也会作映射：
Java 类型	            Kotlin 类型
java.lang.Object	    kotlin.Any!
java.lang.Cloneable	    kotlin.Cloneable!
java.lang.Comparable	kotlin.Comparable!
java.lang.Enum	        kotlin.Enum!
java.lang.Annotation	kotlin.Annotation!
java.lang.Deprecated	kotlin.Deprecated!
java.lang.CharSequence	kotlin.CharSequence!
java.lang.String	    kotlin.String!
java.lang.Number	    kotlin.Number!
java.lang.Throwable	    kotlin.Throwable!

Java 的装箱原始类型映射到可空的 Kotlin 类型：
Java type	        Kotlin type
java.lang.Byte	    kotlin.Byte?
java.lang.Short	    kotlin.Short?
java.lang.Integer	kotlin.Int?
java.lang.Long	    kotlin.Long?
java.lang.Character	kotlin.Char?
java.lang.Float	    kotlin.Float?
java.lang.Double	kotlin.Double?
java.lang.Boolean	kotlin.Boolean?

请注意，用作类型参数的装箱原始类型映射到平台类型： 例如，List<java.lang.Integer> 在 Kotlin 中会成为 List<Int!>。
集合类型在 Kotlin 中可以是只读的或可变的，因此 Java 集合类型作如下映射： （下表中的所有 Kotlin 类型都驻留在 kotlin.collections包中）:
Java 类型	    Kotlin 只读类型	    Kotlin 可变类型	            加载的平台类型
Iterator<T>	    Iterator<T>	        MutableIterator<T>	        (Mutable)Iterator<T>!
Iterable<T>	    Iterable<T>	        MutableIterable<T>	        (Mutable)Iterable<T>!
Collection<T>	Collection<T>	    MutableCollection<T>        (Mutable)Collection<T>!
Set<T>	        Set<T>	            MutableSet<T>	            (Mutable)Set<T>!
List<T>	        List<T>	            MutableList<T>	            (Mutable)List<T>!
ListIterator<T>	ListIterator<T>	    MutableListIterator<T>	    (Mutable)ListIterator<T>!
Map<K, V>	    Map<K, V>	        MutableMap<K, V>	        (Mutable)Map<K, V>!
Map.Entry<K, V>	Map.Entry<K, V>	    MutableMap.MutableEntry<K,V> (Mutable)Map.(Mutable)Entry<K, V>!
*/