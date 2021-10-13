package com.yiyang.cn.activity.a_yiyang.activity.jinlaoka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.model.JinglaokaModel;
import com.yiyang.cn.app.BaseActivity;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JinglaokaYouActivity extends BaseActivity {


    @BindView(R.id.ll_chongzhi)
    LinearLayout llChongzhi;
    @BindView(R.id.ll_chongzhijilu)
    LinearLayout llChongzhijilu;
    @BindView(R.id.ll_jiaoyijilu)
    LinearLayout llJiaoyijilu;
    @BindView(R.id.ll_nianjian)
    LinearLayout llNianjian;
    private JinglaokaModel model;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jinglaoka_you;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, JinglaokaModel model) {
        Intent intent = new Intent(context, JinglaokaYouActivity.class);
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
        tv_title.setText("敬老卡");
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
        model = (JinglaokaModel) getIntent().getSerializableExtra("model");
    }

    @OnClick({R.id.ll_chongzhi, R.id.ll_chongzhijilu, R.id.ll_jiaoyijilu, R.id.ll_nianjian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_chongzhi:
                JinglaokaChongzhiActivity.actionStart(mContext);
                break;
            case R.id.ll_chongzhijilu:
                JiluChongzhiActivity.actionStart(mContext);
                break;
            case R.id.ll_jiaoyijilu:
                JiluJiaoyiActivity.actionStart(mContext);
                break;
            case R.id.ll_nianjian:
                ShenheActivity.actionStart(mContext, model);
                break;
        }
    }
}
