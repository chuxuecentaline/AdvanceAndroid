package com.example.cherish.salehouse_kotlin.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.example.cherish.salehouse_kotlin.utils.ToastUtils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 自定义抖动的文本
 * Created by cherish
 */

public class ShakeTextView extends AppCompatTextView {
    List<Integer> temps = new LinkedList<>();
    Map<Integer, List<Integer>> mMap = new LinkedHashMap<>();
    List<Integer> group;
    private LinearLayout.LayoutParams mLayoutParams;
    private String num = "25176";
    private StringBuilder mSb;
    private StringBuilder mSb2;

    public ShakeTextView(Context context) {
        super(context);
    }

    public ShakeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    //  2-》 1 2
    //  5-》 1 2 3 4 5 (6 弹回)
    //  1-》 1
    //  7-》 1 2 3 4 5 6 7 （8 弹回）
    //  6-》 1 2 3 4 5 6 （7 弹回）
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        //位数
        num = getText().toString().trim();
        int length = num.length();
        //拆分 成 2 5 1 7 6
        for (int i = length - 1; i >= 0; i--) {
            int pow = (int) Math.pow(10, i);
            int result = Integer.parseInt(num) / pow;
            temps.add(result);
            num = String.valueOf(Integer.parseInt(num) % pow);
        }
        System.out.println("length->" + temps.toString());

        // 分组
        for (int i = 0; i < temps.size(); i++) {
            int size = temps.get(i);

            if (size >= 5) {
                size++;
            }
            group = new LinkedList<>();
            for (int j = 1; j <= size; j++) {
                group.add(j);
            }
            if (size >= 5) {
                size--;
            }
            mMap.put(size, group);
        }
        System.out.println("length->" + mMap.toString());
        mLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mSb = new StringBuilder();
        //动画
        for (Map.Entry<Integer, List<Integer>> entry : mMap.entrySet()) {
          //  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());



        }
        animal( 2);

}

    /**
     * 从上到下的动画
     *
     * @param key
     */
    private void animal(final Integer key) {
        mSb2 = new StringBuilder();
        int duration = key * 1;

        System.out.println("Key = " + key);
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(1, key);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                System.out.println("progress = " + progress);
               /* if (key == value) {
                    mSb.append(key);
                    setText(mSb.toString());
                } else {
                    mSb2.append(mSb.toString());
                    mSb2.append(value);
                    setText(mSb2.toString());
                }*/
            }
        });
        valueAnimator.start();
        //setTranslationY();


       /* ValueAnimator animator = ValueAnimator.ofInt(1, key);
        animator.setDuration(duration);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer values = (Integer) animation.getAnimatedValue();
                System.out.println(" Value --> " + values);
                if (values != value) {
                    if (key == values) {
                        mSb.append(key);
                        setText(mSb.toString());
                    } else {
                        mSb2.append(mSb.toString());
                        mSb2.append(values);
                        setText(mSb2.toString());
                    }
                }

                setText(values + "");


            }
        });
        animator.start();*/

    }

}
