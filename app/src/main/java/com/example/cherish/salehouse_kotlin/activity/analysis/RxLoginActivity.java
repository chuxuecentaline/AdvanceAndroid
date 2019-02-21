package com.example.cherish.salehouse_kotlin.activity.analysis;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.ioc.onClick;
import com.example.baselibrary.rx.rxLogin.RxLogin;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxLoginActivity extends BaseActivity {


    private RxLogin mRxLogin;

    @Override
    public int getContentViewId() {
        return R.layout.activity_rx_login;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        new NormalNavigationBar.Build(this).setTitle("登录").setRightMenu(R.menu.navigation_tab)
                .create();

    }

    @Override
    protected void init(Bundle savedInstanceState) {

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.CAMERA).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {

            }
        });

    }

    @onClick({R.id.login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                mRxLogin = new RxLogin(this);
                mRxLogin.request().subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        toast(o.toString());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;

            default:
                break;
        }

    }
}
