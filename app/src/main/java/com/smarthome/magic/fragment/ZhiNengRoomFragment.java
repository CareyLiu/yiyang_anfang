package com.smarthome.magic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.ZhiNengRoomManageActivity;
import com.smarthome.magic.activity.ZhiNengRoomSettingActivity;
import com.smarthome.magic.adapter.ZhiNengRoomListAdapter;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.smarthome.magic.util.GridAverageUIDecoration;
import com.smarthome.magic.view.XRecyclerView;

import org.jaaksi.pickerview.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;


public class ZhiNengRoomFragment extends BaseFragment implements View.OnClickListener {
    private View viewLayout;
    private View footerView;
    private XRecyclerView recyclerView;
    private ZhiNengRoomListAdapter zhiNengRoomListAdapter;
    private List<ZhiNengHomeBean.DataBean.RoomBean> mDatas = new ArrayList<>();
    private LinearLayout ll_room_add;
    private TextView tv_room_add;
    private String member_type = "";
    private String family_id = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        viewLayout = inflater.inflate(R.layout.fragment_zhineng_room, container, false);
        initView1(viewLayout);
        return viewLayout;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_zhineng_room;
    }

    @Override
    protected void initView(View rootView) {

    }

    public static ZhiNengRoomFragment newInstance(Bundle bundle) {
        ZhiNengRoomFragment fragment = new ZhiNengRoomFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN) {
                    getnet();
                }
            }
        }));
        getnet();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        //UIHelper.ToastMessage(getActivity(), "页面可见");
        getnet();
    }

    private void initView1(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        footerView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_zhineng_room_footer, null);
        ll_room_add = footerView.findViewById(R.id.ll_room_add);
        ll_room_add.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        zhiNengRoomListAdapter = new ZhiNengRoomListAdapter(R.layout.item_zhineng_room, mDatas);
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

//    public void onRefresh() {
//        if (getArguments() != null) {
//            List<ZhiNengHomeBean.DataBean.RoomBean> room = getArguments().getParcelableArrayList("room");
//            member_type = getArguments().getString("member_type");
//            family_id = getArguments().getString("family_id");
//            roomBeanList.clear();
//            roomBeanList.addAll(room);
//            if(zhiNengRoomListAdapter!=null){
//                zhiNengRoomListAdapter.notifyDataSetChanged();
//            }
//
//        }
//    }

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

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16001");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengHomeBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengHomeBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengHomeBean.DataBean>> response) {
                        PreferenceHelper.getInstance(getActivity()).putString(AppConfig.FAMILY_ID, response.body().data.get(0).getFamily_id());
                        mDatas.clear();
                        mDatas.addAll(response.body().data.get(0).getRoom());

                        member_type = response.body().data.get(0).getMember_type();

                        if (zhiNengRoomListAdapter != null) {
                            zhiNengRoomListAdapter.notifyDataSetChanged();
                        }

                        if (recyclerView != null) {
                            for (int i = 0; i < recyclerView.getItemDecorationCount(); i++) {
                                recyclerView.removeItemDecorationAt(i);
                            }
                            if (mDatas.size() == 0) {
                                recyclerView.addItemDecoration(new GridAverageUIDecoration(0, 10));
                            } else {
                                recyclerView.addItemDecoration(new GridAverageUIDecoration(14, 10));
                            }
                        }
                    }
                });
    }

}
