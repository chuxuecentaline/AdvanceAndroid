package com.example.cherish.salehouse_kotlin.view.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 加载更多
 * Created by cherish
 */

public abstract class LoadMoreViewCreator {
    /**
     * 获取上拉加载更多的View
     *
     * @param context
     * @param parent
     * @return
     */
    public abstract View getLoadView(Context context, ViewGroup parent);

    /**
     * 正在上拉
     *
     * @param currentDragHeight 当前拖动的高度
     * @param loadViewHeight    总的加载高度
     * @param currentLoadStatus 当前状态
     */
    public abstract void onPull(int currentDragHeight, int loadViewHeight, int currentLoadStatus);

    /**
     * 正在加载中
     */
    public abstract void onLoading();

    /**
     * 停止加载
     */
    public abstract void onStopLoad();
}
