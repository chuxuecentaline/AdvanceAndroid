package com.example.cherish.salehouse_kotlin.okHttp.upload;

import android.util.Log;

import com.example.cherish.salehouse_kotlin.okHttp.UploadProgressListener;

import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

/**
 * 上传进度
 * Created by cherish
 */

public class ExMultipartBody extends RequestBody {
    private MultipartBody build;//静态代理
    private UploadProgressListener uploadProgressListener;
    private long mCurrentLength;

    public ExMultipartBody(MultipartBody build, UploadProgressListener uploadProgressListener) {
     this.build=build;
     this.uploadProgressListener=uploadProgressListener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        // 静态代理最终还是调用的代理对象的方法
        return build.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return build.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        ForwardingSink forwardingSink=new ForwardingSink(sink) {
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                long contentLength = build.contentLength();
                mCurrentLength+=byteCount;
                if(uploadProgressListener!=null){
                    uploadProgressListener.onProgress(contentLength,mCurrentLength);
                }
                Log.e("TAG",contentLength+" : "+mCurrentLength);
            }
        };
        // 转一把
        BufferedSink bufferedSink = Okio.buffer(forwardingSink);
        build.writeTo(bufferedSink);
        // 刷新，RealConnection 连接池
        bufferedSink.flush();

    }
}
