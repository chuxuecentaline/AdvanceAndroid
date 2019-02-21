package com.example.cherish.salehouse_kotlin.activity.frame.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.widget.AppCompatTextView;

import com.example.aidllibrary.IPCInterface;
import com.example.aidllibrary.IPCService;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

/**
 * 客户端
 */
public class Aidl2Activity extends BaseActivity {

    private AppCompatTextView mTvContent;

    @Override
    public int getContentViewId() {
        return R.layout.activity_aidl2;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("跨进程通信").setRightMenu(R.menu.navigation_tab)
                .create();
        mTvContent = findViewById(R.id.tv_content);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        String text = PersonMessage.getInstance().getApply();

        bind();
    }
    /**
     * 绑定服务
     */
    private void bind() {
        Intent intent = new Intent(this, IPCService.class);
        IPCServiceConnection serviceConnection=new IPCServiceConnection();
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }
    private  class  IPCServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IPCInterface ipcInterface = IPCInterface.Stub.asInterface(service);
            try {
                String content = ipcInterface.getContent();
                mTvContent.setText(content);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
