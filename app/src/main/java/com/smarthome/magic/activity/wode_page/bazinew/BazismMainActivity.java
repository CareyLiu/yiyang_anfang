package com.smarthome.magic.activity.wode_page.bazinew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BazismMainActivity extends BaziBaseActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.view_tab_zhitianming)
    LinearLayout view_tab_zhitianming;
    @BindView(R.id.view_tab_nianyunshi)
    LinearLayout view_tab_nianyunshi;
    @BindView(R.id.view_tab_yueyunshi)
    LinearLayout view_tab_yueyunshi;
    @BindView(R.id.view_tab_riyunshi)
    LinearLayout view_tab_riyunshi;
    @BindView(R.id.iv_gerendangan)
    ImageView iv_gerendangan;
    @BindView(R.id.ll_gerendangan)
    LinearLayout ll_gerendangan;
    @BindView(R.id.fl_fenrendangan)
    FrameLayout fl_fenrendangan;
    @BindView(R.id.tv_color)
    TextView tv_color;
    @BindView(R.id.tv_dongwu)
    TextView tv_dongwu;
    @BindView(R.id.tv_shuzi)
    TextView tv_shuzi;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_main;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("八紫");
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BazismMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.view_tab_zhitianming, R.id.view_tab_nianyunshi, R.id.view_tab_yueyunshi, R.id.view_tab_riyunshi, R.id.fl_fenrendangan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_tab_nianyunshi:
                clickNian();
                break;
            case R.id.view_tab_yueyunshi:
                clickYue();
                break;
            case R.id.view_tab_riyunshi:
                clickRi();
                break;
            case R.id.view_tab_zhitianming:
            case R.id.fl_fenrendangan:
                clickDanan();
                break;
        }
    }

    private void clickNian() {
        openActivity(DanganguanliActivity.class);
    }

    private void clickYue() {
        openActivity(DanganguanliActivity.class);
    }

    private void clickRi() {
        openActivity(DanganguanliActivity.class);
    }

    private void clickDanan() {
        openActivity(DanganguanliActivity.class);
    }
}
