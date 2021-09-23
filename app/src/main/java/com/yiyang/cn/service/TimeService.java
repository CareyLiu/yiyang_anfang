package com.yiyang.cn.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


import java.util.concurrent.ScheduledExecutorService;

public class TimeService extends Service {

    public String TAG = TimeService.class.getSimpleName();
    private static final long LOOP_TIME = 1; //循环时间
    private static ScheduledExecutorService mExecutorService;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

}
