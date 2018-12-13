package com.example.cherish.salehouse_kotlin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.viewHolder.BaseViewHolder;

import java.util.List;

/**
 * Created by cherish
 */

public class ViewAdapter extends BaseRecyclerAdapter<String> {

    public ViewAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    public int layoutResId() {
        return R.layout.item_main;
    }

    @Override
    protected void convert(BaseViewHolder holder, String data) {
        holder.setTitle(R.id.atv_content, data);

    }

}
