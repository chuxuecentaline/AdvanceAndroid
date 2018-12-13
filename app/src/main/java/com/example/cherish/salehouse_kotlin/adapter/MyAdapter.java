package com.example.cherish.salehouse_kotlin.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.cherish.salehouse_kotlin.R;

import java.math.MathContext;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getContext;

/**
 * 多布局
 * Created by cherish
 */

public class MyAdapter implements MultiAdapter<String> {
    private final List<String> mData;
    private final Context mContext;

    public MyAdapter(Context context, List<String> data) {
        mData = data;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public View getView(int position) {
        TextView textView = new TextView(mContext);
        textView.setText(mData.get(position)+ "data");
        textView.setPadding(10, 10, 10, 10);
        textView.setTextSize(14);
        textView.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        return textView;
    }
}
