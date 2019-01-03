package com.example.cherish.salehouse_kotlin.utils.responsibilityChain;

/**
 * 迭代器的设计模式
 * Created by cherish
 */

public interface IIterator<T> {
    boolean hasNext();
    T next();

}
