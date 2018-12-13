package com.example.cherish.salehouse_kotlin.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class GloberUtils {
    /**
     * 获取屏幕的宽度
     */
    public int getScreenWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
}
