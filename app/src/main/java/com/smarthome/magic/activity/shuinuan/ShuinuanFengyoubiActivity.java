package com.smarthome.magic.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.smarthome.magic.R;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuo_Base;
import com.smarthome.magic.dialog.newdia.TishiDialog;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ShuinuanFengyoubiActivity extends ShuinuanBaseActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.ed_fengjizhuansu1)
    TextView ed_fengjizhuansu1;
    @BindView(R.id.ed_youbengpinlv1)
    TextView ed_youbengpinlv1;
    @BindView(R.id.ed_fengjizhuansu2)
    TextView ed_fengjizhuansu2;
    @BindView(R.id.ed_youbengpinlv2)
    TextView ed_youbengpinlv2;
    @BindView(R.id.bt_quxiao)
    TextView bt_quxiao;
    @BindView(R.id.bt_ok)
    TextView bt_ok;
    @BindView(R.id.bt_huifu)
    TextView bt_huifu;

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_fenyoubi;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShuinuanFengyoubiActivity.class);
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

        dialog.dismiss();
        if (msg.contains("a")) {
            showNodata();
        }

        if (msg.contains("h_s")) {

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
        dialog.setTextContent("暂无风油比参数信息");
        dialog.setTextConfirm("关闭");
        dialog.show();
    }


    private void getHost() {
        showDialog("连接中...");
        AndMqtt.getInstance().publish(new MqttPublish()
                .setMsg("M_s111.")
                .setQos(2).setRetained(false)
                .setTopic(SN_Send), new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i("app端向水暖加热器获取风油比参数", "");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
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
                dialog.dismiss();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }

    @OnClick({R.id.back, R.id.ed_fengjizhuansu1, R.id.ed_youbengpinlv1, R.id.ed_fengjizhuansu2, R.id.ed_youbengpinlv2, R.id.bt_quxiao, R.id.bt_ok, R.id.bt_huifu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ed_fengjizhuansu1:
                break;
            case R.id.ed_youbengpinlv1:
                break;
            case R.id.ed_fengjizhuansu2:
                break;
            case R.id.ed_youbengpinlv2:
                break;
            case R.id.bt_quxiao:
                break;
            case R.id.bt_ok:
                break;
            case R.id.bt_huifu:
                huifuchuchang();
                break;
        }
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
}
