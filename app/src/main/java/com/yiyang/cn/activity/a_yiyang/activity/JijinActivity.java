package com.yiyang.cn.activity.a_yiyang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JijinActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_select1)
    TextView tvSelect1;
    @BindView(R.id.tv_select2)
    TextView tvSelect2;
    @BindView(R.id.tv_select3)
    TextView tvSelect3;
    @BindView(R.id.tv_select4)
    TextView tvSelect4;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.ll_card)
    LinearLayout llCard;
    @BindView(R.id.iv_zhuyi)
    ImageView ivZhuyi;
    @BindView(R.id.bt_xiayibu)
    TextView btXiayibu;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jijin;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JijinActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    public boolean isImmersive() {
        return true;
    }

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }

    @OnClick({R.id.rl_back, R.id.tv_select1, R.id.tv_select2, R.id.tv_select3, R.id.tv_select4, R.id.ll_name, R.id.ll_card, R.id.bt_xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_select1:
                break;
            case R.id.tv_select2:
                break;
            case R.id.tv_select3:
                break;
            case R.id.tv_select4:
                break;
            case R.id.ll_name:
                break;
            case R.id.ll_card:
                break;
            case R.id.bt_xiayibu:
                JijinQuerenActivity.actionStart(mContext);
                break;
        }
    }
}
