package com.yiyang.cn.activity.a_yiyang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.activity.falvfuwu.FalvfuwuActivity;
import com.yiyang.cn.activity.a_yiyang.activity.jigou.JigouyanglaoActivity;
import com.yiyang.cn.activity.a_yiyang.activity.jijin.JijinListActivity;
import com.yiyang.cn.activity.a_yiyang.activity.jinlaoka.JinglaokaWuActivity;
import com.yiyang.cn.activity.a_yiyang.activity.jinlaoka.JinglaokaYouActivity;
import com.yiyang.cn.activity.a_yiyang.model.JinglaokaModel;
import com.yiyang.cn.activity.tongcheng58.model.TcHomeModel;
import com.yiyang.cn.activity.a_yiyang.adapter.AYiyangAdapter;
import com.yiyang.cn.activity.a_yiyang.adapter.HomeZhylAdapter;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.config.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabMoreActivity extends BaseActivity {
    @BindView(R.id.rv_tab)
    RecyclerView rv_tab;
    @BindView(R.id.ll_quanbu_huodong)
    LinearLayout ll_quanbu_huodong;
    @BindView(R.id.ll_quanbu_zhengce)
    LinearLayout ll_quanbu_zhengce;
    @BindView(R.id.rv_zhengce)
    RecyclerView rv_zhengce;

    private List<TcHomeModel.DataBean.IconListBean> zhylList = new ArrayList<>();

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_tab_more;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TabMoreActivity.class);
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
        tv_title.setText("更多服务");
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
    }

    private void initAdapter() {
        initTabAdapter();
        initZhengceAdapter();
    }

    private void initZhengceAdapter() {
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        AYiyangAdapter adapter = new AYiyangAdapter(R.layout.yiyang_item_zhengce, strings);
        rv_zhengce.setLayoutManager(new LinearLayoutManager(mContext));
        rv_zhengce.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZhengceDetailsActivity.actionStart(mContext);
            }
        });
    }

    private void initTabAdapter() {
        initZhylList();
        HomeZhylAdapter zhylAdapter = new HomeZhylAdapter(R.layout.yiyang_item_home_zhyl, zhylList);
        rv_tab.setLayoutManager(new GridLayoutManager(mContext, 5));
        rv_tab.setAdapter(zhylAdapter);
        zhylAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TcHomeModel.DataBean.IconListBean listBean = zhylList.get(position);
                switch (position) {
                    case 0:
                        ShequyanglaoActivity.actionStart(mContext);
                        break;
                    case 1:
                        ZhinengActivity.actionStart(mContext);
                        break;
                    case 2:
                        JigouyanglaoActivity.actionStart(mContext);
                        break;
                    case 3:
                        FuwuZhujiActivity.actionStart(mContext, "呼叫中心");
                        break;
                    case 4:
                        HuodongActivity.actionStart(mContext);
                        break;
                    case 5:
                        YiyangTuTActivity.actionStart(mContext, R.mipmap.act_jiankangshuju);
                        break;
                    case 6:
                        ZaixianyishengActivity.actionStart(mContext);
                        break;
                    case 7:
                        FuwuZhucanActivity.actionStart(mContext);
                        break;
                    case 8:
                        ZhuyuActivity.actionStart(mContext);
                        break;
                    case 9:
                        ZhujieActivity.actionStart(mContext);
                        break;
                    case 10:
                        ZhuxingActivity.actionStart(mContext);
                        break;
                    case 11:
                        FuwuZhujiActivity.actionStart(mContext, "助急服务");
                        break;
                    case 12:
                        FalvfuwuActivity.actionStart(mContext);
                        break;
                    case 13:
                        ErtongjiuzhuActivity.actionStart(mContext);
                        break;
                    case 14:
                        CaigouActivity.actionStart(mContext);
                        break;
                    case 15:
                        JijinListActivity.actionStart(mContext);
                        break;
                    case 16:
                        clickYanglaoka();
                        break;
                    case 17:

                        break;
                }
            }
        });
    }

    private void clickYanglaoka() {
        String yaolaoka = PreferenceHelper.getInstance(mContext).getString(AppConfig.YIYANG_YANGLAOKA, "");
        JinglaokaModel jinglaokaModel = JSON.parseObject(yaolaoka, JinglaokaModel.class);
        if (jinglaokaModel!=null){
            if (TextUtils.isEmpty(jinglaokaModel.getHaveCard())){
                JinglaokaWuActivity.actionStart(mContext);
            }else {
                JinglaokaYouActivity.actionStart(mContext,jinglaokaModel);
            }
        }else {
            JinglaokaWuActivity.actionStart(mContext);
        }
    }

    private void initZhylList() {
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_shequyanglao, "社区养老"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jujiayanglao, "居家养老"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jigouyanglao, "机构养老"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_hujiaozhongxin, "呼叫中心"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_huodongzhongxin, "活动中心"));

        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jiankangshuju, "健康数据"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhuyifuwu, "助医服务"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhucanfuwu, "助餐服务"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhuyufuwu, "助浴服务"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhujiefuwu, "助洁服务"));

        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhuxingfuwu, "助行服务"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_fuwutaocan, "助急服务"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_falvfuwu, "法律服务"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_ertongjiuzhu, "儿童救助"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhengfucaigou, "政府采购"));

        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_yanglaojijin, "养老基金"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jinglaoka, "敬老卡"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_gouyao, "便民购药"));
    }

    @OnClick({R.id.ll_quanbu_huodong, R.id.ll_quanbu_zhengce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_quanbu_huodong:
            case R.id.ll_quanbu_zhengce:
                ZhengceActivity.actionStart(mContext);
                break;
        }
    }
}
