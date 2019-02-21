package com.example.baselibrary.rx.rxLogin;

import android.app.Activity;
import android.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;



/**
 * 响应式编程 支持 账号密码  QQ  微信  新浪微博 等第三方登陆
 * （仿照 rxPermission）
 * @Author: cherish
 * @CreateDate: 2019/2/19 14:38
 */

public class RxLogin {
    private  String TAG="RXLOGINFRAGMENT";
    RxLoginFragment mRxLoginFragment;
    private PublishSubject<String> mSubject=PublishSubject.create();

    public RxLogin(Activity activity) {
        mRxLoginFragment = getRxLoginFragment(activity);
    }

    private RxLoginFragment getRxLoginFragment(Activity activity) {
        RxLoginFragment loginFragment=(RxLoginFragment)activity.getFragmentManager().findFragmentByTag(TAG);
        if(loginFragment==null){
            loginFragment = new RxLoginFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(loginFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }

        return loginFragment;
    }

    public Observable request() {
        mRxLoginFragment.getSubject().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                mSubject.onNext(s);
            }
        });
        List<PublishSubject<String>> list=new ArrayList<>();
        list.add(mSubject);
        return Observable.concat(Observable.fromIterable(list));
    }
}
