package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import okhttp3.internal.platform.Platform;

/**
 * @Author: cherish
 * @CreateDate: 2019/2/1 9:17
 */

public class Retrofit {
    public String baseUrl;
    public okhttp3.Call.Factory callFactory;
    private Annotation[] methodAnnotations;
    private Annotation[][] parameterAnnotationsArray;
    private Type[] parameterTypes;
    private static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    @Nullable
    Set<String> relativeUrlParamNames;
    @Nullable
    ParameterHandler<?>[] parameterHandlers;
    private static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{(" + PARAM + ")\\}");
    private Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();//线程安全的

    public Retrofit(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.callFactory = builder.callFactory;
    }

    public <T> T create(final Class<T> service) {
        validateServiceInterface(service);
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new
                InvocationHandler() {
            private final Platform platform = Platform.get();
            private final Object[] emptyArgs = new Object[0];

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.d("TAG", method.getName());
                // If the method is a method from Object then defer to normal invocation.
                if (method.getDeclaringClass() == Object.class) {  //因为接口默认也继承Object
                    // .所以接口也有Object中的方法的. 如继承自Object如equals()方法
                    return method.invoke(this, args);  //这里需要注意下,这里调用的是invocationHandler(H)的方法
                    // .一般的动态代理这里应该是调用委托类(A)的方法.
                }
                ServiceMethod serviceMethod = loadServiceMethod(method);
                OkHttpCall<Object> okHttpCall = new OkHttpCall<>(serviceMethod, args);
                return okHttpCall;
            }


        });

    }

    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod serviceMethod = serviceMethodCache.get(method);
        synchronized (serviceMethodCache) {
            if (serviceMethod == null) {
                serviceMethod = new ServiceMethod.Builder(this, method).build();
                serviceMethodCache.put(method, serviceMethod);
            }
        }
        return serviceMethod;
    }


    private <T> void validateServiceInterface(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declaration must be interface.");
        }
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces most not extend other interfaces.");
        }
    }


    public static class Builder {
        private String baseUrl;
        private okhttp3.Call.Factory callFactory;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder client(okhttp3.Call.Factory callFactory) {
            this.callFactory = callFactory;
            return this;
        }

        public Retrofit build() {
            return new Retrofit(this);
        }
    }
}
