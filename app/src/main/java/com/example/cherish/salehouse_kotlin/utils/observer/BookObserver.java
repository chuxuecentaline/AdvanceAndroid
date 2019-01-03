package com.example.cherish.salehouse_kotlin.utils.observer;

import android.text.TextUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by cherish
 */

public class BookObserver implements Observer {

    private final int mTotal;
    private int count;
    private LibraryManager mLibraryManager;

    public BookObserver(int total) {
        mTotal=total;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("消息" + arg);
        count++;
        if (mLibraryManager == null) {
            mLibraryManager = (LibraryManager) o;
        }
        if (count == mTotal) {
           mLibraryManager.selectBook();
        }



    }


}
