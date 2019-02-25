package com.example.cherish.salehouse_kotlin.activity.analysis.mvvm;

import android.arch.lifecycle.ViewModel;

import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.base.BaseModule;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.base.ModuleCall;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 收集ViewModel 的请求对象，便于回收
 *
 * @Author: cherish
 * @CreateDate: 2019/2/22 11:14
 */

public class BaseModuleImpl extends ViewModel {
    private static List<ModuleCall> mCalls = new ArrayList<>();

    public static <M extends BaseModule> M getModule(Class<M> moduleClass) {
        M module = ModuleManager.getModule(moduleClass);
        return  (M) Proxy.newProxyInstance(moduleClass.getClassLoader(), new
                Class[]{moduleClass}, new ModuleCallInvokeHandler(module));

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        System.out.println("onCleared");
        for (ModuleCall mCall : mCalls) {
            mCall.onCancel();
        }
    }

    private static class ModuleCallInvokeHandler<M extends BaseModule> implements InvocationHandler {


        private  M moduleClazz;

        public  ModuleCallInvokeHandler(M module) {
            moduleClazz=module;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object invoke = method.invoke(moduleClazz, args);
            if (ModuleCall.class.equals(method.getReturnType())) {
                mCalls.add((ModuleCall) invoke);
            }

            return invoke;
        }
    }
}
