package com.smarthome.magic.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.HeaterSettingActivity;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuo_Base;
import com.smarthome.magic.util.AlertUtil;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.config.MyApplication.CAR_CTROL;

public class ShuinuanSetActivity extends ShuinuanBaseActivity {
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.item_state)
    LinearLayout item_state;
    @BindView(R.id.item_host)
    LinearLayout item_host;
    @BindView(R.id.item_ratio)
    LinearLayout item_ratio;
    @BindView(R.id.item_set)
    LinearLayout item_set;

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_set_new;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShuinuanSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initHuidiao();
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
        if (msg.contains("k_s")) {
            String code = msg.substring(3, 5);
            if (code.equals("01")) {
                String zhuantai = msg.substring(5, 6);
                if (zhuantai.equals("1")) {
                    Log.i("操作成功", "");
                } else {
                    Log.i("操作失败", "");
                }
            } else if (code.equals("10")) {
                String zhuantai = msg.substring(5, 6);
                if (zhuantai.equals("1")) {
                    Log.i("恢复操作成功", "");
                } else {
                    Log.i("恢复操作失败", "");
                }
            }
        }
    }

    @OnClick({R.id.back, R.id.item_state, R.id.item_host, R.id.item_ratio, R.id.item_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.item_state:
                ShuinuanStateActivity.actionStart(mContext);
                break;
            case R.id.item_host:
                ShuinuanHostActivity.actionStart(mContext);
                break;
            case R.id.item_ratio:
                ShuinuanFengyoubiActivity.actionStart(mContext);
                break;
            case R.id.item_set:
                huifuchuchang();
                break;
        }
    }

    private void huifuchuchang() {
        MyCarCaoZuoDialog_CaoZuo_Base base = new MyCarCaoZuoDialog_CaoZuo_Base(this, "恢复出厂", "是否执行恢复出厂", new MyCarCaoZuoDialog_CaoZuo_Base.OnDialogItemClickListener() {
            @Override
            public void clickLeft() {

            }

            @Override
            public void clickRight() {
                sendHuifu();
            }
        });
        base.show();
    }

    /**
     * 恢复出厂设置
     */
    private void sendHuifu() {
        String data = "M_s103";
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

    public static final String SN_Send = "wh/hardware/11111111111111111111111";
}
