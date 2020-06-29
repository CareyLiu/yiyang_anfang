package com.smarthome.magic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.ZhiNengRoomManageActivity;
import com.smarthome.magic.activity.ZhiNengRoomSettingActivity;
import com.smarthome.magic.adapter.ZhiNengRoomListAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.smarthome.magic.view.XRecyclerView;

import org.jaaksi.pickerview.util.Util;

import java.util.ArrayList;
import java.util.List;


public class ZhiNengRoomFragment extends Fragment implements View.OnClickListener {
    private View viewLayout;
    private View footerView;
    private XRecyclerView recyclerView;
    private ZhiNengRoomListAdapter zhiNengRoomListAdapter;
    private List<ZhiNengHomeBean.DataBean.RoomBean> roomBeanList = new ArrayList<>();
    private LinearLayout ll_room_add;
    private TextView tv_room_add;
    private String member_type = "";
    private String family_id = "";

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
        ll_room_add = footerView.findViewById(R.id.ll_room_add);
        ll_room_add.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        zhiNengRoomListAdapter = new ZhiNengRoomListAdapter(R.layout.item_zhineng_room, roomBeanList);
        zhiNengRoomListAdapter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.activity_zhineng_room_none, null));
        tv_room_add = zhiNengRoomListAdapter.getEmptyView().findViewById(R.id.tv_room_add);
        tv_room_add.setOnClickListener(this);
        zhiNengRoomListAdapter.openLoadAnimation();//默认为渐显效果
        zhiNengRoomListAdapter.addFooterView(footerView);
        recyclerView.setAdapter(zhiNengRoomListAdapter);
        zhiNengRoomListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZhiNengHomeBean.DataBean.RoomBean roomBean = (ZhiNengHomeBean.DataBean.RoomBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("room_id", roomBean.getRoom_id());
                bundle.putString("family_id", roomBean.getFamily_id());
                bundle.putString("room_name", roomBean.getRoom_name());
                bundle.putString("member_type", member_type);
                startActivity(new Intent(getActivity(), ZhiNengRoomSettingActivity.class).putExtras(bundle));
            }
        });
    }

    public void onRefresh() {
        if (getArguments() != null) {
            List<ZhiNengHomeBean.DataBean.RoomBean> room = getArguments().getParcelableArrayList("room");
            member_type = getArguments().getString("member_type");
            family_id = getArguments().getString("family_id");
            roomBeanList.clear();
            roomBeanList.addAll(room);
            if(zhiNengRoomListAdapter!=null){
                zhiNengRoomListAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_room_add:
                Bundle bundle1 = new Bundle();
                bundle1.putString("member_type", member_type);
                bundle1.putString("family_id", family_id);
                startActivity(new Intent(getActivity(), ZhiNengRoomManageActivity.class).putExtras(bundle1));
                break;
            case R.id.ll_room_add:
                Bundle bundle = new Bundle();
                bundle.putString("member_type", member_type);
                bundle.putString("family_id", family_id);
                startActivity(new Intent(getActivity(), ZhiNengRoomManageActivity.class).putExtras(bundle));
                break;
        }
    }
}
