package com.example.baselibrary.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public  @interface onClick {
    int[] value();

}
