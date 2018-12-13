package com.example.baselibrary.cache;

/**
 * 缓存的格式
 * Created by cherish
 */

public interface IoHandler {

    void setString(String key, String value);

    void setInt(String key, int value);

    void setBoolean(String key, boolean value);

    void setFloat(String key, float value);

    void setObject(String key, Object value);

    String getString(String key);

    int getInt(String key, int defaultValue);

    boolean getBoolean(String key, boolean defaultValue);

    float getFloat(String key, float defaultValue);

    Object getObject(String key, Object value);
}
