package com.yiyang.cn.activity.a_yiyang.activity.jinlaoka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.util.Y;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JinglaokaChongzhiActivity extends BaseActivity {

    @BindView(R.id.tv_select1)
    TextView tvSelect1;
    @BindView(R.id.tv_select2)
    TextView tvSelect2;
    @BindView(R.id.tv_select3)
    TextView tvSelect3;
    @BindView(R.id.tv_select4)
    TextView tvSelect4;
    @BindView(R.id.iv_zhifubao)
    ImageView ivZhifubao;
    @BindView(R.id.rl_zhifubao)
    RelativeLayout rlZhifubao;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.rl_weixin)
    RelativeLayout rlWeixin;
    @BindView(R.id.bt_chongzhi)
    TextView btChongzhi;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jinglaoka_chongzhi;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JinglaokaChongzhiActivity.class);
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
        tv_title.setText("充值");
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

    @OnClick({R.id.tv_select1, R.id.tv_select2, R.id.tv_select3, R.id.tv_select4, R.id.rl_zhifubao, R.id.rl_weixin, R.id.bt_chongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select1:
                select(1);
                break;
            case R.id.tv_select2:
                select(2);
                break;
            case R.id.tv_select3:
                select(3);
                break;
            case R.id.tv_select4:
                select(4);
                break;
            case R.id.rl_zhifubao:
                selectPayZfb();
                break;
            case R.id.rl_weixin:
                selectPayWX();
                break;
            case R.id.bt_chongzhi:
                clickChongzhi();
                break;
        }
    }

    private void clickChongzhi() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {

            }

            @Override
            public void onDismiss(TishiDialog dialog) {
                finish();
            }
        });
        dialog.setTextContent("充值成功");
        dialog.show();
    }

    private void selectPayWX() {
        ivZhifubao.setImageResource(R.mipmap.tuya_faxian_icon_selector_nor);
        ivWeixin.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
    }

    private void selectPayZfb() {
        ivZhifubao.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        ivWeixin.setImageResource(R.mipmap.tuya_faxian_icon_selector_nor);
    }

    private void select(int i) {
        tvSelect1.setTextColor(Y.getColor(R.color.color_9));
        tvSelect2.setTextColor(Y.getColor(R.color.color_9));
        tvSelect3.setTextColor(Y.getColor(R.color.color_9));
        tvSelect4.setTextColor(Y.getColor(R.color.color_9));
        tvSelect1.setBackgroundResource(R.drawable.yiyang_jijin_select_nor);
        tvSelect2.setBackgroundResource(R.drawable.yiyang_jijin_select_nor);
        tvSelect3.setBackgroundResource(R.drawable.yiyang_jijin_select_nor);
        tvSelect4.setBackgroundResource(R.drawable.yiyang_jijin_select_nor);
        switch (i) {
            case 1:
                tvSelect1.setTextColor(Y.getColor(R.color.color_main_yiyang));
                tvSelect1.setBackgroundResource(R.drawable.yiyang_jijin_select_sel);
                break;
            case 2:
                tvSelect2.setTextColor(Y.getColor(R.color.color_main_yiyang));
                tvSelect2.setBackgroundResource(R.drawable.yiyang_jijin_select_sel);
                break;
            case 3:
                tvSelect3.setTextColor(Y.getColor(R.color.color_main_yiyang));
                tvSelect3.setBackgroundResource(R.drawable.yiyang_jijin_select_sel);
                break;
            case 4:
                tvSelect4.setTextColor(Y.getColor(R.color.color_main_yiyang));
                tvSelect4.setBackgroundResource(R.drawable.yiyang_jijin_select_sel);
                break;
        }
    }
}
