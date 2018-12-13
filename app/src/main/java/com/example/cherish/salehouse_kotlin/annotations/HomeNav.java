package com.example.cherish.salehouse_kotlin.annotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.cherish.salehouse_kotlin.annotations.HomeNav.HOME;
import static com.example.cherish.salehouse_kotlin.annotations.HomeNav.IDLE;
import static com.example.cherish.salehouse_kotlin.annotations.HomeNav.MINE;
import static com.example.cherish.salehouse_kotlin.annotations.HomeNav.NEWS;
import static com.example.cherish.salehouse_kotlin.annotations.HomeNav.STORE;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

@IntDef({IDLE, HOME, NEWS, STORE, MINE})
@Retention(RetentionPolicy.SOURCE)
public @interface HomeNav {

    int IDLE = -1;
    int HOME = 0;
    int NEWS = 1;
    int STORE = 2;
    int MINE = 3;

}
