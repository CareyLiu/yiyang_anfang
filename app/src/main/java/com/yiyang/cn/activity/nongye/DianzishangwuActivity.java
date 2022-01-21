package com.yiyang.cn.activity.nongye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.adapter.DianzishangwuAdapter;
import com.yiyang.cn.activity.nongye.model.DianzishangwuModel;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.config.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DianzishangwuActivity extends BaseActivity {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_chuan)
    TextView tv_chuan;
    @BindView(R.id.bt_chuan)
    LinearLayout bt_chuan;
    @BindView(R.id.tv_shan)
    TextView tv_shan;
    @BindView(R.id.bt_shan)
    LinearLayout bt_shan;
    @BindView(R.id.tv_pei)
    TextView tv_pei;
    @BindView(R.id.bt_pei)
    LinearLayout bt_pei;
    @BindView(R.id.tv_za)
    TextView tv_za;
    @BindView(R.id.bt_za)
    LinearLayout bt_za;
    @BindView(R.id.tv_hua)
    TextView tv_hua;
    @BindView(R.id.bt_hua)
    LinearLayout bt_hua;
    @BindView(R.id.tv_ya)
    TextView tv_ya;
    @BindView(R.id.bt_ya)
    LinearLayout bt_ya;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    private List<DianzishangwuModel> models = new ArrayList<>();
    private DianzishangwuAdapter adapter;


    @Override
    public int getContentViewResId() {
        return R.layout.nongye_act_dianzishangwu;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DianzishangwuActivity.class);
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
        tv_title.setText("电子商务");
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
        initView();
        initBanner();
        initAdapdter();
    }

    private void initView() {

    }

    private void initBanner() {
        List<Integer> items = new ArrayList<>();
        items.add(R.mipmap.nongye_shangwu_banner1);
        items.add(R.mipmap.nongye_shangwu_banner2);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(items);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void initAdapdter() {
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic1, "杂交水稻", "永兴老李水稻","1.04"));
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic2, "传统水稻", "五常水稻","1.45"));
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic2, "传统水稻", "永旺水稻","1.86"));
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic1, "杂交水稻", "兴村水稻","2.12"));

        adapter = new DianzishangwuAdapter(R.layout.nongye_item_dianzishangwu, models);
        rv_content.setLayoutManager(new GridLayoutManager(mContext, 2));
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DianzishangwuDetailsActivity.actionStart(mContext,models.get(position));
            }
        });
    }

    @OnClick({R.id.bt_chuan, R.id.bt_shan, R.id.bt_pei, R.id.bt_za, R.id.bt_hua, R.id.bt_ya})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_chuan:
                DianzishangwuTypeActivity.actionStart(mContext,"传统水稻");
                break;
            case R.id.bt_shan:
                DianzishangwuTypeActivity.actionStart(mContext,"下山品种");
                break;
            case R.id.bt_pei:
                DianzishangwuTypeActivity.actionStart(mContext,"组培品种");
                break;
            case R.id.bt_za:
                DianzishangwuTypeActivity.actionStart(mContext,"杂交水稻");
                break;
            case R.id.bt_hua:
                DianzishangwuTypeActivity.actionStart(mContext,"带花品种");
                break;
            case R.id.bt_ya:
                DianzishangwuTypeActivity.actionStart(mContext,"带芽品种");
                break;
        }
    }
}
