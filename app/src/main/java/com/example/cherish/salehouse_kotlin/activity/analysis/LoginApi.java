package com.example.cherish.salehouse_kotlin.activity.analysis;


import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;
import com.example.cherish.salehouse_kotlin.bean.WxLoginReq;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * FIXME: cherish 请添加描述
 *
 * @Author: cherish
 * @CreateDate: 2019/2/20 16:24
 */

public interface LoginApi {
    /**
     * 登录
     */
    @POST("api/User/UserLogin")
    Observable<BaseResponse<LoginData>> getLogin(@Body LoginReq data);

    /**
     * 登录
     */
    @POST("api/User/UserWXLogin")
    Observable<BaseResponse<LoginData>> wxLogin(@Body WxLoginReq wxLoginReq );



}
