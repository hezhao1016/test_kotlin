// 可以使用 @JvmName 注解修改生成的 Java 类的类名
// @file:JvmName("Example")

// 如果多个文件中生成了相同的 Java 类名（包名相同并且类名相同或者有相同的 @JvmName 注解）通常是错误的。
// 然而，编译器能够生成一个单一的 Java 外观类，它具有指定的名称且包含来自所有文件中具有该名称的所有声明。 要启用生成这样的外观，请在所有相关文件中使用 @JvmMultifileClass 注解。
// @file:JvmMultifileClass

package com.hz.learnkt.withjava.kotlin

/** Kotlin类
 * Created by hezhao on 2018-06-12 11:36
 */
class Person @JvmOverloads constructor(val firstName: String, val lastName: String, var age: Int = 22) {
    // val会生成getter()方法, var会生成getter()、setter()方法

    // 将 Kotlin 属性作为字段暴露
    @JvmField
    var ID = 10000

    companion object {
        // 静态字段
        @JvmField
        val VERSION = "v1.2.41"

        // 静态方法
        @JvmStatic fun foo() {
            println("this is static method foo()...")
        }
    }

    // 实例方法
    fun sayHello(){
        println("My Name is $firstName-$lastName, And I is $age years old.")
    }

    // 生成重载
    // 通常，如果你写一个有默认参数值的 Kotlin 函数，在 Java 中只会有一个所有参数都存在的完整参数签名的方法可见，如果希望向 Java 调用者暴露多个重载，可以使用 @JvmOverloads 注解。
    // 该注解也适用于构造函数、静态方法等。它不能用于抽象方法，包括在接口中定义的方法。
    @JvmOverloads
    fun sayHello(name:String, age:Int=22){
        println("Hello, $name")
    }

    /*
    对于每一个有默认值的参数，都会生成一个额外的重载，这个重载会把这个参数和它右边的所有参数都移除掉。
    上例中，会生成以下代码 ：
    构造函数：
        Person(int x, double y)
        Person(int x)
    方法
        void sayHello(String name, int age) { }
        void sayHello(String name) { }
        void sayHello() { }
    */

}

// 包级函数
// 在 com.hz.learnkt.withjava.kotlin 包内的 Example.kt 文件中声明的所有的函数和属性，包括扩展函数，都编译成一个名为 com.hz.learnkt.withjava.kotlin.ExampleKt 的 Java 类的静态方法。
fun sum(a:Int, b:Int):Int{
    return a + b
}