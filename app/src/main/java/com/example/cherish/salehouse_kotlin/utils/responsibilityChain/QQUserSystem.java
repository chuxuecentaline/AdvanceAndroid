package com.example.cherish.salehouse_kotlin.utils.responsibilityChain;

/**
 * QQ 系统
 * Created by cherish
 */

public class QQUserSystem  extends  AbsUserSystemHandler implements Aggregate<User>  {
    User[] mUsers = new User[5];

    public QQUserSystem() {
        User user0 = new User("xin", "123456");
        User user1 = new User("du", "111111");
        User user2 = new User("shu", "123456");
        User user3 = new User("zhi", "111111");
        User user4 = new User("min", "123456");
        mUsers[0] = user0;
        mUsers[1] = user1;
        mUsers[2] = user2;
        mUsers[3] = user3;
        mUsers[4] = user4;
    }

    @Override
    public IIterator<User> iterator() {
        return new QqIterator(mUsers);
    }

    @Override
    public User queryUser(String account, String password) {
        IIterator<User> iterator = iterator();
        while (iterator.hasNext()){
            User next = iterator.next();
            if(next.getAccount().equals(account)&&next.getPassword().equals(password)){
                return  next;
            }
        }
        AbsUserSystemHandler systemHandler = getSystemHandler();
        if(systemHandler!=null){
            return systemHandler.queryUser(account,password);

        }
        return null;
    }
}
