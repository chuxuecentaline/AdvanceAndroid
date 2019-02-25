package com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.prac;

import com.example.cherish.salehouse_kotlin.activity.analysis.LoginApi;
import com.example.cherish.salehouse_kotlin.activity.analysis.RetrofitClient;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.BaseModuleImpl;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;
import com.example.cherish.salehouse_kotlin.bean.WxLoginReq;

import io.reactivex.Observable;

/**
 * 具体实现
 *
 * @Author: cherish
 * @CreateDate: 2019/2/22 11:31
 */

public class LoginModuleImpl extends BaseModuleImpl {

    public Observable<BaseResponse<LoginData>> login(LoginReq loginReq) {
        return RetrofitClient.getInstance().create(LoginApi.class).getLogin(loginReq);
    }

    public Observable<BaseResponse<LoginData>> wxLogin(WxLoginReq loginReq) {
        return RetrofitClient.getInstance().create(LoginApi.class).wxLogin(loginReq);
    }
}
