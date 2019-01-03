package com.example.cherish.salehouse_kotlin.utils.responsibilityChain;

/**
 * 链式模式
 * Created by cherish
 */

public interface IUserSystemHandler<T extends  IUserSystemHandler> {
    void nextHandle(T t);

}
