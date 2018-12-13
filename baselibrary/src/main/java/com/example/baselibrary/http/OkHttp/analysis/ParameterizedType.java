package com.example.baselibrary.http.OkHttp.analysis;

import java.lang.reflect.Type;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public interface ParameterizedType extends Type {// 返回Map<String,User>里的String和User，所以这里返回[String.class,User.clas]
    Type[] getActualTypeArguments();
    // Map<String,User>里的Map,所以返回值是Map.class
    Type getRawType();
    // 用于这个泛型上中包含了内部类的情况,一般返回null
    Type getOwnerType();

}
