package com.smarthome.magic.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.ZhiNengRoomManageSettingAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengRoomManageBean;
import com.smarthome.magic.model.ZhiNengRoomManageSettingBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengRoomSettingActivity extends BaseActivity {

    @BindView(R.id.tv_room_name)
    TextView tv_room_name;
    @BindView(R.id.tv_room_num)
    TextView tv_room_num;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Context context = ZhiNengRoomSettingActivity.this;
    private String room_id = "";
    private String family_id = "";
    private String room_name = "";
    private List<ZhiNengRoomManageSettingBean.DataBean> dataBeanList = new ArrayList<>();
    private ZhiNengRoomManageSettingAdapter zhiNengRoomManageSettingAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhineng_room_setting;
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
        room_id = getIntent().getStringExtra("room_id");
        if (room_id == null) {
            room_id = "";
        }
        family_id = getIntent().getStringExtra("family_id");
        if (family_id == null) {
            family_id = "";
        }
        room_name = getIntent().getStringExtra("room_name");
        if (room_name == null) {
            room_name = "";
        }
        getnet();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        zhiNengRoomManageSettingAdapter = new ZhiNengRoomManageSettingAdapter(R.layout.item_zhineng_room_setting, dataBeanList);
        zhiNengRoomManageSettingAdapter.setEmptyView(LayoutInflater.from(context).inflate(R.layout.activity_zhineng_room_setting_none, null));
        zhiNengRoomManageSettingAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(zhiNengRoomManageSettingAdapter);

        zhiNengRoomManageSettingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZhiNengRoomManageSettingBean.DataBean dataBean = (ZhiNengRoomManageSettingBean.DataBean) adapter.getItem(position);
            }
        });
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("房间设置");
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

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16026");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("room_id", room_id);
        map.put("family_id", family_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengRoomManageSettingBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengRoomManageSettingBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengRoomManageSettingBean.DataBean>> response) {
                        dataBeanList.clear();
                        dataBeanList.addAll(response.body().data);
                        tv_room_name.setText(room_name);
                        tv_room_num.setText("该房间有" + dataBeanList.size() + "个设备");
                        zhiNengRoomManageSettingAdapter.notifyDataSetChanged();
                    }
                });
    }
}
