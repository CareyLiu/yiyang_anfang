package com.yiyang.cn.activity.nongye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.adapter.ShuyuanDetailsAdapter;
import com.yiyang.cn.activity.nongye.model.ShuyuanDetailsModel;
import com.yiyang.cn.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChanpinshuyuanDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    private List<ShuyuanDetailsModel> detailsModels = new ArrayList<>();
    private ShuyuanDetailsAdapter detailsAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.nongye_act_chanpinshuyuan_details;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String name) {
        Intent intent = new Intent(context, ChanpinshuyuanDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("农产品溯源信息");
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
        tv_name.setText(getIntent().getStringExtra("name"));
        initAdapdter();
    }

    private void initAdapdter() {
        detailsModels.add(new ShuyuanDetailsModel("收获", "2021-05-25"));
        detailsModels.add(new ShuyuanDetailsModel("植保", "2021-05-05"));
        detailsModels.add(new ShuyuanDetailsModel("施有机肥", "2021-04-13"));
        detailsModels.add(new ShuyuanDetailsModel("施无机肥", "2021-04-02"));
        detailsModels.add(new ShuyuanDetailsModel("喷药", "2021-02-25"));
        detailsModels.add(new ShuyuanDetailsModel("种植", "2021-01-12"));
        detailsModels.add(new ShuyuanDetailsModel("挖坑", "2021-01-11"));
        detailsModels.add(new ShuyuanDetailsModel("育苗", "2021-01-01"));
        detailsAdapter = new ShuyuanDetailsAdapter(detailsModels, mContext);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(detailsAdapter);
    }
}
