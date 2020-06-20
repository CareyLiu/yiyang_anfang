package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.ZhiNengRoomManageAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_Success;
import com.smarthome.magic.dialog.ZhiNengFamilyAddDIalog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengRoomManageBean;
import com.smarthome.magic.model.ZhiNengRoomManageCreatBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengRoomManageActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_room_add)
    ImageView iv_room_add;
    private Context context = ZhiNengRoomManageActivity.this;
    private String member_type = "";
    private String family_id = "";
    private List<ZhiNengRoomManageBean.DataBean> dataBeanList = new ArrayList<>();
    private ZhiNengRoomManageAdapter zhiNengRoomManageAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhineng_room_manage;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightMode(this);
        initToolbar();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        member_type = getIntent().getStringExtra("member_type");
        if (member_type == null) {
            member_type = "";
        }
        family_id = getIntent().getStringExtra("family_id");
        if (family_id == null) {
            family_id = "";
        }
        getnet();
    }

    private void initView() {
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getnet();
            }
        });
        srLSmart.setEnableLoadMore(false);
        iv_room_add.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        zhiNengRoomManageAdapter = new ZhiNengRoomManageAdapter(R.layout.item_zhineng_room, dataBeanList);
        zhiNengRoomManageAdapter.setEmptyView(LayoutInflater.from(context).inflate(R.layout.activity_zhineng_room_none, null));
        zhiNengRoomManageAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(zhiNengRoomManageAdapter);
        zhiNengRoomManageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZhiNengRoomManageBean.DataBean dataBean = (ZhiNengRoomManageBean.DataBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("room_id", dataBean.getRoom_id());
                bundle.putString("family_id", dataBean.getFamily_id());
                bundle.putString("room_name", dataBean.getRoom_name());
                bundle.putString("member_type", member_type);
                startActivity(new Intent(context, ZhiNengRoomSettingActivity.class).putExtras(bundle));
            }
        });
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ROOM_MANAGE_ADD) {
                    creatRoom(message.content.toString());
                }
            }
        }));
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("房间管理");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_room_add:
                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(context, ConstanceValue.MSG_ROOM_MANAGE_ADD);
                zhiNengFamilyAddDIalog.show();
                break;
        }
    }

    private void getnet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16023");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("family_id", family_id);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengRoomManageBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengRoomManageBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengRoomManageBean.DataBean>> response) {
                        if (srLSmart != null) {
                            srLSmart.setEnableRefresh(true);
                            srLSmart.finishRefresh();
                            srLSmart.setEnableLoadMore(false);
                        }
                        dataBeanList.clear();
                        dataBeanList.addAll(response.body().data);
                        zhiNengRoomManageAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 创建房间
     */
    private void creatRoom(String roomName) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16021");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("family_id", family_id);
        map.put("room_name", roomName);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengRoomManageCreatBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengRoomManageCreatBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengRoomManageCreatBean>> response) {
                        getnet();
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengRoomManageCreatBean>> response) {
                        String str = response.getException().getMessage();
                        Log.i("cuifahuo", str);
                        String[] str1 = str.split("：");
                        if (str1.length == 3) {
                            MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,
                                    "提示", str1[2], "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {

                                }
                            });
                            myCarCaoZuoDialog_caoZuoTIshi.show();
                        }
                    }
                });
    }

}
