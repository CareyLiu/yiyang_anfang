package com.smarthome.magic.activity.yaokongqi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.yaokongqi.dialog.YaokongPeiDialog;
import com.smarthome.magic.activity.yaokongqi.dialog.YaokongPeiFirstDialog;
import com.smarthome.magic.activity.yaokongqi.model.YaokongKeyModel;
import com.smarthome.magic.activity.yaokongqi.model.YaokongTagModel;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.mqtt_zhiling.ZnjjMqttMingLing;
import com.smarthome.magic.util.SoundPoolUtils;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.app.ConstanceValue.MSG_PEIWANG_SUCCESS;
import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class KongQiJingHuaPeiActivity extends BaseActivity {


    @BindView(R.id.rl_dianyuan)
    RelativeLayout rlDianyuan;
    @BindView(R.id.rl_zidong)
    RelativeLayout rlZidong;
    @BindView(R.id.rl_moshi)
    RelativeLayout rlMoshi;
    @BindView(R.id.rl_dingshi)
    RelativeLayout rlDingshi;
    @BindView(R.id.rl_fengsu)
    RelativeLayout rlFengsu;
    @BindView(R.id.rl_shuimian)
    RelativeLayout rlShuimian;
    @BindView(R.id.rl_tongsuo)
    RelativeLayout rlTongsuo;
    @BindView(R.id.rl_zidingyi)
    RelativeLayout rlZidingyi;
    @BindView(R.id.iv_dianyuan)
    ImageView ivDianyuan;
    @BindView(R.id.iv_zidong)
    ImageView ivZidong;
    @BindView(R.id.iv_moshi)
    ImageView ivMoshi;
    @BindView(R.id.iv_dingshi)
    ImageView ivDingshi;
    @BindView(R.id.iv_fengsu)
    ImageView ivFengsu;
    @BindView(R.id.iv_shuimian)
    ImageView ivShuimian;
    @BindView(R.id.iv_tongsuo)
    ImageView ivTongsuo;
    @BindView(R.id.iv_zidingyi)
    ImageView ivZidingyi;
    private String shebeiMa;
    private String keyMa;
    private String keyName;

    private YaokongPeiFirstDialog firstDialog;
    private YaokongPeiDialog peiDialog;
    private TishiDialog tishiDialog;

    private ZnjjMqttMingLing znjjMqttMingLing;
    private String ccid;
    private String serverId;
    private String topic;
    private List<YaokongKeyModel> keyModels;

    @Override
    public int getContentViewResId() {
        return R.layout.layout_kongqijinghua;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("空气净化配对");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));

        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("保存");
        tv_rightTitle.setTextSize(17);
        tv_rightTitle.setTextColor(Y.getColor(R.color.color_main));
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePeidui();
            }
        });


        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, KongQiJingHuaPeiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void savePeidui() {
        boolean isOK = false;
        for (int i = 0; i < keyModels.size(); i++) {
            YaokongKeyModel keyModel = keyModels.get(i);
            String mark_status = keyModel.getMark_status();
            if (mark_status.equals("1")) {
                isOK = true;
            }
        }

        if (!isOK) {
            Y.t("请先配对");
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "16072");
        jsonObject.put("key", Urls.key);
        jsonObject.put("token", UserManager.getManager(mContext).getAppToken());
        jsonObject.put("label_header", shebeiMa);
        jsonObject.put("ccid", ccid);
        jsonObject.put("pro", keyModels);
        OkGo.<YaokongTagModel>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(jsonObject.toJSONString())
                .execute(new JsonCallback<YaokongTagModel>() {
                    @Override
                    public void onSuccess(Response<YaokongTagModel> response) {
                        if (response.body().getMsg_code().equals("0000")) {
                            TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
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
                            dialog.setTextContent("遥控器配对成功");
                            dialog.show();
                        } else {
                            Y.t(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<YaokongTagModel> response) {
                        Y.tError(response);
                    }

                    @Override
                    public void onStart(Request<YaokongTagModel, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    private void back() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                String mingLingMa = "M22" + shebeiMa + ".";
                znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    }
                });
                finish();
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.setTextContent("确定退出配对么，未保存的的数据将消失！");
        dialog.setTextConfirm("确定");
        dialog.setTextCancel("取消");
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initMqtt();
        initDialog();
        initHuidiao();
        getnet();
    }

    private void initKeyModels() {
        keyModels = new ArrayList<>();
        keyModels.add(new YaokongKeyModel(shebeiMa + "01", "电源", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "02", "自动", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "03", "模式", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "04", "定时", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "05", "风速", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "06", "睡眠", "0"));
        keyModels.add(new YaokongKeyModel(shebeiMa + "07", "童锁", "0"));
    }

    private void initMqtt() {
        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);
        topic = "zn/hardware/" + serverId + ccid;
        znjjMqttMingLing.yaoKongQiPeiDui(topic, "M1737", new IMqttActionListener() {
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
    }

    private void initDialog() {
        firstDialog = new YaokongPeiFirstDialog(mContext);
        firstDialog.show();

        peiDialog = new YaokongPeiDialog(mContext);

        tishiDialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
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
        tishiDialog.setTextTitle("提示");
        tishiDialog.setTextCancel("");
        tishiDialog.setTextConfirm("确定");
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI) {
                    String msg = message.content.toString();
                    String shebeiCode = msg.substring(3, 7);
                    String keyCode = msg.substring(7, 9);
                    String isOk = msg.substring(9, 10);
                    if (shebeiMa.equals(shebeiCode)) {
                        peiDialog.dismiss();
                        if (isOk.equals("1")) {
                            SoundPoolUtils.soundPool(mContext, R.raw.hongwai_learn_suc);
                            tishiDialog.setTextContent(keyName + "键配对成功!");
                            if (keyCode.equals("01")) {
                                ivDianyuan.setImageResource(R.mipmap.yaokong_icon_guanbi_bl);
                                rlDianyuan.setEnabled(false);
                                YaokongKeyModel keyModel = keyModels.get(0);

                                keyModel.setMark_status("1");
                                keyModels.set(0, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("02")) {
                                ivZidong.setImageResource(R.mipmap.yaokong_icon_zidong_black);
                                rlZidong.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(1);
                                keyModel.setMark_status("1");
                                keyModels.set(1, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("03")) {
                                ivMoshi.setImageResource(R.mipmap.jinghuaqi_icon_moshi_bl);
                                rlFengsu.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(2);
                                keyModel.setMark_status("1");
                                keyModels.set(2, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("04")) {
                                ivDingshi.setImageResource(R.mipmap.jinghuaqi_icon_shijian_bl);
                                rlDingshi.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(3);
                                keyModel.setMark_status("1");
                                keyModels.set(3, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("05")) {
                                ivFengsu.setImageResource(R.mipmap.jinghuaqi_icon_fengsu_bk);
                                rlFengsu.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(4);
                                keyModel.setMark_status("1");
                                keyModels.set(4, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("06")) {
                                ivShuimian.setImageResource(R.mipmap.jinghuaqi_icon_shuimian_bk);
                                rlShuimian.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(5);
                                keyModel.setMark_status("1");
                                keyModels.set(5, keyModel);
                                tishiDialog.show();
                            } else if (keyCode.equals("06")) {
                                ivTongsuo.setImageResource(R.mipmap.jinghuaqi_icon_tongsuo_bl_blue);
                                rlTongsuo.setEnabled(false);

                                YaokongKeyModel keyModel = keyModels.get(6);
                                keyModel.setMark_status("1");
                                keyModels.set(6, keyModel);
                                tishiDialog.show();
                            }
                        } else {
                            tishiDialog.setTextContent(keyName + "键配对失败，请重新配对!");
                            tishiDialog.show();
                        }
                    }
                } else if (message.type == ConstanceValue.MSG_WANNENGYAOKONGQI_CODE_PEIDUI_ZIDINGYI) {
                    YaokongKeyModel keyModel = (YaokongKeyModel) message.content;
                    keyModels.add(keyModel);
                }
            }
        }));
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16071");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_type", "37");
        map.put("ccid", ccid);
        Gson gson = new Gson();
        OkGo.<YaokongTagModel>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<YaokongTagModel>() {
                    @Override
                    public void onSuccess(Response<YaokongTagModel> response) {
                        shebeiMa = response.body().getLabel_header();
                        initKeyModels();
                    }

                    @Override
                    public void onError(Response<YaokongTagModel> response) {
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                        shebeiMa = "";
                    }

                    @Override
                    public void onStart(Request<YaokongTagModel, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }


    private void clickKey(String key, String minglingma) {
        keyName = key;
        keyMa = minglingma;

        peiDialog.setKey(key);
        peiDialog.show();

        sendMsg(minglingma);

        SoundPoolUtils.soundPool(mContext, R.raw.hongwai_learn_voice);
    }


    private void sendMsg(String code) {
        String mingLingMa = "M19" + shebeiMa + code + ".";
        znjjMqttMingLing.yaoKongQiMingLing(mingLingMa, topic, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        back();
    }

    @OnClick({R.id.rl_dianyuan, R.id.rl_zidong, R.id.rl_moshi, R.id.rl_dingshi, R.id.rl_fengsu, R.id.rl_shuimian, R.id.rl_tongsuo, R.id.rl_zidingyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_dianyuan:
                clickKey("电源键", "01");
                break;
            case R.id.rl_zidong:
                clickKey("自动", "02");
                break;
            case R.id.rl_moshi:
                clickKey("模式", "03");
                break;
            case R.id.rl_dingshi:
                clickKey("定时", "04");
                break;
            case R.id.rl_fengsu:
                clickKey("风速", "05");
                break;
            case R.id.rl_shuimian:
                clickKey("睡眠", "06");
                break;
            case R.id.rl_tongsuo:
                clickKey("童锁", "07");
                break;
            case R.id.rl_zidingyi:
                WanNengYaoKongQiPeiDuiZidingyi.actionStart(mContext, shebeiMa, keyModels);
                break;
        }
    }
}
