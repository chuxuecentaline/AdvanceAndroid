package com.example.cherish.salehouse_kotlin.activity.pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.observer.BookObserver;
import com.example.cherish.salehouse_kotlin.utils.observer.LibraryManager;

public class ObserverActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_observer;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("观察者设计模式 ").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        BookObserver observer=new BookObserver(5);
        LibraryManager libraryManager=new LibraryManager();
        libraryManager.addObserver(observer);
        libraryManager.addBook("java基础");
        libraryManager.addBook("Android 进阶");
        libraryManager.addBook("速度与激情系列 ");
        libraryManager.addBook("速度与激情7 ");
        libraryManager.removeBook("速度与激情系列");
        libraryManager.deleteObserver(observer);

    }
}
