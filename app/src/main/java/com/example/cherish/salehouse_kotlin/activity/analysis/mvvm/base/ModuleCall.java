package com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.base;


import com.example.cherish.salehouse_kotlin.activity.analysis.mvvm.SchedulersTransform;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * @Author: cherish
 * @CreateDate: 2019/2/21 16:14
 */

public class ModuleCall<Result> {
    private ObservableSource<Result> mObservable;
    private boolean mExecuted;
    private boolean mCanceled;
    private boolean mDone;
    private ModuleCallback<Result> mModuleCallback;
    private Object mCancelHandle;

    public void setObservable(ObservableSource<Result> observable) {
        this.mObservable = observable;
    }

    public void onCancel() {
        System.out.println("onCancel-------->"+mCancelHandle.getClass().getName());
        mCanceled = true;
        if (mCancelHandle instanceof Disposable) {
            ((Disposable) mCancelHandle).dispose();
        } else if (mCancelHandle instanceof Subscription) {
            ((Subscription) mCancelHandle).cancel();
        }
    }

    public void enqueue(final ModuleCallback<Result> callback) {
        synchronized (this) {
            if (mExecuted) {
                throw new IllegalStateException("每个ModuleCall只能enqueue一次");
            }
            mExecuted = true;
        }
        if (mCanceled || mDone) {
            return;
        }
        mModuleCallback = callback;

        if (mObservable instanceof Observable) {
            subscribeObservable((Observable<Result>) mObservable);
        } else if (mObservable instanceof Single) {
            subscribeSingle((Single<Result>) mObservable);
        } else if (mObservable instanceof Flowable) {
            subscribeFlowable((Flowable<Result>) mObservable);
        } else {
            subscribeMaybe((Maybe<Result>) mObservable);
        }


    }

    private void subscribeObservable(Observable<Result> observable) {
        observable.compose(SchedulersTransform.<Result, Result>transform()).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                mCancelHandle = d;
            }

            @Override
            public void onNext(Result result) {
                mModuleCallback.onSuccess(result);
            }

            @Override
            public void onError(Throwable e) {
                mModuleCallback.onError(e);
                mDone = true;
            }

            @Override
            public void onComplete() {
                mDone = true;
            }
        });

    }

    private void subscribeSingle(Single<Result> single) {
        single.compose(SchedulersTransform.<Result, Result>singleTransformer()).subscribe(new SingleObserver<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                mCancelHandle = d;
            }

            @Override
            public void onSuccess(Result result) {
                mModuleCallback.onSuccess(result);
            }

            @Override
            public void onError(Throwable e) {
                mModuleCallback.onError(e);
                mDone = true;
            }
        });

    }

    private void subscribeFlowable(Flowable<Result> flowable) {
        flowable.compose(SchedulersTransform.<Result, Result>flowableTransformer()).subscribe
                (new FlowableSubscriber<Result>() {
            @Override
            public void onSubscribe(Subscription s) {
                mCancelHandle = s;
            }

            @Override
            public void onNext(Result result) {
                mModuleCallback.onSuccess(result);
            }

            @Override
            public void onError(Throwable t) {
                mModuleCallback.onError(t);
                mDone = true;
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void subscribeMaybe(Maybe<Result> maybe) {
        maybe.compose(SchedulersTransform.<Result, Result>maybeTransformer()).subscribe
                (new MaybeObserver<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                mCancelHandle = d;
            }

            @Override
            public void onSuccess(Result result) {
                mModuleCallback.onSuccess(result);
            }

            @Override
            public void onError(Throwable e) {
                mModuleCallback.onError(e);
                mDone = true;
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void execute() {

    }
}
