package com.yiyang.cn.activity.zckt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.yiyang.cn.R;
import com.yiyang.cn.util.Y;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.dialog.newdia.TishiDialog;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ZcktStateActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.switch_button)
    ImageView switch_button;
    @BindView(R.id.tv_yinliang)
    TextView tv_yinliang;
    @BindView(R.id.seek_yinliang)
    SeekBar seek_yinliang;
    @BindView(R.id.tv_qianyazhi)
    TextView tv_qianyazhi;
    @BindView(R.id.seek_qianya)
    SeekBar seek_qianya;

    private String KT_Send = "zckt/cbox/hardware/11111111111111111111111";
    private String KT_Accept = "zckt/cbox/app/11111111111111111111111";
    private String KT_ccid;
    private boolean isZaixian;
    private String kt_mode;
    private String nowMingling;
    private String kt_lamp_dianhou;
    private String fengmenMode;
    private String kt_yinliang_dianhou;
    private String yinliang;
    private String kt_qianya_dianhou;
    private String qianyazhi;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_air_state;
    }

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    /**
     * ????????????Activty????????????Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ZcktStateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
        initHuidiao();
        registerKtMqtt();
    }

    private void init() {
        KT_ccid = PreferenceHelper.getInstance(this).getString("ccid", "");
        KT_Send = "zckt/cbox/hardware/" + KT_ccid;
        KT_Accept = "zckt/cbox/app/" + KT_ccid;

        switch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fengmenMode.equals("0")) {
                    lampOn();
                } else {
                    lampOff();
                }
            }
        });


        seek_yinliang.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                Y.e("?????????????????????" + progress);
                if (progress < 10) {
                    setYiling("0" + progress);
                } else {
                    setYiling("" + progress);
                }
            }
        });

        seek_qianya.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress() + 190;
                Y.e("?????????????????????" + progress);
                setQianya("0" + progress);
            }
        });
    }

    private void registerKtMqtt() {
        showProgressDialog("???????????????...");
        initHandlerStart();

        //????????????????????????????????????
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(KT_Send)
                .setQos(2), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
        //???????????????app?????????????????????
        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                .setTopic(KT_Accept)
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

    /**
     * ??????????????????????????????????????????????????????
     */
    private void getNs() {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M001.")
                .setQos(2).setRetained(false)
                .setTopic(KT_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    /**
     * ???????????????????????????
     */
    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ZCKT) {
                    String msg = message.content.toString();
                    getData(msg);
                }
            }
        }));
    }

    private void getData(String msg) {
        if (msg.contains("j_g")) {
            isZaixian = true;
            //???????????????
            Y.e("????????????????????????" + "    " + msg.toString());
            //?????????????????????
            String banbenhao = msg.substring(3, 6);
            //????????????     0.?????? 1.????????????2.????????????3.????????????4.????????????
            kt_mode = msg.substring(6, 7);
            //??????   0 1 2 3 4 5 6
            String kt_fengsu = msg.substring(7, 8);
            //????????????   05-35
            String kt_temperature = msg.substring(8, 10);
            //????????????   4???????????????   0???????????????
            fengmenMode = msg.substring(10, 11);
            //??????????????????190-230
            qianyazhi = msg.substring(11, 14);
            //????????????00-15
            yinliang = msg.substring(14, 16);
            //????????????00-50V
            String dianya = msg.substring(16, 18);
            //????????????-025=-25???   0026=26???
            String wendu_jinfeng = msg.substring(18, 22);
            //????????????-025=-25???   0026=26???
            String wendu_chufeng = msg.substring(22, 26);
            //??????????????????
            String compress_code = msg.substring(26, 28);
            //???????????????
            String panel_code = msg.substring(28, 30);
            //????????????
            String kt_lamp = msg.substring(30, 31);

            if (fengmenMode.equals("4")) {
                switch_button.setImageResource(R.mipmap.switch_open);
            } else {
                switch_button.setImageResource(R.mipmap.switch_close);
            }
        }
    }

    private void initHandlerStart() {
        Message message = handlerStart.obtainMessage(1);
        handlerStart.sendMessageDelayed(message, 1000);
    }

    private void initDengClick() {
        Message message = handlerStart.obtainMessage(2);
        handlerStart.sendMessageDelayed(message, 1000);
    }

    private void initYinliangClick() {
        Message message = handlerStart.obtainMessage(3);
        handlerStart.sendMessageDelayed(message, 1000);
    }

    private void initQianyaClick() {
        Message message = handlerStart.obtainMessage(4);
        handlerStart.sendMessageDelayed(message, 1000);
    }

    private long time;

    private Handler handlerStart = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            time++;
            switch (msg.what) {
                case 1:
                    if (!isZaixian) {
                        if (time >= 30) {
                            showTishiDialog();
                        } else {
                            if (time == 5 || time == 10 || time == 15 || time == 20 || time == 25) {
                                getNs();
                            }
                            initHandlerStart();
                        }
                    } else {
                        dismissProgressDialog();
                        time = 0;
                    }
                    break;
                case 2:
                    if (!fengmenMode.equals(kt_lamp_dianhou)) {
                        if (time >= 30) {
                            showTishiDialog();
                            if (fengmenMode.equals("4")) {
                                switch_button.setImageResource(R.mipmap.switch_open);
                            } else {
                                switch_button.setImageResource(R.mipmap.switch_close);
                            }
                        } else {
                            if (time == 5 || time == 10 || time == 15 || time == 20 || time == 25) {
                                sendDeng(nowMingling);
                            }
                            initDengClick();
                        }
                    } else {
                        dismissProgressDialog();
                        time = 0;
                    }
                    break;
                case 3:
                    if (!kt_yinliang_dianhou.equals(yinliang)) {
                        if (time >= 30) {
                            showTishiDialog();
                            tv_yinliang.setText("????????????:" + yinliang);
                        } else {
                            if (time == 5 || time == 10 || time == 15 || time == 20 || time == 25) {
                                sendYinliang(nowMingling);
                            }
                            initYinliangClick();
                        }
                    } else {
                        dismissProgressDialog();
                        time = 0;
                    }
                    break;
                case 4:
                    if (!kt_qianya_dianhou.equals(qianyazhi)) {
                        if (time >= 30) {
                            showTishiDialog();
                            tv_qianyazhi.setText("??????????????????:" + qianyazhi);
                        } else {
                            if (time == 5 || time == 10 || time == 15 || time == 20 || time == 25) {
                                sendQianya(nowMingling);
                            }
                            initQianyaClick();
                        }
                    } else {
                        dismissProgressDialog();
                        time = 0;
                    }
                    break;
            }
            Y.e(msg.what + "  ?????????????????????  " + time);
            return false;
        }
    });

    private void showTishiDialog() {
        isZaixian = false;
        time = 0;
        dismissProgressDialog();
        TishiDialog tishiDialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                registerKtMqtt();
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        tishiDialog.setTextTitle("???????????????????????????");
        tishiDialog.setTextContent("????????????????????????1:???????????????????????? 2:??????????????????????????? 3:????????????????????? 4:????????????????????????????????????????????????????????????????????????");
        tishiDialog.setTextConfirm("??????");
        tishiDialog.setTextCancel("??????");
        tishiDialog.show();
    }

    /**
     * ????????????
     */
    private void lampOn() {
        if (!isZaixian) {
            return;
        }

        if (kt_mode.equals("0")) {
            Y.t("?????????????????????????????????????????????");
            return;
        }

        switch_button.setImageResource(R.mipmap.switch_open);
        showProgressDialog("??????????????????...");
        nowMingling = "M054.";
        kt_lamp_dianhou = "4";
        initDengClick();
        sendDeng(nowMingling);
    }

    /**
     * ????????????
     */
    private void lampOff() {
        if (!isZaixian) {
            return;
        }

        if (kt_mode.equals("0")) {
            Y.t("?????????????????????????????????????????????");
            return;
        }

        switch_button.setImageResource(R.mipmap.switch_close);
        showProgressDialog("????????????...");
        nowMingling = "M050.";
        kt_lamp_dianhou = "0";
        initDengClick();
        sendDeng(nowMingling);
    }

    private void sendDeng(String nowMingling) {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(nowMingling)
                .setQos(2).setRetained(false)
                .setTopic(KT_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    /**
     * ????????????
     */
    private void setYiling(String pos) {
        if (!isZaixian) {
            return;
        }

        if (kt_mode.equals("0")) {
            Y.t("?????????????????????????????????????????????");
            return;
        }

        showProgressDialog("?????????...");
        nowMingling = "M07" + pos + ".";
        kt_yinliang_dianhou = pos;
        tv_yinliang.setText("????????????:" + kt_yinliang_dianhou);
        initYinliangClick();
        sendYinliang(nowMingling);
    }

    private void sendYinliang(String nowMingling) {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(nowMingling)
                .setQos(2).setRetained(false)
                .setTopic(KT_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    /**
     * ???????????????
     */
    private void setQianya(String qianya) {
        if (!isZaixian) {
            return;
        }

        if (kt_mode.equals("0")) {
            Y.t("?????????????????????????????????????????????");
            return;
        }

        showProgressDialog("?????????...");
        nowMingling = "M06" + qianya + ".";
        kt_qianya_dianhou = qianya;
        tv_qianyazhi.setText("??????????????????:" + kt_qianya_dianhou);
        initQianyaClick();
        sendQianya(nowMingling);
    }

    private void sendQianya(String nowMingling) {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(nowMingling)
                .setQos(2).setRetained(false)
                .setTopic(KT_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }
}
