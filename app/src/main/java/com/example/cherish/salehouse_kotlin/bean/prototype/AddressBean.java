package com.example.cherish.salehouse_kotlin.bean.prototype;

/**
 * 用户地址
 * Created by cherish
 */

public class AddressBean  implements  Cloneable{
    public String province;
    public String city;
    public String area;

    public AddressBean(String province, String city, String area) {
        this.province = province;
        this.city = city;
        this.area = area;
    }

    @Override
    protected AddressBean clone() throws CloneNotSupportedException {
        return (AddressBean) super.clone();
    }
}
