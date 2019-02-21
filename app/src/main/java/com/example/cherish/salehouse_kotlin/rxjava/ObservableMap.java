package com.example.cherish.salehouse_kotlin.rxjava;

/**
 *
 * @Author: cherish
 * @CreateDate: 2019/1/23 21:34
 */

public class ObservableMap<T, R> extends Observable<R> {
    private Function<? super T, ? extends R> mapper;
    private Observable<T> observable;

    public ObservableMap(Observable<T> observable, Function<? super T, ? extends R> mapper) {
        this.mapper = mapper;
        this.observable = observable;
    }


    @Override
    protected void subscribeActual(Observer<R> observer) {
        observable.subscribe(new MapObserver<T, R>(observer, mapper));
    }

    //静态代理
    private class MapObserver<T, R> implements Observer<T> {
        private Observer<R> observer;
        private Function<? super T, ? extends R>  mapper;

        public MapObserver(Observer<R> observer, Function<? super T, ? extends R> mapper) {
            this.observer = observer;
            this.mapper = mapper;
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
            try {
                R apply = mapper.apply(t);
                observer.onNext(apply);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
