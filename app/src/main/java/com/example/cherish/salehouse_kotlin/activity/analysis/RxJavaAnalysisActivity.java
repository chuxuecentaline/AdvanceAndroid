package com.example.cherish.salehouse_kotlin.activity.analysis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.rxjava.Function;
import com.example.cherish.salehouse_kotlin.rxjava.Observable;
import com.example.cherish.salehouse_kotlin.rxjava.Observer;
import com.example.cherish.salehouse_kotlin.rxjava.Schedulers;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class RxJavaAnalysisActivity extends BaseActivity {


    private ImageView imageView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_rx_java_analysis;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("源码分析").setRightMenu(R.menu.navigation_tab)
                .create();
        imageView = findViewById(R.id.imageView);
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        Observable.just("http://pic1.win4000.com/wallpaper/4/586b0952baadd.jpg").map(new Function<String, Bitmap>() {
            @Override
            public Bitmap apply(String urlPath) throws Exception {
                URL url = new URL(urlPath);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;

            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.mainThread()).subscribe(new Observer<Bitmap>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted线程："+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError线程："+Thread.currentThread().getName());
            }

            @Override
            public void onNext(Bitmap bitmap) {
                System.out.println("onNext线程："+Thread.currentThread().getName());
                imageView.setImageBitmap(bitmap);
            }
        });
        //
    }
}
