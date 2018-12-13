package com.example.baselibrary.http.cache;

import android.content.Context;

/**
 * 缓存
 * Created by cherish
 */

public interface CacheImpl {
    /**
     * 保存
     * @param context
     * @param path
     * @param result
     */
    void saveCatch(Context context, String path, String result);

    /**
     * 取出
     * @param context
     * @param path
     */
    String getCatch(Context context,String path);
}
