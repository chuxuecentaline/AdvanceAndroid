package com.example.cherish.salehouse_kotlin.activity.pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.baselibrary.base.BaseActivity;
import com.example.baselibrary.toolbar.normal.NormalNavigationBar;
import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.iterator.IIterator;
import com.example.cherish.salehouse_kotlin.utils.iterator.QQUserSystem;
import com.example.cherish.salehouse_kotlin.utils.iterator.User;
import com.example.cherish.salehouse_kotlin.utils.iterator.WXUserSystem;

public class IteratorActivity extends BaseActivity {


    @Override
    public int getContentViewId() {
        return R.layout.activity_iterator;
    }

    @Override
    protected void findViews() {
        new NormalNavigationBar.Build(this).setTitle("迭代器设计模式 ").setRightMenu(R.menu
                .navigation_tab).create();
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        QQUserSystem qqUserSystem = new QQUserSystem();
        User user=queryUser("xin","123456",qqUserSystem.iterator());
        if (user==null){
            WXUserSystem wxUserSystem=new WXUserSystem();
            User user2=queryUser("xin","123456",    wxUserSystem.iterator());
           if(user2==null){
               System.out.println("用户huaShao不存在");
           }
        }


    }

    private User queryUser(String account, String password, IIterator<User> iterator) {
        while (iterator.hasNext()){
            User user = iterator.next();
            if(user.getAccount().equals(account)&&user.getPassword().equals(password)){
                return  user;

            }

        }
        return  null;
    }

}
