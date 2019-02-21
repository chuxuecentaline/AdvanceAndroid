package com.example.cherish.salehouse_kotlin.activity.analysis.mvp;

import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.BaseResponse;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginData;
import com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean.LoginReq;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * FIXME: cherish 请添加描述
 *
 * @Author: cherish
 * @CreateDate: 2019/2/20 16:41
 */

public class LoginPresenter extends LoginContract.LoginPresenter<LoginContract.LoginView,LoginModel> {

    @Override
    public void request(LoginReq loginReq) {
        getModel().realRequest(loginReq).subscribe(new Observer<BaseResponse<LoginData>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getView().loading();
            }

            @Override
            public void onNext(BaseResponse<LoginData> response) {
                if(response==null){
                    getView().empty();
                }else{
                    getView().bindView(response.getContent());
                }

            }

            @Override
            public void onError(Throwable e) {
                getView().error();
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
