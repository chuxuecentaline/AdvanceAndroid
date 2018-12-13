package com.example.cherish.salehouse_kotlin.utils.Proxy;

/**
 * 广大群众
 * Created by cherish
 */

public class Men implements IBank {
    @Override
    public void apply() {
        System.out.println("办卡");

    }

    @Override
    public void lose() {
        System.out.println("挂失");
    }

    @Override
    public void deposit() {
        System.out.println("存钱");
    }

    @Override
    public void take() {
        System.out.println("取钱");
    }
}
