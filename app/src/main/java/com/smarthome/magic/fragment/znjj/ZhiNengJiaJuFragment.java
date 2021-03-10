package com.smarthome.magic.fragment.znjj;

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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.ZhiNengFamilyManageDetailActivity;
import com.smarthome.magic.activity.ZhiNengHomeListActivity;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.add.TuyaDeviceAddActivity;
import com.smarthome.magic.activity.tuya_device.changjing.TuyaTianqiActivity;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.smarthome.magic.activity.zhinengjiaju.peinet.PeiWangYinDaoPageActivity;
import com.smarthome.magic.adapter.NewsFragmentPagerAdapter;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.fragment.znjj.model.ZhiNengModel;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengFamilyManageBean;
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
import com.tuya.smart.home.sdk.bean.WeatherBean;
import com.tuya.smart.home.sdk.callback.ITuyaGetHomeListCallback;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;

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
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private NewsFragmentPagerAdapter viewPagerAdapter;

    private ZhiNengDeviceFragment zhiNengDeviceFragment;
    private ZhiNengRoomFragment zhiNengRoomFragment;
    private ZhiNengChangJingFragment zhiNengJiaJuChangJingFragment;

    private List<ZhiNengModel.DataBean> dataBean = new ArrayList<>();
    private String zhuJiWenShiDu;
    private boolean isSettianqi;

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).statusBarColor(R.color.white).init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void initLogic() {

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        getnet();
        initSM();
        initHuidiao();
    }

    private void initData() {
        ll_checkhome.setOnClickListener(this);
        bt_add_camera.setOnClickListener(this);
        ll_tianqi_click.setOnClickListener(this);
        initViewpager();
        initMagicIndicator();
    }

    private void initViewpager() {
        viewPager.setScroll(false);
        zhiNengDeviceFragment = new ZhiNengDeviceFragment();
        fragmentList.add(zhiNengDeviceFragment);
        zhiNengRoomFragment = new ZhiNengRoomFragment();
        fragmentList.add(zhiNengRoomFragment);
        zhiNengJiaJuChangJingFragment = new ZhiNengChangJingFragment();
        fragmentList.add(zhiNengJiaJuChangJingFragment);
        tabs.add("设备");
        tabs.add("房间");
        tabs.add("场景");
        viewPagerAdapter = new NewsFragmentPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
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

    private void initSM() {
        srLSmart.setEnableLoadMore(false);
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getnet();
            }
        });
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_PEIWANG_SUCCESS) {
                    ivZhujiZhuangtai.setBackgroundResource(R.mipmap.zhikong_zhujizaixian);
                    tvZhujiZhuangtai.setText("主机在线");
                } else if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN) {
                    getnet();
                } else if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_ZHUJI) {
                    getnet();
                } else if (message.type == ConstanceValue.MSG_DEVICE_ADD) {
                    getnet();
                } else if (message.type == ConstanceValue.MSG_TUYA_TIANQI) {
                    Object content = message.content;
                    if (content == null) {
                        isSettianqi = false;
                        tv_tianqi.setText("欢迎回家");
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
                } else if (message.type == ConstanceValue.MSG_DEVICE_DELETE) {//删除设备
                    getnet();
                } else if (message.type == ConstanceValue.MSG_TIANJIASHEBEI) {//添加普通设备
                    getnet();
                } else if (message.type == ConstanceValue.MSG_DEVICE_ROOM_NAME_CHANGE) {//设备转移
                    getnet();
                }
            }
        }));
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16001");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengModel.DataBean>> response) {
                        dataBean = response.body().data;
                        String wendu = dataBean.get(0).getTemperature();//温度
                        String shidu = dataBean.get(0).getHumidity();//湿度

//                        Notice notice = new Notice();
//                        notice.type = ConstanceValue.MSG_ZHINENGJIAJU_ZI_SHUAXIN;
//                        notice.content = dataBean;
//                        sendRx(notice);

                        zhiNengDeviceFragment.getData(dataBean);
                        zhiNengRoomFragment.getData(dataBean);
                        zhiNengJiaJuChangJingFragment.getData(dataBean);


                        if (StringUtils.isEmpty(wendu) && StringUtils.isEmpty(shidu)) {
                            zhuJiWenShiDu = "";
                        } else {
                            zhuJiWenShiDu = "室内温度" + wendu + "℃" + " " + "室内湿度" + shidu + "℃" + " ";
                        }

                        tv_tianqi_wendu.setText(zhuJiWenShiDu);

                        if (dataBean.get(0).getMember_type().equals("1")) {
                            tv_family_name.setText(dataBean.get(0).getFamily_name());
                        } else {
                            tv_family_name.setText(dataBean.get(0).getFamily_name() + "(共享家庭)");
                        }
                        tv_device_num.setText(dataBean.get(0).getDevice_num() + "个设备");

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

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        srLSmart.finishRefresh();
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

    private TishiDialog tishiDialog;

    private void clickAddCamera() {
        // TODO: 2021/2/19 判断是不是管理员
        String str = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.ZHINENGJIAJUGUANLIYUAN, "0");
        if (str.equals("0")) {
            tishiDialog = new TishiDialog(getActivity(), 3, new TishiDialog.TishiDialogListener() {
                @Override
                public void onClickCancel(View v, TishiDialog dialog) {

                }

                @Override
                public void onClickConfirm(View v, TishiDialog dialog) {
                    tishiDialog.dismiss();
                }

                @Override
                public void onDismiss(TishiDialog dialog) {

                }
            });
            tishiDialog.setTextContent("非管理员无法添加主机及设备");
            tishiDialog.show();

        } else if (str.equals("1")) {
            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.MC_DEVICE_CCID, "aaaaaaaaaaaaaaaa80140018");
            TuyaDeviceAddActivity.actionStart(getContext());
//            getHomeList();
        }
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
                }
            }

            @Override
            public void onError(String errorCode, String error) {

            }
        });
    }
}
