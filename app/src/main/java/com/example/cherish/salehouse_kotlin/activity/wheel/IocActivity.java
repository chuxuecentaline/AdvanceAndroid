package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.ioc.CheckNet;
import com.example.baselibrary.ioc.onClick;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

/**
 * IOC  注解
 */
public class IocActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView tv_content;
    @Override
    public int getContentViewId() {
        return R.layout.activity_ioc;
    }

    @Override
    protected void findViews() {
         BindViewUtils.inject(this);
        tv_content.setText("IOC 注解");

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        new NormalNavigationBar.Build(this).setTitle("注解+ 反射实现IOC ").setRightMenu(R.menu
                .navigation_tab).create();
    }
    @onClick({R.id.btn})
    @CheckNet          // 检测网络
    private   void onClick(View view){
        toast("点击");
    }
}
