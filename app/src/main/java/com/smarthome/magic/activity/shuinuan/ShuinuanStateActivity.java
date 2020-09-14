package com.smarthome.magic.activity.shuinuan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ShuinuanStateActivity extends ShuinuanBaseActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.tv_jiareji_state)
    TextView tv_jiareji_state;
    @BindView(R.id.tv_dangqianwendu)
    TextView tv_dangqianwendu;
    @BindView(R.id.tv_yushe_wendu)
    TextView tv_yushe_wendu;
    @BindView(R.id.tv_dianya)
    TextView tv_dianya;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_shebei_state)
    TextView tv_shebei_state;

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_shebei;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShuinuanStateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initHuidiao();
        getNs();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_SN_DATA) {
                    String msg = message.content.toString();
                    getData(msg);
                }
            }
        }));
    }

    private void getData(String msg) {
        Log.i("水暖加热器返回的数据是", msg);
        if (msg.contains("j_s")) {
            dialog.dismiss();

            String sn_state = msg.substring(3, 4);//水暖状态
            String syscTime = msg.substring(4, 7);//加热剩余时长
            String shuibeng_state = msg.substring(7, 8);//水泵状态  1.工作中2.待机中
            String youbeng_state = msg.substring(8, 9);//油泵状态  1.工作中2.待机中
            String fengji_state = msg.substring(9, 10);//风机状态  1.工作中2.待机中
            String dianyan = (getInt(msg.substring(10, 14)) / 10.0f) + "";//电压  0253 = 25.3
            String fengjizhuansu = msg.substring(14, 19);//风机转速   13245
            String jairesaigonglv = (getInt(msg.substring(19, 23)) / 10.0f) + "";// 加热塞功率  0264=26.4
            String youbenggonglv = (getInt(msg.substring(23, 27)) / 10.0f) + "";// 油泵频率  0264=26.4
            String rushukowendu = (getInt(msg.substring(27, 30)) - 50) + "";// 入水口温度（℃）  -50至150（000 = -50，100 = 50）
            String chushuikowendu = (getInt(msg.substring(30, 33)) - 50) + "";// 出水口温度（℃）  -50至150（000 = -50，100 = 50）
            String weiqiwendu = (getInt(msg.substring(33, 37)) - 50) + "";// 尾气温度（℃）  -50至2000（000 = -50，100 = 50）
            String danqiandangwei = msg.substring(37, 38);// 1.一档2.二档（注：用*占位）
            String yushewendu = msg.substring(38, 40);//预设温度（℃） 预设温度（℃）
            String zongTime = getInt(msg.substring(40, 45)) + "";//总时长 （小时）

            switch (sn_state) {
                case "1"://开机中
                case "2"://加热中
                case "4"://循环水
                    tv_jiareji_state.setText("开机");
                    break;
                case "0"://待机中
                case "3"://关机中
                    tv_jiareji_state.setText("关机");
                    break;
            }
            tv_dangqianwendu.setText(chushuikowendu + "℃");
            tv_yushe_wendu.setText(yushewendu + "℃");
            tv_dianya.setText(dianyan + "V");
            tv_time.setText(zongTime + "H");
            tv_shebei_state.setText("在线");
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    private void getNs() {
        showDialog("连接中...");
        //向水暖加热器发送获取实时数据
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("N_s.")
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("app端向水暖加热器请求实时数据", "");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }
}
