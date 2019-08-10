package com.javalemon.serialize.bean;

import java.io.Serializable;

/**
 * @author lemon
 * @date 2019-08-04
 * @desc
 */

public class Dog extends Animal implements Serializable{
    private static final long serialVersionUID = 4115425178199685592L;

    public String name;

    public transient Integer age;

    public static Dog getSizeCompareBean() {
        Dog dog = new Dog();
        dog.name = "dogSize";
        dog.age = 12;
        return dog;
    }

}
