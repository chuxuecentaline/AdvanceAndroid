package com.example.cherish.salehouse_kotlin.bean;

import java.util.List;

/**
 * json  基类
 * Created by cherish
 */

public class BaseBean<T> {
    private String RMessage;
    private String RCode;
    private T Result;

    public String getRMessage() {
        return RMessage;
    }

    public void setRMessage(String RMessage) {
        this.RMessage = RMessage;
    }

    public String getRCode() {
        return RCode;
    }

    public void setRCode(String RCode) {
        this.RCode = RCode;
    }

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }
}
