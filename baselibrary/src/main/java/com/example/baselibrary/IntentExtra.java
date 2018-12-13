package com.example.baselibrary;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *  参数 key
 * Created by cherish
 */
@Retention(RetentionPolicy.SOURCE)
public @interface IntentExtra {
    String JSON = "JSON";//类对象
    String JSON_LIST = "JSON_LIST";//数组对象
}
