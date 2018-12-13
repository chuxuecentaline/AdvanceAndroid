package com.example.cherish.salehouse_kotlin.activity.pattern;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.baselibrary.CarouselViewPager.BannerView;
import com.example.baselibrary.CarouselViewPager.ViewPagerViewAdapter;
import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.http.EngineCallBack;
import com.example.baselibrary.http.HttpUtils;
import com.example.baselibrary.ioc.BindView;
import com.example.baselibrary.ioc.BindViewUtils;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.activity.wheel.AutoViewPagerActivity;
import com.example.cherish.salehouse_kotlin.adapter.BaseRecyclerAdapter;
import com.example.cherish.salehouse_kotlin.adapter.NewsAdapter;
import com.example.cherish.salehouse_kotlin.bean.BannerBean;
import com.example.cherish.salehouse_kotlin.bean.BaseBean;
import com.example.cherish.salehouse_kotlin.view.WrapRecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 装饰设计模式
 */
public class WrapActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    WrapRecyclerView recyclerView;
    private String url="https://act.centanet.com/www/scripts/loader4app.php?mediaId=172";

    @Override
    public int getContentViewId() {
        return R.layout.activity_wrap;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("装饰设计模式 ").setRightMenu(R.menu
                .navigation_tab).create();
        BindViewUtils.inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        final List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(i + "");
        }
        View headView = LayoutInflater.from(this).inflate(R.layout.item_head, recyclerView, false);
        final BannerView mBannerView = headView.findViewById(R.id.bannerView);
        View footerView = LayoutInflater.from(this).inflate(R.layout.item_bottom, recyclerView,
                false);

        final NewsAdapter adapter = new NewsAdapter(this, data);


        HttpUtils.with(this).url(url).get().execute(new EngineCallBack() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onSuccess(final String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("---------->bean"+result.toString());
                        Gson gson = new Gson();
                        Type type = new TypeToken<BaseBean<List<BannerBean>>>(){}.getType();
                        BaseBean<List<BannerBean>> bean=   gson.fromJson(result, type);
                        final List<BannerBean> beanResult = bean.getResult();
                        final float percentage = 1f*Integer.parseInt(beanResult.get(0).getWidth()) / Integer.parseInt(beanResult.get(0).getHeight());
                        mBannerView.setAdapter(new ViewPagerViewAdapter() {
                            @Override
                            public int getCount() {
                                return beanResult.size();
                            }

                            @Override
                            public View getView(int position,View container) {
                                ImageView imageView;
                                //界面复用
                                if(container==null){
                                    imageView = new ImageView(WrapActivity.this);
                                }else{
                                    imageView= (ImageView) container;
                                    System.out.println("复用页面---------->" + imageView.toString());
                                }
                                BannerBean bean = beanResult.get(position);

                                Glide.with(WrapActivity.this).load(bean.getImageUrl()).into(imageView);
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

        recyclerView.setAdapter(adapter);
        recyclerView.addHeaderView(headView);
        recyclerView.addFooterView(footerView);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(int position) {
                //因为是包装了一层WrapRecyclerViewAdapter  直接删除不起效果，必须监听一下adapter 的
                data.remove(position);
                System.out.println("-----------"+position);
                adapter.notifyDataSetChanged();


            }
        });
    }
}
