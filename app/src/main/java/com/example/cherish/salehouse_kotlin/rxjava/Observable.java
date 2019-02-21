package com.example.cherish.salehouse_kotlin.rxjava;


import android.support.annotation.NonNull;

/**
 * 被观察者
 *
 * @Author: cherish
 * @CreateDate: 2019/1/23 20:45
 */

public abstract class Observable<T> implements ObservableSource<T> {


    public static <T> Observable just(T item) {
        return onAssembly(new ObservableJust<T>(item));
    }

    public <R> Observable<R> map(Function<? super T, ? extends R> mapper) {
        return onAssembly(new ObservableMap<T, R>(this, mapper));
    }

    private static <T> Observable<T> onAssembly(@NonNull Observable<T> source) {
        return source;
    }
    public  Observable<T> subscribeOn(Scheduler scheduler) {
     return onAssembly(new ObservableSubscribeOn<T>(this, scheduler));
    }

    public Observable<T>  observeOn(Scheduler scheduler) {
        return onAssembly(new ObservableObserveOn<T>(this, scheduler));
    }

    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);



}
