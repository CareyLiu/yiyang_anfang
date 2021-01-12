package com.smarthome.magic.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.flyco.roundview.RoundRelativeLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;

import butterknife.BindView;

public class ChangJingYiJianZhiXingActivity extends BaseActivity {
    @BindView(R.id.rl_yijianzhixing_tianjiatiaojian)
    RoundRelativeLayout rlYijianzhixingTianjiatiaojian;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.rl_yijianzhixing)
    RoundRelativeLayout rlYijianzhixing;
    @BindView(R.id.ll_tianjiashebei)
    LinearLayout llTianjiashebei;
    @BindView(R.id.rl_changjing)
    RoundRelativeLayout rlChangjing;
    @BindView(R.id.ll_yijian_zhixing)
    LinearLayout llYijianZhixing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlChangjing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangJingTuBiaoActivity.actionStart(mContext);
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_changjing_yijianzhixing;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("场景");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
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
        Intent intent = new Intent(context, ChangJingYiJianZhiXingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
