package com.example.cherish.salehouse_kotlin.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.cherish.salehouse_kotlin.R;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class StatusBarUtils {

    /**
     * 修改状态栏的颜色
     * 兼容4.4 以上的版本
     * @param activity
     * @param colors
     */
    public void setStatusBarColors(Activity activity, int colors) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(colors);
        }else   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //必须加上 addFlags
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View view = new View(activity);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, getStatusBarHeight(activity));
            params.height=40;
            view.setLayoutParams(params);
            view.setBackgroundColor(colors);
            decorView.addView(view);
            ViewGroup contentView = activity.findViewById(android.R.id.content);
            contentView.setPadding(0,getStatusBarHeight(activity),0,0);
            // View activityView = contentView.getChildAt(0);
            // activityView.setFitsSystemWindows(true);
            // activityView.setPadding(0,getStatusBarHeight(activity),0,0);

        }
    }

    /**
     * 设置全屏
     * 兼容4.4 以上的版本
     */
    public void setStatusTranslate(Activity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //看源码 5.0以上
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);

        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

    }

    /**
     * 获取状态栏高度的方法
     *
     * @return
     */
    private int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
