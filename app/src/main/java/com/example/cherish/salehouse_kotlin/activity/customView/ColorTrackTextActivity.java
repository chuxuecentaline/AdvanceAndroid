package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.view.ColorTrackTextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 文本渐变
 */
public class ColorTrackTextActivity extends BaseActivity {

    private ColorTrackTextView colorTrackTextView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_color_track_text;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("文本渐变").setRightMenu(R.menu
                .navigation_tab).create();
        colorTrackTextView = findViewById(R.id.colorTrackTextView);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //计时器
        Flowable.interval(100, TimeUnit.MILLISECONDS).onBackpressureDrop().observeOn
                (AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(11);
            }

            @Override
            public void onNext(Long aLong) {
                float progress = (float) aLong / 10;
                colorTrackTextView.setCurrentProgress(progress);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
