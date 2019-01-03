package com.example.cherish.salehouse_kotlin.utils.responsibilityChain;

/**
 * Created by cherish
 */

public abstract class AbsUserSystemHandler implements IUserSystemHandler<AbsUserSystemHandler>,IUserInfo {
    AbsUserSystemHandler mSystemHandler;

    public AbsUserSystemHandler getSystemHandler() {
        return mSystemHandler;
    }

    public void nextHandle(AbsUserSystemHandler systemHandler) {
        mSystemHandler = systemHandler;
    }
}
