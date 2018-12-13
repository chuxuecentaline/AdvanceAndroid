package com.example.cherish.salehouse_kotlin.view.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.cherish.salehouse_kotlin.utils.ToastUtils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 音频播放器
 * Created by cherish
 */

public class MediaPlayUtils implements MediaPlayer.OnErrorListener, MediaPlayer
        .OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnCompletionListener {
    MediaPlayer mediaPlayer;//播放音频控件
    //网络url
    private String url = "http://bmob-cdn-14121.b0.upaiyun" +
            ".com/2017/09/26/464edee940b4371b80f9f4b4627f92e0.mp3";
    //存放的路径
    private String path;
    private Context mContext;
    private MediaPlaySeekCallBack mCallBack;
    //播放状态
    private boolean isPrepare;
    private Timer mTimer;

    public interface MediaPlaySeekCallBack {
        void seek(int step, int total);
    }

    public void init(Context context, MediaPlaySeekCallBack callBack) {
        mContext = context;
        mCallBack = callBack;
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnErrorListener(this);//设置错误监听
        mediaPlayer.setOnPreparedListener(this);//资源准备完成监听
        mediaPlayer.setOnSeekCompleteListener(this);//seek监听
        mediaPlayer.setOnCompletionListener(this);//播放完成监听
        long time = mediaPlayer.getDuration();//获得了视频的时长（以毫秒为单位）
        System.out.println("------------时长"+time);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 开始播放，开始是在准备结束完成后才播放的
     */
    public synchronized void start() {

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(mContext, Uri.parse(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //因为是在线播放，所以采用异步的方式进行资源准备，
        // 准备完成后会执行OnPreparedListener的onPrepared方法
        mediaPlayer.prepareAsync();

    }


    /**
     * 快进
     */
    public void next(int step) {
        int curPosition = mediaPlayer.getCurrentPosition();
        if ((curPosition + step) > mediaPlayer.getDuration()) return;
        mediaPlayer.seekTo(curPosition + step);
    }

    /**
     * 快退
     */
    public void previous(int step) {
        int curPosition = mediaPlayer.getCurrentPosition();
        if ((curPosition - step) < 0) return;
        mediaPlayer.seekTo(curPosition - step);
    }

    /**
     * 暂停
     */
    public void pause() {
        if (isPlay()) mediaPlayer.pause();
    }

    /**
     * 资源释放，在退出界面是调用
     */
    public void release() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        mediaPlayer.release();
        mediaPlayer = null;
    }

    /**
     * 继续播放
     */
    public synchronized void play() {
        if (!isPrepare) {
            start();
        } else {
            if (!isPlay()) {
                mediaPlayer.start();
            } else {
                mediaPlayer.pause();
            }
        }


    }


    /**
     * 当前是否在播放
     *
     * @return
     */
    public boolean isPlay() {
        return mediaPlayer!=null&&mediaPlayer.isPlaying();
    }


    /**
     * 手动定位播放位置，在外部滑动seekbar时调用
     *
     * @param position
     */
    public void seekTo(int position) {
        System.out.println("-------seekTo"+position);
        if (position < 0 || position > mediaPlayer.getDuration()) return;
        mediaPlayer.seekTo(position);
    }

    /**
     * 获取总时长
     *
     * @return
     */
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d("mp", "播放失败: " + what + "--" + extra);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //准备完成，开始播放，并修改状态
        ToastUtils.show(mContext, "音频准备中");
        isPrepare = true;
        mp.start();
        setTimer();
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {


    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //播放完成
        isPrepare = false;
        mp.reset();

    }

    /**
     * 设置定时器
     */
    private void setTimer() {
        mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (isPrepare && mediaPlayer != null) {
                    if (isPlay())
                        mCallBack.seek(mediaPlayer.getCurrentPosition(), mediaPlayer.getDuration());
                }

            }
        };
        mTimer.schedule(mTimerTask, 0, 1);
    }


}
