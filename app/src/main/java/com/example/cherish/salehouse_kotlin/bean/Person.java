package com.example.cherish.salehouse_kotlin.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cherish
 */

public class Person implements Serializable{
    public String name;
    public int age;
    public long data;
    public boolean gender;

    public Person() {

    }

    public Person(String name, int age, long data, boolean gender) {
        this.name = name;
        this.age = age;
        this.data = data;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + ", data=" + data + ", " +
                "gender=" + gender + '}';
    }
}
