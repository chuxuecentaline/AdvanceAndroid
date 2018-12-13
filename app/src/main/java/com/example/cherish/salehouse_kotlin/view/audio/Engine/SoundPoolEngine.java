package com.example.cherish.salehouse_kotlin.view.audio.Engine;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.cherish.salehouse_kotlin.R;
import com.example.cherish.salehouse_kotlin.utils.ToastUtils;

/**
 * 声音短小，延迟度小，并且需要几种声音同时播放的场景，适合使用SoundPool
 * Created by cherish
 */

public class SoundPoolEngine implements PlayEngine {
    private SoundPool mSp;
    private int mLoad;
    private int mStreamID;
    private Context mContext;

    public void init(Context context) {
        mContext = context;
        SoundPool.Builder spb = new SoundPool.Builder();
        spb.setMaxStreams(10);
        mSp = spb.build();
        mLoad = mSp.load(context, R.raw.drop, 1);

    }

    @Override
    public void play() {
        mSp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                mStreamID= mSp.play(mLoad, 1, 1, 0, 0, 1);

            }
        });

    }

    @Override
    public void pause() {
        mSp.pause(mStreamID);
    }

    @Override
    public void seekTo(int progress) {

    }

    @Override
    public void reset() {
        ToastUtils.show(mContext, "reset");
        mSp.release();
    }

    @Override
    public void save() {

    }
}
