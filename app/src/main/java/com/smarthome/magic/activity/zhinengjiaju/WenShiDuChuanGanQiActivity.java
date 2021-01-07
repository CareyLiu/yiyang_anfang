package com.smarthome.magic.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.SuiYiTieSetting;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.util.icon_util.LineChart01View;
import com.smarthome.magic.util.icon_util.LineChart02View;

import butterknife.BindView;

public class WenShiDuChuanGanQiActivity extends BaseActivity {
    @BindView(R.id.tv_wendu)
    TextView tvWendu;
    @BindView(R.id.tv_wenduzhi)
    TextView tvWenduzhi;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.tv_shidu)
    TextView tvShidu;
    @BindView(R.id.tv_shiduzhi)
    TextView tvShiduzhi;
    @BindView(R.id.iv_icon_1)
    ImageView ivIcon1;
    @BindView(R.id.tv_dangqianwendu)
    TextView tvDangqianwendu;
    @BindView(R.id.tv_dangqianshidu)
    TextView tvDangqianshidu;
    @BindView(R.id.ll_main)
    LinearLayout llMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout content = new FrameLayout(this);

        //缩放控件放置在FrameLayout的上层，用于放大缩小图表
        FrameLayout.LayoutParams frameParm = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameParm.gravity = Gravity.BOTTOM | Gravity.RIGHT;

		   /*
		  //缩放控件放置在FrameLayout的上层，用于放大缩小图表
	       mZoomControls = new ZoomControls(this);
	       mZoomControls.setIsZoomInEnabled(true);
	       mZoomControls.setIsZoomOutEnabled(true);
		   mZoomControls.setLayoutParams(frameParm);
		   */

        //图表显示范围在占屏幕大小的90%的区域内
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int scrWidth = (int) (dm.widthPixels * 0.9);
        int scrHeight = (int) (dm.heightPixels * 0.9);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                scrWidth, scrHeight);

        //居中显示
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        //图表view放入布局中，也可直接将图表view放入Activity对应的xml文件中
        final RelativeLayout chartLayout = new RelativeLayout(this);

        chartLayout.addView(new LineChart02View(mContext), layoutParams);
        llMain.addView(chartLayout);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_wenshidu_chuanguanqi;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("温湿度传感器");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2021/1/7 添加ccid 和 ccidup
                SuiYiTieSetting.actionStart(mContext, "", "");
            }
        });
        iv_rightTitle.setBackgroundResource(R.mipmap.fengnuan_icon_shezhi);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, WenShiDuChuanGanQiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
