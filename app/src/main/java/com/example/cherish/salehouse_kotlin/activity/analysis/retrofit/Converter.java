package com.example.cherish.salehouse_kotlin.activity.analysis.retrofit;

import java.io.IOException;

import javax.annotation.Nullable;

/**
 * FIXME: cherish 请添加描述
 *
 * @Author: cherish
 * @CreateDate: 2019/2/1 10:29
 */

public interface Converter<F, T> {
    @Nullable
    T convert(F value) throws IOException;
}
