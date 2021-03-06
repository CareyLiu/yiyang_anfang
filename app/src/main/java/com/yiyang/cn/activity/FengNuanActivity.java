package com.yiyang.cn.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.rairmmd.andmqtt.MqttUnSubscribe;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.util.Y;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConfigValue;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.dialog.LordingDialog;
import com.yiyang.cn.dialog.MyCarCaoZuoDialog_Notify;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.util.DoMqttValue;
import com.yiyang.cn.util.SoundPoolUtils;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.math.BigDecimal;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.app.ConfigValue.STARTSHELVES;
import static com.yiyang.cn.app.ConstanceValue.MSG_MQTT_CONNECTARRIVE;
import static com.yiyang.cn.app.ConstanceValue.MSG_MQTT_CONNECTCOMPLETE;
import static com.yiyang.cn.app.ConstanceValue.MSG_MQTT_CONNECTLOST;
import static com.yiyang.cn.app.ConstanceValue.MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE;
import static com.yiyang.cn.app.ConstanceValue.MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS;
import static com.yiyang.cn.config.MyApplication.CARBOX_GETNOW;
import static com.yiyang.cn.config.MyApplication.CAR_CTROL;
import static com.yiyang.cn.config.MyApplication.CAR_NOTIFY;
import static com.yiyang.cn.config.MyApplication.getAppContext;


public class FengNuanActivity extends BaseActivity implements View.OnLongClickListener {
    ProgressDialog waitDialog;
    @BindView(R.id.iv_kaiji)
    ImageView ivKaiji;
    @BindView(R.id.iv_guanji)
    ImageView ivGuanji;
    @BindView(R.id.ll_click_shebeima)
    LinearLayout llClickShebeima;
    @BindView(R.id.ll_dingshi)
    LinearLayout llDingshi;
    @BindView(R.id.ll_xinhao)
    LinearLayout llXinhao;
    @BindView(R.id.ll_fuwu_tianshu)
    LinearLayout llFuwuTianshu;
    @BindView(R.id.iv_dangweimoshi)
    ImageView ivDangweimoshi;
    @BindView(R.id.iv_kongtiaomoshi)
    ImageView ivKongtiaomoshi;
    @BindView(R.id.ll_gongneng)
    LinearLayout llGongneng;
    @BindView(R.id.ll_wendu)
    LinearLayout llWendu;
    @BindView(R.id.seekBar1)
    SeekBar seekBar1;
    @BindView(R.id.tv_zuidiwendu)
    TextView tvZuidiwendu;
    @BindView(R.id.tv_zuigaowendu)
    TextView tvZuigaowendu;
    @BindView(R.id.tv_dangqian_wendu_or_dangwei)
    TextView tvDangqianWenduOrDangwei;
    @BindView(R.id.tv_sheding_wendu_or_dangwei)
    TextView tvShedingWenduOrDangwei;
    @BindView(R.id.tv_shuibeng)
    TextView tvShuibeng;
    @BindView(R.id.tv_zidongbengyou)
    TextView tvZidongbengyou;
    @BindView(R.id.tv_yutongfeng)
    TextView tvYutongfeng;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.iv_xinhao)
    ImageView ivXinhao;
    @BindView(R.id.tv_zaixian)
    TextView tvZaixian;
    @BindView(R.id.tv_shebeizhuangtai)
    TextView tvShebeizhuangtai;
    @BindView(R.id.seekBar_kongtiao)
    SeekBar seekBarKongtiao;
    @BindView(R.id.tv_dianya)
    TextView tvDianya;
    @BindView(R.id.tv_rufengkouwendu)
    TextView tvRufengkouwendu;
    @BindView(R.id.tv_chufengkouwendu)
    TextView tvChufengkouwendu;
    @BindView(R.id.tv_haibagaodu)
    TextView tvHaibagaodu;
    @BindView(R.id.tv_hanyangliang)
    TextView tvHanyangliang;
    @BindView(R.id.tv_daqiya)
    TextView tvDaqiya;
    @BindView(R.id.iv_shezhi)
    ImageView ivShezhi;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_gongzuo_shichang)
    TextView tvGongzuoShichang;
    private LordingDialog lordingDialog;
    public String car_server_id;
    public String ccid;
    public String of_user_id;
    MyCarCaoZuoDialog_Notify myCarCaoZuoDialog_notify;
    String version;
    private String dangWeiMoShiValue = "0";//0 ??? 1 ???
    private String kongTiaoMoshiValue = "0";//0??? 1 ???
    private String bengYouValue = "0";//0 ??? 1 ???
    private String tongFengValue = "0";
    private String shuiBengValue = "0";//0 ??? 1??? a ???

    private String shuiBengZhongXianFlag = "1";
    private String firstSetDangWei = "0";
    private String firstSetKongTiao = "0";
    TishiDialog tishiDialog;


    //????????????????????????   1.?????????????????? 2.?????????????????? 3.?????????????????? 4.?????????????????? 5????????????????????? 6??????????????? 7???????????? 8???????????? 9???????????? 10 ????????????,11??????????????????
    private String whatUWant = "aaa";


    private final String DANGWEIKAIJI = "1";
    private final String DANGWEIGUANJI = "2";
    private final String KONGTIAOKAIJI = "3";
    private final String KONGTIAOGUANJI = "4";
    private final String YUTONGFENGKAIJI = "5";
    private final String YUTONGFENGGUANJI = "6";
    private final String BENGYOUKAIJI = "7";
    private final String BENGYOUGUANJI = "8";
    private final String SHUIBENGKAIJI = "9";
    private final String SHUIBENGGUANJI = "10";
    private final String PANDUANZAIXIANZHUAGNTAI = "11";//??????????????????
    N9Thread n9Thread = null;
    DangWeiGuanJiThread dangWeiGuanJiThread = null;//????????????
    DangWeiKaiJiThread dangWeiKaiJiThread = null;//????????????
    KongTiaoKaiJiThread kongTiaoKaiJiThread = null;//????????????
    KongTiaoGuanJiThread kongTiaoGuanJiThread = null;//????????????
    YuTongFengKaiJiThread yuTongFengKaiJiThread = null;//???????????????
    YuTongFengGuanJiThread yuTongFengGuanJiThread = null;//???????????????
    BengYouKaiJiThread bengYouKaiJiThread = null;// ????????????
    BengYouGuanJiThread bengYouGuanJiThread = null;//????????????
    XunHuanN xunHuanN = null;
    String dangQianDangWei = "3";//??????3???

    String simKaIdFlag = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_JIEBANG) {
                    finish();
                } else if (message.type == ConstanceValue.MSG_NETWORK_CHANGE) {
//                    if (!n9Thread.isAlive()){
//                        n9Thread.start();
//                    }
                }
            }
        }));

        simKaIdFlag = getIntent().getStringExtra("simKaIdFlag");

        ivShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        llDingshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FengnuandishiActivity.actionStart(mContext);
            }
        });
        llClickShebeima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TishiDialog tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                    @Override
                    public void onClickCancel(View v, TishiDialog dialog) {

                    }

                    @Override
                    public void onClickConfirm(View v, TishiDialog dialog) {

                    }

                    @Override
                    public void onDismiss(TishiDialog dialog) {

                    }
                });
                String ccid = PreferenceHelper.getInstance(mContext).getString("ccid", "");
                tishiDialog.setTextTitle("?????????");
                tishiDialog.setTextCancel("");
                tishiDialog.setTextContent(ccid);
                tishiDialog.show();
            }
        });
        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.FENGNUAN);
        waitDialog = ProgressDialog.show(mContext, null, "?????????????????????,???????????????", true, true);
        lordingDialog = new LordingDialog(mContext);

        car_server_id = PreferenceHelper.getInstance(mContext).getString("car_server_id", "");
        ccid = PreferenceHelper.getInstance(mContext).getString("ccid", "");
        of_user_id = PreferenceHelper.getInstance(mContext).getString("of_user_id", "");
        setMqttZhiLing();

        Glide.with(mContext).asGif().load(R.drawable.fengnuan_kaiji).into(ivKaiji);

        ivKaiji.setVisibility(View.GONE);
        ivGuanji.setVisibility(View.VISIBLE);

        myCarCaoZuoDialog_notify = new MyCarCaoZuoDialog_Notify(getAppContext(), new MyCarCaoZuoDialog_Notify.OnDialogItemClickListener() {
            @Override
            public void clickLeft() {
                // player.stop();

            }

            @Override
            public void clickRight() {
                DiagnosisActivity.actionStart(mContext);
                //SoundPoolUtils.soundPool.release();
                myCarCaoZuoDialog_notify.dismiss();
                if (SoundPoolUtils.soundPool != null) {
                    SoundPoolUtils.soundPool.release();
                }

            }
        }
        );

        myCarCaoZuoDialog_notify.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);

        getTongZhi();

        String value = PreferenceHelper.getInstance(mContext).getString(STARTSHELVES, "1");
