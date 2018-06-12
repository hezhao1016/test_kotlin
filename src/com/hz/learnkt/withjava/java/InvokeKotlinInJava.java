package com.hz.learnkt.withjava.java;

import com.hz.learnkt.withjava.kotlin.ExampleKt;
import com.hz.learnkt.withjava.kotlin.Person;

/** Java 中调用Kotlin
 * Created by hezhao on 2018-06-12 11:05
 */
public class InvokeKotlinInJava {
    public static void main(String[] args) {
        // 使用包级函数
        int sum = ExampleKt.sum(1, 2);
        System.out.println(sum);

        // 使用Kotlin类
        // Person person = new Person("张", "三");
        Person person = new Person("张", "三", 22);

        // getter()
        System.out.println(person.getFirstName() + person.getLastName());
        person.setAge(28);

        person.sayHello();

        // 方法重载
        person.sayHello("李四");
        // Kotlin默认方法会生成一个额外的重载
        person.sayHello("王五", 33);

        // 公开实例字段
        System.out.println(person.ID);

        // 静态字段
        System.out.println(Person.VERSION);

        // 静态方法
        Person.foo();

    }
}
