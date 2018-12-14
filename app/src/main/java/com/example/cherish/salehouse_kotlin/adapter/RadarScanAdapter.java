package com.example.cherish.salehouse_kotlin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.viewHolder.BaseViewHolder;

import java.util.List;

/**
 * 微信雷达扫描
 * Created by cherish
 */

public class RadarScanAdapter extends BaseRecyclerAdapter<String> {

    private View mConstraintLayout;
    private ViewGroup.LayoutParams params;
    private int mItemWidth;
    private int mMiddlePosition;

    public RadarScanAdapter(Context context, List<String> data) {
        super(context, data);
        params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public int layoutResId() {
        return R.layout.radar_item;
    }

    @Override
    protected void convert(BaseViewHolder holder, String data) {
        mConstraintLayout = holder.itemView.findViewById(R.id.constraintLayout);
        params.width=mItemWidth;
        mConstraintLayout.setLayoutParams(params);
        holder.setTitle(R.id.tv_name,"淡化"+data);


    }

    @Override
    public void getPosition(int position, BaseViewHolder holder) {
        super.getPosition(position, holder);
        if(position==0||position>=getItemCount()-1){
            holder.itemView.setAlpha(0);
        }else{
            holder.itemView.setAlpha(1);
        }
        if(mMiddlePosition==position){
            holder.itemView.setScaleY(1f);
            holder.itemView.setScaleX(1.2f);
        }else{
            holder.itemView.setScaleY(0.7f);
            holder.itemView.setScaleX(0.7f);
        }
    }

    public void setItemWidth(int itemWidth){
        mItemWidth=itemWidth;
        notifyDataSetChanged();
    }

    public void setMiddleItem(int middlePosition){
        mMiddlePosition=middlePosition;
        notifyDataSetChanged();
    }
}
