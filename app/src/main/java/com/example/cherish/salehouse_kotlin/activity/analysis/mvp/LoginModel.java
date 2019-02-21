package com.example.cherish.salehouse_kotlin.activity.analysis.mvp;

import com.example.cherish.salehouse_kotlin.activity.analysis.LoginApi;
import com.example.cherish.salehouse_kotlin.activity.analysis.RetrofitClient;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * FIXME: cherish 请添加描述
 *
 * @Author: cherish
 * @CreateDate: 2019/2/20 16:41
 */

public class LoginModel extends LoginContract.LoginModel {
    @Override
    Observable<BaseResponse<LoginData>> realRequest(LoginReq loginReq) {
       return RetrofitClient.getInstance().create(LoginApi.class).getLogin( loginReq).subscribeOn
                (Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }
}
