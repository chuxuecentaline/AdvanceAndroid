package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.example.baselibrary.IntentExtra;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.db.DaoSupportFactory;
import com.example.baselibrary.db.IDaoSupport;
import com.example.cherish.salehouse_kotlin.server.DaoSupportServer;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.bean.Person;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class DaoActicity extends BaseActivity {

    public static String ACTION_BROADCAST = "com.example.cherish.DaoBroadcastReceiver";
    private DaoBroadcastReceiver myBroadcastReceiver;

    public class DaoBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // String status = intent.getStringExtra("status");
            long time = intent.getLongExtra("time", 0);
            toast("------时隔->" + time);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_dao_acticity;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("定制外部存储的数据库").setRightMenu(R.menu
                .navigation_tab).create();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_BROADCAST);
        myBroadcastReceiver = new DaoBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(myBroadcastReceiver, intentFilter);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                    dbOperation();
                    return;
                }
                if (permission.shouldShowRequestPermissionRationale) {
                    toast("请打开设置，开启Storage 权限");
                    return;
                }
            }
        });


    }

    /**
     * 数据库相关操作
     */
    private void dbOperation() {
        Intent intent = new Intent(this, DaoSupportServer.class);
        startService(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myBroadcastReceiver);
    }
}
