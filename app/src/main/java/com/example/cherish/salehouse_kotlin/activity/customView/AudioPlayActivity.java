package com.example.cherish.salehouse_kotlin.activity.customView;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;

import com.example.baselibrary.Utils.FileUtils;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.http.EngineCallBack;
import com.example.baselibrary.http.HttpUtils;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.view.audio.AudioPlayView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class AudioPlayActivity extends BaseActivity {


    private AudioPlayView mPlay;
    private AppCompatTextView atv_down;
    private String url = "http://bmob-cdn-14121.b0.upaiyun" +
            ".com/2017/09/26/464edee940b4371b80f9f4b4627f92e0.mp3";


    @Override
    public int getContentViewId() {
        return R.layout.activity_audio_play;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("play").setRightMenu(R.menu.navigation_tab)
                .create();
        mPlay = findViewById(R.id.play);
        atv_down = findViewById(R.id.atv_down);

    }

    @Override
    protected void init(Bundle savedInstanceState) {

    /*  ValueAnimator animator=ValueAnimator.ofFloat(0f,567f);
        animator.setDuration(5000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mPlay.setCurrentProgress(value/567f);
            }
        });
     */
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission
                .RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                    atv_down.setEnabled(true);
                    return;
                }
                if (permission.shouldShowRequestPermissionRationale) {
                    toast("请打开设置，开启Storage 权限");
                    atv_down.setEnabled(false);
                    return;
                }
            }
        });

        atv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downAudio();
            }
        });
    }

    private void downAudio() {
        String path = FileUtils.getDownPath();
        if (!TextUtils.isEmpty(path)) {
            mPlay.setPlayPath(path);
            mPlay.downStatus(0);
            toast("已保存在本地");
            return;
        }
        HttpUtils.with(this).url(url).path("cache").name(FileUtils.getNameFromUrl(url)).down()
                .execute(new EngineCallBack() {

            @Override
            public void onPreExecute() {
                toast("文件下载中");
                mPlay.downStatus(-1);
            }

            @Override
            public void onSuccess(final String result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toast("下载成功");
                        mPlay.setPlayPath(result);
                        mPlay.downStatus(0);
                        System.out.println("" + result);
                    }
                });

            }

            @Override
            public void onFailed(Exception e) {
                System.out.println("----------" + e.toString());
            }
        });

    }

    @Override
    protected void onDestroy() {
        mPlay.onDestroy();
        super.onDestroy();
    }
}
