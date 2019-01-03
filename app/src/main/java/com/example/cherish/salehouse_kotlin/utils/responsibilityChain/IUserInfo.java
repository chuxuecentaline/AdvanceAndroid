package com.example.cherish.salehouse_kotlin.utils.responsibilityChain;

/**
 * 查询用户信息
 * Created by cherish
 */

public interface IUserInfo {
    User queryUser(String account, String password);
}
