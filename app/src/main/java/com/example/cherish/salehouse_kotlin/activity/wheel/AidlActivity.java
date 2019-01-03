package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.baselibrary.ICommAidlInterface;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.ioc.onClick;
import com.example.baselibrary.server.MessageService;
import com.example.cherish.salehouse_kotlin.R;


public class AidlActivity extends BaseActivity {

    ICommAidlInterface mAidl;
    @BindView(R.id.tv_content)
    AppCompatTextView tv_content;

    @Override
    public int getContentViewId() {
        return R.layout.activity_aidl;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);

        startService(new Intent(AidlActivity.this, MessageService.class));
    }

    /**
     * 绑定服务
     */
    private void bindService() {
        //显示意图
        //Intent intent = new Intent(this,MessageService.class);
        //隐示意图
        Intent intent=new Intent();
        intent.setAction("com.example.cherish.aidl.message");
        // 在Android 5.0之后google出于安全的角度禁止了隐式声明Intent来启动Service.也禁止使用Intent filter.否则就会抛个异常出来
        intent.setPackage("com.example.cherish.salehouse_kotlin");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    public ComponentName mName;
    private ServiceConnection serviceConnection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            toast("绑定服务");
            mName=name;
            mAidl = ICommAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            toast("解除服务");
            mAidl = null;
        }
    };

    @Override
    protected void init(Bundle savedInstanceState) {


    }
    @onClick({R.id.tv_bindService,R.id.tv_content,R.id.tv_relieveService})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_bindService:

                bindService();
                break;
            case R.id.tv_relieveService:
                if (mAidl != null) {

                    serviceConnection.onServiceDisconnected(mName);
                    unbindService(serviceConnection);
                }
                break;
            case R.id.tv_content:
                try {
                    getUserName();
                    getUserPassword();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
                default:
                    break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 获取用户密码
     *
     */
    public void getUserPassword() throws Exception {

        if (mAidl != null) {
            String userPassword = mAidl.getPassWord();
            toast("用户密码：" + userPassword);
        } else {
            toast("服务器未绑定或被异常杀死，请重新绑定服务端");
        }
    }

    /**
     * 获取用户名
     *
     */
    public void getUserName() throws Exception {

        if (mAidl != null) {
            String userName = mAidl.getUserName();
            toast("用户名：" + userName);
        } else {
            toast("服务器未绑定或被异常杀死，请重新绑定服务端");
        }
    }
}

