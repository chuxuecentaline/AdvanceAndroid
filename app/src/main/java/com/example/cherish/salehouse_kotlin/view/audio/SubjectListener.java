package com.example.cherish.salehouse_kotlin.view.audio;

/**
 * 被观察者接口
 * Created by cherish
 */

public interface SubjectListener {
    void add(ObserverListener observerListener);

    void notifyObserver(float progress);

    void remove(ObserverListener observerListener);
}
