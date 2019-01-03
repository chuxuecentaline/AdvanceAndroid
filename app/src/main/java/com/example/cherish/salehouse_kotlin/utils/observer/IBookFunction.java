package com.example.cherish.salehouse_kotlin.utils.observer;

/**
 * FIXME: cherish 请添加描述
 * Created by cherish
 */

public interface IBookFunction {
    void addBook(String name);

    void removeBook(String name);

    void updateBook(String name);
    int count();

    void selectBook();

}
