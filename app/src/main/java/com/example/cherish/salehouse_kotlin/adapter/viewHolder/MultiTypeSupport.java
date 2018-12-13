package com.example.cherish.salehouse_kotlin.adapter.viewHolder;

/**
 * RecyclerView 多布局支持接口
 * Created by cherish
 */

public interface MultiTypeSupport<T>{
    //根据当前位置或者条目数据返回布局
    public int getLayoutId(T item,int position);
}
