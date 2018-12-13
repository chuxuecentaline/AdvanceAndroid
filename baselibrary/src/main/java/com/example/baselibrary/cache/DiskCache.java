package com.example.baselibrary.cache;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 磁盘缓存
 * Created by cherish
 */

public class DiskCache implements IoHandler {
    private static Context mContent;
    private File newFile;
    private String path = "";

    private DiskCache() {
        if (mContent == null) {//反射的时候会报错（必须加此判断）
            return;
        }
        if (Environment.getExternalStorageState().equals(Context.MODE_APPEND)) {//判断内存卡是否存在
            //得到sd卡的目录
            File dir = Environment.getExternalStorageDirectory();
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                    "cache";
        } else {
            path = mContent.getCacheDir().getAbsolutePath() + File.separator + "cache";
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        newFile = new File(dir, "cache.txt");


    }

    public static DiskCache getInstance(Context context) {
        mContent = context.getApplicationContext();
        return DiskCacheHolder.mDiskCache;
    }

    private static class DiskCacheHolder {
        private static volatile DiskCache mDiskCache = new DiskCache();
    }

    @Override
    public void setString(String key, String value) {
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(newFile, true);//以追加的模式将字符写入
            bw = new BufferedWriter(fw);//又包裹一层缓冲流 增强IO功能
            bw.write(value);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.flush();//将内容一次性写入文件
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void setInt(String key, int value) {

    }

    @Override
    public void setBoolean(String key, boolean value) {

    }

    @Override
    public void setFloat(String key, float value) {

    }

    @Override
    public void setObject(String key, Object value) {

    }

    @Override
    public String getString(String key) {
        String content;//读取到的内容
        String temp = "";//存放内容
        try {
            FileReader fr = new FileReader(newFile);
            BufferedReader br = new BufferedReader(fr);
            //一行行读取
            while ((content = br.readLine()) != null) {
                temp += content + "\r\n";
            }
            br.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return 0;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return false;
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return 0;
    }

    @Override
    public Object getObject(String key, Object value) {
        return null;
    }


}
