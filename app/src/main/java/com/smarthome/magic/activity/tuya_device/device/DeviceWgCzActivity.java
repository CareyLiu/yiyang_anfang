package com.smarthome.magic.activity.tuya_device.device;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.gyf.barlibrary.ImmersionBar;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.TuyaBaseDeviceActivity;
import com.smarthome.magic.activity.tuya_device.device.tongyong.DeviceDingshiActivity;
import com.smarthome.magic.activity.tuya_device.device.tongyong.DeviceSetActivity;
import com.smarthome.magic.activity.tuya_device.utils.TuyaConfig;
import com.smarthome.magic.activity.tuya_device.utils.TuyaDialogUtils;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.smarthome.magic.activity.wode_page.bazinew.utils.TimeUtils;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class DeviceWgCzActivity extends TuyaBaseDeviceActivity {


    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_switch_name)
    TextView tv_switch_name;
    @BindView(R.id.rl_set)
    RelativeLayout rl_set;
    @BindView(R.id.view_title)
    RelativeLayout view_title;
    @BindView(R.id.ll_kaiguan)
    LinearLayout ll_kaiguan;
    @BindView(R.id.ll_daojishi)
    LinearLayout ll_daojishi;
    @BindView(R.id.ll_dingshi)
    LinearLayout ll_dingshi;
    @BindView(R.id.ll_shezhi)
    LinearLayout ll_shezhi;
    @BindView(R.id.iv_switch_state)
    ImageView iv_switch_state;
    @BindView(R.id.tv_switch_state)
    TextView tv_switch_state;
    @BindView(R.id.tv_daojishi)
    TextView tv_daojishi;
    @BindView(R.id.view_main)
    RelativeLayout view_main;
    @BindView(R.id.ll_add_device)
    LinearLayout ll_add_device;
    @BindView(R.id.ll_no_data)
    LinearLayout ll_no_data;
    @BindView(R.id.rv_device_content)
    RecyclerView rv_device_content;
    private DeviceBean mDeviceBeen;
    private ITuyaDevice mDevice;

    private String ty_device_ccid;
    private String old_name;
    private boolean isOnline;
    private boolean switchState;//开关状态
    private int daojishi;

    private TimePickerView timePicker;
    private String productId;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String member_type, String device_id, String ty_device_ccid, String old_name, String room_name) {
        Intent intent = new Intent(context, DeviceWgCzActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("member_type", member_type);
        intent.putExtra("device_id", device_id);
        intent.putExtra("ty_device_ccid", ty_device_ccid);
        intent.putExtra("old_name", old_name);
        intent.putExtra("room_name", room_name);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_wangguan_swich;
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.black)
                .statusBarDarkFont(false)
                .init();
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    Y.e("倒计时是多少啊 " + daojishi);
                    if (daojishi > 0) {
                        daojishi--;
                        tv_daojishi.setVisibility(View.VISIBLE);
                        if (switchState) {
                            tv_daojishi.setText(daojishi + "秒后关闭插座");
                        } else {
                            tv_daojishi.setText(daojishi + "秒后开启插座");
                        }
                    } else {
                        tv_daojishi.setVisibility(View.GONE);
                    }
                    startHandler();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
        initHuidiao();
    }

    private void init() {
        ty_device_ccid = getIntent().getStringExtra("ty_device_ccid");
        old_name = getIntent().getStringExtra("old_name");
        tv_switch_name.setText(old_name);

        DeviceBean haveDevice = TuyaHomeManager.getHomeManager().isHaveDevice(ty_device_ccid);
        if (haveDevice != null) {
            TuyaDeviceManager.getDeviceManager().initDevice(haveDevice);
            initSwich();
        } else {
            TuyaDialogUtils.t(mContext, "设备已失效!");
        }
    }

    private void initSwich() {
        mDeviceBeen = TuyaDeviceManager.getDeviceManager().getDeviceBeen();
        mDevice = TuyaDeviceManager.getDeviceManager().getDevice();
        productId = mDeviceBeen.getProductId();

        Map<String, Object> dps = mDeviceBeen.getDps();
        switchState = (boolean) dps.get("1");
        setSwichState();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(mDeviceBeen.getDevId())) {
                        getData((String) message.content);
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_REMOVED) {
                    String devId = TuyaDeviceManager.getDeviceManager().getDeviceBeen().getDevId();
                    String ccc = message.devId;
                    if (ccc.equals(devId)) {
                        TuyaDeviceManager.getDeviceManager().device_removed(DeviceWgCzActivity.this);
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_STATUSCHANGED) {
                    if (message.devId.equals(mDeviceBeen.getDevId())) {
                        isOnline = (boolean) message.content;
                        if (!isOnline) {
                            Y.e("离线了啊啊啊啊");
                        }
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_NETWORKSTATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_DEVINFOUPDATE) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_DELETE) {
                    finish();
                }
            }
        }));
    }

    public void getData(String dpStr) {
        JSONObject jsonObject = JSON.parseObject(dpStr);
        Set<String> strings = jsonObject.keySet();
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            // 获得key
            String key = it.next();
            String value = jsonObject.getString(key);
            jieData(key, value, jsonObject);
        }
    }

    private void jieData(String key, String value, JSONObject jsonObject) {
        Y.e("解析出的数据:  " + "key = " + key + "  |  value = " + value);
        if (key.equals("1")) {//switch开关
            switchState = jsonObject.getBoolean(key);
            setSwichState();
        } else if (key.equals("11")) {//倒计时
            daojishi = Y.getInt(value);
            if (daojishi > 0) {
                tv_daojishi.setVisibility(View.VISIBLE);
                if (switchState) {
                    tv_daojishi.setText(daojishi + "秒后关闭插座");
                } else {
                    tv_daojishi.setText(daojishi + "秒后开启插座");
                }
                startHandler();
            } else {
                tv_daojishi.setVisibility(View.GONE);
                stopHandler();
            }
        } else if (key.equals("9")) {//倒计时
            daojishi = Y.getInt(value);
            if (daojishi > 0) {
                tv_daojishi.setVisibility(View.VISIBLE);
                if (switchState) {
                    tv_daojishi.setText(daojishi + "秒后关闭插座");
                } else {
                    tv_daojishi.setText(daojishi + "秒后开启插座");
                }
                startHandler();
            } else {
                tv_daojishi.setVisibility(View.GONE);
                stopHandler();
            }
        }
    }

    private void setSwichState() {
        if (switchState) {
            iv_switch_state.setImageResource(R.mipmap.tuya_chazuo_pic_on);
            view_main.setBackgroundColor(Y.getColor(R.color.color_main));
            view_title.setBackgroundColor(Y.getColor(R.color.color_main));
            tv_switch_state.setText("插座已开启");
        } else {
            iv_switch_state.setImageResource(R.mipmap.tuya_chazuo_pic_off);
            view_main.setBackgroundColor(Color.parseColor("#353E4F"));
            view_title.setBackgroundColor(Color.parseColor("#353E4F"));
            tv_switch_state.setText("插座已关闭");
        }
    }

    private void startHandler() {
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);
    }

    private void stopHandler() {
        handler.removeMessages(1);
    }

    @OnClick({R.id.rl_back, R.id.rl_set, R.id.ll_kaiguan, R.id.ll_daojishi, R.id.ll_dingshi, R.id.iv_switch_state})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_switch_state:
            case R.id.ll_kaiguan:
                clickSwitch();
                break;
            case R.id.ll_daojishi:
                clickDaojishi();
                break;
            case R.id.ll_dingshi:
                DeviceDingshiActivity.actionStart(mContext);
                break;
            case R.id.rl_set:
                set();
                break;
        }
    }

    private void set() {
        DeviceSetActivity.actionStart(mContext,
                getIntent().getStringExtra("member_type"),
                getIntent().getStringExtra("device_id"),
                getIntent().getStringExtra("old_name"),
                getIntent().getStringExtra("room_name")
        );
    }

    private void clickSwitch() {//开关
        setDp("1", !switchState);
    }

    private void clickDaojishi() {//倒计时
        try {
            Calendar instance = Calendar.getInstance();
            Date data = TimeUtils.getData("2014-10-30");
            instance.setTime(data);
            boolean[] select = {false, false, false, false, true, true};
            timePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    int minutes = date.getMinutes();
                    int seconds = date.getSeconds();
                    int timeDingshi = minutes * 60 + seconds;

                    if (TuyaConfig.PRODUCTID_SWITCH_A.equals(productId)) {
                        setDp("11", timeDingshi);
                    } else if (TuyaConfig.PRODUCTID_SWITCH_B.equals(productId)) {
                        setDp("11", timeDingshi);
                    } else if (TuyaConfig.PRODUCTID_SWITCH_WG.equals(productId)) {
                        setDp("9", timeDingshi);
                    }
                }
            })
                    .setType(select)// 默认全部显示
                    .setDate(instance)// 如果不设置的话，默认是系统时间*/
                    .build();
            timePicker.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TuyaDeviceManager.getDeviceManager().unRegisterDevListener();
        stopHandler();
    }
}
