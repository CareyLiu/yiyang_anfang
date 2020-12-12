package com.smarthome.magic.activity.tuya_camera.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_camera.utils.TuyaDeviceManager;
import com.smarthome.magic.app.BaseActivity;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuyasmart.camera.devicecontrol.ITuyaCameraDevice;
import com.tuyasmart.camera.devicecontrol.TuyaCameraDeviceControlSDK;
import com.tuyasmart.camera.devicecontrol.api.ITuyaCameraDeviceControlCallback;
import com.tuyasmart.camera.devicecontrol.bean.DpSDFormatStatus;
import com.tuyasmart.camera.devicecontrol.bean.DpSDRecordSwitch;
import com.tuyasmart.camera.devicecontrol.bean.DpSDStatus;
import com.tuyasmart.camera.devicecontrol.bean.DpSDStorage;
import com.tuyasmart.camera.devicecontrol.model.DpNotifyModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraSetCunchuActivity extends BaseActivity implements ITuyaCameraDeviceControlCallback {

    @BindView(R.id.tv_zongrongliang)
    TextView tv_zongrongliang;
    @BindView(R.id.tv_yishiyong)
    TextView tv_yishiyong;
    @BindView(R.id.tv_shengyurongliang)
    TextView tv_shengyurongliang;
    @BindView(R.id.iv_switch_luxiang)
    ImageView iv_switch_luxiang;
    @BindView(R.id.ll_dingshi)
    LinearLayout ll_dingshi;
    @BindView(R.id.bt_geshihua)
    TextView bt_geshihua;
    private DeviceBean deviceBeen;
    private ITuyaCameraDevice mTuyaCameraDevice;
    private boolean isluxiang;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CameraSetCunchuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera_set_cunchu;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("存储设置");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        showProgressDialog();
        deviceBeen = TuyaDeviceManager.getDeviceManager().getDeviceBeen();
        mTuyaCameraDevice = TuyaCameraDeviceControlSDK.getCameraDeviceInstance(deviceBeen.getDevId());

        mTuyaCameraDevice.registorTuyaCameraDeviceControlCallback(DpSDStatus.ID, this);
        mTuyaCameraDevice.registorTuyaCameraDeviceControlCallback(DpSDStorage.ID, this);
        mTuyaCameraDevice.registorTuyaCameraDeviceControlCallback(DpSDRecordSwitch.ID, this);
        mTuyaCameraDevice.registorTuyaCameraDeviceControlCallback(DpSDFormatStatus.ID, this);

        mTuyaCameraDevice.publishCameraDps(DpSDStatus.ID, null);
        mTuyaCameraDevice.publishCameraDps(DpSDStorage.ID, null);
        mTuyaCameraDevice.publishCameraDps(DpSDFormatStatus.ID, null);

        isluxiang = (boolean) deviceBeen.getDps().get("150");

        mTuyaCameraDevice.publishCameraDps(DpSDRecordSwitch.ID, isluxiang);
    }

    @Override
    public void onSuccess(String s, DpNotifyModel.ACTION action, DpNotifyModel.SUB_ACTION sub_action, Object o) {
        dismissProgressDialog();
        Y.e("获取成功了  " + action + "   " + sub_action + "   " + o.toString());
        if (action.equals(DpNotifyModel.ACTION.SDCARD)) {
            if (sub_action.equals(DpNotifyModel.SUB_ACTION.SDCARD_STATUS)) {
                String status = o.toString();
                if (status.equals("1")) {

                }
            } else if (sub_action.equals(DpNotifyModel.SUB_ACTION.REQUEST_STORAGE)) {
                String[] split = o.toString().replace("|", ",").split(",");
                if (split.length == 3) {
                    String s1 = TuyaDeviceManager.bytes2kb(Y.getLong(split[0]));
                    String s2 = TuyaDeviceManager.bytes2kb(Y.getLong(split[1]));
                    String s3 = TuyaDeviceManager.bytes2kb(Y.getLong(split[2]));

                    tv_zongrongliang.setText(s1);
                    tv_yishiyong.setText(s2);
                    tv_shengyurongliang.setText(s3);
                }
            } else if (sub_action.equals(DpNotifyModel.SUB_ACTION.PROGRESS)) {

            }
        } else if (action.equals(DpNotifyModel.ACTION.SDCARD_RECORD_SWITCH)) {
            if (sub_action.equals(DpNotifyModel.SUB_ACTION.SET_STATUS)) {
                isluxiang = (boolean) o;
                if (isluxiang) {
                    iv_switch_luxiang.setImageResource(R.mipmap.switch_open);
                } else {
                    iv_switch_luxiang.setImageResource(R.mipmap.switch_close);
                }
            }
        }
    }

    @Override
    public void onFailure(String s, DpNotifyModel.ACTION action, DpNotifyModel.SUB_ACTION sub_action, String s1, String s2) {
        dismissProgressDialog();
    }

    @OnClick({R.id.iv_switch_luxiang, R.id.ll_dingshi, R.id.bt_geshihua})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_switch_luxiang:
                mTuyaCameraDevice.publishCameraDps(DpSDRecordSwitch.ID, !isluxiang);
                break;
            case R.id.ll_dingshi:
                break;
            case R.id.bt_geshihua:
                break;
        }
    }
}
