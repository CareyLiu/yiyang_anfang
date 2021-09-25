package com.yiyang.cn.activity.a_yiyang;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class YiShengZhuYeActivity extends BaseActivity {
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_yisheng_ming)
    TextView tvYishengMing;
    @BindView(R.id.view_guahao)
    View viewGuahao;
    @BindView(R.id.view_jieshao)
    View viewJieshao;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_yishengzhuye;
    }

    @OnClick({R.id.view_guahao, R.id.view_jieshao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_guahao:
                break;
            case R.id.view_jieshao:
                break;
        }
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("医生主页");
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

}
