package com.example.cherish.salehouse_kotlin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cherish.salehouse_kotlin.R;

/**
 * 卡片适配器
 * Created by cherish
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private final Context mContext;

    public CardAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_card, parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String string = mContext.getString(R.string.content);
        String substring = string.substring(0, (position + 1) * 60);
        holder.tv_content.setText(substring);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }
}
