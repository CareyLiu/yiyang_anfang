package com.smarthome.magic.activity.tuya_device.device.tongyong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.TuyaBaseDeviceActivity;
import com.smarthome.magic.activity.tuya_device.device.model.DpsTimeModel;
import com.smarthome.magic.activity.tuya_device.dialog.TuyaInputDialog;
import com.smarthome.magic.activity.tuya_device.utils.TuyaConfig;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.smarthome.magic.activity.wode_page.bazinew.utils.TimeUtils;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.dialog.ZhiNengFamilyAddDIalog;
import com.tuya.smart.android.device.builder.TuyaTimerBuilder;
import com.tuya.smart.android.device.enums.TimerDeviceTypeEnum;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.IResultCallback;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceDingshiSetActivity extends TuyaBaseDeviceActivity {


    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_chongfu)
    TextView tv_chongfu;
    @BindView(R.id.ll_chongfu)
    LinearLayout ll_chongfu;
    @BindView(R.id.tv_beizhu)
    TextView tv_beizhu;
    @BindView(R.id.ll_beizhu)
    LinearLayout ll_beizhu;
    @BindView(R.id.tv_dps_key)
    TextView tv_dps_key;
    @BindView(R.id.tv_dps_value)
    TextView tv_dps_value;
    @BindView(R.id.ll_dps_kongzhi)
    LinearLayout ll_dps_kongzhi;


    private TimePickerView timePicker;

    private String devId;
    private String category;
    private String dps;
    private String dpsId;
    private Object value;
    private String time;
    private String loops;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DeviceDingshiSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_dingshi_set;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("添加定时");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setTextSize(16);
        tv_rightTitle.setTextColor(Y.getColor(R.color.color_main));
        tv_rightTitle.setText("保存");
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDingshi();
            }
        });

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
        initPeizhi();
//        initHuidiao();
    }


    private void init() {
        devId = TuyaDeviceManager.getDeviceManager().getDeviceBeen().getDevId();
        category = TuyaDeviceManager.getDeviceManager().getDeviceBeen().getProductBean().getCategory();

        time = "00:00";
        loops = "0000000";
    }

    private void initPeizhi() {
        switch (category) {
            case TuyaConfig.CATEGORY_CAMERA:
                dpsId = "119";
                value = true;
                break;
            case TuyaConfig.CATEGORY_SWITCH:
                dpsId = "1";
                value = true;
                break;
        }
    }

    private void addDingshi() {
        Map<String, Object> map = new HashMap<>();
        map.put(dpsId, value);
        DpsTimeModel model = new DpsTimeModel(map, time);
        dps = new Gson().toJson(model);

        String beizhu = tv_beizhu.getText().toString();

        Y.e("设置的dps是多少   " + dps + "    " + loops + "  " + beizhu);
        TuyaTimerBuilder builder = new TuyaTimerBuilder.Builder()
                .taskName("")
                .devId(devId)
                .deviceType(TimerDeviceTypeEnum.DEVICE)
                .actions(dps)
                .loops(loops)
                .aliasName(beizhu)
                .status(1)
                .appPush(false)
                .build();
        TuyaHomeSdk.getTimerInstance().addTimer(builder, new IResultCallback() {
            @Override
            public void onSuccess() {
                Y.e("设置成功了啊");
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Y.t("添加定时失败:" + errorMsg);
            }
        });
    }

    @OnClick({R.id.tv_time, R.id.ll_chongfu, R.id.ll_beizhu, R.id.ll_dps_kongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                clickTime();
                break;
            case R.id.ll_chongfu:
                break;
            case R.id.ll_beizhu:
                clickBeizhu();
                break;
            case R.id.ll_dps_kongzhi:
                break;
        }
    }

    private void clickBeizhu() {
        TuyaInputDialog dialog = new TuyaInputDialog(mContext, new TuyaInputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TuyaInputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TuyaInputDialog dialog) {
                tv_beizhu.setText(dialog.getTextContent());
            }

            @Override
            public void onDismiss(TuyaInputDialog dialog) {

            }
        });
        dialog.setDismissAfterClick(true);
        dialog.setTextTitle("请输入备注");
        dialog.setTextInput(InputType.TYPE_CLASS_TEXT);
        dialog.setTextContent(tv_beizhu.getText().toString());
        dialog.show();
    }

    private void clickTime() {
        boolean[] select = {false, false, false, true, true, false};
        timePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                int hours = date.getHours();
                int minutes = date.getMinutes();
                if (hours < 10) {
                    if (minutes < 10) {
                        time = "0" + hours + ":" + "0" + minutes;
                    } else {
                        time = "0" + hours + ":" + minutes;
                    }
                } else {
                    if (minutes < 10) {
                        time = hours + ":" + "0" + minutes;
                    } else {
                        time = hours + ":" + minutes;
                    }
                }
            }
        })
                .setType(select)// 默认全部显示
                .build();
        timePicker.show();
    }
}

