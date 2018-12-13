package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

public class SlidingMenuActivity extends BaseActivity {

    private View vv_content;


    @Override
    public int getContentViewId() {
        return R.layout.activity_sliding_menu;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("ViewGroup 事件").setRightMenu(R.menu
                .navigation_tab).create();
        vv_content = findViewById(R.id.vv_content);
        vv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "不要点我啦！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
