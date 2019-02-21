package com.example.cherish.salehouse_kotlin.activity.analysis.mvp;

import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base.BaseModel;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base.BasePresenter;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base.BaseView;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;

import io.reactivex.Observable;

/**
 * 桥接
 *
 * @Author: cherish
 * @CreateDate: 2019/2/20 16:42
 */

public class LoginContract {
    public interface LoginView extends BaseView {
        void bindView(LoginData loginData);
    }

    public static abstract class LoginModel extends BaseModel {
        abstract Observable<BaseResponse<LoginData>> realRequest(LoginReq loginReq);

    }

    public static abstract class LoginPresenter<V extends  BaseView,M extends BaseModel> extends BasePresenter<V,M> {
        public abstract void request(LoginReq loginReq);

    }
}
