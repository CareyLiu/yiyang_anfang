package com.yiyang.cn.activity.a_yiyang.activity.jinlaoka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class JinglaokaWuActivity extends BaseActivity {


    @BindView(R.id.tv_shiyongxuzhi)
    TextView tvShiyongxuzhi;
    @BindView(R.id.tv_yewufufu)
    TextView tvYewufufu;
    @BindView(R.id.bt_xiayibu)
    TextView btXiayibu;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jinglaoka_wu;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JinglaokaWuActivity.class);
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
        tv_title.setText("敬老卡");
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
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_YIYANG_JINGLAOKA) {
                    finish();
                }
            }
        }));
    }

    @OnClick({R.id.tv_shiyongxuzhi, R.id.tv_yewufufu, R.id.bt_xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shiyongxuzhi:
                break;
            case R.id.tv_yewufufu:
                break;
            case R.id.bt_xiayibu:
                JinglaokaShenqingActivity.actionStart(mContext);
                break;
        }
    }
}
