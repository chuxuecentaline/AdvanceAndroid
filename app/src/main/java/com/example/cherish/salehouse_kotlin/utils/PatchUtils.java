package com.example.cherish.salehouse_kotlin.utils;

/**
 *  批量更新
 * Created by cherish
 */

public class PatchUtils {
    /**
     *
     * @param oldApkPath 原来的apk 1.0 本地安装的apk
     * @param newApkPath 合并后新的apk路径 需要生成的2.0
     * @param patchPath  差分包路径 从服务器下载的
     */
    public  static  native  void Combine(
            String oldApkPath,String newApkPath,String patchPath
    );
}
