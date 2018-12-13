package com.example.cherish.salehouse_kotlin.activity.customView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.ElementAdapter;
import com.example.baselibrary.base.BaseActivity;

/**
 * 共享元素动画
 */
public class ElementAnimalActivity extends BaseActivity {


    private RecyclerView recycler_view;
    private ElementAdapter mAdapter;


    @Override
    public int getContentViewId() {
        return R.layout.activity_element_animal;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle(R.string.element).setRightMenu(R.menu
                .navigation_tab).create();
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ElementAdapter(this);
        recycler_view.setAdapter(mAdapter);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mAdapter.setItemClickListener(new ElementAdapter.onItemClickListener() {
            @Override
            public void onClick(int position, ElementAdapter.ViewHolder holder) {
                ImageView iv_icon = holder.itemView.findViewById(R.id.iv_icon);
                AppCompatTextView tv_content = holder.itemView.findViewById(R.id.tv_content);
                /**
                 * todo  多个元素共享动画
                 */
                Intent intent = new Intent(ElementAnimalActivity.this,
                        ElementAnimalDetailActivity.class);
                Pair<View, String> pair_image = new Pair<View, String>(iv_icon, "share_image");
                Pair<View, String> pair_text = new Pair<View, String>(tv_content, "share_text");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                        (ElementAnimalActivity.this, pair_image, pair_text);
                //options.toBundle()
                /**
                 * todo  单个元素共享动画
                 */
               startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ElementAnimalActivity.this, iv_icon, "share_image").toBundle());
                //   startActivity(new Intent(ElementAnimalActivity.this,ElementAnimalDetailActivity.class));
            }
        });
    }
}
