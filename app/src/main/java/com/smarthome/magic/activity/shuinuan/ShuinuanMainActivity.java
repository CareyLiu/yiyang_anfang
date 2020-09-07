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
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.util.AlertUtil;

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
    private String sn_preset_temperature1;      //预设温度

    float temperature_lattice = 360.0f / 160.0f;

    private AnimationDrawable animationDrawable;

    private boolean isDingcheng = false;

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
        initDialog();
        showDialog("设备连接中...");
        initHandler();
    }

    private int time = 0;

    private void initHandler() {
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    time++;
                    if (TextUtils.isEmpty(sn_state)) {
                        if (time < 10) {
                            initHandler();
                        } else {
                            AlertUtil.t(mContext, "无法连结到服务器");
                            dialog.dismiss();
                        }
                    } else {
                        dialog.dismiss();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

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

    private void init() {
        iv_shuinuan_zhen.setRotation(-123);
        registerKtMqtt();
        initHuidiao();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_SN_DATA) {
                    getDataMoni(message.content.toString());
                } else if (message.type == ConstanceValue.MSG_KT_DATA) {
                    getData(message.content.toString());
                }
            }
        }));
    }

    private void getDataMoni(String msg) {
        if (msg.contains("M_s")) {
            String code = msg.substring(3, msg.length() - 1);





        }
    }

    private void getData(String msg) {
        //接收到信息
        Log.i("水暖加热器啊模拟自己", msg);
        dialog.dismiss();
        String messageData = msg.substring(3, msg.length() - 1);
        //水暖状态
        sn_state = messageData.substring(0, 1);
        //预设温度
        sn_preset_temperature1 = messageData.substring(35, 37);

        //当前出水口温度
        //current_temperature = messageData.substring(27, 30);
        Log.i("水暖加热器啊", sn_state + "    " + sn_preset_temperature1);
        switch (sn_state) {
            case "0":
                //待机中
                btn_heater_close.setBackgroundResource(R.mipmap.car_open);
                break;
            case "1":
                //开机中
                btn_heater_close.setBackgroundResource(R.mipmap.car_open);
                iv_heater_host.setBackgroundResource(R.drawable.plumbing);
                animationDrawable = (AnimationDrawable) iv_heater_host.getBackground();
                animationDrawable.start();
                break;
            case "2":
                //加热中
                btn_heater_close.setBackgroundResource(R.mipmap.car_open);
                iv_heater_host.setBackgroundResource(R.drawable.plumbing);
                animationDrawable = (AnimationDrawable) iv_heater_host.getBackground();
                animationDrawable.start();
                break;
            case "3":
                //关机中
                btn_heater_close.setBackgroundResource(R.mipmap.car_close);
                iv_heater_host.setBackgroundResource(R.drawable.shuinuan_pic_gif_nor);
                iv_shuinuan_zhen.setRotation(-123);
                break;
            case "4":
                //循环水
                btn_heater_close.setBackgroundResource(R.mipmap.car_open);
                iv_heater_host.setBackgroundResource(R.drawable.plumbing);
                animationDrawable = (AnimationDrawable) iv_heater_host.getBackground();
                animationDrawable.start();
                break;
        }
        if (!sn_state.equals("3")) {
            //开机状态显示当前设置问题
            if (Integer.parseInt(sn_preset_temperature1) == 60) {
                tv_shuiwen1.setBackgroundResource(R.mipmap.sheding_button_sel);
                tv_shuiwen2.setBackgroundResource(R.mipmap.sheding_button_nor);
            } else if (Integer.parseInt(sn_preset_temperature1) == 80) {
                tv_shuiwen1.setBackgroundResource(R.mipmap.sheding_button_nor);
                tv_shuiwen2.setBackgroundResource(R.mipmap.sheding_button_sel);
            } else {
                tv_shuiwen1.setBackgroundResource(R.mipmap.sheding_button_nor);
                tv_shuiwen2.setBackgroundResource(R.mipmap.sheding_button_nor);
            }
            //开机状态显示当前出水温度
            float temperature = 0;
            if (sn_preset_temperature1.contains("-")) {
                String[] str = sn_preset_temperature1.split("-");
                String format = new BigDecimal("-" + str[1]).toString();
                temperature = Float.valueOf(format);
            } else {
                String format = new BigDecimal(sn_preset_temperature1).toString();
                temperature = Float.valueOf(format);
            }
            //先把仪表盘指针复位，再重新旋转
            iv_shuinuan_zhen.setRotation(-123);
            float default_angle = iv_shuinuan_zhen.getRotation();
            iv_shuinuan_zhen.setRotation(default_angle + temperature * temperature_lattice);

        } else {
            tv_shuiwen1.setBackgroundResource(R.mipmap.sheding_button_nor);
            tv_shuiwen2.setBackgroundResource(R.mipmap.sheding_button_nor);
        }
    }


    private void registerKtMqtt() {
        //注册向空调发送数据的主题
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Send)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("水暖加热器", "订阅主体-发布成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("水暖加热器", "订阅主体-发布失败");
            }
        });

        //注册空调向app回调数据的主题
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(SN_Accept)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("水暖加热器", "订阅主体-发布成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("水暖加热器", "订阅主体-发布失败");
            }
        });

        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("j_s")
                .setQos(2).setRetained(false)
                .setTopic(SN_Accept), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("水暖加热器", "订阅主体-发布成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("水暖加热器", "订阅主体-发布失败");
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
            state = 3;
        } else {
            state = 1;
        }

        String data = "M_s01" + state + "020080.";
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
    private void sendSwichMoni() {
        if (TextUtils.isEmpty(sn_state)) {
            AlertUtil.t(this, "水暖服务器未连接，请重新连接服务器");
            return;
        }

        int state = Integer.parseInt(sn_state);
        if (state == 1) {
            state = 3;
        } else {
            state = 1;
        }

        String data = "j_s" + state + "45611166666661102640265050070600018002310.";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(SN_Accept), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }


    /**
     * 模拟设定60度
     */
    private void sendTemperature60() {
        if (TextUtils.isEmpty(sn_state)) {
            AlertUtil.t(this, "水暖服务器未连接，请重新连接服务器");
            return;
        }

        if (sn_state.equals("3")) {
            AlertUtil.t(this, "水暖已关机，请打开水暖发送指令");
            return;
        }
        showDialog("发送指令中...");
        sn_preset_temperature1 = "60";
        String data = "j_s" + sn_state + "4561116666666110264026505007060001" + sn_preset_temperature1 + "02310.";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(SN_Accept), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("水暖加热器", "发送60温度-发布成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("水暖加热器", "发送60温度-发布失败");
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

        if (sn_state.equals("3")) {
            AlertUtil.t(this, "水暖已关机，请打开水暖发送指令");
            return;
        }
        showDialog("发送指令中...");
        sn_preset_temperature1 = "80";
        String data = "j_s" + sn_state + "4561116666666110264026505007060001" + sn_preset_temperature1 + "02310.";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(SN_Accept), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("水暖加热器", "发送80温度-发布成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("水暖加热器", "发送80温度-发布失败");
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
}
