package com.smarthome.magic.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.smarthome.magic.R;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;

import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.CarDetails;
import com.smarthome.magic.model.DataIn;
import com.smarthome.magic.model.HostModel;
import com.smarthome.magic.model.SerializableMap;
import com.smarthome.magic.model.SmartDevice_0;
import com.smarthome.magic.model.SmartDevice_car_0364;
import com.smarthome.magic.service.WitMqttFormatService;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.ConstantUtil;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.config.MyApplication.CAR_NOTIFY;

public class PlumbingHeaterActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, View.OnLongClickListener {


    private Context context = PlumbingHeaterActivity.this;
    private static final String TAG = "WindHeaterActivity";
    public Handler mHandler;
    public static Handler stateHandler;

    List<SmartDevice_car_0364.DataBean> carList = new ArrayList<>();//车联网列表数据源
    private static int progressValue;
    DrawerLayout drawer;
    int gear = 0;
    WitMqttFormatService witMqttFormatService;
    ValueAnimator valueAnimator;
    ImageView ivBrandPic;
    TextView tvBrandName, tvCarNumber;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_heater_host)
    RelativeLayout ivHeaterHost;
    //  @BindView(R.id.tv_wd)
    //  TextView tvWd;
    // @BindView(R.id.arcProgressBar)
    // ArcProgressBar arcProgressBar;

    @BindView(R.id.shuiwen1)
    TextView shuiwen1;

    @BindView(R.id.wendu2)
    TextView wendu2;

    @BindView(R.id.btn_heater_close)
    ImageView btn_heater_close;

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    ImageView mZhenShuinuan;
    Bitmap bitmap;
    AnimationDrawable animationDrawable;

    String SN_Send = "wh/hardware/11111111111111111111111";
    String SN_Accept = "wh/app/11111111111111111111111";

    private String sn_state = "";
    private String sn_preset_temperature1 = "";
    float temperature_lattice = 360 / 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuinuan);
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
                .statusBarColor(R.color.transparent)     //状态栏颜色，不写默认透明色
                .navigationBarColor(R.color.transparent) //导航栏颜色，不写默认黑色
                .barColor(R.color.transparent).init();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        ivBrandPic = navigationView.getHeaderView(0).findViewById(R.id.iv_brand_pic);
        tvBrandName = navigationView.getHeaderView(0).findViewById(R.id.tv_brand_name);
        tvCarNumber = navigationView.getHeaderView(0).findViewById(R.id.tv_car_number);
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbarTitle.setText("水暖加热器");
        @SuppressLint("ResourceType") ColorStateList csl = getResources().getColorStateList(R.drawable.menu_item_color);
        navigationView.setItemTextColor(csl);
        initHandler();
//          arcProgressBar.setHandler(mHandler);
//           arcProgressBar.setOpen(true);
        mZhenShuinuan = findViewById(R.id.zhen_shuinuan);

        //mZhenShuinuan.setPivotX(mZhenShuinuan.getWidth() / 2);
        // mZhenShuinuan.setPivotY(mZhenShuinuan.getHeight() / 2);//支点在图片中心
        mZhenShuinuan.setRotation(-123);

        shuiwen1.setOnLongClickListener(this);
        wendu2.setOnLongClickListener(this);
        btn_heater_close.setOnClickListener(this);

        registerKtMqtt();
        snedDefaultMqtt();
        getnotice();

