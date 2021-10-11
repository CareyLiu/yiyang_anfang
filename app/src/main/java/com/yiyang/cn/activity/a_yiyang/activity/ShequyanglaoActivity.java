package com.yiyang.cn.activity.a_yiyang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.adapter.AYiyangAdapter;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.config.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShequyanglaoActivity extends BaseActivity {


    @BindView(R.id.iv_zhucan)
    ImageView ivZhucan;
    @BindView(R.id.iv_zhujie)
    ImageView ivZhujie;
    @BindView(R.id.iv_zhuyu)
    ImageView ivZhuyu;
    @BindView(R.id.iv_zhuyi)
    ImageView ivZhuyi;
    @BindView(R.id.iv_zhuxing)
    ImageView ivZhuxing;
    @BindView(R.id.iv_zhuji)
    ImageView ivZhuji;
    @BindView(R.id.ll_shequhuodong)
    LinearLayout llShequhuodong;
    @BindView(R.id.rv_shequhuodong)
    RecyclerView rvShequhuodong;
    @BindView(R.id.banner)
    Banner banner;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_shequyanglao;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShequyanglaoActivity.class);
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
        tv_title.setText("社区养老");
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
        initAdapter();
        initBanner();
    }

    private void initBanner() {
        List<Integer> items = new ArrayList<>();
        items.add(R.mipmap.banner_1);
        items.add(R.mipmap.banner_2);
        items.add(R.mipmap.banner_3);
        items.add(R.mipmap.banner_4);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(items);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void initAdapter() {
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        AYiyangAdapter adapter = new AYiyangAdapter(R.layout.yiyang_item_shequhuodong, strings);
        rvShequhuodong.setLayoutManager(new LinearLayoutManager(mContext));
        rvShequhuodong.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZhengceDetailsActivity.actionStart(mContext);
            }
        });
    }

    @OnClick({R.id.iv_zhucan, R.id.iv_zhujie, R.id.iv_zhuyu, R.id.iv_zhuyi, R.id.iv_zhuxing, R.id.iv_zhuji, R.id.ll_shequhuodong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_zhucan:
                FuwuZhucanActivity.actionStart(mContext);
                break;
            case R.id.iv_zhujie:
                ZhujieActivity.actionStart(mContext);
                break;
            case R.id.iv_zhuyu:
                ZhuyuActivity.actionStart(mContext);
                break;
            case R.id.iv_zhuyi:
                ZaixianyishengActivity.actionStart(mContext);
                break;
            case R.id.iv_zhuxing:
                ZhuxingActivity.actionStart(mContext);
                break;
            case R.id.iv_zhuji:
                FuwuZhujiActivity.actionStart(mContext, "助急服务");
                break;
            case R.id.ll_shequhuodong:
                ShequhuodongActivity.actionStart(mContext);
                break;
        }
    }
}
