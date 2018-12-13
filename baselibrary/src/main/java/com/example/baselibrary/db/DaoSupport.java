package com.example.baselibrary.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据库工厂
 * Created by cherish
 */

public class DaoSupport<T> implements IDaoSupport<T> {
    // SQLiteDatabase
    private SQLiteDatabase mSqLiteDatabase;
    // 泛型类
    private Class<T> mClazz;

    private String TAG = "DaoSupport";
    //使用缓存
    private static final Object[] mPutMethodArgs = new Object[2];
    private static final Map<String, Method> mPutMethods = new ArrayMap<>();

    /*"create table if not exists Person ("
                 + "id integer primary key autoincrement, "
                 + "name text, "
                 + "age integer, "
                 + "flag boolean)";*/
    @Override
    public void create(SQLiteDatabase sqLiteDatabase, Class<T> aClass) {
        this.mSqLiteDatabase = sqLiteDatabase;
        this.mClazz = aClass;
        // 创建表
        StringBuffer sb = new StringBuffer();
        sb.append("create table if not exists ");
        sb.append(DaoReflexUtils.getTableName(mClazz));
        sb.append("(id integer primary key autoincrement,");
        Field[] fields = mClazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);// 设置权限
            sb.append(field.getName());
            sb.append(DaoReflexUtils.getColumnType(field.getType().getSimpleName()));
            sb.append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), ")");
        String createTable = sb.toString();
        Log.e(TAG, "表语句------>" + createTable);
        mSqLiteDatabase.execSQL(createTable);

    }

    @Override
    public long insert(T obj) {
        // 使用的其实还是  原生的使用方式，只是我们是封装一下而已
        ContentValues values = contentValuesByObj(obj);
        // null  速度比第三方的快一倍左右
        return mSqLiteDatabase.insert(DaoReflexUtils.getTableName(mClazz), null, values);
    }

    @Override
    public void insert(ArrayList<T> datas) {
        // 批量插入采用 事物
        mSqLiteDatabase.beginTransaction();
        for (T data : datas) {
            // 调用单条插入
            insert(data);
        }
        mSqLiteDatabase.setTransactionSuccessful();
        mSqLiteDatabase.endTransaction();
    }

    @Override
    public List<T> query() {
        Cursor query = mSqLiteDatabase.query(DaoReflexUtils.getTableName(mClazz), null, null,
                null, null, null, null);
        return cursorToList(query);
    }

    @Override
    public T queryById(String whereClause, String[] whereArgs) {
        Cursor cursor = mSqLiteDatabase.query(DaoReflexUtils.getTableName(mClazz), null,
                whereClause, whereArgs, null, null, null, null);
        List<T> list = cursorToList(cursor);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public long remove(String whereClause, String[] whereArgs) {
        return mSqLiteDatabase.delete(DaoReflexUtils.getTableName(mClazz), whereClause, whereArgs);
    }


    @Override
    public long remove() {
        return mSqLiteDatabase.delete(DaoReflexUtils.getTableName(mClazz), null, null);
    }

    @Override
    public long update(T data, String whereClause, String[] whereArgs) {
        ContentValues values = contentValuesByObj(data);
        return mSqLiteDatabase.update(DaoReflexUtils.getTableName(mClazz), values, whereClause,
                whereArgs);
    }


    /*ContentValues values = new ContentValues();
          values.put("name",person.getName());
          values.put("age",person.getAge());
          values.put("flag",person.getFlag());
          db.insert("Person",null,values);*/
    // obj 转成 ContentValues
    // obj 转成 ContentValues
    private ContentValues contentValuesByObj(T obj) {
        // 第三方的 使用比对一下 了解一下源码
        ContentValues values = new ContentValues();

        // 封装values
        Field[] fields = mClazz.getDeclaredFields();

        for (Field field : fields) {
            try {
                // 设置权限，私有和共有都可以访问
                field.setAccessible(true);
                String key = field.getName();
                // 获取value
                Object value = field.get(obj);
                // put 第二个参数是类型  把它转换

                mPutMethodArgs[0] = key;
                mPutMethodArgs[1] = value;

                // 方法使用反射 ， 反射在一定程度上会影响性能
                // 源码里面  activity实例 反射  View创建反射
                // 第三方以及是源码给我们提供的是最好的学习教材   插件换肤
                // 感谢google提供的源码，我们明天再见

                String filedTypeName = field.getType().getName();
                // 还是使用反射  获取方法  put  缓存方法
                Method putMethod = mPutMethods.get(filedTypeName);
                if (putMethod == null) {
                    putMethod = ContentValues.class.getDeclaredMethod("put",
                            String.class, value.getClass());
                    mPutMethods.put(filedTypeName, putMethod);
                }

                // 通过反射执行
                putMethod.invoke(values, mPutMethodArgs);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mPutMethodArgs[0] = null;
                mPutMethodArgs[1] = null;
            }
        }
        return values;
    }

    private List<T> cursorToList(Cursor cursor) {
        List<T> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            //通过反射new 对象
            String columnName;

            Field[] fields = mClazz.getDeclaredFields();

            try {
                // 通过反射new对象
                T instance = mClazz.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    columnName = field.getName();
                    //获取角标 获取在第几列
                    int index = cursor.getColumnIndex(columnName);
                    if (index == -1) {
                        continue;
                    }
                    //通过反射获取 游标的方法 filed.getType() -> 获取的类型
                    //int anInt = cursor.getInt(index);
                    Method cursorMethod = cursorMethod(field.getType());
                    if (cursorMethod != null) {
                        Object value = cursorMethod.invoke(cursor, index);
                        if (value == null) {
                            continue;
                        }

                        // 处理一些特殊的部分
                        if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                            if ("0".equals(String.valueOf(value))) {
                                value = false;
                            } else if ("1".equals(String.valueOf(value))) {
                                value = true;
                            }
                        } else if (field.getType() == char.class || field.getType() == Character.class) {
                            value = ((String) value).charAt(0);
                        } else if (field.getType() == Date.class) {
                            long date = (Long) value;
                            if (date <= 0) {
                                value = null;
                            } else {
                                value = new Date(date);
                            }
                        }

                        // 通过反射注入
                        field.set(instance, value);


                    }

                }
                //加入集合
                list.add(instance);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        cursor.close();
        return list;
    }

    // 获取游标的方法
    private Method cursorMethod(Class<?> type) throws Exception {
        String methodName = getColumnMethodName(type);
        // type String getString(index); int getInt; boolean getBoolean
        Method method = Cursor.class.getMethod(methodName, int.class);
        return method;
    }

    private String getColumnMethodName(Class<?> fieldType) {
        String typeName;
        if (fieldType.isPrimitive()) {
            typeName = DaoReflexUtils.capitalize(fieldType.getName());
        } else {
            typeName = fieldType.getSimpleName();
        }
        String methodName = "get" + typeName;
        if ("getBoolean".equals(methodName)) {
            methodName = "getInt";
        } else if ("getChar".equals(methodName) || "getCharacter".equals(methodName)) {
            methodName = "getString";
        } else if ("getDate".equals(methodName)) {
            methodName = "getLong";
        } else if ("getInteger".equals(methodName)) {
            methodName = "getInt";
        }
        return methodName;
    }
}
