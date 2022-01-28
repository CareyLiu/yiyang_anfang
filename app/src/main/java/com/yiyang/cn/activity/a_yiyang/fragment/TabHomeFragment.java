package com.yiyang.cn.activity.a_yiyang.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.activity.FuwuZhucanActivity;
import com.yiyang.cn.activity.a_yiyang.activity.FuwuZhujiActivity;
import com.yiyang.cn.activity.a_yiyang.activity.HuodongActivity;
import com.yiyang.cn.activity.a_yiyang.activity.ShequyanglaoActivity;
import com.yiyang.cn.activity.a_yiyang.activity.TabMoreActivity;
import com.yiyang.cn.activity.a_yiyang.activity.YiyangTuTActivity;
import com.yiyang.cn.activity.a_yiyang.activity.ZaixianyishengActivity;
import com.yiyang.cn.activity.a_yiyang.activity.ZhujieActivity;
import com.yiyang.cn.activity.a_yiyang.activity.ZhuxingActivity;
import com.yiyang.cn.activity.a_yiyang.activity.ZhuyuActivity;
import com.yiyang.cn.activity.a_yiyang.activity.jigou.JigouyanglaoActivity;
import com.yiyang.cn.activity.a_yiyang.activity.pinggu.JiashuDanganActivity;
import com.yiyang.cn.activity.a_yiyang.activity.pinggu.YanglaopingguActivity;
import com.yiyang.cn.activity.a_yiyang.adapter.HomeZhylAdapter;
import com.yiyang.cn.activity.shengming.SmHomeActivity;
import com.yiyang.cn.activity.shengming.shengmingmodel.CreateSession;
import com.yiyang.cn.activity.shengming.utils.UrlUtils;
import com.yiyang.cn.activity.tongcheng58.TongChengMainActivity;
import com.yiyang.cn.activity.tongcheng58.model.TcHomeModel;
import com.yiyang.cn.activity.zijian_shangcheng.FenLeiThirdActivity;
import com.yiyang.cn.activity.zijian_shangcheng.ZiJianShopMallActivity;
import com.yiyang.cn.adapter.gaiban.HomeReMenAdapter;
import com.yiyang.cn.adapter.gaiban.HomeZiYingAdapter;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.GlideImageLoader;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.Home;
import com.yiyang.cn.util.GridAverageUIDecoration;
import com.yiyang.cn.util.Utils;
import com.yiyang.cn.view.ObservableScrollView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

import static com.yiyang.cn.app.App.JINGDU;
import static com.yiyang.cn.app.App.WEIDU;

