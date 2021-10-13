package com.yiyang.cn.activity.a_yiyang.activity.jinlaoka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.model.JinglaokaModel;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.dialog.InputDialog;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.util.Y;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShenheActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.ll_name)
    LinearLayout ll_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;
    @BindView(R.id.tv_card)
    TextView tv_card;
    @BindView(R.id.ll_card)
    LinearLayout ll_card;
    @BindView(R.id.bt_shenhe)
    TextView bt_shenhe;

    private String name;
    private String card;
    private String phone;
    private JinglaokaModel model;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jinglaoka_shenhe;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, JinglaokaModel model) {
        Intent intent = new Intent(context, ShenheActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("model", model);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("年检审核");
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
        model = (JinglaokaModel) getIntent().getSerializableExtra("model");
        name = model.getName();
        card = model.getCard();
        phone = model.getPhone();

        tv_name.setText(name);
        tv_card.setText(card);
        tv_phone.setText(phone);
    }

    @OnClick({R.id.ll_name, R.id.ll_phone, R.id.ll_card, R.id.bt_shenhe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_name:
                clickName();
                break;
            case R.id.ll_phone:
                clickPhone();
                break;
            case R.id.ll_card:
                clickCard();
                break;
            case R.id.bt_shenhe:
                clickShenhe();
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
                tv_name.setText(name);

            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入姓名");
        dialog.setTextContent(tv_name.getText().toString());
        dialog.show();
    }

    private void clickPhone() {
        InputDialog dialog = new InputDialog(mContext, new InputDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, InputDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, InputDialog dialog) {
                phone = dialog.getTextContent();
                tv_phone.setText(phone);
            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextInput(InputType.TYPE_CLASS_NUMBER);
        dialog.setTextTitle("请输入手机号");
        dialog.setTextContent(tv_phone.getText().toString());
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
                tv_card.setText(card);

            }

            @Override
            public void onDismiss(InputDialog dialog) {
                Y.hideInputMethod(dialog.tv_content);
            }
        });

        dialog.setTextTitle("请输入身份证号");
        dialog.setTextContent(tv_card.getText().toString());
        dialog.show();
    }

    private void clickShenhe() {
        if (TextUtils.isEmpty(name)) {
            Y.t("请输入姓名");
            return;
        }

        if (TextUtils.isEmpty(card)) {
            Y.t("请输入身份证号");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            Y.t("请输入手机号");
            return;
        }

        ShenheJieguoActivity.actionStart(mContext);
        finish();
    }
}
