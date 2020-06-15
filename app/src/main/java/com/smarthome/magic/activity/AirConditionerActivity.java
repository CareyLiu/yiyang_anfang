package com.smarthome.magic.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.util.AlertUtil;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.math.BigDecimal;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.app.ConfigValue.STARTSHELVES;
import static com.smarthome.magic.config.MyApplication.CAR_NOTIFY;

public class AirConditionerActivity extends BaseActivity implements View.OnClickListener {

    private Context context = AirConditionerActivity.this;
    private TextView tv_connect_device, tv_temperature, tv_device_mode, tv_device_state;
    private TextView tv_hj_temperature, tv_device_fengsu, tv_alert, tv_clean_alert, tv_lamp_on, tv_lamp_off;
    private RelativeLayout rl_device_state, rl_alert, rl_switch;
    private TextView tv_change_mode, tv_change_fengsu;
    private ImageView img_air_conditioner, img_temperature_add, img_temperature_sub, img_switch;

    String KT_Send = "zckt/cbox/hardware/11111111111111111111111";
    String KT_Accept = "zckt/cbox/app/11111111111111111111111";
    private String kt_temperature = "";
    private String kt_lamp = "";
    private String kt_mode = "";
    private String kt_fengsu = "";
    private String kt_alert = "";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_air_conditioner;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightMode(this);
        initToolbar();
        initView();
        registerKtMqtt();
        snedDefaultMqtt();
        getnotice();
    }

    private void initView() {
        tv_connect_device = findViewById(R.id.tv_connect_device);
        tv_temperature = findViewById(R.id.tv_temperature);
        tv_device_mode = findViewById(R.id.tv_device_mode);
        tv_device_state = findViewById(R.id.tv_device_state);
        tv_hj_temperature = findViewById(R.id.tv_hj_temperature);
        tv_device_fengsu = findViewById(R.id.tv_device_fengsu);
        tv_alert = findViewById(R.id.tv_alert);
        tv_clean_alert = findViewById(R.id.tv_clean_alert);
        tv_lamp_on = findViewById(R.id.tv_lamp_on);
        tv_lamp_off = findViewById(R.id.tv_lamp_off);
        rl_device_state = findViewById(R.id.rl_device_state);
        rl_alert = findViewById(R.id.rl_alert);
        rl_switch = findViewById(R.id.rl_switch);
        img_air_conditioner = findViewById(R.id.img_air_conditioner);
        img_temperature_add = findViewById(R.id.img_temperature_add);
        img_temperature_sub = findViewById(R.id.img_temperature_sub);
        tv_change_mode = findViewById(R.id.tv_change_mode);
        tv_change_fengsu = findViewById(R.id.tv_change_fengsu);
        img_switch = findViewById(R.id.img_switch);
        tv_connect_device.setOnClickListener(this);
        rl_device_state.setOnClickListener(this);
        tv_clean_alert.setOnClickListener(this);
        img_temperature_add.setOnClickListener(this);
        img_temperature_sub.setOnClickListener(this);
        tv_lamp_on.setOnClickListener(this);
        tv_lamp_off.setOnClickListener(this);
        tv_change_mode.setOnClickListener(this);
        tv_change_fengsu.setOnClickListener(this);
        rl_switch.setOnClickListener(this);
    }

    private void registerKtMqtt() {
        //注册向空调发送数据的主题
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(KT_Send)
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
                .setTopic(KT_Accept)
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
                .setMsg("M001.")
                .setQos(2).setRetained(false)
                .setTopic(KT_Accept), new IMqttActionListener() {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_connect_device:

                break;
            case R.id.tv_clean_alert:
                //清除故障
                cleanAlert();
                break;
            case R.id.img_temperature_add:
                //温度加
                temperatureAdd();
                break;
            case R.id.img_temperature_sub:
                //温度减
                temperatureSub();
                break;
            case R.id.tv_lamp_on:
                //开灯
                lampOn();
                break;
            case R.id.tv_lamp_off:
                //关灯
                lampOff();
                break;
            case R.id.tv_change_mode:
                //更改模式
                changeMode();
                break;
            case R.id.tv_change_fengsu:
                //更改风速
                changeFengsu();
                break;
            case R.id.rl_switch:
                //开关机
                Ktswitch();
                break;
        }
    }

    /**
     * 获取通知返回的数据
     */
    private void getnotice() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_CAR_J_G) {
                    //接收到信息
                    Log.i("msg_kt_j_", message.content.toString());
                    String messageData = message.content.toString().substring(1, message.content.toString().length());
                    //空调模式
                    String mode = messageData.substring(3, 4);
                    kt_mode = mode;
                    //空调风速
                    String fengsu = messageData.substring(4, 5);
                    kt_fengsu = fengsu;
                    //空调温度
                    String temperature = messageData.substring(5, 7);
                    kt_temperature = temperature;
                    //环境温度
                    String hj_temperature = messageData.substring(15, 19);
                    if (hj_temperature.contains("-")) {
                        hj_temperature = hj_temperature.substring(1, hj_temperature.length());
                    } else {
                        hj_temperature = hj_temperature.substring(2, hj_temperature.length());
                    }
                    //压缩机故障码
                    String compress_code = messageData.substring(23, 25);
                    //面板故障码
                    String panel_code = messageData.substring(25, 27);
                    kt_alert = compress_code + panel_code;
                    //照明状态
                    String lamp = messageData.substring(27, 28);
                    kt_lamp = lamp;
                    switch (mode) {
                        case "0":
                            tv_device_mode.setText("关机状态");
                            rl_device_state.setBackgroundResource(R.drawable.btn_equipment_offline);
                            tv_device_state.setText("设备离线");
                            img_air_conditioner.setImageResource(R.mipmap.img_air_conditioner_close);
                            img_switch.setImageResource(R.mipmap.img_air_conditioner_switch_close);
                            tv_device_fengsu.setText("0");
                            tv_temperature.setText("0");
                            tv_hj_temperature.setText("0℃");
                            return;
                        case "1":
                            tv_device_mode.setText("经济模式");
                            rl_device_state.setBackgroundResource(R.drawable.btn_equipment_online);
                            tv_device_state.setText("设备在线");
                            img_air_conditioner.setImageResource(R.mipmap.img_air_conditioner_open);
                            img_switch.setImageResource(R.mipmap.img_air_conditioner_switch_open);
                            break;
                        case "2":
                            tv_device_mode.setText("标准模式");
                            rl_device_state.setBackgroundResource(R.drawable.btn_equipment_online);
                            img_air_conditioner.setImageResource(R.mipmap.img_air_conditioner_open);
                            img_switch.setImageResource(R.mipmap.img_air_conditioner_switch_open);
                            tv_device_state.setText("设备在线");
                            break;
                        case "3":
                            tv_device_mode.setText("强力模式");
                            rl_device_state.setBackgroundResource(R.drawable.btn_equipment_online);
                            img_air_conditioner.setImageResource(R.mipmap.img_air_conditioner_open);
                            img_switch.setImageResource(R.mipmap.img_air_conditioner_switch_open);
                            tv_device_state.setText("设备在线");
                            break;
                        case "4":
                            tv_device_mode.setText("固定模式");
                            rl_device_state.setBackgroundResource(R.drawable.btn_equipment_online);
                            img_air_conditioner.setImageResource(R.mipmap.img_air_conditioner_open);
                            img_switch.setImageResource(R.mipmap.img_air_conditioner_switch_open);
                            tv_device_state.setText("设备在线");
                            break;
                    }
                    tv_device_fengsu.setText(fengsu);
                    tv_temperature.setText(temperature);
                    tv_hj_temperature.setText(hj_temperature + "℃");
                    if (compress_code.equals("00") && panel_code.equals("00")) {
                        rl_alert.setVisibility(View.GONE);
                    } else {
                        //压缩机故障
                        if (!compress_code.equals("00")) {
                            rl_alert.setVisibility(View.VISIBLE);
                            tv_alert.setText("压缩机报警");
                        }
                        if (!panel_code.equals("00")) {
                            rl_alert.setVisibility(View.VISIBLE);
                            tv_alert.setText("控制面板报警");
                        }
                    }
                    if (lamp.equals("0")) {
                        //灯关闭
                        tv_lamp_on.setTextColor(Color.rgb(153, 153, 153));
                        tv_lamp_off.setTextColor(Color.rgb(6, 239, 1));
                    } else {
                        //灯开启
                        tv_lamp_on.setTextColor(Color.rgb(6, 239, 1));
                        tv_lamp_off.setTextColor(Color.rgb(153, 153, 153));
                    }
                }
            }
        }));
    }

    /**
     * 清除故障
     */
    private void cleanAlert() {
        if (kt_mode.equals("0")) {
            Toast.makeText(context, "空调已关机，请打开空调发送指令", Toast.LENGTH_SHORT).show();
            return;
        }
        String alert = "0000";
        String data = "j_g001" + kt_mode + kt_fengsu + kt_temperature + "122015500-26-025" + alert + kt_lamp + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(KT_Accept), new IMqttActionListener() {
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

    /**
     * 温度加
     */
    private void temperatureAdd() {
        if (kt_mode.equals("0")) {
            Toast.makeText(context, "空调已关机，请打开空调发送指令", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Integer.parseInt(kt_temperature) == 35) {
            Toast.makeText(context, "空调温度最高为35度", Toast.LENGTH_SHORT).show();
            return;
        }
        String temperature = String.valueOf(Integer.parseInt(kt_temperature) + 1);
        if (temperature.length() == 1) {
            temperature = "0" + temperature;
        }
        String data = "j_g001" + kt_mode + kt_fengsu + temperature + "122015500-26-025" + kt_alert + kt_lamp + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(KT_Accept), new IMqttActionListener() {
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

    /**
     * 温度减
     */
    private void temperatureSub() {
        if (kt_mode.equals("0")) {
            Toast.makeText(context, "空调已关机，请打开空调发送指令", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Integer.parseInt(kt_temperature) == 5) {
            Toast.makeText(context, "空调温度最低为5度", Toast.LENGTH_SHORT).show();
            return;
        }
        String temperature = String.valueOf(Integer.parseInt(kt_temperature) - 1);
        if (temperature.length() == 1) {
            temperature = "0" + temperature;
        }
        String data = "j_g001" + kt_mode + kt_fengsu + temperature + "122015500-26-025" + kt_alert + kt_lamp + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(KT_Accept), new IMqttActionListener() {
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

    /**
     * 开灯
     */
    private void lampOn() {
        if (kt_mode.equals("0")) {
            Toast.makeText(context, "空调已关机，请打开空调发送指令", Toast.LENGTH_SHORT).show();
            return;
        }
        String lamp = "1";
        if (kt_lamp.equals("1")) {
            Toast.makeText(context, "照明灯已打开", Toast.LENGTH_SHORT).show();
            return;
        }
        String data = "j_g001" + kt_mode + kt_fengsu + kt_temperature + "122015500-26-025" + kt_alert + lamp + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(KT_Accept), new IMqttActionListener() {
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

    /**
     * 关灯
     */
    private void lampOff() {
        if (kt_mode.equals("0")) {
            Toast.makeText(context, "空调已关机，请打开空调发送指令", Toast.LENGTH_SHORT).show();
            return;
        }
        String lamp = "0";
        if (kt_lamp.equals("0")) {
            Toast.makeText(context, "照明灯已关闭", Toast.LENGTH_SHORT).show();
            return;
        }
        String data = "j_g001" + kt_mode + kt_fengsu + kt_temperature + "122015500-26-025" + kt_alert + lamp + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(KT_Accept), new IMqttActionListener() {
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

    /**
     * 更改模式
     *
     * @return
     */
    private void changeMode() {
        if (kt_mode.equals("0")) {
            Toast.makeText(context, "空调已关机，请打开空调发送指令", Toast.LENGTH_SHORT).show();
            return;
        }
        int mode = Integer.parseInt(kt_mode);
        if (mode == 4) {
            mode = 1;
        } else {
            mode += 1;
        }
        String data = "j_g001" + mode + kt_fengsu + kt_temperature + "122015500-26-025" + kt_alert + kt_lamp + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(KT_Accept), new IMqttActionListener() {
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

    /**
     * 更改风速
     */
    private void changeFengsu() {
        if (kt_mode.equals("0")) {
            Toast.makeText(context, "空调已关机，请打开空调发送指令", Toast.LENGTH_SHORT).show();
            return;
        }
        int fengsu = Integer.parseInt(kt_fengsu);
        if (fengsu == 5) {
            fengsu = 1;
        } else {
            fengsu += 1;
        }
        String data = "j_g001" + kt_mode + fengsu + kt_temperature + "122015500-26-025" + kt_alert + kt_lamp + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(KT_Accept), new IMqttActionListener() {
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

    /**
     * 空调开关
     *
     * @return
     */
    private void Ktswitch() {
        int mode = Integer.parseInt(kt_mode);
        if (mode > 0) {
            mode = 0;
        } else {
            mode = 1;
        }
        String data = "j_g001" + mode + kt_fengsu + kt_temperature + "122015500-26-025" + kt_alert + kt_lamp + ".";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
                .setQos(2).setRetained(false)
                .setTopic(KT_Accept), new IMqttActionListener() {
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

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("驻车空调");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }
}
