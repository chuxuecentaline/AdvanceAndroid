package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;


import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;

/**
 *
 *
 * @Author: cherish
 * @CreateDate: 2019/2/1 8:48
 */

public interface AccountApi {
    /**
     * 登录
     */
    @POST("api/User/UserLogin")
    Call<BaseResponse<LoginData>> login(@Body LoginReq data);

}
