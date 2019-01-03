package com.example.cherish.salehouse_kotlin.utils;

import android.text.TextUtils;

import com.example.baselibrary.CarouselViewPager.BannerView;
import com.example.baselibrary.http.EngineCallBack;
import com.example.baselibrary.http.HttpUtils;
import com.example.baselibrary.http.OkHttp.analysis.ParameterizedTypeImpl;
import com.example.cherish.salehouse_kotlin.bean.BannerBean;
import com.example.cherish.salehouse_kotlin.bean.BaseBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public abstract class HttpBaseCallBack<T> implements EngineCallBack {


    @Override
    public void onPreExecute() {

    }

    @Override
    public void onSuccess(String result) {
        if (!TextUtils.isEmpty(result)) {
            try {
                Gson gson=new Gson();
                T json = (T) gson.fromJson(result, analysisClazzInfo(this));
                onSuccess(json);
            } catch (Exception e) {//解析异常，说明是array数组
                try {
                    Class<?> aClass = analysisClazzInfo(this);
                    onSuccess(HttpBaseCallBack.<T>fromJsonArray(result, this));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        }

    }

    protected abstract void onSuccess(T json);

    protected abstract void onSuccess(BaseBean<List<T>> json);

    /**
     * 解析一个类上面的class信息
     */
    public static Class<?> analysisClazzInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

    public static Class<?> analysisJsonArrayClazzInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

    public static <T> BaseBean<T> fromJsonObject(String reader, Object object) throws Exception{
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = new ParameterizedTypeImpl(BaseBean.class, params);
        return new Gson().fromJson(reader, type);
    }

    public static <T> BaseBean<List<T>> fromJsonArray(String reader, Object object)throws Exception {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        // 生成List<T> 中的 List<T>
        Type listType = new ParameterizedTypeImpl(List.class, params);
        // 根据List<T>生成完整的Result<List<T>>
        Type type = new ParameterizedTypeImpl(BaseBean.class, new Type[]{listType});
        return new Gson().fromJson(reader, type);
    }


}
