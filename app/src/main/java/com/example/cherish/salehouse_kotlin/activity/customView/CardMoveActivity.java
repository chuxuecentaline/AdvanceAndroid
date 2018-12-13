package com.example.cherish.salehouse_kotlin.activity.customView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

/** fixme 修改自动动画效果
 * 卡片移除
 */
public class CardMoveActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_card_move;
    }

    @Override
    protected void findViews() {

        new NormalNavigationBar.Build(this).setTitle("卡片动画").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
