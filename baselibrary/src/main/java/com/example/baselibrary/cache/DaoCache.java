package com.example.baselibrary.cache;

import android.content.Context;

/**
 * 数据库
 * Created by cherish
 */

public class DaoCache implements IoHandler {
    public DaoCache() {
    }

    public static DaoCache getInstance(Context context) {
        System.out.println("--------->DaoCache 执行");
        return DaoCacheHolder.mInstance;
    }

    private static class DaoCacheHolder {
        public static volatile DaoCache mInstance = new DaoCache();
    }

    @Override
    public void setString(String key, String value) {

    }

    @Override
    public void setInt(String key, int value) {

    }

    @Override
    public void setBoolean(String key, boolean value) {

    }

    @Override
    public void setFloat(String key, float value) {

    }

    @Override
    public void setObject(String key, Object value) {

    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return 0;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return false;
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return 0;
    }

    @Override
    public Object getObject(String key, Object value) {
        return null;
    }

}
