package com.yiyang.cn.activity.a_yiyang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.dialog.newdia.TishiDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuodongLaoActivity extends BaseActivity {


    @BindView(R.id.bt_baoming)
    Button btBaoming;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_huodong_laonian;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, HuodongLaoActivity.class);
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
        tv_title.setText("老年活动");
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

    @OnClick(R.id.bt_baoming)
    public void onViewClicked() {
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
        dialog.setTextContent("报名成功！!");
        dialog.show();
    }
}
