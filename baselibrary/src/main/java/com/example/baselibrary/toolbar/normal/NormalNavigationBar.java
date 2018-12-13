package com.example.baselibrary.toolbar.normal;

import android.support.v7.app.AppCompatActivity;

import com.example.baselibrary.R;
import com.example.baselibrary.toolbar.NavigationBar;

/**
 * 普通的toolBar
 * 返回按钮
 * 名称
 * 菜单
 * Created by cherish
 */

public class NormalNavigationBar extends NavigationBar<NormalNavigationParams> {
    public NormalNavigationBar(NormalNavigationParams params) {
        super(params);
    }


    @Override
    public int bindLayoutId() {
        return R.layout.toolbar;
    }


    @Override
    public void applyParams() {
        setToolBar(R.id.toolBar);
        setTitle(getParams().mTitle);
        setBackIcon(getParams().mBackResId);

    }

    @Override
    public void inAnimal(int distanceY) {
        super.inAnimal(distanceY);
    }

    @Override
    public void outAnimal(int distanceY) {
        super.outAnimal(distanceY);
    }

    public static class Build extends NavigationBar.Build {

        private final NormalNavigationParams P;
        private final AppCompatActivity mActivity;

        public Build(AppCompatActivity activity) {
            super(activity);
            P = new NormalNavigationParams(activity);
            mActivity = activity;

        }

        public NormalNavigationBar.Build setBackIcon(int resId) {
            P.mBackResId = resId;
            return this;
        }

        public NormalNavigationBar.Build setTitle(String title) {
            P.mTitle = title;
            return this;
        }

        public NormalNavigationBar.Build setTitle(int resId) {
            P.mTitle = mActivity.getResources().getString(resId);
            return this;
        }

        public NormalNavigationBar.Build setRightMenu(int navigation_tab) {
            return this;
        }

        public NormalNavigationBar create() {
            NormalNavigationBar navigationBar = new NormalNavigationBar(P);
            return navigationBar;
        }
    }
}
