package com.smarthome.magic.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.smarthome.magic.config.MyApplication.CAR_NOTIFY;

public class ShuinuanMainActivity extends BaseActivity {

    @BindView(R.id.iv_heater_host)
    RelativeLayout iv_heater_host;
    @BindView(R.id.tv_lianjie_ccid)
    TextView tv_lianjie_ccid;
    @BindView(R.id.tv_shuiwen_set)
    TextView tv_shuiwen_set;
    @BindView(R.id.tv_shuiwen1)
    TextView tv_shuiwen1;
    @BindView(R.id.tv_shuiwen2)
    TextView tv_shuiwen2;
    @BindView(R.id.tv_shuiwen_biao)
    TextView tv_shuiwen_biao;
    @BindView(R.id.iv_shuinuan_ceng)
    ImageView iv_shuinuan_ceng;
    @BindView(R.id.iv_shuinuan_zhen)
    ImageView iv_shuinuan_zhen;
    @BindView(R.id.btn_heater_close)
    ImageView btn_heater_close;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.rl_set)
    RelativeLayout rl_set;

    String SN_Send = "wh/hardware/11111111111111111111111";
    String SN_Accept = "wh/app/11111111111111111111111";

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_new;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShuinuanMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        iv_shuinuan_zhen.setRotation(-123);

        registerKtMqtt();
        snedDefaultMqtt();
    }

    private void registerKtMqtt() {
        //注册向空调发送数据的主题
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Send)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "notify:  " + CAR_NOTIFY);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });
        //注册空调向app回调数据的主题
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Accept)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "notify:  " + CAR_NOTIFY);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });
    }

    /**
     * 界面打开时向空调发送查询实时数据指令
     */
    private void snedDefaultMqtt() {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("j_s")
                .setQos(2).setRetained(false)
                .setTopic(SN_Accept), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(KT_Accept)-onSuccess:-&gt;发布成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
            }
        });
    }

    @OnClick({R.id.iv_heater_host, R.id.tv_lianjie_ccid, R.id.tv_shuiwen1, R.id.tv_shuiwen2, R.id.btn_heater_close, R.id.rl_back, R.id.rl_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_heater_host:
                break;
            case R.id.tv_lianjie_ccid:
                break;
            case R.id.tv_shuiwen1:
                break;
            case R.id.tv_shuiwen2:
                break;
            case R.id.btn_heater_close:
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_set:
                ShuinuanSetActivity.actionStart(mContext);
                break;
        }
    }
}
