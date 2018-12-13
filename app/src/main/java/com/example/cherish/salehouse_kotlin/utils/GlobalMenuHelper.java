package com.example.cherish.salehouse_kotlin.utils;

import android.app.Activity;
import android.view.MenuItem;

import com.example.cherish.salehouse_kotlin.R;


/**
 * 通用菜单的帮助类
 * Created by cherish
 */

public class GlobalMenuHelper {

    public boolean onOptionsItemSelected(Activity activity, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_home:
                ToastUtils.show(activity, "控件");
                break;
            case R.id.tab_news:
                ToastUtils.show(activity, "设计模式");
                break;
            case R.id.tab_store:
                ToastUtils.show(activity, "订单");
                break;
            case R.id.tab_mine:
                ToastUtils.show(activity, "我的");
                break;
            default:
                break;

        }
        return  true;
    }
}
