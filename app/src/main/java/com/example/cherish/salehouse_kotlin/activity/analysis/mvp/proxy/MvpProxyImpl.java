package com.example.cherish.salehouse_kotlin.activity.analysis.mvp.proxy;

import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base.BasePresenter;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.base.BaseView;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvp.reJect.MvpPresenter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
        this.view = view;
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
                presenterClazz = (Class<? extends BasePresenter>) field.getType();
                field.setAccessible(true);
                if (!BasePresenter.class.isAssignableFrom(field.getType())) {
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
                //检测我们的view  是否实现了BasePresenter 的 view 接口
                checkView(presenterClazz);
            }
        }


    }

    /**
     * 检测是否implements 子类的View
     * @param presenterClazz
     */
    private void checkView(Class<? extends BasePresenter> presenterClazz) {
        //1.Presenter 的所有接口
        //2.要拿到view 层的所有接口
        //3.View 层没有实现就抛异常
        Type[] types = ((ParameterizedType) presenterClazz.getGenericSuperclass())
                .getActualTypeArguments();
        Class aClass = (Class) types[0];
        Class<?>[] interfaces = view.getClass().getInterfaces();
        boolean isImplementsView=false;
        for (Class<?> anInterface : interfaces) {
            if(anInterface.isAssignableFrom(aClass)){
                isImplementsView=true;
            }
        }
        if(!isImplementsView){
            throw new RuntimeException(view.getClass().getName()+"no implements !" +
                    aClass.getName());
        }

    }

    @Override
    public void unbindPresenter() {
        for (BasePresenter presenter : mPresenters) {
            presenter.detach();
        }
        view = null;
    }
}
