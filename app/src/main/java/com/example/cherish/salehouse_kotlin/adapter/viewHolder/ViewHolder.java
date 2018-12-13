package com.example.cherish.salehouse_kotlin.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cherish
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    //用来存放子View 减少findViewById的次数
    public ViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    /**
     * 设置TextView 文本
     */
    public ViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置ImageView的资源
     */
    public void setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
    }

    private <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            //直接从ItemView 中取
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;

    }

    /**
     * 设置条目点击事件
     */
    public void setOnIntemClickListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }

    /**
     * 设置条目长按事件
     */
    public void setOnIntemLongClickListener(View.OnLongClickListener listener) {
        itemView.setOnLongClickListener(listener);
    }

}
