package com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * @Author: cherish
 * @CreateDate: 2019/2/20 16:45
 */

public class BasePresenter<V extends BaseView, M extends BaseModel> {
    private V view;
    private M mModel;
    private V proxyView;

    public void attach(final V view) {
        this.view = view;
        proxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass()
                .getInterfaces(), new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (view == null) {
                    return null;
                }
                return method.invoke(view, args);
            }
        });
        //使用动态代理，进行判空处理
        Type type = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        try {
            mModel = (M) ((Class) params[1]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void detach() {
        view=null;

    }

    public V getView() {
        return proxyView;
    }

    public M getModel() {
        return mModel;
    }


}
