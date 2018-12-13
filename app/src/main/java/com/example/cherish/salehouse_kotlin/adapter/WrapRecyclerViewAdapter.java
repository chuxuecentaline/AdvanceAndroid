package com.example.cherish.salehouse_kotlin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.core.internal.deps.guava.collect.Lists;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 装饰适配模式 recyclerView  仿ListView 加载头部和底部
 * Created by cherish
 */

public class WrapRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final RecyclerView.Adapter mAdapter;
    ArrayList<View> mHeaderViews;
    ArrayList<View> mFooterViews;

    public WrapRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        mHeaderViews = Lists.newArrayList();
        mFooterViews = Lists.newArrayList();
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

            @Override
            public void onChanged() {
                super.onChanged();
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder---------"+viewType);
        if (viewType==10000000) {
            return createHFViewHolder(mHeaderViews.get(0));
        }else if(viewType==20000000){
            return createHFViewHolder(mFooterViews.get(0));
        }
        final int adjPosition = viewType - mHeaderViews.size();

        return mAdapter.onCreateViewHolder(parent, mAdapter.getItemViewType(adjPosition));
    }

    /**
     * 底部
     *
     * @param position
     * @return
     */
    private boolean isFooter(int position) {

        return position >=(mAdapter.getItemCount() + mHeaderViews.size());
    }

    /**
     * 创建底部+头部
     */
    public RecyclerView.ViewHolder createHFViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };

    }

    /**
     * 头部
     *
     * @param position
     * @return
     */
    private boolean isHead(int position) {
        return position < mHeaderViews.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        System.out.println("-----------"+position);
        if (isHead(position)) {
            return;
        } else if (isFooter(position)) {
            return;

        } else {
            int realPosition = position - mHeaderViews.size();
            mAdapter.onBindViewHolder(holder, realPosition);


        }
    }

    /**
     * 修改根据position 判断头部 recyclerView 底部
     *
     * @param position
     * @return
     */
/*
    @Override
    public int getItemViewType(int position) {
        return position;
    }
*/

    @Override
    public int getItemViewType(int position) {
        if (isHead(position)) {
            // 直接返回position位置的key
            return 10000000;
        }
        if (isFooter(position)) {
            // 直接返回position位置的key
            position = position - mHeaderViews.size() - mAdapter.getItemCount();
            return 20000000;
        }
        // 返回列表Adapter的getItemViewType
        position = position - mHeaderViews.size();
        return mAdapter.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mHeaderViews.size() + mAdapter.getItemCount() + mFooterViews.size();
    }

    /**
     * 增加头部
     *
     * @param view
     * @return
     */
    public void addHeaderView(View view) {
        if (!mHeaderViews.contains(view)) {
            mHeaderViews.add(view);
            notifyDataSetChanged();
        }

    }

    /**
     * 增加底部
     *
     * @param view
     * @return
     */
    public void addFooterView(View view) {
        if (!mFooterViews.contains(view)) {
            mFooterViews.add(view);
            notifyDataSetChanged();
        }

    }

    /**
     * 移除头部
     *
     * @param view
     * @return
     */
    public void removeHeaderView(View view) {
        if (mHeaderViews.contains(view)) {
            mHeaderViews.remove(view);
            mHeaderViews.remove(view);
            notifyDataSetChanged();

        }
    }

    /**
     * 移除底部
     *
     * @param view
     * @return
     */
    public void removeFooterView(View view) {
        if (mFooterViews.contains(view)) {
            mHeaderViews.remove(view);
            mHeaderViews.remove(view);
            notifyDataSetChanged();
        }
    }
}
