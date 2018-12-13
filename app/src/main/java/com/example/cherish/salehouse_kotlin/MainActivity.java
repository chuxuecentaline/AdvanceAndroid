package com.example.cherish.salehouse_kotlin;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MenuItem;

import com.example.cherish.salehouse_kotlin.annotations.HomeNav;
import com.example.cherish.salehouse_kotlin.frame.HomeFragment;
import com.example.cherish.salehouse_kotlin.frame.MineFragment;
import com.example.cherish.salehouse_kotlin.frame.NewsFragment;
import com.example.cherish.salehouse_kotlin.frame.StoreFragment;

/**
 * 自定义控件合集
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    private SparseArray<Fragment> mFragmentSparseArray = new SparseArray<>(4);
    private BottomNavigationView bottomNavigationView;
    private int mHomeNav=HomeNav.IDLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView =findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        updateFragments( HomeNav.HOME);
    }

    /**
     * 更新模块
     */
    private void updateFragments(int model) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if(mHomeNav!=HomeNav.IDLE){
            fragmentTransaction.hide(mFragmentSparseArray.get(mHomeNav));
        }
        String tag;
        switch (model) {
            case HomeNav.NEWS:
                tag = NewsFragment.TAG;
                if (fragmentManager.findFragmentByTag(NewsFragment.TAG) == null) {
                    mFragmentSparseArray.put(model, new NewsFragment());
                } else {
                    mFragmentSparseArray.put(model, fragmentManager.findFragmentByTag(NewsFragment
                            .TAG));
                }
                break;
            case HomeNav.STORE:
                tag = StoreFragment.TAG;
                if (fragmentManager.findFragmentByTag(StoreFragment.TAG) == null) {
                    mFragmentSparseArray.put(model, new StoreFragment());
                } else {
                    mFragmentSparseArray.put(model, fragmentManager.findFragmentByTag
                            (StoreFragment.TAG));
                }
                break;
            case HomeNav.MINE:
                tag = MineFragment.TAG;
                if (fragmentManager.findFragmentByTag(MineFragment.TAG) == null) {
                    mFragmentSparseArray.put(model, new MineFragment());
                } else {
                    mFragmentSparseArray.put(model, fragmentManager.findFragmentByTag(MineFragment.TAG));
                }
                break;

            default:
                tag = HomeFragment.TAG;
                if (fragmentManager.findFragmentByTag(HomeFragment.TAG) == null) {
                    mFragmentSparseArray.put(model, new HomeFragment());
                } else {
                    mFragmentSparseArray.put(model, fragmentManager.findFragmentByTag(HomeFragment
                            .TAG));
                }
                break;
        }
        mHomeNav=model;
        if (!mFragmentSparseArray.get(model).isAdded()) {
            fragmentTransaction.add(R.id.fl_content, mFragmentSparseArray.get(model), tag);
        } else {
            fragmentTransaction.show(mFragmentSparseArray.get(model));
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.tab_home:
                updateFragments( HomeNav.HOME);
                break;
            case R.id.tab_news:
                updateFragments( HomeNav.NEWS);
                break;
            case R.id.tab_store:
                updateFragments( HomeNav.STORE);
                break;
            case R.id.tab_mine:
                updateFragments( HomeNav.MINE);
                break;
        }

        return true;
    }


}
