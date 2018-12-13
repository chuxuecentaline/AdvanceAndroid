package com.example.baselibrary.http;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求工具类
 * Created by cherish
 * 实现链式调用的方法
 */

public class HttpUtils {

    private static final int POST_TYPE = 0x0011;
    private static final int GET_TYPE = 0x0022;
    private static final int DOWN_TYPE = 0x0033;
    private static final int UPLOAD_TYPE = 0x0044;
    private static HttpEngine mHttpEngine;
    private Context mContext;
    private String mUrl;
    private Map<String, Object> mParams;
    private boolean mCatch;
    private int mType = GET_TYPE;
    private String mPath;
    private String mName;

    public static void initEngine(HttpEngine httpEngine) {
        mHttpEngine = httpEngine;
    }

    private HttpUtils(Context context) {
        mContext = context;
        mParams=new HashMap<>();

    }

    public static HttpUtils with(Context context) {
        return new HttpUtils(context);
    }


    public HttpUtils url(String url) {
        mUrl = url;
        return this;
    }

    public HttpUtils param(String key,Object value) {
        mParams .put(key,value);
        return this;
    }
    public HttpUtils params(Map<String, Object> params) {
        mParams = params;
        return this;
    }

    public HttpUtils path(String path) {
        mPath = path;
        return this;
    }
    public HttpUtils name(String name) {
        mName = name;
        return this;
    }
    public HttpUtils cache(boolean cache) {
        mCatch = cache;
        return this;
    }

    public HttpUtils get() {
        mType = GET_TYPE;
        return this;
    }

    public HttpUtils post() {
        mType = POST_TYPE;
        return this;
    }
    public HttpUtils down() {
        mType = DOWN_TYPE;
        return this;
    }
    public HttpUtils switchEngine(HttpEngine httpEngine) {
        mHttpEngine = httpEngine;
        return this;
    }

    /**
     * 执行
     *
     * @param callBack
     */
    public void execute(EngineCallBack callBack) {
        if (mType == GET_TYPE) {
            mHttpEngine.get(mContext, mUrl, mParams, callBack);
        } else if (mType == POST_TYPE) {
            mHttpEngine.post(mContext, mUrl, mParams, callBack);
        }else if(mType==DOWN_TYPE){
            mHttpEngine.downLoadFile(mContext,mUrl,mParams,mPath,mName,callBack);
        }

    }
}
