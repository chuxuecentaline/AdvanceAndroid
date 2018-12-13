package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.ioc.onClick;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.view.FlowerView;

import io.reactivex.Flowable;

public class FlowerActivity extends BaseActivity {

    @BindView(R.id.flower)
    FlowerView flower;
    @BindView(R.id.iv_touch)
    ImageView iv_touch;

    @Override
    public int getContentViewId() {
        return R.layout.activity_flower;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        new NormalNavigationBar.Build(this).setTitle("花束直播点赞动画").setRightMenu(R.menu
                .navigation_tab).create();

    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
    @onClick(R.id.iv_touch)
    public void onClick(View view){

        flower.start();

    }
}
