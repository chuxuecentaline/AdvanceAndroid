package com.example.cherish.salehouse_kotlin.rxjava;

/**
 * 子线程
 *
 * @Author: cherish
 * @CreateDate: 2019/1/24 20:45
 */

public class ObservableSubscribeOn<T> extends Observable<T> {
    private Observable<T> observable;
    private Scheduler scheduler;

    public ObservableSubscribeOn(Observable<T> observable, Scheduler scheduler) {
        this.observable = observable;
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        final SubscribeOnObserver<T> parent = new SubscribeOnObserver<T>(observer);
        observer.onSubscribe();
        scheduler.scheduleDirect(new SubscribeTask(parent));
    }

    private class SubscribeOnObserver<T> implements Observer<T> {
        private  Observer<T> observer;
        public SubscribeOnObserver(Observer<T> observer) {
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
            observer.onNext(t);
        }


    }

    private class SubscribeTask implements Runnable {
        private SubscribeOnObserver<T> parent;

        public SubscribeTask(SubscribeOnObserver<T> parent) {
            this.parent = parent;
        }

        @Override
        public void run() {
            observable.subscribe(parent);
        }
    }
}
