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

    // 幕后属性
    // 如果你的需求不符合这套“隐式的幕后字段”方案，那么总可以使用 幕后属性（backing property）：
    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数已推断出
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }

    // 延迟初始化属性与变量
    // 一般地，属性声明为非空类型必须在构造函数中初始化。 然而，这经常不方便。例如：属性可以通过依赖注入来初始化， 或者在单元测试的 setup 方法中初始化。 这种情况下，你不能在构造函数内提供一个非空初始器。 但你仍然想在类体中引用该属性时避免空检查。
    // 为处理这种情况，你可以用 lateinit 修饰符标记该属性：
    // 该修饰符只能用于在类体中的属性（不是在主构造函数中声明的 var 属性，并且仅当该属性没有自定义 getter 或 setter 时），而自 Kotlin 1.2 起，也用于顶层属性与局部变量。该属性或变量必须为非空类型，并且不能是原生类型。
    // 在初始化前访问一个 lateinit 属性会抛出一个特定异常，该异常明确标识该属性被访问及它没有初始化的事实。
    /*
    lateinit var subject: TestSubject
    @SetUp fun setup() {
        subject = TestSubject()
    }
    @Test fun test() {
        subject.method()  // 直接解引用
    }
    */

    // 检测一个 lateinit var 是否已初始化（自 1.2 起）
    // 要检测一个 lateinit var 是否已经初始化过，请在该属性的引用上使用 .isInitialized：
    /*if (foo::bar.isInitialized) {
        println(foo.bar)
    }*/
    // 此检测仅对可词法级访问的属性可用，即声明位于同一个类型内、位于其中一个外围类型中或者位于相同文件的顶层的属性。

}

// 编译期常量
// 已知值的属性可以使用 const 修饰符标记为 编译期常量。 这些属性需要满足以下要求：
// - 位于顶层或者是 object 的一个成员
// - 用 String 或原生类型 值初始化
// - 没有自定义 getter
// 这些属性可以用在注解中：
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"
@Deprecated(SUBSYSTEM_DEPRECATED) fun foo() {
    //...
}