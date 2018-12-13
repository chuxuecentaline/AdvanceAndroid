package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;

/**
 * recyclerView 自定义分割线
 * Created by cherish
 */

public class ItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private Paint mPaint;
    private Paint mTextPaint;
    private int mHeight = 40;
    private Drawable mDivider;
    private String mText;

    public ItemDecoration(Context context) {
        mContext = context;
        mDivider = context.getDrawable(R.drawable.ic_divider);
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.cardview_dark_background));
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//防止抖动
        mTextPaint = new Paint();
        mTextPaint.setColor(context.getResources().getColor(R.color.colorAccent));
        mTextPaint.setAntiAlias(true);//抗锯齿
        mTextPaint.setTextSize(18);
        mTextPaint.setDither(true);//防止抖动
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom += mHeight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount()-1;
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            rect.top = child.getBottom();
            rect.bottom = rect.top + mHeight;
            mText = "分割线";
            //获取文字的范围
            Rect bounds = new Rect();
            mTextPaint.getTextBounds(mText, 0, mText.length(), bounds);
            //获取文字的Metrics 用来计算基线
            Paint.FontMetricsInt pf = new Paint.FontMetricsInt();
            //获取文字的宽高
            int fontTotalHeight = pf.bottom - pf.top;
            //计算基线到中心点的位置
            int offY = fontTotalHeight / 2 - pf.bottom;
            //计算基线位置
            int baseline = (mHeight + fontTotalHeight) / 2 - offY;
            c.drawRect(rect, mPaint);
            c.drawText(mText, parent.getWidth() / 2 - bounds.width() / 2, child.getBottom() +
                    mHeight / 5 + baseline, mTextPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
       /* int childCount = parent.getChildCount();
        Rect rect = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            rect.top = child.getBottom();
            rect.bottom = rect.top + mHeight;
            mDivider.setBounds(rect);
            mDivider.draw(c);
        }*/
    }
}
