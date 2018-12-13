package com.example.cherish.salehouse_kotlin.activity.customView;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.baselibrary.base.BaseActivity;
import com.example.cherish.salehouse_kotlin.view.LoadingView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class LoadingAnimalActivity extends BaseActivity {


    private LoadingView loadingView;
    private AppCompatTextView tv_content;

    @Override
    public int getContentViewId() {
        return R.layout.activity_loading_animal;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("加载动画").setRightMenu(R.menu.navigation_tab)
                .create();
        loadingView = findViewById(R.id.loadingView);
        tv_content = findViewById(R.id.tv_content);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Flowable.interval(5, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingView.setVisibility(loadingView.getVisibility() == View.VISIBLE ? View
                                .INVISIBLE : View.VISIBLE);
                        tv_content.setVisibility(tv_content.getVisibility() == View.GONE ? View
                                .VISIBLE : View.GONE);
                    }
                });


            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
