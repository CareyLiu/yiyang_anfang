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
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.MasterListAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;

import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.MasterModel;
import com.smarthome.magic.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.view.ViewGroup.FOCUS_AFTER_DESCENDANTS;

public class ServiceMasterFragment extends BaseFragment {

    @BindView(R.id.list)
    LRecyclerView list;
    Unbinder unbinder;

    List<MasterModel.DataBean> modelList = new ArrayList<>();
    MasterListAdapter masterListAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;
    String servicefromId, userPhone, plateNumber = "";

    View header = null;
    EditText etNumber;
    EditText etPhone;
    LinearLayout layoutQuery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_master, container, false);
        view.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件
        unbinder = ButterKnife.bind(this, view);

        masterListAdapter = new MasterListAdapter(getActivity());
        masterListAdapter.setDataList(modelList);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(masterListAdapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setAdapter(lRecyclerViewAdapter);
        header = LayoutInflater.from(getActivity()).inflate(R.layout.list_master_header, (ViewGroup) getActivity().findViewById(android.R.id.content), false);
        etPhone = header.findViewById(R.id.et_phone);
        etNumber = header.findViewById(R.id.et_number);
        layoutQuery = header.findViewById(R.id.layout_query);
        layoutQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plateNumber = etNumber.getText().toString();
                userPhone = etPhone.getText().toString();
                modelList.clear();
                requestData("", plateNumber, userPhone);
            }
        });
        lRecyclerViewAdapter.addHeaderView(header);
        //设置头部加载颜色
        list.setHeaderViewColor(R.color.blue_light, R.color.blue_light, R.color.transparent);
        //设置底部加载颜色
        list.setFooterViewColor(R.color.blue_light, R.color.blue_light, R.color.transparent);
        //设置底部加载文字提示
        list.setFooterViewHint("正在加载更多信息", "我是有底线的", "网络不给力啊，点击再试一次吧");

        list.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                requestData(servicefromId, plateNumber, userPhone);
            }
        });
        list.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                modelList.clear();
                requestData("", plateNumber, userPhone);
            }
        });
        list.refresh();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void requestData(final String fromId, String plate_number, String user_phone) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03319");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("plate_number", plate_number);
        map.put("user_phone", user_phone);
        map.put("of_user_id", fromId);
        Gson gson = new Gson();
        OkGo.<AppResponse<MasterModel.DataBean>>post(Urls.SERVER_URL + "wit/app/car/witAgent")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MasterModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<MasterModel.DataBean>> response) {
                        modelList.addAll(response.body().data);
                        masterListAdapter.setDataList(modelList);
                        list.refreshComplete(10);
                        lRecyclerViewAdapter.notifyDataSetChanged();
                        if (response.body().data.size() > 0) {
                            servicefromId = modelList.get(modelList.size() - 1).getOf_user_id();
                        } else {
                            if (!fromId.equals(""))
                                list.setNoMore(true);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<MasterModel.DataBean>> response) {
                        AlertUtil.t(getActivity(), response.getException().getMessage());
                    }
                });
    }


}
