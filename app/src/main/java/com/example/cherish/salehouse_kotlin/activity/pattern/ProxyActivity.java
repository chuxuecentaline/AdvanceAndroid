package com.example.cherish.salehouse_kotlin.activity.pattern;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.ioc.onClick;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.Proxy.BusinessMan;
import com.example.cherish.salehouse_kotlin.utils.Proxy.IBank;
import com.example.cherish.salehouse_kotlin.utils.Proxy.Men;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ProxyActivity extends BaseActivity {
    @BindView(R.id.tv_dynamic_proxy)
    AppCompatTextView tv_dynamic_proxy;
    @BindView(R.id.tv_proxy)
    AppCompatTextView tv_proxy;

    @Override
    public int getContentViewId() {
        return R.layout.activity_proxy;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        new NormalNavigationBar.Build(this).setTitle("代理设计模式 ").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @onClick({R.id.tv_dynamic_proxy, R.id.tv_proxy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_dynamic_proxy:
                Men m = new Men();

                IBank proxy = (IBank) Proxy.newProxyInstance(IBank.class.getClassLoader(), new
                        Class<?>[]{IBank.class}, new BankInvocationHandler(m));
                proxy.apply();
                proxy.lose();
                proxy.deposit();
                proxy.take();
                break;

            default:
                Men men = new Men();
                BusinessMan businessMan = new BusinessMan(men);
                businessMan.apply();
                businessMan.lose();
                businessMan.deposit();
                businessMan.take();
                break;
        }

    }

    class BankInvocationHandler implements InvocationHandler {
        Men men;

        public BankInvocationHandler(Men men) {
            this.men = men;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (men != null) {
                System.out.println("录入信息");
                Object invoke = method.invoke(men, args);
                System.out.println("完成");
                return invoke;
            }
            return null;
        }
    }
}
