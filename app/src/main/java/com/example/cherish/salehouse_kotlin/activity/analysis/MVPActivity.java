package com.example.cherish.salehouse_kotlin.activity.analysis;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.LoginContract;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.LoginPresenter;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base.BaseMvpActivity;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.reJect.MvpPresenter;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;

public class MVPActivity extends BaseMvpActivity implements LoginContract.LoginView {

    @BindView(R.id.tv_content)
    AppCompatTextView tv_content;
    @MvpPresenter
    LoginPresenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mPresenter.request(new LoginReq("18823215170", "123456"));
    }

    @Override
    public void bindView(LoginData loginData) {
        tv_content.setText("------>" + loginData.toString());
    }

    @Override
    public void loading() {
        tv_content.setText("网页加载中...");

    }

    @Override
    public void error() {
        tv_content.setText("网络错误");
    }

    @Override
    public void empty() {
        tv_content.setText("暂无数据");
    }
}
