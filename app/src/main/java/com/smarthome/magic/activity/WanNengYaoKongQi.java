package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.mqtt_zhiling.ZnjjMqttMingLing;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class WanNengYaoKongQi extends BaseActivity {

    @BindView(R.id.ll_dianyuan)
    LinearLayout llDianyuan;
    @BindView(R.id.ll_yinliang_add)
    LinearLayout llYinliangAdd;
    @BindView(R.id.ll_yinliang_jian)
    LinearLayout llYinliangJian;
    @BindView(R.id.ll_pindao_add)
    LinearLayout llPindaoAdd;
    @BindView(R.id.ll_pindao_jian)
    LinearLayout llPindaoJian;
    @BindView(R.id.ll_caidan)
    LinearLayout llCaidan;
    @BindView(R.id.ll_shuchu)
    LinearLayout llShuchu;
    @BindView(R.id.bt_up)
    View btUp;
    @BindView(R.id.bt_down)
    View btDown;
    @BindView(R.id.bt_left)
    View btLeft;
    @BindView(R.id.bt_right)
    View btRight;
    @BindView(R.id.bt_ok)
    View btOk;
    @BindView(R.id.ll_jingyin)
    LinearLayout llJingyin;

    private String zhuangZhiId;
    private String ccid;
    private String serverId;
    private ZnjjMqttMingLing znjjMqttMingLing;
    private String topic;

    @Override
    public int getContentViewResId() {
        return R.layout.layout_wanneng_yaokongqi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("电视遥控器");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setTextSize(17);
        tv_rightTitle.setTextColor(Y.getColor(R.color.color_main));
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set();
            }
        });

        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void set() {
        

    }

    public static void actionStart(Context context, String device_ccid) {
        Intent intent = new Intent(context, WanNengYaoKongQi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMqtt();
        initHuidiao();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {

            }
        }));
    }

    private void initMqtt() {
        zhuangZhiId = getIntent().getStringExtra("device_ccid");
        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
        topic = "zn/hardware/" + serverId + ccid;
        znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(ccid, serverId, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        znjjMqttMingLing.subscribeServerShiShiXinXi_WithCanShu(ccid, serverId, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }


    private void sendMsg(String code) {
        String mingLingMa = "M18" + zhuangZhiId + code + ".";
        Y.e("发送的命令是什么啊啊啊  " + mingLingMa);
        znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    @OnClick({R.id.ll_jingyin, R.id.ll_dianyuan, R.id.ll_yinliang_add, R.id.ll_yinliang_jian, R.id.ll_pindao_add, R.id.ll_pindao_jian, R.id.ll_caidan, R.id.ll_shuchu, R.id.bt_up, R.id.bt_down, R.id.bt_left, R.id.bt_right, R.id.bt_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_dianyuan:
                sendMsg("01");
                break;
            case R.id.ll_yinliang_add:
                sendMsg("02");
                break;
            case R.id.ll_yinliang_jian:
                sendMsg("03");
                break;
            case R.id.ll_pindao_add:
                sendMsg("04");
                break;
            case R.id.ll_pindao_jian:
                sendMsg("05");
                break;
            case R.id.ll_caidan:
                sendMsg("06");
                break;
            case R.id.ll_shuchu:
                sendMsg("07");
                break;
            case R.id.ll_jingyin:
                sendMsg("08");
                break;
            case R.id.bt_up:
                sendMsg("09");
                break;
            case R.id.bt_down:
                sendMsg("10");
                break;
            case R.id.bt_left:
                sendMsg("11");
                break;
            case R.id.bt_right:
                sendMsg("12");
                break;
            case R.id.bt_ok:
                sendMsg("13");
                break;
        }
    }
}
