package com.example.cherish.salehouse_kotlin.view.audio;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.ToastUtils;
import com.example.cherish.salehouse_kotlin.view.audio.Engine.SoundPoolEngine;


/**
 * 自定义播放控件
 * <p>
 * Created by cherish
 */

public class AudioPlayView extends LinearLayout implements View.OnClickListener, ObserverListener {
    private DefineProgressBar mProgress;
    private AppCompatTextView mTvStartTime;
    private AppCompatTextView mTvEndTime;
    private ImageView mIvPlay;
    private MediaPlayUtils mPlay;
    Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            //  super.handleMessage(msg);
            AudioInfo info = (AudioInfo) msg.obj;
            setCurrentTime((long) info.duration);
            setCurrentProgress(info.step);
        }
    };
    private int mStatus;
    private AudioInfo audioInfo;

    public AudioPlayView(Context context) {
        this(context, null);
    }

    public AudioPlayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AudioPlayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.audio_layout, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mProgress = findViewById(R.id.progress);
        mTvStartTime = findViewById(R.id.tv_start_time);
        mTvEndTime = findViewById(R.id.tv_end_time);
        mIvPlay = findViewById(R.id.iv_play);
        mIvPlay.setOnClickListener(this);
        mPlay = new MediaPlayUtils();
        mPlay.init(getContext(), new MediaPlayUtils.MediaPlaySeekCallBack() {
            @Override
            public void seek(int step, int total) {
                if (audioInfo == null) {
                    audioInfo = new AudioInfo();
                }
                mHandler.removeMessages(0);
                audioInfo.duration = step;
                audioInfo.step = 1f*step/total ;
                Message message = Message.obtain();
                message.obj = audioInfo;
                message.what = 0;
                mHandler.sendMessage(message);


            }
        });
        setStartTime(mPlay.getDuration());
        mProgress.setEnableDrag(true);
        //注册
        ObserverManager.getInstance().add(this);
        SoundPoolEngine soundPoolEngine = new SoundPoolEngine();
        soundPoolEngine.init(getContext());
        soundPoolEngine.play();


    }

    public void setCurrentProgress(float step) {

        mProgress.setCurrentProgress(step);
    }

    public void setStartTime(int time) {
        mTvStartTime.setText(formatTime(time));
    }

    public void setCurrentTime(long time) {

        mTvEndTime.setText(formatTime(time));
    }

    public String formatTime(long time) {
        time = time / 1000;
        String strHour = "" + (time / 3600);
        String strMinute = "" + time % 3600 / 60;
        String strSecond = "" + time % 3600 % 60;
        strHour = strHour.length() < 2 ? "0" + strHour : strHour;
        strMinute = strMinute.length() < 2 ? "0" + strMinute : strMinute;
        strSecond = strSecond.length() < 2 ? "0" + strSecond : strSecond;
        String duration = "";
        if (!strHour.equals("00")) {
            duration += strHour + ":";
        }
      //  if (!strMinute.equals("00")) {
            duration += strMinute + ":";
      //  }

        duration += strSecond;
        return duration;
    }

    @Override
    public void onClick(View v) {
        if (mStatus != 0) {
            ToastUtils.show(getContext(), "音频下载中，请耐心等待");
        }
        if (mPlay != null) {
            mPlay.play();
        }

    }

    @Override
    public void observerUpProgress(float progress) {
        if (mPlay != null) {
            if (mPlay.isPlay()) {
                mPlay.seekTo((int) (progress * mPlay.getDuration()));

            }
        }
    }

    public void downStatus(int status) {
        mStatus = status;
    }

    /**
     * 设置播放路径
     *
     * @param path
     */
    public void setPlayPath(String path) {
        mPlay.setUrl(path);

    }

    public void onDestroy() {
        mPlay.release();
        mProgress.release();
    }
}
