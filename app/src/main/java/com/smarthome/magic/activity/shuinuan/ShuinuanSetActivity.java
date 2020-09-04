package com.smarthome.magic.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.ServerPassWordActivity;
import com.smarthome.magic.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShuinuanSetActivity extends BaseActivity {

    @BindView(R.id.ll_shebeizhuangtai)
    LinearLayout ll_shebeizhuangtai;
    @BindView(R.id.ll_zhujicangshu)
    LinearLayout ll_zhujicangshu;
    @BindView(R.id.ll_fengyoubi)
    LinearLayout ll_fengyoubi;
    @BindView(R.id.ll_chuchangset)
    LinearLayout ll_chuchangset;

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设置");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black_111111));
        mToolbar.setNavigationIcon(R.mipmap.img_back_black);
        mToolbar.setBackgroundColor(Color.WHITE);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_set;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShuinuanSetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_shebeizhuangtai, R.id.ll_zhujicangshu, R.id.ll_fengyoubi, R.id.ll_chuchangset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shebeizhuangtai:
                ServerPassWordActivity.actionStart(this, "host");
                break;
            case R.id.ll_zhujicangshu:
                break;
            case R.id.ll_fengyoubi:
                ServerPassWordActivity.actionStart(this, "RatioActivity");
                break;
            case R.id.ll_chuchangset:
                break;
        }
    }
}
