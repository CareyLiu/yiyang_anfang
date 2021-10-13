package com.yiyang.cn.activity.a_yiyang.activity.jijin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.activity.pinggu.AddJiarenActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.config.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JijinListActivity extends BaseActivity {


    @BindView(R.id.iv_banner)
    Banner iv_banner;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jijin_list;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JijinQuerenActivity.class);
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
        tv_title.setText("养老基金");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("保单管理");
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JinjinguanliActivity.actionStart(mContext);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initBanner();
    }

    private void initBanner() {
        List<Integer> items = new ArrayList<>();
        items.add(R.mipmap.yanglaojijin_banner);
        items.add(R.mipmap.yanglaojijin_banner_2);
        items.add(R.mipmap.yanglaojijin_banner_3);

        //设置图片加载器
        iv_banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        iv_banner.setImages(items);
        //banner设置方法全部调用完毕时最后调用
        iv_banner.start();
    }

    @OnClick({R.id.iv1, R.id.iv2, R.id.iv3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv1:
            case R.id.iv2:
            case R.id.iv3:
                JijinActivity.actionStart(mContext);
                break;
        }
    }
}
