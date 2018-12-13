package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * QQ消息汽包拖拽
 * Created by cherish
 */

public class MessageBubbleView extends View {
    /**
     * 固定点 拖动点
     *
     * @param context
     */
    private PointF mFixationPoint, mDragPoint;
    private PointF mCenterPoint;
    private int mDragRadius = 10;
    /**
     * 固定圆的半径
     */
    private int mFixationRadius;
    private int mFixationRadiusMax = 7;
    private int mFixationRadiusMin = 3;
    private Paint mPaint;

    public MessageBubbleView(Context context) {
        this(context, null);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFixationPoint = new PointF();
        mDragPoint = new PointF();
        mCenterPoint = new PointF();
        mDragRadius = dix2px(mDragRadius);
        mFixationRadiusMax = dix2px(mFixationRadiusMax);
        mFixationRadiusMin = dix2px(mFixationRadiusMin);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);

    }

    private int dix2px(float dragRadius) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dragRadius,
                getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mFixationPoint == null || mDragPoint == null) {
            return;
        }
        canvas.drawCircle(mDragPoint.x, mDragPoint.y, mDragRadius, mPaint);
        if (getBeiSaiPath() != null) {
            canvas.drawPath(getBeiSaiPath(), mPaint);
            canvas.drawCircle(mFixationPoint.x, mFixationPoint.y, mFixationRadius, mPaint);
        }

    }

    /**
     * 贝塞尔曲线
     */
    private Path getBeiSaiPath() {
        mFixationRadius = mFixationRadiusMax - getPointDistance() / 14;
        if (mFixationRadius < mFixationRadiusMin) {
            return null;
        }
        Path path = new Path();
        //已知 ，计算斜率
        double tan = (mFixationPoint.y - mDragPoint.y) / (mFixationPoint.x - mDragPoint.x);
        // 获取角a度值
        double arcTanA = Math.atan(tan);
        //p0
        float p0x = (float) (mDragPoint.x + Math.sin(arcTanA) * mDragRadius);
        float p0y = (float) (mDragPoint.y - Math.cos(arcTanA) * mDragRadius);
        //p1
        float p1x = (float) (mFixationPoint.x + Math.sin(arcTanA) * mFixationRadius);
        float p1y = (float) (mFixationPoint.y - Math.cos(arcTanA) * mFixationRadius);
        //p2
        float p2x = (float) (mFixationPoint.x - Math.sin(arcTanA) * mFixationRadius);
        float p2y = (float) (mFixationPoint.y + Math.cos(arcTanA) * mFixationRadius);
        //p3
        float p3x = (float) (mDragPoint.x - Math.sin(arcTanA) * mDragRadius);
        float p3y = (float) (mDragPoint.y + Math.cos(arcTanA) * mDragRadius);
        //p4 控制点取 两个圆点连线的中心点
        mCenterPoint.set((mDragPoint.x + mFixationPoint.x) / 2, (mDragPoint.y + mFixationPoint.y)
                / 2);
        // 整合贝塞尔曲线路径
        path.moveTo(p0x, p0y);
        path.quadTo(mCenterPoint.x, mCenterPoint.y, p1x, p1y);
        path.lineTo(p2x, p2y);
        path.quadTo(mCenterPoint.x, mCenterPoint.y, p3x, p3y);
        path.close();//闭合
        return path;
    }

    /**
     * 两个圆心之间的距离，利用勾股定理
     *
     * @return
     */
    private int getPointDistance() {

        return (int) Math.sqrt((mFixationPoint.x - mDragPoint.x) * (mFixationPoint.x - mDragPoint
                .x) + (mFixationPoint.y - mDragPoint.y) * (mFixationPoint.y - mDragPoint.y));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFixationPoint.set(event.getX(), event.getY());
                mDragPoint.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mDragPoint.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
}
