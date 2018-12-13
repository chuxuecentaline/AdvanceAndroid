package com.example.cherish.salehouse_kotlin.adapter;

import android.content.Context;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.viewHolder.MultiTypeSupport;
import com.example.cherish.salehouse_kotlin.adapter.viewHolder.ViewHolder;
import com.example.cherish.salehouse_kotlin.bean.BookBean;

import java.util.List;

/**
 * 多布局模式
 * Created by cherish
 */

public class CategoryMultiAdapter extends CommonRecyclerAdapter<BookBean, String> {


    public CategoryMultiAdapter(Context context, String head, List<BookBean> data,
                                MultiTypeSupport<String> multiTypeSupport) {
        super(context, head, data, multiTypeSupport);
    }

    @Override
    public int getItemLayout() {
        return R.layout.channel_list_item;
    }

    @Override
    protected void bindData(ViewHolder holder, BookBean s) {


    }

    @Override
    protected void bindHeadData(ViewHolder holder, String head) {
        holder.setText(R.id.tv_title, head);
    }


}
