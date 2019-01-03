package com.example.cherish.salehouse_kotlin.utils.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 图书管理
 * Created by cherish
 */

public class LibraryManager extends Observable implements IBookFunction {
     List<String> books;
    public LibraryManager() {
        books=new ArrayList<>();
    }

    @Override
    public void addBook(String name) {
        books.add(name);
        setChanged();
        notifyObservers(name);

    }

    @Override
    public void removeBook(String name) {
        books.remove(name);
        setChanged();
        notifyObservers(name);
    }

    @Override
    public void updateBook(String name) {
        books.add(name);
        setChanged();
        notifyObservers(name);
    }

    @Override
    public int count() {
        return books.size();
    }

    @Override
    public void selectBook() {
        for (int i = 0; i < books.size(); i++) {
            System.out.println("查询"+books.get(i));
        }
    }


}
