package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.cherish.salehouse_kotlin.adapter.WrapRecyclerViewAdapter;

/**
 * 装饰模式
 * Created by cherish
 */

public class WrapRecyclerView extends RecyclerView {

    private WrapRecyclerViewAdapter mWrapRecyclerViewAdapter;

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(Adapter adapter) {

        mWrapRecyclerViewAdapter = new WrapRecyclerViewAdapter(adapter);
        super.setAdapter(mWrapRecyclerViewAdapter);
    }


    /**
     * 增加头部
     *
     * @param view
     * @return
     */
    public void addHeaderView(View view) {
        if (mWrapRecyclerViewAdapter != null) {
            mWrapRecyclerViewAdapter.addHeaderView(view);
        }

    }

    /**
     * 增加底部
     *
     * @param view
     * @return
     */
    public void addFooterView(View view) {
        if (mWrapRecyclerViewAdapter != null) {
            mWrapRecyclerViewAdapter.addFooterView(view);
        }

    }

    /**
     * 移除头部
     *
     * @param view
     * @return
     */
    public void removeHeadView(View view) {
        if (mWrapRecyclerViewAdapter != null) {
            mWrapRecyclerViewAdapter.removeHeaderView(view);
        }
    }

    /**
     * 移除底部
     *
     * @param view
     * @return
     */
    public void removeFooterView(View view) {
        if (mWrapRecyclerViewAdapter != null) {
            mWrapRecyclerViewAdapter.removeFooterView(view);
        }
    }
}
