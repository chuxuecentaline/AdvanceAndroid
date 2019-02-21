package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;

import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 封装请求参数
 *
 * @Author: cherish
 * @CreateDate: 2019/2/18 14:21
 */

public class RequestBuilder {
    private String baseUrl;
    private String relativeUrl;
    private String httpMethod;
    private ParameterHandler<Object>[] parameterHandlers;
    private Object[] args;
    private @Nullable
    RequestBody body;
    public RequestBuilder(String baseUrl, String relativeUrl, String httpMethod,
                          ParameterHandler<?>[] parameterHandlerss, Object[] args) {
        this.baseUrl = baseUrl;
        this.relativeUrl = relativeUrl;
        this.httpMethod = httpMethod;
        this.args = args;
        this.parameterHandlers = (ParameterHandler<Object>[]) parameterHandlerss;

    }

    public Request build() {
        int count = args.length;
        for (int i=0;i < count;i++) {
            // userName = Darren
            try {
                parameterHandlers[i].apply(this,args[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Request.Builder().url(baseUrl + relativeUrl).method(httpMethod,body).build();

    }

    public void addBody(RequestBody body) {
        this.body = body;
    }
}
