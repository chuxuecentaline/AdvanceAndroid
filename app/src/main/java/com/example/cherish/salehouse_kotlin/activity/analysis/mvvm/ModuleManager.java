package com.example.cherish.salehouse_kotlin.activity.analysis.mvvm;

import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.base.BaseModule;
import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.base.ModuleCall;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;

/**
 * module 管理类
 *
 * @Author: cherish
 * @CreateDate: 2019/2/22 11:00
 */

public class ModuleManager {
    private static Map<Class<?>, BaseModule> mModuleCache = new ConcurrentHashMap<>();
    private static Map<Class<?>, BaseModuleImpl> mModuleImplCache = new ConcurrentHashMap<>();

    /**
     * 一个 接口 对应多条请求 方法  所以要使用单例的模式
     *
     * @param moduleClass
     * @param <M>
     * @return
     */
    public static <M extends BaseModule> M getModule(Class<M> moduleClass) {
        BaseModule module = mModuleCache.get(moduleClass);
        if (module != null) {
            return (M) module;
        }
        synchronized (mModuleCache) {
            try {
                module = mModuleCache.get(moduleClass);
                if (module == null) {
                    module = create(moduleClass);
                    mModuleCache.put(moduleClass, module);
                }
                return (M) module;
            } catch (Throwable t) {
                throw new RuntimeException("获取module失败 " + moduleClass.getName() + "  " + t);
            }
        }

    }

    /**
     * 使用动态代理 实现impl 代理 module
     * @param moduleClass
     * @param <M>
     * @return
     * @throws Exception
     */
    private static <M extends BaseModule> M create(Class<M> moduleClass) throws Exception {
        ProxyTarget proxyTarget = moduleClass.getAnnotation(ProxyTarget.class);
        Class<? extends BaseModuleImpl> targetClass = proxyTarget.value();
        Method[] methodsImpl = targetClass.getDeclaredMethods();
        Method[] methods = moduleClass.getDeclaredMethods();
        HashMap<Method, Method> methodMap = new HashMap<>();
        for (Method method : methods) {
            for (Method targetMethod : methodsImpl) {
                System.out.println("Method" + method.getName());
                System.out.println("Method" + targetMethod.getName());
                if (method.getName().equals(targetMethod.getName())) {
                    methodMap.put(method, targetMethod);
                }
            }
        }
        Object targetObject = getImpl(targetClass);
        return (M) Proxy.newProxyInstance(moduleClass.getClassLoader(), new Class[]{moduleClass},
                new ModuleInvocationHandler(targetObject, methodMap));
    }

    /**
     *获取module实现类的实例（单例）
     * @param moduleClass
     * @param <M>
     * @return
     */
    private static <M extends BaseModuleImpl> M getImpl(Class<M> moduleClass) {
        BaseModuleImpl module = mModuleImplCache.get(moduleClass);
        if (module != null) {
            return (M) module;
        }
        synchronized (mModuleImplCache) {

            try {
                module = mModuleImplCache.get(moduleClass);
                if (module == null) {
                    module = moduleClass.newInstance();
                    mModuleImplCache.put(moduleClass, module);
                }
                return (M) module;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("获取moduleImpl失败 " + moduleClass.getName() + "  " + e);
            }

        }
    }

    /**
     * 动态代理 将 ModuleCall  代理 成 observer 请求
     */
    private static class ModuleInvocationHandler implements InvocationHandler {
        private Object targetObject;
        private HashMap<Method, Method> methodMap;

        public ModuleInvocationHandler(Object targetObject, HashMap<Method, Method> methodMap) {
            this.targetObject = targetObject;
            this.methodMap = methodMap;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object invoke = methodMap.get(method).invoke(targetObject, args);
            if (ModuleCall.class.equals(method.getReturnType())) {
                ModuleCall call = new ModuleCall();
                call.setObservable((Observable) invoke);
                return call;
            } else {
                return invoke;
            }
        }
    }
}
