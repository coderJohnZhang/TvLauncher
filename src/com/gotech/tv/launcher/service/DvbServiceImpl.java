package com.gotech.tv.launcher.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.dvb.service.IDvbService;
import com.gotech.tv.launcher.util.LogTool;

public class DvbServiceImpl {
    private final static String TAG = DvbServiceImpl.class.getSimpleName();
    private static IDvbService instance;

    private static final Object obj = new Object();
    private static ServiceConnection mRemoteConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            instance = IDvbService.Stub.asInterface(service);
            LogTool.d(TAG, "ComponentName = " + className.getClassName() + "  instance " + instance);
            synchronized (obj) {
                obj.notifyAll();
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            instance = null;
        }
    };

    public static synchronized boolean isInit() {
        return instance != null;
    }

    public static synchronized boolean init(Context context) {
        if (instance == null) {
            Intent service = new Intent(IDvbService.class.getName());
            boolean bBindOk = context.getApplicationContext().bindService(service, mRemoteConnection, Context.BIND_AUTO_CREATE);
            Log.d(TAG, "bBindOk = " + bBindOk + "  IDvbService.class.getName() " + IDvbService.class.getName());
            return bBindOk;
        }
        return true;
    }

    public static synchronized boolean init(Context ctx, final InitListener listener) {
        if (instance == null) {
            if (listener != null) {
                new Thread() {
                    @Override
                    public void run() {
                        synchronized (obj) {
                            if (instance == null) {
                                try {
                                    obj.wait(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            listener.onInitEnd(instance);
                        }
                    }
                }.start();
            }
            Intent service = new Intent(IDvbService.class.getName());
            try {
                boolean bBindOk = ctx.getApplicationContext().bindService(service, mRemoteConnection, Context.BIND_AUTO_CREATE);
                Log.d(TAG, "bBindOk = " + bBindOk);
                return bBindOk;
            } catch (SecurityException e) {
                e.printStackTrace();
                return false;
            }

        } else {
            if (listener != null) {
                listener.onInitEnd(instance);
            }
            return true;
        }

    }

    public static synchronized void deInit(Context context) {
        if (instance != null) {
            Log.i(TAG, "deInit...");
            context.unbindService(mRemoteConnection);
            instance = null;
        }
    }

    public static synchronized IDvbService getDvbService(final Context context) throws UnInitException {
        if (instance == null) {
            LogTool.e(TAG, " instance " + instance);
            throw new UnInitException("Please Call init First");
        }
        LogTool.e(TAG, " instance " + instance);
        return instance;
    }

    public static class UnInitException extends Exception {
        private static final long serialVersionUID = 1L;

        public UnInitException() {
            super();
        }

        public UnInitException(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
        }

        public UnInitException(String detailMessage) {
            super(detailMessage);
        }

        public UnInitException(Throwable throwable) {
            super(throwable);
        }

    }

    public static interface InitListener {
        public void onInitEnd(IDvbService instance);
    }
}
