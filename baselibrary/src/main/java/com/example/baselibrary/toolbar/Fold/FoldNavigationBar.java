package com.example.baselibrary.toolbar.Fold;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.support.v7.widget.AppCompatTextView;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

/**
 * 可折叠的toolBar
 * * Created by cherish
 */

public class FoldNavigationBar extends AbsNavigationBar<FoldNavigationBar.Build
        .FoldNavigationParams> {

    private FoldNavigationBar(Build.FoldNavigationParams params) {
        super(params);

    }

    @Override
    public void onBindData(Build.FoldNavigationParams data) {
        super.onBindData(data);
        for (Map.Entry<Integer, Integer> integerIntegerEntry : data.mMap.entrySet()) {
            View view = findViewById(data.getParent(), integerIntegerEntry.getKey());
            view.setBackgroundResource(integerIntegerEntry.getValue());
        }
    }

    /**
     * 获取view
     *
     * @param viewId
     * @param <V>
     * @return
     */
    private <V extends View> V findViewById(ViewGroup viewGroup, int viewId) {
        return viewGroup.findViewById(viewId);
    }

    public static class Build extends AbsNavigationBar.Build<Build> {
        private static FoldNavigationParams params;

        public Build(AppCompatActivity context, int layoutId, ViewGroup parent) {
            super(context, layoutId, parent);
            params = new FoldNavigationParams(context, layoutId, parent);
        }

        public Build setBackground(int viewId, int resId) {
            params.setBackground(viewId, resId);
            return this;
        }

        public FoldNavigationBar create() {
            getParams().createView();
            return new FoldNavigationBar(params);

        }

        public static class FoldNavigationParams extends AbsNavigationBar.Build
                .AbsNavigationParams {
            public Map<Integer, Integer> mMap = new ArrayMap<>();

            public FoldNavigationParams(AppCompatActivity context, int layoutId, ViewGroup parent) {
                super(context, layoutId, parent);
            }

            public void setBackground(int viewId, int resId) {
                mMap.put(viewId, resId);
            }
        }
    }
}
