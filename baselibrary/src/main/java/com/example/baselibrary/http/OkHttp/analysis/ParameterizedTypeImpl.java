package com.example.baselibrary.http.OkHttp.analysis;

import java.lang.reflect.Type;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public class ParameterizedTypeImpl implements ParameterizedType  {
    private final Class raw;
    private final Type[] args;

    public ParameterizedTypeImpl(Class raw, Type[] args) {
        this.raw = raw;
        this.args = args != null ? args : new Type[0];
    }

    @Override
    public Type[] getActualTypeArguments() {
        return args;
    }

    @Override
    public Type getRawType() {
        return raw;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}