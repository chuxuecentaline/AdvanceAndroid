package com.example.aidllibrary;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * 响应参数
 *
 * @Author: cherish
 * @CreateDate: 2019/1/31 11:15
 */

public class IPCResponse implements Parcelable{
    private  String content;
    IPCRequest mIPCRequest;

    protected IPCResponse(Parcel in) {
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<IPCResponse> CREATOR = new Creator<IPCResponse>() {
        @Override
        public IPCResponse createFromParcel(Parcel in) {
            return new IPCResponse(in);
        }

        @Override
        public IPCResponse[] newArray(int size) {
            return new IPCResponse[size];
        }
    };
}
