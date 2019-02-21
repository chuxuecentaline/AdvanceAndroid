package com.example.cherish.salehouse_kotlin.activity.analysis.mvp.proxy;

import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base.BasePresenter;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base.BaseView;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.reJect.MvpPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 静态代理
 *
 * @Author: cherish
 * @CreateDate: 2019/2/21 13:51
 */

public class MvpProxyImpl<V extends BaseView> implements IMvpProxy {
    private List<BasePresenter> mPresenters;
    private V view;

    public MvpProxyImpl(V view) {
        this.view=view;
        mPresenters = new ArrayList<>();
        bindPresenter();
    }

    @Override
    public void bindPresenter() {
        //// 这个地方要去注入 Presenter 通过反射 (Dagger) soEasy
        Field[] fields = view.getClass().getDeclaredFields();
        for (Field field : fields) {
            MvpPresenter presenter = field.getAnnotation(MvpPresenter.class);
            Class<? extends BasePresenter> presenterClazz = null;
            if (presenter != null) {
                field.setAccessible(true);
                if (field.getType().getSuperclass().getSuperclass().getSimpleName().equals
                        (BasePresenter.class.getSimpleName())) {
                    presenterClazz = (Class<? extends BasePresenter>) field.getType();
                } else {
                    throw new RuntimeException("no support inject presenter type!" +
                            BasePresenter.class.getSimpleName());
                }

                try {
                    BasePresenter instance = presenterClazz.newInstance();
                    mPresenters.add(instance);
                    instance.attach(view);
                    field.set(view, instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unbindPresenter() {
        for (BasePresenter presenter : mPresenters) {
            presenter.detach();
        }
        view=null;
    }
}
