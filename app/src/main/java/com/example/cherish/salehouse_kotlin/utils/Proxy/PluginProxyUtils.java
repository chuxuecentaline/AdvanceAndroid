package com.example.cherish.salehouse_kotlin.utils.Proxy;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static com.morgoo.droidplugin.hook.handle.PluginCallback.LAUNCH_ACTIVITY;

/**
 * 拦截Activity的启动流程绕过AndroidManifest检测
 * Created by cherish
 * 作者：红橙Darren
 * 链接：https://www.jianshu.com/p/e359fafe5c29
 * 來源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */

public class PluginProxyUtils {
    private final Context mContext;
    private final Class<?> mProxyActivity;
    private String EXTRA_ORIGIN_INTENT="realIntent";

    public PluginProxyUtils(Context context, Class<?> proxyActivity) {
        mContext = context;
        mProxyActivity = proxyActivity;
    }

    //怎么样去找Hook点是个问题，把钩子下在哪里呢？
    // 一般的套路肯定最好是静态，然后是接口，配合反射注入就可以了
    public void hookStartActivity() {
        /**
         *    int result = ActivityManager.getService()
         *   startActivity(whoThread, who.getBasePackageName(), intent,
         *    intent.resolveTypeIfNeeded(who.getContentResolver()),
         *     token, target, requestCode, 0, null, options);
         */
        // Class.forName("")
        Class<ActivityManager> activityManagerClass = ActivityManager.class;
        try {
            Field field = activityManagerClass.getDeclaredField("IActivityManagerSingleton");
            field.setAccessible(true);
            Object gDefaultObj = field.get(null);
            Class<?> singleton = Class.forName("android.util.Singleton");
            Field instance = singleton.getDeclaredField("mInstance");
            instance.setAccessible(true);
            Object amsObj = instance.get(gDefaultObj);//获取无参的构造函数
            amsObj = Proxy.newProxyInstance(mContext.getClass().getClassLoader(), amsObj.getClass
                    ().getInterfaces(), new HookInvocationHandler(amsObj));
            instance.set(gDefaultObj, amsObj);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class HookInvocationHandler implements InvocationHandler {
        Object mAmsObj;

        public HookInvocationHandler(Object amsObj) {
            mAmsObj = amsObj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 拦截到所有ActivityManagerService的方法
            Log.e("TAG", "methodName" + method.getName());
            if (method.getName().equals("startActivity")) {
                //启动Activity 的方法， 找到原来的Intent
                Intent realIntent = (Intent) args[2];
                //代理的Intent
                Intent proxyIntent = new Intent();
                proxyIntent.setComponent(new ComponentName(mContext, mProxyActivity));
                //把原来的Intent 绑在代理Intent 上面
                proxyIntent.putExtra(EXTRA_ORIGIN_INTENT, realIntent);
                // 让proxyIntent去晒太阳，借尸
                args[2] = proxyIntent;
            }
            return method.invoke(mAmsObj, args);
        }
    }

    /**
     * hook Launch Activity
     */
    public void hookLaunchActivity() throws Exception {
        // 获取ActivityThread
        Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");
        Field sCurrentActivityThreadField = activityThreadClazz.getDeclaredField
                ("sCurrentActivityThread");
        sCurrentActivityThreadField.setAccessible(true);
        Object sCurrentActivityThreadObj = sCurrentActivityThreadField.get(null);
        // 获取Handler mH
        Field mHField = activityThreadClazz.getDeclaredField("mH");
        mHField.setAccessible(true);
        Handler mH = (Handler) mHField.get(sCurrentActivityThreadObj);
        // 设置Callback
        Field callBackField = Handler.class.getDeclaredField("mCallback");
        callBackField.setAccessible(true);
        callBackField.set(mH, new ActivityThreadHandlerCallBack());

    }

    class ActivityThreadHandlerCallBack implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == LAUNCH_ACTIVITY) {
                handleLaunchActivity(msg);
            }
            return false;
        }
    }

    // 还魂
    private void handleLaunchActivity(Message msg) {
        // final ActivityClientRecord r = (ActivityClientRecord) msg.obj;
        try {
            Object obj = msg.obj;
            Field intentField = obj.getClass().getDeclaredField("intent");
            intentField.setAccessible(true);
            Intent proxyIntent = (Intent) intentField.get(obj);
            // 代理意图
            Intent originIntent = proxyIntent.getParcelableExtra(EXTRA_ORIGIN_INTENT);
            if (originIntent != null) {
                // 替换意图
                intentField.set(obj, originIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
