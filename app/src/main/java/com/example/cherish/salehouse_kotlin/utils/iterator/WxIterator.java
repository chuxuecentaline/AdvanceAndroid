package com.example.cherish.salehouse_kotlin.utils.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信
 * Created by cherish
 */

public class WxIterator implements IIterator<User> {
    private List<User> mUsers;
    private int index;

    public WxIterator(List<User> users) {
        mUsers = users;
    }

    @Override
    public boolean hasNext() {
        return index < mUsers.size();
    }

    @Override
    public User next() {
        return mUsers.get(index++);
    }
}
