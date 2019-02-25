package com.example.cherish.salehouse_kotlin.activity.analysis.mvvm;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程调度
 *
 * @Author: cherish
 * @CreateDate: 2019/2/22 17:10
 */

public class SchedulersTransform {
    public static <R, T> ObservableTransformer<? super R, T> transform() {
        return new ObservableTransformer<R, T>() {
            @Override
            public ObservableSource<T> apply(Observable<R> upstream) {
                return (ObservableSource<T>) upstream.subscribeOn(Schedulers.io()).observeOn
                        (AndroidSchedulers.mainThread());
            }
        };
    }

    public static <R, T> SingleTransformer<? super R, T> singleTransformer() {
        return new SingleTransformer<R, T>() {
            @Override
            public SingleSource<T> apply(Single<R> upstream) {
                return (SingleSource<T>) upstream.subscribeOn(Schedulers.io()).observeOn
                        (AndroidSchedulers.mainThread());
            }
        };
    }

    public static <R, T> MaybeTransformer<? super R, T> maybeTransformer() {
        return new MaybeTransformer<R, T>() {
            @Override
            public MaybeSource<T> apply(Maybe<R> upstream) {
                return (MaybeSource<T>) upstream.subscribeOn(Schedulers.io()).observeOn
                        (AndroidSchedulers.mainThread());
            }
        };
    }

    public static <R, T> FlowableTransformer<? super R, T> flowableTransformer() {
        return new FlowableTransformer<R, T>() {
            @Override
            public Publisher<T> apply(Flowable<R> upstream) {
                return (Publisher<T>) upstream.subscribeOn(Schedulers.io()).observeOn
                        (AndroidSchedulers.mainThread());
            }
        };
    }
}
