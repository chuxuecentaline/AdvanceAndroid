package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.baselibrary.CarouselViewPager.BannerView;
import com.example.baselibrary.CarouselViewPager.ViewPagerViewAdapter;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.http.EngineCallBack;
import com.example.cherish.salehouse_kotlin.bean.BannerBean;
import com.example.cherish.salehouse_kotlin.bean.BaseBean;
import com.example.baselibrary.http.HttpUtils;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.HttpBaseCallBack;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AutoViewPagerActivity extends BaseActivity {
    //轮播图
    private String url="https://act.centanet.com/www/scripts/loader4app.php?mediaId=172";
    @BindView(R.id.bannerView)
    BannerView mBannerView;


    @Override
    public int getContentViewId() {
        return R.layout.activity_auto_view_pager;
    }

    @Override
    protected void findViews() {
        BindViewUtils.inject(this);
        HttpUtils.with(this).url(url).get().execute(new HttpBaseCallBack<BannerBean>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            protected void onSuccess(BannerBean json) {

            }

            @Override
            protected void onSuccess(final BaseBean<List<BannerBean>> json) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final List<BannerBean> result = json.getResult();
                        System.out.println("---------->json2"+result.toString());

                        final float percentage = 1f*Integer.parseInt(result.get(0).getWidth()) / Integer.parseInt(result.get(0).getHeight());
                        mBannerView.setAdapter(new ViewPagerViewAdapter() {
                            @Override
                            public int getCount() {
                                return result.size();
                            }

                            @Override
                            public View getView(int position,View container) {
                                ImageView imageView;
                                //界面复用
                                if(container==null){
                                    imageView = new ImageView(AutoViewPagerActivity.this);
                                }else{
                                    imageView= (ImageView) container;
                                    System.out.println("复用页面---------->" + imageView.toString());
                                }
                                BannerBean bean = result.get(position);

                                Glide.with(AutoViewPagerActivity.this).load(bean.getImageUrl()).into(imageView);
                                return imageView;
                            }

                            @Override
                            public float percentage() {
                                return percentage;
                            }
                        });

                    }
                });
            }


            @Override
            public void onFailed(Exception e) {

            }

        });

    }

    @Override
    protected void init(Bundle savedInstanceState) {


    }


}
