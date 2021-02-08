package com.smarthome.magic.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.ZhiNengFamilyManageDetailActivity;
import com.smarthome.magic.activity.ZhiNengHomeListActivity;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.add.TuyaDeviceAddActivity;
import com.smarthome.magic.activity.tuya_device.changjing.TuyaChangjingActivity;
import com.smarthome.magic.activity.tuya_device.changjing.TuyaTianqiActivity;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaHomeManager;
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
import com.tuya.smart.home.sdk.bean.MemberBean;
import com.tuya.smart.home.sdk.bean.MemberWrapperBean;
import com.tuya.smart.home.sdk.bean.WeatherBean;
import com.tuya.smart.home.sdk.callback.ITuyaGetHomeListCallback;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;
import com.tuya.smart.sdk.api.ITuyaDataCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import io.reactivex.internal.operators.maybe.MaybeDoAfterSuccess;
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
    ImageView bt_add_camera;

    @BindView(R.id.iv_tianqi)
    ImageView iv_tianqi;
    @BindView(R.id.tv_tianqi)
    TextView tv_tianqi;
    @BindView(R.id.tv_tianqi_wendu)
    TextView tv_tianqi_wendu;
    @BindView(R.id.iv_tianqi_enter)
    ImageView iv_tianqi_enter;
    @BindView(R.id.ll_tianqi_click)
    LinearLayout ll_tianqi_click;


    private List<String> tabs = new ArrayList<>();
    private NewsFragmentPagerAdapter viewPagerAdapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ZhiNengDeviceFragment zhiNengDeviceFragment;
    private ZhiNengRoomFragment zhiNengRoomFragment;
    private ZhiNengJiaJuChangJingFragment zhiNengJiaJuChangJingFragment;

    private List<ZhiNengHomeBean.DataBean> dataBean = new ArrayList<>();
    private Bundle device = new Bundle();
    private Bundle room = new Bundle();
    private boolean isSettianqi;

    private String zhuJiWenShiDu;

    @Override
    protected void initLogic() {

    }

    public void initData() {
        getnet();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        //UIHelper.ToastMessage(getActivity(), "页面可见");
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
        zhiNengJiaJuChangJingFragment = ZhiNengJiaJuChangJingFragment.newInstance(room);
        fragmentList.add(zhiNengJiaJuChangJingFragment);
        tabs.add("设备");
        tabs.add("房间");
        tabs.add("场景");
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
        ll_tianqi_click.setOnClickListener(this);
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
                } else if (message.type == ConstanceValue.MSG_TUYA_TIANQI) {
                    Object content = message.content;
                    if (content == null) {
                        isSettianqi = false;
                        tv_tianqi.setText("欢迎回家");
//                        iv_tianqi.setImageResource();
                        tv_tianqi_wendu.setText("设置家庭位置，获取更多信息");
                        iv_tianqi_enter.setVisibility(View.VISIBLE);
                    } else {
                        isSettianqi = true;
                        WeatherBean result = (WeatherBean) message.content;
                        tv_tianqi.setText(result.getCondition());
                        Glide.with(getContext()).load(result.getInIconUrl()).into(iv_tianqi);
                        tv_tianqi_wendu.setText(zhuJiWenShiDu + "室外温度:" + result.getTemp() + "℃");
                        iv_tianqi_enter.setVisibility(View.GONE);
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_DELETE) {
                    getnet();
                }
            }
        }));

        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getnet();
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                sendRx(notice);
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
                        String wendu = dataBean.get(0).temperature;//温度
                        String shidu = dataBean.get(0).humidity;//湿度

                        zhuJiWenShiDu = "室内温度" + wendu + "℃" + " " + "室内湿度" + shidu + "℃" + " ";
                        tv_tianqi_wendu.setText(zhuJiWenShiDu);

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
                        if (dataBean.get(0).getMember_type().equals("1")) {
                            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.ZHINENGJIAJUGUANLIYUAN, "1");
                        } else {
                            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.ZHINENGJIAJUGUANLIYUAN, "0");
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
                                        Y.e("创建家庭失败:" + errorMsg);
                                    }
                                });
                            }
                        } else {
                            PreferenceHelper.getInstance(getActivity()).putLong(AppConfig.TUYA_HOME_ID, Y.getLong(ty_family_id));
                            TuyaHomeManager.getHomeManager().setHomeId(Y.getLong(ty_family_id));
                        }

                        for (int i = 0; i < dataBean.get(0).getDevice().size(); i++) {
                            if (dataBean.get(0).getDevice().get(i).getDevice_type().equals("00")) {
                                PreferenceHelper.getInstance(getActivity()).putString(AppConfig.HAVEZHUJI, "1");
                                return;
                            }
                            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.HAVEZHUJI, "0");
                        }
                        /**
                         * online_state	在线状态：1.在线 2.离线
                         */
                        if (dataBean.get(0).getDevice().size() == 0) {
                            ivZhujiZhuangtai.setBackgroundResource(R.mipmap.img_connect_device);
                            tvZhujiZhuangtai.setText("添加主机");
                            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.HAVEZHUJI, "0");
                            llConnectDevice.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PeiWangYinDaoPageActivity.actionStart(getActivity());
                                }
                            });
                        } else {
                            if (dataBean.get(0).getDevice().get(0).getOnline_state() == null) {
                                ivZhujiZhuangtai.setBackgroundResource(R.mipmap.zhikong_zhujilixian);
                                tvZhujiZhuangtai.setText("主机离线");
                            } else if (dataBean.get(0).getDevice().get(0).getOnline_state().equals("1")) {
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
                        TuyaHomeManager.getHomeManager().setHomeId(ty_family_id);
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
            case R.id.ll_tianqi_click:
                clickSetTianqi();
                break;
            case R.id.bt_add_camera:
                clickAddCamera();
                break;
        }
    }

    private void clickSetTianqi() {
        if (isSettianqi) {
            TuyaTianqiActivity.actionStart(getContext());
//            TuyaChangjingActivity.actionStart(getContext());
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("family_id", dataBean.get(0).getFamily_id());
            bundle.putString("ty_family_id", dataBean.get(0).getTy_family_id());
            startActivity(new Intent(getContext(), ZhiNengFamilyManageDetailActivity.class).putExtras(bundle));
        }
    }

    private void clickAddCamera() {
        PreferenceHelper.getInstance(getActivity()).putString(AppConfig.MC_DEVICE_CCID, "aaaaaaaaaaaaaaaa80140018");
        TuyaDeviceAddActivity.actionStart(getContext());

        getHomeList();
//        yaoqing("13091891781");
    }


    private void getHomeList() {
        TuyaHomeSdk.getHomeManagerInstance().queryHomeList(new ITuyaGetHomeListCallback() {
            @Override
            public void onSuccess(List<HomeBean> homeBeans) {
                for (int i = 0; i < homeBeans.size(); i++) {
                    HomeBean homeBean = homeBeans.get(i);
                    long homeId = homeBean.getHomeId();
                    String name = homeBean.getName();

                    Y.e("家庭名称  " + name + "  " + homeId);

//                    TuyaHomeSdk.newHomeInstance(homeId).dismissHome(new IResultCallback() {
//                        @Override
//                        public void onSuccess() {
//                            Y.e("解散涂鸦家庭成功 " + homeId);
//                        }
//
//                        @Override
//                        public void onError(String code, String error) {
//                            Y.t("解散家庭失败:" + error);
//                        }
//                    });
                }
            }

            @Override
            public void onError(String errorCode, String error) {

            }
        });
    }

    private void yaoqing(String phone) {
        long homeId = PreferenceHelper.getInstance(getContext()).getLong(AppConfig.TUYA_HOME_ID, 0);
        @SuppressLint("WrongConstant") MemberWrapperBean bean = new MemberWrapperBean.Builder()
                .setHomeId(homeId)
                .setNickName(phone)
                .setAccount(phone)
                .setCountryCode("86")
                .setRole(0)
                .setHeadPic("")
                .setAutoAccept(true)
                .build();

        TuyaHomeSdk.getMemberInstance().addMember(bean, new ITuyaDataCallback<MemberBean>() {
            @Override
            public void onSuccess(MemberBean result) {
                Y.t("邀请成功");
            }

            @Override
            public void onError(String errorCode, String errorMessage) {
                Y.e("邀请失败啦 " + errorMessage);
            }
        });
    }
}
