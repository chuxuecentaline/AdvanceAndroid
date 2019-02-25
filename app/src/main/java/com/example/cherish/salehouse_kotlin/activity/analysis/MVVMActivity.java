package com.example.cherish.salehouse_kotlin.activity.analysis;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.prac.LoginViewModel;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;
import com.example.cherish.salehouse_kotlin.bean.WxLoginReq;

public class MVVMActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    AppCompatTextView tv_content;
    @BindView(R.id.tv_wx)
    AppCompatTextView tv_wx;
    private LoginViewModel mLoginViewModel;

    @Override
    public int getContentViewId() {
        return R.layout.activity_mvvm;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        // step0 获取相关的viewModel
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);



    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mLoginViewModel.login(new LoginReq("18823215170", "123456")).observe(this, new
                Observer<LoginData>() {
            @Override
            public void onChanged(@Nullable LoginData loginData) {
                tv_content.setText("data:" + loginData);
            }
        });
        mLoginViewModel.wxLogin(new WxLoginReq("15221080532")).observe(this, new Observer<BaseResponse<LoginData>>() {

            @Override
            public void onChanged(@Nullable BaseResponse<LoginData> response) {
                tv_wx.setText(response.toString());
            }
        });
    }

}