//        if (value.equals("1")) {//??????
//            ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_sel);
//            ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);
//
//        } else if (value.equals("2")) {//??????
//            ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_sel);
//            ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
//        }


        ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
        ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);

        ivDangweimoshi.setOnLongClickListener(this);
        ivKongtiaomoshi.setOnLongClickListener(this);


        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (dangWeiMoShiValue.equals("1")) {

                    //   UIHelper.ToastMessage(mContext, seekBar.getProgress() + "");
                    if (seekBar.getProgress() >= 0 && seekBar.getProgress() < 20) {
                        seekBar1.setProgress(20);
                        qieHuanDangWei(1);

                    } else if (seekBar.getProgress() > 20 && seekBar.getProgress() < 40) {

                        if (seekBar.getProgress() > 20 && seekBar.getProgress() < 30) {
                            seekBar1.setProgress(20);
                            qieHuanDangWei(1);
                        } else {
                            seekBar1.setProgress(40);
                            qieHuanDangWei(2);
                        }

                    } else if (seekBar.getProgress() > 40 && seekBar.getProgress() < 60) {
                        if (seekBar.getProgress() > 40 && seekBar.getProgress() < 50) {
                            seekBar1.setProgress(40);
                            qieHuanDangWei(2);
                        } else {
                            seekBar1.setProgress(60);
                            qieHuanDangWei(3);
                        }

                    } else if (seekBar.getProgress() > 60 && seekBar.getProgress() < 80) {
                        if (seekBar.getProgress() > 60 && seekBar.getProgress() < 70) {
                            seekBar1.setProgress(60);
                            qieHuanDangWei(3);
                        } else {
                            seekBar1.setProgress(80);
                            qieHuanDangWei(4);
                        }

                    } else if (seekBar.getProgress() > 80 && seekBar.getProgress() < 100) {
                        if (seekBar.getProgress() > 80 && seekBar.getProgress() < 90) {
                            seekBar1.setProgress(80);
                            qieHuanDangWei(4);
                        } else {
                            seekBar1.setProgress(100);
                            qieHuanDangWei(5);
                        }
                    }

                }

            }
        });

        seekBarKongtiao.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                qieHuanWenDu(seekBar.getProgress());

            }
        });
        lordingDialog.setTextMsg("???????????????...");
        lordingDialog.show();

        tvShuibeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (run==true){
//                    run = false;
//                    handler.removeCallbacks(myRunnable);
//                }

                if (shuiBengValue.equals("a")) {
                    UIHelper.ToastMessage(mContext, "???????????????");
                    return;
                }

                if (shuiBengValue.equals("0")) {
                    whatUWant = SHUIBENGKAIJI;
                    lordingDialog.setTextMsg("????????????????????????????????????");
                    lordingDialog.show();
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M711.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                            //UIHelper.ToastMessage(WindHeaterActivity.this, "??????????????????,?????????????????????", Toast.LENGTH_SHORT);
                            //PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k71" + "1" + "1.");


                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            UIHelper.ToastMessage(mContext, "??????????????????", Toast.LENGTH_SHORT);
                        }
                    });
                } else {
                    whatUWant = SHUIBENGGUANJI;
                    lordingDialog.setTextMsg("????????????????????????????????????");
                    lordingDialog.show();
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M712.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                            //UIHelper.ToastMessage(WindHeaterActivity.this, "??????????????????,?????????????????????", Toast.LENGTH_SHORT);
                            PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k71" + "2" + "1.");

                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            UIHelper.ToastMessage(mContext, "??????????????????", Toast.LENGTH_SHORT);
                        }
                    });
                }


            }
        });
        tvYutongfeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (run==true){
//                    run = false;
//                    handler.removeCallbacks(myRunnable);
//                }
                if (kongTiaoMoshiValue.equals("1") || dangWeiMoShiValue.equals("1") || bengYouValue.equals("1")) {
                    UIHelper.ToastMessage(mContext, "????????????????????????????????????");
                    return;
                }

                if (tongFengValue.equals("0")) {

//                    lordingDialog.setTextMsg("???????????????????????????...");
//                    lordingDialog.show();

                    whatUWant = YUTONGFENGKAIJI;
                    yuTongFengKaiJiThread = new YuTongFengKaiJiThread();
                    yuTongFengKaiJiThread.start();

                } else if (tongFengValue.equals("1")) {
                    //???????????????
//                    lordingDialog.setTextMsg("?????????????????????...");
//                    lordingDialog.show();
                    whatUWant = YUTONGFENGGUANJI;
                    yuTongFengGuanJiThread = new YuTongFengGuanJiThread();
                    yuTongFengGuanJiThread.start();


                }
            }
        });

        tvZidongbengyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (run==true){
//                    run = false;
//                    handler.removeCallbacks(myRunnable);
//                }
                if (kongTiaoMoshiValue.equals("1") || dangWeiMoShiValue.equals("1") || tongFengValue.equals("1")) {
                    UIHelper.ToastMessage(mContext, "?????????????????????????????????");
                    return;
                }

                if (bengYouValue.equals("0")) {
//                    lordingDialog.setTextMsg("?????????????????????????????????");
//                    lordingDialog.show();
                    whatUWant = BENGYOUKAIJI;
                    bengYouKaiJiThread = new BengYouKaiJiThread();
                    bengYouKaiJiThread.start();

                } else if (bengYouValue.equals("1")) {
                    //????????????
//                    lordingDialog.setTextMsg("?????????????????????...");
//                    lordingDialog.show();
                    whatUWant = BENGYOUGUANJI;
                    bengYouGuanJiThread = new BengYouGuanJiThread();
                    bengYouGuanJiThread.start();
                }
            }
        });
        tishiDialog = new TishiDialog(getAppContext(), 1, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {

            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (!AndMqtt.getInstance().isConnect()) {
                    UIHelper.ToastMessage(mContext, "??????????????????????????????");
                    return;
                }
                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("N.")
                        .setQos(2)
                        .setTopic(CAR_CTROL)
                        .setRetained(false), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        //Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????" + " N9 ???????????????????????????");
                        Log.i("xunhuancishu", "???????????????" + xunHuanCiShu + "???");
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        //Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                    }
                });
            }
        });
    }

    boolean flag = true;

    private void getTongZhi() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                Log.i("message.type", String.valueOf(message.type));
                if (message.type == ConstanceValue.MSG_CAR_J_G) {

                    //???????????????
                    Log.i("msg_car_j_", message.content.toString());
                    String messageData = message.content.toString().substring(1, message.content.toString().length() - 1);
                    if (0 <= messageData.substring(0, 6).indexOf("a")) {
                        // ??????x 045.666666=045666666 9
                        BigDecimal dec = new BigDecimal(messageData.substring(0, 3) + "." + messageData.substring(3, 9));
                        String gps_x = dec.toString();
                        // ??????y 126.666666=126666666 9
                        dec = new BigDecimal(messageData.substring(9, 12) + "." + messageData.substring(12, 18));
                        String gps_y = dec.toString();
                        // ??????????????????:1???2???3???4???5?????? 6??????7?????? 8??????
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
                    if (smartRefreshLayout != null) {
                        smartRefreshLayout.finishRefresh();
                    }

                    if (lordingDialog != null) {
                        if (lordingDialog.isShowing()) {
                            lordingDialog.dismiss();
                        }
                    }
                    if (flag) {

                        if (simKaIdFlag.equals("2")) {
                            AndMqtt.getInstance().publish(new MqttPublish()
                                    .setMsg("X.")
                                    .setQos(2)
                                    .setTopic(CAR_CTROL)
                                    .setRetained(false), new IMqttActionListener() {
                                @Override
                                public void onSuccess(IMqttToken asyncActionToken) {
                                    Log.i("Rair", "????????????P.????????????");

                                }

                                @Override
                                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                    Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                                }
                            });
                        }


                        //??????????????????
                        AndMqtt.getInstance().publish(new MqttPublish()
                                .setMsg("Y.")
                                .setQos(2)
                                .setTopic(CAR_CTROL)
                                .setRetained(false), new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                Log.i("Rair", "????????????P.????????????");
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            }
                        });
                        flag = false;
                    }


                    lordingDialog.dismiss();
                    tvZaixian.setText("??????");
                    if (whatUWant.equals(PANDUANZAIXIANZHUAGNTAI)) {
                        whatUWant = "";

                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_N9_LIANJIE;
                        RxBus.getDefault().sendRx(n);
                    }
                    //???????????????
                    Log.i("msg_car_j_m", message.content.toString());

                    String messageData = message.content.toString().substring(1);
                    Log.i("msg_car_j_m_data", messageData);
                    // ???????????????:????????????1???5???	1	???
                    String oper_dang = messageData.substring(0, 1);

                    dangQianDangWei = oper_dang;
                    if (0 <= oper_dang.indexOf("a")) {
                        oper_dang = "";
                    } else {
                        if (oper_dang.equals("1")) {
                            seekBar1.setProgress(20);
                        } else if (oper_dang.equals("2")) {
                            seekBar1.setProgress(40);
                        } else if (oper_dang.equals("3")) {
                            seekBar1.setProgress(60);
                        } else if (oper_dang.equals("4")) {
                            seekBar1.setProgress(80);
                        } else if (oper_dang.equals("5")) {
                            seekBar1.setProgress(100);
                        }

                    }
                    // ????????????1???32???	2	???
                    String oper_wendu_now = messageData.substring(1, 3);
                    oper_wendu_now = 0 <= oper_wendu_now.indexOf("a") ? "" : new BigDecimal(oper_wendu_now).toString();

                    // ???????????????:1.????????????2.????????????3.????????????4.??????:??????5.??????:??????	1	???
                    String oper_open_close = messageData.substring(3, 4);
                    /**
                     * ???????????????:1.????????????2.????????????3.?????? 4.????????????9.?????????
                     * 6.?????????7.?????????
                     */


                    switch (oper_open_close) {
                        case "1":

//                            if (run!=true){
//                                run = true;
//                                handler.post(myRunnable);
//                            }

                            tvShebeizhuangtai.setText("???????????????????????????");
                            tvDangqianWenduOrDangwei.setText("???????????????" + oper_dang + "???");


                            //   UIHelper.ToastMessage(mContext, seekBar.getProgress() + "");
                            if (seekBar1.getProgress() >= 0 && seekBar1.getProgress() < 20) {

                                tvShedingWenduOrDangwei.setText("???????????????" + "1" + "???");
                            } else if (seekBar1.getProgress() >= 20 && seekBar1.getProgress() < 40) {

                                if (seekBar1.getProgress() >= 20 && seekBar1.getProgress() < 30) {
                                    tvShedingWenduOrDangwei.setText("???????????????" + "1" + "???");
                                } else {

                                    tvShedingWenduOrDangwei.setText("???????????????" + "2" + "???");
                                }

                            } else if (seekBar1.getProgress() >= 40 && seekBar1.getProgress() < 60) {
                                if (seekBar1.getProgress() >= 40 && seekBar1.getProgress() < 50) {
                                    tvShedingWenduOrDangwei.setText("???????????????" + "2" + "???");
                                } else {
                                    tvShedingWenduOrDangwei.setText("???????????????" + "3" + "???");
                                }

                            } else if (seekBar1.getProgress() >= 60 && seekBar1.getProgress() < 80) {
                                if (seekBar1.getProgress() >= 60 && seekBar1.getProgress() < 70) {
                                    tvShedingWenduOrDangwei.setText("???????????????" + "3" + "???");
                                } else {
                                    tvShedingWenduOrDangwei.setText("???????????????" + "4" + "???");
                                }

                            } else if (seekBar1.getProgress() >= 80 && seekBar1.getProgress() < 100) {
                                if (seekBar1.getProgress() >= 80 && seekBar1.getProgress() < 90) {
                                    tvShedingWenduOrDangwei.setText("???????????????" + "4" + "???");
                                } else {
                                    tvShedingWenduOrDangwei.setText("???????????????" + "5" + "???");
                                }
                            }


                            if (firstSetDangWei.equals("0")) {
                                tvShedingWenduOrDangwei.setText("???????????????" + oper_dang + "???");
                                firstSetDangWei = "1";
                            }
                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "1");
                            //openMode(Integer.parseInt(oper_dang));
                            //switchModel(rbHeaterGearMode, 1);
                            // button = rbHeaterGearMode;

                            // if (oper_dang != null) {
                            //    openMode(Integer.parseInt(oper_dang));
                            //  }
                            ivKaiji.setVisibility(View.VISIBLE);
                            ivGuanji.setVisibility(View.GONE);

                            ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_sel);
                            ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);

                            tvZuidiwendu.setText("1???");
                            tvZuigaowendu.setText("5???");

                            dangWeiMoShiValue = "1";


                            seekBar1.setVisibility(View.VISIBLE);
                            seekBarKongtiao.setVisibility(View.GONE);

                            if (whatUWant.equals(DANGWEIKAIJI)) {
                                lordingDialog.dismiss();
                                SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                                whatUWant = "";
                                dangWeiKaiJiThread.interrupt();
                            }

                            if (banBenHao != null) {
                                if (banBenHao.equals("2020")) {
                                    if (xunHuanN == null) {
                                        Log.i("xunhuancishu", "??????????????????");
                                        //  UIHelper.ToastMessage(mContext, "xunhuan xunhuan");
                                        xunHuanNFlag = true;
                                        xunHuanN = new XunHuanN();
                                        xunHuanN.start();
                                    }
                                }

                            }

                            break;
                        case "2":
