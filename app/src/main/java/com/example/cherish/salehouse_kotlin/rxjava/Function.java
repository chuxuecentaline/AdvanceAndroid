package com.example.cherish.salehouse_kotlin.rxjava;

import android.support.annotation.NonNull;

/**
 *
 *
 * @Author: cherish
 * @CreateDate: 2019/1/24 19:30
 */

public interface Function<T, R> {
    /**
     * Apply some calculation to the input value and return some other value.
     * @param t the input value
     * @return the output value
     * @throws Exception on error
     */
    R apply(@NonNull T t) throws Exception;
}