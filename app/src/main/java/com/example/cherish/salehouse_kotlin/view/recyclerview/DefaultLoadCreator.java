package com.example.cherish.salehouse_kotlin.view.recyclerview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cherish.salehouse_kotlin.R;

/**
 * Created by cherish
 */

public class DefaultLoadCreator extends LoadMoreViewCreator {
    //加载数据的imageView
    private View mRefreshIv;
    private ValueAnimator mAnimator;
    private Context mContext;
    private float mProgress;
    private AppCompatTextView tv_tips;

    @Override
    public View getLoadView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout.layout_load_footer_view,
                parent, false);
        mRefreshIv = refreshView.findViewById(R.id.iv);
        tv_tips = refreshView.findViewById(R.id.tv_tips);
        mContext = context;
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        tv_tips.setVisibility(View.VISIBLE);
        mRefreshIv.setVisibility(View.GONE);
    }

    @Override
    public void onLoading() {
        tv_tips.setVisibility(View.GONE);
        mRefreshIv.setVisibility(View.VISIBLE);
        mAnimator = ObjectAnimator.ofFloat(mRefreshIv, "rotation", mProgress, 720);
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(-1);
        mAnimator.start();
    }

    @Override
    public void onStopLoad() {
        mRefreshIv.setVisibility(View.GONE);
        if (mAnimator != null) {
            mAnimator.cancel();
            mAnimator = null;
        }

    }
}
