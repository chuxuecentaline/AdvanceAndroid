package com.example.baselibrary.toolbar.Fold;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;
import java.util.Set;

/**
 * 标题栏基类
 * Created by cherish
 */

public class AbsNavigationBar<T extends AbsNavigationBar.Build.AbsNavigationParams> implements
        IAbsNavigationBar<T> {

    private final T mParams;

    protected AbsNavigationBar(T params) {
        //1绑定布局
        //2绑定参数
        mParams = params;
        onBindData(params);

        //onClickListener();
    }


    /**
     * 初始化基础布局参数
     */

    @Override
    public void onBindData(T data) {


    }

    @Override
    public void onClickListener(int viewId, View.OnClickListener clickListener) {

    }

    /**
     * 返回 Params
     *
     * @return
     */
    public T getParams() {
        return mParams;
    }

    public static class Build<B extends Build> {
        private static AbsNavigationParams params;

        public Build(AppCompatActivity context, int layoutId, ViewGroup parent) {
            params = new AbsNavigationParams(context, layoutId, parent);
        }

        public B setTitle(int viewId, String content) {
            params.setTitle(viewId, content);
            return (B) this;
        }

        public B setBack(int viewId, View.OnClickListener listener) {
            params.setBack(viewId, listener);
            return (B) this;
        }

        public static AbsNavigationParams getParams() {
            return params;
        }

        public static class AbsNavigationParams {
            private AppCompatActivity mContext;
            private int mLayoutId;
            private ViewGroup mParent;
            private Map<Integer, String> con = new ArrayMap<>();
            private Map<Integer, View.OnClickListener> lis = new ArrayMap<>();

            public AbsNavigationParams(AppCompatActivity context, int layoutId, ViewGroup parent) {
                mContext = context;
                mLayoutId = layoutId;
                mParent = parent;
            }

            public Context getContext() {
                return mContext;
            }

            public int getLayoutId() {
                return mLayoutId;
            }

            public ViewGroup getParent() {
                return mParent;
            }


            public void setTitle(int viewId, String content) {
                con.put(viewId, content);

            }


            public void setBack(int viewId, View.OnClickListener listener) {
                lis.put(viewId, listener);
            }

            /**
             * 获取view
             *
             * @param viewId
             * @param <V>
             * @return
             */
            private <V extends View> V findViewById(int viewId) {
                return mParent.findViewById(viewId);
            }

            /**
             * 设置布局
             */
            void createView() {

                View view = LayoutInflater.from(params.getContext()).inflate(params.getLayoutId()
                        , mParent, false);
                mParent.addView(view, 0);
                for (Map.Entry<Integer, String> entry : con.entrySet()) {
                    Toolbar toolbar = findViewById(entry.getKey());
                    mContext.setSupportActionBar(toolbar);
                    ActionBar mActionBar = mContext.getSupportActionBar();
                    mActionBar.setDisplayHomeAsUpEnabled(true);
                    mActionBar.setHomeButtonEnabled(true);
                    toolbar.setTitle(entry.getValue());
                }
                for (Map.Entry<Integer, View.OnClickListener> listenerEntry : lis.entrySet()) {
                    Toolbar v = findViewById(listenerEntry.getKey());
                    v.setOnClickListener(listenerEntry.getValue());
                }

            }
        }
    }
}
