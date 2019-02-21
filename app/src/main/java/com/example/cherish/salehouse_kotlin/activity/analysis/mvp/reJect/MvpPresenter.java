package com.example.cherish.salehouse_kotlin.activity.analysis.mvp.reJect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 多个Presenter
 *
 * @Author: cherish
 * @CreateDate: 2019/2/21 11:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MvpPresenter {
}
