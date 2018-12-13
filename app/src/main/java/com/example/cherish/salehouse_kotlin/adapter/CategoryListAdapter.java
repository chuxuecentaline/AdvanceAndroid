package com.example.cherish.salehouse_kotlin.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.viewHolder.ViewHolder;
import com.example.cherish.salehouse_kotlin.bean.BookBean;

import java.util.List;

/**
 * 利用万能通用的Adapter改造后的列表
 * Created by cherish
 */

public class CategoryListAdapter extends CommonRecyclerAdapter<BookBean, Object> {
    public CategoryListAdapter(Context context, List<BookBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemLayout() {
        return R.layout.channel_list_item;
    }

    @Override
    protected void bindData(ViewHolder holder, BookBean bookBean) {

        //holder.setImageResource(R.id.image,R.drawable.ic_launcher_background);
        holder.setText(R.id.tv_content,bookBean.getTitle());


    }

    @Override
    protected void bindHeadData(ViewHolder holder, Object head) {

    }


}
