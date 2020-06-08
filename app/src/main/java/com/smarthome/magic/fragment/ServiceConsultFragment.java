package com.smarthome.magic.fragment;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.CarListActivity;
import com.smarthome.magic.activity.DiagnosisActivity;
import com.smarthome.magic.adapter.ConsultListAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.ConsultModel;
import com.smarthome.magic.model.SmartDevices;
import com.smarthome.magic.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ServiceConsultFragment extends BaseFragment {

    @BindView(R.id.list)
    LRecyclerView list;
    Unbinder unbinder;

    List<ConsultModel.DataBean> modelList = new ArrayList<>();
    ConsultListAdapter consultListAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    String servicefromId = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_consult, container, false);
        view.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件
        unbinder = ButterKnife.bind(this, view);
        consultListAdapter = new ConsultListAdapter(getActivity());
        consultListAdapter.setDataList(modelList);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(consultListAdapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setAdapter(lRecyclerViewAdapter);

        list.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                requestData(servicefromId);
            }
        });
        list.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                modelList.clear();
                requestData("");
            }
        });
        list.refresh();

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(View view, int position) {
                PreferenceHelper.getInstance(getActivity()).putString("service_form_id",modelList.get(position).getService_form_id());
                //此处title参数用来区分是车主端还是客服端

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void requestData(final String fromId){
        Map<String, String> map = new HashMap<>();
        map.put("code", "03317");
        map.put("key", Constant.KEY);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("service_form_id",fromId);
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
                        AlertUtil.t(getActivity(),response.getException().getMessage());
                    }
                });
    }
}
