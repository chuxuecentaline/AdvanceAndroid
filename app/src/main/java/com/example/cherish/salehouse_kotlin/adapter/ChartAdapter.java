package com.example.cherish.salehouse_kotlin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.cherish.salehouse_kotlin.adapter.viewHolder.MultiTypeSupport;
import com.example.cherish.salehouse_kotlin.adapter.viewHolder.ViewHolder;

import java.util.List;

/**
 * 行情 (多布局)
 * Created by cherish
 */

public class ChartAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final MultiTypeSupport mSupport;
    private final Context mContext;
    private final List<String> mData;
    private final LayoutInflater mInflater;

    public ChartAdapter(Context context, List<String> data, MultiTypeSupport support) {
        mContext = context;
        mData = data;
        mSupport = support;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //多布局支持
        return new ViewHolder(mInflater.inflate(viewType, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mSupport.getLayoutId(null,position);
                ///super.getItemViewType(position);
    }
}
