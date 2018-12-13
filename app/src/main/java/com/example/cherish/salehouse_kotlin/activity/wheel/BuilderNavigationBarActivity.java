package com.example.cherish.salehouse_kotlin.activity.wheel;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.NewsAdapter;
import com.example.cherish.salehouse_kotlin.utils.GlobalMenuHelper;

import java.util.ArrayList;
import java.util.List;

public class BuilderNavigationBarActivity extends BaseActivity {

    private NormalNavigationBar mNavigationBar;
    private RecyclerView mRecyclerView;
    private List<String> datas = new ArrayList<>();


    @Override
    public int getContentViewId() {
        return R.layout.activity_builder_navigation_bar;
    }

    @Override
    protected void findViews() {
        mNavigationBar = new NormalNavigationBar.Build(this).setTitle("普通的toolBar").setRightMenu
                (R.menu.navigation_tab).create();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL));


    }

    @Override
    protected void init(Bundle savedInstanceState) {
        for (int i = 0; i < 18; i++) {
            datas.add("测试数据" + i);
        }
        mRecyclerView.setAdapter(new NewsAdapter(this, datas));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int offset;
            public boolean animalIn=false;
            //当前滑动距离
            private int currentDistanceY;
            //滑动距离
            private int distanceY;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentDistanceY += dy;
                Log.e("TAG", "distanceY:_____" + distanceY + "currentDistanceY:_____" + currentDistanceY);
                // >0 向下 <0 向上
                offset = currentDistanceY - distanceY;
                Log.e("TAG","offset:_____"+offset);
                if(offset >10){
                    if(!animalIn){
                       mNavigationBar.inAnimal(offset);
                        animalIn=true;
                    }
                }else if(offset<-10){
                    if(animalIn){
                        mNavigationBar.inAnimal(offset);
                        animalIn=false;
                    }
                }

                distanceY = currentDistanceY;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_tab, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tab_share:
                toast("分享");
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item) || new GlobalMenuHelper().onOptionsItemSelected
                (BuilderNavigationBarActivity.this, item);

    }
}
