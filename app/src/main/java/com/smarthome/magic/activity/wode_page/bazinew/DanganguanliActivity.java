package com.smarthome.magic.activity.wode_page.bazinew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.smarthome.magic.activity.wode_page.bazinew.model.DanganModel;
import com.smarthome.magic.activity.wode_page.bazinew.adapter.DananguanliAdapter;
import com.smarthome.magic.activity.wode_page.bazinew.utils.BaziCode;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DanganguanliActivity extends BaziBaseActivity {
    @BindView(R.id.lv_dangan)
    ListView lv_dangan;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.bt_add)
    Button bt_add;

    private List<DanganModel.DataBean> list = new ArrayList<>();
    private DananguanliAdapter adapter;
    private int code;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_danganguanli;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("档案管理");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNet();
    }

    private void init() {
        code = getIntent().getIntExtra("code", 0);
        initAdapter();
        initSM();
    }

    private void initAdapter() {
        adapter = new DananguanliAdapter(list, this);
        lv_dangan.setAdapter(adapter);
        lv_dangan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list != null && list.size() > position) {
                    DanganModel.DataBean dataBean = list.get(position);
                    if (code == BaziCode.ST_mingpan) {
                        Intent intent = new Intent(DanganguanliActivity.this, MingpanActivity.class);
                        intent.putExtra("model", dataBean);
                        startActivity(intent);
                    } else if (code == BaziCode.ST_nian || code == BaziCode.ST_yue || code == BaziCode.ST_ri ) {
                        Intent intent = new Intent(DanganguanliActivity.this, YunshiActivity.class);
                        intent.putExtra("mingpan_id", dataBean.getMingpan_id());
                        intent.putExtra("code", code);
                        startActivity(intent);
                    } else if (code == BaziCode.ST_yanpan) {
                        Intent intent = new Intent(DanganguanliActivity.this, YanpanActivity.class);
                        intent.putExtra("model", dataBean);
                        startActivity(intent);
                    }else if (code == BaziCode.ST_chuanyi) {
                        Intent intent = new Intent(DanganguanliActivity.this, YunshiChuanyiActivity.class);
                        intent.putExtra("mingpan_id", dataBean.getMingpan_id());
                        intent.putExtra("code", code);
                        startActivity(intent);
                    }
                }
            }
        });

        adapter.setEditClick(new DananguanliAdapter.OnEditClick() {
            @Override
            public void click(int pos) {
                if (list != null && list.size() > pos) {
                    DanganModel.DataBean dataBean = list.get(pos);
                    Intent intent = new Intent(DanganguanliActivity.this, DanganEditActivity.class);
                    intent.putExtra("model", dataBean);
                    startActivity(intent);
                }
            }
        });
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                page_number = 0;
                getNet();
            }
        });


        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                page_number++;
                getNet();
            }
        });
    }


    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11013");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<DanganModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DanganModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DanganModel.DataBean>> response) {
                        smartRefreshLayout.finishLoadMore();
                        smartRefreshLayout.finishRefresh();

                        list = response.body().data;
                        adapter.setList(list);
                    }
                });
    }

    @OnClick(R.id.bt_add)
    public void onViewClicked() {
        openActivity(DanganNewActivity.class);
    }
}
