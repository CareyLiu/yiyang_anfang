package com.smarthome.magic.activity;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.ConsultListAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.ConsultModel;
import com.smarthome.magic.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ConsultActiviy extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.list)
    LRecyclerView list;

    List<ConsultModel.DataBean> modelList = new ArrayList<>();
    ConsultListAdapter consultListAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    String servicefromId,state = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        ButterKnife.bind(this);
        tvTitle.setText(getIntent().getStringExtra("title"));
        state = getIntent().getStringExtra("state");
        View mEmptyView = findViewById(R.id.empty_view);
        list.setEmptyView(mEmptyView);
        consultListAdapter = new ConsultListAdapter(this);
        consultListAdapter.setDataList(modelList);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(consultListAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(lRecyclerViewAdapter);

        list.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                requestData(servicefromId,state);
            }
        });
        list.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                modelList.clear();
                requestData("",state);
            }
        });
        list.refresh();

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(View view, int position) {
                PreferenceHelper.getInstance(ConsultActiviy.this).putString("service_form_id",modelList.get(position).getService_form_id());
                //此处title参数用来区分是车主端还是客服端

            }
        });
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

    public void requestData(final String fromId,String state){
        Map<String, String> map = new HashMap<>();
        map.put("code", "03317");
        map.put("key", Constant.KEY);
        map.put("token", UserManager.getManager(ConsultActiviy.this).getAppToken());
        map.put("service_form_id",fromId);
        map.put("state",state);
        Gson gson = new Gson();
        OkGo.<AppResponse<ConsultModel.DataBean>>post(Constant.SERVER_URL + "wit/app/car/witAgent")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ConsultModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ConsultModel.DataBean>> response) {
                        modelList.addAll(response.body().data);
                        consultListAdapter.setDataList(modelList);
                        list.refreshComplete(10);
                        lRecyclerViewAdapter.notifyDataSetChanged();
                        if (response.body().data.size()>0){
                            servicefromId = modelList.get(modelList.size()-1).getService_form_id();
                        }else {
                            if (!fromId.equals(""))
                                list.setNoMore(true);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ConsultModel.DataBean>> response) {
                        AlertUtil.t(ConsultActiviy.this,response.getException().getMessage());
                    }
                });
    }
}
