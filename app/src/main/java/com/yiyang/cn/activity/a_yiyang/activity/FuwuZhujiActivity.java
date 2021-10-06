package com.yiyang.cn.activity.a_yiyang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;

import butterknife.ButterKnife;

public class FuwuZhujiActivity extends BaseActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_fuwuzhuji;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String name) {
        Intent intent = new Intent(context, FuwuZhujiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
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
        String name = getIntent().getStringExtra("name");
        tv_title.setText(name);
    }
}
