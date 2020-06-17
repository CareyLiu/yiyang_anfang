package com.smarthome.magic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.smarthome.magic.R;
import com.smarthome.magic.adapter.ZhiNengRoomListAdapter;
import com.smarthome.magic.view.XRecyclerView;

import org.jaaksi.pickerview.util.Util;

import java.util.ArrayList;


public class ZhiNengRoomFragment extends Fragment {
    private View viewLayout;
    private View footerView;
    private XRecyclerView recyclerView;
    private ZhiNengRoomListAdapter zhiNengRoomListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        viewLayout = inflater.inflate(R.layout.fragment_zhineng_room, container, false);
        initView(viewLayout);
        return viewLayout;
    }

    public static ZhiNengRoomFragment newInstance(Bundle bundle) {
        ZhiNengRoomFragment fragment = new ZhiNengRoomFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        footerView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_zhineng_room_footer, null);
        recyclerView.addFooterView(footerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<String> shopList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            shopList.add("");
        }
        zhiNengRoomListAdapter = new ZhiNengRoomListAdapter(R.layout.item_zhineng_room, shopList);
        zhiNengRoomListAdapter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.activity_zhineng_room_none, null));
        zhiNengRoomListAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(zhiNengRoomListAdapter);
    }
}
