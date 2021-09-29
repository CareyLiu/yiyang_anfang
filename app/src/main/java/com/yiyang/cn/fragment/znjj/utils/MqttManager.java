package com.yiyang.cn.fragment.znjj.utils;

import android.content.Context;

import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.fragment.znjj.model.ZhiNengModel;

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
