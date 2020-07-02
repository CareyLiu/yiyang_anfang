package com.smarthome.magic.activity.wode_page.bazinew;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.bazinew.base.BaziBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YunshiActivity extends BaziBaseActivity {


    @BindView(R.id.tv_name_sex)
    TextView tv_name_sex;
    @BindView(R.id.tv_yangli)
    TextView tv_yangli;
    @BindView(R.id.tv_nongli)
    TextView tv_nongli;
    @BindView(R.id.tv_yunshi)
    TextView tv_yunshi;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.ll_jiesuo)
    LinearLayout ll_jiesuo;
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.tv_select_data)
    TextView tv_select_data;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_yunshi;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("运势");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
