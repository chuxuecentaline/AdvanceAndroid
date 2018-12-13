package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 视差ViewPager
 * Created by cherish
 */

public class ParallaxViewPager extends ViewPager {

    public ParallaxViewPager(@NonNull Context context) {
        super(context);
    }

    public ParallaxViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