//                            if (run!=true){
//                                run = true;
//                                handler.post(myRunnable);
//                            }

                            if (firstSetKongTiao.equals("0")) {
                                tvShedingWenduOrDangwei.setText("???????????????" + oper_wendu_now + "???");
                                firstSetKongTiao = "1";
                            }
                            seekBarKongtiao.setProgress(Integer.parseInt(oper_wendu_now));
                            tvShebeizhuangtai.setText("???????????????????????????");
                            ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
                            ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_sel);
                            if (oper_wendu_now != null) {
                                tvDangqianWenduOrDangwei.setText("???????????????" + oper_wendu_now + "???");
                            }
                            tvShedingWenduOrDangwei.setText("???????????????" + seekBarKongtiao.getProgress() + "???");
                            tvZuidiwendu.setText("0???");
                            tvZuigaowendu.setText("32???");
                            kongTiaoMoshiValue = "1";
                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "2");
//                            if (null != oper_wendu_now) {
//                                openKongTiaoMode(Integer.parseInt(oper_wendu_now));
//                            } else {
//                                openMode(0);
//                            }
//
//                            switchModel(rbHeaterAirMode, 2);
//                            button = rbHeaterAirMode;
                            ivKaiji.setVisibility(View.VISIBLE);
                            ivGuanji.setVisibility(View.GONE);

                            seekBar1.setVisibility(View.GONE);
                            seekBarKongtiao.setVisibility(View.VISIBLE);

                            if (whatUWant.equals(KONGTIAOKAIJI)) {
                                lordingDialog.dismiss();
                                SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                                whatUWant = "";
                                if (kongTiaoKaiJiThread != null) {
                                    kongTiaoKaiJiThread.interrupt();
                                }

                                if (tishiDialog != null) {
                                    tishiDialog.dismiss();
                                }
                            }
                            if (banBenHao != null) {
                                if (banBenHao.equals("2020")) {
                                    if (xunHuanN == null) {
                                        Log.i("xunhuancishu", "??????????????????");
                                        xunHuanN = new XunHuanN();
                                        xunHuanNFlag = true;
                                        xunHuanN.start();
                                    }
                                }

                            }
                            break;
                        case "3":
//                            if (run==true){
//                                run = false;
//                                handler.removeCallbacks(myRunnable);
//                            }

//                            if (xunHuanN != null) {
//                                xunHuanNFlag = false;
//                                xunHuanN.interrupt();
//                                xunHuanN = null;
//                            }


                            tvShebeizhuangtai.setText("?????????????????????");
                            ivKaiji.setVisibility(View.GONE);
                            ivGuanji.setVisibility(View.VISIBLE);

                            ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
                            ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);

                            dangWeiMoShiValue = "0";
                            kongTiaoMoshiValue = "0";
                            shuiBengValue = "0";
                            tongFengValue = "0";
                            bengYouValue = "0";

                            tvYutongfeng.setTextColor(mContext.getResources().getColor(R.color.gray999999));
                            tvZidongbengyou.setTextColor(mContext.getResources().getColor(R.color.gray999999));

                            //   closeMode(0);
                            //  AlertUtil.t(WindHeaterActivity.this, "?????????????????????");
                            //tvYuShe_WenDu.setText("????????????????????????");

                            tvDangqianWenduOrDangwei.setText("????????????/??????: - -");
                            tvShedingWenduOrDangwei.setText("????????????/??????: - -");
                            tvZuidiwendu.setText("0???/1???");
                            tvZuigaowendu.setText("32???/5???");
                            if (whatUWant.equals(DANGWEIGUANJI)) {
                                lordingDialog.dismiss();
                                SoundPoolUtils.soundPool(mContext, R.raw.yiguanji);
                                whatUWant = "";
                                if (dangWeiGuanJiThread != null) {
                                    dangWeiGuanJiThread.interrupt();
                                }
                            } else if (whatUWant.equals(KONGTIAOGUANJI)) {
                                lordingDialog.dismiss();
                                SoundPoolUtils.soundPool(mContext, R.raw.yiguanji);
                                whatUWant = "";
                                if (kongTiaoGuanJiThread != null) {
                                    kongTiaoGuanJiThread.interrupt();
                                }
                            } else if (whatUWant.equals(BENGYOUGUANJI)) {
                                tishiDialog.dismiss();
                                whatUWant = "";
                            } else if (whatUWant.equals(YUTONGFENGGUANJI)) {
                                whatUWant = "";
                                if (yuTongFengGuanJiThread != null) {
                                    yuTongFengGuanJiThread.interrupt();
                                }
                                tishiDialog.dismiss();
                            } else if (whatUWant.equals(SHUIBENGGUANJI)) {
                                whatUWant = "";
                                lordingDialog.dismiss();
                            } else if (whatUWant.equals(BENGYOUGUANJI)) {
                                whatUWant = "";
                                tishiDialog.dismiss();
                            }

                            break;
                        case "4"://????????????
