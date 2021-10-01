package com.yiyang.cn.activity.a_yiyang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.adapter.JiashuAdapter;
import com.yiyang.cn.activity.a_yiyang.model.JiashuModel;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.inter.OnItemClickListener;
import com.yiyang.cn.util.Y;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class JiashuDanganActivity extends BaseActivity {


    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    private List<JiashuModel> jiashuModels;
    private JiashuAdapter adapter;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jiashudangan;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JiashuDanganActivity.class);
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
        tv_title.setText("家庭档案");
        tv_title.setTextSize(17);
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("添加家庭成员");
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddJiarenActivity.actionStart(mContext);
            }
        });
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
        initData();
        initHuidiao();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new JiashuAdapter(mContext, jiashuModels);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(adapter);
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                JiashuModel model = jiashuModels.get(position);
            }
        });
    }


    private void initData() {
        String jiashudanganString = PreferenceHelper.getInstance(mContext).getString(AppConfig.YIYANG_GET_JIASHUDANGAN, "");
        Y.e("我获取到的数据是什么呢  " + jiashudanganString);
        jiashuModels = JSON.parseArray(jiashudanganString, JiashuModel.class);
        if (jiashuModels != null && jiashuModels.size() > 0) {

        } else {
            jiashuModels = new ArrayList<>();
        }
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_YIYANG_ADDJIAREN) {
                    JiashuModel modelNew = (JiashuModel) message.content;
                    jiashuModels.add(modelNew);
                    adapter.setJiashuModels(jiashuModels);
                    String jiashudanganString = JSON.toJSONString(jiashuModels);
                    Y.e("我保存的数据是什么呢  " + jiashudanganString);
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.YIYANG_GET_JIASHUDANGAN, jiashudanganString);
                }
            }
        }));
    }
}
