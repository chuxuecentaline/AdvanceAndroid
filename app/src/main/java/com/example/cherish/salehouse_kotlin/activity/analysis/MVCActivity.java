package com.example.cherish.salehouse_kotlin.activity.analysis;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvc.LoginModel;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvc.LoginView;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;


public class MVCActivity extends BaseActivity implements LoginView {
    @BindView(R.id.tv_content)
    AppCompatTextView tv_content;
    private LoginModel mModel;

    @Override
    public int getContentViewId() {
        return R.layout.activity_mvc;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        new NormalNavigationBar.Build(this).setTitle("MVC 模式").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mModel = new LoginModel(this);
        mModel.request(new LoginReq("18823215170", "123456"));
    }

    @Override
    public void bindView(LoginData loginData) {
        tv_content.setText("------>" + loginData.toString());
    }
}
