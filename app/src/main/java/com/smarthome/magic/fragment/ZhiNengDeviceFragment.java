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
import com.smarthome.magic.view.RecycleItemSpance;

import org.jaaksi.pickerview.util.Util;

import java.util.ArrayList;


public class ZhiNengDeviceFragment extends Fragment {

    private View viewLayout;
    private LinearLayout ll_content_bg;
    private RecyclerView recyclerView;
    private ZhiNengDeviceListAdapter zhiNengDeviceListAdapter;

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
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<String> shopList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            shopList.add("");
        }
        zhiNengDeviceListAdapter = new ZhiNengDeviceListAdapter(R.layout.item_zhineng_device, shopList);
        zhiNengDeviceListAdapter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.activity_zhineng_device_none, null));
        zhiNengDeviceListAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(zhiNengDeviceListAdapter);

        if (shopList.size() == 0) {
            ll_content_bg.setPadding(Util.dip2px(getActivity(), 14), 0, Util.dip2px(getActivity(), 14), 0);
        } else {
            ll_content_bg.setPadding(Util.dip2px(getActivity(), 10), 0, Util.dip2px(getActivity(), 10), 0);
            recyclerView.addItemDecoration(new RecycleItemSpance(20, 2));
        }
    }
}
