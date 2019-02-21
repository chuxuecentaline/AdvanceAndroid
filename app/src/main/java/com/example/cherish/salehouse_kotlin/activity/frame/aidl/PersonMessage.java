package com.example.cherish.salehouse_kotlin.activity.frame.aidl;

/**
 * @Author: cherish
 * @CreateDate: 2019/1/31 10:44
 */

public class PersonMessage implements IMessage {

    private static final PersonMessage ourInstance = new PersonMessage();
    private String content;

    public static PersonMessage getInstance() {
        return ourInstance;
    }

    private PersonMessage() {
    }

    @Override
    public void apply(String content) {
        this.content = content;
    }

    @Override
    public String getApply() {
        return content;
    }
}
