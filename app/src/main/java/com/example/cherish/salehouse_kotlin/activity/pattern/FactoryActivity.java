package com.example.cherish.salehouse_kotlin.activity.pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.cache.IoHandler;
import com.example.baselibrary.cache.IoHandlerFactory;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.ioc.onClick;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.wheel.BuilderNavigationBarActivity;
import com.example.cherish.salehouse_kotlin.utils.GlobalMenuHelper;

/**
 * 工厂设计模式 - 抽象工厂
 */
public class FactoryActivity extends BaseActivity {
    @BindView(R.id.ed_content)
    AppCompatEditText ed_content;
    private String model="preferences";
    private IoHandler ioHandler;
    private String content;

    @Override
    public int getContentViewId() {
        return R.layout.activity_factory;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("抽象工厂 ").setRightMenu(R.menu.navigation_tab)
                .create();
        BindViewUtils.inject(this);

    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @onClick({R.id.look, R.id.save})
    private void click(View view) {
        if (view.getId() == R.id.save) {
            String tips = ed_content.getText().toString().trim();
            if (TextUtils.isEmpty(tips)) {
                toast("请输入保存的内容");
                return;
            }
            switch (model) {
                case "preferences":
                  //  ioHandler = IoHandlerFactory.DefaultIoHandler(this);
                 //   ioHandler.setString("practice", tips);
                    toast("保存");
                    break;
                case "dao":

                    toast("功能暂未实现");
                    break;
                case "memory":
                    ioHandler = IoHandlerFactory.MemoryIoHandler(this);
                    ioHandler.setString("practice", tips);
                    toast("保存");
                    break;
                case "disk":
                    ioHandler = IoHandlerFactory.DiskIoHandler(this);
                    ioHandler.setString("practice", tips);
                    toast("保存");
                    break;

                default:
                    break;
            }
        } else {
            switch (model) {
                case "preferences":
                  //  ioHandler = IoHandlerFactory.DefaultIoHandler(this);
                   // content = ioHandler.getString("practice");
                    toast(content);
                    break;
                case "dao":

                    toast("功能暂未实现");
                    break;
                case "memory":
                    ioHandler = IoHandlerFactory.MemoryIoHandler(this);
                    content = ioHandler.getString("practice");
                    toast(content);
                    break;
                case "disk":
                    ioHandler = IoHandlerFactory.DiskIoHandler(this);
                    content = ioHandler.getString("practice");
                    toast(content);
                    break;

                default:
                    break;
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_factory, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferences:
                model = "preferences";
                break;
            case R.id.dao:
                model = "dao";
                break;
            case R.id.memory:
                model = "memory";
                break;
            case R.id.disk:
                model = "disk";
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);

    }
}
