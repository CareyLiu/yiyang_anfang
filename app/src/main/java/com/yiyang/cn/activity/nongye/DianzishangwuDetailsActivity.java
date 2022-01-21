package com.yiyang.cn.activity.nongye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.model.DianzishangwuModel;
import com.yiyang.cn.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DianzishangwuDetailsActivity extends BaseActivity {


    @BindView(R.id.iv_main)
    ImageView iv_main;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private DianzishangwuModel model;

    @Override
    public int getContentViewResId() {
        return R.layout.nongye_act_dianzishangwu_details;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, DianzishangwuModel model) {
        Intent intent = new Intent(context, DianzishangwuDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("model", model);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("详情");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        model = (DianzishangwuModel) getIntent().getSerializableExtra("model");
        iv_main.setImageResource(model.getSrcId());
        tv_name.setText("品种：" + model.getName());
        tv_address.setText(model.getAddress());
        tv_money.setText(model.getMoney() + "元/斤");
    }
}
