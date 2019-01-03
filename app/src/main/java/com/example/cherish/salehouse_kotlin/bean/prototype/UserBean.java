package com.example.cherish.salehouse_kotlin.bean.prototype;

/**
 * 用户信息
 * Created by cherish
 */

public class UserBean implements  Cloneable{
    public String name;
    public  int age;
    public  AddressBean mAddressBean;

    @Override
    public UserBean clone() throws CloneNotSupportedException {
        UserBean bean = (UserBean) super.clone();
        bean.mAddressBean = bean.mAddressBean.clone();
        return bean;
    }
}
