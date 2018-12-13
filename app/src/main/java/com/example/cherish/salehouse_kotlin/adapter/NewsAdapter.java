package com.example.cherish.salehouse_kotlin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.viewHolder.BaseViewHolder;

import java.util.List;

/**
 * 内涵段子系列
 * Created by cherish
 */

public class NewsAdapter extends BaseRecyclerAdapter<String> {

    public NewsAdapter(Context context, List<String> data) {
        super(context, data);

    }

    @Override
    public int layoutResId() {
        return R.layout.item_new;
    }

    @Override
    protected void convert(BaseViewHolder holder, String data) {
        holder.setTitle(R.id.tv_content,data);
    }
}
