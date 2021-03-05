package com.smarthome.magic.activity.tuya_device.add;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import com.espressif.iot.esptouch.util.ByteUtil;
import com.espressif.iot.esptouch.util.TouchNetUtil;
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
import com.smarthome.magic.activity.zhinengjiaju.EsptouchAsyncTask4;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.smarthome.magic.model.ZhiNengJiaJu_0007Model;
import com.smarthome.magic.model.ZhiNengJiaJu_0009Model;
import com.smarthome.magic.mqtt_zhiling.ZnjjMqttMingLing;
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
import com.tuya.smart.home.sdk.builder.ActivatorBuilder;
import com.tuya.smart.sdk.api.ITuyaActivator;
import com.tuya.smart.sdk.api.ITuyaActivatorGetToken;
import com.tuya.smart.sdk.api.ITuyaSmartActivatorListener;
import com.tuya.smart.sdk.api.bluemesh.ITuyaBlueMeshActivator;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.bean.SigMeshBean;
import com.tuya.smart.sdk.enums.ActivatorModelEnum;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

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

public class TuyaDeviceAddZiFragmentBeifen extends BaseFragment {
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
    private String bssid;
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

        ll_wifi.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ceShiZhuJi();
                return false;
            }
        });
        ll_lanya.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ceShiSheBei();
                return false;
            }
        });

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

    ZhiNengJiaJu_0007Model zhiNengJiaJu_0007Model;
    ZhiNengJiaJu_0009Model zhiNengJiaJu_0009Model;
    List<ZhiNengJiaJu_0007Model.MatchListBean> mDatas = new ArrayList<>();

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_WIFI_SET) {
                    wifiSSid = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_ADMIN, "");
                    mima = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_MIMA, "");
                    bssid = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_BSSID, "");
                    if (!TextUtils.isEmpty(wifiSSid) && !TextUtils.isEmpty(mima) & !TextUtils.isEmpty(bssid)) {
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
                } else if (message.type == ConstanceValue.MSG_TIANJIASHEBEI) {
                    //添加设备
                    zhiNengJiaJu_0007Model = (ZhiNengJiaJu_0007Model) message.content;
                    mDatas.clear();
                    mDatas.addAll(zhiNengJiaJu_0007Model.getMatch_list());

                    UIHelper.ToastMessage(getActivity(), "接收到的图片是：" + zhiNengJiaJu_0007Model.getMatch_list().get(0).getDevice_type_pic());
//                    oneImageAdapter.notifyDataSetChanged();
                } else if (message.type == ConstanceValue.MSG_TIANJIAZHUJI) {
                    //添加主机
                    zhiNengJiaJu_0009Model = (ZhiNengJiaJu_0009Model) message.content;
                    UIHelper.ToastMessage(getActivity(), "接收到的图片是：" + zhiNengJiaJu_0009Model.mc_device_url);
                    // TODO: 2021/2/2 获得主机信息后续处理
                } else if (message.type == ConstanceValue.MSG_PEIWNAG_ESPTOUCH) {
                    int ob = (int) message.content;
                    /**
                     * @param str 0连接失败 1开始连接页面 2连接中3连接成功
                     */
                    if (ob == 0) {//连接失败
                        UIHelper.ToastMessage(getActivity(), "连接失败");
                    } else if (ob == 2) {//连接中
                        UIHelper.ToastMessage(getActivity(), "连接中");
                    } else if (ob == 3) {//连接成功
                        UIHelper.ToastMessage(getActivity(), "连接成功");
                    }
                }
            }
        }));
    }

    private void init() {
        homeId = TuyaHomeManager.getHomeManager().getHomeId();
        familyId = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.PEIWANG_FAMILYID, "");
        wifiSSid = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_ADMIN, "");
        mima = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_MIMA, "");
        bssid = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.TUYA_PEIWANG_BSSID, "");
        Y.e("wifi信息   " + wifiSSid + "   " + bssid + "   " + mima);

        if (!TextUtils.isEmpty(wifiSSid) && !TextUtils.isEmpty(mima) && !TextUtils.isEmpty(bssid)) {
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

    String zhuJiDeviceCCidUp;
    String shiFouTianJiaGuoZhuJieType;
    String serverId;

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

    private ZnjjMqttMingLing znjjMqttMingLing;
    public EsptouchAsyncTask4 mTask;

    /**
     * @param zhuJiShiFouTianJiaType  这个家庭是否添加过主机 0否 1 是
     * @param zhuji_device_ccid_up    主机ccid
     * @param serverId                serverid  ccid最后一位
     * @param zhuangZhiLeixing        装置类型 详见mqtt文档
     * @param zhuangZhiLeiXingXingHao 装置类型型号 a款 b款
     */
    private void jiBenPeiWang(String zhuJiShiFouTianJiaType, String zhuji_device_ccid_up, String serverId, String zhuangZhiLeixing, String zhuangZhiLeiXingXingHao) {
        zhuJiShiFouTianJiaType = "0";
        if (zhuJiShiFouTianJiaType.equals("1")) {
            znjjMqttMingLing = new ZnjjMqttMingLing(getActivity());

            znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(zhuji_device_ccid_up, serverId, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
            // TODO: 2021/2/2 添加的命令待赋值
            String str = "M12" + zhuangZhiLeixing + zhuangZhiLeiXingXingHao + "2";

            znjjMqttMingLing.tianJiaSheBei(zhuji_device_ccid_up, serverId, str, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } else if (zhuJiShiFouTianJiaType.equals("0")) {
            // TODO: 2021/2/2 接入密码  主机配对
            executeEsptouch(mima, bssid, wifiSSid);
        }
    }

    /**
     * wifi 密码
     */
    private void executeEsptouch(String pwdStr, String mBssid, String mSsid) {
        byte[] password = pwdStr == null ? null : ByteUtil.getBytesByString(pwdStr.toString());
        byte[] bssid = TouchNetUtil.parseBssid2bytes(mBssid.toString());
        CharSequence devCountStr = String.valueOf(1);
        byte[] deviceCount = devCountStr == null ? new byte[0] : devCountStr.toString().getBytes();
        byte[] broadcast = {1};

        if (mTask != null) {
            mTask.cancelEsptouch();
        }
        mTask = new EsptouchAsyncTask4(getActivity());

        int yonghuming_length = mSsid.toString().length();
        int password_length = password.toString().length();

        String zhengHeYongHuMing = String.format("%02d", yonghuming_length) + mSsid.toString();

        String zhengHePasssword_length = String.format("%02d", password_length);

        String familyId = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.PEIWANG_FAMILYID, "");

        if (familyId == null) {
            UIHelper.ToastMessage(getActivity(), "请退出应用后重新进入");
            return;
        }

        String familyId_length = String.format("%02d", familyId.length());

        Log.i("execute_info", "   mssid  :" + mSsid.toString() + "   mBssid  :" + mBssid + "   password  :" + password);

        String zhengHePassWord = zhengHePasssword_length + familyId_length + password + familyId;

        Log.i("execute_info", zhengHePassWord);

        mTask.execute(zhengHeYongHuMing.getBytes(), bssid, zhengHePassWord.getBytes(), deviceCount, broadcast);
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
            startJyjSearch();
        } else {
            Y.t("请配置wifi");
        }
    }

    private void startJyjSearch() {
        // TODO: 2021/2/2 待增加参数
        zhuJiDeviceCCidUp = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.DEVICECCID, "");
        if (StringUtils.isEmpty(zhuJiDeviceCCidUp)) {
            shiFouTianJiaGuoZhuJieType = "0";
        } else {
            shiFouTianJiaGuoZhuJieType = "1";
        }
        serverId = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.SERVERID, "");
        jiBenPeiWang(shiFouTianJiaGuoZhuJieType, zhuJiDeviceCCidUp, serverId, "00", "00");
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
//                        startLanyaMeshPeiwang();
                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        Y.t(s1);
                        stopPeiwang();
                    }
                });
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
                        getShebei();
                        addShebei(deviceBean);
                        deviceBeans.add(deviceBean);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String mac, String errorCode, String errorMsg) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });

        ITuyaBlueMeshActivator iTuyaBlueMeshActivator = TuyaHomeSdk.getTuyaBlueMeshConfig().newSigActivator(tuyaSigMeshActivatorBuilder);
        iTuyaBlueMeshActivator.startActivator();
    }

    private void startLanyaPeiwang(String token) {
        if (isSetLanya) {
            bleOperator = TuyaHomeSdk.getBleOperator();
            bleOperator.startLeScan(60000, ScanType.SINGLE, new TyBleScanResponse() {
                @Override
                public void onResult(ScanDeviceBean bean) {
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
        if (znjjMqttMingLing != null) {
            znjjMqttMingLing.unSubscribeShiShiXinXi_WithCanShu(zhuJiDeviceCCidUp, serverId, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        }
    }

    public void ceShiSheBei() {
//        Notice n = new Notice();
//        n.type = ConstanceValue.MSG_TIANJIASHEBEI;
//
//        ZhiNengJiaJu_0007Model zhiNengJiaJuNotifyJson = new ZhiNengJiaJu_0007Model();
//        ZhiNengJiaJu_0007Model.DataBean dataBean = new ZhiNengJiaJu_0007Model.DataBean();
//        dataBean.device_type_pic = "https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920";
//
//        List<ZhiNengJiaJu_0007Model.DataBean> list = new ArrayList<>();
//        list.add(dataBean);
//        zhiNengJiaJuNotifyJson.setData(list);
//        n.content = zhiNengJiaJuNotifyJson;
//        RxBus.getDefault().sendRx(n);
    }

    public void ceShiZhuJi() {
        Notice n = new Notice();
        n.type = ConstanceValue.MSG_TIANJIAZHUJI;

        ZhiNengJiaJu_0009Model zhiNengJiaJuNotifyJson = new ZhiNengJiaJu_0009Model();
        zhiNengJiaJuNotifyJson.mc_device_url = "https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920";
        n.content = zhiNengJiaJuNotifyJson;
        RxBus.getDefault().sendRx(n);
    }


}
