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
import com.smarthome.magic.activity.wode_page.bazinew.YanpanActivity;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.DoMqttValue;
import com.smarthome.magic.util.SoundPoolUtils;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.config.MyApplication.CAR_NOTIFY;

public class ShuinuanMainActivity extends ShuinuanBaseActivity {

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

    private String sn_state;     //水暖状态
    private String yushewendu;      //预设温度

    float wenduxishu = 360.0f / 160.0f;

    private AnimationDrawable animationDrawable;

    private boolean isDingcheng = false;
    private String dataMsg = "j_s312022202531234502640265100100010018002310.";
    private boolean isKaiji;
    private boolean iskaijiDianhou;
    private GuzhangDialog guzhangDialog;

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_new;
    }

    public static void actionStart(Context context, String ccid, String count) {
        Intent intent = new Intent(context, ShuinuanMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ccid", ccid);
        intent.putExtra("count", count);
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
        init();
        initHuidiao();
        registerKtMqtt();
        initGuzhangDialog();
    }

    private void initGuzhangDialog() {
        guzhangDialog = new GuzhangDialog(mContext, new GuzhangDialog.Guzhang() {
            @Override
            public void onClickConfirm(View v, GuzhangDialog dialog) {

            }

            @Override
            public void onDismiss(GuzhangDialog dialog) {

            }
        });
    }

    private void init() {
        PreferenceHelper.getInstance(mContext).putString("ccid", "aaaaaaaaaaaaaaaa20040018");
        iv_shuinuan_zhen.setRotation(-123);
        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.SHUINUAN);
        isKaiji = false;
        iskaijiDianhou = false;


        String ccid = getIntent().getStringExtra("ccid");
        String count = getIntent().getStringExtra("count");

        if (!StringUtils.isEmpty(ccid)) {
            SN_Send = "wh/hardware/" + count + ccid;
            SN_Accept = "wh/app/" + count + ccid;
        }
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
            if (iskaijiDianhou == isKaiji) {
                dialogClick.dismiss();
            }
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
                case "1"://开机中
                case "2"://加热中
                case "4"://循环水
                    btn_heater_close.setBackgroundResource(R.mipmap.car_open);
                    iv_heater_host.setBackgroundResource(R.drawable.plumbing);
                    animationDrawable = (AnimationDrawable) iv_heater_host.getBackground();
                    animationDrawable.start();
                    isKaiji = true;
                    break;
                case "0"://待机中
                case "3"://关机中
                    btn_heater_close.setBackgroundResource(R.mipmap.car_close);
                    iv_heater_host.setBackgroundResource(R.drawable.shuinuan_pic_gif_nor);
                    iv_shuinuan_zhen.setRotation(-123);
                    isKaiji = false;
                    break;
            }

            if (isKaiji) {//开机状态显示当前设置问题
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
                    Log.i("操作成功", "");
                } else {
                    Log.i("操作失败", "");
                }
            } else {

            }
        } else if (msg.contains("M_s")) {
            String code = msg.substring(3, 5);
            if (code.equals("01")) {

            } else if (code.equals("02")) {

            }
        } else if (msg.contains("N_s")) {

        } else if (msg.contains("r_s")) {
            String dianya = msg.substring(3, 4);//电压	0.正常1.过高2.过低3.故障
            String youbeng = msg.substring(4, 5);//油泵	0.正常1.开路2.短路3.故障
            String shuibeng = msg.substring(5, 6);//水泵	0.正常1.开路2.短路3.过流4.堵转5.故障
            String chushuiko = msg.substring(6, 7);//出水口	0.正常1.开路2.短路3.高温4.故障
            String rushuiko = msg.substring(7, 8);//入水口	0.正常1.开路2.短路3.高温4.故障
            String wensheng = msg.substring(8, 9);//温升	0.正常1.异常
            String fengji = msg.substring(9, 10);//风机	0.正常1.开路2.短路3.过流4.堵转5.故障
            String chufengko = msg.substring(10, 11);//出风口	0.正常1.开路2.短路3.高温4.故障
            String dianhuosai = msg.substring(11, 12);//点火塞	0.正常1.开路2.短路3.故障
            String houyan = msg.substring(12, 13);//火焰	0.正常1.熄火
            String dianhuo = msg.substring(13, 14);//点火	0.正常1.失败


            List<String> guzhangs = new ArrayList<>();

            if (dianya.equals("1")) {
                guzhangs.add("故障报警：电压过高");
            } else if (dianya.equals("2")) {
                guzhangs.add("故障报警：电压过低");
            } else if (dianya.equals("3")) {
                guzhangs.add("故障报警：电压故障");
            }

            if (youbeng.equals("1")) {
                guzhangs.add("故障报警：油泵开路");
            } else if (youbeng.equals("2")) {
                guzhangs.add("故障报警：油泵短路");
            } else if (youbeng.equals("3")) {
                guzhangs.add("故障报警：油泵故障");
            }

            if (shuibeng.equals("1")) {
                guzhangs.add("故障报警：水泵开路");
            } else if (shuibeng.equals("2")) {
                guzhangs.add("故障报警：水泵短路");
            } else if (shuibeng.equals("3")) {
                guzhangs.add("故障报警：水泵过流");
            } else if (shuibeng.equals("4")) {
                guzhangs.add("故障报警：水泵堵转");
            } else if (shuibeng.equals("5")) {
                guzhangs.add("故障报警：水泵故障");
            }

            if (chushuiko.equals("1")) {
                guzhangs.add("故障报警：出水口开路");
            } else if (chushuiko.equals("2")) {
                guzhangs.add("故障报警：出水口短路");
            } else if (chushuiko.equals("3")) {
                guzhangs.add("故障报警：出水口高温");
            } else if (chushuiko.equals("4")) {
                guzhangs.add("故障报警：出水口故障");
            }

            if (rushuiko.equals("1")) {
                guzhangs.add("故障报警：入水口开路");
            } else if (rushuiko.equals("2")) {
                guzhangs.add("故障报警：入水口短路");
            } else if (rushuiko.equals("3")) {
                guzhangs.add("故障报警：入水口高温");
            } else if (rushuiko.equals("4")) {
                guzhangs.add("故障报警：入水口故障");
            }

            if (wensheng.equals("1")) {
                guzhangs.add("故障报警：升温异常");
            }

            if (fengji.equals("1")) {
                guzhangs.add("故障报警：风机开路");
            } else if (fengji.equals("2")) {
                guzhangs.add("故障报警：风机短路");
            } else if (fengji.equals("3")) {
                guzhangs.add("故障报警：风机过流");
            } else if (fengji.equals("4")) {
                guzhangs.add("故障报警：风机堵转");
            } else if (fengji.equals("5")) {
                guzhangs.add("故障报警：风机故障");
            }

            if (chufengko.equals("1")) {
                guzhangs.add("故障报警：出风口开路");
            } else if (chufengko.equals("2")) {
                guzhangs.add("故障报警：出风口短路");
            } else if (chufengko.equals("3")) {
                guzhangs.add("故障报警：出风口高温");
            } else if (chufengko.equals("4")) {
                guzhangs.add("故障报警：出风口故障");
            }

            if (dianhuosai.equals("1")) {
                guzhangs.add("故障报警：点火塞开路");
            } else if (dianhuosai.equals("2")) {
                guzhangs.add("故障报警：点火塞短路");
            } else if (dianhuosai.equals("3")) {
                guzhangs.add("故障报警：点火塞故障");
            }

            if (houyan.equals("1")) {
                guzhangs.add("故障报警：火焰熄火");
            }

            if (dianhuo.equals("1")) {
                guzhangs.add("故障报警：点火失败");
            }

            if (guzhangs.size() > 0) {
                showguzhangla(guzhangs);
            }
        }
    }

    private void registerKtMqtt() {
        showDialog("设备连接中...");
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

    private void showguzhangla(List<String> strings) {
        if (guzhangDialog != null && !guzhangDialog.isShowing()) {
            guzhangDialog.showDD(strings);
        }
    }


    /**
     * 水暖加热器开关
     */
    private void sendSwich() {
        if (TextUtils.isEmpty(sn_state) || TextUtils.isEmpty(yushewendu)) {
            AlertUtil.t(this, "水暖服务器未连接，请重新连接服务器");
            return;
        }

        String data;
        if (isKaiji) {
            iskaijiDianhou = false;
            data = "M_s012.";
        } else {
            iskaijiDianhou = true;
            data = "M_s0110000" + yushewendu + ".";
        }

        initHandler();

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
     * 模拟设定60度
     */
    private void sendTemperature60() {
        if (TextUtils.isEmpty(sn_state) || TextUtils.isEmpty(yushewendu)) {
            AlertUtil.t(this, "水暖服务器未连接，请重新连接服务器");
            return;
        }

        if (isKaiji) {
            return;
        }

        iskaijiDianhou = true;
        showDialogClick("发送指令中...");
        initHandler();

        yushewendu = "60";
        String data = "M_s0110000" + yushewendu + ".";
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
        if (TextUtils.isEmpty(sn_state) || TextUtils.isEmpty(yushewendu)) {
            AlertUtil.t(this, "水暖服务器未连接，请重新连接服务器");
            return;
        }

        if (isKaiji) {
            return;
        }

        showDialogClick("发送指令中...");
        initHandler();
        iskaijiDianhou = true;
        yushewendu = "80";
        String data = "M_s0110000" + yushewendu + ".";
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

    private void initHandler() {
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 5000);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (iskaijiDianhou != isKaiji) {
                        initHandler();
                        getNs();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
