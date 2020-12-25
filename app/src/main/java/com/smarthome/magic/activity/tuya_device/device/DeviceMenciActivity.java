package com.smarthome.magic.activity.tuya_device.device;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.TuyaBaseDeviceActivity;
import com.smarthome.magic.activity.tuya_device.device.adapter.MenciAdapter;
import com.smarthome.magic.activity.tuya_device.device.model.DpModel;
import com.smarthome.magic.activity.tuya_device.device.tongyong.DeviceSetActivity;
import com.smarthome.magic.activity.tuya_device.utils.TuyaDialogUtils;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaDeviceManager;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.ITuyaDataCallback;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class DeviceMenciActivity extends TuyaBaseDeviceActivity {

    @BindView(R.id.iv_dianchi_state)
    ImageView iv_dianchi_state;
    @BindView(R.id.tv_dianchi_state)
    TextView tv_dianchi_state;
    @BindView(R.id.view_device_state)
    View view_device_state;
    @BindView(R.id.tv_device_state)
    TextView tv_device_state;
    @BindView(R.id.iv_menci_left)
    ImageView iv_menci_left;
    @BindView(R.id.iv_menci_right)
    ImageView iv_menci_right;
    @BindView(R.id.switch_gaojing)
    ImageView switch_gaojing;
    @BindView(R.id.switch_dianliang)
    ImageView switch_dianliang;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private String ty_device_ccid;
    private String old_name;
    private String room_name;
    private ITuyaDevice mDevice;
    private DeviceBean mDeviceBeen;
    private Boolean isOnline;
    private String productId;

    private List<DpModel.DpsBean> dps = new ArrayList<>();
    private MenciAdapter adapter;
    private int offset;

    private int dianliang;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String member_type, String device_id, String ty_device_ccid, String old_name, String room_name) {
        Intent intent = new Intent(context, DeviceMenciActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("member_type", member_type);
        intent.putExtra("device_id", device_id);
        intent.putExtra("ty_device_ccid", ty_device_ccid);
        intent.putExtra("old_name", old_name);
        intent.putExtra("room_name", room_name);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_menci;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("门磁传感器");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setImageResource(R.mipmap.mine_shezhi);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set();
            }
        });
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
        initHuidiao();
        initAdapter();
        initSM();
        getRefresh();
    }

    private void init() {
        ty_device_ccid = getIntent().getStringExtra("ty_device_ccid");
        room_name = getIntent().getStringExtra("room_name");
        old_name = getIntent().getStringExtra("old_name");

        DeviceBean haveDevice = TuyaHomeManager.getHomeManager().isHaveDevice(ty_device_ccid);
        if (haveDevice != null) {
            TuyaDeviceManager.getDeviceManager().initDevice(haveDevice);
            initMenci();
        } else {
            TuyaDialogUtils.t(mContext, "设备已失效!");
        }
    }

    private void initMenci() {
        mDeviceBeen = TuyaDeviceManager.getDeviceManager().getDeviceBeen();
        mDevice = TuyaDeviceManager.getDeviceManager().getDevice();
        productId = mDeviceBeen.getProductId();
        isOnline = mDeviceBeen.getIsOnline();
        setIsOnline();

        Map<String, Object> dps = mDeviceBeen.getDps();
        dianliang = (int) dps.get("103");
        Y.e("电量是多少少 " + dianliang);
    }

    private void setIsOnline() {
        if (isOnline) {
            view_device_state.setBackgroundResource(R.drawable.bg_zhineng_device_online);
            tv_device_state.setText("在线");
        } else {
            view_device_state.setBackgroundResource(R.drawable.bg_zhineng_device_offline);
            tv_device_state.setText("离线");
        }
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(mDeviceBeen.getDevId())) {
                        getData((String) message.content);
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_REMOVED) {
                    String devId = TuyaDeviceManager.getDeviceManager().getDeviceBeen().getDevId();
                    String ccc = message.devId;
                    if (ccc.equals(devId)) {
                        TuyaDeviceManager.getDeviceManager().device_removed(DeviceMenciActivity.this);
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_STATUSCHANGED) {
                    if (message.devId.equals(mDeviceBeen.getDevId())) {
                        isOnline = (boolean) message.content;
                        if (!isOnline) {
                            Y.e("离线了啊啊啊啊");
                        }
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_NETWORKSTATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_DEVINFOUPDATE) {


                }else if (message.type == ConstanceValue.MSG_DEVICE_DELETE) {
                    finish();
                }
            }
        }));
    }

    public void getData(String dpStr) {
        JSONObject jsonObject = JSON.parseObject(dpStr);
        Set<String> strings = jsonObject.keySet();
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            // 获得key
            String key = it.next();
            String value = jsonObject.getString(key);
            jieData(key, value, jsonObject);
        }
    }

    public void jieData(String key, String value, JSONObject jsonObject) {
        Y.e("解析出的数据:  " + "key = " + key + "  |  value = " + value);
        if (key.equals("101")) {
            DpModel.DpsBean dpsBean = new DpModel.DpsBean();
            String data = Y.getDataS(new Date());
            dpsBean.setTimeStr(data);
            dpsBean.setValue(value);
            dps.add(0, dpsBean);
            offset++;
            adapter.notifyDataSetChanged();
        } else if (key.equals("103")) {

        }
    }

    private void initAdapter() {
        adapter = new MenciAdapter(R.layout.item_tuya_device_menci, dps);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(adapter);
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getRefresh();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                lordMore();
            }
        });
    }

    private void lordMore() {
        Map<String, Object> map = new HashMap<>();
        map.put("devId", ty_device_ccid);
        map.put("dpIds", "101");
        map.put("offset", offset);
        map.put("limit", 10);
        TuyaHomeSdk.getRequestInstance().requestWithApiName("tuya.m.smart.operate.all.log", "1.0", map, DpModel.class, new ITuyaDataCallback<DpModel>() {
            @Override
            public void onSuccess(DpModel dpModel) {
                List<DpModel.DpsBean> dpsNext = dpModel.getDps();
                dps.addAll(dpsNext);
                offset = offset + dpsNext.size();
                adapter.setNewData(dps);
                adapter.notifyDataSetChanged();
                smartRefreshLayout.finishLoadMore();
            }

            @Override
            public void onError(String s, String s1) {
                smartRefreshLayout.finishLoadMore();
            }
        });
    }

    private void getRefresh() {
        offset = 0;
        Map<String, Object> map = new HashMap<>();
        map.put("devId", ty_device_ccid);
        map.put("dpIds", "101");
        map.put("offset", offset);
        map.put("limit", 10);
        TuyaHomeSdk.getRequestInstance().requestWithApiName("tuya.m.smart.operate.all.log", "1.0", map, DpModel.class, new ITuyaDataCallback<DpModel>() {
            @Override
            public void onSuccess(DpModel dpModel) {
                dps = dpModel.getDps();
                offset = offset + dps.size();
                adapter.setNewData(dps);
                adapter.notifyDataSetChanged();
                smartRefreshLayout.finishRefresh();
            }

            @Override
            public void onError(String s, String s1) {
                smartRefreshLayout.finishRefresh();
            }
        });
    }


    @OnClick({R.id.switch_gaojing, R.id.switch_dianliang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.switch_gaojing:
                clickGaojing();
                break;
            case R.id.switch_dianliang:
                clickDianliang();
                break;
        }
    }

    private void clickGaojing() {

    }

    private void clickDianliang() {
//        getDp(mDevice, "103");
    }

    private void set() {
        DeviceSetActivity.actionStart(mContext,
                getIntent().getStringExtra("member_type"),
                getIntent().getStringExtra("device_id"),
                getIntent().getStringExtra("old_name"),
                getIntent().getStringExtra("room_name")
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TuyaDeviceManager.getDeviceManager().unRegisterDevListener();
    }
}
