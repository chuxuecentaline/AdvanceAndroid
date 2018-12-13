package com.example.baselibrary.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences 缓存
 * Created by cherish
 */

public class PreferencesCache implements IoHandler {

    private static Context mContext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;

    private PreferencesCache() {
        if (mContext == null) {
            return;
        }

    }

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static PreferencesCache getInstance(Context context) {

        return PreferencesCacheHolder.mInstance;
    }

    public static class PreferencesCacheHolder {
        private static volatile PreferencesCache mInstance = new PreferencesCache();

    }


    @Override
    public void setString(String key, String value) {
        initSharePreferences();
        mEditor.putString(key, value).commit();
    }

    @Override
    public void setInt(String key, int value) {
        initSharePreferences();
        mEditor.putInt(key, value).commit();
    }

    @Override
    public void setBoolean(String key, boolean value) {
        initSharePreferences();
        mEditor.putBoolean(key, value).commit();
    }

    @Override
    public void setFloat(String key, float value) {
        initSharePreferences();
        mEditor.putFloat(key, value).commit();
    }

    @Override
    public void setObject(String key, Object value) {

    }

    @Override
    public String getString(String key) {
        mSharedPreferences = mContext.getSharedPreferences("cache", Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, "");
    }

    @Override
    public int getInt(String key, int defaultValue) {
        mSharedPreferences = mContext.getSharedPreferences("cache", Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, defaultValue);
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

    public void initSharePreferences() {
        mSharedPreferences = mContext.getSharedPreferences("cache", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }


}
