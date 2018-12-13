package com.example.cherish.salehouse_kotlin.view.audio.Engine;

/**
 *  播放器引擎
 * Created by cherish
 */

public interface PlayEngine {
    /**
     * 播放
     */
    public void play();

    /**
     * 暂停
     */
    public void pause();

    /**
     * 播放进度
     * @param progress
     */
    public void seekTo(int progress);

    /**
     * 释放资源
     */
    public void reset();

    /**
     * 保存本地
     */
    public void save();
}
