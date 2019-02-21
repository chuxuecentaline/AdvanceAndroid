package com.example.aidllibrary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * 服务端
 * @Author: cherish
 * @CreateDate: 2019/1/31 11:16
 */

public class IPCService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    IPCInterface.Stub mBinder=new IPCInterface.Stub() {
        public String aString;

        @Override
        public void send(String aString) throws RemoteException {
           this.aString=aString;
        }

        @Override
        public String getContent() throws RemoteException {
            return aString;
        }
    };
}
