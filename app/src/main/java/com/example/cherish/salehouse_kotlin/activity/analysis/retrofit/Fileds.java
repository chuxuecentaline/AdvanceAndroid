package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 网络请求
 *
 * @Author: cherish
 * @CreateDate: 2019/1/31 20:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Fileds {
    String value();
}
