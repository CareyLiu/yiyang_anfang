package com.yiyang.cn.activity.nongye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.view.WeatherChartView;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.util.Y;

import butterknife.BindView;
import butterknife.OnClick;


public class ZaihaiyujingActivity extends BaseActivity {


    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.bt_diwen)
    TextView btDiwen;
    @BindView(R.id.bt_ganhan)
    TextView btGanhan;
    @BindView(R.id.line_char)
    WeatherChartView chartView;

    @Override
    public int getContentViewResId() {
        return R.layout.nongye_act_zaihaiyujing;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ZaihaiyujingActivity.class);
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
        tv_title.setText("灾害预警");
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
        initLineView();
    }

    private void initLineView() {
        // set day
        chartView.setTempDay(new int[]{21, 22, 23, 22, 22, 21, 20});
        // set night
        chartView.setTempNight(new int[]{13, 14, 13, 12, 14, 12, 13});
        chartView.invalidate();
    }

    @OnClick({R.id.tv_city_name, R.id.bt_diwen, R.id.bt_ganhan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_city_name:
                break;
            case R.id.bt_diwen:
                clickDiwen();
                break;
            case R.id.bt_ganhan:
                clickGanhan();
                break;
        }
    }

    private void clickGanhan() {
        btDiwen.setTextColor(Y.getColor(R.color.color_3));
        btGanhan.setTextColor(Y.getColor(R.color.color_nongye_main));

        btDiwen.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_left_nor);
        btGanhan.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_right_sel);
    }

    private void clickDiwen() {
        btDiwen.setTextColor(Y.getColor(R.color.color_nongye_main));
        btGanhan.setTextColor(Y.getColor(R.color.color_3));

        btDiwen.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_left_sel);
        btGanhan.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_right_nor);
    }
}