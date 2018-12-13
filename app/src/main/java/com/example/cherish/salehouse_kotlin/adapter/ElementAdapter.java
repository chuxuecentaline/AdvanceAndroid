package com.example.cherish.salehouse_kotlin.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cherish.salehouse_kotlin.R;

import java.util.zip.Inflater;

/**
 * 共享元素
 * Created by cherish
 */

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ViewHolder> {
    private final Context mContext;
    private onItemClickListener mItemClickListener;

    public interface onItemClickListener {
        void onClick(int position, ViewHolder holder);
    }


    public ElementAdapter(Context context) {
        mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onClick(position, holder);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        AppCompatTextView tv_content;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }

    public void setItemClickListener(onItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
