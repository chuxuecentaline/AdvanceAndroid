package com.example.cherish.salehouse_kotlin;

import android.app.Application;

import com.example.baselibrary.cache.PreferencesCache;
import com.example.baselibrary.http.HttpUtils;
import com.example.baselibrary.http.OkHttp.OkHttpEngine;
import com.morgoo.droidplugin.PluginApplication;

/**
 * Created by cherish
 */

public class MyApp extends PluginApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        HttpUtils.initEngine(new OkHttpEngine(this));
        PreferencesCache.init(this);
    }
}
