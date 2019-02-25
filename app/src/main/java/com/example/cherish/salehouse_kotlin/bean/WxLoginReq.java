package com.example.cherish.salehouse_kotlin.bean;

/**
 * FIXME: cherish 请添加描述
 *
 * @Author: cherish
 * @CreateDate: 2019/2/22 9:40
 */

public class WxLoginReq {
    private  String WeiXinCode;
    public WxLoginReq(String WeiXinCode) {
        this.WeiXinCode=WeiXinCode;
    }

    public String getWeiXinCode() {
        return WeiXinCode;
    }

    public void setWeiXinCode(String weiXinCode) {
        WeiXinCode = weiXinCode;
    }
}

