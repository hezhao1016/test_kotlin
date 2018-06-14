package com.hz.learnkt.obj

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

/** 枚举类
 * Created by hezhao on 2018-06-14 16:10
 */

// 定义
// 每个枚举常量都是一个对象。枚举常量用逗号分隔。
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}


// 初始化
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}


// 匿名类
// 枚举常量也可以声明自己的匿名类：
enum class ProtocolState {

    // 注意，如果枚举类定义任何成员，要使用分号将成员定义中的枚举常量定义分隔开，就像在 Java 中一样。
    // 枚举条目不能包含内部类以外的嵌套类型（已在 Kotlin 1.2 中弃用）。

    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}


// 在枚举类中实现接口
// 一个枚举类可以实现接口（但不能从类继承），可以为所有条目提供统一的接口成员实现，也可以在相应匿名类中为每个条目提供各自的实现。只需将接口添加到枚举类声明中即可，如下所示：
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}


// 自 Kotlin 1.1 起，可以使用 enumValues<T>() 和 enumValueOf<T>() 函数以泛型的方式访问枚举类中的常量 ：
enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
    println()
}

inline fun <reified T : Enum<T>> printValuesOf(name: String) {
    print(enumValueOf<T>(name))
    println()
}



fun main(args: Array<String>) {

    val east = Direction.EAST
    println(east)

    val blue = Color.BLUE
    println(blue.rgb)

    // 使用枚举常量
    // 就像在 Java 中一样，Kotlin 中的枚举类也有合成方法允许列出定义的枚举常量以及通过名称获取枚举常量。
    // 如果指定的名称与类中定义的任何枚举常量均不匹配，valueOf() 方法将抛出 IllegalArgumentException 异常。
    val valueOf = Direction.valueOf("EAST")
    val values = Direction.values()

    println(valueOf)
    println(values.joinToString())


    // 使用 enumValues<T>() 和 enumValueOf<T>()
    printAllValues<RGB>() // 输出 RED, GREEN, BLUE
    printValuesOf<RGB>("GREEN")


    // 每个枚举常量都具有在枚举类声明中获取其名称和位置的属性：
    // 枚举常量还实现了 Comparable 接口， 其中自然顺序是它们在枚举类中定义的顺序。
}