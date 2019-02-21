package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.ResponseBody;
import retrofit2.http.HTTP;

/**
 * 请求接口参数解析
 *
 * @Author: cherish
 * @CreateDate: 2019/2/1 13:19
 */

public class ServiceMethod {
    static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{(" + PARAM + ")\\}");
    static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);
    private Retrofit retrofit;
    private Method method;
    private Annotation[] mAnnotations;
    private String httpMethod;
    private String relativeUrl;
    private Set<String> relativeUrlParamNames;
    private  ParameterHandler<?>[] parameterHandlers;

    public ServiceMethod(Builder builder) {
        retrofit = builder.retrofit;
        method = builder.method;
        mAnnotations = builder.mAnnotations;
        httpMethod = builder.httpMethod;
        relativeUrl = builder.relativeUrl;
        relativeUrlParamNames = retrofit.relativeUrlParamNames;
        parameterHandlers=builder.parameterHandlers;

    }

    public Call createNewCall(Object[] args) {
        // 还需要一个对象，专门用来添加参数的
        RequestBuilder requestBuilder = new RequestBuilder(retrofit.baseUrl, relativeUrl, httpMethod, parameterHandlers, args);
        return retrofit.callFactory.newCall(requestBuilder.build());
    }

    public <T> T parseBody(ResponseBody responseBody) {
        // 获取解析类型 T 获取方法返回值的类型
        Type returnType = method.getGenericReturnType();// 返回值对象
        Class <T> dataClass = (Class <T>) ((ParameterizedType) returnType).getActualTypeArguments()[0];
        // 解析工厂去转换
        Gson gson = new Gson();
        T body = gson.fromJson(responseBody.charStream(),dataClass);
        return body;
    }

    public  <T> T convertBody(ResponseBody body) throws IOException {

        Type returnType = method.getGenericReturnType();
        Type observableType =Utils. getParameterUpperBound(0, (ParameterizedType) returnType);
        Gson gson = new Gson();
        TypeAdapter<T> adapter = (TypeAdapter<T>) gson.getAdapter(TypeToken.get(observableType));
        JsonReader jsonReader = gson.newJsonReader(body.charStream());
        try {
            T result =  adapter.read(jsonReader);
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
            return result;
        } finally {
            body.close();
        }

    }

    public static class Builder {
        private final Type[] parameterTypes;
        private final Annotation[][] parameterAnnotationsArray;
        private Retrofit retrofit;
        private Method method;
        private Annotation[] mAnnotations;
        private String httpMethod;
        private String relativeUrl;
        private Set<String> relativeUrlParamNames;
        ParameterHandler<?>[] parameterHandlers;

        public Builder(Retrofit retrofit, Method method) {
            this.retrofit = retrofit;
            this.method = method;
            mAnnotations = method.getAnnotations();
            parameterTypes = method.getGenericParameterTypes();
            parameterAnnotationsArray = method.getParameterAnnotations();
        }

        public ServiceMethod build() {
            //解析okHttp请求的时候 url=baseUrl+relativeUrl  ,method

            for (Annotation annotation : mAnnotations) {
                //解析 post  get
                parseMethodAnnotation(annotation);

            }
            int length = parameterAnnotationsArray.length;
            parameterHandlers = new ParameterHandler<?>[length];
            for (int i = 0; i < length; i++) {
               Annotation parameter = parameterAnnotationsArray[i][0];
                //Query  body 等等
                Log.e("TAG",parameter.annotationType().getName());
                Type parameterType = parameterTypes[i];
                if(parameter instanceof Body){
                    parameterHandlers[i]=new ParameterHandler.Body<>(parameterType);

                }

            }
            return new ServiceMethod(this);
        }


        private void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof GET) {
                parseHttpMethodAndPath("GET", ((GET) annotation).value());
            } else if (annotation instanceof POST) {
                parseHttpMethodAndPath("POST", ((POST) annotation).value());

            } else if (annotation instanceof HTTP) {
                HTTP http = (HTTP) annotation;
                parseHttpMethodAndPath(http.method(), http.path());
            }

        }

        private void parseHttpMethodAndPath(String httpMethod, String value) {

            this.httpMethod = httpMethod;

            if (value.isEmpty()) {
                return;
            }

            // Get the relative URL path and existing query string, if present.
            int question = value.indexOf('?');
            if (question != -1 && question < value.length() - 1) {
                // Ensure the query string does not have any named parameters.
                String queryParams = value.substring(question + 1);
                Matcher queryParamMatcher = PARAM_URL_REGEX.matcher(queryParams);
                if (queryParamMatcher.find()) {
                    throw new IllegalArgumentException("URL query string  must not have replace " +
                            "block. For dynamic query parameters use @Query.");
                }
            }

            this.relativeUrl = value;
            this.relativeUrlParamNames = parsePathParameters(value);
        }

        /**
         * Gets the set of unique path parameters used in the given URI. If a parameter is used
         * twice
         * in the URI, it will only show up once in the set.
         */
        static Set<String> parsePathParameters(String path) {
            Matcher m = PARAM_URL_REGEX.matcher(path);
            Set<String> patterns = new LinkedHashSet<>();
            while (m.find()) {
                patterns.add(m.group(1));
            }
            return patterns;
        }
    }


}

