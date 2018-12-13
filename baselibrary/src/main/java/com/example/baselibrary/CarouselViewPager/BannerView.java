package com.example.baselibrary.CarouselViewPager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.baselibrary.R;


/**
 * 布局
 * Created by cherish
 */

public class BannerView extends FrameLayout {

    private AutoViewPager mViewPager;
    private LinearLayout ll_indicator;
    private FrameLayout.LayoutParams mParams;
    private int mWidth;
    private int oldCurrent=0;
    private int mAdapterCount;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.item_banner, this);
        mParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mViewPager = findViewById(R.id.viewPager);
        ll_indicator = findViewById(R.id.ll_indicator);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int index=position%mAdapterCount;
                System.out.println("----------->"+index);
                changeIndicator(index);
                oldCurrent=index;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    /**
     * 切换指示器
     * @param position
     */
    private void changeIndicator(int position) {

        IndicatorView view = (IndicatorView) ll_indicator.getChildAt(position);
        view.setColor(Color.parseColor("#FF4081"));
        IndicatorView view1 = (IndicatorView) ll_indicator.getChildAt(oldCurrent);
        view1.setColor(Color.parseColor("#ffffff"));


    }

    public void setAdapter(ViewPagerViewAdapter adapter) {
        mAdapterCount = adapter.getCount();
        float percentage = adapter.percentage();
        mParams.height= (int) (mWidth/percentage);
        mViewPager.setLayoutParams(mParams);
        mViewPager.setAdapter(adapter);
        int count = adapter.getCount();
        initIndicator(count);
    }

    /**
     * 初始化指示器
     * @param count
     */
    private void initIndicator(int count) {
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < count; i++) {
            IndicatorView indicator=new IndicatorView(getContext());
            params.height= (int) indicator.dip2px(8);
            params.width= (int) indicator.dip2px(8);
            params.rightMargin= (int) indicator.dip2px(10);
            params.gravity=Gravity.RIGHT|Gravity.CENTER;
            indicator.setLayoutParams(params);
            if(i==0){
                oldCurrent=i;
                indicator.setColor(Color.parseColor("#FF4081"));
            }else{
                indicator.setColor(Color.parseColor("#ffffff"));
            }

            ll_indicator.addView(indicator);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
    }
}
