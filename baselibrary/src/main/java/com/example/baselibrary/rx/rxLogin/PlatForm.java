package com.example.baselibrary.rx.rxLogin;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.baselibrary.rx.rxLogin.PlatForm.ACCOUNT;
import static com.example.baselibrary.rx.rxLogin.PlatForm.QQ;
import static com.example.baselibrary.rx.rxLogin.PlatForm.SNIA;
import static com.example.baselibrary.rx.rxLogin.PlatForm.WECHAT;

/**
 *
 * @Author: cherish
 * @CreateDate: 2019/2/19 14:49
 */
@StringDef({ACCOUNT, QQ, WECHAT, SNIA})
@Retention(RetentionPolicy.SOURCE)
public @interface PlatForm {
    String ACCOUNT = "ACCOUNT";
    String QQ = "QQ";
    String WECHAT = "WECHAT";
    String SNIA = "SNIA";

}
