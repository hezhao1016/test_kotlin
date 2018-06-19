package com.hz.learnkt.other

/** 异常
 * Created by hezhao on 2018-06-19 09:06
 */

/*
异常类
Kotlin 中所有异常类都是 Throwable 类的子孙类。 每个异常都有消息、堆栈回溯信息和可选的原因。

使用 throw-表达式来抛出异常：
throw MyException("Hi There!")

使用 try-表达式来捕获异常：
try {
    // 一些代码
} catch (e: SomeException) {
    // 处理程序
} finally {
    // 可选的 finally 块
}

可以有零到多个 catch 块。finally 块可以省略。 但是 catch 和 finally 块至少应该存在一个。

Try 是一个表达式
try 是一个表达式，即它可以有一个返回值：
val a: Int? = try { parseInt(input) } catch (e: NumberFormatException) { null }

try-表达式的返回值是 try 块中的最后一个表达式或者是（所有）catch 块中的最后一个表达式。 finally 块中的内容不会影响表达式的结果。


受检的异常
Kotlin 没有受检的异常。这其中有很多原因，但我们会提供一个简单的例子。

以下是 JDK 中 StringBuilder 类实现的一个示例接口：
Appendable append(CharSequence csq) throws IOException;

这个签名是什么意思？ 它是说，每次我追加一个字符串到一些东西（一个 StringBuilder、某种日志、一个控制台等）上时我就必须捕获那些 IOException。 为什么？因为它可能正在执行 IO 操作（Writer 也实现了 Appendable）…… 所以它导致这种代码随处可见的出现：
try {
    log.append(message)
} catch (IOException e) {
    // 必须要安全
}

这并不好，参见《Effective Java》第三版 第 77 条：不要忽略异常。

Bruce Eckel 在《Java 是否需要受检的异常？》（Does Java need Checked Exceptions?） 中指出：
-> 通过一些小程序测试得出的结论是异常规范会同时提高开发者的生产力和代码质量，但是大型软件项目的经验表明一个不同的结论——生产力降低、代码质量很少或没有提高。

其他相关引证：
-> 《Java 的受检异常是一个错误》（Java's checked exceptions were a mistake）（Rod Waldhoff）
-> 《受检异常的烦恼》（The Trouble with Checked Exceptions）（Anders Hejlsberg）


Nothing 类型
在 Kotlin 中 throw 是表达式，所以你可以使用它（比如）作为 Elvis 表达式的一部分：
val s = person.name ?: throw IllegalArgumentException("Name required")

throw 表达式的类型是特殊类型 Nothing。 该类型没有值，而是用于标记永远不能达到的代码位置。 在你自己的代码中，你可以使用 Nothing 来标记一个永远不会返回的函数：
fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}

当你调用该函数时，编译器会知道执行不会超出该调用：
val s = person.name ?: fail("Name required")
println(s)     // 在此已知“s”已初始化

可能会遇到这个类型的另一种情况是类型推断。这个类型的可空变体 Nothing? 有一个可能的值是 null。如果用 null 来初始化一个要推断类型的值，而又没有其他信息可用于确定更具体的类型时，编译器会推断出 Nothing? 类型：
val x = null           // “x”具有类型 `Nothing?`
val l = listOf(null)   // “l”具有类型 `List<Nothing?>

*/