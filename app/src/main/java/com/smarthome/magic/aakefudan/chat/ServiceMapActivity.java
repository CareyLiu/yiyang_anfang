package com.smarthome.magic.aakefudan.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.util.AlertUtil;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceMapActivity extends BaseActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.activity_kefu_conversation;
    }


    public static void actionStart(Context context, MyMessage message) {
        Intent intent = new Intent(context, ServiceMapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("message", (Serializable) message);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        MyMessage message = (MyMessage) getIntent().getSerializableExtra("message");
        AlertUtil.t(mContext,message.getCustomTitle());
    }}
