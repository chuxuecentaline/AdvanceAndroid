package com.example.cherish.salehouse_kotlin.activity.pattern;

import android.app.Activity;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.responsibilityChain.QQUserSystem;
import com.example.cherish.salehouse_kotlin.utils.responsibilityChain.RYUserSystem;
import com.example.cherish.salehouse_kotlin.utils.responsibilityChain.User;
import com.example.cherish.salehouse_kotlin.utils.responsibilityChain.WXUserSystem;


public class ResponsibilityChainActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_responsibility_chain;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("责任链设计模式 ").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        QQUserSystem qqUserSystem = new QQUserSystem();
        WXUserSystem wxUserSystem = new WXUserSystem();
        RYUserSystem ryUserSystem = new RYUserSystem();
        User xin = qqUserSystem.queryUser("xinYu", "123456");
        qqUserSystem.nextHandle(wxUserSystem);
        wxUserSystem.nextHandle(ryUserSystem);
        if (xin != null) {
            System.out.println("user:" + xin.toString());
        }
        User user = qqUserSystem.queryUser("Darren", "123456");
        System.out.println("user:" + user.toString());
    }
}
