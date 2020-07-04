package com.smarthome.magic.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.RequiresApi;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.rairmmd.andmqtt.MqttUnSubscribe;
import com.smarthome.magic.R;
import com.smarthome.magic.app.AppManager;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConfigValue;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.MyApplication;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.CarDetails;
import com.smarthome.magic.model.HostModel;
import com.smarthome.magic.model.SerializableMap;
import com.smarthome.magic.service.HeaterMqttService;
import com.smarthome.magic.service.WitMqttFormatService;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.ConstantUtil;
import com.smarthome.magic.util.DialogManager;
import com.smarthome.magic.view.ArcProgressBar;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.app.ConfigValue.STARTSHELVES;
import static com.smarthome.magic.app.ConstanceValue.MSG_MQTT_CONNECTARRIVE;
import static com.smarthome.magic.app.ConstanceValue.MSG_MQTT_CONNECTCOMPLETE;
import static com.smarthome.magic.app.ConstanceValue.MSG_MQTT_CONNECTLOST;
import static com.smarthome.magic.app.ConstanceValue.MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE;
import static com.smarthome.magic.app.ConstanceValue.MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS;
import static com.smarthome.magic.config.MyApplication.CARBOX_GETNOW;
import static com.smarthome.magic.config.MyApplication.CAR_CTROL;
import static com.smarthome.magic.config.MyApplication.CAR_NOTIFY;


