package com.example.cherish.salehouse_kotlin.rxjava;

/**
 * 主线程
 *
 * @Author: cherish
 * @CreateDate: 2019/1/24 21:16
 */

public class ObservableObserveOn<T> extends Observable<T> {
    private Observable<T> observable;
    private Scheduler scheduler;

    public ObservableObserveOn(Observable<T> observable, Scheduler scheduler) {
     this.observable=observable;
     this.scheduler=scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        ObserveOnObserver<T> onObserver = new ObserveOnObserver<>(observer);

        observable.subscribe(onObserver);
    }

    private class ObserveOnObserver<T> implements Observer<T> , Runnable {
        private Observer<T> observer;
        private T t;
        public ObserveOnObserver(Observer<T> observer) {
            this.observer=observer;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onCompleted() {
            observer.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onNext(T t) {
            this.t=t;
            scheduler.scheduleDirect(this);

        }

        @Override
        public void run() {
            observer.onNext(t);
        }
    }
}