public class TabHomeFragment extends BaseFragment implements ObservableScrollView.ScrollViewListener {
    @BindView(R.id.iv_weizhi)
    ImageView iv_weizhi;
    @BindView(R.id.tv_weizhi)
    TextView tv_weizhi;
    @BindView(R.id.iv_saoma)
    ImageView iv_saoma;
    @BindView(R.id.banner_main)
    Banner banner_main;
    @BindView(R.id.rv_zhihuiyanglao)
    RecyclerView rv_zhihuiyanglao;
    @BindView(R.id.banner_two)
    ImageView banner_two;
    @BindView(R.id.iv_tab_jiatingyisheng)
    ImageView iv_tab_jiatingyisheng;
    @BindView(R.id.iv_tab_yanglaopinggu)
    ImageView iv_tab_yanglaopinggu;
    @BindView(R.id.iv_tab_jitingdangan)
    ImageView iv_tab_jitingdangan;
    @BindView(R.id.rv_jujiashenghuo)
    RecyclerView rv_jujiashenghuo;
    @BindView(R.id.iv_tab_bianminshenghuo)
    ImageView iv_tab_bianminshenghuo;
    @BindView(R.id.iv_tab_zhihuishangcheng)
    ImageView iv_tab_zhihuishangcheng;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.iv_logo)
    TextView ivLogo;
    @BindView(R.id.banner_one)
    ImageView banner_one;
    @BindView(R.id.iv_tianmao_or_taobao)
    ImageView ivTianmaoOrTaobao;


    private View topPanel, middlePanel;
    private int topHeight;
    private ConstraintLayout clZiYing_Middle, clReMen_Middle;
    private ConstraintLayout clZiYing_Top, clReMen_Top;
    private LinearLayout llBackground_Top, llBackground_Middle;
    private TextView tvZiYingTop, tvRemTop, tvZiYingZhiGongTop, tvReMenShangPinTop;
    private TextView tvZiYingMiddle, tvReMenMiddle, tvZiYingZhiGongMiddle, tvReMenShangPinMiddle;
    private ConstraintLayout clQuanBu;
    private ObservableScrollView nestedScrollView;
    private View viewLineTop, viewLineMiddle, remenViewLineTop;

    private RecyclerView rlv_ziYing;
    private RecyclerView rlvRemen;

    private String rimenOrZiYing = "0"; //0 自营直供 1 热门商品

    private List<Home.DataBean.ProShowListBean> ziYingListBean = new ArrayList<>();
    private List<Home.DataBean.IndexShowListBean> remenListBean = new ArrayList<>();
    private HomeZiYingAdapter homeZiYingAdapter;
    private HomeReMenAdapter homeReMenAdapter;

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .statusBarColor(R.color.white)
                .init();
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
        return R.layout.yiyang_frag_tab_home;
    }

    @Override
    protected void initView(View rootView) {
        initAdapter();
        initSM();
        initBanner();
        initViewNew(rootView);
        getData();
        initLocation();
    }

    private void initViewNew(View view) {
        nestedScrollView = view.findViewById(R.id.scrollView);
        topPanel = view.findViewById(R.id.topPanel);
        middlePanel = view.findViewById(R.id.middlePanel);
        clZiYing_Top = topPanel.findViewById(R.id.cl_ziying);
        clReMen_Top = topPanel.findViewById(R.id.cl_remen);
        clZiYing_Middle = middlePanel.findViewById(R.id.cl_ziying);
        clReMen_Middle = middlePanel.findViewById(R.id.cl_remen);
        tvZiYingTop = topPanel.findViewById(R.id.tv_ziying);
        tvRemTop = topPanel.findViewById(R.id.tv_remen);
        clQuanBu = view.findViewById(R.id.cl_quanbu);
        clQuanBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rimenOrZiYing.equals("0")) {
                    FenLeiThirdActivity.actionStart(getActivity(), "品牌直供", "2");
                } else {
                    FenLeiThirdActivity.actionStart(getActivity(), "热门商品", "1");
                }
            }
        });

        tvZiYingZhiGongTop = topPanel.findViewById(R.id.ziyingzhigong);
        tvReMenShangPinTop = topPanel.findViewById(R.id.remenshangpin);
        tvZiYingMiddle = middlePanel.findViewById(R.id.tv_ziying);
        tvReMenMiddle = middlePanel.findViewById(R.id.tv_remen);
        tvZiYingZhiGongMiddle = middlePanel.findViewById(R.id.ziyingzhigong);
        tvReMenShangPinMiddle = middlePanel.findViewById(R.id.remenshangpin);
        nestedScrollView.setScrollViewListener(this);
        rlv_ziYing = view.findViewById(R.id.rlv_ziying_or_remen);
        rlv_ziYing.setFocusable(false);
        viewLineTop = topPanel.findViewById(R.id.view_line);
        rlvRemen = view.findViewById(R.id.rlv_remen);
        rlvRemen.setFocusable(false);
        remenViewLineTop = topPanel.findViewById(R.id.view_line_remen);
        clZiYing_Top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(getActivity(), "点击了自营");
                rlv_ziYing.setVisibility(View.VISIBLE);
                rlvRemen.setVisibility(View.GONE);
                setZiYingOrReMenLine("0");
                rimenOrZiYing = "0";
            }
        });
        clReMen_Top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(getActivity(), "点击了热门");
                rlv_ziYing.setVisibility(View.GONE);
                rlvRemen.setVisibility(View.VISIBLE);
                setZiYingOrReMenLine("1");
                rimenOrZiYing = "1";
            }
        });

        clReMen_Middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(getActivity(), "点击了热门");
                rlv_ziYing.setVisibility(View.GONE);
                rlvRemen.setVisibility(View.VISIBLE);
                setZiYingOrReMenLine("1");
                rimenOrZiYing = "1";
            }
        });

        clZiYing_Middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(getActivity(), "点击了自营");
                rlv_ziYing.setVisibility(View.VISIBLE);
                rlvRemen.setVisibility(View.GONE);
                setZiYingOrReMenLine("0");
                rimenOrZiYing = "0";
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        rlv_ziYing.addItemDecoration(new GridAverageUIDecoration(14, 10));
        rlv_ziYing.setLayoutManager(layoutManager);
        homeZiYingAdapter = new HomeZiYingAdapter(R.layout.item_home_ziying, ziYingListBean);
        rlv_ziYing.setAdapter(homeZiYingAdapter);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 2);
        rlvRemen.addItemDecoration(new GridAverageUIDecoration(9, 10));
        rlvRemen.setLayoutManager(layoutManager1);
        homeReMenAdapter = new HomeReMenAdapter(R.layout.item_home_remen, remenListBean);
        rlvRemen.setAdapter(homeReMenAdapter);
    }


    /**
     * 定位监听
     */
    AMapLocationListener gaodeDingWeiListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    sb.append("定位时间: " + Utils.formatUTC(location.getTime(), "yyyy-MM-dd HH:mm:ss") + "\n");

                    PreferenceHelper.getInstance(getActivity()).putString(JINGDU, String.valueOf(location.getLongitude()));
                    PreferenceHelper.getInstance(getActivity()).putString(WEIDU, String.valueOf(location.getLatitude()));
                    PreferenceHelper.getInstance(getActivity()).putString(AppConfig.LOCATION_CITY_NAME, location.getCity());
                    PreferenceHelper.getInstance(getActivity()).putString(AppConfig.ADDRESS, location.getAddress());

                    stopLocation();
                } else {

                    //"gps_x=45.666043" + "&" + "gps_y=126.605713";
                    // x 纬度 y 经度
                    PreferenceHelper.getInstance(getActivity()).putString(WEIDU, "45.666043");
                    PreferenceHelper.getInstance(getActivity()).putString(JINGDU, "126.605713");
                    PreferenceHelper.getInstance(getActivity()).putString(AppConfig.LOCATION_CITY_NAME, "长春市");
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                sb.append("***定位质量报告***").append("\n");
                sb.append("* WIFI开关：").append(location.getLocationQualityReport().isWifiAble() ? "开启" : "关闭").append("\n");
                sb.append("* GPS状态：").append(getGPSStatusString(location.getLocationQualityReport().getGPSStatus())).append("\n");
                sb.append("* GPS星数：").append(location.getLocationQualityReport().getGPSSatellites()).append("\n");
                sb.append("* 网络类型：" + location.getLocationQualityReport().getNetworkType()).append("\n");
                sb.append("* 网络耗时：" + location.getLocationQualityReport().getNetUseTime()).append("\n");
                sb.append("****************").append("\n");
                //定位之后的回调时间
                sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");

                //解析定位结果，
                String result = sb.toString();
                Log.i("Location_result", result);
            } else {
                PreferenceHelper.getInstance(getActivity()).putString(WEIDU, "45.666043");
                PreferenceHelper.getInstance(getActivity()).putString(JINGDU, "126.605713");
                PreferenceHelper.getInstance(getActivity()).putString(AppConfig.LOCATION_CITY_NAME, "长春市");
                Log.i("Location_result", "定位失败");
            }

            String cityName = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.LOCATION_CITY_NAME, "");
            Log.i("location_city", cityName);
            if (TextUtils.isEmpty(cityName)) {
                tv_weizhi.setText("长春市");
            } else {
                tv_weizhi.setText(cityName);
            }
        }
    };

    /**
     * 获取GPS状态的字符串
     *
     * @param statusCode GPS状态码
     * @return
     */
    private String getGPSStatusString(int statusCode) {
        String str = "";
        switch (statusCode) {
            case AMapLocationQualityReport.GPS_STATUS_OK:
                str = "GPS状态正常";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPROVIDER:
                str = "手机中没有GPS Provider，无法进行GPS定位";
                break;
            case AMapLocationQualityReport.GPS_STATUS_OFF:
                str = "GPS关闭，建议开启GPS，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_MODE_SAVING:
                str = "选择的定位模式中不包含GPS定位，建议选择包含GPS定位的模式，提高定位质量";
                break;
            case AMapLocationQualityReport.GPS_STATUS_NOGPSPERMISSION:
                str = "没有GPS定位权限，建议开启gps定位权限";
                break;
        }
        return str;
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private void initLocation() {
        try {
            //初始化client
            locationClient = new AMapLocationClient(getActivity().getApplicationContext());
            locationOption = getDefaultOption();
            //设置定位参数
            locationClient.setLocationOption(locationOption);
            // 设置定位监听
            locationClient.setLocationListener(gaodeDingWeiListener);
            startLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        //根据控件的选择，重新设置定位参数
        //resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }


    private void initBanner() {
        List<Integer> items = new ArrayList<>();
        items.add(R.mipmap.a_img_banner_1);
        items.add(R.mipmap.a_img_banner_2);
        items.add(R.mipmap.a_img_banner_3);
        items.add(R.mipmap.a_img_banner_4);
        items.add(R.mipmap.a_img_banner_5);

        //设置图片加载器
        banner_main.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner_main.setImages(items);
        //banner设置方法全部调用完毕时最后调用
        banner_main.start();
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartRefreshLayout.finishRefresh();
            }
        });
    }

    private void initAdapter() {
        initZhylAdapter();
        initJjshAdapter();
    }

    private List<TcHomeModel.DataBean.IconListBean> zhylList = new ArrayList<>();
    private List<TcHomeModel.DataBean.IconListBean> jjshList = new ArrayList<>();

    private void initZhylList() {
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_shequyanglao, "社区养老"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jujiayanglao, "居家养老"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_jigouyanglao, "机构养老"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_hujiaozhongxin, "呼叫中心"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_huodongzhongxin, "活动中心"));
        zhylList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_gengduo, "更多功能"));
    }

    private void initJjshList() {
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhuyifuwu, "助医服务"));
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhucanfuwu, "助餐服务"));
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhuyufuwu, "助浴服务"));
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhujiefuwu, "助洁服务"));
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_zhuxingfuwu, "助行服务"));
        jjshList.add(new TcHomeModel.DataBean.IconListBean(R.mipmap.yiyang_tab_fuwutaocan, "助急服务"));

    }

    private void initZhylAdapter() {
        initZhylList();
        HomeZhylAdapter zhylAdapter = new HomeZhylAdapter(R.layout.yiyang_item_home_zhyl, zhylList);
        rv_zhihuiyanglao.setLayoutManager(new GridLayoutManager(getContext(), 6));
        rv_zhihuiyanglao.setAdapter(zhylAdapter);
        zhylAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TcHomeModel.DataBean.IconListBean listBean = zhylList.get(position);
                switch (position) {
                    case 0:
                        ShequyanglaoActivity.actionStart(getContext());
                        break;
                    case 1:
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_ZHINENGJIAJU;
                        RxBus.getDefault().sendRx(n);
                        break;
                    case 2:
                        JigouyanglaoActivity.actionStart(getContext());
                        break;
                    case 3:
                        FuwuZhujiActivity.actionStart(getContext(), "呼叫中心");
                        break;
                    case 4:
                        HuodongActivity.actionStart(getContext());
                        break;
                    case 5:
                        TabMoreActivity.actionStart(getContext());
                        break;
                }


            }
        });

