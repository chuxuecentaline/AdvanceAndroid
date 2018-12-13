package com.example.cherish.salehouse_kotlin.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.example.cherish.salehouse_kotlin.R;

/**
 * Build 设计模式
 * Created by cherish
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    // 用来存放子View减少findViewById的次数
    private SparseArray<View> mViews;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public BaseViewHolder setTitle(int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    private <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
