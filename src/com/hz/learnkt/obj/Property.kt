package com.hz.learnkt.obj

/** 属性和字段
 * Created by hezhao on 2018-06-13 09:41
 */

// 声明属性
// var 声明为可变的，否则使用只读关键字val
class Address{
    var name: String = ""
    var street: String = ""
    var city: String = ""
    var state: String? = ""
    val zip: String = ""
}

// 使用属性
fun copyAddress(address: Address): Address {
    val result = Address() // Kotlin 中没有“new”关键字
    result.name = address.name // 将调用访问器
    result.street = address.street
    // ……
    return result
}


/*
Getters 与 Setters
声明一个属性的完整语法是:
var <propertyName>[: <PropertyType>] [= <property_initializer>]
[<getter>]
[<setter>]

其初始器（initializer）、getter 和 setter 都是可选的。属性类型如果可以从初始器 （或者从其 getter 返回值，如下文所示）中推断出来，也可以省略。

例如:
var allByDefault: Int? // 错误：需要显式初始化器，隐含默认 getter 和 setter
var initialized = 1 // 类型 Int、默认 getter 和 setter

一个只读属性的语法和一个可变的属性的语法有两方面的不同：
    1、只读属性的用 val开始代替var
    2、只读属性不允许 setter
val simple: Int? // 类型 Int、默认 getter、必须在构造函数中初始化
val inferredType = 1 // 类型 Int 、默认 getter
*/


// 自定义访问器
class Demo1 {

    // getter
    val isEmpty: Boolean
        get() = this.size == 0

    // 从 getter 推断出属性类型
    val size get() = 0  // 具有类型 Int

    // getter、setter
    var stringRepresentation: String
        get() = this.stringRepresentation
        set(value) {
            this.stringRepresentation = value
        }

    // 如果你需要改变一个访问器的可见性或者对其注解，但是不需要改变默认的实现， 你可以定义访问器而不定义其实现:
    var setterVisibility: String = "abc"
        private set // 此 setter 是私有的并且有默认实现

    // 幕后字段
    // 在 Kotlin 类中不能直接声明字段。然而，当一个属性需要一个幕后字段时，Kotlin 会自动提供。这个幕后字段可以使用field标识符在访问器中引用：
    var counter = 0 // 注意：这个初始器直接为幕后字段赋值
        set(value) {
            if (value >= 0) field = value
        }
}
