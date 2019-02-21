package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.CheckNet;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.bean.ReflexBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import io.reactivex.internal.fuseable.SimplePlainQueue;

public class ReflexActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_reflex;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("java 反射基础+注解").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        try {
            Constructor<ReflexBean> constructor = ReflexBean.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            ReflexBean reflexBean = constructor.newInstance();
            Method instance = ReflexBean.class.getDeclaredMethod("getInstance", String.class);
            instance.setAccessible(true);
            instance.invoke(reflexBean, "鑫鑫");
            Field field = ReflexBean.class.getDeclaredField("names");
            field.setAccessible(true);
            String name = (String) field.get(reflexBean);
            System.out.println("-》" + name);
            Method method = ReflexBean.class.getDeclaredMethod("print");
            method.setAccessible(true);
            method.invoke(reflexBean);
            Method method2 = ReflexBean.class.getDeclaredMethod("setNames", String.class);
            method.setAccessible(true);
            method2.invoke(reflexBean, "Hello Darren");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @CheckNet
    public void check(View view) {
        startActivity(new Intent(ReflexActivity.this, SimplePlainQueue.class));
    }
}
