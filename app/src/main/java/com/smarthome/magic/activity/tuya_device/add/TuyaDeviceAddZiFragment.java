package com.smarthome.magic.activity.tuya_device.add;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.add.adapter.TuyaDeviceAdapter;
import com.smarthome.magic.activity.tuya_device.add.model.TuyaAddDeviceModel;
import com.smarthome.magic.activity.tuya_device.utils.OnTuyaItemClickListener;
import com.smarthome.magic.activity.tuya_device.utils.WifiReceiver;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.tuya.smart.android.ble.ITuyaBleOperator;
import com.tuya.smart.android.ble.api.ITuyaBleConfigListener;
import com.tuya.smart.android.ble.api.ScanDeviceBean;
import com.tuya.smart.android.ble.api.ScanType;
import com.tuya.smart.android.ble.api.TyBleScanResponse;
import com.tuya.smart.android.blemesh.api.ITuyaBlueMeshActivatorListener;
import com.tuya.smart.android.blemesh.api.ITuyaBlueMeshSearch;
import com.tuya.smart.android.blemesh.api.ITuyaBlueMeshSearchListener;
import com.tuya.smart.android.blemesh.bean.SearchDeviceBean;
import com.tuya.smart.android.blemesh.builder.SearchBuilder;
import com.tuya.smart.android.blemesh.builder.TuyaSigMeshActivatorBuilder;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.ConfigProductInfoBean;
import com.tuya.smart.home.sdk.builder.ActivatorBuilder;
import com.tuya.smart.sdk.api.ITuyaActivator;
import com.tuya.smart.sdk.api.ITuyaActivatorGetToken;
import com.tuya.smart.sdk.api.ITuyaDataCallback;
import com.tuya.smart.sdk.api.ITuyaSmartActivatorListener;
import com.tuya.smart.sdk.api.bluemesh.ITuyaBlueMeshActivator;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.bean.ProductBean;
import com.tuya.smart.sdk.bean.SigMeshBean;
import com.tuya.smart.sdk.enums.ActivatorModelEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class TuyaDeviceAddZiFragment extends BaseFragment {
    @BindView(R.id.iv_wifi)
    ImageView iv_wifi;
    @BindView(R.id.ll_wifi)
    LinearLayout ll_wifi;
    @BindView(R.id.iv_lanya)
    ImageView iv_lanya;
    @BindView(R.id.ll_lanya)
    LinearLayout ll_lanya;
    @BindView(R.id.bt_search_device)
    TextView bt_search_device;
    @BindView(R.id.ll_peizhi)
    RelativeLayout ll_peizhi;
    @BindView(R.id.rv_shebei)
    RecyclerView rv_shebei;
    @BindView(R.id.rl_shuoming)
    RelativeLayout rl_shuoming;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.bt_xiugai)
    TextView bt_xiugai;
    @BindView(R.id.rl_sousuo)
    RelativeLayout rl_sousuo;
    @BindView(R.id.bt_xiayibu)
    TextView bt_xiayibu;
    @BindView(R.id.rl_faxian)
    RelativeLayout rl_faxian;
    @BindView(R.id.bt_chongxinsousuo)
    TextView bt_chongxinsousuo;

    private String wifiSSid;
    private String mima;
    private long homeId;
    private boolean isSetWifi;
    private boolean isSetLanya;
    private boolean isSousuozhong;
    private List<DeviceBean> deviceBeans = new ArrayList<>();
    private TuyaDeviceAdapter adapter;
    private BluetoothAdapter bluetoothAdapter;
    private ITuyaActivator mTuyaActivator;
    private ITuyaBleOperator bleOperator;
    private String familyId;
    private ITuyaBlueMeshSearchListener iTuyaBlueMeshSearchListener;
    private SigMeshBean sigMeshBean;

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_device_add_zi;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        init();
        initLanya();
        initHuidiao();
        initAdapter();
        return rootView;
    }

    private void initAdapter() {
        adapter = new TuyaDeviceAdapter(deviceBeans, getContext());
        rv_shebei.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rv_shebei.setAdapter(adapter);
        adapter.setListener(new OnTuyaItemClickListener() {
            @Override
            public void onItmeCilck(int pos) {
                DeviceBean deviceBean = deviceBeans.get(pos);
                Y.t("点击的了设备" + deviceBean.getName() + "  " + deviceBean.getIconUrl());
            }
        });
    }

    private void initLanya() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            if (bluetoothAdapter.isEnabled()) {
                isSetLanya = true;
                iv_lanya.setVisibility(View.VISIBLE);
            } else {
                isSetLanya = false;
                iv_lanya.setVisibility(View.GONE);
            }
        } else {
            isSetLanya = false;
            iv_lanya.setVisibility(View.GONE);
        }
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_WIFI_SET) {
                    wifiSSid = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_ADMIN, "");
                    mima = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_MIMA, "");
                    if (!TextUtils.isEmpty(wifiSSid) && !TextUtils.isEmpty(mima)) {
                        isSetWifi = true;
                        iv_wifi.setVisibility(View.VISIBLE);
                    } else {
                        isSetWifi = false;
                        iv_wifi.setVisibility(View.GONE);
                    }

                    if (isSousuozhong) {
                        if (mTuyaActivator != null) {
                            mTuyaActivator.stop();
                            starPeiwang();
                        }
                    }
                } else if (message.type == ConstanceValue.MSG_WIFI_INFO) {
                    if (!TextUtils.isEmpty(wifiSSid) && !TextUtils.isEmpty(mima)) {
                        isSetWifi = true;
                        iv_wifi.setVisibility(View.VISIBLE);
                    } else {
                        isSetWifi = false;
                        iv_wifi.setVisibility(View.GONE);
                    }
                } else if (message.type == ConstanceValue.MSG_WIFI_CLOSE) {
                    isSetWifi = false;
                    iv_wifi.setVisibility(View.GONE);
                }
            }
        }));
    }

    private void init() {
        homeId = TuyaHomeManager.getHomeManager().getHomeId();

        wifiSSid = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_ADMIN, "");
        mima = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_MIMA, "");
        familyId = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.PEIWANG_FAMILYID, "");

        if (!TextUtils.isEmpty(wifiSSid) && !TextUtils.isEmpty(mima)) {
            if (wifiSSid.equals(WifiReceiver.WIFI_NAME)) {
                isSetWifi = true;
                iv_wifi.setVisibility(View.VISIBLE);
            } else {
                isSetWifi = false;
                iv_wifi.setVisibility(View.GONE);
            }
        } else {
            isSetWifi = false;
            iv_wifi.setVisibility(View.GONE);
        }

        isSousuozhong = false;
    }

    @OnClick({R.id.ll_wifi, R.id.ll_lanya, R.id.bt_search_device, R.id.bt_xiugai, R.id.bt_chongxinsousuo, R.id.bt_xiayibu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_wifi:
                TuyaDevicePeiwangActivity.actionStart(getContext());
                break;
            case R.id.ll_lanya:
                clickLanya();
                break;
            case R.id.bt_search_device:
            case R.id.bt_chongxinsousuo:
                clickSearch();
                break;
            case R.id.bt_xiugai:
                break;
            case R.id.bt_xiayibu:
                clickXiayibu();
                break;
        }
    }

    private void clickXiayibu() {
        List<TuyaAddDeviceModel> deviceModels = new ArrayList<>();
        for (int i = 0; i < deviceBeans.size(); i++) {
            DeviceBean deviceBean = deviceBeans.get(i);
            TuyaAddDeviceModel model = new TuyaAddDeviceModel();
            model.setDeviceId(deviceBean.getDevId());
            model.setName(deviceBean.getName());
            model.setIcon(deviceBean.getIconUrl());
            model.setSelect(true);
            deviceModels.add(model);
        }
        TuyaDeviceAddFinishActivity.actionStart(getContext(), deviceModels);
    }

    private void clickSearch() {
        if (isSetWifi) {
            starPeiwang();
        } else {
            Y.t("请配置wifi");
        }
    }

    private void starPeiwang() {
        ll_peizhi.setVisibility(View.GONE);
        rl_sousuo.setVisibility(View.VISIBLE);
        rl_shuoming.setVisibility(View.VISIBLE);
        rv_shebei.setVisibility(View.GONE);
        bt_chongxinsousuo.setVisibility(View.GONE);

        isSousuozhong = true;
        startAnimation();
        TuyaHomeSdk.getActivatorInstance().getActivatorToken(homeId,
                new ITuyaActivatorGetToken() {
                    @Override
                    public void onSuccess(String token) {
                        Y.e("获取Token成功：" + token + "   账号：" + wifiSSid + "  密码：" + mima);
                        startWifiPeiwang(token);
                        startLanyaPeiwang(token);
                        startLanyaMeshPeiwang();
                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        Y.t(s1);
                        stopPeiwang();
                    }
                });
    }

    private void startLanyaMeshPeiwang() {
        Y.e("是否执行蓝牙Mesh配网      " + isSetLanya);
        sigMeshBean = TuyaHomeManager.getHomeManager().getSigMeshBean();
        if (isSetLanya && sigMeshBean != null) {
            Y.e("执行了蓝牙Mesh配网      ");
            iTuyaBlueMeshSearchListener = new ITuyaBlueMeshSearchListener() {
                @Override
                public void onSearched(SearchDeviceBean deviceBean) {
                    Y.e("我扫描到了Mesh设备" + deviceBean.getMeshName() + "   " + deviceBean.getProductId() + "   " + deviceBean.getMacAdress());
                    stopSearch();
                    jixua(deviceBean);
                }

                @Override
                public void onSearchFinish() {
                    Y.e("蓝牙Mesh搜索结束了");
                }
            };
            // 待配网的SigMesh设备UUID是固定的
            UUID[] MESH_PROVISIONING_UUID = {UUID.fromString("00001827-0000-1000-8000-00805f9b34fb")};
            SearchBuilder searchBuilder = new SearchBuilder()
                    .setServiceUUIDs(MESH_PROVISIONING_UUID)    //SigMesh的UUID是固定值
                    .setTimeOut(100)        //扫描时长 单位秒
                    .setTuyaBlueMeshSearchListener(iTuyaBlueMeshSearchListener).build();
            ITuyaBlueMeshSearch mMeshSearch = TuyaHomeSdk.getTuyaBlueMeshConfig().newTuyaBlueMeshSearch(searchBuilder);
            //开启扫描
            mMeshSearch.startSearch();
        } else {
            Y.e("缺少参数蓝牙失败");
        }
    }

    private void jixua(SearchDeviceBean deviceBean) {
        List<SearchDeviceBean> mSearchDeviceBeanList = new ArrayList<>();
        mSearchDeviceBeanList.add(deviceBean);
        TuyaSigMeshActivatorBuilder tuyaSigMeshActivatorBuilder = new TuyaSigMeshActivatorBuilder()
                .setSearchDeviceBeans(mSearchDeviceBeanList)
                .setSigMeshBean(sigMeshBean) // Sigmesh基本信息
                .setTimeOut(120)  //超时时间
                .setTuyaBlueMeshActivatorListener(new ITuyaBlueMeshActivatorListener() {
                    @Override
                    public void onSuccess(String mac, DeviceBean deviceBean) {
                        Y.e("搜索设备Mesh成功了啊  " + deviceBean.getIconUrl() + "   " + deviceBean.getName());
                        getShebei();
                        addShebei(deviceBean);
                        deviceBeans.add(deviceBean);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String mac, String errorCode, String errorMsg) {
                        Y.e("搜索设备蓝牙Mesh失败了  " + errorMsg + "   " + errorCode);
                    }

                    @Override
                    public void onFinish() {

                    }
                });

        ITuyaBlueMeshActivator iTuyaBlueMeshActivator = TuyaHomeSdk.getTuyaBlueMeshConfig().newSigActivator(tuyaSigMeshActivatorBuilder);
        //开启配网
        iTuyaBlueMeshActivator.startActivator();
    }


    private void startLanyaPeiwang(String token) {
        Y.e("是否执行蓝牙  " + token + "    " + isSetLanya);
        if (isSetLanya) {
            bleOperator = TuyaHomeSdk.getBleOperator();
            bleOperator.startLeScan(60000, ScanType.SINGLE, new TyBleScanResponse() {
                @Override
                public void onResult(ScanDeviceBean bean) {
                    Y.e("我成功了么么么   id:" + bean.getId() + "   name:" + bean.getName() + "    providerName:" + bean.getProviderName() +
                            "   configType:" + bean.getConfigType() +
                            "   productId:" + bean.getProductId() +
                            "   uuid:" + bean.getUuid() +
                            "   mac:" + bean.getMac() +
                            "   isbind:" + bean.getIsbind() +
                            "   flag:" + bean.getFlag());

                    stopSearch();

                    Map<String, Object> param = new HashMap<>();
                    param.put("ssid", wifiSSid); //wifi ssid
                    param.put("password", mima); //wifi pwd
                    param.put("token", token); // user token
                    TuyaHomeSdk.getBleManager().startBleConfig(homeId, bean.getUuid(), param, new ITuyaBleConfigListener() {
                        @Override
                        public void onSuccess(DeviceBean devResp) {
                            Y.e("蓝牙成功添加了设备啊啊啊" + devResp.getName() + "   " + devResp.getIconUrl());
                            getShebei();
                            addShebei(devResp);
                            deviceBeans.add(devResp);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFail(String code, String msg, Object handle) {
                            Y.t("获取设备失败,请重新搜索" + msg);
                            Y.e("蓝牙配网失败了,请重新搜索" + msg);
                            stopPeiwang();
                        }
                    });
                }
            });
        }
    }

    private void startWifiPeiwang(String token) {
        ActivatorBuilder builder = new ActivatorBuilder()
                .setSsid(wifiSSid)
                .setContext(getContext())
                .setPassword(mima)
                .setActivatorModel(ActivatorModelEnum.TY_EZ)
                .setTimeOut(60)
                .setToken(token)
                .setListener(new ITuyaSmartActivatorListener() {
                                 @Override
                                 public void onError(String errorCode, String errorMsg) {
                                     Y.t("获取设备失败,请重新搜索" + errorMsg);
                                     Y.e("获取设备失败,请重新搜索" + errorMsg);
                                     stopPeiwang();
                                 }

                                 @Override
                                 public void onActiveSuccess(DeviceBean devResp) {
                                     Y.e("成功添加设备" + devResp.getName() + "   " + devResp.getIconUrl());
                                     getShebei();
                                     addShebei(devResp);
                                     deviceBeans.add(devResp);
                                     adapter.notifyDataSetChanged();
                                 }

                                 @Override
                                 public void onStep(String step, Object data) {

                                 }
                             }
                );

        mTuyaActivator = TuyaHomeSdk.getActivatorInstance().newMultiActivator(builder);
        mTuyaActivator.start();
    }

    private void addShebei(DeviceBean devResp) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16042");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("family_id", familyId);
        map.put("ty_device_ccid", devResp.getDevId());
        map.put("ty_family_id", homeId + "");
        map.put("ty_room_id", "0");
        map.put("device_type", devResp.getProductBean().getCategory());
        map.put("device_category", devResp.getProductId());
        map.put("device_category_code", devResp.getProductBean().getCategoryCode());
        map.put("device_type_name", devResp.getName());
        map.put("device_type_pic", devResp.getIconUrl());
        Gson gson = new Gson();
        OkGo.<AppResponse<ZhiNengHomeBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengHomeBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengHomeBean.DataBean>> response) {

                    }
                });
    }

    private void getShebei() {
        rv_shebei.setVisibility(View.VISIBLE);
        rl_shuoming.setVisibility(View.GONE);
        rl_faxian.setVisibility(View.VISIBLE);
        bt_xiugai.setVisibility(View.GONE);
        bt_chongxinsousuo.setVisibility(View.GONE);
    }

    private void clickLanya() {
        if (bluetoothAdapter == null) {
            Y.t("该设备不支持蓝牙设备");
            return;
        }
        if (isSetLanya) {
            bluetoothAdapter.disable();
            isSetLanya = false;
            iv_lanya.setVisibility(View.GONE);
        } else {
            bluetoothAdapter.enable();
            isSetLanya = true;
            iv_lanya.setVisibility(View.VISIBLE);
        }
    }

    private void startAnimation() {
        Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.search_anim);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        if (rotate != null) {
            iv_search.startAnimation(rotate);
        } else {
            iv_search.setAnimation(rotate);
            iv_search.startAnimation(rotate);
        }
    }

    private void stopPeiwang() {
        bt_chongxinsousuo.setVisibility(View.VISIBLE);
        stopAnimation();
        stopSearch();
    }

    private void stopSearch() {
        if (mTuyaActivator != null) {
            mTuyaActivator.stop();
            mTuyaActivator.onDestroy();
        }

        if (bleOperator != null) {
            bleOperator.stopLeScan();
        }
    }

    private void stopAnimation() {
        iv_search.clearAnimation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPeiwang();
    }
}
