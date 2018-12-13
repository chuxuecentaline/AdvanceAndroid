package com.example.cherish.salehouse_kotlin.activity.pattern;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.toolbar.Fold.FoldNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * build  设计模式第二弹
 */
public class BuildActivity extends BaseActivity {
   @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @Override
    public int getContentViewId() {
        return R.layout.activity_build;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        new FoldNavigationBar.Build(this,R.layout.layout_fold_navigation_bar,coordinator)
        .setTitle(R.id.tool_bar,"修心")
       .setBackground(R.id.iv_icon, R.drawable.icon_centaline)
        .create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
