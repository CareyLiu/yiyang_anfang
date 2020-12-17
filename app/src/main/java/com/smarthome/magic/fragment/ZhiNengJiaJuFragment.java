package com.smarthome.magic.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.ZhiNengFamilyManageDetailActivity;
import com.smarthome.magic.activity.ZhiNengHomeListActivity;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_camera.add.TuyaDeviceAddActivity;
import com.smarthome.magic.activity.tuya_camera.utils.TuyaDeviceManager;
import com.smarthome.magic.activity.tuya_camera.utils.TuyaDialogUtils;
import com.smarthome.magic.activity.zhinengjiaju.peinet.PeiWangYinDaoPageActivity;
import com.smarthome.magic.adapter.NewsFragmentPagerAdapter;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengFamilyManageBean;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.smarthome.magic.view.NoSlidingViewPager;
import com.smarthome.magic.view.magicindicator.MagicIndicator;
import com.smarthome.magic.view.magicindicator.ViewPagerHelper;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;
import com.tuya.smart.sdk.TuyaSdk;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;


/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class ZhiNengJiaJuFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "ZhiNengJiaJuFragment";
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.ll_checkhome)
    LinearLayout ll_checkhome;
    @BindView(R.id.tv_family_name)
    TextView tv_family_name;
    @BindView(R.id.tv_device_num)
    TextView tv_device_num;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewPager)
    NoSlidingViewPager viewPager;
    @BindView(R.id.iv_zhuji_zhuangtai)
    ImageView ivZhujiZhuangtai;
    @BindView(R.id.ll_connect_device)
    LinearLayout llConnectDevice;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.rl_main)
    LinearLayout rlMain;
    @BindView(R.id.tv_zhuji_zhuangtai)
    TextView tvZhujiZhuangtai;
    @BindView(R.id.bt_add_camera)
    Button bt_add_camera;


    private List<String> tabs = new ArrayList<>();
    private NewsFragmentPagerAdapter viewPagerAdapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ZhiNengDeviceFragment zhiNengDeviceFragment;
    private ZhiNengRoomFragment zhiNengRoomFragment;

    private List<ZhiNengHomeBean.DataBean> dataBean = new ArrayList<>();
    private Bundle device = new Bundle();
    private Bundle room = new Bundle();

    @Override
    protected void initLogic() {

    }

    public void initData() {
        getnet();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).statusBarColor(R.color.white).init();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.zhinengjiaju;
    }

    @Override
    protected void initView(View view) {
        view.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initViewpager() {
        viewPager.setScroll(false);
        zhiNengDeviceFragment = ZhiNengDeviceFragment.newInstance(device);
        fragmentList.add(zhiNengDeviceFragment);
        zhiNengRoomFragment = ZhiNengRoomFragment.newInstance(room);
        fragmentList.add(zhiNengRoomFragment);
        tabs.add("设备");
        tabs.add("房间");
        viewPagerAdapter = new NewsFragmentPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
    }

    private void initMagicIndicator() {

        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabs == null ? 0 : tabs.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(tabs.get(index));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.sousuo));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.black_111111));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(getResources().getColor(R.color.car_text_black));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        srLSmart.setEnableLoadMore(false);
        ll_checkhome.setOnClickListener(this);
        bt_add_camera.setOnClickListener(this);
        initViewpager();
        initMagicIndicator();
        initData();

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_PEIWANG_SUCCESS) {

                    Log.v("MSG_PEIWANG_SUCCESS", "000");
                    ivZhujiZhuangtai.setBackgroundResource(R.mipmap.zhikong_zhujizaixian);
                    tvZhujiZhuangtai.setText("主机在线");
                    // getnet();
                } else if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN) {
                    getnet();
                } else if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_ZHUJI) {
                    //刷新列表
                    Log.i("设备离线", "设备离线刷新列表");
                    getnet();
                } else if (message.type == ConstanceValue.MSG_DEVICE_ADD) {
                    getnet();
                }
            }
        }));

        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getnet();
            }
        });
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
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
                        if (srLSmart != null) {
                            srLSmart.setEnableRefresh(true);
                            srLSmart.finishRefresh();
                            srLSmart.setEnableLoadMore(false);
                        }
                        dataBean = response.body().data;
                        if (dataBean.get(0).getMember_type().equals("1")) {
                            tv_family_name.setText(dataBean.get(0).getFamily_name());
                        } else {
                            tv_family_name.setText(dataBean.get(0).getFamily_name() + "(共享家庭)");
                        }
                        tv_device_num.setText(dataBean.get(0).getDevice_num() + "个设备");

                        device.putParcelableArrayList("device", dataBean.get(0).getDevice());
                        device.putString("member_type", dataBean.get(0).getMember_type());
                        device.putString("family_id", dataBean.get(0).getFamily_id());

                        room.putParcelableArrayList("room", dataBean.get(0).getRoom());
                        room.putString("member_type", dataBean.get(0).getMember_type());
                        room.putString("family_id", dataBean.get(0).getFamily_id());
                        if (zhiNengDeviceFragment != null) {
                            zhiNengDeviceFragment.onRefresh();
                        }
                        if (zhiNengRoomFragment != null) {
                            zhiNengRoomFragment.onRefresh();
                        }

                        String familyId = dataBean.get(0).getFamily_id();
                        PreferenceHelper.getInstance(getActivity()).putString(AppConfig.PEIWANG_FAMILYID, familyId);
                        String ty_family_id = dataBean.get(0).getTy_family_id();
                        if (TextUtils.isEmpty(ty_family_id)) {
                            if (dataBean.get(0).getMember_type().equals("1")) {
                                List<String> addRooms = new ArrayList<>();
                                TuyaHomeSdk.getHomeManagerInstance().createHome(dataBean.get(0).getFamily_name(), 0, 0, "", addRooms, new ITuyaHomeResultCallback() {
                                    @Override
                                    public void onSuccess(HomeBean bean) {
                                        long tuyaHomeId = bean.getHomeId();
                                        PreferenceHelper.getInstance(getActivity()).putLong(AppConfig.TUYA_HOME_ID, tuyaHomeId);
                                        addTuyaHome(familyId, tuyaHomeId);
                                    }

                                    @Override
                                    public void onError(String errorCode, String errorMsg) {
                                        Y.t("创建家庭失败:" + errorMsg);
                                    }
                                });
                            }
                        } else {
                            PreferenceHelper.getInstance(getActivity()).putLong(AppConfig.TUYA_HOME_ID, Y.getLong(ty_family_id));
//                            TuyaHomeSdk.newHomeInstance(Y.getLong(ty_family_id)).getHomeDetail(new ITuyaHomeResultCallback() {
//                                @Override
//                                public void onSuccess(HomeBean bean) {
//                                    List<DeviceBean> deviceList = bean.getDeviceList();
//                                    Y.e("裂缝宽度发生的  " + deviceList.size());
//                                    for (int i = 0; i < deviceList.size(); i++) {
//                                        DeviceBean deviceBean = deviceList.get(i);
//                                        Y.e("愧疚反倒是离开的是 " + deviceBean.getName());
//                                        TuyaHomeSdk.newDeviceInstance(deviceBean.getDevId()).removeDevice(new IResultCallback() {
//                                            @Override
//                                            public void onError(String errorCode, String errorMsg) {
//                                                Y.e("东方斯卡拉就发顺丰 ");
//                                            }
//
//                                            @Override
//                                            public void onSuccess() {
//                                                Y.e("我一处乘客都给对方 "+deviceBean.getName());
//                                            }
//                                        });
//                                    }
//                                }
//
//                                @Override
//                                public void onError(String errorCode, String errorMsg) {
////                                    TuyaDialogUtils.t(mContext, "获取设备信息失败");
//                                }
//                            });

                        }


                        /**
                         * online_state	在线状态：1.在线 2.离线
                         */

                        if (dataBean.get(0).getDevice().size() == 0) {
                            ivZhujiZhuangtai.setBackgroundResource(R.mipmap.img_connect_device);
                            tvZhujiZhuangtai.setText("添加主机");

                            llConnectDevice.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PeiWangYinDaoPageActivity.actionStart(getActivity());
                                }
                            });
                        } else {
                            if (dataBean.get(0).getDevice().get(0).getOnline_state().equals("1")) {
                                ivZhujiZhuangtai.setBackgroundResource(R.mipmap.zhikong_zhujizaixian);
                                tvZhujiZhuangtai.setText("主机在线");
                            } else {
                                ivZhujiZhuangtai.setBackgroundResource(R.mipmap.zhikong_zhujilixian);
                                tvZhujiZhuangtai.setText("主机离线");
                            }
                        }
                    }
                });
    }

    private void addTuyaHome(String family_id, long ty_family_id) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16045");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("family_id", family_id);
        map.put("ty_family_id", ty_family_id + "");
        map.put("ty_room_id", "0");
        Gson gson = new Gson();
        OkGo.<AppResponse<ZhiNengFamilyManageBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyManageBean.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ZhiNengFamilyManageBean.DataBean>> response) {

                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyManageBean.DataBean>> response) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_checkhome:
                startActivity(new Intent(getActivity(), ZhiNengHomeListActivity.class));
                break;
            case R.id.bt_add_camera:
                clickAddCamera();
                break;
        }
    }

    private void clickAddCamera() {
        PreferenceHelper.getInstance(getActivity()).putString(AppConfig.MC_DEVICE_CCID, "aaaaaaaaaaaaaaaa80140018");
        TuyaDeviceAddActivity.actionStart(getContext());
    }


}
