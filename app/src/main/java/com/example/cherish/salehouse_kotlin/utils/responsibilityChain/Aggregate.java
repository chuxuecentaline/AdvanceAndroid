package com.example.cherish.salehouse_kotlin.utils.responsibilityChain;

/**
 *
 * Created by cherish
 */

public interface Aggregate<T> {
    IIterator<T> iterator();

}
