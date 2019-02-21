package com.example.cherish.salehouse_kotlin.activity.frame.aidl;

/**
 * FIXME: cherish 请添加描述
 *
 * @Author: cherish
 * @CreateDate: 2019/1/31 10:45
 */

public class Person {
    private static final Person ourInstance = new Person();

    public static Person getInstance() {
        return ourInstance;
    }

    private Person() {
    }
}
