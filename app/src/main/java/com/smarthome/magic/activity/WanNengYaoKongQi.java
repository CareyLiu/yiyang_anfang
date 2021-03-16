package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.smarthome.magic.R;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.model.FenLeiContentModel;
import com.smarthome.magic.mqtt_zhiling.ZnjjMqttMingLing;
import com.smarthome.magic.util.DoMqttValue;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class WanNengYaoKongQi extends BaseActivity {
    @BindView(R.id.rl_top_view)
    RelativeLayout rlTopView;
    @BindView(R.id.rl_yinliangjia)
    RelativeLayout rlYinliangjia;
    @BindView(R.id.rl_yinliang_jia_mengban)
    RelativeLayout rlYinliangJiaMengban;
    @BindView(R.id.rl_yinliang)
    RelativeLayout rlYinliang;
    @BindView(R.id.rl_yinliangjian)
    RelativeLayout rlYinliangjian;
    @BindView(R.id.rl_yinliang_jian_mengban)
    RelativeLayout rlYinliangJianMengban;
    @BindView(R.id.rl_pindaojia)
    RelativeLayout rlPindaojia;
    @BindView(R.id.rl_pindao_jia_mengban)
    RelativeLayout rlPindaoJiaMengban;
    @BindView(R.id.rl_pindao)
    RelativeLayout rlPindao;
    @BindView(R.id.rl_pindaojian)
    RelativeLayout rlPindaojian;
    @BindView(R.id.rl_pindao_jian_mengban)
    RelativeLayout rlPindaoJianMengban;
    @BindView(R.id.iv_kaiguan_mengban)
    ImageView ivKaiguanMengban;
    private String zhuangZhiId;

    private String ccid;
    private String serverId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.ZHINENGJIAJU);
        zhuangZhiId = getIntent().getStringExtra("device_ccid");

        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        ZnjjMqttMingLing znjjMqttMingLing = new ZnjjMqttMingLing(mContext);

        String topic = "zn/hardware/" + serverId + ccid;
        znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(ccid, serverId, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

        znjjMqttMingLing.subscribeServerShiShiXinXi_WithCanShu(ccid, serverId, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });


        setAllWeiPeiDui();
        ivKaiguanMengban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mingLingMa = "M18" + zhuangZhiId + "01.";
                znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            }
        });
        rlYinliangjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //音量加
                String mingLingMa = "M18" + zhuangZhiId + "02.";
                znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            }
        });
        rlYinliangjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //音量减
                String mingLingMa = "M18" + zhuangZhiId + "03.";
                znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            }
        });

        rlPindaojia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //频道加

                String mingLingMa = "M18" + zhuangZhiId + "04.";
                znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            }
        });
        rlPindaojian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //频道减
                String mingLingMa = "M18" + zhuangZhiId + "05.";
                znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_WANNENGYAOKONGQIPEIWANG) {
                    // 01.电源键配对 02.音量加配对（温度加配对） 03.音量减配对（温度减配对） 04.频道加配对（风量加配对） 05.频道减配对（风量减配对）
                    String jianWei = (String) message.content;
                    switch (jianWei) {
                        case "01":
                            dianYuanJian();
                            break;
                        case "02":
                            yinLiangJia();
                            break;
                        case "03":
                            yinLiangJian();
                            break;
                        case "04":
                            pinDaoJia();
                            break;
                        case "05":
                            pinDaoJian();
                            break;
                    }
                } else if (message.type == ConstanceValue.MSG_WANNENGYAOKONGQICHENGGONGSHIBAI) {
                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
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
                    String wangNengPeiWangState = (String) message.content;
                    if (wangNengPeiWangState.equals("1")) {
                        tishiDialog.setTextContent("电视配网成功");
                    } else if (wangNengPeiWangState.equals("2")) {
                        tishiDialog.setTextContent("电视配网失败");
                    }
                    tishiDialog.setTextCancel("");
                    tishiDialog.show();
                }
            }
        }));
    }

    TishiDialog tishiDialog;

    @Override
    public int getContentViewResId() {
        return R.layout.layout_wanneng_yaokongqi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("电视遥控器");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setAllWeiPeiDui() {
        rlPindaoJiaMengban.setVisibility(View.GONE);
        rlPindaoJianMengban.setVisibility(View.GONE);
        rlYinliangJiaMengban.setVisibility(View.GONE);
        rlYinliangJianMengban.setVisibility(View.GONE);
        ivKaiguanMengban.setVisibility(View.GONE);

    }

    public void setAllYiPeiDui() {
        rlPindaoJiaMengban.setVisibility(View.VISIBLE);
        rlPindaoJianMengban.setVisibility(View.VISIBLE);
        rlYinliangJiaMengban.setVisibility(View.VISIBLE);
        rlYinliangJianMengban.setVisibility(View.VISIBLE);
        ivKaiguanMengban.setVisibility(View.VISIBLE);
    }

    public static void actionStart(Context context, String device_ccid) {
        Intent intent = new Intent(context, WanNengYaoKongQi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        context.startActivity(intent);
    }

    private void yinLiangJia() {
        rlYinliangJiaMengban.setVisibility(View.VISIBLE);
    }

    private void yinLiangJian() {
        rlYinliangJianMengban.setVisibility(View.VISIBLE);
    }

    private void pinDaoJia() {
        rlPindaoJiaMengban.setVisibility(View.VISIBLE);
    }

    private void pinDaoJian() {
        rlPindaoJianMengban.setVisibility(View.VISIBLE);
    }

    private void dianYuanJian() {
        ivKaiguanMengban.setVisibility(View.VISIBLE);
    }

    private void peiDuiFinish() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceHelper.getInstance(mContext).removeKey(App.CHOOSE_KONGZHI_XIANGMU);
    }
}
