package com.hz.learnkt.other

/** 操作符重载
 * @Author hezhao
 * @Time   2018-06-18 18:12
 * @Description 无
 * @Version V 1.0
 */

// 重载操作符的函数需要用 operator 修饰符标记。
// 表达式	翻译为
// +a	    a.unaryPlus()
// -a	    a.unaryMinus()
// !a	    a.not()
// a++	    a.inc()
// a--	    a.dec()

// 一元操作

// 一元前缀操作符
data class Point(var x: Int, var y: Int)

operator fun Point.unaryMinus() = Point(-x, -y)


// 递增与递减
operator fun Point.inc(): Point {
    val tempx = x
    val tempy = y
    x += 1
    y += 1
    return Point(tempx, tempy)
}


/*
二元操作
算术运算符
表达式	翻译为
a + b	a.plus(b)
a - b	a.minus(b)
a * b	a.times(b)
a / b	a.div(b)
a % b	a.rem(b)、 a.mod(b) （已弃用）
a..b	a.rangeTo(b)
对于此表中的操作，编译器只是解析成翻译为列中的表达式。

请注意，自 Kotlin 1.1 起支持 rem 运算符。Kotlin 1.0 使用 mod 运算符，它在 Kotlin 1.1 中被弃用。*/


data class Counter(val dayIndex: Int) {
    operator fun plus(increment: Int): Counter {
        return Counter(dayIndex + increment)
    }
}


/*
“In”操作符
表达式	    翻译为
a in b	    b.contains(a)
a !in b	    !b.contains(a)
对于 in 和 !in，过程是相同的，但是参数的顺序是相反的。

索引访问操作符
表达式	            翻译为
a[i]	            a.get(i)
a[i, j]	            a.get(i, j)
a[i_1, ……, i_n]	    a.get(i_1, ……, i_n)
a[i] = b	        a.set(i, b)
a[i, j] = b	        a.set(i, j, b)
a[i_1, ……, i_n] = b	a.set(i_1, ……, i_n, b)
方括号转换为调用带有适当数量参数的 get 和 set。

调用操作符
表达式	        翻译为
a()	            a.invoke()
a(i)	        a.invoke(i)
a(i, j)     	a.invoke(i, j)
a(i_1, ……, i_n)	a.invoke(i_1, ……, i_n)
圆括号转换为调用带有适当数量参数的 invoke。

广义赋值
表达式	翻译为
a += b	a.plusAssign(b)
a -= b	a.minusAssign(b)
a *= b	a.timesAssign(b)
a /= b	a.divAssign(b)
a %= b	a.remAssign(b), a.modAssign(b)（已弃用）

对于赋值操作，例如 a += b，编译器执行以下步骤：
- 如果右列的函数可用
    - 如果相应的二元函数（即 plusAssign() 对应于 plus()）也可用，那么报告错误（模糊），
    - 确保其返回类型是 Unit，否则报告错误，
    - 生成 a.plusAssign(b) 的代码；
- 否则试着生成 a = a + b 的代码（这里包含类型检查：a + b 的类型必须是 a 的子类型）。
注意：赋值在 Kotlin 中不是表达式。

相等与不等操作符
表达式	翻译为
a == b	a?.equals(b) ?: (b === null)
a != b	!(a?.equals(b) ?: (b === null))

注意：=== 和 !==（同一性检查）不可重载，因此不存在对他们的约定。
这个 == 操作符有些特殊：它被翻译成一个复杂的表达式，用于筛选 null 值。 null == null 总是 true，对于非空的 x，x == null 总是 false 而不会调用 x.equals()。

比较操作符
表达式	翻译为
a > b	a.compareTo(b) > 0
a < b	a.compareTo(b) < 0
a >= b	a.compareTo(b) >= 0
a <= b	a.compareTo(b) <= 0
所有的比较都转换为对 compareTo 的调用，这个函数需要返回 Int 值

属性委托操作符
provideDelegate、 getValue 以及 setValue 操作符函数已在委托属性中描述。
*/


fun main(args: Array<String>) {
    // 重载一元减运算符的示例
    val point = Point(10, 20)
    println(-point)  // 输出“(-10, -20)”
}