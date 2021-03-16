package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.model.FenLeiContentModel;
import com.smarthome.magic.mqtt_zhiling.ZnjjMqttMingLing;
import com.smarthome.magic.util.DoMqttValue;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import androidx.annotation.Nullable;
import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.app.ConstanceValue.MSG_PEIWANG_SUCCESS;


public class WanNengYaoKongQiPeiDui extends BaseActivity {
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
    @BindView(R.id.iv_pic)
    ImageView ivPic;

    @BindView(R.id.iv_peidui_dianyuan)
    ImageView iv_peidui_dianyuan;
    @BindView(R.id.tv_peidui_dianyuan)
    TextView tv_peidui_dianyuan;
    @BindView(R.id.iv_peidui_yinliang_add)
    ImageView iv_peidui_yinliang_add;
    @BindView(R.id.tv_peidui_yinliang_add)
    TextView tv_peidui_yinliang_add;
    @BindView(R.id.iv_peidui_yinliang_jian)
    ImageView iv_peidui_yinliang_jian;
    @BindView(R.id.tv_peidui_yinliang_jian)
    TextView tv_peidui_yinliang_jian;
    @BindView(R.id.iv_peidui_pindao_add)
    ImageView iv_peidui_pindao_add;
    @BindView(R.id.tv_peidui_pindao_add)
    TextView tv_peidui_pindao_add;
    @BindView(R.id.iv_peidui_pindao_jian)
    ImageView iv_peidui_pindao_jian;
    @BindView(R.id.tv_peidui_pindao_jian)
    TextView tv_peidui_pindao_jian;

    private String zhuangZhiId;
    private String ccid;
    private String serverId;
    private FenLeiContentModel fenLeiContentModel;
    private String topic;
    private ZnjjMqttMingLing znjjMqttMingLing;


    @Override
    public int getContentViewResId() {
        return R.layout.layout_wanneng_yaokongqi_peidui_dianshi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("电视遥控器配网");
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.ZHINENGJIAJU);
        zhuangZhiId = getIntent().getStringExtra("zhuangZhiId");
        fenLeiContentModel = (FenLeiContentModel) getIntent().getSerializableExtra("fenLeiContentModel");

        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
        topic = "zn/hardware/" + serverId + ccid;
        znjjMqttMingLing.yaoKongQiPeiDui(topic, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("rair", "发送配电视命令" + topic);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
        znjjMqttMingLing.subscribeLastAppShiShiXinXi_WithCanShu(ccid, serverId, new IMqttActionListener() {
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

//        YaoKongQiGongNengModel yaoKongQiGongNengModel = new YaoKongQiGongNengModel();
//
//        for (int i = 0; i < 5; i++) {
//            yaoKongQiGongNengModel.id = String.valueOf(i);
//            switch (i) {
//                case 1:
//                    yaoKongQiGongNengModel.name = "电源";
//                    break;
//                case 2:
//                    yaoKongQiGongNengModel.name = "温度加";
//                    break;
//                case 3:
//                    yaoKongQiGongNengModel.name = "温度减";
//                    break;
//                case 4:
//                    yaoKongQiGongNengModel.name = "频道加";
//                    break;
//                case 5:
//                    yaoKongQiGongNengModel.name = "频道减";
//                    break;
//            }
//        }


        initHuidiao();
    }

    private boolean[] peiduiNum = {false, false, false, false, false};
    private boolean peiwang = false;

    private void initHuidiao() {
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
                    String wangNengPeiWangState = (String) message.content;
                    if (wangNengPeiWangState.equals("1")) {
                        Notice notice1 = new Notice();
                        notice1.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                        sendRx(notice1);

                        TishiDialog tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {
                                Notice notice = new Notice();
                                notice.type = MSG_PEIWANG_SUCCESS;
                                RxBus.getDefault().sendRx(notice);
                                finish();
                            }
                        });

                        tishiDialog.setTextContent("电视配网成功");
                        tishiDialog.setTextCancel("");
                        tishiDialog.setTextConfirm("确定");
                        tishiDialog.setCancelable(false);
                        tishiDialog.setCanceledOnTouchOutside(false);
                        tishiDialog.show();
                    } else if (wangNengPeiWangState.equals("2")) {
                        TishiDialog tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {
                                finish();
                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {
                                znjjMqttMingLing.yaoKongQiPeiDui(topic, new IMqttActionListener() {
                                    @Override
                                    public void onSuccess(IMqttToken asyncActionToken) {
                                        Log.i("rair", "发送配电视命令" + topic);
                                    }

                                    @Override
                                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                                    }
                                });
                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        tishiDialog.setTextContent("电视配网失败,是否重试？");
                        tishiDialog.setTextCancel("取消");
                        tishiDialog.setTextConfirm("重试");
                        tishiDialog.setCancelable(false);
                        tishiDialog.setCanceledOnTouchOutside(false);
                        tishiDialog.show();
                    }
                }
            }
        }));
    }

    private void yinLiangJia() {
        peiduiNum[1] = true;
        tv_peidui_yinliang_add.setTextColor(Y.getColor(R.color.color_main));
        iv_peidui_yinliang_add.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        check();
    }

    private void yinLiangJian() {
        peiduiNum[2] = true;
        tv_peidui_yinliang_jian.setTextColor(Y.getColor(R.color.color_main));
        iv_peidui_yinliang_jian.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        check();
    }

    private void pinDaoJia() {
        peiduiNum[3] = true;
        tv_peidui_pindao_add.setTextColor(Y.getColor(R.color.color_main));
        iv_peidui_pindao_add.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        check();
    }

    private void pinDaoJian() {
        peiduiNum[4] = true;
        tv_peidui_pindao_jian.setTextColor(Y.getColor(R.color.color_main));
        iv_peidui_pindao_jian.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        check();
    }

    private void dianYuanJian() {
        peiduiNum[0] = true;
        tv_peidui_dianyuan.setTextColor(Y.getColor(R.color.color_main));
        iv_peidui_dianyuan.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        check();
    }

    private void check() {
        if (!peiduiNum[0]) {
            return;
        }

        if (!peiduiNum[1]) {
            return;
        }

        if (!peiduiNum[2]) {
            return;
        }

        if (!peiduiNum[3]) {
            return;
        }

        if (!peiduiNum[4]) {
            return;
        }
        Y.t("配对成功了啊啊啊啊啊");
        Y.e("配对成功了啊啊啊啊啊");

        peiwang = true;
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

    public static void actionStart(Context context, FenLeiContentModel fenLeiContentModel) {
        Intent intent = new Intent(context, WanNengYaoKongQiPeiDui.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("fenLeiContentModel", fenLeiContentModel);
        context.startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceHelper.getInstance(mContext).removeKey(App.CHOOSE_KONGZHI_XIANGMU);
    }
}
