package com.example.cherish.salehouse_kotlin.view.audio;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理类
 * Created by cherish
 */

public class ObserverManager implements SubjectListener {
    private static ObserverManager mObserverManager;
    //观察者接口集合
    private List<ObserverListener> list = new ArrayList<>();

    static {
        mObserverManager = new ObserverManager();
    }

    public static ObserverManager getInstance() {
        return mObserverManager;
    }

    @Override
    public void add(ObserverListener observerListener) {
        list.add(observerListener);
    }

    @Override
    public void notifyObserver(float progress) {
        //todo 根据业务进行修改
        if(list.size()>0){
            ObserverListener listener = list.get(0);
            listener.observerUpProgress(progress);
        }
    }

    @Override
    public void remove(ObserverListener observerListener) {
        list.remove(observerListener);
    }
}
