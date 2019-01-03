package com.example.cherish.salehouse_kotlin.activity.pattern;

import android.app.Activity;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.bean.prototype.AddressBean;
import com.example.cherish.salehouse_kotlin.bean.prototype.UserBean;

/**
 * 原型设计模式
 */
public class PrototypeActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_prototype;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("原型设计模式 ").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        UserBean userBean=new UserBean();
        userBean.name="雪儿";
        userBean.age=20;
        userBean.mAddressBean=new AddressBean("江苏省","南京市","宣武区");

        try {
            UserBean clone = userBean.clone();
            System.out.println("user"+userBean.name+"address"+userBean.mAddressBean.province);
            System.out.println("user"+clone.name+"address"+clone.mAddressBean.province);
            clone.name="初雪";
            clone.mAddressBean.province="上海";
            System.out.println("user"+userBean.name+"address"+userBean.mAddressBean.province);
            System.out.println("user"+clone.name+"address"+clone.mAddressBean.province);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
