package com.example.cherish.salehouse_kotlin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cherish.salehouse_kotlin.adapter.viewHolder.BaseViewHolder;

import java.util.List;

/**
 * 万能的适配器
 * Created by cherish
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private final Context mContext;
    List<T> mData;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void ItemClickListener(int position);
    }

    public BaseRecyclerAdapter(Context context, List<T> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = new BaseViewHolder(LayoutInflater.from(mContext).inflate
                (layoutResId(), parent, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        convert(holder, mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.ItemClickListener(position);

                }
            }
        });
        getPosition(position,holder);
    }
    /**
     * 获取当前的位置
     * @param position
     * @param holder
     */
    public void getPosition(int position, BaseViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract int layoutResId();

    protected abstract void convert(BaseViewHolder holder, T data);

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
