package com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base;

import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.proxy.ActivityMvpProxyImpl;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.proxy.ActivityMvpProxy;

/**
 * mvp 开发框架 基类
 *
 * @Author: cherish
 * @CreateDate: 2019/2/20 17:22
 */

public abstract class BaseMvpActivity extends BaseActivity implements BaseView {
    private ActivityMvpProxyImpl mMvpProxy;

    @Override
    protected void init(Bundle savedInstanceState) {
        createMvpProxy();
    }

    /**
     * 创建 Mvp 的代理  自己去写 Fragment
     *
     * @return
     */
    private ActivityMvpProxy createMvpProxy() {
        if (mMvpProxy == null) {
            mMvpProxy = new ActivityMvpProxyImpl<>(this);
        }
        return mMvpProxy;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMvpProxy.unbindPresenter();

    }

}
