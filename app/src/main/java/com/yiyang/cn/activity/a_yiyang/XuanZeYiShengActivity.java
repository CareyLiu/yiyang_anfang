package com.yiyang.cn.activity.a_yiyang;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yiyang.cn.R;
import com.yiyang.cn.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class XuanZeYiShengActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    XuanZeYiShengAdapter xuanZeYiShengAdapter;

    List<String> mDatas = new ArrayList<>();
    String menzhen = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menzhen = getIntent().getStringExtra("menzhen");
        for (int i = 0; i < 100; i++) {
            mDatas.add("1");
        }
        initAdapter();
        tv_title.setText(menzhen);
    }

    private void initAdapter() {
        xuanZeYiShengAdapter = new XuanZeYiShengAdapter(R.layout.item_xuanze, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setAdapter(xuanZeYiShengAdapter);
        xuanZeYiShengAdapter.setNewData(mDatas);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_xuanze_yisheng;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String string) {
        Intent intent = new Intent(context, XuanZeYiShengActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("menzhen", string);
        context.startActivity(intent);
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
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }
}
