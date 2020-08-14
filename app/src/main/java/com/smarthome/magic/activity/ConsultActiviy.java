package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.aakefudan.adapter.ZixunAdapter;
import com.smarthome.magic.aakefudan.base.ServiceBaseActivity;
import com.smarthome.magic.adapter.ConsultListAdapter;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ConsultModel;
import com.smarthome.magic.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imlib.model.Conversation;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class ConsultActiviy extends ServiceBaseActivity {

    @BindView(R.id.lv_dangan)
    RecyclerView lv_dangan;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private View mEmptyView;

    List<ConsultModel.DataBean> modelList = new ArrayList<>();
    String servicefromId, state = "";
    private ZixunAdapter adapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_consult;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mEmptyView = findViewById(R.id.empty_view);
        tv_title.setText(getIntent().getStringExtra("title"));
        state = getIntent().getStringExtra("state");
        initAdapter();
        initSM();
        getNet();
        initHuidiao();
    }

    private boolean isOn = false;

    @Override
    protected void onPause() {
        super.onPause();
        isOn = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isOn = true;
        adapter.notifyDataSetChanged();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_RONGYUN_REVICE) {
                    if (isOn) {
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }));
    }

    private void initAdapter() {
        adapter = new ZixunAdapter(R.layout.item_consult, modelList);
        lv_dangan.setLayoutManager(new LinearLayoutManager(mContext));
        lv_dangan.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ConsultModel.DataBean dataBean = modelList.get(position);
                Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
                String of_user_accid = dataBean.getOf_user_accid();
                String service_form_id = dataBean.getService_form_id();
                String instName = dataBean.getUser_name_car();
                Bundle bundle = new Bundle();
                bundle.putString("dianpuming", instName);
                bundle.putString("inst_accid", of_user_accid);
                startConversation(mContext, conversationType, of_user_accid, instName, bundle, service_form_id);
            }
        });
    }

    public void startConversation(Context context, Conversation.ConversationType conversationType, String targetId, String title, Bundle bundle, String service_form_id) {
        if (context != null && !TextUtils.isEmpty(targetId) && conversationType != null) {
            Uri uri = Uri.parse("rong://" + context.getApplicationInfo().processName).buildUpon().appendPath("conversationnew").appendPath(conversationType.getName().toLowerCase(Locale.US)).appendQueryParameter("targetId", targetId).appendQueryParameter("title", title).build();
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.putExtra("service_form_id", service_form_id);
            context.startActivity(intent);
        }
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getMore();
            }
        });
    }

    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03317");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("state", state);
        Gson gson = new Gson();
        OkGo.<AppResponse<ConsultModel.DataBean>>post(Urls.SERVER_URL + "wit/app/car/witAgent")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ConsultModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ConsultModel.DataBean>> response) {
                        modelList = response.body().data;
                        adapter.setNewData(modelList);
                        adapter.notifyDataSetChanged();
                        if (modelList.size() > 0) {
                            servicefromId = modelList.get(modelList.size() - 1).getService_form_id();
                            mEmptyView.setVisibility(View.GONE);
                        } else {
                            mEmptyView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ConsultModel.DataBean>> response) {
                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    public void getMore() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03317");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("service_form_id", servicefromId);
        map.put("state", state);
        Gson gson = new Gson();
        OkGo.<AppResponse<ConsultModel.DataBean>>post(Urls.SERVER_URL + "wit/app/car/witAgent")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ConsultModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ConsultModel.DataBean>> response) {
                        modelList.addAll(response.body().data);
                        adapter.setNewData(modelList);
                        adapter.notifyDataSetChanged();
                        if (modelList.size() > 0) {
                            servicefromId = modelList.get(modelList.size() - 1).getService_form_id();
                            mEmptyView.setVisibility(View.GONE);
                        } else {
                            mEmptyView.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ConsultModel.DataBean>> response) {
                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }
}
