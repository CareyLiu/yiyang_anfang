package com.yiyang.cn.activity.nongye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.adapter.JidiListAdapter;
import com.yiyang.cn.activity.nongye.model.JidiListModel;
import com.yiyang.cn.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShijiankongActivity extends BaseActivity {

    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    private List<JidiListModel> jidiListModels = new ArrayList<>();
    private JidiListAdapter jidiListAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.nongye_act_list;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShijiankongActivity.class);
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
        tv_title.setText("实时监控");
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
        jidiListModels.add(new JidiListModel("玉米基地"));
        jidiListModels.add(new JidiListModel("土豆基地"));
        jidiListModels.add(new JidiListModel("高粱基地"));
        jidiListModels.add(new JidiListModel("水稻基地"));
        jidiListModels.add(new JidiListModel("大豆基地"));
        jidiListModels.add(new JidiListModel("小麦基地"));

        jidiListAdapter = new JidiListAdapter(R.layout.nongye_item_shishijiankong, jidiListModels);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(jidiListAdapter);
        rv_content.setFocusable(false);
    }
}
