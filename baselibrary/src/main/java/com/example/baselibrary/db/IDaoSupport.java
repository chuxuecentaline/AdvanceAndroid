package com.example.baselibrary.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cherish
 */

public interface IDaoSupport<T> {
    void create(SQLiteDatabase sqLiteDatabase, Class<T> aClass);

    public long insert(T data);

    public void insert(ArrayList<T> data);

    public List<T> query();

    public T queryById(String whereClause,String[] whereArgs);

    public long remove(String whereClause,String[] whereArgs);

    public long remove();

    public long update(T data,String whereClause,String[] whereArgs);


}
