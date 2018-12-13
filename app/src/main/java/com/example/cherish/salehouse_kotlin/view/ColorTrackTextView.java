package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * TextView 文本变色
 * Created by cherish
 */

public class ColorTrackTextView extends AppCompatTextView {
    // 默认的字体颜色的画笔
    private Paint mOriginPaint;
    // 改变的字体颜色的画笔
    private Paint mChangePaint;
    // 当前的进度
    private float mCurrentProgress = 0f;

    // 当前文本
    private String mText;
    // 当前朝向
    private Direction mDirection = Direction.DIRECTION_LEFT;

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化画笔
     */
    private void init() {
        mOriginPaint = getPaintByColor(Color.BLACK);
        mChangePaint = getPaintByColor(Color.RED);
    }

    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        // 抗锯齿
        paint.setAntiAlias(true);
        // 仿抖动
        paint.setDither(true);
        // 字体的大小设置为TextView的文字大小
        paint.setTextSize(getTextSize());
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取当前文本
        mText = getText().toString();
        //获取控件的宽度
        int width = getWidth();
        if (!TextUtils.isEmpty(mText)) {
            //根据当前的进度计算中间位置
            int middle = (int) (width * mCurrentProgress);
            // 根据不同的朝向去画字体
            if (mDirection ==Direction. DIRECTION_LEFT) {
                drawOriginDirectionLeft(canvas, middle);
                drawChangeDirectionLeft(canvas, middle);
            }
            if (mDirection == Direction.DIRECTION_RIGHT) {
                drawOriginDirectionRight(canvas, middle);
                drawChangeDirectionRight(canvas, middle);
            }

        }
    }

    private void drawChangeDirectionRight(Canvas canvas, int middle) {
        drawText(canvas,mChangePaint,getWidth()-middle,getWidth());
    }

    private void drawOriginDirectionRight(Canvas canvas, int middle) {
        drawText(canvas,mOriginPaint,0,getWidth()-middle);

    }

    private void drawChangeDirectionLeft(Canvas canvas, int middle) {
        drawText(canvas,mChangePaint,0,middle);
    }

    private void drawOriginDirectionLeft(Canvas canvas, int middle) {
        drawText(canvas,mOriginPaint,middle,getWidth());
    }

    /**
     * 变色文本
     *
     * @param canvas
     * @param middle
     */
    private void drawChange(Canvas canvas, int middle) {
        drawText(canvas, mChangePaint, 0, middle);
    }

    /**
     * 默认文本
     *
     * @param canvas
     * @param middle
     */
    private void drawOrigin(Canvas canvas, int middle) {
        drawText(canvas, mOriginPaint, middle, getWidth());
    }

    /**
     * 绘制文本根据指定的位置
     *
     * @param canvas canvas画布
     * @param paint  画笔
     * @param startX 开始的位置
     * @param endX   结束的位置
     */
    private void drawText(Canvas canvas, Paint paint, int startX, int endX) {
        //保存画笔状态
        canvas.save();
        //截取绘制的内容，待会就只会绘制clipRect设置的参数部分
        canvas.clipRect(startX, 0, endX, getHeight());

        //todo 测量文字的宽高
        Rect rect = new Rect();
        paint.getTextBounds(mText, 0, mText.length(), rect);
        int dx = (getWidth() - rect.width()) / 2;
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int baseLine = (int) (getHeight() / 2 + (fontMetrics.bottom - fontMetrics.top) / 2 -
                fontMetrics.bottom);
        canvas.drawText(mText, dx, baseLine, paint);
        //释放画笔状态
        canvas.restore();
    }

    public void setCurrentProgress(float currentProgress) {
        mCurrentProgress = currentProgress;
        invalidate();
    }

    public void setDirection(Direction direction) {
        mDirection = direction;
    }

    public enum Direction {
        DIRECTION_LEFT, DIRECTION_RIGHT
    }
}
