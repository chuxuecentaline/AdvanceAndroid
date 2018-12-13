package com.example.cherish.salehouse_kotlin.utils.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例设计模式 - 容器管理 系统的服务就是用的这种
 * Created by hcDarren on 2017/9/17.
 */

public class SingletonMap {
    private static Map<String, Object> mSingleMap = new HashMap<>();

    static {
        mSingleMap.put("activity_manager", new SingletonMap());
    }

    private SingletonMap() {
    }

    public static Object getService(String serviceName) {
        return mSingleMap.get(serviceName);
    }
}
