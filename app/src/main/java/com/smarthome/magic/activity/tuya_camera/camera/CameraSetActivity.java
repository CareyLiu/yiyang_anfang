package com.smarthome.magic.activity.tuya_camera.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.ZhiNengRoomManageActivity;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_camera.dialog.BottomDialogView;
import com.smarthome.magic.activity.tuya_camera.dialog.BottomNewDialog;
import com.smarthome.magic.activity.tuya_camera.dialog.TishiNewDialog;
import com.smarthome.magic.activity.tuya_camera.utils.TuyaDeviceManager;
import com.smarthome.magic.activity.tuya_camera.utils.TuyaDialogUtils;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.config.PreferenceHelper;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class CameraSetActivity extends BaseActivity {


    @BindView(R.id.ll_device_info)
    LinearLayout ll_device_info;
    @BindView(R.id.ll_device_weizhi)
    LinearLayout ll_device_weizhi;
    @BindView(R.id.ll_device_name)
    LinearLayout ll_device_name;
    @BindView(R.id.ll_jichu)
    LinearLayout ll_jichu;
    @BindView(R.id.ll_yeshi)
    LinearLayout ll_yeshi;
    @BindView(R.id.ll_baojing)
    LinearLayout ll_baojing;
    @BindView(R.id.ll_cunchu)
    LinearLayout ll_cunchu;
    @BindView(R.id.bt_delete)
    TextView bt_delete;
    @BindView(R.id.tv_yeshi)
    TextView tv_yeshi;
    private ITuyaDevice device;
    private DeviceBean deviceBeen;
    private String yeshiPos;
    private String family_id;
    private String device_id;
    private String member_type;
    private String old_name;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String member_type, String device_id, String old_name) {
        Intent intent = new Intent(context, CameraSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("member_type", member_type);
        intent.putExtra("device_id", device_id);
        intent.putExtra("old_name", old_name);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera_set;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设置");
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
        initHuidiao();
    }

    private void init() {
        family_id = PreferenceHelper.getInstance(mContext).getString(AppConfig.PEIWANG_FAMILYID, "");
        device_id = getIntent().getStringExtra("device_id");
        old_name = getIntent().getStringExtra("old_name");
        member_type = getIntent().getStringExtra("member_type");

        deviceBeen = TuyaDeviceManager.getDeviceManager().getDeviceBeen();
        device = TuyaDeviceManager.getDeviceManager().getDevice();
        Map<String, Object> dps = deviceBeen.getDps();
        yeshiPos = (String) dps.get("108");
        if (yeshiPos.equals("0")) {
            tv_yeshi.setText("自动");
        } else if (yeshiPos.equals("2")) {
            tv_yeshi.setText("开启");
        } else if (yeshiPos.equals("1")) {
            tv_yeshi.setText("关闭");
        }
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(deviceBeen.getDevId())) {
                        getData((String) message.content);
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_REMOVED) {
                    String devId = TuyaDeviceManager.getDeviceManager().getDeviceBeen().getDevId();
                    String ccc = message.content.toString();
                    if (ccc.equals(devId)) {
                        finish();
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_STATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_NETWORKSTATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_DEVINFOUPDATE) {


                } else if (message.type == ConstanceValue.MSG_CAMERA_FAIL) {
                    TuyaDialogUtils.t(mContext, (String) message.content);
                }
            }
        }));
    }

    public void getData(String dpStr) {
        JSONObject jsonObject = JSON.parseObject(dpStr);
        Set<String> strings = jsonObject.keySet();
        Iterator<String> it = strings.iterator( );
        while (it.hasNext()) {
            // 获得key
            String key = it.next();
            String value = jsonObject.getString(key);
            jieData(key, value, jsonObject);
        }
    }

    private void jieData(String key, String value, JSONObject jsonObject) {
        Y.e("解析出的数据:  " + "key: " + key + ",value:" + value);
        if (key.equals("108")) {
            dismissProgressDialog();
            yeshiPos = value;
            if (yeshiPos.equals("0")) {
                tv_yeshi.setText("自动");
            } else if (yeshiPos.equals("2")) {
                tv_yeshi.setText("开启");
            } else if (yeshiPos.equals("1")) {
                tv_yeshi.setText("关闭");
            }
        }
    }

    @OnClick({R.id.bt_delete, R.id.ll_device_info, R.id.ll_device_weizhi, R.id.ll_device_name, R.id.ll_jichu, R.id.ll_yeshi, R.id.ll_baojing, R.id.ll_cunchu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_device_info:
                break;
            case R.id.ll_device_weizhi:
                clickRoom();
                break;
            case R.id.ll_device_name:
                break;
            case R.id.ll_jichu:
                CameraSetJichuActivity.actionStart(mContext);
                break;
            case R.id.ll_yeshi:
                clickYeshi();
                break;
            case R.id.ll_baojing:
                CameraSetBaojingActivity.actionStart(mContext);
                break;
            case R.id.ll_cunchu:
                CameraSetCunchuActivity.actionStart(mContext);
                break;
            case R.id.bt_delete:
                clickDelete();
                break;
        }
    }

    private void clickRoom() {
        Bundle bundle = new Bundle();
        bundle.putString("device_id", device_id);
        bundle.putString("family_id", family_id);
        bundle.putString("member_type", member_type);
        startActivity(new Intent(mContext, ZhiNengRoomManageActivity.class).putExtras(bundle));
    }

    private void clickYeshi() {
        List<String> names = new ArrayList<>();
        names.add("自动");
        names.add("开启");
        names.add("关闭");
        final BottomNewDialog bottomDialog = new BottomNewDialog(this);
        bottomDialog.setModles(names);
        bottomDialog.setClickListener(new BottomDialogView.ClickListener() {
            @Override
            public void onClickItem(int pos) {
                bottomDialog.dismiss();
                if (pos == 0) {
                    setYeshi(0);
                } else if (pos == 1) {
                    setYeshi(2);
                } else if (pos == 2) {
                    setYeshi(1);
                }
            }

            @Override
            public void onClickCancel(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.showBottom();
    }

    private void setYeshi(int pos) {
        showProgressDialog();
        Map<String, String> dps = new HashMap<>();
        dps.put("108", pos + "");
        device.publishDps(TuyaDeviceManager.getDeviceManager().getJosnString(dps), new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                dismissProgressDialog();
                Y.e("设置失败 " + error);
            }

            @Override
            public void onSuccess() {
                Y.e("设置成功");
            }
        });
    }

    private void clickDelete() {
        TishiNewDialog dialog = new TishiNewDialog(mContext, new TishiNewDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiNewDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiNewDialog dialog) {
                device.removeDevice(new IResultCallback() {
                    @Override
                    public void onError(String errorCode, String errorMsg) {
                        Y.t("移除失败:" + errorCode);
                    }

                    @Override
                    public void onSuccess() {
                        Y.t("移除成功");
                    }
                });
            }

            @Override
            public void onDismiss(TishiNewDialog dialog) {

            }
        });
        dialog.setTextCont("是否移除设备");
        dialog.setTextConfirm("移除");
        dialog.show();
    }
}
