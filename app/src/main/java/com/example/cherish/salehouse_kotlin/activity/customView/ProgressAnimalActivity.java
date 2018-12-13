package com.example.cherish.salehouse_kotlin.activity.customView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.view.ProgressAnimalView;

public class ProgressAnimalActivity extends BaseActivity implements View.OnClickListener {

    private ProgressAnimalView progress;
    private int mLoad;
    private SoundPool mSp;

    @Override
    public int getContentViewId() {
        return R.layout.activity_progress_animal;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("进度动画").setRightMenu(R.menu.navigation_tab)
                .create();
        progress = findViewById(R.id.progress);
        progress.setOnClickListener(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 100);
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                progress.setCurrentProgress(value);
            }
        });
        valueAnimator.start();

    }

    @Override
    public void onClick(View v) {

    }
}
