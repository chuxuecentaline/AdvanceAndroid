package com.example.cherish.salehouse_kotlin.activity.customView;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.baselibrary.base.BaseActivity;
import com.example.cherish.salehouse_kotlin.view.AlphabeticalIndexView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 自定义字母索引控件
 */
public class AlphabeticalIndexActivity extends BaseActivity {


    private AppCompatTextView tv_tips;
    private AlphabeticalIndexView indexView;


    @Override
    public int getContentViewId() {
        return R.layout.activity_alphabetical_index;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("26个字母索引控件").setRightMenu(R.menu
                .navigation_tab).create();
        tv_tips=findViewById(R.id.tv_tips);
        indexView= findViewById(R.id.indexView);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        indexView.setOnItemClickListener(new AlphabeticalIndexView.OnItemClickListener() {
            @Override
            public void onClick(String tips) {
                tv_tips.setVisibility(View.VISIBLE);
                tv_tips.setText(tips);
            }

            @Override
            public void onGone() {
                //延迟消失
                Flowable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tv_tips.setVisibility(View.GONE);
                    }
                });

            }
        });
    }
}
