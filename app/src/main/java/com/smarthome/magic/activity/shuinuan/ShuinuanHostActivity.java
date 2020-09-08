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

public class ShuinuanHostActivity extends BaseActivity {

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
    EditText edJiqigonglv;
    @BindView(R.id.tv_dianya_12v)
    TextView tvDianya12v;
    @BindView(R.id.tv_dianya_24v)
    TextView tvDianya24v;
    @BindView(R.id.tv_dianya_zidong)
    TextView tvDianyaZidong;
    @BindView(R.id.ed_youbengrongjizhi)
    EditText edYoubengrongjizhi;
    @BindView(R.id.ed_guoyazhi)
    EditText edGuoyazhi;
    @BindView(R.id.ed_guoyazhi_time)
    EditText edGuoyazhiTime;
    @BindView(R.id.ed_qianya)
    EditText edQianya;
    @BindView(R.id.ed_qianya_time)
    EditText edQianyaTime;
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
    }

    @OnClick({R.id.back, R.id.tv_cixianquan0, R.id.tv_cixianquan1, R.id.tv_cixianquan2, R.id.tv_cixianquan3, R.id.tv_cixianquan4, R.id.tv_jiaresai_jingci, R.id.tv_jiaresai_limai, R.id.tv_dianya_12v, R.id.tv_dianya_24v, R.id.tv_dianya_zidong, R.id.bt_quxiao, R.id.bt_ok, R.id.bt_huifu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_cixianquan0:
                break;
            case R.id.tv_cixianquan1:
                break;
            case R.id.tv_cixianquan2:
                break;
            case R.id.tv_cixianquan3:
                break;
            case R.id.tv_cixianquan4:
                break;
            case R.id.tv_jiaresai_jingci:
                break;
            case R.id.tv_jiaresai_limai:
                break;
            case R.id.tv_dianya_12v:
                break;
            case R.id.tv_dianya_24v:
                break;
            case R.id.tv_dianya_zidong:
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
