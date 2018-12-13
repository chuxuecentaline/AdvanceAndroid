package com.example.cherish.salehouse_kotlin.view.parallax;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.cherish.salehouse_kotlin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 视差动画 Fragment
 * Created by cherish
 */

public class ParallaxFragment extends Fragment implements LayoutInflater.Factory2 {

    private static String layoutId = "layoutId";
    private CompatViewInflater mCompatViewInflater;
    //仿系统源码
    private int[] attrsIds = {R.attr.outTranslateX, R.attr.outTranslateY, R.attr.inTranslateX, R
            .attr.inTranslateY, R.attr.outScaleX, R.attr.outScale, R.attr.outAlpha, R.attr
            .outAlpha, R.attr.outRotate, R.attr.inRotate};
    private View childView;
    private List<View> mViewList=new ArrayList<>();

    public static ParallaxFragment newInstance(int layoutId) {
        ParallaxFragment fragment = new ParallaxFragment();
        Bundle args = new Bundle();
        args.putInt(ParallaxFragment.layoutId, layoutId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            int layoutId = getArguments().getInt(ParallaxFragment.layoutId, -1);
            //获取布局的属性
            inflater = inflater.cloneInContext(getActivity());  //fix  A factory has already been
            // set on this LayoutInflater
            LayoutInflaterCompat.setFactory2(inflater, this);

            return inflater.inflate(layoutId, container, false);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        final boolean isPre21 = Build.VERSION.SDK_INT < 21;

        if (mCompatViewInflater == null) {
            mCompatViewInflater = new CompatViewInflater();
        }

        // We only want the View to inherit its context if we're running pre-v21
        final boolean inheritContext = isPre21 && shouldInheritContext((ViewParent) parent);

        @SuppressLint("RestrictedApi") View view = mCompatViewInflater.createView(parent, name,
                context, attrs, inheritContext, isPre21, /* Only read android:theme pre-L (L+
                handles this anyway) */
                true, /* Read read app:theme as a fallback at all times for legacy reasons */
                VectorEnabledTintResources.shouldBeUsed() /* Only tint wrap the context if
                enabled */);
        if (view != null) {

            analysisAttrs(view, context, attrs);
        }


        return view;


    }

    /**
     * 解析控件属性 （将动画属性和控件 绑定在一起 使用Tag 一一对应）
     *
     * @param view
     * @param context
     * @param attrs
     */
    private void analysisAttrs(View view, Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, attrsIds);
        //仿KeyboardView 源码解析 attr
        int n = array.getIndexCount();
        System.out.println("attr----------->"+n);
        AttrParams params = new AttrParams();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            System.out.println("attr----------->"+attr);
            float attrP;

            switch (attr) {
                case 0:
                    attrP = array.getFloat(attr, 1);
                    params.outTranslateX = attrP;
                    break;
                case 1:
                    attrP = array.getFloat(attr, 1);
                    params.outTranslateY = attrP;
                    break;
                case 2:
                    attrP = array.getFloat(attr, 1);
                    params.inTranslateX = attrP;
                    break;
                case 3:
                    attrP = array.getFloat(attr, 1);
                    params.inTranslateY = attrP;
                    break;
                case 4:
                    attrP = array.getFloat(attr, 1);
                    params.outScaleX = attrP;
                    break;
                case 5:
                    attrP = array.getFloat(attr, 0);
                    params.outScale = attrP;
                    break;
                case 6:
                    attrP = array.getFloat(attr, 1);
                    params.outAlpha = attrP;
                    break;
                case 7:
                    attrP = array.getFloat(attr, 1);
                    params.inAlpha = attrP;
                    break;
                case 8:
                    attrP = array.getFloat(attr, 1);
                    params.outRotate = attrP;
                    break;
                case 9:
                    attrP = array.getFloat(attr, 1);
                    params.inRotate = attrP;
                    break;
                default:
                    break;
            }


        }
        view.setTag(view.getId(), params);
        mViewList.add(view);
        System.out.println("view------------>" + params.toString());
        array.recycle();


    }

   public List<View> getChildView() {
        return mViewList;
    }

    // 从系统源码贴的，google工程师写的基本不会有问题
    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }

        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (!(parent instanceof View) || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return null;
    }
}
