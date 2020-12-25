package com.smarthome.magic.activity.tuya_device.utils.manager;

import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.utils.TuyaDialogUtils;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.List;

public class TuyaHomeManager {
    private static TuyaHomeManager instance;
    private HomeBean homeBean;
    private long homeId;

    public static TuyaHomeManager getHomeManager() {
        if (null == instance) {
            instance = new TuyaHomeManager();
        }
        return instance;
    }

    public void setHomeBean(HomeBean homeBean) {
        this.homeBean = homeBean;
    }

    public HomeBean getHomeBean() {
        return homeBean;
    }

    public long getHomeId() {
        return homeId;
    }

    public void setHomeId(long homeId) {
        this.homeId = homeId;
        initHome();
    }

    private void initHome() {
        TuyaHomeSdk.newHomeInstance(homeId).getHomeDetail(new ITuyaHomeResultCallback() {
            @Override
            public void onSuccess(HomeBean bean) {
                Y.e("设置家庭成功了");
                setHomeBean(bean);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Y.e("设置家庭失败了");
            }
        });
    }

    public DeviceBean isHaveDevice(String devId) {
        DeviceBean  mineDeviceBean=null;
        if (homeBean!=null){
            List<DeviceBean> deviceList = homeBean.getDeviceList();
            for (int i = 0; i < deviceList.size(); i++) {
                DeviceBean deviceBean = deviceList.get(i);
                if (devId.equals(deviceBean.getDevId())) {
                    mineDeviceBean=deviceBean;
                }
            }
        }
        return mineDeviceBean;
    }
}