public class WindHeaterActivity extends BaseActivity implements View.OnLongClickListener, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "WindHeaterActivity";
    public Handler mHandler;
    public static Handler stateHandler;
    @BindView(R.id.iv_heater_host)
    RelativeLayout ivHeaterHost;
    @BindView(R.id.iv_heater_impeller)
    ImageView ivHeaterImpeller;
    @BindView(R.id.iv_heater_fire)
    ImageView ivHeaterFire;

    @BindView(R.id.arcProgressBar)
    ArcProgressBar arcProgressBar;
    @BindView(R.id.btn_heater_close)
    Button btnHeaterClose;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.tv_wd)
    TextView mTvWd;
    private int progressValue;
    DrawerLayout drawer;
    WitMqttFormatService witMqttFormatService;
    ValueAnimator valueAnimator;
    //当前档位
    int gear = 0;
    //默认档位模式
    int openMode = 1;
    ImageView ivBrandPic;
    TextView tvBrandName, tvCarNumber;
    List<RadioButton> btns;
    @BindView(R.id.rb_heater_air_mode)
    RadioButton rbHeaterAirMode;
    @BindView(R.id.rb_heater_gear_mode)
    RadioButton rbHeaterGearMode;
    @BindView(R.id.rb_heater_pump_mode)
    RadioButton rbHeaterPumpMode;
    @BindView(R.id.rb_heater_yby_mode)
    RadioButton rbHeaterYbyMode;
    @BindView(R.id.rb_heater_ytf_mode)
    RadioButton rbHeaterYtfMode;
    @BindView(R.id.rg_magnet)
    RadioGroup rgMagnet;
    @BindView(R.id.tv_yushe_wendu)
    TextView tvYuShe_WenDu;
    RadioButton button;
    public String car_server_id;
    public String ccid;
    public String of_user_id;
    Toolbar toolbar;
    String version;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_wind_heater;
    }

    ProgressDialog waitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        waitDialog = ProgressDialog.show(mContext, null, "网络状态不稳定,连接中···", true, true);


        initView();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        toolbar.setNavigationIcon(R.drawable.back_icon);
        @SuppressLint("ResourceType") ColorStateList csl = getResources().getColorStateList(R.drawable.menu_item_color);
        navigationView.setItemTextColor(csl);
        ivBrandPic = navigationView.getHeaderView(0).findViewById(R.id.iv_brand_pic);
        tvBrandName = navigationView.getHeaderView(0).findViewById(R.id.tv_brand_name);
        tvCarNumber = navigationView.getHeaderView(0).findViewById(R.id.tv_car_number);
        mToolbarTitle.setText("风暖加热器");
        ivHeaterFire.setVisibility(View.GONE);
        ivHeaterImpeller.setVisibility(View.GONE);
        initHandler();
        arcProgressBar.setHandler(mHandler);
        btnHeaterClose.setOnLongClickListener(this);
        rbHeaterAirMode.setOnLongClickListener(this);
        rbHeaterGearMode.setOnLongClickListener(this);
        rbHeaterPumpMode.setOnLongClickListener(this);
        rbHeaterYbyMode.setOnLongClickListener(this);
        rbHeaterYtfMode.setOnLongClickListener(this);
        witMqttFormatService = new WitMqttFormatService();
        car_server_id = PreferenceHelper.getInstance(mContext).getString("car_server_id", "");
        ccid = PreferenceHelper.getInstance(mContext).getString("ccid", "");
        of_user_id = PreferenceHelper.getInstance(mContext).getString("of_user_id", "");
        setMqttZhiLing();

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_CAR_J_G) {
                    showLoadSuccess();
                    //接收到信息
                    Log.i("msg_car_j_", message.content.toString());
                    String messageData = message.content.toString().substring(1, message.content.toString().length() - 1);
                    if (0 <= messageData.substring(0, 6).indexOf("a")) {
                        // 纬度x 045.666666=045666666 9
                        BigDecimal dec = new BigDecimal(messageData.substring(0, 3) + "." + messageData.substring(3, 9));
                        String gps_x = dec.toString();
                        // 经度y 126.666666=126666666 9
                        dec = new BigDecimal(messageData.substring(9, 12) + "." + messageData.substring(12, 18));
                        String gps_y = dec.toString();
                        // 车头朝向方向:1东2南3西4北5西北 6西南7东南 8东北
                        String car_orientations = messageData.substring(18, 19);
                        if (!"a".equals(car_orientations)) {

                            if (car_orientations.equals("1")) {

                            } else if (car_orientations.equals("2")) {

                            } else if (car_orientations.equals("3")) {

                            } else if (car_orientations.equals("4")) {

                            } else if (car_orientations.equals("5")) {

                            } else if (car_orientations.equals("6")) {

                            } else if (car_orientations.equals("7")) {

                            } else if (car_orientations.equals("8")) {

                            }
                        }
                    }
                } else if (message.type == ConstanceValue.MSG_CAR_J_M) {
                    showLoadSuccess();
                    //接收到信息
                    Log.i("msg_car_j_m", message.content.toString());

                    String messageData = message.content.toString().substring(1, message.content.toString().length() - 1);
                    Log.i("msg_car_j_m_data", messageData);
                    // 驻车加热器:当前档位1至5档	1	是
                    String oper_dang = messageData.substring(0, 1);
                    if (0 <= oper_dang.indexOf("a")) {
                        oper_dang = "";
                    }
                    // 预设温度1至32度	2	是
                    String oper_wendu_now = messageData.substring(1, 3);
                    oper_wendu_now = 0 <= oper_wendu_now.indexOf("a") ? "" : new BigDecimal(oper_wendu_now).toString();

                    // 驻车预热器:1.档位开机2.空调开机3.风暖关机4.水暖:开机5.水暖:关机	1	是
                    String oper_open_close = messageData.substring(3, 4);
                    /**
                     * 驻车预热器:1.档位开机2.空调开机3.关机 4.水泵开机9.关机中
                     * 6.预泵油7.预通风
                     */
                    switch (oper_open_close) {
                        case "1":
                            tvYuShe_WenDu.setText("当前的挡位为：" + oper_dang + "挡");
                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "1");
                            openMode(Integer.parseInt(oper_dang));
                            switchModel(rbHeaterGearMode, 1);
                            button = rbHeaterGearMode;
                            //档位开机
//                            if (oper_dang.equals("1")) {
//                                // handleProgressMsg(message1);
//                                openMode(Integer.parseInt(oper_dang));
//                            } else if (oper_dang.equals("2")) {
//                                // handleProgressMsg(message1);
//                                openMode(Integer.parseInt(oper_dang));
//                            } else if (oper_dang.equals("3")) {
//                                //handleProgressMsg(message1);
//                                openMode(Integer.parseInt(oper_dang));
//                            } else if (oper_dang.equals("4")) {
//
//                                //handleProgressMsg(message1);
//                                openMode(Integer.parseInt(oper_dang));
//                            } else if (oper_dang.equals("5")) {
//                                //  handleProgressMsg(message1);
//                                openMode(Integer.parseInt(oper_dang));
//                            }

                            if (oper_dang != null) {
                                openMode(Integer.parseInt(oper_dang));
                            }

                            break;
                        case "2":
                            showLoadSuccess();
                            if (oper_wendu_now != null) {
                                tvYuShe_WenDu.setText("当前的预设温度为：" + oper_wendu_now + "℃");
                            }

                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "2");
                            if (null != oper_wendu_now) {
                                openKongTiaoMode(Integer.parseInt(oper_wendu_now));
                            } else {
                                openMode(0);
                            }

                            switchModel(rbHeaterAirMode, 2);
                            button = rbHeaterAirMode;
                            break;
                        case "3":
                            showLoadSuccess();
                            closeMode(0);
                            AlertUtil.t(WindHeaterActivity.this, "当前设备已关机");
                            tvYuShe_WenDu.setText("长按选择开机模式");
                            break;
                        case "4"://水泵开机
                            showLoadSuccess();
                            boolean flag = true;
                            if (version != null) {
                                if (version.equals("2019")) {
                                    flag = false;

                                }
                            }

                            if (flag) {
                                tvYuShe_WenDu.setText("当前的挡位为：" + oper_dang + "挡");
                                PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "4");
                                button = rbHeaterPumpMode;

                                switchModel(rbHeaterPumpMode, 4);
                                //档位开机
                                if (oper_dang != null) {
                                    openMode(Integer.parseInt(oper_dang));
                                }
                            } else {
                                UIHelper.ToastMessage(WindHeaterActivity.this, "2019款机型不支持水泵模式", Toast.LENGTH_SHORT);
                            }


                            break;
                        case "6":
                            showLoadSuccess();
                            tvYuShe_WenDu.setText("当前的挡位为：" + oper_dang + "挡");
                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "6");
                            button = rbHeaterYbyMode;
                            switchModel(rbHeaterYbyMode, 6);
                            //档位开机
                            if (oper_dang != null) {
                                openMode(Integer.parseInt(oper_dang));
                            }

                            break;
                        case "7":
                            showLoadSuccess();
                            tvYuShe_WenDu.setText("当前的挡位为：" + oper_dang + "挡");
                            button = rbHeaterYtfMode;
                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "7");

                            switchModel(rbHeaterYtfMode, 7);
                            //档位开机
                            if (oper_dang != null) {
                                openMode(Integer.parseInt(oper_dang));
                            }

                            break;
                        case "9":
                            showLoadSuccess();
                            AlertUtil.t(WindHeaterActivity.this, "正在关机，请稍候");
                            break;
                    }
                    // 驻车加热器:当前温度	3	是
                    String oper_wendu = messageData.substring(4, 6);
                    mTvWd.setText(oper_wendu);

                } else if (message.type == ConstanceValue.MSG_CAR_K) {
                    showLoadSuccess();
                } else if (message.type == ConstanceValue.MSG_CAR_I) {
                    showLoadSuccess();
                    //接收到信息
                    Log.i("msg_car_i", message.content.toString());

                    String message1 = message.content.toString();
//                    ctsl = message1.substring(message1.indexOf("i") + 1, message1.indexOf("i") + 2);
//                    jrs = message1.substring(message1.indexOf("i") + 2, message1.indexOf("i") + 3);
//                    jqgl = message1.substring(message1.indexOf("i") + 3, message1.indexOf("i") + 4);
//                    dfdy = message1.substring(message1.indexOf("i") + 4, message1.indexOf("i") + 5);
//                    cgq = message1.substring(message1.indexOf("i") + 5, message1.indexOf("i") + 6);
//                    dy = message1.substring(message1.indexOf("i") + 6, message1.indexOf("i") + 7);
                    version = message1.substring(message1.indexOf("i") + 7, message1.indexOf("i") + 11);
                    //  Log.i("hostCanShu:", "磁铁数量:" + ctsl + " | 加热塞:" + jrs + " | 机器功率：" + jqgl + " | 大风大油：" + dfdy + " | 传感器:" + cgq + " | 电压:" + dy + " | 版本号：" + version);

                    if (version != null) {
                        if (version.equals("2019")) {


                        }
                    }

                    /**
                     *     int MSG_MQTT_CONNECTCOMPLETE = 0x10045;//连接完成
                     *     int MSG_MQTT_CONNECTLOST = 0x10046;//连接丢失
                     *     int MSG_MQTT_CONNECTARRIVE = 0x10047;//收到连接
                     *     int MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS = 0x10048;//重连成功
                     *     int MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE = 0x10049;//重连失败
                     */
                } else if (message.type == MSG_MQTT_CONNECTCOMPLETE) {
                    showLoadSuccess();
                    if (waitDialog.isShowing()) {
                        waitDialog.dismiss();
                        setMqttZhiLing();
                    }
                    Log.i("rair", "complete");

                } else if (message.type == MSG_MQTT_CONNECTLOST) {
                    showLoadSuccess();
                    if (!waitDialog.isShowing()) {
                        waitDialog.show();
                    }
                    Log.i("rair", "complete_lost");
                } else if (message.type == MSG_MQTT_CONNECTARRIVE) {
                    showLoadSuccess();
                    if (waitDialog.isShowing()) {
                        waitDialog.dismiss();
                    }
                    Log.i("rair", "arrive");
                } else if (message.type == MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS) {
                    showLoadSuccess();
                    if (waitDialog.isShowing()) {
                        waitDialog.dismiss();
                    }

                    Log.i("rair", "chonglian_success");

                } else if (message.type == MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE) {
                    showLoadSuccess();
                    if (!waitDialog.isShowing()) {
                        waitDialog.show();
                    }
                    Log.i("rair", "chonglian_failer");
                }
            }
        }));
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).titleBar(toolbar).init();
    }

    public void initView() {
        btns = new ArrayList<>();
        btns.add(rbHeaterAirMode);
        btns.add(rbHeaterGearMode);
        btns.add(rbHeaterPumpMode);
        btns.add(rbHeaterYbyMode);
        btns.add(rbHeaterYtfMode);
        for (int i = 0; i < btns.size(); i++) {
            btns.get(i).setOnLongClickListener(this);
        }
    }

    private int gearToValue(int gear) {

        switch (gear) {
            case 1:
                if (progressValue > 20 || progressValue < 5)
                    progressValue = 10;
                break;
            case 2:
                if (progressValue < 20 || progressValue > 40)
                    progressValue = 30;
                break;
            case 3:
                if (progressValue < 40 || progressValue > 60)
                    progressValue = 50;
                break;
            case 4:
                if (progressValue < 60 || progressValue > 80)
                    progressValue = 70;
                break;
            case 5:
                if (progressValue < 80)
                    progressValue = 100;
                break;
            case 0:
                progressValue = 0;
                break;
        }
        return progressValue;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CAR_NOTIFY), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;取消订阅成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;取消订阅失败");
            }
        });

        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CARBOX_GETNOW), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;取消订阅成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;取消订阅失败");
            }
        });

        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CAR_CTROL), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;取消订阅成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;取消订阅失败");
            }
        });
    }

    /**
     * 向汽车盒子发送命令开启或关闭驻车加热器
     *
     * @param button 用户当前按下的按钮
     * @param model  用户选择的开机模式常量
     **/
    public void heaterSwitch(RadioButton button, int model) {
        if (button != null && button.isChecked()) {
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg("M613.")
                    .setQos(2).setRetained(false)
                    .setTopic(CAR_CTROL), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                    UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);
                    showLoading();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                    UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                }
            });
        } else {
            if (!arcProgressBar.getIsOpen()) {

                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M61" + model + ".")
                        .setQos(2).setRetained(false)
                        .setTopic(CAR_CTROL), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                        UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);
                        showLoading();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                        UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                    }
                });

            } else {
                AlertUtil.t(WindHeaterActivity.this, "请先关机，再切换模式");
            }
        }
    }

    /**
     * 根据实时数据控制RadioButton的背景
     *
     * @param button   当前需要开启的RadioButton
     * @param openMode 用户选择的开机模式
     **/
    public void switchModel(RadioButton button, int openMode) {
        rgMagnet.clearCheck();
        if (button != null) {
            rgMagnet.check(button.getId());
        }
        this.openMode = openMode;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ConstantUtil.MSG_HEATER_ROGRESS_VALUE_CHANGE:

                        handleProgressMsg(msg);

                        break;
                }
                super.handleMessage(msg);
            }
        };

    }

    Integer myValue;

    private void handleProgressMsg(Message msg) {
        Bundle b = msg.getData();
        progressValue = b.getInt("progress_value");


        //先判断到底是什么种类的，开机模式

        //空调 档位 水泵 预泵油  预通风
        /**
         * 驻车预热器:1.档位开机2.空调开机3.关机 4.水泵开机9.关机中
         * 6.预泵油7.预通风
         */

        String dangWeiValue = PreferenceHelper.getInstance(mContext).getString(STARTSHELVES, "1");

        if (dangWeiValue.equals("1")) {//档位相关
            Log.i("MyProgressValue", String.valueOf(progressValue));
            dangWeiMode(progressValue);

        } else if (dangWeiValue.equals("2")) {//档位相关

            Log.i("MyProgressValue", String.valueOf(progressValue));

            myValue = 14 + progressValue;
            Log.i("myValue", String.valueOf(myValue));


            if (myValue > 0 && myValue <= 9) {


            } else if (myValue > 9 && myValue < 20) {

                String value = myValue.toString().substring(1, 2);
                Log.i("myValue", value);
                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M66" + value + ".")
                        .setQos(2)
                        .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                        showLoading();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败" + exception.getMessage());

                    }
                });

            } else if (myValue > 20 && myValue < 30) {
                String value = myValue.toString().substring(1, 2);
                Log.i("myValue", value);
                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M67" + value + ".")
                        .setQos(2)
                        .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                        showLoading();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败" + exception.getMessage());

                    }
                });

            } else if (myValue > 20 && myValue <= 32) {
                String value = myValue.toString().substring(1, 2);
                Log.i("myValue", value);
                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("M68" + value + ".")
                        .setQos(2)
                        .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                        showLoading();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败" + exception.getMessage());

                    }
                });
            }

            //  tvYuShe_WenDu.setText("当前的预设温度为：" + myValue + "℃");

        } else if (dangWeiValue.equals("3")) {//关机

        } else if (dangWeiValue.equals("4")) {//档位相关
            dangWeiMode(progressValue);

        } else if (dangWeiValue.equals("6")) {//档位相关
            dangWeiMode(progressValue);

        } else if (dangWeiValue.equals("7")) {//档位相关
            dangWeiMode(progressValue);

        }


    }

    private void dangWeiMode(int progressValue) {
        progressValue = progressValue + 14;
        if (progressValue >= 0 && progressValue <= 18) {
            gear = 1;
        } else if (progressValue > 18 && progressValue <= 21) {
            gear = 2;
        } else if (progressValue > 21 && progressValue <= 25) {
            gear = 3;
        } else if (progressValue > 25 && progressValue <= 28) {
            gear = 4;
        } else if (progressValue > 28 && progressValue <= 32) {
            gear = 5;
        } else {
            gear = 3;
        }
        //    HeaterMqttService.mqttService.publish("M62" + gear + ".", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);
        //String TOPIC_SERVER_ORDER = "wit/cbox/hardware/" + car_server_id + ccid;
//同步更新 进度条进度，选中进度条之后 更新进度条

        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M62" + gear + ".")
                .setQos(2)
                .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                showLoading();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                UIHelper.ToastMessage(WindHeaterActivity.this, "切换档位失败:" + exception.getMessage(), Toast.LENGTH_SHORT);

            }
        });


        int dangValue = 3;

        if (gear == 1) {
            dangValue = 4;

        } else if (gear == 2) {

            dangValue = 7;
        } else if (gear == 3) {

            dangValue = 11;
        } else if (gear == 4) {
            dangValue = 14;

        } else if (gear == 5) {
            dangValue = 18;
        }


        valueAnimator = ValueAnimator.ofInt(arcProgressBar.getCurrentProgerss(), dangValue);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();


    }


    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.btn_heater_close:
