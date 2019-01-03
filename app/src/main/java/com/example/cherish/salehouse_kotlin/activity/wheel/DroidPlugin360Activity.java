package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.ioc.onClick;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.Proxy.PluginProxyUtils;
import com.morgoo.droidplugin.pm.PluginManager;

import java.io.File;

public class DroidPlugin360Activity extends BaseActivity {


    private String apkPath= Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+
            "Download"+File.separator+"app-local-debug.apk";

    @Override
    public int getContentViewId() {
        return R.layout.activity_droid_plugin360;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        PluginProxyUtils pluginProxyUtils = new PluginProxyUtils(this, null);
        pluginProxyUtils .hookStartActivity();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
      /*  try {
            System.out.println("apkPath------------->"+apkPath);
            int result = PluginManager.getInstance().installPackage(apkPath, 0);
            System.out.println("------------result"+result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/

    }
    @onClick(R.id.tv_content)
    public  void onClick(View view){
       /* PackageManager pm =getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        ApplicationInfo appInfo = null;
        if (info != null) {
            appInfo = info.applicationInfo;
            Intent intent = pm.getLaunchIntentForPackage(appInfo.packageName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }*/



    }
}
