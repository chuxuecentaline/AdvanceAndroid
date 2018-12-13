package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;

/**
 * 自定义字母索引控件
 * Created by cherish
 */

public class AlphabeticalIndexView extends View {

    public String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private Paint mDefaultPaint;
    private Paint mSelectPaint;
    private float mItemHeight;
    private int mCurrentItem = -1;//当前选中的位置
    private Paint mPathPaint;
    private Path mPath;//选中圆弧的路径

    public AlphabeticalIndexView(Context context) {
        this(context, null);
    }

    public AlphabeticalIndexView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphabeticalIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        //默认字母画笔
        mDefaultPaint = new Paint();
        mDefaultPaint.setTextSize(sp2px(14));
        mDefaultPaint.setColor(Color.GRAY);
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setDither(true);
        //选中字母的画笔
        mSelectPaint = new Paint();
        mSelectPaint.setTextSize(sp2px(16));
        mSelectPaint.setColor(Color.BLUE);
        mSelectPaint.setAntiAlias(true);
        mSelectPaint.setDither(true);
        //选中圆弧的画笔
        mPathPaint = new Paint();
        mPathPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPathPaint.setAntiAlias(true);
        mPathPaint.setDither(true);

    }

    private int sp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, getResources()
                .getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            width = (int) (mDefaultPaint.measureText("A") + getPaddingLeft() + getPaddingRight()
                    + dip2px(10));
        }
        int height = getMeasuredHeight();
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算每个item 的高度
        mItemHeight = getHeight() / letters.length;
        for (int i = 0; i < letters.length; i++) {
            float x = getWidth() / 2 - mDefaultPaint.measureText(letters[i]) / 2;
            //设置居中显示
            Paint.FontMetricsInt fontMetricsInt = mDefaultPaint.getFontMetricsInt();
            int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            //获取基线
            float baseLine = mItemHeight * i + mItemHeight / 2 + dy;
            if (mCurrentItem == i) {
                mPath = new Path();
                float controlX = getPaddingLeft() - dip2px(10);
                float controlY = mItemHeight * i + mItemHeight / 2;
                mPath.moveTo(x, mItemHeight * i);
                mPath.quadTo(controlX, controlY, x, mItemHeight * i);
                mPath.quadTo(controlX, controlY, x, mItemHeight * (i + 1));
                mPath.lineTo(getWidth(), mItemHeight * (i + 1));
                mPath.lineTo(getWidth(), mItemHeight * i);
                mPath.close();
                canvas.drawPath(mPath, mPathPaint);
                canvas.drawText(letters[i], x, baseLine, mSelectPaint);
            } else {
                canvas.drawText(letters[i], x, baseLine, mDefaultPaint);
            }


        }

    }

    private int dip2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources()
                .getDisplayMetrics());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y;
        float index;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
               /* y = event.getY();
                index = y / mItemHeight;
                if (index > 0 && index < letters.length) {
                    mCurrentItem = (int) index;
                }
                break;*/
            case MotionEvent.ACTION_MOVE:
                y = event.getY();
                index = y / mItemHeight;
                if (index > 0 && index < letters.length) {
                    mCurrentItem = (int) index;
                }
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(letters[mCurrentItem]);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onGone();
                }
                break;
        }

        invalidate();
        return true;
    }

    public interface OnItemClickListener {
        void onClick(String tips);

        void onGone();

    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
