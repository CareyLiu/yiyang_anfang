package com.smarthome.magic.activity.tuya_device;

import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.smarthome.magic.app.BaseActivity;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaDevice;

import java.util.HashMap;
import java.util.Map;

public class TuyaBaseDeviceActivity extends BaseActivity {

    public void setDp(String key, Object value) {
        TuyaDeviceManager.getDeviceManager().setDp(key, value);
    }


    public void getDp(String dpId) {
        TuyaDeviceManager.getDeviceManager().getDp(dpId);
    }
}
