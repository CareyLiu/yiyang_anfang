package com.smarthome.magic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarthome.magic.R;
import com.smarthome.magic.adapter.ZhiNengDeviceListAdapter;
import com.smarthome.magic.util.GridAverageUIDecoration;
import com.smarthome.magic.util.GridSectionAverageGapItemDecoration;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.smarthome.magic.view.RecycleItemSpance;

import org.jaaksi.pickerview.util.Util;

import java.util.ArrayList;
import java.util.List;


public class ZhiNengDeviceFragment extends Fragment {

    private View viewLayout;
    private LinearLayout ll_content_bg;
    private RecyclerView recyclerView;
    private ZhiNengDeviceListAdapter zhiNengDeviceListAdapter;
    private List<ZhiNengHomeBean.DataBean.DeviceBean> dataBean = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLayout = inflater.inflate(R.layout.fragment_zhineng_device, container, false);
        initView(viewLayout);
        return viewLayout;
    }

    public static ZhiNengDeviceFragment newInstance(Bundle bundle) {
        ZhiNengDeviceFragment fragment = new ZhiNengDeviceFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initView(View view) {
        ll_content_bg = view.findViewById(R.id.ll_content_bg);
        recyclerView = view.findViewById(R.id.recyclerView);
       // recyclerView.addItemDecoration(new RecycleItemSpance(20, 2));
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.addItemDecoration(new GridAverageUIDecoration(14, 10));

        recyclerView.setLayoutManager(layoutManager);
        zhiNengDeviceListAdapter = new ZhiNengDeviceListAdapter(R.layout.item_zhineng_device, dataBean);
        zhiNengDeviceListAdapter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.activity_zhineng_device_none, null));
        zhiNengDeviceListAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(zhiNengDeviceListAdapter);
    }

    public void onRefresh() {
        if (getArguments() != null) {
            List<ZhiNengHomeBean.DataBean.DeviceBean> device = getArguments().getParcelableArrayList("device");
            dataBean.addAll(device);
            zhiNengDeviceListAdapter.notifyDataSetChanged();
        }
    }
}
