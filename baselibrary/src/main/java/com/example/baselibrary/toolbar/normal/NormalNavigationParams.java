package com.example.baselibrary.toolbar.normal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.example.baselibrary.toolbar.NavigationParams;

/**
 * 配置参数
 * Created by cherish
 */

public class NormalNavigationParams extends NavigationParams{
    public String mTitle;
    public int mBackResId;

    public NormalNavigationParams(AppCompatActivity activity) {
        super(activity);
    }

}
