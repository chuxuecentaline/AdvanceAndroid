package com.example.baselibrary.toolbar.Fold;

import android.view.View;

/**
 * 数据
 * Created by cherish
 */

public interface IAbsNavigationBar<T> {
    void onBindData(T data);
    void onClickListener(int viewId,View.OnClickListener clickListener);
}
