package com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.prac;

import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.ProxyTarget;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.base.BaseModule;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.base.ModuleCall;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;
import com.example.cherish.salehouse_kotlin.bean.WxLoginReq;

/**
 *
 * @Author: cherish
 * @CreateDate: 2019/2/21 15:51
 */
@ProxyTarget(LoginModuleImpl.class)
public interface LoginModule extends BaseModule {
    ModuleCall<BaseResponse<LoginData>> login(LoginReq loginReq);
    ModuleCall<BaseResponse<LoginData>> wxLogin(WxLoginReq loginReq);
}
