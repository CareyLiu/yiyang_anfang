package com.yiyang.cn.activity.a_yiyang.activity.jijin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.dialog.InputDialog;
import com.yiyang.cn.util.Y;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JijinActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_select1)
    TextView tvSelect1;
    @BindView(R.id.tv_select2)
    TextView tvSelect2;
    @BindView(R.id.tv_select3)
    TextView tvSelect3;
    @BindView(R.id.tv_select4)
    TextView tvSelect4;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.ll_card)
    LinearLayout llCard;
    @BindView(R.id.iv_zhuyi)
    ImageView ivZhuyi;
    @BindView(R.id.bt_xiayibu)
    TextView btXiayibu;

    private String name;
    private String card;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jijin;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JijinActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    public boolean isImmersive() {
        return true;
    }

    @Override
    public void initImmersion() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

    }

    @OnClick({R.id.rl_back, R.id.tv_select1, R.id.tv_select2, R.id.tv_select3, R.id.tv_select4, R.id.ll_name, R.id.ll_card, R.id.bt_xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
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
            case R.id.ll_name:
                clickName();
                break;
            case R.id.ll_card:
                clickCard();
                break;
            case R.id.bt_xiayibu:
                clickXiayibu();
                break;
        }
    }

    private void clickXiayibu() {
        if (TextUtils.isEmpty(name)) {
            Y.t("请输入姓名");
            return;
        }

        if (TextUtils.isEmpty(card)) {
            Y.t("请输入身份证号");
            return;
        }

        JijinQuerenActivity.actionStart(mContext,name,card);
        finish();
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


    private void clickName() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                name = dialog.getTextContent();
                tvName.setText(name);

            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入姓名");
        dialog.setTextContent(tvName.getText().toString());
        dialog.show();
    }


    private void clickCard() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                card = dialog.getTextContent();
                tvCard.setText(card);

            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入身份证号");
        dialog.setTextContent(tvCard.getText().toString());
        dialog.show();
    }
}
