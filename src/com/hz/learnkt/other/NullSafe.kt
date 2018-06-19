package com.hz.learnkt.other

/** 空安全
 * Created by hezhao on 2018-06-19 08:55
 */

fun main(args: Array<String>) {
    // 在 Kotlin 中，类型系统区分一个引用可以容纳 null （可空引用）还是不能容纳（非空引用）。 例如，String 类型的常规变量不能容纳 null：
    var a: String = "abc"
    // a = null // 编译错误

    // 如果要允许为空，我们可以声明一个变量为可空字符串，写作 String?：
    var b: String? = "abc"
    b = null // ok

    // 现在，如果你调用 a 的方法或者访问它的属性，它保证不会导致 NPE，这样你就可以放心地使用：
    val len = a.length

    // 但是如果你想访问 b 的同一个属性，那么这是不安全的，并且编译器会报告一个错误：
    // val len2 = b.length // 错误：变量“b”可能为空


    // 如何访问可空的属性：

    // 在条件中检查 null
    // 首先，你可以显式检查 b 是否为 null，并分别处理两种可能：
    val len2 = if (b != null) b.length else -1

    // 编译器会跟踪所执行检查的信息，并允许你在 if 内部调用 length。 同时，也支持更复杂（更智能）的条件：
    if (b != null && b.length > 0) {
        print("String of length ${b.length}")
    } else {
        print("Empty string")
    }

    // 请注意，这只适用于 b 是不可变的情况（即在检查和使用之间没有修改过的局部变量 ，或者不可覆盖并且有幕后字段的 val 成员），因为否则可能会发生在检查之后 b 又变为 null 的情况。


    // 安全的调用
    // 你的第二个选择是安全调用操作符，写作 ?.
    val len3 = b?.length
    // 如果 b 非空，就返回 b.length，否则返回 null，这个表达式的类型是 Int?。

    // 安全调用在链式调用中很有用。例如，如果一个员工 Bob 可能会（或者不会）分配给一个部门， 并且可能有另外一个员工是该部门的负责人，那么获取 Bob 所在部门负责人（如果有的话）的名字，我们写作：
    // bob?.department?.head?.name

    // 如果任意一个属性（环节）为空，这个链式调用就会返回 null。

    // 如果要只对非空值执行某个操作，安全调用操作符可以与 let 一起使用：
    val listWithNulls: List<String?> = listOf("A", null)
    for (item in listWithNulls) {
        item?.let { println(it) } // 输出 A 并忽略 null
    }

    // 安全调用也可以出现在赋值的左侧。这样，如果调用链中的任何一个接收者为空都会跳过赋值，而右侧的表达式根本不会求值：
    // 如果 `person` 或者 `person.department` 其中之一为空，都不会调用该函数：
    // person?.department?.head = managersPool.getManager()



    // Elvis 操作符（艾维斯操作符）
    // 当我们有一个可空的引用 r 时，我们可以说“如果 r 非空，我使用它；否则使用某个非空的值 x”：
    val l1: Int = if (b != null) b.length else -1

    // 除了完整的 if-表达式，这还可以通过 Elvis 操作符表达，写作 ?:：
    val l2 = b?.length ?: -1

    // 如果 ?: 左侧表达式非空，elvis 操作符就返回其左侧表达式，否则返回右侧表达式。 请注意，当且仅当左侧为空时，才会对右侧表达式求值。

    // 请注意，因为 throw 和 return 在 Kotlin 中都是表达式，所以它们也可以用在 elvis 操作符右侧。这可能会非常方便，例如，检查函数参数：
    /*fun foo(node: Node): String? {
        val parent = node.getParent() ?: return null
        val name = node.getName() ?: throw IllegalArgumentException("name expected")
        // ……
    }*/



    // !! 操作符
    // 第三种选择是为 NPE 爱好者准备的：非空断言运算符（!!）将任何值转换为非空类型，若该值为空则抛出异常。我们可以写 b!! ，这会返回一个非空的 b 值 （例如：在我们例子中的 String）或者如果 b 为空，就会抛出一个 NPE 异常：

    val l = b!!.length
    // 因此，如果你想要一个 NPE，你可以得到它，但是你必须显式要求它，否则它不会不期而至。

    // 安全的类型转换
    // 如果对象不是目标类型，那么常规类型转换可能会导致 ClassCastException。 另一个选择是使用安全的类型转换，如果尝试转换不成功则返回 null：
    val aInt: Int? = a as? Int

    // 可空类型的集合
    // 如果你有一个可空类型元素的集合，并且想要过滤非空元素，你可以使用 filterNotNull 来实现：
    val nullableList: List<Int?> = listOf(1, 2, null, 4)
    val intList: List<Int> = nullableList.filterNotNull()

}