package com.yiyang.cn.activity.nongye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.adapter.DianzishangwuAdapter;
import com.yiyang.cn.activity.nongye.model.DianzishangwuModel;
import com.yiyang.cn.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DianzishangwuTypeActivity extends BaseActivity {


    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    private List<DianzishangwuModel> models = new ArrayList<>();
    private DianzishangwuAdapter adapter;


    @Override
    public int getContentViewResId() {
        return R.layout.nongye_act_dianzishangwu_list;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String name) {
        Intent intent = new Intent(context, DianzishangwuTypeActivity.class);
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
        initAdapdter();
    }

    private void initAdapdter() {
        String name = getIntent().getStringExtra("name");
        tv_title.setText(name);
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic1, name, "永兴老李水稻","1.04"));
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic2, name, "五常水稻","1.45"));
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic2, name, "永旺水稻","1.86"));
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic1, name, "兴村水稻","2.12"));
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic1, name, "永兴老李水稻","1.04"));
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic2, name, "五常水稻","1.45"));
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic2, name, "永旺水稻","1.86"));
        models.add(new DianzishangwuModel(R.mipmap.nongye_pic1, name, "兴村水稻","2.12"));

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
}
