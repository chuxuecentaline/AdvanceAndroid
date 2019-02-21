package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit.bean;

/**
 *
 *
 * @Author: cherish
 * @CreateDate: 2019/2/1 9:01
 */

public class LoginReq {
    private String Mobile;
    private String Password;

    public LoginReq(String mobile, String password) {
        Mobile = mobile;
        Password = password;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
