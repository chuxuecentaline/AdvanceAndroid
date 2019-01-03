package com.example.cherish.salehouse_kotlin.utils.iterator;

/**
 * QQ
 * Created by cherish
 */

public class QqIterator implements IIterator<User> {
    private User[] mUsers;
    private int index;

    public QqIterator(User[] users) {
        mUsers = users;
    }

    @Override
    public boolean hasNext() {
        return index < mUsers.length;
    }

    @Override
    public User next() {
        return mUsers[index++];
    }
}
