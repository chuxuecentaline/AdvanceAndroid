package com.example.cherish.salehouse_kotlin.utils.responsibilityChain;

import java.util.List;

/**
 * Created by cherish
 */

public class RYIterator implements   IIterator<User> {
    private List<User> users;
    private  int index;
    public RYIterator(List<User> users) {
        this.users=users;
    }

    @Override
    public boolean hasNext() {
        return index<users.size();
    }

    @Override
    public User next() {
        return users.get(index++);
    }
}
