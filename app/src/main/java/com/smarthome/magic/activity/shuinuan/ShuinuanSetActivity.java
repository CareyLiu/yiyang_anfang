package com.smarthome.magic.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.AppointmentActivity;
import com.smarthome.magic.activity.DriveinfoActivity;
import com.smarthome.magic.activity.ServerPassWordActivity;
import com.smarthome.magic.activity.UserInfoActivity;
import com.smarthome.magic.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShuinuanSetActivity extends BaseActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.item_host)
    LinearLayout itemHost;
    @BindView(R.id.item_ovner)
    LinearLayout itemOvner;
    @BindView(R.id.item_parts)
    LinearLayout itemParts;
    @BindView(R.id.item_ratio)
    LinearLayout itemRatio;
    @BindView(R.id.item_atmos)
    LinearLayout itemAtmos;
    @BindView(R.id.item_chuchangset)
    LinearLayout itemChuchangset;
    @BindView(R.id.item_dingshi)
    LinearLayout itemDingshi;

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.transparent)
                .statusBarDarkFont(false)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
                .statusBarColor(R.color.transparent)     //状态栏颜色，不写默认透明色
                .navigationBarColor(R.color.white) //导航栏颜色，不写默认黑色
                .barColor(R.color.transparent).init();
    }

    @Override
    public boolean showToolBar() {
        return false;
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

    @OnClick({R.id.back, R.id.item_host, R.id.item_ovner, R.id.item_parts, R.id.item_ratio, R.id.item_atmos, R.id.item_chuchangset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.item_host:
                ServerPassWordActivity.actionStart(this, "host");
                break;
            case R.id.item_ovner:
                startActivity(new Intent(this, UserInfoActivity.class));
                break;
            case R.id.item_parts:
                DriveinfoActivity.actionStart(this);
                break;
            case R.id.item_ratio:
                ServerPassWordActivity.actionStart(this, "RatioActivity");
                break;
            case R.id.item_atmos:
                ServerPassWordActivity.actionStart(this, "AtmosActivity");
                break;
            case R.id.item_dingshi:
                startActivity(new Intent(this, AppointmentActivity.class));
                break;
            case R.id.item_chuchangset:
                ClickChuchang();
                break;
        }
    }

    private void ClickChuchang() {

    }
}