//                Log.d("isSub", "isSub==" + isSub);
                if (arcProgressBar.getIsOpen()) {
                    //关机
                    //HeaterMqttService.mqttService.publish("M613.", CAR_CTROL, 2, false);
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M613.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                            UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);
                            showLoading();
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                            UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                        }
                    });

                } else {
                    //开机
                    //根据用户选择的模式开机
                    //HeaterMqttService.mqttService.publish("M61" + openMode + PreferenceHelper.getInstance(WindHeaterActivity.this).getString("atmos", "") + ".", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);
                    //关机
                    //  HeaterMqttService.mqttService.publish("M61", CAR_CTROL, 2, false);
                    Log.i("Rair", "主题：" + CAR_CTROL + "  " + "M61" + PreferenceHelper.getInstance(this).getString(STARTSHELVES, "1") + ".");
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M61" + PreferenceHelper.getInstance(this).getString(STARTSHELVES, "1") + ".")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
                            UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送成功,等待服务器响应", Toast.LENGTH_SHORT);
                            showLoading();
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                            UIHelper.ToastMessage(WindHeaterActivity.this, "指令发送失败", Toast.LENGTH_SHORT);
                        }
                    });

                }
                // heaterSwitch(button, openMode);
                break;
            case R.id.rb_heater_air_mode: //空调模式
                heaterSwitch(rbHeaterAirMode, 2);
                break;
            case R.id.rb_heater_gear_mode://档位模式
                heaterSwitch(rbHeaterGearMode, 1);
                break;
            case R.id.rb_heater_pump_mode://水泵模式
                heaterSwitch(rbHeaterPumpMode, 4);
                break;
            case R.id.rb_heater_yby_mode://预泵油
                heaterSwitch(rbHeaterYbyMode, 6);
                break;
            case R.id.rb_heater_ytf_mode://预通风
                heaterSwitch(rbHeaterYtfMode, 7);
                break;
        }

        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.heater_menu_option, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawers();
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_bluetooth:
                //切换蓝牙模式
                UIHelper.ToastMessage(this, "暂不支持");
                break;
            case R.id.nav_trajectory:
                //历史轨迹
                startActivity(new Intent(this, HistoryLocusActivity.class));
                break;
            case R.id.nav_location:
                //定位
                startActivity(new Intent(this, LocationActivity.class));
                break;
            case R.id.nav_timing:
                //定时
                startActivity(new Intent(this, AppointmentActivity.class));
                break;
            case R.id.nav_record:
                //维修记录
                startActivity(new Intent(this, RepairOrderAcitivity.class));
                break;
            case R.id.nav_state:
                //加热器状态
                startActivity(new Intent(this, DeviceStateActivity.class));
                break;
            case R.id.nav_report:
                //故障报警
                // startActivity(new Intent(this, DiagnosisActivity.class));
                DiagnosisActivity.actionStart(this);
                break;
            case R.id.nav_corral:
                //地理围栏
                startActivity(new Intent(this, WebViewActivity.class).
                        putExtra("url", PreferenceHelper.getInstance(this).getString("fence_url", "")).putExtra("title", "地理围栏"));
                break;
            case R.id.nav_setting:
                startActivity(new Intent(this, HeaterSettingActivity.class));
                //设置
                break;
        }


        return false;
    }

    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03107");
        map.put("key", Constant.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(this).getString("car_id", ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<CarDetails.DataBean>>post(Constant.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CarDetails.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<CarDetails.DataBean>> response) {
                        PreferenceHelper.getInstance(WindHeaterActivity.this).putString("fence_url", response.body().data.get(0).getWeilan_url());
                        Glide.with(WindHeaterActivity.this).load(response.body().data.get(0).getCar_brand_url_one()).into(ivBrandPic);
                        tvBrandName.setText(response.body().data.get(0).getCar_brand_name_one());
                        tvCarNumber.setText(response.body().data.get(0).getPlate_number());

                    }

                    @Override
                    public void onError(Response<AppResponse<CarDetails.DataBean>> response) {
                        AlertUtil.t(WindHeaterActivity.this, response.getException().getMessage());
                    }
                });


    }

    public void openKongTiaoMode(int value) {
        arcProgressBar.setOpen(true);
        ivHeaterFire.setVisibility(View.VISIBLE);
        ivHeaterImpeller.setVisibility(View.VISIBLE);
        //    btnHeaterClose.setBackground(getResources().getDrawable(R.drawable.bg_heater_close_btn_on));
        btnHeaterClose.setSelected(true);

        //    myValue = (progressValue * 17) / 100 + 15;


//        progressValue = ((value - 15) * 100) / 18;
//        if (progressValue > 100) {
//            progressValue = 100;
//        }


        if (value > 32) {
            value = 32;
        }

        if (value > 15) {
            value = value - 14;
        }
        Log.i("arcProgressBar", value + "");
        valueAnimator = ValueAnimator.ofInt(arcProgressBar.getCurrentProgerss(), value);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();


    }

    public void openMode(int value) {
        arcProgressBar.setOpen(true);
        ivHeaterFire.setVisibility(View.VISIBLE);
        ivHeaterImpeller.setVisibility(View.VISIBLE);
        //    btnHeaterClose.setBackground(getResources().getDrawable(R.drawable.bg_heater_close_btn_on));
        btnHeaterClose.setSelected(true);

        //    myValue = (progressValue * 17) / 100 + 15;


//        progressValue = ((value - 15) * 100) / 18;
//        if (progressValue > 100) {
//            progressValue = 100;
//        }

        int dangValue = 3;

        if (value == 1) {
            dangValue = 4;

        } else if (value == 2) {

            dangValue = 7;
        } else if (value == 3) {

            dangValue = 11;
        } else if (value == 4) {
            dangValue = 14;

        } else if (value == 5) {
            dangValue = 18;
        }


        valueAnimator = ValueAnimator.ofInt(arcProgressBar.getCurrentProgerss(), dangValue);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();


    }

    public void closeMode(int value) {
        arcProgressBar.setOpen(false);
        ivHeaterFire.setVisibility(View.GONE);
        ivHeaterImpeller.setVisibility(View.GONE);
        //   btnHeaterClose.setBackground(getResources().getDrawable(R.drawable.bg_heater_close_btn_off));
        btnHeaterClose.setSelected(false);
        valueAnimator = ValueAnimator.ofInt(arcProgressBar.getCurrentProgerss(), value);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();
        switchModel(null, openMode);
    }

    @OnClick({R.id.rb_heater_air_mode, R.id.rb_heater_gear_mode, R.id.rb_heater_pump_mode, R.id.rb_heater_yby_mode, R.id.rb_heater_ytf_mode})
    public void onViewClicked(View view) {
        rgMagnet.clearCheck();
        if (button != null && arcProgressBar.getIsOpen())
            rgMagnet.check(button.getId());

    }

    public void setMqttZhiLing() {

        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(CAR_NOTIFY)
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


        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("k001.")
                .setQos(2).setRetained(false)
                .setTopic(CAR_NOTIFY), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(CAR_NOTIFY.java:79)-onSuccess:-&gt;发布成功");
                showLoading();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
            }
        });

        //获得车辆的实时数据和基本信息
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(CARBOX_GETNOW)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "订阅成功 carbox_getnow:  " + CARBOX_GETNOW);
                showLoading();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });


        //控制车辆
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(CAR_CTROL)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", " Rair用户订阅成功" + "wit/app/" + of_user_id);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");
            }
        });


        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("N9.")
                .setQos(2)
                .setTopic(CAR_CTROL)
                .setRetained(false), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;发布成功");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
            }
        });

        //查询车辆详情数据
        requestData();


        // HeaterMqttService.mqttService.publish("M512.", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M512.")
                .setQos(2).setRetained(false)
                .setTopic(CAR_CTROL), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(CAR_NOTIFY.java:79)-onSuccess:-&gt;发布成功");
                showLoading();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
            }
        });
    }

}