//                            if (run!=true){
//                                run = true;
//                                handler.post(myRunnable);
//                            }

                            //  tvShebeizhuangtai.setText("???????????????????????????");
//                            tvShuibeng.setTextColor(mContext.getResources().getColor(R.color.blue00fff));
//                            tvShuibeng.setText("???????????????");
                            if (lordingDialog != null) {
                                if (lordingDialog.isShowing()) {
                                    lordingDialog.dismiss();
                                }
                            }

                            break;
                        case "6":
//                            if (run!=true){
//                                run = true;
//                                handler.post(myRunnable);
//                            }

                            tvShebeizhuangtai.setText("????????????????????????");
                            tvZidongbengyou.setTextColor(mContext.getResources().getColor(R.color.blue00fff));
                            bengYouValue = "1";

                            if (whatUWant.equals(BENGYOUKAIJI)) {

                                whatUWant = "";

                                if (bengYouKaiJiThread != null) {
                                    bengYouKaiJiThread.interrupt();
                                }
                            }
                            break;
                        case "7":
//                            if (run!=true){
//                                run = true;
//                                handler.post(myRunnable);
//                            }

                            tvShebeizhuangtai.setText("????????????????????????");
                            tvYutongfeng.setTextColor(mContext.getResources().getColor(R.color.blue00fff));
                            tongFengValue = "1";
//                            tvYuShe_WenDu.setText("?????????????????????" + oper_dang + "???");
//                            button = rbHeaterYtfMode;
//                            PreferenceHelper.getInstance(mContext).putString(STARTSHELVES, "7");
//
//                            switchModel(rbHeaterYtfMode, 7);
//                            //????????????
//                            if (oper_dang != null) {
//                                openMode(Integer.parseInt(oper_dang));
//                            }

                            if (whatUWant.equals(YUTONGFENGKAIJI)) {


                                whatUWant = "";
                                yuTongFengKaiJiThread.interrupt();
                            }

                            break;
                        case "9":
