package com.example.cherish.salehouse_kotlin.okHttp.down;

import android.content.Context;

import java.io.File;

/**
 * 文件管理
 * Created by cherish
 */

public class FileManager {
    private Context mContext;
    private File mRootDir;

    private FileManager() {

    }
    public static FileManager getManager() {
        return FileManagerInstance.instance;
    }
    public void init(Context context){
        mContext=context;
    }

    /**
     * 通过网络的路径获取一个本地文件路径
     * @param url
     * @return
     */
    public File getFile(String url) {
        String fileName = Utils.md5Url(url);
        if(mRootDir == null){
            mRootDir = mContext.getCacheDir();
        }
        File file = new File(mRootDir,fileName);
        return file;
    }
    private static class FileManagerInstance {
        public volatile static FileManager instance = new FileManager();
    }
}
