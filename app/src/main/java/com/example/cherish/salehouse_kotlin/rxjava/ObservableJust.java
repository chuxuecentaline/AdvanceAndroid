package com.example.cherish.salehouse_kotlin.rxjava;

/**
 * 静态代理
 *
 * @Author: cherish
 * @CreateDate: 2019/1/23 21:07
 */

public class ObservableJust<T> extends Observable<T> {
    T item;

    public ObservableJust(T item) {
        this.item = item;
    }


    @Override
    protected void subscribeActual(Observer<T> observer) {
        ScalarDisposable scalarDisposable = new ScalarDisposable(observer, item);
        scalarDisposable.run();
    }

    /**
     * 用于高扩展
     *
     * @param <T>
     */
    private class ScalarDisposable<T> {
        private Observer<T> observer;
        private T item;

        public ScalarDisposable(Observer<T> observer, T item) {
            this.observer = observer;
            this.item = item;

        }

        public void run() {
            try {
                observer.onNext(item);
                observer.onCompleted();
            } catch (Exception e) {
                observer.onError(e);
            }
        }
    }
}
