package com.example.baselibrary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.baselibrary.Utils.FileUtils;

import java.io.File;

/**
 * Created by cherish
 */

public class DaoSupportFactory {
    private static Context mContext;

    //静态内部类实现单列模式
    public static synchronized DaoSupportFactory getInstance(Context context) {
        mContext = context;
        return DaoSupportHolder.instance;
    }

    private static class DaoSupportHolder {
        public static DaoSupportFactory instance = new DaoSupportFactory();
    }

    // 持有外部数据库的引用
    private SQLiteDatabase mSqLiteDatabase;

    public DaoSupportFactory() {
        //打开或创建一个数据库
        File file = FileUtils.dbFile(mContext, "nhdz.db");
        mSqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);

    }

    public <T> IDaoSupport<T> getDao(Class<T> Class) {
        IDaoSupport<T> daoSoupport = new DaoSupport();
        daoSoupport.create(mSqLiteDatabase, Class);
        return daoSoupport;

    }


}
