package com.example.cherish.salehouse_kotlin.activity.analysis.mvp.proxy;

/**
 *静态代理
 *
 * @Author: cherish
 * @CreateDate: 2019/2/21 13:48
 */

public interface IMvpProxy {
    void bindPresenter();//绑定Presenter
    void unbindPresenter();//解绑
}
