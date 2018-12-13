package com.example.baselibrary.ioc;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;



import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.MissingFormatArgumentException;

/**
 * 注解的工具类
 * Created by cherish
 */

public class BindViewUtils {
    public static void inject(Activity activity) {
        inject(new ViewFinder(activity), activity);
    }

    // 兼容View
    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    // 兼容Fragment
    public static void inject(View view, Object object) {
        inject(new ViewFinder(view), object);
    }

    private static void inject(ViewFinder viewFinder, Object object) {
        injectFiled(viewFinder, object);
        injectEvent(viewFinder, object);
    }

    /**
     * 事件
     *
     * @param viewFinder
     * @param object
     */
    private static void injectEvent(ViewFinder viewFinder, Object object) {
        //1.获取所有的属性
        Class<?> targetClass = object.getClass();
        // 获取所有方法包括私有和公有
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            //1.获取 方法上面的
            onClick click = method.getAnnotation(onClick.class);
            if (click != null) {
                int[] viewIds = click.value();
                if (viewIds.length > 0) {
                    for (int viewId : viewIds) {
                        View view = viewFinder.findViewById(viewId);
                        if (view != null) {
                            view.setOnClickListener(new DeclaredOnClickListener(method, object));
                        }

                    }
                }

            }

        }


    }


    /**
     * 属性
     *
     * @param viewFinder
     * @param object
     */
    private static void injectFiled(ViewFinder viewFinder, Object object) {
        Class<?> targetClass = object.getClass();
        //获取所有的属性
        Field[] fields = targetClass.getDeclaredFields();
        //获取所有属性包括私有和公有
        for (Field field : fields) {
            //2.获取属性上面的ViewById 的值
            BindView view = field.getAnnotation(BindView.class);
            if (view != null) {
                // 获取ViewById属性上的viewId值
                int viewId = view.value();
                // 3. 通过findViewById获取View
                View viewById = viewFinder.findViewById(viewId);
                if (viewById != null) {
                    //4.反射注入View 属性
                    //设置所有属性都能注入包括私有和公有
                    field.setAccessible(true);
                    try {
                        field.set(object, viewById);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new RuntimeException("Invalid @ViewInject for" + targetClass
                            .getSimpleName() + "." + field.getName());
                }
            }
        }
    }

    private static class DeclaredOnClickListener implements View.OnClickListener {
        private Method mMethod;
        private Object mHandlerType;

        public DeclaredOnClickListener(Method method, Object handlerType) {
            mMethod = method;
            mHandlerType = handlerType;
        }

        @Override
        public void onClick(View v) {
            try {

                // 扩展功能 检测网络
                boolean isCheckNet = mMethod.getAnnotation(CheckNet.class) != null;
                if(isCheckNet&&!networkAvailable(v.getContext())){
                    Toast.makeText(v.getContext(),"网络不稳定，请重新连接",Toast.LENGTH_SHORT).show();
                }else{
                    mMethod.setAccessible(true);
                    mMethod.invoke(mHandlerType, v);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                // 传一个空数组
                Object[] object = new Object[]{};
                try {
                    mMethod.invoke(mHandlerType, object);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 判断当前网络是否可用
     */
    private static boolean networkAvailable(Context context) {
        // 得到连接管理器对象
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
