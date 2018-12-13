package com.example.baselibrary.Utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.example.baselibrary.cache.PreferencesCache;
import com.github.mikephil.charting.utils.FSize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件工具类
 * Created by cherish
 */

public class FileUtils {
    public static File dbFile(Context context, String name) {
        //1.判断是否有内存卡
        String basePath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            basePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            basePath = context.getCacheDir().getPath();
        }
        String path = basePath + File.separator + "db";
        File dbRoot = new File(path);
        if (!dbRoot.exists()) {
            dbRoot.mkdirs();
        }
        return new File(dbRoot, name);
    }

    /**
     * 保存下载文件的路径
     *  @param context
     * @param is      输入流
     * @param total
     * @param path    保存路径
     * @param name    文件名.扩展名
     */
    public static boolean downFile(Context context, InputStream is, long total, String path, String name) {
        File file = new File(getDirPath(context, path), name);
        byte[] buf = new byte[2048];
        FileOutputStream os = null;
        int length=0;
        boolean success=false;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PreferencesCache.getInstance(null).setString("path",file.getPath());
            os = new FileOutputStream(file);
            long sum=0;
            while ((length = is.read(buf)) != -1) {
                os.write(buf,0,length);
                sum += length;
                int progress = (int) (sum * 1.0f / total * 100);
                System.out.println("当前进度"+progress);
            }
            os.flush();
            success= true;
        } catch (Exception e) {
            e.printStackTrace();
            success=false;
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }
            try {
                if (os != null)
                    os.close();
            } catch (IOException e) {
            }
            return success;
        }


    }

    private static String getDirPath(Context context, String path) {
        String basePath;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            basePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            basePath = context.getCacheDir().getPath();
        }

        File file = new File(basePath + File.separator + path);
        if (!file.exists()) {
            file.mkdirs();
        }
        System.out.println("file---------" + file.getPath());
        return file.getPath();
    }

    /**
     * @param url
     * @return
     * 从下载连接中解析出文件名
     */
    @NonNull
    public static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    /**
     * 获取下载路径
     * @return
     */
    public static String getDownPath() {
        return   PreferencesCache.getInstance(null).getString("path");
    }
}
