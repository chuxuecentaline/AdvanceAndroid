package com.example.cherish.salehouse_kotlin.utils.iterator;

import com.example.cherish.salehouse_kotlin.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信系统
 * Created by cherish
 */

public class WXUserSystem  implements  Aggregate<User>{
    List<User> mUsers = new ArrayList<>();

    public WXUserSystem() {
        User user0 = new User("Darren", "123456");
        User user1 = new User("Cherish", "111111");
        User user2 = new User("xue", "123456");
        User user3 = new User("siQi", "111111");
        User user4 = new User("GG", "123456");
        mUsers.add(user0);
        mUsers.add(user1);
        mUsers.add(user2);
        mUsers.add(user3);
        mUsers.add(user4);
    }


    @Override
    public IIterator<User> iterator() {
        return new WxIterator(mUsers);
    }
}