//        YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_jiankangshuju);
    }

    private void initJjshAdapter() {
        initJjshList();
        HomeZhylAdapter jjshAdapter = new HomeZhylAdapter(R.layout.yiyang_item_home_zhyl, jjshList);
        rv_jujiashenghuo.setLayoutManager(new GridLayoutManager(getContext(), 6));
        rv_jujiashenghuo.setAdapter(jjshAdapter);
        jjshAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TcHomeModel.DataBean.IconListBean listBean = zhylList.get(position);
                switch (position) {
                    case 0:
                        ZaixianyishengActivity.actionStart(getContext());
                        break;
                    case 1:
                        FuwuZhucanActivity.actionStart(getContext());
                        break;
                    case 2:
                        ZhuyuActivity.actionStart(getContext());
                        break;
                    case 3:
                        ZhujieActivity.actionStart(getContext());
                        break;
                    case 4:
                        ZhuxingActivity.actionStart(getContext());
                        break;
                    case 5:
                        FuwuZhujiActivity.actionStart(getContext(), "助急服务");
                        break;
                }
            }
        });
    }

    @OnClick({R.id.iv_tianmao_or_taobao, R.id.banner_one, R.id.iv_weizhi, R.id.tv_weizhi, R.id.iv_saoma, R.id.banner_two, R.id.iv_tab_jiatingyisheng, R.id.iv_tab_yanglaopinggu, R.id.iv_tab_jitingdangan, R.id.iv_tab_bianminshenghuo, R.id.iv_tab_zhihuishangcheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_weizhi:
            case R.id.tv_weizhi:

                break;
            case R.id.iv_saoma:

                break;
            case R.id.banner_two:
                YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_kuaisuwenzhen);
                break;
            case R.id.iv_tab_jiatingyisheng:
                YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_zaixianyisheng);
                break;
            case R.id.iv_tab_yanglaopinggu:
                YanglaopingguActivity.actionStart(getContext());
                break;
            case R.id.iv_tab_jitingdangan:
                JiashuDanganActivity.actionStart(getContext());
                break;
            case R.id.iv_tab_bianminshenghuo:
                TongChengMainActivity.actionStart(getActivity());
                break;
            case R.id.iv_tab_zhihuishangcheng:
                ZiJianShopMallActivity.actionStart(getActivity());
                break;
            case R.id.banner_one:
                enterShengming();
                break;
            case R.id.iv_tianmao_or_taobao:
                break;
        }
    }

    private void enterShengming() {
        showProgressDialog();
        String timestamp = System.currentTimeMillis() + "";
        String ltoken = UrlUtils.getMainLtoken(timestamp);
        OkGo.<CreateSession>get(UrlUtils.createSession)
                .params("customerCode", UrlUtils.CUSTOMERCODE)
                .params("timestamp", timestamp)
                .params("ltoken", ltoken)
                .tag(this)//
                .execute(new JsonCallback<CreateSession>() {
                    @Override
                    public void onSuccess(Response<CreateSession> response) {
                        String sessionId = response.body().getData();
                        PreferenceHelper.getInstance(getContext()).putString("sm_sessionId", sessionId);
                        SmHomeActivity.actionStart(getContext());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    /**
     * private TextView tvZiYingTop, tvRemTop, tvZiYingZhiGongTop, tvReMenShangPinTop;
     * private TextView tvZiYingMiddle, tvReMenMiddle, tvZiYingZhiGongMiddle, tvReMenShangPinMiddle;
     */
    //0 自营 1 热门
    private void setZiYingOrReMenLine(String remenOrZiYing) {
        if (remenOrZiYing.equals("0")) {
            tvZiYingTop.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));
            tvZiYingZhiGongTop.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));

            tvZiYingMiddle.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));
            tvZiYingZhiGongMiddle.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));


            tvRemTop.setTextColor(getActivity().getResources().getColor(R.color.black_666666));
            tvReMenShangPinTop.setTextColor(getActivity().getResources().getColor(R.color.black_333333));

            tvReMenMiddle.setTextColor(getActivity().getResources().getColor(R.color.black_666666));
            tvReMenShangPinMiddle.setTextColor(getActivity().getResources().getColor(R.color.black_333333));

            viewLineTop.setVisibility(View.VISIBLE);
            remenViewLineTop.setVisibility(View.GONE);

            tvZiYingMiddle.setBackgroundResource(R.drawable.bg_color_fc0100_1a);
            tvReMenMiddle.setBackgroundResource(0);
        } else {
            tvZiYingTop.setTextColor(getActivity().getResources().getColor(R.color.black_666666));
            tvZiYingZhiGongTop.setTextColor(getActivity().getResources().getColor(R.color.black_333333));
            tvZiYingMiddle.setTextColor(getActivity().getResources().getColor(R.color.black_666666));
            tvZiYingZhiGongMiddle.setTextColor(getActivity().getResources().getColor(R.color.black_333333));


            tvRemTop.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));
            tvReMenShangPinTop.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));

            tvReMenMiddle.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));
            tvReMenShangPinMiddle.setTextColor(getActivity().getResources().getColor(R.color.color_FFFC0100));

            tvReMenMiddle.setBackgroundResource(R.drawable.bg_color_fc0100_1a);
            tvZiYingMiddle.setBackgroundResource(0);

            viewLineTop.setVisibility(View.GONE);
            remenViewLineTop.setVisibility(View.VISIBLE);
        }
    }


    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04131");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("gps_x", PreferenceHelper.getInstance(getActivity()).getString(WEIDU, ""));
        map.put("gps_y", PreferenceHelper.getInstance(getActivity()).getString(JINGDU, ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<Home.DataBean>>post(Urls.HOME_PICTURE)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Home.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Home.DataBean>> response) {
                        remenListBean = response.body().data.get(0).getIndexShowList();
                        ziYingListBean = response.body().data.get(0).getProShowList();

                        homeZiYingAdapter.setNewData(ziYingListBean);
                        homeZiYingAdapter.notifyDataSetChanged();

                        homeReMenAdapter.setNewData(remenListBean);
                        homeReMenAdapter.notifyDataSetChanged();
                    }
                });


    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        int[] location = new int[2];
        clZiYing_Middle.getLocationOnScreen(location);
        int locationY = location[1];
        if (locationY <= topHeight && (topPanel.getVisibility() == View.GONE || topPanel.getVisibility() == View.INVISIBLE)) {
            topPanel.setVisibility(View.VISIBLE);
            tvRemTop.setVisibility(View.GONE);
            tvZiYingTop.setVisibility(View.GONE);
            clReMen_Top.setBackgroundResource(R.color.white);
            clZiYing_Top.setBackgroundResource(R.color.white);
        }

        if (locationY > topHeight && topPanel.getVisibility() == View.VISIBLE) {
            topPanel.setVisibility(View.GONE);
            tvRemTop.setVisibility(View.VISIBLE);
            tvZiYingTop.setVisibility(View.VISIBLE);
            clReMen_Top.setBackgroundResource(R.color.grayfff5f5f5);
            clZiYing_Top.setBackgroundResource(R.color.grayfff5f5f5);
        }
    }
}
