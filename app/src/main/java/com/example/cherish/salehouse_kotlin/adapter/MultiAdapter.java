package com.example.cherish.salehouse_kotlin.adapter;

import android.view.View;

/**
 * 适配器模式
 * Created by cherish
 */

public interface MultiAdapter<T> {
    public int getItemCount();
    public View  getView( int position);
}
