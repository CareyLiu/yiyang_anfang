package com.smarthome.magic.util;

import android.os.CountDownTimer;
import androidx.core.content.ContextCompat;
import android.widget.TextView;

import com.smarthome.magic.R;
import com.smarthome.magic.config.MyApplication;
import com.smarthome.magic.service.HeaterMqttService;


/**
 * Created by Administrator on 2017/3/31.
 */
//计时器
public class TimeCount extends CountDownTimer {
    private TextView view;
    public TimeCount(long millisInFuture, long countDownInterval, TextView view) {
        super(millisInFuture, countDownInterval);
        this.view= view;
    }

    @Override
    public void onFinish() {// 计时完毕
        view.setClickable(true);
        view.setText(R.string.get_code);
        view.setTextColor(ContextCompat.getColor(MyApplication.context, R.color.app_bg));
        view.setTextSize(14);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程
        view.setClickable(false);
        view.setTextSize(12);
        view.setText(millisUntilFinished / 1000 + "s后重新发送");
        view.setTextColor(ContextCompat.getColor(MyApplication.context,R.color.app_bg));

    }


}