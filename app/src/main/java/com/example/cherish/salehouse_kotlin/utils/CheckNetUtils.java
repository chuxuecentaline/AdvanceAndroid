package com.example.cherish.salehouse_kotlin.utils;

import com.example.baselibrary.ioc.CheckNet;

import java.lang.reflect.Method;

/**
 *检查网络的工具类
 *
 * @Author: cherish
 * @CreateDate: 2019/2/2 15:53
 */

public class CheckNetUtils {
    private static final CheckNetUtils ourInstance = new CheckNetUtils();

    public static CheckNetUtils getInstance() {
        return ourInstance;
    }

    private CheckNetUtils() {

        //1. ReflexActivity 获取 Activity  对象
        //2.获取 @check 方法
    }
    private void create(Class<?> clazz){
        try {
            Class instance = clazz.getClass().newInstance();
            CheckNet annotation = clazz.getAnnotation(CheckNet.class);
            Method[] methods = annotation.annotationType().getDeclaredMethods();
            for (Method method : methods) {
                method.invoke(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
