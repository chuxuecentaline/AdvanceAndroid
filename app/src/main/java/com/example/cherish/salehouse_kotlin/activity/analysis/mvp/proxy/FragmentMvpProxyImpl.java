package com.example.cherish.salehouse_kotlin.activity.analysis.mvp.proxy;

import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base.BaseView;

/**
 * 静态代理
 *
 * @Author: cherish
 * @CreateDate: 2019/2/21 13:56
 */

public class FragmentMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements ActivityMvpProxy {
    public FragmentMvpProxyImpl(V view) {
        super(view);
    }
    // 不同对待，一般可能不会
}
