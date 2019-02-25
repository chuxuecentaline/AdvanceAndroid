package com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.prac;


import android.arch.lifecycle.MutableLiveData;

import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.BaseModuleImpl;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.base.ModuleCallback;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;
import com.example.cherish.salehouse_kotlin.bean.WxLoginReq;

/**
 * FIXME: cherish 请添加描述
 *
 * @Author: cherish
 * @CreateDate: 2019/2/21 15:41
 */

public class LoginViewModel extends BaseModuleImpl {
    public MutableLiveData<LoginData> mData = new MutableLiveData<>();
    public MutableLiveData<BaseResponse<LoginData>> mWxData = new MutableLiveData<>();

    public MutableLiveData<LoginData> login(LoginReq loginReq) {
        getModule(LoginModule.class).login(loginReq).enqueue(new ModuleCallback<BaseResponse<LoginData>>() {

            @Override
            public void loading() {

            }

            @Override
            public void empty() {

            }

            @Override
            public void onSuccess(BaseResponse<LoginData> result) {
                mData.postValue(result.getContent());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        return mData;

    }

    public MutableLiveData<BaseResponse<LoginData>> wxLogin(WxLoginReq wxLoginReq) {
        getModule(LoginModule.class).wxLogin(wxLoginReq).enqueue(new ModuleCallback<BaseResponse<LoginData>>() {

            @Override
            public void loading() {

            }

            @Override
            public void empty() {

            }

            @Override
            public void onSuccess(BaseResponse<LoginData> result) {
                mWxData.postValue(result);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        return mWxData;
    }


}
