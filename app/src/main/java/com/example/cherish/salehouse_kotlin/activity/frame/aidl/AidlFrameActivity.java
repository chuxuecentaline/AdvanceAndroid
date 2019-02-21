package com.example.cherish.salehouse_kotlin.activity.frame.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.aidllibrary.IPCInterface;
import com.example.aidllibrary.IPCService;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

import java.util.Random;

/**
 * 客户端
 */
public class AidlFrameActivity extends BaseActivity {

    private AppCompatTextView mTvContent;
    String[] data=new String[]{
            "段誉","虚竹","乔峰","语嫣","天山童姥","慕容复","段正淳",
            "钟灵","木婉清","无崖子","天龙八部","萧远山","扫地神僧","南海鳄神"
    };
    private IPCInterface mIpcInterface;

    @Override
    public int getContentViewId() {
        return R.layout.activity_aidl_frame;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("AIDL").setRightMenu(R.menu.navigation_tab)
                .create();
        mTvContent = findViewById(R.id.tv_content);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        bind();
    }

    public void changeText(View view) {
        mTvContent.setText(data[new Random().nextInt(14)]);
        PersonMessage.getInstance().apply(mTvContent.getText().toString());
        if(mIpcInterface!=null){
            try {
                mIpcInterface.send(mTvContent.getText().toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    public void start(View view) {
        startActivity(new Intent(AidlFrameActivity.this,Aidl2Activity.class));
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
            mIpcInterface = IPCInterface.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


}
