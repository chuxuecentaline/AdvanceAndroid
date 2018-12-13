package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.view.PedometerView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 计步器
 */
public class PedometerActivity extends BaseActivity {
    private PedometerView mPedometerView;


    @Override
    public int getContentViewId() {
        return R.layout.activity_pedometer;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("计步器").setRightMenu(R.menu
                .navigation_tab).create();
        mPedometerView = findViewById(R.id.pedometerView);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //计时器
        Flowable.interval(10, TimeUnit.MILLISECONDS).onBackpressureDrop().observeOn
                (AndroidSchedulers.mainThread()).subscribe(new Subscriber<Long>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(2401);
            }

            @Override
            public void onNext(Long aLong) {
                mPedometerView.setProgress(Integer.valueOf(String.valueOf(aLong)));
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
