package com.example.baselibrary.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toolbar;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * 抽象工厂
 * Created by cherish
 */

public abstract class IoHandlerFactory implements IoHandler {

    private static IoHandler createIoHandler(Class<? extends IoHandler> classIoHandle, Context
            context) {
        try {
            Constructor<? extends IoHandler> constructor = classIoHandle.getDeclaredConstructor();
            constructor.setAccessible(true);
            IoHandler instance = constructor.newInstance();
            Method method = instance.getClass().getDeclaredMethod("getInstance", Context
                    .class);

            return (IoHandler) method.invoke(instance,context);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------------->"+e.toString());
        }
        return PreferencesCache.getInstance(context);
    }


    /**
     * sharePreferences
     *
     * @return
     */
    public static IoHandler DefaultIoHandler(Context context) {
        return createIoHandler(PreferencesCache.class, context);
    }

    /**
     * 内存缓存
     *
     * @return
     */
    public static IoHandler MemoryIoHandler(Context context) {
        return createIoHandler(MemoryCache.class, context);
    }

    /**
     * 磁盘
     *
     * @return
     */
    public static IoHandler DiskIoHandler(Context context) {
        return createIoHandler(DiskCache.class, context);
    }

    /**
     * 数据库
     *
     * @return
     */
    public static IoHandler DaoIoHandler(Context context) {
        return createIoHandler(DaoCache.class, context);
    }
}
