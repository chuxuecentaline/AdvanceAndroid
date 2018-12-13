package com.example.cherish.salehouse_kotlin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制圆形图片
 * Created by cherish
 */

public class CanvasDrawable extends View {

    private Drawable mDrawable;
    private Paint mPaint;


    public CanvasDrawable(Context context) {
        this(context, null);
    }

    public CanvasDrawable(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasDrawable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
           initPaint();
    }

    private void initPaint() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
       // mPaint.setFilterBitmap(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDrawable != null) {
           /* mDrawable.setBounds(0,0,getMeasuredWidth(),getMeasuredHeight());
            mDrawable.draw(canvas);*/
            // 7.把图片变成圆形
            // 画圆
            Bitmap bitmap = drawableToBitmap(mDrawable);

            // 把Bitmap变为圆的
            Bitmap circleBitmap = getCircleBitmap(bitmap);

            // 把圆形的Bitmap绘制到画布上
            canvas.drawBitmap(circleBitmap, 0, 0, null);



        }

    }

    /**
     * 7.获取圆形bitmap
     */
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        // 创建一个Bitmap
        Bitmap circleBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap
                .Config.ARGB_8888);
        Canvas canvas=new Canvas(circleBitmap);
        // 在画布上面画个圆
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,getMeasuredWidth()/2,mPaint);
        // 取圆和Bitmap矩形的交集
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 再把原来的Bitmap绘制到新的圆上面
        canvas.drawBitmap(bitmap,0,0,mPaint);

        return circleBitmap;

    }

    /**
     * 7.从drawable中得到Bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        // 其他类型 ColorDrawable
        // 创建一个什么也没有的bitmap
        Bitmap outBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap
                .Config.ARGB_8888);
        // 创建一个画布
        Canvas canvas = new Canvas(outBitmap);
        drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        drawable.draw(canvas);


        return outBitmap;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
        invalidate();
    }
}
