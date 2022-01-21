package com.yiyang.cn.activity.nongye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.util.Y;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QixiangjianceActivity extends BaseActivity {


    @BindView(R.id.bt_wendu)
    TextView bt_wendu;
    @BindView(R.id.bt_voc)
    TextView bt_voc;
    @BindView(R.id.bt_shidu)
    TextView bt_shidu;
    @BindView(R.id.view_line_left)
    View view_line_left;
    @BindView(R.id.view_line_right)
    View view_line_right;
    @BindView(R.id.tv_shuzhi)
    TextView tv_shuzhi;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_danqian_text)
    TextView tv_danqian_text;

    @Override
    public int getContentViewResId() {
        return R.layout.nongye_act_qixiangjiance;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, QixiangjianceActivity.class);
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
        tv_title.setText("气象环境监测");
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
    }

    @OnClick({R.id.bt_wendu, R.id.bt_voc, R.id.bt_shidu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_wendu:
                clickWendu();
                break;
            case R.id.bt_voc:
                clickVoc();
                break;
            case R.id.bt_shidu:
                clickShidu();
                break;
        }
    }

    private void clickVoc() {
        bt_wendu.setTextColor(Y.getColor(R.color.color_3));
        bt_shidu.setTextColor(Y.getColor(R.color.color_3));
        bt_voc.setTextColor(Y.getColor(R.color.color_nongye_main));

        bt_wendu.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_left_nor);
        bt_shidu.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_center_nor);
        bt_voc.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_right_sel);

        view_line_left.setBackgroundColor(Y.getColor(R.color.color_nongye_zaihai));
        view_line_right.setBackgroundColor(Y.getColor(R.color.color_nongye_main));

        tv_shuzhi.setText("120g/L");
        tv_name.setText("当前VOC");
        tv_danqian_text.setText("注意：当前空气质量正常");
    }

    private void clickShidu() {
        bt_wendu.setTextColor(Y.getColor(R.color.color_nongye_main));
        bt_shidu.setTextColor(Y.getColor(R.color.color_nongye_main));
        bt_voc.setTextColor(Y.getColor(R.color.color_3));

        bt_wendu.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_left_nor);
        bt_shidu.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_center_sel);
        bt_voc.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_right_nor);

        view_line_left.setBackgroundColor(Y.getColor(R.color.color_nongye_main));
        view_line_right.setBackgroundColor(Y.getColor(R.color.color_nongye_main));

        tv_shuzhi.setText("60%");
        tv_name.setText("当前湿度");
        tv_danqian_text.setText("注意：当前湿度正常");
    }

    private void clickWendu() {
        bt_wendu.setTextColor(Y.getColor(R.color.color_nongye_main));
        bt_shidu.setTextColor(Y.getColor(R.color.color_3));
        bt_voc.setTextColor(Y.getColor(R.color.color_3));

        bt_wendu.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_left_sel);
        bt_shidu.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_center_nor);
        bt_voc.setBackgroundResource(R.drawable.yiyang_nongye_zaihai_right_nor);

        view_line_left.setBackgroundColor(Y.getColor(R.color.color_nongye_main));
        view_line_right.setBackgroundColor(Y.getColor(R.color.color_nongye_zaihai));

        tv_shuzhi.setText("20℃");
        tv_name.setText("当前温度");
        tv_danqian_text.setText("注意：当前温度正常");
    }
}
