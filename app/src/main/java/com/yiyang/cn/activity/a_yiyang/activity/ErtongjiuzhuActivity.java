package com.yiyang.cn.activity.a_yiyang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.adapter.AYiyangAdapter;
import com.yiyang.cn.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ErtongjiuzhuActivity extends BaseActivity {


    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_rv_quanbu;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ErtongjiuzhuActivity.class);
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
        tv_title.setText("儿童救助");
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
        initZhengceAdapter();
    }

    private void initZhengceAdapter() {
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        AYiyangAdapter adapter = new AYiyangAdapter(R.layout.yiyang_item_ertongjiuzhu, strings);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ErtongjiuzhuDetailsActivity.actionStart(mContext);
            }
        });
    }
}
