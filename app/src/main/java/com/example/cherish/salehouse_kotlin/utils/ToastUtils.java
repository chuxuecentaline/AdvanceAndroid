package com.example.cherish.salehouse_kotlin.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 提示框工具类
 * Created by cherish
 */

public class ToastUtils {


    public static void show(Context context, String tips) {
        Toast.makeText(context, tips, Toast.LENGTH_SHORT).show();
    }

}
