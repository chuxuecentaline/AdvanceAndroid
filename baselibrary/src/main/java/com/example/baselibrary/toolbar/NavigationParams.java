package com.example.baselibrary.toolbar;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

/**
 * Created by cherish
 */

public class NavigationParams {
    private AppCompatActivity mActivity;

    public NavigationParams(AppCompatActivity activity) {
        mActivity = activity;
    }

    public AppCompatActivity getActivity() {
        return mActivity;
    }

}
