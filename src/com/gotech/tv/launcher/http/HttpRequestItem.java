package com.gotech.tv.launcher.http;

import java.util.concurrent.Future;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

public class HttpRequestItem {
    public Context mContext;
    public Handler mHandler;
    public String mReqUrl;
    public Bundle mReqParams;
    public String mRspMsg;

    public int mKey;
    public long mStartTime;
    public Future<?> mHttpThread;
    public boolean mIsCanceled;

    public HttpRequestItem(Context context, Handler handler, String url) {
        this.mContext = context;
        this.mHandler = handler;
        this.mReqUrl = url;
    }

}
