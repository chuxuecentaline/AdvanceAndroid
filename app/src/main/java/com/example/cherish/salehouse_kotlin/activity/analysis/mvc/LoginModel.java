package com.example.cherish.salehouse_kotlin.activity.analysis.mvc;

import com.example.cherish.salehouse_kotlin.activity.analysis.LoginApi;
import com.example.cherish.salehouse_kotlin.activity.analysis.RetrofitClient;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * FIXME: cherish 请添加描述
 *
 * @Author: cherish
 * @CreateDate: 2019/2/20 16:30
 */

public class LoginModel {
   private LoginView view;
    public LoginModel(LoginView view) {
        this.view=view;
    }

    public void request(LoginReq loginReq) {
        RetrofitClient.getInstance().create(LoginApi.class).getLogin( loginReq).subscribeOn
                (Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceObserver<BaseResponse<LoginData>>() {

                    @Override
                    public void onNext(BaseResponse<LoginData> response) {
                        view.bindView(response.getContent());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
