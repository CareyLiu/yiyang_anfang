package com.smarthome.magic.fragment.znjj.utils;

import android.content.Context;

import com.airbnb.lottie.animation.content.Content;
import com.google.gson.Gson;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.fragment.znjj.model.ZhiNengModel;
import com.tuya.smart.sdk.bean.DeviceBean;

public class MqttManager {
    private static MqttManager instance;
    private Context mContext;


    public static MqttManager getMqttManager() {
        if (null == instance) {
            instance = new MqttManager();
        }
        return instance;
    }


    public void initMqtt(Context context, ZhiNengModel.DataBean dataBean) {
        mContext = context;
        PreferenceHelper.getInstance(mContext).putString(AppConfig.PEIWANG_FAMILYID, dataBean.getFamily_id());

        



    }


}
