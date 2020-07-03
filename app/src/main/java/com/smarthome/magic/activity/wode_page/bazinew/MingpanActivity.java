package com.smarthome.magic.activity.wode_page.bazinew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.smarthome.magic.activity.wode_page.bazinew.model.DanganModel;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MingpanActivity extends BaziBaseActivity {

    @BindView(R.id.lfkdfd)
    LinearLayout lfkdfd;
    private DanganModel.DataBean model;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_mingpan;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("本命盘");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        model = (DanganModel.DataBean) getIntent().getSerializableExtra("model");
    }

    @OnClick(R.id.lfkdfd)
    public void onViewClicked() {
        Intent intent = new Intent(this, JiexiMainActivity.class);
        intent.putExtra("model", model);
        startActivity(intent);
    }
}
