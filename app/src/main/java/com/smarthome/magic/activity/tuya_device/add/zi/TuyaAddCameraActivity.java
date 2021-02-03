package com.smarthome.magic.activity.tuya_device.add.zi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.add.TuyaDeviceAddFinishActivity;
import com.smarthome.magic.activity.tuya_device.add.model.TuyaAddDeviceModel;
import com.smarthome.magic.activity.tuya_device.utils.TuyaConfig;
import com.smarthome.magic.activity.tuya_device.utils.WifiReceiver;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.builder.ActivatorBuilder;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaActivator;
import com.tuya.smart.sdk.api.ITuyaActivatorGetToken;
import com.tuya.smart.sdk.api.ITuyaSmartActivatorListener;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.enums.ActivatorModelEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class TuyaAddCameraActivity extends BaseActivity {


    @BindView(R.id.rv_shebei)
    RecyclerView rv_shebei;
    @BindView(R.id.rl_shuoming)
    RelativeLayout rl_shuoming;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.bt_xiugai)
    TextView btXiugai;
    @BindView(R.id.bt_chongxinsousuo)
    TextView bt_chongxinsousuo;
    @BindView(R.id.bt_xiayibu)
    TextView btXiayibu;
    @BindView(R.id.rl_faxian)
    RelativeLayout rlFaxian;
    @BindView(R.id.rl_sousuo)
    RelativeLayout rl_sousuo;
    @BindView(R.id.tv_huashu1)
    TextView tvHuashu1;
    @BindView(R.id.ll_huashu2)
    LinearLayout llHuashu2;
    @BindView(R.id.tv_huashu3)
    TextView tvHuashu3;
    @BindView(R.id.tv_wifi_ming)
    TextView tvWifiMing;
    @BindView(R.id.ll_wifi_name)
    LinearLayout llWifiName;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.iv_icon_mima)
    ImageView ivIconMima;
    @BindView(R.id.et_wifi_mima)
    EditText etWifiMima;
    @BindView(R.id.iv_see_mima)
    ImageView ivSeeMima;
    @BindView(R.id.ll_wifi_mima)
    RelativeLayout llWifiMima;
    @BindView(R.id.view_line_1)
    View viewLine1;
    @BindView(R.id.tv_huashu4)
    TextView tvHuashu4;
    @BindView(R.id.iv_peiwang_mima)
    ImageView ivPeiwangMima;
    @BindView(R.id.rll_kaishilianjie)
    TextView rllKaishilianjie;
    @BindView(R.id.ll_peizhi)
    ConstraintLayout ll_peizhi;
    private WifiReceiver wifiReceiver;
    private String seeMiMa = "0";//0 隐藏 1显示
    private String jiZhuMiMa = "1";//0 不记住  1 记住
    private boolean isOnWifiConect;

    private String bssid;
    private String wifiName;
    private String mima;
    private boolean isSousuozhong;
    private long homeId;
    private String familyId;
    private ITuyaActivator mTuyaActivator;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TuyaAddCameraActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_add_camera;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("摄像机配网");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initPeizhi();
        initHuidiao();
    }

    private void initPeizhi() {
        ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_buxianshimima);
        ivPeiwangMima.setBackgroundResource(R.mipmap.peiwang_icon_mima_jizhu);

        homeId = TuyaHomeManager.getHomeManager().getHomeId();
        familyId = PreferenceHelper.getInstance(mContext).getString(AppConfig.PEIWANG_FAMILYID, "");

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) { // 在android 6.0之前会默认返回true
                    reginstWifi();
                } else {
                    Y.tLong("配置wifi需要赋予访问网络定位的权限，不开启将无法获取wifi信息！");
                }
            }
        });
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_WIFI_INFO) {
                    rllKaishilianjie.setEnabled(true);
                    isOnWifiConect = true;

                    WifiInfo wifiInfo = (WifiInfo) message.content;
                    String name = wifiInfo.getSSID().replace("\"", "");
                    bssid = wifiInfo.getBSSID();
                    tvWifiMing.setText(name);
                    String wifiName = PreferenceHelper.getInstance(mContext).getString(AppConfig.TUYA_PEIWANG_ADMIN_GET, "");
                    if (wifiName.equals(name)) {
                        String mima = PreferenceHelper.getInstance(mContext).getString(AppConfig.TUYA_PEIWANG_MIMA_GET, "");
                        etWifiMima.setText(mima);
                    } else {
                        etWifiMima.setText("");
                    }
                } else if (message.type == ConstanceValue.MSG_WIFI_CLOSE) {
                    isOnWifiConect = false;
                    rllKaishilianjie.setEnabled(false);
                    tvWifiMing.setText("Wifi断开，请连接Wifi");
                    etWifiMima.setText("");
                    bssid = "";
                }
            }
        }));
    }

    private void reginstWifi() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        wifiReceiver = new WifiReceiver();
        registerReceiver(wifiReceiver, filter);
    }


    @OnClick({R.id.ll_wifi_name, R.id.iv_see_mima, R.id.iv_peiwang_mima, R.id.rll_kaishilianjie, R.id.bt_xiugai, R.id.bt_chongxinsousuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_wifi_name:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
            case R.id.iv_see_mima:
                clickMima();
                break;
            case R.id.iv_peiwang_mima:
                setPeiWangMiMa();
                break;
            case R.id.bt_xiugai:
                xiugai();
                break;
            case R.id.bt_chongxinsousuo:
                starPeiwang();
                break;
            case R.id.rll_kaishilianjie:
                clickXiayibu();
                break;
        }
    }

    private void xiugai() {
        stopPeiwang();
        rl_sousuo.setVisibility(View.GONE);
        ll_peizhi.setVisibility(View.VISIBLE);
    }

    private void clickXiayibu() {
        wifiName = tvWifiMing.getText().toString();
        mima = etWifiMima.getText().toString();
        if (TextUtils.isEmpty(mima)) {
            Y.t("请输入您的配网密码");
        } else {
            PreferenceHelper.getInstance(mContext).putString(AppConfig.TUYA_PEIWANG_ADMIN, wifiName);
            PreferenceHelper.getInstance(mContext).putString(AppConfig.TUYA_PEIWANG_MIMA, mima);
            PreferenceHelper.getInstance(mContext).putString(AppConfig.TUYA_PEIWANG_BSSID, bssid);
            if (jiZhuMiMa.equals("1")) {
                PreferenceHelper.getInstance(mContext).putString(AppConfig.TUYA_PEIWANG_ADMIN_GET, wifiName);
                PreferenceHelper.getInstance(mContext).putString(AppConfig.TUYA_PEIWANG_MIMA_GET, mima);
            } else {
                PreferenceHelper.getInstance(mContext).removeKey(AppConfig.TUYA_PEIWANG_ADMIN_GET);
                PreferenceHelper.getInstance(mContext).removeKey(AppConfig.TUYA_PEIWANG_MIMA_GET);
            }
            Y.hideInputMethod(etWifiMima);
            Notice notice = new Notice();
            notice.type = ConstanceValue.MSG_WIFI_SET;
            RxBus.getDefault().sendRx(notice);

            starPeiwang();
        }
    }

    private void clickMima() {
        if (seeMiMa.equals("0")) {
            seeMiMa = "1";
            ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_xianshimima);
            etWifiMima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            seeMiMa = "0";
            ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_buxianshimima);
            etWifiMima.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    private void setPeiWangMiMa() {
        if (jiZhuMiMa.equals("0")) {
            ivPeiwangMima.setBackgroundResource(R.mipmap.peiwang_icon_mima_jizhu);
            jiZhuMiMa = "1";
        } else if (jiZhuMiMa.equals("1")) {
            ivPeiwangMima.setBackgroundResource(R.mipmap.peiwang_icon_mima_weixuanze);
            jiZhuMiMa = "0";
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
                        Y.e("获取Token成功：" + token + "   账号：" + wifiName + "  密码：" + mima);
                        startWifiPeiwang(token);
                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        Y.t(s1);
                        stopPeiwang();
                    }
                });
    }

    private void startWifiPeiwang(String token) {
        ActivatorBuilder builder = new ActivatorBuilder()
                .setSsid(wifiName)
                .setContext(mContext)
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
                                     Y.e("获取到了设备" + devResp.getName() + "   " + devResp.getIconUrl());
                                     if (devResp.getProductBean().getCategory().equals(TuyaConfig.CATEGORY_CAMERA)) {
                                         Y.e("成功添加了摄像机");
                                         addShebei(devResp);
                                     } else {
                                         TuyaHomeSdk.newDeviceInstance(devResp.getDevId()).removeDevice(new IResultCallback() {
                                             @Override
                                             public void onError(String errorCode, String errorMsg) {
                                                 Y.e("移除错误设备失败" + errorMsg);
                                             }

                                             @Override
                                             public void onSuccess() {
                                                 Y.e("移除了错误设备");
                                             }
                                         });
                                     }
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
        map.put("token", UserManager.getManager(mContext).getAppToken());
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
                        wwwwww(devResp);
                    }
                });
    }

    private void wwwwww(DeviceBean deviceBean) {
        List<TuyaAddDeviceModel> deviceModels = new ArrayList<>();
        TuyaAddDeviceModel model = new TuyaAddDeviceModel();
        model.setDeviceId(deviceBean.getDevId());
        model.setName(deviceBean.getName());
        model.setIcon(deviceBean.getIconUrl());
        model.setSelect(true);
        deviceModels.add(model);
        TuyaDeviceAddFinishActivity.actionStart(mContext, deviceModels);
    }

    private void startAnimation() {
        Animation rotate = AnimationUtils.loadAnimation(mContext, R.anim.search_anim);
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
    }

    private void stopAnimation() {
        iv_search.clearAnimation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiReceiver);
        stopPeiwang();
    }
}
