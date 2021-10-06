package com.yiyang.cn.activity.a_yiyang.activity.pinggu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.adapter.AYiyangAdapter;
import com.yiyang.cn.activity.a_yiyang.adapter.JiashuAdapter;
import com.yiyang.cn.activity.a_yiyang.adapter.PingguAdapter;
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
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class YanglaopingguActivity extends BaseActivity {


    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.tv_pinggu)
    TextView tv_pinggu;

    private List<JiashuModel> jiashuModels;
    private PingguAdapter adapter;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_yanglaopinggu;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, YanglaopingguActivity.class);
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
        tv_title.setText("养老评估");
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
        initData();
        initHuidiao();
        initAdapter();
    }

    @OnClick(R.id.tv_pinggu)
    public void onViewClicked() {
        AddJiarenActivity.actionStart(mContext,"2");
    }

    private void initAdapter() {
        adapter = new PingguAdapter(R.layout.yiyang_item_yanglaopinggu, jiashuModels);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                JiashuModel model = jiashuModels.get(position);
                EditJiarenActivity.actionStart(mContext, model, position);
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
                    adapter.setNewData(jiashuModels);
                    adapter.notifyDataSetChanged();
                    String jiashudanganString = JSON.toJSONString(jiashuModels);
                    Y.e("我保存的数据是什么呢  " + jiashudanganString);
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.YIYANG_GET_JIASHUDANGAN, jiashudanganString);
                } else if (message.type == ConstanceValue.MSG_YIYANG_DELETEJIAREN) {
                    int pos = (int) message.content;
                    jiashuModels.remove(pos);
                    adapter.setNewData(jiashuModels);
                    adapter.notifyDataSetChanged();
                    String jiashudanganString = JSON.toJSONString(jiashuModels);
                    Y.e("我保存的数据是什么呢  " + jiashudanganString);
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.YIYANG_GET_JIASHUDANGAN, jiashudanganString);
                } else if (message.type == ConstanceValue.MSG_YIYANG_EDITJAREN) {
                    Y.e("愧疚反倒是李开复但是发生的");
                    JiashuModel modelNew = (JiashuModel) message.content;
                    int pos = message.pinggu_id;
                    jiashuModels.set(pos, modelNew);
                    adapter.setNewData(jiashuModels);
                    adapter.notifyDataSetChanged();
                    String jiashudanganString = JSON.toJSONString(jiashuModels);
                    Y.e("我保存的数据是什么呢  " + jiashudanganString);
                    PreferenceHelper.getInstance(mContext).putString(AppConfig.YIYANG_GET_JIASHUDANGAN, jiashudanganString);
                }
            }
        }));
    }
}
