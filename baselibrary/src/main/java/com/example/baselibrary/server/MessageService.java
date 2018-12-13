package com.example.baselibrary.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.baselibrary.ICommAidlInterface;


/**
 * 服务绑定进程
 * Created by cherish
 */

public class MessageService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate--------");
    }

    //应用间通信进行绑定
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand--------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("onUnbind--------" + super.onUnbind(intent));
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        System.out.println("onRebind--------");
    }

    /* //应用间解绑
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }*/

    private final ICommAidlInterface.Stub mBinder = new ICommAidlInterface.Stub() {
        @Override
        public String getUserName() throws RemoteException {
            return "Cherish@163.com";
        }

        @Override
        public String getPassWord() throws RemoteException {
            return "123456";
        }
    };
}
