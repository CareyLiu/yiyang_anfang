package com.smarthome.magic.activity.tuya_camera.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.IDevListener;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TuyaDeviceManager implements IDevListener {
    private static TuyaDeviceManager instance;
    private DeviceBean deviceBean;
    private ITuyaDevice mDevice;
    private Gson gson;

    public static TuyaDeviceManager getDeviceManager() {
        if (null == instance) {
            instance = new TuyaDeviceManager();
        }
        return instance;
    }

    public void initDevice(DeviceBean deviceBean) {
        this.deviceBean = deviceBean;
        mDevice = TuyaHomeSdk.newDeviceInstance(deviceBean.getDevId());
        mDevice.registerDevListener(this);
        gson = new Gson();

        String schema = deviceBean.getSchema();
        Y.e(schema);
    }

    public ITuyaDevice getDevice() {
        return mDevice;
    }

    public DeviceBean getDeviceBeen() {
        return deviceBean;
    }

    public void setDeviceBeen(DeviceBean deviceBean) {
        this.deviceBean = deviceBean;
    }

    public String getJosnString(Map dps) {
        return gson.toJson(dps);
    }

    public void unRegisterDevListener() {
        if (mDevice != null) {
            mDevice.unRegisterDevListener();
            mDevice.onDestroy();
        }
    }


    /**
     * dp数据更新
     *
     * @param devId 设备 id
     * @param dpStr 设备发生变动的功能点，为 json 字符串，数据格式：{"101": true}
     */
    @Override
    public void onDpUpdate(String devId, String dpStr) {
        Y.e("Dp点数据更新啦:" + devId + " | " + dpStr);
        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_DEVICE_DATA;
        notice.content = dpStr;
        notice.devId = devId;
        RxBus.getDefault().sendRx(notice);
    }

    /**
     * 设备移除回调
     *
     * @param devId 设备id
     */
    @Override
    public void onRemoved(String devId) {
        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_DEVICE_REMOVED;
        notice.content = devId;
        RxBus.getDefault().sendRx(notice);
    }


    /**
     * 设备上下线回调
     *
     * @param devId  设备id
     * @param online 是否在线，在线为 true
     */
    @Override
    public void onStatusChanged(String devId, boolean online) {

    }


    /**
     * 网络状态发生变动时的回调
     *
     * @param devId  设备id
     * @param status 网络状态是否可用，可用为 true
     */
    @Override
    public void onNetworkStatusChanged(String devId, boolean status) {

    }

    /**
     * 设备信息更新回调
     *
     * @param devId 设备 id
     */
    @Override
    public void onDevInfoUpdate(String devId) {

    }

    public void getData(String devId, String dpStr) {
        JSONObject jsonObject = JSON.parseObject(dpStr);
        Set<String> strings = jsonObject.keySet();
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            // 获得key
            String key = it.next();
            String value = jsonObject.getString(key);
            Y.e("key: " + key + ",value:" + value);
        }
    }

    private int p2pType;

    public int getP2pType() {
        Map<String, Object> map = deviceBean.getSkills();
        p2pType = 1;
        if (map == null || map.size() == 0) {
            p2pType = 1;
        } else {
            p2pType = (Integer) (map.get("p2pType"));
        }
        return p2pType;
    }

    //定义GB的计算常量
    private static final int GB = 1024 * 1024;
    //定义MB的计算常量
    private static final int MB = 1024;

    public static String bytes2kb(long bytes) {
        //格式化小数
        DecimalFormat format = new DecimalFormat("###.0");
        if (bytes / GB >= 1) {
            return format.format(bytes / GB) + "GB";
        } else if (bytes / MB >= 1) {
            return format.format(bytes / MB) + "MB";
        } else {
            return bytes + "KB";
        }
    }

    public static final String CAMERA_A = "aooci0uh0ufwuaey";
    public static final String CAMERA_B = "qw9quez5r4m8neyh";


}
