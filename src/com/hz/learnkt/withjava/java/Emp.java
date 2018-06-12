package com.hz.learnkt.withjava.java;

/**
 * Created by hezhao on 2017-07-19 11:20.
 */
public class Emp {

    private String name;

    public Emp(String name) {
        this.name = name;
    }

    public String sayHello(){
        return "我是 "+ name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
