package com.example.cherish.salehouse_kotlin.activity.customView;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.view.CanvasDrawable;

public class CanvasCicleDrawableActivity extends BaseActivity {
    @BindView(R.id.iv_img)
    CanvasDrawable iv_img;
    @BindView(R.id.iv)
    CanvasDrawable iv;

    @Override
    public int getContentViewId() {
        return R.layout.activity_canvas_cicle_drawable;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        new NormalNavigationBar.Build(this).setTitle("绘制圆形的Drawable").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Drawable drawable = getResources().getDrawable(R.drawable.icon);
        iv_img.setDrawable(drawable);

        Drawable drawable1 = getResources().getDrawable(R.color.colorAccent);
        iv.setDrawable(drawable1);

    }
}
