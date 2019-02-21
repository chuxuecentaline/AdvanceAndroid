package com.example.cherish.salehouse_kotlin.activity.customView;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class TreeSetActivity extends BaseActivity {


    private AppCompatTextView tv;

    @Override
    public int getContentViewId() {
        return R.layout.activity_tree_set;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("treeSet 列数相同获取每列的最大值").setRightMenu(R.menu
                .navigation_tab).create();
        tv = findViewById(R.id.tv);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        Map<Integer, int[]> map = new HashMap<>();
        int[] int1 = {1, 2, 3, 4, 5, 6, 7};
        int[] int2 = {12, 3, 4, 5, 6, 7, 8};
        int[] int3 = {3, 14, 5, 6, 7, 18, 19};
        int[] int4 = {4, 5, 6, 17, 5, 6, 10};
        map.put(0, int1);
        map.put(1, int2);
        map.put(2, int3);
        map.put(3, int4);
        List<TreeSet<Integer>> list = new ArrayList<>();
        for (int i = 0; i < int1.length; i++) {
            TreeSet<Integer> treeSet = new TreeSet<>();
            for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
                int[] value = entry.getValue();
                treeSet.add(value[i]);
            }

            list.add(treeSet);
        }

        System.out.print("list" + list);
        tv.setText(list.toString());
        for (int i = 0; i < list.size(); i++) {
            System.out.print("list" + list.get(i).pollLast());

        }


    }
}
