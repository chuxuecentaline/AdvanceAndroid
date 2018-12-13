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


            private View mView;
            public int mCurrentPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
                //当前页面
                ParallaxFragment fragment = fragments.get(position);
                if(fragment!=null){
                    List<View> views = fragment.getChildView();
                    if(views!=null){
                        for (int i = 0; i < views.size(); i++) {
                            View view = views.get(i);
                            AttrParams tag = (AttrParams) view.getTag(view.getId());
                            view.setTranslationX(-positionOffsetPixels*tag.outTranslateX);
                            view.setTranslationY(-positionOffsetPixels*tag.outTranslateY);
                            view.setRotation(tag.outRotate);
                           // view.setAlpha(tag.outAlpha);
                        }

                    }
                }
               //下一页面
                try {
                    ParallaxFragment fragmentNex = fragments.get(position+1);
                    if(fragmentNex!=null){
                        List<View> views = fragmentNex.getChildView();
                        for (int i = 0; i < views.size(); i++) {
                            View view = views.get(i);
                            AttrParams tag = (AttrParams) view.getTag(view.getId());
                            view.setTranslationX(positionOffsetPixels*tag.inTranslateX);
                            view.setTranslationY(positionOffsetPixels*tag.inTranslateY);
                            view.setRotation(tag.inRotate);
                          //  view.setAlpha(tag.inAlpha);
                        }
                    }
                }catch (Exception e){

                }





            }

            @Override
            public void onPageSelected(int position) {
                //  System.out.println("onPageSelected position--->" + position );
                mCurrentPosition = position;
                try {
                    new StatusBarUtils().setStatusBarColors((Activity) getContext(), getResources
                            ().getColor(colors[position]));

                } catch (Exception e) {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
