package com.example.cherish.salehouse_kotlin.view.radar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cherish.salehouse_kotlin.utils.DensityUtils;

/**
 * 自定义分割线
 * Created by cherish
 * <p>
 * 2. 区别：RectF Rect
 * (1).精度不一样。Rect是使用int类型作为数值，RectF是使用float类型作为数值。
 * (2).两个类型提供的方法也不是完全一致。
 */

public class ItemDecoration extends RecyclerView.ItemDecoration {

    private final Paint mPaint;
    private final int dividerHeight;

    public ItemDecoration(Context context, int color) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(color);
        dividerHeight = new DensityUtils().dip2px(context, 10);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //绘制在上方


    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //第二种实现
      /* int childCount = parent.getChildCount();
        float top = parent.getPaddingTop();
        float bottom = parent.getHeight()-parent.getPaddingBottom();
        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float left = view.getLeft()+dividerHeight;
            float right = view.getRight();
            c.drawRect(left, top, right, bottom, mPaint);
        }*/
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 第一种实现
        outRect.bottom=dividerHeight;
        outRect.right = dividerHeight;


    }
}
