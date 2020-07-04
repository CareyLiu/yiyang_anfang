package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.ZhiNengHomeListAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.smarthome.magic.model.ZhiNengHomeListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengHomeListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    TextView tv_family_name;
    TextView tv_family_num;
    LinearLayout ll_family_manage;
    private Context context = ZhiNengHomeListActivity.this;
    private ZhiNengHomeListAdapter zhiNengHomeListAdapter;
    private List<ZhiNengHomeListBean.DataBean> dataBean = new ArrayList<>();
    private View headView, footerView;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhineng_home_list;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightMode(this);
        initToolbar();
        initView();
        showLoadFailed();
        getnet();
    }


    @Override
    protected void onLoadRetry() {
        super.onLoadRetry();
        getnet();
    }

    private void initView() {
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                showLoading();
                getnet();
            }
        });
        srLSmart.setEnableLoadMore(false);
        headView = LayoutInflater.from(context).inflate(R.layout.activity_zhineng_familylist_head, null);
        footerView = LayoutInflater.from(context).inflate(R.layout.activity_zhineng_familylist_footer, null);
        tv_family_name = headView.findViewById(R.id.tv_family_name);
        tv_family_num = headView.findViewById(R.id.tv_family_num);
        ll_family_manage = footerView.findViewById(R.id.ll_family_manage);
        ll_family_manage.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        zhiNengHomeListAdapter = new ZhiNengHomeListAdapter(R.layout.item_zhineng_home, dataBean);
        recyclerView.setLayoutManager(layoutManager);
        zhiNengHomeListAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(zhiNengHomeListAdapter);
        zhiNengHomeListAdapter.addHeaderView(headView);
        zhiNengHomeListAdapter.addFooterView(footerView);
        zhiNengHomeListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZhiNengHomeListBean.DataBean dataBean = (ZhiNengHomeListBean.DataBean) adapter.getItem(position);
                checkFamily(dataBean);
            }
        });
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("切换家庭");
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_family_manage:
                startActivity(new Intent(context, ZhiNengFamilyManageActivity.class));
                break;
        }
    }

    private void checkFamily(ZhiNengHomeListBean.DataBean dataBean) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16014");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("family_id", dataBean.getFamily_id());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengHomeBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengHomeBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengHomeBean.DataBean>> response) {
                        if (response.body().msg.equals("ok")) {
                            finish();
                            //切换家庭成功 发通知 ， 通知首页刷新

                            Notice notice = new Notice();
                            notice.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                            sendRx(notice);
                        }
                    }
                });
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16013");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengHomeListBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengHomeListBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengHomeListBean.DataBean>> response) {
                        if (srLSmart != null) {
                            srLSmart.setEnableRefresh(true);
                            srLSmart.finishRefresh();
                            srLSmart.setEnableLoadMore(false);
                        }
                        dataBean.clear();
                        dataBean.addAll(response.body().data);

                        for (int i = 0; i < dataBean.size(); i++) {
                            if (dataBean.get(i).getActive().equals("1")) {
                                tv_family_name.setText(dataBean.get(i).getFamily_name());
                            }
                        }
                        tv_family_num.setText(dataBean.size() + "个家庭");
                        zhiNengHomeListAdapter.notifyDataSetChanged();
                        showLoadSuccess();

                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengHomeListBean.DataBean>> response) {
                        super.onError(response);
                        showLoadFailed();


                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }

                    @Override
                    public void onStart(Request<AppResponse<ZhiNengHomeListBean.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();

                    }
                });
    }

}