//                            if (run==true){
//                                run = false;
//                                handler.removeCallbacks(myRunnable);
//                            }
                            tvShebeizhuangtai.setText("????????????????????????");
                            // mTvWd.setText("00");
                            // showLoadSuccess();
                            //  AlertUtil.t(WindHeaterActivity.this, "????????????????????????");
                            break;
                    }
                    // ???????????????:????????????	3	???
                    String oper_wendu = messageData.substring(4, 6);
                    oper_wendu += "0".equals(messageData.substring(6, 7)) ? "" : "." + messageData.substring(6, 7);

                    // ???????????????:???????????? ??????:-03	3	???
                    String xinhaoQiangDu = messageData.substring(7, 9);
                    String jiGeXinHao = "??????????????????";
                    if (!StringUtils.isEmpty(xinhaoQiangDu)) {

                        int xinhao = Y.getInt(xinhaoQiangDu);
                        if (xinhao >= 0 && xinhao <= 14) {
                            jiGeXinHao = "????????????";
                            ivXinhao.setBackgroundResource(R.mipmap.fengnuan_icon_signal1);
                        } else if (xinhao >= 15 && xinhao <= 19) {
                            jiGeXinHao = "????????????";
                            ivXinhao.setBackgroundResource(R.mipmap.fengnuan_icon_signal2);
                        } else if (xinhao >= 20 && xinhao <= 25) {
                            jiGeXinHao = "????????????";
                            ivXinhao.setBackgroundResource(R.mipmap.fengnuan_icon_signal3);
                        } else if (xinhao >= 26 && xinhao <= 35) {
                            jiGeXinHao = "????????????";
                            ivXinhao.setBackgroundResource(R.mipmap.fengnuan_icon_signal4);
                        }

                        //  UIHelper.ToastMessage(mContext, jiGeXinHao+xinhao);
                    }


                    Log.i("rair", "??????????????????: " + xinhaoQiangDu);
                    // ???????????????:??????->0253 = 25.3	4	???
                    String machine_voltage = Y.getInt(messageData.substring(10, 13)) + "." + messageData.substring(13, 14);
                    tvDianya.setText(machine_voltage + "V");
                    // ???????????????:????????????->13245	5	???
                    String revolution = messageData.substring(14, 19);
                    // ???????????????:???????????????->0264=26.4	4	???
                    String power = messageData.substring(19, 23);
                    // ???????????????:????????????->0265=26.5	4	???
                    String frequency = messageData.substring(23, 27);
                    frequency = frequency.substring(0, 3) + "." + frequency.substring(3);
                    // ???????????????:???????????????->??????:-026	4	???

                    String in_temperature = messageData.substring(27, 31);
                    tvRufengkouwendu.setText(Y.getInt(in_temperature) + "???");
                    // ???????????????:???????????????->0128	4	???
                    String out_temperature = messageData.substring(31, 35);
                    tvChufengkouwendu.setText(Y.getInt(out_temperature) + "???");

                    String wendu = out_temperature.substring(2, 4);
                    if (kongTiaoMoshiValue.equals("1")) {
                        tvDangqianWenduOrDangwei.setText("???????????????" + oper_wendu_now + "???");
                    }

                    // ????????????????????????->01???18	2	 ???????????????
                    String zhu_car_stoppage_no = messageData.substring(35, 37);
                    if (!StringUtils.isEmpty(zhu_car_stoppage_no)) {
                        zhu_car_stoppage_no = 0 <= zhu_car_stoppage_no.indexOf("a") ? "" : String.valueOf(Integer.parseInt(zhu_car_stoppage_no));
                    }


                    if (!StringUtils.isEmpty(zhu_car_stoppage_no)) {
                        Activity currentActivity = AppManager.getAppManager().currentActivity();
                        if (currentActivity != null) {
                            if (!currentActivity.getClass().getSimpleName().equals(DiagnosisActivity.class.getSimpleName())) {

                                if (!myCarCaoZuoDialog_notify.isShowing()) {
                                    myCarCaoZuoDialog_notify.show();

//                                    AndMqtt.getInstance().publish(new MqttPublish()
//                                            .setMsg("M692.")
//                                            .setQos(2).setRetained(false)
//                                            .setTopic(CAR_CTROL), new IMqttActionListener() {
//                                        @Override
//                                        public void onSuccess(IMqttToken asyncActionToken) {
//                                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
//                                            //  UIHelper.ToastMessage(WindHeaterActivity.this, "??????????????????,?????????????????????", Toast.LENGTH_SHORT);
//
//                                        }
//
//                                        @Override
//                                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
//                                            UIHelper.ToastMessage(mContext, "????????????????????????????????????????????????", Toast.LENGTH_SHORT);
//                                        }
//                                    });
                                }

                            }
                        }

                    }
                    if (messageData.length() >= 38) {
                        // ????????????   0 ???  1 ??? a ?????????
                        String shuiBengZhuangTai = messageData.substring(37, 38);

                        switch (shuiBengZhuangTai) {
                            case "0"://0???
//                                rbHeaterPumpMode.setChecked(false);
//                                rbHeaterPumpMode.setVisibility(View.VISIBLE);
                                tvShuibeng.setTextColor(mContext.getResources().getColor(R.color.white));
                                shuiBengValue = "0";
                                if (whatUWant.equals(SHUIBENGGUANJI)) {
                                    whatUWant = "";
                                    lordingDialog.dismiss();
                                }

                                break;
                            case "1"://1???
//                                rbHeaterPumpMode.setChecked(true);
//                                rbHeaterPumpMode.setVisibility(View.VISIBLE);
                                tvShuibeng.setTextColor(mContext.getResources().getColor(R.color.blue00fff));
                                shuiBengValue = "1";
                                if (kongTiaoMoshiValue.equals("0") && dangWeiMoShiValue.equals("0") && tongFengValue.equals("0") && bengYouValue.equals("0")) {
                                    tvShebeizhuangtai.setText("???????????????" + "????????????");
                                }

                                if (whatUWant.equals(SHUIBENGKAIJI)) {
                                    whatUWant = "";
                                    lordingDialog.dismiss();
                                }


                                break;
                            case "a"://???
//                                flag = false;
//                                rbHeaterPumpMode.setVisibility(View.GONE);
                                shuiBengValue = "a";
                                tvShuibeng.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);


                                break;
                        }
                    }


                    String worktime = "1";

                    if (messageData.length() >= 44) {
                        worktime = messageData.substring(38, 44);        // ???????????????:????????????(??????)
                      //  worktime = worktime.replaceFirst("0", "");
                        tvGongzuoShichang.setText(worktime + "???");
                    }


                    if (messageData.length() >= 46) {
                        // ????????????????????????->01???18	2	???????????????
                        String zhu_car_stoppage_no_o = messageData.substring(44, 46);
                        zhu_car_stoppage_no_o = 0 <= zhu_car_stoppage_no_o.indexOf("a") ? "" : String.valueOf(Integer.parseInt(zhu_car_stoppage_no_o));


                    }

                    if (messageData.length() > 46) {
                        String daqiya = messageData.substring(46, 49);
                        tvDaqiya.setText(Y.getInt(daqiya) + "kpa");

                        String haiBaGaoDu = messageData.substring(49, 53);
                        tvHaibagaodu.setText(Y.getInt(haiBaGaoDu) + "m");

                        String hanYangLiang = messageData.substring(53);
                        tvHanyangliang.setText(Y.getInt(hanYangLiang) + "g/m3");
                    }


                } else if (message.type == ConstanceValue.MSG_CAR_K) {

                } else if (message.type == ConstanceValue.MSG_CAR_I) {

                    //???????????????
                    Log.i("msg_car_i", message.content.toString());
                    String message1 = message.content.toString();
                    version = message1.substring(message1.indexOf("i") + 7, message1.indexOf("i") + 11);


                    /**
                     *     int MSG_MQTT_CONNECTCOMPLETE = 0x10045;//????????????
                     *     int MSG_MQTT_CONNECTLOST = 0x10046;//????????????
                     *     int MSG_MQTT_CONNECTARRIVE = 0x10047;//????????????
                     *     int MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS = 0x10048;//????????????
                     *     int MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE = 0x10049;//????????????
                     */
                } else if (message.type == MSG_MQTT_CONNECTCOMPLETE) {

                    if (waitDialog.isShowing()) {
                        waitDialog.dismiss();
                        //  setMqttZhiLing();
                    }
                    Log.i("rair", "complete");

                } else if (message.type == MSG_MQTT_CONNECTLOST) {

                    if (!waitDialog.isShowing()) {
                        waitDialog.show();
                    }
                    Log.i("rair", "complete_lost");
                } else if (message.type == MSG_MQTT_CONNECTARRIVE) {

                    if (waitDialog.isShowing()) {
                        waitDialog.dismiss();
                    }
                    Log.i("rair", "arrive");
                } else if (message.type == MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS) {

                    if (waitDialog.isShowing()) {
                        waitDialog.dismiss();
                    }

                    Log.i("rair", "chonglian_success");

                } else if (message.type == MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE) {

                    if (!waitDialog.isShowing()) {
                        waitDialog.show();
                    }
                    Log.i("rair", "chonglian_failer");
                } else if (message.type == ConstanceValue.MSG_K6111) {
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    }, ConfigValue.YANCHI);//3????????????Runnable??????run??????

                } else if (message.type == ConstanceValue.MSG_K6141) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                            lordingDialog.dismiss();
                        }
                    }, ConfigValue.YANCHI);//3????????????Runnable??????run??????
                } else if (message.type == ConstanceValue.MSG_K6161) {


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);

                        }
                    }, ConfigValue.YANCHI);//3????????????Runnable??????run??????
                } else if (message.type == ConstanceValue.MSG_ZHILINGMA) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lordingDialog.dismiss();
                        }
                    }, ConfigValue.YANCHI);//3????????????Runnable??????run??????

                } else if (message.type == ConstanceValue.MSG_N9_WEILIANJIE) {


                    TishiDialog tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {
                            whatUWant = "";
                            lordingDialog.dismiss();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            whatUWant = PANDUANZAIXIANZHUAGNTAI;

                            if (n9Thread != null) {
                                whatUWant = PANDUANZAIXIANZHUAGNTAI;
                                n9Thread.interrupt();
                                n9Thread = new N9Thread();
                                n9Thread.start();
                            }

                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });

                    tishiDialog.setTextTitle("???????????????????????????");
                    tishiDialog.setTextContent("????????????????????????1??????????????????????????? 2?????????????????????????????? 3???????????????????????? 4????????????????????????????????????????????????????????????????????????");
                    tishiDialog.setTextConfirm("??????");
                    tishiDialog.setTextCancel("??????");
                    tishiDialog.show();

                    tvZaixian.setText("??????");

                    whatUWant = "";
                    n9Thread.interrupt();

                } else if (message.type == ConstanceValue.MSG_N9_LIANJIE) {

                } else if (message.type == ConstanceValue.MSG_DANGWEIGUANJI) {
                    lordingDialog.dismiss();
                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {
                            tishiDialog.dismiss();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {

                            finish();
                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });

                    tishiDialog.setTextTitle("??????");
                    tishiDialog.setTextContent("???????????????????????????????????????????????????????????????????????????????????????");
                    tishiDialog.setCancelable(false);
                    FengNuanActivity.this.tishiDialog.setTextConfirm("????????????");
                    tishiDialog.setTextCancel("??????");

                    tishiDialog.show();

                    tvZaixian.setText("??????");


                    whatUWant = "";
                    if (dangWeiGuanJiThread != null) {
                        dangWeiGuanJiThread.interrupt();
                    }

                } else if (message.type == ConstanceValue.MSG_DANGWEIKAIJI) {
                    lordingDialog.dismiss();

                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {
                            tishiDialog.dismiss();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            finish();

                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });

                    tishiDialog.setTextTitle("??????");
                    tishiDialog.setTextContent("???????????????????????????????????????????????????????????????????????????????????????");
                    tishiDialog.setCancelable(false);
                    FengNuanActivity.this.tishiDialog.setTextConfirm("????????????");
                    tishiDialog.setTextCancel("??????");

                    tishiDialog.show();

                    tvZaixian.setText("??????");


                    whatUWant = "";
                    if (dangWeiKaiJiThread != null) {
                        dangWeiKaiJiThread.interrupt();
                    }
                } else if (message.type == ConstanceValue.MSG_KONGTIAOKAIJI) {

                    lordingDialog.dismiss();

                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {
                            tishiDialog.dismiss();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            finish();

                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });

                    tishiDialog.setTextTitle("??????");
                    tishiDialog.setTextContent("???????????????????????????????????????????????????????????????????????????????????????");
                    tishiDialog.setCancelable(false);
                    FengNuanActivity.this.tishiDialog.setTextConfirm("????????????");
                    tishiDialog.setTextCancel("??????");

                    tishiDialog.show();

                    tvZaixian.setText("??????");


                    whatUWant = "";
                    if (dangWeiKaiJiThread != null) {
                        dangWeiKaiJiThread.interrupt();
                    }
                } else if (message.type == ConstanceValue.MSG_KONGTIAOGUANJI) {
                    lordingDialog.dismiss();

                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {
                            tishiDialog.dismiss();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            finish();

                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });

                    tishiDialog.setTextTitle("??????");
                    tishiDialog.setTextContent("???????????????????????????????????????????????????????????????????????????????????????");
                    tishiDialog.setCancelable(false);
                    FengNuanActivity.this.tishiDialog.setTextConfirm("????????????");
                    tishiDialog.setTextCancel("??????");

                    tishiDialog.show();

                    tvZaixian.setText("??????");


                    whatUWant = "";
                    if (kongTiaoGuanJiThread != null) {
                        kongTiaoGuanJiThread.interrupt();
                    }
                } else if (message.type == ConstanceValue.MSG_C_P) {

                    UIHelper.ToastMessage(mContext, message.content.toString());
                } else if (message.type == ConstanceValue.MSG_YUTONGFENGKAIJI) {

                    lordingDialog.dismiss();

                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {
                            tishiDialog.dismiss();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            finish();

                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });

                    tishiDialog.setTextTitle("??????");
                    tishiDialog.setTextContent("???????????????????????????????????????????????????????????????????????????????????????");
                    tishiDialog.setCancelable(false);
                    FengNuanActivity.this.tishiDialog.setTextConfirm("????????????");
                    tishiDialog.setTextCancel("??????");

                    tishiDialog.show();

                    tvZaixian.setText("??????");
                    whatUWant = "";
                    if (yuTongFengKaiJiThread != null) {
                        yuTongFengKaiJiThread.interrupt();
                    }
                } else if (message.type == ConstanceValue.MSG_YUTONGFENGGUANJI) {

                    if (tishiDialog != null) {
                        if (tishiDialog.isShowing()) {
                            tishiDialog.dismiss();
                        }
                    }

                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {
                            tishiDialog.dismiss();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            finish();

                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });

                    tishiDialog.setTextTitle("??????");
                    tishiDialog.setTextContent("???????????????????????????????????????????????????????????????????????????????????????");
                    tishiDialog.setCancelable(false);
                    FengNuanActivity.this.tishiDialog.setTextConfirm("????????????");
                    tishiDialog.setTextCancel("??????");

                    tishiDialog.show();

                    tvZaixian.setText("??????");


                    whatUWant = "";
                    if (kongTiaoGuanJiThread != null) {
                        kongTiaoGuanJiThread.interrupt();
                    }

                } else if (message.type == ConstanceValue.MSG_BEGNYOUKAIJI) {
                    if (lordingDialog != null) {
                        if (lordingDialog.isShowing()) {
                            lordingDialog.dismiss();
                        }
                    }
                    if (tishiDialog != null) {
                        if (tishiDialog.isShowing()) {
                            tishiDialog.dismiss();
                        }
                    }

                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {
                            tishiDialog.dismiss();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            finish();

                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });

                    tishiDialog.setTextTitle("??????");
                    tishiDialog.setTextContent("???????????????????????????????????????????????????????????????????????????????????????");
                    tishiDialog.setCancelable(false);
                    FengNuanActivity.this.tishiDialog.setTextConfirm("????????????");
                    tishiDialog.setTextCancel("??????");

                    tishiDialog.show();

                    tvZaixian.setText("??????");


                    whatUWant = "";
                    if (bengYouKaiJiThread != null) {
                        bengYouKaiJiThread.interrupt();
                    }
                } else if (message.type == ConstanceValue.MSG_BENGYOUGUANJI) {

                    if (lordingDialog != null) {
                        if (lordingDialog.isShowing()) {
                            lordingDialog.dismiss();
                        }
                    }
                    if (tishiDialog != null) {
                        if (tishiDialog.isShowing()) {
                            tishiDialog.dismiss();
                        }
                    }

                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {
                            tishiDialog.dismiss();
                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            finish();

                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });

                    tishiDialog.setTextTitle("??????");
                    tishiDialog.setTextContent("???????????????????????????????????????????????????????????????????????????????????????");
                    tishiDialog.setCancelable(false);
                    FengNuanActivity.this.tishiDialog.setTextConfirm("????????????");
                    tishiDialog.setTextCancel("??????");

                    tishiDialog.show();

                    tvZaixian.setText("??????");


                    whatUWant = "";
                    if (bengYouGuanJiThread != null) {
                        bengYouGuanJiThread.interrupt();
                    }
                } else if (message.type == ConstanceValue.MSG_CAR_Z) {
                    //Z2201802012


                    if (StringUtils.isEmpty(banBenHao)) {
                        String str = String.valueOf(message.content);

                        if (str != null) {
                            if (str.length() > 3) {
                                banBenHao = str.substring(2, 6);
                                Log.i("banbenhao", banBenHao);

                                if (banBenHao != null) {
                                    if (banBenHao.equals("2020")) {
                                        if (xunHuanN == null) {
                                            Log.i("xunhuancishu", "??????????????????");
                                            //  UIHelper.ToastMessage(mContext, "xunhuan xunhuan");
                                            xunHuanNFlag = true;
                                            xunHuanN = new XunHuanN();
                                            xunHuanN.start();
                                        }
                                    }

                                }
                            }
                        }

                    }


                }
            }
        }));
    }

    String banBenHao = null;

    @Override
    public int getContentViewResId() {
        return R.layout.layout_fengnuan;
    }

    /**
     * ????????????Activty????????????Activity
     *
     * @param context simKaIdFlag 1??? 2??????
     */
    public static void actionStart(Context context, String simKaIdFlag) {
        Intent intent = new Intent(context, FengNuanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("simKaIdFlag", simKaIdFlag);
        context.startActivity(intent);
    }


    public void setMqttZhiLing() {

        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(CAR_NOTIFY)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "notify:  " + CAR_NOTIFY + "CAR_NOTIFY ???????????????????????????");


                MyApplication.mqttDingyue.add(CAR_NOTIFY);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;????????????");
            }
        });


