package com.example.cherish.salehouse_kotlin.activity.analysis;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.AccountApi;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.Callback;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.RetrofitClient;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;

import okhttp3.Call;


public class RetrofitActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    AppCompatTextView tv_content;

    @Override
    public int getContentViewId() {
        return R.layout.activity_rerofit;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        new NormalNavigationBar.Build(this).setTitle("Retrofit源码分析").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        LoginReq loginReq = new LoginReq("18823215170", "123456");
        RetrofitClient.getInstance().create(AccountApi.class).login(loginReq).enqueue(new Callback<BaseResponse<LoginData>>() {
            @Override
            public void onResponse(Call call, BaseResponse<LoginData> response) {
                tv_content.setText("------>" + response.getContent().toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });


    }
}
