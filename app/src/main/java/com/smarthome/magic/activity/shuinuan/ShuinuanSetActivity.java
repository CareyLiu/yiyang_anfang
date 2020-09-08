package com.smarthome.magic.activity.shuinuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.HeaterSettingActivity;
import com.smarthome.magic.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShuinuanSetActivity extends BaseActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.item_state)
    LinearLayout item_state;
    @BindView(R.id.item_host)
    LinearLayout item_host;
    @BindView(R.id.item_ratio)
    LinearLayout item_ratio;
    @BindView(R.id.item_set)
    LinearLayout item_set;

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
        return R.layout.activity_shuinuan_set_new;
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

    @OnClick({R.id.back, R.id.item_state, R.id.item_host, R.id.item_ratio, R.id.item_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.item_state:
                ShuinuanStateActivity.actionStart(mContext);
                break;
            case R.id.item_host:
                ShuinuanHostActivity.actionStart(mContext);
                break;
            case R.id.item_ratio:
                ShuinuanFengyoubiActivity.actionStart(mContext);
                break;
            case R.id.item_set:
                startActivity(new Intent(this, HeaterSettingActivity.class));
                break;
        }
    }
}
