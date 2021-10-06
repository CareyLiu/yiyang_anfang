package com.yiyang.cn.activity.a_yiyang.activity.pinggu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;

import butterknife.ButterKnife;

public class PinggujieguoActivity extends BaseActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_pinggujieguo;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PinggujieguoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("评估报告");
        tv_title.setTextSize(17);
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("完成评估");
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
    }
}
