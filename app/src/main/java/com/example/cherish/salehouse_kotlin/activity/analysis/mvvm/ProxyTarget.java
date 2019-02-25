package com.example.cherish.salehouse_kotlin.activity.analysis.mvvm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @Author: cherish
 * @CreateDate: 2019/2/22 11:14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxyTarget {
    Class<? extends BaseModuleImpl> value();
}
