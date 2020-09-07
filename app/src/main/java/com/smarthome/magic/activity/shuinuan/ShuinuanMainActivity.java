package com.smarthome.magic.activity.shuinuan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.smarthome.magic.R;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.DoMqttValue;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

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

    public static final String SN_Send = "wh/hardware/11111111111111111111111";
    public static final String SN_Accept = "wh/app/11111111111111111111111";

    private String sn_state;     //水暖状态
    private String yushewendu;      //预设温度

    float wenduxishu = 360.0f / 160.0f;

    private AnimationDrawable animationDrawable;

    private boolean isDingcheng = false;
    private String dataMsg = "j_s312022202531234502640265100100010018002310.";

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
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.transparent)
                .statusBarDarkFont(false)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
                .statusBarColor(R.color.transparent)     //状态栏颜色，不写默认透明色
                .navigationBarColor(R.color.white) //导航栏颜色，不写默认黑色
                .barColor(R.color.transparent).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.SHUINUAN);
        iv_shuinuan_zhen.setRotation(-123);
        registerKtMqtt();
        initHuidiao();
        initDialog();
        showDialog("设备连接中...");
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
            sn_state = msg.substring(3, 4);//水暖状态
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
            yushewendu = msg.substring(38, 40);//预设温度（℃） 预设温度（℃）
            String zongTime = msg.substring(40, 45);//总时长 （小时）

            switch (sn_state) {
                case "0"://待机中
                case "1"://开机中
                case "2"://加热中
                case "4"://循环水
                    btn_heater_close.setBackgroundResource(R.mipmap.car_open);
                    iv_heater_host.setBackgroundResource(R.drawable.plumbing);
                    animationDrawable = (AnimationDrawable) iv_heater_host.getBackground();
                    animationDrawable.start();
                    break;
                case "3"://关机中
                    btn_heater_close.setBackgroundResource(R.mipmap.car_close);
                    iv_heater_host.setBackgroundResource(R.drawable.shuinuan_pic_gif_nor);
                    iv_shuinuan_zhen.setRotation(-123);
                    break;
            }

            if (!sn_state.equals("3")) {//开机状态显示当前设置问题
                if (yushewendu.equals("60")) {
                    tv_shuiwen1.setBackgroundResource(R.mipmap.sheding_button_sel);
                    tv_shuiwen2.setBackgroundResource(R.mipmap.sheding_button_nor);
                } else if (yushewendu.equals("80")) {
                    tv_shuiwen1.setBackgroundResource(R.mipmap.sheding_button_nor);
                    tv_shuiwen2.setBackgroundResource(R.mipmap.sheding_button_sel);
                } else {
                    tv_shuiwen1.setBackgroundResource(R.mipmap.sheding_button_nor);
                    tv_shuiwen2.setBackgroundResource(R.mipmap.sheding_button_nor);
                }

                //开机状态显示当前出水温度
                iv_shuinuan_zhen.setRotation(-123);
                String format = new BigDecimal(yushewendu).toString();
                float wenduNow = Float.valueOf(format);
                float zhizhen = iv_shuinuan_zhen.getRotation();
                iv_shuinuan_zhen.setRotation(zhizhen + wenduNow * wenduxishu);
            } else {
                tv_shuiwen1.setBackgroundResource(R.mipmap.sheding_button_nor);
                tv_shuiwen2.setBackgroundResource(R.mipmap.sheding_button_nor);
            }
        } else if (msg.contains("k_s")) {
            String code = msg.substring(3, 5);
            if (code.equals("01")) {
                String zhuantai = msg.substring(5, 6);
                if (zhuantai.equals("1")) {
//                    sendSwichMoni(zhuantai);
                    Log.i("操作成功", "");
                } else {
                    Log.i("操作失败", "");
                }
            } else {

            }
        } else if (msg.contains("M_s")) {
            String code = msg.substring(3, 5);
            if (code.equals("01")) {
                String zhuantai = msg.substring(5, 6);
                sendSwichMoni(zhuantai);
            } else if (code.equals("02")) {

            }
        } else if (msg.contains("N_s")) {
            sendJs();
        }
    }

    private void getDataMoni(String msg) {
        Log.i("发送的获取模拟数据", msg);

    }

    private void registerKtMqtt() {
        //注册水暖加热器订阅
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Send)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        //模拟水暖加热器订阅app
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Accept)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        getNs();
    }

    private void getNs() {
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

    private void sendJs() {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(dataMsg)
                .setQos(2).setRetained(false)
                .setTopic(SN_Accept), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("水暖加热器把实时数据发给了app端", "");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

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
                sendTemperature60();
                break;
            case R.id.tv_shuiwen2:
                sendTemperature80();
                break;
            case R.id.btn_heater_close:
                sendSwich();
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_set:
                ShuinuanSetActivity.actionStart(mContext);
                break;
        }
    }


    /**
     * 水暖加热器开关
     */
    private void sendSwich() {
        if (TextUtils.isEmpty(sn_state)) {
            AlertUtil.t(this, "水暖服务器未连接，请重新连接服务器");
            return;
        }

        showDialog("发送指令中...");

        int state = Integer.parseInt(sn_state);
        if (state == 1) {
            state = 2;
        } else {
            state = 1;
        }

        String data = "M_s01" + state + "0200" + yushewendu + ".";
        Log.i("发送的数据是多少", data);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }


    /**
     * 模拟水暖加热器开关
     */
    private void sendSwichMoni(String zhuantai) {
        if (zhuantai.equals("1")) {
            dataMsg = "j_s11202220253123450264026510010001001" + yushewendu + "02310.";
        } else {
            dataMsg = "j_s31202220253123450264026510010001001" + yushewendu + "02310.";
        }

        getNs();
    }


    /**
     * 模拟设定60度
     */
    private void sendTemperature60() {
        if (TextUtils.isEmpty(sn_state)) {
            AlertUtil.t(this, "水暖服务器未连接，请重新连接服务器");
            return;
        }

        if (!sn_state.equals("3")) {
            return;
        }

        showDialog("发送指令中...");

        int state = Integer.parseInt(sn_state);
        if (state == 1) {
            state = 2;
        } else {
            state = 1;
        }

        yushewendu = "60";
        String data = "M_s01" + state + "0200" + yushewendu + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    /**
     * 模拟设定80度
     */
    private void sendTemperature80() {
        if (TextUtils.isEmpty(sn_state)) {
            AlertUtil.t(this, "水暖服务器未连接，请重新连接服务器");
            return;
        }

        if (!sn_state.equals("3")) {
            return;
        }

        showDialog("发送指令中...");

        int state = Integer.parseInt(sn_state);
        if (state == 1) {
            state = 2;
        } else {
            state = 1;
        }

        yushewendu = "80";
        String data = "M_s01" + state + "0200" + yushewendu + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    private ProgressDialog dialog;

    private void initDialog() {
        dialog = new ProgressDialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

    }

    private void showDialog(String msg) {
        dialog.setMessage(msg);
        dialog.show();
    }

    private int getInt(String content) {
        try {
            return Integer.parseInt(content);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
