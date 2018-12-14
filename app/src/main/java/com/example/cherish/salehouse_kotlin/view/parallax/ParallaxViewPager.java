package com.example.cherish.salehouse_kotlin.view.parallax;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.StatusBarUtils;

import java.util.List;

/**
 * 视差ViewPager
 * Created by cherish
 */

public class ParallaxViewPager extends ViewPager {
    int[] colors = {R.color.colorAccent, R.color.orange_dark, R.color.blue_dark, R.color
            .green_dark};

    public ParallaxViewPager(@NonNull Context context) {
        this(context, null);
    }


    public ParallaxViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLayout(FragmentManager supportFragmentManager, int[] layoutIds) {
        final ParallaxAdapter adapter = new ParallaxAdapter(supportFragmentManager, layoutIds);
        setAdapter(adapter);
        final List<ParallaxFragment> fragments = adapter.getChildView();
        addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
              //  System.out.println("--------->p" + position + "---------->o" + positionOffset + "------------>s" + positionOffsetPixels);
                //当前页面
                ParallaxFragment outFragment = fragments.get(position);
                if (outFragment != null) {
                    List<View> views = outFragment.getChildView();
                    if (views != null) {
                        for (int i = 0; i < views.size(); i++) {
                            View view = views.get(i);
                            AttrParams tag = (AttrParams) view.getTag(R.id.parallax_tag);
                            view.setTranslationX(-positionOffsetPixels * tag.outTranslateX);
                            view.setTranslationY(-positionOffsetPixels * tag.outTranslateY);
                          //  view.setRotation(positionOffsetPixels * tag.outRotate);
                            view.setAlpha(1 - positionOffset + tag.outAlpha);
                            if(positionOffset>0){
                              //  view.setScaleX(positionOffset * tag.outScaleX);
                                //view.setScaleY(positionOffset * tag.outScaleY);
                            }


                        }

                    }
                }
                //下一页面
                try {
                    ParallaxFragment inFragment = fragments.get(position + 1);
                    if (inFragment != null) {
                        List<View> views = inFragment.getChildView();
                        for (int i = 0; i < views.size(); i++) {
                            View view = views.get(i);
                            AttrParams tag = (AttrParams) view.getTag(R.id.parallax_tag);
                            view.setTranslationX((getMeasuredWidth()-positionOffsetPixels) * tag.inTranslateX);
                            view.setTranslationY((getMeasuredWidth()-positionOffsetPixels) * tag.inTranslateY);
                            //view.setRotation(positionOffsetPixels * tag.inRotate);
                            view.setAlpha(positionOffset + tag.inAlpha);
                            System.out.println("nex" + view);
                            if(positionOffset>0){
                              //  view.setScaleX(positionOffset * tag.inScaleX);
                                //view.setScaleY(positionOffset * tag.inScaleX);
                            }

                        }
                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