//        AndMqtt.getInstance().publish(new MqttPublish()
//                .setMsg("P.")
//                .setQos(2).setRetained(false)
//                .setTopic(CAR_CTROL), new IMqttActionListener() {
//            @Override
//            public void onSuccess(IMqttToken asyncActionToken) {
//                Log.i("Rair", "(CAR_NOTIFY.java:79)-onSuccess:-&gt;????????????" + "k001 ???????????????????????????");
//
//            }
//
//            @Override
//            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
//            }
//        });

        //??????????????????????????????????????????
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(CARBOX_GETNOW)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "???????????? carbox_getnow:  " + CARBOX_GETNOW + " CARBOX_GETNOW ???????????????????????????");
                MyApplication.mqttDingyue.add(CARBOX_GETNOW);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:68)-onFailure:-&gt;????????????");
            }
        });


        //????????????
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(CAR_CTROL)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", " Rair??????????????????" + "wit/app/" + of_user_id + " CAR_CTROL ???????????????????????????");
                MyApplication.mqttDingyue.add(CAR_CTROL);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:68)-onFailure:-&gt;????????????");
            }


        });


        whatUWant = PANDUANZAIXIANZHUAGNTAI;
        n9Thread = new N9Thread();
        n9Thread.start();


//        XunHuanN xunHuanN = new XunHuanN();
//        xunHuanN.start();
        //????????????????????????
        // requestData();

        // HeaterMqttService.mqttService.publish("M512.", HeaterMqttService.TOPIC_SERVER_ORDER, 2, false);
//        AndMqtt.getInstance().publish(new MqttPublish()
//                .setMsg("M512.")
//                .setQos(2).setRetained(false)
//                .setTopic(CAR_CTROL), new IMqttActionListener() {
//            @Override
//            public void onSuccess(IMqttToken asyncActionToken) {
//                Log.i("Rair", "(CAR_NOTIFY.java:79)-onSuccess:-&gt;????????????" + "M512 ???????????????????????????");
//            }
//
//            @Override
//            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        whatUWant = "";
        if (n9Thread != null) {
            n9Thread.interrupt();
        }
        if (dangWeiGuanJiThread != null) {
            dangWeiGuanJiThread.interrupt();
        }

        if (dangWeiKaiJiThread != null) {
            dangWeiKaiJiThread.interrupt();
        }

        if (kongTiaoKaiJiThread != null) {
            kongTiaoKaiJiThread.interrupt();
        }
        if (kongTiaoGuanJiThread != null) {
            kongTiaoGuanJiThread.interrupt();
        }

        if (yuTongFengGuanJiThread != null) {
            yuTongFengGuanJiThread.interrupt();
        }
        if (yuTongFengKaiJiThread != null) {
            yuTongFengKaiJiThread.interrupt();
        }

        if (bengYouKaiJiThread != null) {
            bengYouKaiJiThread.interrupt();
        }

        if (bengYouGuanJiThread != null) {
            bengYouGuanJiThread.interrupt();
        }
        xunHuanNFlag = false;
        if (xunHuanN != null) {
            xunHuanN.interrupt();
        }
        PreferenceHelper.getInstance(mContext).removeKey(App.CHOOSE_KONGZHI_XIANGMU);
        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CAR_NOTIFY), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;??????????????????");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;??????????????????");
            }
        });

        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CARBOX_GETNOW), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;??????????????????");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;??????????????????");
            }
        });

        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CAR_CTROL), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;??????????????????");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;??????????????????");
            }
        });


        for (int i = 0; i < MyApplication.mqttDingyue.size(); i++) {
            if (MyApplication.mqttDingyue.get(i).equals(CAR_NOTIFY)) {
                MyApplication.mqttDingyue.remove(i);
            }
        }

        for (int i = 0; i < MyApplication.mqttDingyue.size(); i++) {
            if (MyApplication.mqttDingyue.get(i).equals(CARBOX_GETNOW)) {
                MyApplication.mqttDingyue.remove(i);
            }
        }

        for (int i = 0; i < MyApplication.mqttDingyue.size(); i++) {
            if (MyApplication.mqttDingyue.get(i).equals(CAR_CTROL)) {
                MyApplication.mqttDingyue.remove(i);
            }
        }
