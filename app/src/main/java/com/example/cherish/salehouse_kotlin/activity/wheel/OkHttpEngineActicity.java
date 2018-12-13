package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.http.EngineCallBack;
import com.example.baselibrary.http.HttpUtils;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.HttpApi;

/**
 * 链式调用 OkHttp
 */
public class OkHttpEngineActicity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_ok_http_engine_acticity;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("链式调用 OkHttp").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //get 请求
        /* HttpUtils.with(this)
                 .url(HttpApi.get)
                 .param("PageIndex",1)
                 .param("MustHasPost",true)
                 .param("PageCount",10)
                 .cache(true)
                 .get().execute(new EngineCallBack() {
                     @Override
                     public void onPreExecute() {

                     }
                     @Override
                     public void onSuccess(String result) {


                     }

                     @Override
                     public void onFailed(Exception e) {

                     }


                 });*/

         //post 请求
        HttpUtils.with(this)
                .url(HttpApi.post)
                .post().execute(new EngineCallBack() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }
}
