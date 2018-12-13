package com.example.baselibrary.toolbar;

import android.animation.ValueAnimator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * 自定义导航栏
 * Created by cherish
 */

public abstract class NavigationBar<P extends NavigationParams> implements NavigationBarImpl {
    private P mParams;
    private ActionBar mActionBar;
    private View mView;
    private int mHeight;
    //偏移量
    private float OFFSET;

    public NavigationBar(P params) {
        mParams = params;
        OFFSET = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, mParams.getActivity()
                .getResources().getDisplayMetrics());
        bindView();
    }

    public void bindView() {
        if (mParams != null) {
            ViewGroup activityRoot = (ViewGroup) mParams.getActivity().getWindow().getDecorView();
            ViewGroup parent = (ViewGroup) activityRoot.getChildAt(0);

            mView = LayoutInflater.from(mParams.getActivity()).inflate(bindLayoutId(), parent,
                    false);
            parent.addView(mView, 0);
            applyParams();
        }

    }

    protected P getParams() {
        return mParams;
    }

    /**
     * 设置toolBar
     *
     * @param viewId
     */
    public void setToolBar(int viewId) {
        Toolbar toolbar = getView(viewId);
        AppCompatActivity appCompatActivity = mParams.getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        mActionBar = appCompatActivity.getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);

    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (mActionBar != null) {
            mActionBar.setTitle(title);
        }
    }

    /**
     * 设置返回按钮
     *
     * @param backIcon
     */
    public void setBackIcon(int backIcon) {
        if (mActionBar != null) {
            mActionBar.setIcon(backIcon);
        }
    }

    /**
     * 设置折叠背景图片
     *
     * @param resId
     * @param src
     */
    public void setImage(int resId, int src) {
        ImageView view = getView(resId);
        view.setBackgroundResource(src);

    }


    protected <T extends View> T getView(int viewId) {
        return mView.findViewById(viewId);
    }

    /**
     * 进入动画
     *
     * @param distanceY
     */
    public void inAnimal(int distanceY) {
        if (distanceY == 0) {
            return;
        }
        int moveY = (int) (getToolBarHeight() + OFFSET);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(distanceY < 0 ? -moveY : 0, distanceY <
                0 ? 0 : -moveY);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mView
                        .getLayoutParams();
                params.topMargin = value;
                mView.setLayoutParams(params);
                mView.requestLayout();
            }
        });
        valueAnimator.start();


    }

    /**
     * 退出动画
     *
     * @param distanceY
     */
    public void outAnimal(int distanceY) {
        if (distanceY <= getToolBarHeight() + OFFSET) {
            System.out.println("-----------distanceY" + distanceY);
            System.out.println("-----------getToolBarHeight" + getToolBarHeight());
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mView.getLayoutParams();
            params.topMargin = -distanceY;
            mView.setLayoutParams(params);
            mView.requestLayout();
        }

    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    private float getToolBarHeight() {
        if (mHeight == 0) {
            mHeight = mView.getMeasuredHeight();
        }
        return mHeight;
    }


    //1.布局
    //2.参数
    //3.事件
    public static class Build {
        private final NavigationParams P;

        public Build(AppCompatActivity activity) {
            P = new NavigationParams(activity);
        }


    }
}
