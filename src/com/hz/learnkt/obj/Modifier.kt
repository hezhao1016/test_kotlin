package com.hz.learnkt.obj

/** 可见性修饰符
 * @Author hezhao
 * @Time   2018-06-13 22:58
 * @Description 无
 * @Version V 1.0
 */

// 类、对象、接口、构造函数、方法、属性和它们的 setter 都可以有 可见性修饰符。 （getter 总是与属性有着相同的可见性。）
// 在 Kotlin 中有这四个可见性修饰符：private、 protected、 internal 和 public。 如果没有显式指定修饰符的话，默认可见性是 public。

/*在包中声明的顶层 函数、属性和类、对象和接口：
- 如果你不指定任何可见性修饰符，默认为 public，这意味着你的声明将随处可见；
- 如果你声明为 private，它只会在声明它的文件内可见；
- 如果你声明为 internal，它会在相同模块内随处可见；
- protected 不适用于顶层声明。
注意：要使用另一包中可见的顶层声明，仍需将其导入进来。

对于类内部声明的成员：
- private 意味着只在这个类内部（包含其所有成员）可见；
- protected—— 和 private一样 + 在子类中可见。
- internal —— 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员；
- public —— 能见到类声明的任何客户端都可见其 public 成员。
注意 对于Java用户：Kotlin 中外部类不能访问内部类的 private 成员。*/

open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4  // 默认 public

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a 不可见
    // b、c、d 可见
    // Nested 和 e 可见

    override val b = 5   // “b”为 protected
}

class Unrelated(o: Outer) {
    // o.a、o.b 不可见
    // o.c 和 o.d 可见（相同模块）
    // Outer.Nested 不可见，Nested::e 也不可见
}


// 默认情况下，所有构造函数都是 public

/*
模块
可见性修饰符 internal 意味着该成员只在相同模块内可见。更具体地说， 一个模块是编译在一起的一套 Kotlin 文件：
- 一个 IntelliJ IDEA 模块；
- 一个 Maven 项目；
- 一个 Gradle 源集（例外是 test 源集可以访问 main 的 internal 声明）；
- 一次 ＜kotlinc＞ Ant 任务执行所编译的一套文件。*/


// 类属性修饰符，标示类本身特性。
//
// abstract    // 抽象类
// final       // 类不可继承，默认属性
// enum        // 枚举类
// open        // 类可继承，类默认是final的
// annotation  // 注解类