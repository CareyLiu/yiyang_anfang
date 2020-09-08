package com.smarthome.magic.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShuinuanStateActivity extends BaseActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.tv_jiareji_state)
    TextView tv_jiareji_state;
    @BindView(R.id.tv_dangqianwendu)
    TextView tv_dangqianwendu;
    @BindView(R.id.tv_yushe_wendu)
    TextView tv_yushe_wendu;
    @BindView(R.id.tv_dianya)
    TextView tv_dianya;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_shebei_state)
    TextView tv_shebei_state;

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_shebei;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShuinuanStateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
