package com.example.cherish.salehouse_kotlin.activity.customView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.adapter.CategoryListAdapter;
import com.example.cherish.salehouse_kotlin.adapter.CommonRecyclerAdapter;
import com.example.cherish.salehouse_kotlin.adapter.WrapRecyclerAdapter;
import com.example.cherish.salehouse_kotlin.view.recyclerview.DefaultLoadCreator;
import com.example.cherish.salehouse_kotlin.view.recyclerview.DefaultRefreshCreator;
import com.example.cherish.salehouse_kotlin.view.recyclerview.LoadRefreshRecyclerView;
import com.example.baselibrary.base.BaseActivity;
import com.example.cherish.salehouse_kotlin.bean.BookBean;
import com.example.cherish.salehouse_kotlin.view.ItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 强大的RecyclerView
 * 通用的Adapter
 * 可定制的下拉刷新、加载更多
 * 侧滑
 */
public class RecyclerViewActivity extends BaseActivity {

    private LoadRefreshRecyclerView recyclerView;
    List<BookBean> mBeans = new ArrayList<>();
    private LayoutInflater mInflater;

    @Override
    protected void onNewIntent(Intent intent) {

    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("强大的RecyclerView").setRightMenu(R.menu
                .navigation_tab).create();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mInflater = LayoutInflater.from(this);

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        for (int i = 0; i <10; i++) {
            BookBean bean = new BookBean();
            bean.setTitle(""+i);
            mBeans.add(bean);
        }
        recyclerView.addItemDecoration(new ItemDecoration(this));
        final CategoryListAdapter  adapter = new CategoryListAdapter(this, mBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.addRefreshViewCreator(new DefaultRefreshCreator());
        View header = mInflater.inflate(R.layout.item_head, recyclerView, false);
        recyclerView.addHeaderView(header);
        View footer = mInflater.inflate(R.layout.item_footer, recyclerView, false);
        recyclerView.addFooterView(footer);
        recyclerView.addLoadViewCreator(new DefaultLoadCreator());
        Flowable.timer(20, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe
                (new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                // recyclerView.onStopRefresh();
                recyclerView.onStopLoad();
            }
        });

        /**
         * todo  实现左边侧滑删除
         */
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder
                    viewHolder) {
                //获取触摸响应的方向 包含两个 1.拖动dragFlags
                //2.侧滑删除swipeFlags
                // 代表只能是向左侧滑删除，当前可以是这样ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT
                int swipeFlags = ItemTouchHelper.LEFT;
                //拖动
                int dragFlags;
                if(recyclerView.getLayoutManager()instanceof GridLayoutManager){
                    // GridView 样式四个方向都可以
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.LEFT |
                            ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT;

                }else {
                    // ListView 样式不支持左右，只支持上下
                    dragFlags = ItemTouchHelper.UP |
                            ItemTouchHelper.DOWN;
                }

                //拖动暂不处理默认是0
                int position = viewHolder.getAdapterPosition();
                if (position < 2 || position >= mBeans.size() + 2) {
                    return makeMovementFlags(0, 0);
                }
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            /**
             * 拖动的时候不断的回调方法
             * @param recyclerView
             * @param viewHolder
             * @param target
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                //获取原来的位置
                int fromPosition = viewHolder.getAdapterPosition()-2;
                //得到目标的位置
                int targetPosition=target.getAdapterPosition()-2;
                if(fromPosition>targetPosition){
                    for (int i = 0; i < fromPosition; i++) {
                        Collections.swap(mBeans,i,i+1);//改变实际的数据集

                    }
                }else{
                    for (int i = fromPosition; i >targetPosition ; i--) {
                     Collections.swap(mBeans,i,i-1);
                    }
                }
                adapter.notifyDataSetChanged();
             //   mAdapter.notifyItemMoved(fromPosition,targetPosition);
                return true;
            }

            /**
             * 侧滑删除后会回调的方法
             * @param viewHolder
             * @param direction
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //获取当前删除的位置
                int position = viewHolder.getAdapterPosition();
                if (mBeans.size() > 0 && position > 1) {
                    mBeans.remove(position - 2);
                    adapter.notifyDataSetChanged();

                }


            }

            /**
             * 拖动选择状态改变回调
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    //ItemTouchHelper.ACTION_STATE_IDLE
                    //侧滑或者拖动的时候背景色设置为灰色
                    viewHolder.itemView.setBackgroundColor(Color.GRAY);
                }

            }

            /**
             * 回到正常状态时候的回调
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }
        });
     //   itemTouchHelper.attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new CommonRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mBeans.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

    }

}