//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        DataIn in = new D ataIn();
//        in.code = "03064";
//        in.key = Urls.key;
//        in.user_car_type = "1";
//        in.token = UserManager.getManager(this).getAppToken();
//        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, new Gson().toJson(in));
//        final Request request = new Request.Builder().url(Urls.SERVER_URL + "wit/app/user").post(body).build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                Gson gson = new Gson();
//                SmartDevice_0 bean = gson.fromJson(response.body().string(), SmartDevice_0.class);
//
//                System.out.println(bean.getData().size() + "");
//                for (int i = 0; i < bean.getData().size(); i++) {
//                    System.out.println(bean.getData().get(i).getCar_brand_name());
//                }
//
//
//            }
//        });
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

    /**
     * 获取通知返回的数据
     */
    private void getnotice() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_SN_DATA) {
                    //接收到信息
                    Log.i("msg_kt_j_", message.content.toString());
                    String messageData = message.content.toString().substring(3, message.content.toString().length() - 1);
                    //水暖状态
                    String state = messageData.substring(0, 1);
                    sn_state = state;
                    //预设温度
                    String preset_temperature = messageData.substring(35, 37);
                    sn_preset_temperature1 = preset_temperature;
                    //当前出水口温度
                    String current_temperature = messageData.substring(27, 30);
                    switch (state) {
                        case "0":
                            //待机中
                            btn_heater_close.setBackgroundResource(R.mipmap.car_open);
                            break;
                        case "1":
                            //开机中
                            btn_heater_close.setBackgroundResource(R.mipmap.car_open);
                            ivHeaterHost.setBackgroundResource(R.drawable.plumbing);
                            animationDrawable = (AnimationDrawable) ivHeaterHost.getBackground();
                            animationDrawable.start();
                            break;
                        case "2":
                            //加热中
                            btn_heater_close.setBackgroundResource(R.mipmap.car_open);
                            ivHeaterHost.setBackgroundResource(R.drawable.plumbing);
                            animationDrawable = (AnimationDrawable) ivHeaterHost.getBackground();
                            animationDrawable.start();
                            break;
                        case "3":
                            //关机中
                            btn_heater_close.setBackgroundResource(R.mipmap.car_close);
                            ivHeaterHost.setBackgroundResource(R.drawable.shuinuan_pic_gif_nor);
                            mZhenShuinuan.setRotation(-123);
                            break;
                        case "4":
                            //循环水
                            btn_heater_close.setBackgroundResource(R.mipmap.car_open);
                            ivHeaterHost.setBackgroundResource(R.drawable.plumbing);
                            animationDrawable = (AnimationDrawable) ivHeaterHost.getBackground();
                            animationDrawable.start();
                            break;
                    }
                    if (!state.equals("3")) {
                        //开机状态显示当前设置问题
                        if (Integer.parseInt(preset_temperature) <= 60) {
                            shuiwen1.setBackgroundResource(R.mipmap.sheding_button_sel);
                            wendu2.setBackgroundResource(R.mipmap.sheding_button_nor);
                        } else if (Integer.parseInt(preset_temperature) > 60 && Integer.parseInt(preset_temperature) <= 80) {
                            shuiwen1.setBackgroundResource(R.mipmap.sheding_button_nor);
                            wendu2.setBackgroundResource(R.mipmap.sheding_button_sel);
                        } else if (Integer.parseInt(preset_temperature) >= 80) {
                            shuiwen1.setBackgroundResource(R.mipmap.sheding_button_nor);
                            wendu2.setBackgroundResource(R.mipmap.sheding_button_sel);
                        }
                        //开机状态显示当前出水温度
                        float temperature = 0;
                        if (current_temperature.contains("-")) {
                            String[] str = current_temperature.split("-");
                            String format = new BigDecimal("-" + str[1]).toString();
                            temperature = Float.valueOf(format) / 10;
                        } else {
                            String format = new BigDecimal(current_temperature).toString();
                            temperature = Float.valueOf(format) / 10;
                        }
                        //先把仪表盘指针复位，再重新旋转
                        mZhenShuinuan.setRotation(-123);
                        float default_angle = mZhenShuinuan.getRotation();
                        mZhenShuinuan.setRotation(default_angle + temperature * temperature_lattice);

                    } else {
                        shuiwen1.setBackgroundResource(R.mipmap.sheding_button_nor);
                        wendu2.setBackgroundResource(R.mipmap.sheding_button_nor);
                    }

                }
            }
        }));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_heater_close:
                ShuiNuanSwitch();
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.shuiwen1:
                sendTemperature60();
                break;
            case R.id.wendu2:
                sendTemperature80();
                break;
        }
        return false;
    }

    /**
     * 设定60度
     */
    private void sendTemperature60() {
        if (sn_state.equals("3")) {
            Toast.makeText(context, "水暖已关机，请打开水暖发送指令", Toast.LENGTH_SHORT).show();
            return;
        }
        sn_preset_temperature1 = "60";
        String data = "j_s" + sn_state + "4561116666666110264026505007060001" + sn_preset_temperature1 + "02310.";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
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

    /**
     * 设定80度
     */
    private void sendTemperature80() {
        if (StringUtils.isEmpty(sn_state)||sn_state.equals("3")) {
            Toast.makeText(context, "水暖已关机，请打开水暖发送指令", Toast.LENGTH_SHORT).show();
            return;
        }
        sn_preset_temperature1 = "80";
        String data = "j_s" + sn_state + "4561116666666110264026505007060001" + sn_preset_temperature1 + "02310.";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
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

    /**
     * 水暖加热器开关
     */
    private void ShuiNuanSwitch() {
        if (StringUtils.isEmpty(sn_state)) {
            AlertUtil.t(this, "服务器未开启");
            return;
        }

        int state = Integer.parseInt(sn_state);
        if (state == 1) {
            state = 3;
        } else {
            state = 1;
        }

        String data = "j_s" + state + "45611166666661102640265050070600017002310.";
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(data)
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

        stateHandler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                switch (msg.what) {
                    case ConstantUtil.MSG_HEATER_MANUAL_ORDER:
                        //手动开机
                        openMode(b.getString("oper_wendu"), b.getString("oper_dang"));
                        break;
                    case ConstantUtil.MSG_HEATER_SHUTDOWN_ORDER:
                        //关机
                        closeMode(b.getString("oper_wendu"));
                        break;
                    case ConstantUtil.MSG_UPDATE_GEAR_ORDER:
                        //通知当前档位
                        AlertUtil.t(PlumbingHeaterActivity.this, "当前档位" + gear);
                        break;

                    case ConstantUtil.MSG_HEATER_ACTUAL_DATA:
                        //加载实时数据
                        SerializableMap serializableMap = (SerializableMap) b.get("map");
                        Log.d("version", serializableMap.getMap().get("version_feng"));
                        HostModel.version = serializableMap.getMap().get("version_feng");


                }
                super.handleMessage(msg);
            }
        };
    }


    private void handleProgressMsg(Message msg) {
        Bundle b = msg.getData();
        progressValue = b.getInt("progress_value");
        int PROGRESSDATABUFF = progressValue;
        if (PROGRESSDATABUFF <= 20) {
            gear = 1;
        } else if ((PROGRESSDATABUFF > 20) && (PROGRESSDATABUFF <= 40)) {
            gear = 2;
        } else if ((PROGRESSDATABUFF > 40) && (PROGRESSDATABUFF <= 60)) {
            gear = 3;
        } else if ((PROGRESSDATABUFF > 60) && (PROGRESSDATABUFF <= 80)) {
            gear = 4;
        } else if ((PROGRESSDATABUFF > 80) && (PROGRESSDATABUFF <= 100)) {
            gear = 5;
        } else {
            gear = 3;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.heater_menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
                showToast("暂不支持");
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
                startActivity(new Intent(this, DiagnosisActivity.class));
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
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(this).getString("car_id", ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<CarDetails.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CarDetails.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<CarDetails.DataBean>> response) {
                        PreferenceHelper.getInstance(PlumbingHeaterActivity.this).putString("fence_url", response.body().data.get(0).getWeilan_url());
                        Glide.with(PlumbingHeaterActivity.this).load(response.body().data.get(0).getCar_brand_url_one()).into(ivBrandPic);
                        tvBrandName.setText(response.body().data.get(0).getCar_brand_name_one());
                        tvCarNumber.setText(response.body().data.get(0).getPlate_number());
                    }

                    @Override
                    public void onError(Response<AppResponse<CarDetails.DataBean>> response) {
                        AlertUtil.t(PlumbingHeaterActivity.this, response.getException().getMessage());
                    }
                });
    }

    public void openMode(String temp, String gear) {
        //  arcProgressBar.setOpen(true);
        btn_heater_close.setBackground(getResources().getDrawable(R.drawable.bg_heater_close_btn_on));
        //  tvWd.setText(temp);
        progressValue = Integer.parseInt(gear) * 100 / 5;
        valueAnimator = ValueAnimator.ofInt(0, progressValue);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //  arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

    public void closeMode(String temp) {
        btn_heater_close.setBackground(getResources().getDrawable(R.drawable.bg_heater_close_btn_off));
        valueAnimator = ValueAnimator.ofInt(progressValue, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //   arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }
}