//        handler.removeCallbacks(myRunnable);
//        run = false;
    }

    @Override
    public boolean onLongClick(View v) {

        switch (v.getId()) {
            case R.id.iv_dangweimoshi:
//                if (run==true){
//                    run = false;
//                    handler.removeCallbacks(myRunnable);
//                }

                if (kongTiaoMoshiValue.equals("1") || bengYouValue.equals("1") || tongFengValue.equals("1")) {
                    UIHelper.ToastMessage(mContext, "???????????????????????????????????????");
                    return false;
                }
                if (dangWeiMoShiValue.equals("0")) {
                    whatUWant = DANGWEIKAIJI;
                    dangWeiKaiJiThread = new DangWeiKaiJiThread();
                    dangWeiKaiJiThread.start();

                } else if (dangWeiMoShiValue.equals("1")) {
                    whatUWant = DANGWEIGUANJI;

                    dangWeiGuanJiThread = new DangWeiGuanJiThread();
                    dangWeiGuanJiThread.start();

                }
                break;
            case R.id.iv_kongtiaomoshi:
//                if (run==true){
//                    run = false;
//                    handler.removeCallbacks(myRunnable);
//                }
                if (dangWeiMoShiValue.equals("1") || bengYouValue.equals("1") || tongFengValue.equals("1")) {
                    UIHelper.ToastMessage(mContext, "???????????????????????????????????????");
                    return false;
                }
                if (kongTiaoMoshiValue.equals("0")) {
                    whatUWant = KONGTIAOKAIJI;
                    kongTiaoKaiJiThread = new KongTiaoKaiJiThread();
                    kongTiaoKaiJiThread.start();

                } else if (kongTiaoMoshiValue.equals("1")) {

                    whatUWant = KONGTIAOGUANJI;
                    kongTiaoGuanJiThread = new KongTiaoGuanJiThread();
                    kongTiaoGuanJiThread.start();


                }

                break;
        }


        return true;
    }

    private void qieHuanDangWei(int dangValue) {

        if (dangQianDangWei.equals(String.valueOf(dangValue))) {
            return;
        }
        tvShedingWenduOrDangwei.setText("???????????????" + String.valueOf(dangValue) + "???");

        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M62" + dangValue + ".")
                .setQos(2)
                .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                lordingDialog.setTextMsg("???????????????,?????????...");
                lordingDialog.show();
                PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k62" + dangValue + "1.");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                UIHelper.ToastMessage(mContext, "??????????????????:" + exception.getMessage(), Toast.LENGTH_SHORT);

            }
        });

    }

    private void qieHuanWenDu(int myValue) {
        tvShedingWenduOrDangwei.setText("???????????????" + myValue + "???");
        if (myValue > 0 && myValue <= 9) {
            String value = String.valueOf(myValue);
            Log.i("myValue", value);
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg("M65" + value + ".")
                    .setQos(2)
                    .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                    PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k66" + value + "1.");
                    lordingDialog.setTextMsg("???????????????,?????????");
                    lordingDialog.show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????" + exception.getMessage());
                }
            });
        } else if (myValue > 9 && myValue < 20) {
            String value = String.valueOf(myValue).substring(1, 2);
            Log.i("myValue", value);
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg("M66" + value + ".")
                    .setQos(2)
                    .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                    PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k66" + value + "1.");
                    lordingDialog.setTextMsg("???????????????,?????????");
                    lordingDialog.show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????" + exception.getMessage());
                }
            });

        } else if (myValue > 20 && myValue < 30) {
            String value = String.valueOf(myValue).substring(1, 2);
            Log.i("myValue", value);
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg("M67" + value + ".")
                    .setQos(2)
                    .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                    PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k67" + value + "1.");
                    lordingDialog.setTextMsg("???????????????,?????????");
                    lordingDialog.show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????" + exception.getMessage());
                }
            });
        } else if (myValue > 20 && myValue <= 32) {
            String value = String.valueOf(myValue).substring(1, 2);
            Log.i("myValue", value);
            AndMqtt.getInstance().publish(new MqttPublish()
                    .setMsg("M68" + value + ".")
                    .setQos(2)
                    .setTopic(CAR_CTROL).setRetained(false), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                    PreferenceHelper.getInstance(mContext).putString(ConfigValue.ZHILINGMA, "k68" + value + "1.");
                    lordingDialog.setTextMsg("???????????????,?????????");
                    lordingDialog.show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????" + exception.getMessage());
                }
            });
        }
    }


    private class N9Thread extends Thread {
        private int i = 0;

        public void run() {
            while (whatUWant.equals(PANDUANZAIXIANZHUAGNTAI)) {

                try {
                    if (i == 6) {

                        whatUWant = "";
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_N9_WEILIANJIE;
                        RxBus.getDefault().sendRx(n);

                    }
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("N.")
                            .setQos(2)
                            .setTopic(CAR_CTROL)
                            .setRetained(false), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????" + " N9 ???????????????????????????");


                            //  UIHelper.ToastMessage(mContext, "???" + String.valueOf(i) + "?????????");
                            i = i + 1;


                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                        }
                    });

                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }

    private class DangWeiGuanJiThread extends Thread {
        private int i = 0;

        public void run() {
            while (whatUWant.equals(DANGWEIGUANJI)) {

                try {
                    if (i == 3) {

                        whatUWant = "";
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_DANGWEIGUANJI;
                        RxBus.getDefault().sendRx(n);

                    }

                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M613.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                            //  UIHelper.ToastMessage(WindHeaterActivity.this, "??????????????????,?????????????????????", Toast.LENGTH_SHORT);


                            if (i == 0) {
                                //   lordingDialog.setTextMsg("?????????????????????...");
                                //   lordingDialog.show();
                                SoundPoolUtils.soundPool(mContext, R.raw.guanjizhong);

                                ivKaiji.setVisibility(View.GONE);
                                ivGuanji.setVisibility(View.VISIBLE);

                                ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
                                ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);

                                tvYutongfeng.setTextColor(mContext.getResources().getColor(R.color.gray999999));
                                tvZidongbengyou.setTextColor(mContext.getResources().getColor(R.color.gray999999));

                            }
                            //UIHelper.ToastMessage(mContext, "???" + String.valueOf(i) + "?????????");
                            i = i + 1;

                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            UIHelper.ToastMessage(mContext, "????????????????????????????????????????????????", Toast.LENGTH_SHORT);
                        }
                    });


                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }


    private class DangWeiKaiJiThread extends Thread {
        private int i = 0;

        public void run() {
            while (whatUWant.equals(DANGWEIKAIJI)) {

                try {
                    if (i == 3) {

                        whatUWant = "";
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_DANGWEIKAIJI;
                        RxBus.getDefault().sendRx(n);

                    }
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M61" + "1" + ".")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                            //UIHelper.ToastMessage(WindHeaterActivity.this, "??????????????????,?????????????????????", Toast.LENGTH_SHORT);

                            if (i == 0) {
                                UIHelper.ToastMessage(mContext, "?????????????????????");
//                                lordingDialog.setTextMsg("?????????" + "????????????" + "???????????????...");
//                                lordingDialog.show();
                                SoundPoolUtils.soundPool(mContext, R.raw.dangwei);

                                ivKaiji.setVisibility(View.VISIBLE);
                                ivGuanji.setVisibility(View.GONE);

                                ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_sel);
                                ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);

                                tvZuidiwendu.setText("1???");
                                tvZuigaowendu.setText("5???");

                                dangWeiMoShiValue = "1";


                                seekBar1.setVisibility(View.VISIBLE);
                                seekBarKongtiao.setVisibility(View.GONE);
                            }

                            //UIHelper.ToastMessage(mContext, "???" + String.valueOf(i) + "?????????");
                            i = i + 1;


                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            UIHelper.ToastMessage(mContext, "??????????????????", Toast.LENGTH_SHORT);
                        }
                    });
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }


    private class KongTiaoKaiJiThread extends Thread {
        private int i = 0;

        public void run() {
            while (whatUWant.equals(KONGTIAOKAIJI)) {

                try {
                    if (i == 3) {

                        whatUWant = "";
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_KONGTIAOKAIJI;
                        RxBus.getDefault().sendRx(n);

                    }
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M61" + "2" + ".")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                            //UIHelper.ToastMessage(WindHeaterActivity.this, "??????????????????,?????????????????????", Toast.LENGTH_SHORT);

                            if (i == 0) {
                                //  lordingDialog.setTextMsg("?????????" + "????????????" + "???????????????...");
                                //  lordingDialog.show();
                                SoundPoolUtils.soundPool(mContext, R.raw.kongtiao);

                                ivKaiji.setVisibility(View.VISIBLE);
                                ivGuanji.setVisibility(View.GONE);

                                seekBar1.setVisibility(View.GONE);
                                seekBarKongtiao.setVisibility(View.VISIBLE);
                                tvZuidiwendu.setText("0???");
                                tvZuigaowendu.setText("32???");
                                tvShebeizhuangtai.setText("???????????????????????????");
                                ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
                                ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_sel);
                            }

                            //UIHelper.ToastMessage(mContext, "???" + String.valueOf(i) + "?????????");
                            i = i + 1;


                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            UIHelper.ToastMessage(mContext, "??????????????????", Toast.LENGTH_SHORT);
                        }
                    });
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }


    private class KongTiaoGuanJiThread extends Thread {
        private int i = 0;

        public void run() {
            while (whatUWant.equals(KONGTIAOGUANJI)) {

                try {
                    if (i == 3) {

                        whatUWant = "";
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_KONGTIAOGUANJI;
                        RxBus.getDefault().sendRx(n);

                    }
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M613.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                            //  UIHelper.ToastMessage(WindHeaterActivity.this, "??????????????????,?????????????????????", Toast.LENGTH_SHORT);


                            if (i == 0) {
                                //  lordingDialog.setTextMsg("?????????????????????...");
                                //  lordingDialog.show();
                                whatUWant = KONGTIAOGUANJI;
                                SoundPoolUtils.soundPool(mContext, R.raw.guanjizhong);

                                ivKaiji.setVisibility(View.GONE);
                                ivGuanji.setVisibility(View.VISIBLE);

                                ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
                                ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);
                                tvYutongfeng.setTextColor(mContext.getResources().getColor(R.color.gray999999));
                                tvZidongbengyou.setTextColor(mContext.getResources().getColor(R.color.gray999999));

                            }

                            i = i + 1;
                            //UIHelper.ToastMessage(mContext, "???" + String.valueOf(i) + "?????????");
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            UIHelper.ToastMessage(mContext, "????????????????????????????????????????????????", Toast.LENGTH_SHORT);
                        }
                    });
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }


    private class YuTongFengKaiJiThread extends Thread {
        private int i = 0;

        public void run() {
            while (whatUWant.equals(YUTONGFENGKAIJI)) {

                try {
                    if (i == 3) {

                        whatUWant = "";
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_YUTONGFENGKAIJI;
                        RxBus.getDefault().sendRx(n);

                    }
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M61" + "7" + ".")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                            //UIHelper.ToastMessage(WindHeaterActivity.this, "??????????????????,?????????????????????", Toast.LENGTH_SHORT);
//                            lordingDialog.setTextMsg("???????????????????????????...");
//                            lordingDialog.show();
//                            SoundPoolUtils.soundPool(mContext, R.raw.yutongfeng);
//                            whatUWant = YUTONGFENGKAIJI;

                            if (i == 0) {

                                SoundPoolUtils.soundPool(mContext, R.raw.yutongfeng);
                                whatUWant = YUTONGFENGKAIJI;

                                ivKaiji.setVisibility(View.GONE);
                                ivGuanji.setVisibility(View.VISIBLE);

                                ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
                                ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);

                                tvYutongfeng.setTextColor(mContext.getResources().getColor(R.color.gray999999));
                                tvZidongbengyou.setTextColor(mContext.getResources().getColor(R.color.gray999999));

                                SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                                //  SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                                //???????????????
                                tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                                    @Override
                                    public void onClickCancel(View v, TishiDialog dialog) {

                                    }

                                    @Override
                                    public void onClickConfirm(View v, TishiDialog dialog) {
                                        if (tongFengValue.equals("1")) {
                                            //???????????????
                                            tishiDialog.setTextConfirm("????????????...");
                                            whatUWant = YUTONGFENGGUANJI;
                                            yuTongFengGuanJiThread = new YuTongFengGuanJiThread();
                                            yuTongFengGuanJiThread.start();

                                        }

                                    }

                                    @Override
                                    public void onDismiss(TishiDialog dialog) {

                                    }
                                });
                                tishiDialog.setTextTitle("????????????????????????");

                                tishiDialog.setTextCancel("");
                                tishiDialog.setDismissAfterClick(false);
                                tishiDialog.setTextContent("????????????");
                                tishiDialog.setTextConfirm("?????????");
                                tishiDialog.setCancelable(true);
                                tishiDialog.show();

                            }
                            i = i + 1;
                            //UIHelper.ToastMessage(mContext, "???" + String.valueOf(i) + "?????????");

                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            UIHelper.ToastMessage(mContext, "??????????????????", Toast.LENGTH_SHORT);
                        }
                    });
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }

    private class YuTongFengGuanJiThread extends Thread {
        private int i = 0;

        public void run() {
            while (whatUWant.equals(YUTONGFENGGUANJI)) {

                try {
                    if (i == 3) {
                        whatUWant = "";
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_YUTONGFENGGUANJI;
                        RxBus.getDefault().sendRx(n);
                    }
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M613.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");

                            if (i == 0) {
                                ivKaiji.setVisibility(View.GONE);
                                ivGuanji.setVisibility(View.VISIBLE);

                                ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
                                ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);

                                tvYutongfeng.setTextColor(mContext.getResources().getColor(R.color.gray999999));
                                tvZidongbengyou.setTextColor(mContext.getResources().getColor(R.color.gray999999));


                            }
                            i = i + 1;
                            //UIHelper.ToastMessage(mContext, "???" + String.valueOf(i) + "?????????");


                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            UIHelper.ToastMessage(mContext, "????????????????????????????????????????????????", Toast.LENGTH_SHORT);
                        }
                    });
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }

    private class BengYouKaiJiThread extends Thread {
        private int i = 0;

        public void run() {
            while (whatUWant.equals(BENGYOUKAIJI)) {

                try {
                    if (i == 3) {
                        whatUWant = "";
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_BEGNYOUKAIJI;
                        RxBus.getDefault().sendRx(n);
                    }
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M61" + "6" + ".")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                            //UIHelper.ToastMessage(WindHeaterActivity.this, "??????????????????,?????????????????????", Toast.LENGTH_SHORT);
                            if (i == 0) {
                                SoundPoolUtils.soundPool(mContext, R.raw.yubengyou);
                                whatUWant = BENGYOUKAIJI;

                                ivKaiji.setVisibility(View.GONE);
                                ivGuanji.setVisibility(View.VISIBLE);

                                ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
                                ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);

                                tvYutongfeng.setTextColor(mContext.getResources().getColor(R.color.gray999999));
                                tvZidongbengyou.setTextColor(mContext.getResources().getColor(R.color.gray999999));


                                //  SoundPoolUtils.soundPool(mContext, R.raw.yikaiji);
                                //????????????
                                tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                                    @Override
                                    public void onClickCancel(View v, TishiDialog dialog) {

                                    }

                                    @Override
                                    public void onClickConfirm(View v, TishiDialog dialog) {
                                        if (bengYouValue.equals("1")) {
                                            //????????????
                                            tishiDialog.setTextConfirm("????????????...");
                                            whatUWant = BENGYOUGUANJI;
                                            bengYouGuanJiThread = new BengYouGuanJiThread();
                                            bengYouGuanJiThread.start();
                                        }

                                    }

                                    @Override
                                    public void onDismiss(TishiDialog dialog) {

                                    }
                                });
                                tishiDialog.setTextTitle("????????????????????????");
                                tishiDialog.setTextCancel("");
                                tishiDialog.setDismissAfterClick(false);
                                tishiDialog.setTextContent("????????????");
                                tishiDialog.setTextConfirm("????????????");
                                tishiDialog.setCancelable(true);
                                tishiDialog.show();
                            }
                            i = i + 1;
                            //UIHelper.ToastMessage(mContext, "???" + String.valueOf(i) + "?????????");
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            UIHelper.ToastMessage(mContext, "??????????????????", Toast.LENGTH_SHORT);
                        }
                    });
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }

    private class BengYouGuanJiThread extends Thread {
        private int i = 0;

        public void run() {
            while (whatUWant.equals(BENGYOUGUANJI)) {

                try {
                    if (i == 3) {
                        whatUWant = "";
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_BENGYOUGUANJI;
                        RxBus.getDefault().sendRx(n);

                    }
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("M613.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_CTROL), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????");
                            //  UIHelper.ToastMessage(WindHeaterActivity.this, "??????????????????,?????????????????????", Toast.LENGTH_SHORT);

                            if (i == 0) {
                                ivKaiji.setVisibility(View.GONE);
                                ivGuanji.setVisibility(View.VISIBLE);

                                ivDangweimoshi.setBackgroundResource(R.mipmap.fengnuan_button_dangwei_nor);
                                ivKongtiaomoshi.setBackgroundResource(R.mipmap.fengnuan_button_kongtiao_nor);

                                tvYutongfeng.setTextColor(mContext.getResources().getColor(R.color.gray999999));
                                tvZidongbengyou.setTextColor(mContext.getResources().getColor(R.color.gray999999));
                            }

                            i = i + 1;
                            // UIHelper.ToastMessage(mContext, "???" + String.valueOf(i) + "?????????");
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                            UIHelper.ToastMessage(mContext, "????????????????????????????????????????????????", Toast.LENGTH_SHORT);
                        }
                    });
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;

                }
            }
        }
    }


    private int xunHuanCiShu = 0;
    private boolean xunHuanNFlag = true;

    private class XunHuanN extends Thread {

        public void run() {
            while (xunHuanNFlag) {
//                UIHelper.ToastMessage(mContext,"??????????????????");
                //  Log.i("xunhuancishu", "???????????????" + xunHuanCiShu + "???");
                try {
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("N.")
                            .setQos(2)
                            .setTopic(CAR_CTROL)
                            .setRetained(false), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            //Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????" + " N9 ???????????????????????????");
                            xunHuanCiShu = xunHuanCiShu + 1;
                            Log.i("xunhuancishu", "???????????????" + xunHuanCiShu + "???");
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            //Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                        }
                    });
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }


    }

    @Override
    public boolean onSearchRequested() {
        return super.onSearchRequested();
    }
    //    private boolean run = true;
//    private int count = 0;
//
//    private Handler handler = new Handler();

//    private Runnable myRunnable = new Runnable() {
//        public void run() {
//
//            if (run) {
//                handler.postDelayed(this, 1000);
//                count++;
//
//            }
//            if (count % 5 == 0 || count == 1) {
//                AndMqtt.getInstance().publish(new MqttPublish()
//                        .setMsg("N.")
//                        .setQos(2)
//                        .setTopic(CAR_CTROL)
//                        .setRetained(false), new IMqttActionListener() {
//                    @Override
//                    public void onSuccess(IMqttToken asyncActionToken) {
//                        //Log.i("Rair", "(MainActivity.java:79)-onSuccess:-&gt;????????????" + " N9 ???????????????????????????");
//                        xunHuanCiShu = xunHuanCiShu + 1;
//                        // Log.i("xunhuancishu", "???????????????" + xunHuanCiShu + "???");
//                    }
//
//                    @Override
//                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                        //Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
//                    }
//                });
//            }
//
//
//           // UIHelper.ToastMessage(mContext, "???" + count + "?????????,?????????" + count % 10);
//
//
//        }
//    };


}
