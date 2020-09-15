package com.smarthome.magic.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.smarthome.magic.R;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuo_Base;
import com.smarthome.magic.dialog.newdia.TishiDialog;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.text.Format;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ShuinuanHostActivity extends ShuinuanBaseActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.tv_cixianquan0)
    TextView tvCixianquan0;
    @BindView(R.id.tv_cixianquan1)
    TextView tvCixianquan1;
    @BindView(R.id.tv_cixianquan2)
    TextView tvCixianquan2;
    @BindView(R.id.tv_cixianquan3)
    TextView tvCixianquan3;
    @BindView(R.id.tv_cixianquan4)
    TextView tvCixianquan4;
    @BindView(R.id.tv_jiaresai_jingci)
    TextView tvJiaresaiJingci;
    @BindView(R.id.tv_jiaresai_limai)
    TextView tvJiaresaiLimai;
    @BindView(R.id.ed_jiqigonglv)
    TextView edJiqigonglv;
    @BindView(R.id.tv_dianya_12v)
    TextView tvDianya12v;
    @BindView(R.id.tv_dianya_24v)
    TextView tvDianya24v;
    @BindView(R.id.tv_dianya_zidong)
    TextView tvDianyaZidong;
    @BindView(R.id.ed_youbengrongjizhi)
    TextView edYoubengrongjizhi;
    @BindView(R.id.ed_guoyazhi)
    TextView edGuoyazhi;
    @BindView(R.id.ed_guoyazhi_time)
    TextView edGuoyazhiTime;
    @BindView(R.id.ed_qianya)
    TextView edQianya;
    @BindView(R.id.ed_qianya_time)
    TextView edQianyaTime;
    @BindView(R.id.bt_quxiao)
    TextView btQuxiao;
    @BindView(R.id.bt_ok)
    TextView btOk;
    @BindView(R.id.bt_huifu)
    TextView btHuifu;

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_host;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShuinuanHostActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initHuidiao();
        getHost();
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
        if (msg.contains("i_s")) {
            dialog.dismiss();
            String substring = msg.substring(3, msg.length() - 2);
            if (substring.contains("a")) {
                showNodata();
            } else {
                citienum = msg.substring(3, 4);//磁铁数量  0：无1：单磁铁2：双磁铁3：三磁铁4：四磁铁
                jiaresai = msg.substring(4, 5);//加热塞  0：京瓷 1：利麦
                jiqigonglv = msg.substring(5, 8);//机器功率  020=2kw   037=3.7kw  170=17kw
                dianya = msg.substring(8, 9);//电压  0：12V  1：24V  9：自动
                rongjizhi = msg.substring(9, 11);//油泵容积值  16-70
                guoyazhi = msg.substring(11, 14);//过压值  135=13.5V
                guoyatime = msg.substring(14, 16);//过压报警时间  10=10秒
                qianyazhi = msg.substring(16, 19);//欠压值  135=13.5V
                qianyatime = msg.substring(19, 21);//欠压报警时间  10=10秒

                clickCixianquan(citienum);
                clickJiaresai(jiaresai);
                edJiqigonglv.setText(formatNum(getFloat(jiqigonglv) / 10));
                clickDianya(dianya);
                edYoubengrongjizhi.setText(rongjizhi);

                edGuoyazhi.setText(formatNum(getFloat(guoyazhi) / 10));
                edGuoyazhiTime.setText(guoyatime);
                edQianya.setText(formatNum(getFloat(qianyazhi) / 10));
                edQianyaTime.setText(qianyatime);
            }
        } else if (msg.contains("k_s")) {
            if (dialog.isShowing()) {
                dialog.dismiss();
                chenggong();
            }
        }
    }

    private void showNodata() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_FAILED, new TishiDialog.TishiDialogListener() {
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
        dialog.setTextTitle("提示");
        dialog.setTextContent("暂无主机参数信息");
        dialog.setTextConfirm("关闭");
        dialog.show();
    }


    private void getHost() {
        showDialog("连接中...");
        //向水暖加热器发送获取主机参数
        Log.i("LKjfdslkfsd    ", SN_Send);
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M_s112.")
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("app端向水暖加热器获取主机参数", "");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Log.i("app端向水暖加热器获取主机参数", "记得是弗兰克斯戴假发的");
            }
        });
    }


    @OnClick({R.id.ed_jiqigonglv, R.id.ed_youbengrongjizhi, R.id.ed_guoyazhi, R.id.ed_guoyazhi_time, R.id.ed_qianya, R.id.ed_qianya_time, R.id.back, R.id.tv_cixianquan0, R.id.tv_cixianquan1, R.id.tv_cixianquan2, R.id.tv_cixianquan3, R.id.tv_cixianquan4, R.id.tv_jiaresai_jingci, R.id.tv_jiaresai_limai, R.id.tv_dianya_12v, R.id.tv_dianya_24v, R.id.tv_dianya_zidong, R.id.bt_quxiao, R.id.bt_ok, R.id.bt_huifu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_cixianquan0:
                clickCixianquan("0");
                break;
            case R.id.tv_cixianquan1:
                clickCixianquan("1");
                break;
            case R.id.tv_cixianquan2:
                clickCixianquan("2");
                break;
            case R.id.tv_cixianquan3:
                clickCixianquan("3");
                break;
            case R.id.tv_cixianquan4:
                clickCixianquan("4");
                break;
            case R.id.tv_jiaresai_jingci:
                clickJiaresai("0");
                break;
            case R.id.tv_jiaresai_limai:
                clickJiaresai("1");
                break;
            case R.id.tv_dianya_12v:
                clickDianya("0");
                break;
            case R.id.tv_dianya_24v:
                clickDianya("1");
                break;
            case R.id.tv_dianya_zidong:
                clickDianya("9");
                break;
            case R.id.bt_quxiao:
                break;
            case R.id.bt_ok:
                clickSetHoset();
                break;
            case R.id.bt_huifu:
                huifuchuchang();
                break;
            case R.id.ed_jiqigonglv:
                clickJiqigonglv();
                break;
            case R.id.ed_youbengrongjizhi:
                clickYoubeng();
                break;
            case R.id.ed_guoyazhi:
                clickGuoyazhi();
                break;
            case R.id.ed_guoyazhi_time:
                clickGuoyazhiTime();
                break;
            case R.id.ed_qianya:
                clickQianyazhi();
                break;
            case R.id.ed_qianya_time:
                clickQianyazhiTime();
                break;
        }
    }

    private void clickQianyazhiTime() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                String textContent = dialog.getTextContent();
                if (StringUtils.isEmpty(textContent)) {
                    t("请输入欠压值报警时间");
                    return;
                }
                int anInt = getInt(textContent);
                if (anInt > 99) {
                    t("欠压值报警时间的范围为0-99S");
                    return;
                }
                edQianyaTime.setText(textContent);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER);
        dialog.setTextTitle("请输入欠压值报警时间");
        dialog.setTextContent(edQianyaTime.getText().toString());
        dialog.show();
    }

    private void clickQianyazhi() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                String textContent = dialog.getTextContent();
                if (StringUtils.isEmpty(textContent)) {
                    t("请输入欠压值");
                    return;
                }

                float aFloat = getFloat(textContent);
                if (aFloat > 99) {
                    t("欠压值的范围为0-99V");
                    return;
                }
                edQianya.setText(textContent);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请输入欠压值");
        dialog.setTextContent(edQianya.getText().toString());
        dialog.show();
    }

    private void clickGuoyazhiTime() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                String textContent = dialog.getTextContent();
                if (StringUtils.isEmpty(textContent)) {
                    t("请输入过压值报警时间");
                    return;
                }
                int anInt = getInt(textContent);
                if (anInt > 99) {
                    t("过压值报警时间的范围为0-99S");
                    return;
                }
                edGuoyazhiTime.setText(textContent);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER);
        dialog.setTextTitle("请输入过压值报警时间");
        dialog.setTextContent(edGuoyazhiTime.getText().toString());
        dialog.show();
    }

    private void clickGuoyazhi() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                String textContent = dialog.getTextContent();
                if (StringUtils.isEmpty(textContent)) {
                    t("请输入过压值");
                    return;
                }

                float aFloat = getFloat(textContent);
                if (aFloat > 99) {
                    t("过压值的范围为0-99V");
                    return;
                }
                edGuoyazhi.setText(textContent);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请输入过压值");
        dialog.setTextContent(edGuoyazhi.getText().toString());
        dialog.show();
    }

    private void clickJiqigonglv() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                String textContent = dialog.getTextContent();
                if (StringUtils.isEmpty(textContent)) {
                    t("请输入机器功率");
                    return;
                }

                float aFloat = getFloat(textContent);
                if (aFloat > 99) {
                    t("机器功率的范围为0-99Kw");
                    return;
                }
                edJiqigonglv.setText(textContent);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        dialog.setTextTitle("请输入机器功率");
        dialog.setTextContent(edJiqigonglv.getText().toString());
        dialog.show();
    }


    private void clickYoubeng() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                String textContent = dialog.getTextContent();
                if (StringUtils.isEmpty(textContent)) {
                    t("请输入油泵容积值");
                    return;
                }
                int anInt = getInt(textContent);
                if (anInt > 70 || anInt < 16) {
                    t("油泵容积值的范围为16-70L");
                    return;
                }
                edYoubengrongjizhi.setText(textContent);
            }

            @Override
            public void onDismiss(InputDialog dialog) {

            }
        });
        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER);
        dialog.setTextTitle("请输入油泵容积值");
        dialog.setTextContent(edYoubengrongjizhi.getText().toString());
        dialog.show();
    }


    private String citienum;
    private String jiaresai;
    private String jiqigonglv;
    private String dianya;
    private String rongjizhi;
    private String guoyazhi;
    private String guoyatime;
    private String qianyazhi;
    private String qianyatime;

    private void clickSetHoset() {
        if (StringUtils.isEmpty(citienum)) {
            t("请选择磁铁圈数量");
            return;
        }

        if (StringUtils.isEmpty(jiaresai)) {
            t("请选择加热塞");
            return;
        }

        String jiqigonglvY = edJiqigonglv.getText().toString();
        if (StringUtils.isEmpty(jiqigonglvY)) {
            t("请输入机器功率");
            return;
        }
        jiqigonglv = ((int) (getFloat(jiqigonglvY) * 10)) + "";
        if (jiqigonglv.length() == 2) {
            jiqigonglv = "0" + jiqigonglv;
        } else if (jiqigonglv.length() == 1) {
            jiqigonglv = "00" + jiqigonglv;
        }

        if (StringUtils.isEmpty(dianya)) {
            t("请选择电压");
            return;
        }

        rongjizhi = edYoubengrongjizhi.getText().toString();
        if (StringUtils.isEmpty(rongjizhi)) {
            t("请输入油泵容积值");
            return;
        }

        String guoyazhiV = edGuoyazhi.getText().toString();
        if (StringUtils.isEmpty(guoyazhiV)) {
            t("请输入过压值");
            return;
        }
        guoyazhi = ((int) (getFloat(guoyazhiV) * 10)) + "";
        if (guoyazhi.length() == 2) {
            guoyazhi = "0" + guoyazhi;
        } else if (guoyazhi.length() == 1) {
            guoyazhi = "00" + guoyazhi;
        }

        guoyatime = edGuoyazhiTime.getText().toString();
        if (StringUtils.isEmpty(guoyatime)) {
            t("请输入过压值报警时间");
            return;
        }
        if (guoyatime.length() == 1) {
            guoyatime = "0" + guoyatime;
        }

        String qianyazhiV = edQianya.getText().toString();
        if (StringUtils.isEmpty(qianyazhiV)) {
            t("请输入欠压值");
            return;
        }
        qianyazhi = ((int) (getFloat(qianyazhiV) * 10)) + "";
        if (qianyazhi.length() == 2) {
            qianyazhi = "0" + qianyazhi;
        } else if (qianyazhi.length() == 1) {
            qianyazhi = "00" + qianyazhi;
        }

        qianyatime = edQianyaTime.getText().toString();
        if (StringUtils.isEmpty(qianyatime)) {
            t("请输入欠压值报警时间");
            return;
        }
        if (qianyatime.length() == 1) {
            qianyatime = "0" + qianyatime;
        }

        String host = "M_s13" + citienum + jiaresai + jiqigonglv + dianya + rongjizhi + guoyazhi + guoyatime + qianyazhi + qianyatime + ".";

        Log.e(" 所有的数据是 ", host);

        TishiDialog tishiDialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                showDialog("发送中...");
                sendHost(host);
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        tishiDialog.setTextTitle("提示");
        tishiDialog.setTextContent("是否设置主机参数");
        tishiDialog.show();
    }


    private void clickDianya(String type) {
        tvDianya12v.setTextColor(Color.parseColor("#999999"));
        tvDianya24v.setTextColor(Color.parseColor("#999999"));
        tvDianyaZidong.setTextColor(Color.parseColor("#999999"));
        switch (type) {
            case "0":
                tvDianya12v.setTextColor(Color.parseColor("#0F85FF"));
                dianya = type;
                break;
            case "1":
                tvDianya24v.setTextColor(Color.parseColor("#0F85FF"));
                dianya = type;
                break;
            case "9":
                tvDianyaZidong.setTextColor(Color.parseColor("#0F85FF"));
                dianya = type;
                break;
        }
    }

    private void clickJiaresai(String type) {
        tvJiaresaiJingci.setTextColor(Color.parseColor("#999999"));
        tvJiaresaiLimai.setTextColor(Color.parseColor("#999999"));
        switch (type) {
            case "0":
                jiaresai = type;
                tvJiaresaiJingci.setTextColor(Color.parseColor("#0F85FF"));
                break;
            case "1":
                jiaresai = type;
                tvJiaresaiLimai.setTextColor(Color.parseColor("#0F85FF"));
                break;
        }
    }

    private void clickCixianquan(String type) {
        tvCixianquan0.setTextColor(Color.parseColor("#999999"));
        tvCixianquan1.setTextColor(Color.parseColor("#999999"));
        tvCixianquan2.setTextColor(Color.parseColor("#999999"));
        tvCixianquan3.setTextColor(Color.parseColor("#999999"));
        tvCixianquan4.setTextColor(Color.parseColor("#999999"));
        switch (type) {
            case "0":
                tvCixianquan0.setTextColor(Color.parseColor("#0F85FF"));
                citienum = type;
                break;
            case "1":
                tvCixianquan1.setTextColor(Color.parseColor("#0F85FF"));
                citienum = type;
                break;
            case "2":
                tvCixianquan2.setTextColor(Color.parseColor("#0F85FF"));
                citienum = type;
                break;
            case "3":
                tvCixianquan3.setTextColor(Color.parseColor("#0F85FF"));
                citienum = type;
                break;
            case "4":
                tvCixianquan4.setTextColor(Color.parseColor("#0F85FF"));
                citienum = type;
                break;
        }
    }

    private void sendHost(String host) {
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg(host)
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                dialog.dismiss();
                chenggong();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    private void huifuchuchang() {
        TishiDialog tishiDialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                showDialog("发送中...");
                sendHuifu();
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        tishiDialog.setTextTitle("恢复出厂");
        tishiDialog.setTextContent("是否执行恢复出厂");
        tishiDialog.show();
    }

    /**
     * 恢复出厂设置
     */
    private void sendHuifu() {
        String data = "M_s101";
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


}
