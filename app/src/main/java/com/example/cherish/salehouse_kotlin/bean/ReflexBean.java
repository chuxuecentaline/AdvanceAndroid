package com.example.cherish.salehouse_kotlin.bean;

/**
 * 测试反射
 *
 * @Author: cherish
 * @CreateDate: 2019/2/2 15:13
 */

public class ReflexBean {
    private static String names = "Darren";
    private static final ReflexBean ourInstance = new ReflexBean();

    private static ReflexBean getInstance(String name) {
        names = name;
        return ourInstance;
    }

    private ReflexBean() {
    }

    private String getNames() {
        return names;
    }

    public void setNames(String name) {
        names = name;
        print();
    }

    private void print() {
        System.out.println("------------->" + names);
    }
}
