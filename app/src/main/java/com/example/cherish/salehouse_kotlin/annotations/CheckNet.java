package com.example.cherish.salehouse_kotlin.annotations;

/**
 * 网络注解
 *
 * @Author: cherish
 * @CreateDate: 2019/2/2 15:45
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckNet {

}
