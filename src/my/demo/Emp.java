package my.demo;

/**
 * Created by hezhao on 2017-07-19 11:20.
 */
public class Emp {

    private String name;

    public Emp(String name) {
        this.name = name;
    }

    String sayHello(){
        return "我是 "+name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        //在Java中使用kotlin
        int sum = Test1Kt.sum(1, 2);
        System.out.println(sum);
    }
}
