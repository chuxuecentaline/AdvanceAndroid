package com.example.cherish.salehouse_kotlin.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 工具类
 * Created by cherish
 */

public class DensityUtils {
    public int dip2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context
                .getResources().getDisplayMetrics());
    }
}
