package com.smarthome.magic.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShuinuanFengyoubiActivity extends ShuinuanBaseActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.ed_fengjizhuansu1)
    EditText ed_fengjizhuansu1;
    @BindView(R.id.ed_youbengpinlv1)
    EditText ed_youbengpinlv1;
    @BindView(R.id.ed_fengjizhuansu2)
    EditText ed_fengjizhuansu2;
    @BindView(R.id.ed_youbengpinlv2)
    EditText ed_youbengpinlv2;
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
    }


    @OnClick({R.id.back, R.id.bt_quxiao, R.id.bt_ok, R.id.bt_huifu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.bt_quxiao:
                break;
            case R.id.bt_ok:
                break;
            case R.id.bt_huifu:
                break;
        }
    }
}
