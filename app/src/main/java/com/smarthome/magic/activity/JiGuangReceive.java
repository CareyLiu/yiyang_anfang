package com.smarthome.magic.activity;

import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class JiGuangReceive extends JPushMessageReceiver {


    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);

        Log.i("jiguang_xx", customMessage.message);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
        Log.i("jiguang_xx", notificationMessage.inAppMsgContentBody);

    }
}
