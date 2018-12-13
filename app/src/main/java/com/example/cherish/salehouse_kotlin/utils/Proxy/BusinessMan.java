package com.example.cherish.salehouse_kotlin.utils.Proxy;

/**
 * 业务员
 * Created by cherish
 */

public class BusinessMan implements IBank  {
    Men men;
    public BusinessMan(Men men) {
        this.men=men;
    }

    @Override
    public void apply() {
        System.out.println("录入信息");
        men.apply();
        System.out.println("完成");

    }

    @Override
    public void lose() {
        System.out.println("录入信息");
        men.lose();
        System.out.println("完成");
    }

    @Override
    public void deposit() {
        System.out.println("录入信息");
        men.deposit();
        System.out.println("完成");
    }

    @Override
    public void take() {
        System.out.println("录入信息");
        men.take();
        System.out.println("完成");
    }
}
