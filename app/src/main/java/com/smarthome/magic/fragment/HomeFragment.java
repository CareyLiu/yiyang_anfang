
package com.smarthome.magic.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.ItemDecoration.GridItemDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.AirConditionerActivity;
import com.smarthome.magic.activity.CarListActivity;
import com.smarthome.magic.activity.DefaultX5WebViewActivity;
import com.smarthome.magic.activity.DefaultX5WebView_HaveNameActivity;
import com.smarthome.magic.activity.Demo_rongyun;
import com.smarthome.magic.activity.PlumbingHeaterActivity;
import com.smarthome.magic.activity.TuanYouWebView;
import com.smarthome.magic.activity.WebViewActivity;
import com.smarthome.magic.activity.fenxiang_tuisong.FenXiangTuiSongActivity;
import com.smarthome.magic.activity.fenxiang_tuisong.HuoDongTanCengActivity;
import com.smarthome.magic.activity.fenxiang_tuisong.ShouYeFenXiangActivity;
import com.smarthome.magic.activity.gouwuche.GouWuCheActivity;
import com.smarthome.magic.activity.homepage.DaLiBaoActivity;
import com.smarthome.magic.activity.jd_taobao_pinduoduo.TaoBao_Jd_PinDuoDuoActivity;
import com.smarthome.magic.activity.rongyun_liaotian.ConversationListActivity;
import com.smarthome.magic.activity.saoma.ScanActivity;
import com.smarthome.magic.activity.tuangou.TuanGouShangJiaListActivity;
import com.smarthome.magic.activity.xin_tuanyou.TuanYouList;
import com.smarthome.magic.activity.zijian_shangcheng.FenLeiThirdActivity;
import com.smarthome.magic.activity.zijian_shangcheng.ZiJianShopMallActivity;
import com.smarthome.magic.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.smarthome.magic.adapter.ChiHeWanLeListAdapter;
import com.smarthome.magic.adapter.DirectAdapter;
import com.smarthome.magic.adapter.GroupBuyAdapter;
import com.smarthome.magic.adapter.HotGoodsAdapter;
import com.smarthome.magic.adapter.ZhiKongListAdapter;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.GlideImageLoader;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.config.Wetch_S;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.CheckModel;
import com.smarthome.magic.model.FenLeiContentModel;
import com.smarthome.magic.model.Home;
import com.smarthome.magic.model.TuiGuangMaModel;
import com.smarthome.magic.service.DownLoadApkService;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.GlideShowImageUtils;
import com.smarthome.magic.util.Utils;
import com.smarthome.magic.util.chenck_banben.UpdateManager;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.app.App.JINGDU;
import static com.smarthome.magic.app.App.WEIDU;
import static com.smarthome.magic.get_net.Urls.HOME_PICTURE;

/**
 * Created by Administrator on 2018/3/29 0029.
 */
public class HomeFragment extends BaseFragment implements Observer, View.OnClickListener {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.list)
    LRecyclerView mList;
    private View view;
    private Unbinder unbinder;
    private ImageView iv_taoke;
    HotGoodsAdapter hotGoodsAdapter = null;//热门商品适配器
    LRecyclerViewAdapter hotLRecyclerViewAdapter = null;
    List<Home.DataBean.IndexShowListBean> hotList = new ArrayList<>();
    //  GroupBuyAdapter groupBuyAdapter = null;//拼团商品适配器
    // LRecyclerViewAdapter groupBuyLRecyclerViewAdapter = null;
    List<Home.DataBean.ShopListBean> groupList = new ArrayList<>();
    DirectAdapter directAdapter = null;//直供商品适配器
    LRecyclerViewAdapter directLRecyclerViewAdapter = null;
    List<Home.DataBean.ProShowListBean> directList = new ArrayList<>();
    public int choosePostion = 99999;
    LRecyclerView directRecyclerView;
    // LRecyclerView groupRecyclerView;
    View header, footer = null;
    Banner banner;
    //   LinearLayout wind_heater, hydronic, start_engine, vehicle_info;

    RecyclerView chiHeWanLeList, zhiKongList;//吃喝玩乐和智控
    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;

    ChiHeWanLeListAdapter chiHeWanLeListAdapter;
    List<Home.DataBean.IconListBean> chiHeWanLeListBeans;

    ZhiKongListAdapter zhiKongListAdapter;
    List<Home.DataBean.IntellectListBean> intellectListBeanList;
    ImageView ivDaLiBao;//大礼包
    ImageView ivZiJian;
    ImageView tianMaoOrTaoBao;
    ImageView ivJd;
    LocationManager locationManager;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //进入app 首次定位

        getYaoQingNet(getActivity());
//        RxPermissions rxPermissions = new RxPermissions(getActivity());
//        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Action1<Boolean>() {
//            @SuppressLint("MissingPermission")
//            @Override
//            public void call(Boolean granted) {
//                if (granted) { // 在android 6.0之前会默认返回true
//                    //mHandler.sendEmptyMessage(LocationUtils.MSG_LOCATION_START);
//                    //定位
//                    locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//                    List<String> list = locationManager.getProviders(true);
//                    if (list != null) {
//                        for (String x : list) {
//                            Log.e("gzq", "name:" + x);
//                        }
//                    }
//
//                    LocationProvider lpGps = locationManager.getProvider(LocationManager.GPS_PROVIDER);//gps定位
//                    LocationProvider lpNet = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);//wifi定位
//                    LocationProvider lpPsv = locationManager.getProvider(LocationManager.PASSIVE_PROVIDER);//基站定位
//
//
//                    Criteria criteria = new Criteria();
//                    // Criteria是一组筛选条件
//                    criteria.setAccuracy(Criteria.ACCURACY_FINE);
//                    //设置定位精准度
//                    criteria.setAltitudeRequired(false);
//                    //是否要求海拔
//                    criteria.setBearingRequired(true);
//                    //是否要求方向
//                    criteria.setCostAllowed(true);
//                    //是否要求收费
//                    criteria.setSpeedRequired(true);
//                    //是否要求速度
//                    criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
//                    //设置电池耗电要求
//                    criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);
//                    //设置方向精确度
//                    criteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);
//                    //设置速度精确度
//                    criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
//                    //设置水平方向精确度
//                    criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
//                    //设置垂直方向精确度
//
//                    //返回满足条件的当前设备可用的provider，第二个参数为false时返回当前设备所有provider中最符合条件的那个provider，但是不一定可用
//                    String mProvider = locationManager.getBestProvider(criteria, true);
//                    if (mProvider != null) {
//                        Log.e("gzq", "mProvider:" + mProvider);
//                    }
//
//                    if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5, 10, locationListener);
//                    } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 10, locationListener);
//                    }
//
//
//                }
//            }
//        });


        getNet_check();

        //初始化定位
        initLocation();
        startLocation();
    }

    /**
     * 安装apk
     */
    private void installApk(String apkFilePath) {
        Intent instal = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        // 这里加上 FLAG_GRANT_READ_URI_PERMISSION ，给目标程序读改uri的权限。
        instal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) { //判读版本是否在7.0以上
            File file = new File(apkFilePath);
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(getActivity(), "com.smarthome.magic.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            instal.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            instal.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            instal.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            Uri uri = Uri.fromFile(new File(apkFilePath));
            instal.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        getActivity().startActivity(instal);
    }

    public void getNet_check() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "00002");
        map.put("key", Urls.key);
        map.put("version_no", getAppVersionName(getActivity()));
        map.put("version_type", "6");
        map.put("version_code", getAppVersionCode(getActivity()));
        Gson gson = new Gson();
        OkGo.<AppResponse<CheckModel.DataBean>>post("https://jy.hljsdkj.com/msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CheckModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<CheckModel.DataBean>> response) {
                        /**
                         * update : Yes
                         * new_version : xxxxx
                         * apk_url : http://cdn.the.url.of.apk/or/patch
                         * update_log : xxxx
                         * delta : false
                         * new_md5 : xxxxxxxxxxxxxx
                         * target_size : 601132
                         */

                        if (response.body().data.get(0).getIsnew().equals("2")) {
                            UpdateManager.getUpdateManager().checkAppUpdate(getActivity(), false, response.body().data.get(0));

                        }


                    }

                    @Override
                    public void onError(Response<AppResponse<CheckModel.DataBean>> response) {
                        AlertUtil.t(getActivity(), response.getException().getMessage());
                    }
                });
    }


    private String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.smarthome.magic", 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    private String getAppVersionCode(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.smarthome.magic", 0);
            versionName = String.valueOf(packageInfo.versionCode);
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    SmartRefreshLayout smartRefreshLayout;
    NestedScrollView nestedScrollView;
    RelativeLayout rl_GouWuChe;


    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home_list;
    }

    @Override
    protected void initView(View view) {

        nestedScrollView = view.findViewById(R.id.nsl_scollview);
        rl_GouWuChe = view.findViewById(R.id.rl_gouwuche);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 600) {
                    rl_GouWuChe.setVisibility(View.VISIBLE);
                } else {
                    rl_GouWuChe.setVisibility(View.GONE);
                }
            }
        });

        rl_GouWuChe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(getActivity(),"功能开发中");
                GouWuCheActivity.actionStart(getActivity());
            }
        });

        view.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件
        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
        smartRefreshLayout.setEnableLoadMore(false);


        unbinder = ButterKnife.bind(this, view);

        header = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_header, (ViewGroup) Objects.requireNonNull(getActivity()).findViewById(android.R.id.content), false);
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_footer, (ViewGroup) getActivity().findViewById(android.R.id.content), false);

        TextView tvMore = header.findViewById(R.id.tv_pinpai_more);
        ImageView ivMore = header.findViewById(R.id.iv_more);

        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FenLeiThirdActivity.actionStart(getActivity(), "品牌直供", "2");
            }
        });

        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FenLeiThirdActivity.actionStart(getActivity(), "品牌直供", "2");
            }
        });


        TextView tvRemenMore = header.findViewById(R.id.tv_remen_look_more);
        ImageView ivRemenMore = header.findViewById(R.id.iv_remen);

        tvRemenMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FenLeiThirdActivity.actionStart(getActivity(), "热门商品", "1");
            }
        });

        ivRemenMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FenLeiThirdActivity.actionStart(getActivity(), "热门商品", "1");
            }
        });


        directRecyclerView = header.findViewById(R.id.list);//品牌制造商
        ImageView ivSaoMa = view.findViewById(R.id.iv_saoma);
        ivSaoMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CrashReport.testJavaCrash();
                RxPermissions rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) { // 在android 6.0之前会默认返回true
                            ScanActivity.actionStart(getActivity());
                        } else {
                            Toast.makeText(getActivity(), "该应用需要赋予访问相机的权限，不开启将无法正常工作！", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                //FenXiangTuiSongActivity.actionStart(getActivity());

            }
        });
        //吃喝玩乐相关列表
        chiHeWanLeList = header.findViewById(R.id.rv_chihe_wanle_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        GridLayoutManager gridLayoutManagerChi_He = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        // gridLayoutManagerChi_He.setOrientation(LinearLayoutManager.VERTICAL);
        //   revisionRecycler.setLayoutManager(gridLayoutManager);

        chiHeWanLeList.setLayoutManager(gridLayoutManagerChi_He);


        chiHeWanLeListAdapter = new ChiHeWanLeListAdapter(R.layout.item_chihewanle, chiHeWanLeListBeans);
        chiHeWanLeListAdapter.openLoadAnimation();//默认为渐显效果
        chiHeWanLeList.setAdapter(chiHeWanLeListAdapter);

        //智控列表
        zhiKongList = header.findViewById(R.id.zhikong_list);
        ivZiJian = header.findViewById(R.id.iv_zijian);
        tianMaoOrTaoBao = header.findViewById(R.id.iv_tianmao_or_taobao);
        // ivJd = header.findViewById(R.id.iv_jd);
        //  LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        // linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

        GridLayoutManager gridLayoutManagerZHiKong = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        //    gridLayoutManagerZHiKong.setOrientation(LinearLayoutManager.VERTICAL);
        zhiKongList.setLayoutManager(gridLayoutManagerZHiKong);

        zhiKongListAdapter = new ZhiKongListAdapter(R.layout.item_zhikong, intellectListBeanList);
        zhiKongListAdapter.openLoadAnimation();//默认为渐显效果
        zhiKongList.setAdapter(zhiKongListAdapter);
        zhiKongListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        /**
                         *  1.智能家居 2.风暖3.水暖 4.空调 5.神灯控车
                         */
                        Home.DataBean.IntellectListBean intellectListBean = (Home.DataBean.IntellectListBean) adapter.getData().get(position);
                        if (intellectListBean.getId().equals("1")) {
                            UIHelper.ToastMessage(getActivity(), "开发中,敬请期待");
                        } else if (intellectListBean.getId().equals("2")) {
                            startActivity(new Intent(getActivity(), CarListActivity.class).putExtra("type", "wind"));
                        } else if (intellectListBean.getId().equals("3")) {
//                            UIHelper.ToastMessage(getActivity(), "开发中,敬请期待");
                            startActivity(new Intent(getActivity(), PlumbingHeaterActivity.class));
                        } else if (intellectListBean.getId().equals("4")) {//空调
                            startActivity(new Intent(getActivity(), AirConditionerActivity.class));
                        } else if (intellectListBean.getId().equals("5")) {//神灯控车
                            UIHelper.ToastMessage(getActivity(), "开发中,敬请期待");
                        }
                        break;
                }
            }
        });
        chiHeWanLeListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {

                switch (view.getId()) {
                    case R.id.constrain:
                        /**
                         * iconList
                         * 美团小图标	id	图标id   1.美食 2.电影 3.酒店 4.休闲娱乐 5.旅游 6.加油
                         * 7.修配厂 8.体检 9.丽人/美发 10更多 14 房产
                         */

                        //首先获得权限

                        if (chiHeWanLeListAdapter.getData().get(position).getId().equals("6")) {
                            String jingdu = PreferenceHelper.getInstance(getActivity()).getString(JINGDU, "0X11");
                            String weidu = PreferenceHelper.getInstance(getActivity()).getString(WEIDU, "0X11");
                            if (!jingdu.equals("0X11")) {
                                String str = chiHeWanLeListAdapter.getData().get(position).getHref_url() + "?i=" + JiaMiToken +
                                        "&" + "gps_x=" + weidu + "&" + "gps_y=" + jingdu;
                                TuanYouWebView.actionStart(getActivity(), str);
                                Log.i("loadUrl", "tuanyou address" + str);

                            } else {
                                choosePostion = position;
                                if (chiHeWanLeListAdapter.getData().get(position).getId().equals("6")) {
                                    String str = chiHeWanLeListAdapter.getData().get(position).getHref_url() + "?i=" + JiaMiToken +
                                            "&" + "gps_x=45.666043" + "&" + "gps_y=126.605713";
                                    TuanYouWebView.actionStart(getActivity(), str);
                                    Log.i("loadUrl", "tuanyou address" + str);

                                    Toast.makeText(getActivity(), "该应用需要赋予定位的权限！", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else if (chiHeWanLeListAdapter.getData().get(position).getId().equals("11")) {
                            //               TuanGouShangJiaListActivity.actionStart(getActivity(), chiHeWanLeListAdapter.getData().get(position).getId());
                            //UIHelper.ToastMessage(getActivity(), "开发中,敬请期待");

                            TuanYouList.actionStart(getActivity());
                        } else if (chiHeWanLeListAdapter.getData().get(position).getId().equals("14")) {
                            //               TuanGouShangJiaListActivity.actionStart(getActivity(), chiHeWanLeListAdapter.getData().get(position).getId());
                            //UIHelper.ToastMessage(getActivity(), "开发中,敬请期待");

                            DefaultX5WebView_HaveNameActivity.actionStart(getActivity(), chiHeWanLeListAdapter.getData().get(position).getHref_url(), chiHeWanLeListAdapter.getData().get(position).getName());
                            //TuanYouList.actionStart(getActivity());
                        } else {
                            TuanGouShangJiaListActivity.actionStart(getActivity(), chiHeWanLeListAdapter.getData().get(position).getId());

                        }
                }
            }
        });


        // groupRecyclerView = footer.findViewById(R.id.list);
        ivDaLiBao = header.findViewById(R.id.iv_dalibao);

        // wind_heater = header.findViewById(R.id.wind_heater);
        // hydronic = header.findViewById(R.id.hydronic);
        //  start_engine = header.findViewById(R.id.start_engine);
        //  vehicle_info = header.findViewById(R.id.vehicle_info);

        //  wind_heater.setOnClickListener(this);
        //  hydronic.setOnClickListener(this);
        //  start_engine.setOnClickListener(this);
        //   vehicle_info.setOnClickListener(this);

        banner = header.findViewById(R.id.banner);
        hotGoodsAdapter = new HotGoodsAdapter(getActivity());
        hotGoodsAdapter.setDataList(hotList);
        //  groupBuyAdapter = new GroupBuyAdapter(getActivity());
        //  groupBuyAdapter.setDataList(groupList);
        directAdapter = new DirectAdapter(getActivity());
        directAdapter.setDataList(directList);

        GridItemDecoration divider = new GridItemDecoration.Builder(getActivity())
                .setHorizontal(R.dimen.default_divider_padding_5dp)
                .setVertical(R.dimen.default_divider_padding_5dp)
                .setColorResource(R.color.white)
                .build();
        directRecyclerView.addItemDecoration(divider);

        mList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        directRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        // groupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        hotLRecyclerViewAdapter = new LRecyclerViewAdapter(hotGoodsAdapter);
        // groupBuyLRecyclerViewAdapter = new LRecyclerViewAdapter(groupBuyAdapter);
        directLRecyclerViewAdapter = new LRecyclerViewAdapter(directAdapter);

        mList.setAdapter(hotLRecyclerViewAdapter);
        directRecyclerView.setAdapter(directLRecyclerViewAdapter);
        //  groupRecyclerView.setAdapter(groupBuyLRecyclerViewAdapter);


        mList.setLoadMoreEnabled(false);
        directRecyclerView.setLoadMoreEnabled(false);


        // groupRecyclerView.setLoadMoreEnabled(false);

        mList.setPullRefreshEnabled(false);
        directRecyclerView.setPullRefreshEnabled(false);

        // groupRecyclerView.setPullRefreshEnabled(false);

        hotLRecyclerViewAdapter.addHeaderView(header);
        hotLRecyclerViewAdapter.addFooterView(footer);


        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        getData();
        //   getNet();
        hotLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                ZiJianShopMallDetailsActivity.actionStart(getActivity(), hotList.get(position).getShop_product_id(), hotList.get(position).getWares_id());

                // startActivity(new Intent(getActivity(), GoosDetailsActivity.class)
                //       .putExtra("shop_product_id", hotList.get(position).getShop_product_id())
                //     .putExtra("wares_id", hotList.get(position).getWares_id()));
            }
        });
        directLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ZiJianShopMallDetailsActivity.actionStart(getActivity(), directList.get(position).getShop_product_id(), directList.get(position).getWares_id());

                //startActivity(new Intent(getActivity(), GoosDetailsActivity.class)
                //      .putExtra("shop_product_id", directList.get(position).getShop_product_id())
                //    .putExtra("wares_id", directList.get(position).getWares_id()));
            }
        });
        tianMaoOrTaoBao.setOnClickListener(this);
        ivDaLiBao.setOnClickListener(this);
        // ivJd.setOnClickListener(this);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DALIBAO_SUCCESS) {
                    ivDaLiBao.setVisibility(View.GONE);
                }
            }
        }));

        // mImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).statusBarColor(R.color.white).init();
    }


    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).statusBarColor(R.color.white).init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void update(Observable o, Object arg) {

        if (arg.equals("update")) {

        }
    }


    String taobaoPicture, jingdongPicture, pinduoduoPicture;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_tianmao_or_taobao:
                //TaokeListActivity.actionStart(getActivity());
                //     TaoKeHomeActivity.actionStart(getActivity());
                TaoBao_Jd_PinDuoDuoActivity.actionStart(getActivity(), taobaoPicture, jingdongPicture, pinduoduoPicture);
                break;
            //   case R.id.wind_heater: 风暖
            //      startActivity(new Intent(getActivity(), CarListActivity.class).putExtra("type", "wind"));
            //      break;
            //   case R.id.hydronic: 水暖
            //      startActivity(new Intent(getActivity(), CarListActivity.class).putExtra("type", "plumbing"));
            //     break;
            // case R.id.start_engine:启动发动机
            //      startActivity(new Intent(getActivity(), CarListActivity.class).putExtra("type", "control"));
            //       break;
            //   case R.id.vehicle_info://车辆信息
            //     break;
            case R.id.iv_dalibao:
                DaLiBaoActivity.actionStart(getActivity());
                break;

//            case R.id.iv_jd:
//                JDWebView.actionStart(getActivity());
//                break;
        }
    }

    private String JiaMiToken;
    public static List<String> items = new ArrayList<>();

    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04131");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("gps_x", PreferenceHelper.getInstance(getActivity()).getString(WEIDU, ""));
        map.put("gps_y", PreferenceHelper.getInstance(getActivity()).getString(JINGDU, ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<Home.DataBean>>post(HOME_PICTURE)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Home.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Home.DataBean>> response) {


                        Logger.d(gson.toJson(response.body()));
                        if (smartRefreshLayout != null) {
                            smartRefreshLayout.setEnableRefresh(true);
                            smartRefreshLayout.finishRefresh();
                            smartRefreshLayout.setEnableLoadMore(false);
                        }

                        hotList = response.body().data.get(0).getIndexShowList();
                        groupList = response.body().data.get(0).getShopList();
                        directList = response.body().data.get(0).getProShowList();
                        JiaMiToken = response.body().data.get(0).getI();
                        // intellectListBeanList = response.body().data.get(0).getIntellectList();
                        //  chiHeWanLeListBeans = response.body().data.get(0).getIconList();

                        hotGoodsAdapter.clear();
                        // groupBuyAdapter.clear();
                        directAdapter.clear();
                        hotGoodsAdapter.addAll(hotList);
                        // groupBuyAdapter.addAll(groupList);
                        directAdapter.addAll(directList);


                        mList.refreshComplete(REQUEST_COUNT);
                        //  groupRecyclerView.refreshComplete(REQUEST_COUNT);
                        directRecyclerView.refreshComplete(REQUEST_COUNT);
                        hotLRecyclerViewAdapter.notifyDataSetChanged();


                        items = new ArrayList<>();
                        if (response.body().data != null) {
                            for (int i = 0; i < response.body().data.get(0).getBannerList().size(); i++) {
                                items.add(response.body().data.get(0).getBannerList().get(i).getImg_url());
                            }
                        }

                        if (banner != null) {
                            //设置图片集合
                            banner.setImages(items);
                            //banner设置方法全部调用完毕时最后调用
                            banner.start();
                            banner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int position) {

                                    if (response.body().data.get(0).getBannerList().get(position).getRotation_img_type().equals("1")) {


                                        ZiJianShopMallDetailsActivity.actionStart(getActivity(), response.body().data.get(0).getBannerList().get(position).getShop_product_id(), response.body().data.get(0).getBannerList().get(position).getWares_id());

                                    } else if (response.body().data.get(0).getBannerList().get(position).getRotation_img_type().equals("2")) {
                                        startActivity(new Intent(getActivity(), WebViewActivity.class).putExtra("url", response.body().data.get(0).getBannerList().get(position).getHtml_url()));

                                    } else if (response.body().data.get(0).getBannerList().get(position).getRotation_img_type().equals("3")) {

                                        DaLiBaoActivity.actionStart(getActivity());

                                    }

                                }
                            });
                        }

                        //  groupBuyLRecyclerViewAdapter.notifyDataSetChanged();
                        directLRecyclerViewAdapter.notifyDataSetChanged();


                        intellectListBeanList = new ArrayList<>();
                        chiHeWanLeListBeans = new ArrayList<>();
                        //下面展示首页顶部图片
                        intellectListBeanList.addAll(response.body().data.get(0).getIntellectList());
                        chiHeWanLeListBeans.addAll(response.body().data.get(0).getIconList());

                        chiHeWanLeListAdapter.setNewData(chiHeWanLeListBeans);
                        zhiKongListAdapter.setNewData(intellectListBeanList);

                        chiHeWanLeListAdapter.notifyDataSetChanged();
                        zhiKongListAdapter.notifyDataSetChanged();

                        if (!response.body().data.get(0).getBuy_state().equals("1")) {
                            ivDaLiBao.setVisibility(View.GONE);
                        }
                        //大礼包
                        Glide.with(getActivity()).applyDefaultRequestOptions(GlideShowImageUtils.showBannerCelve()).load(response.body().data.get(0).getGift_img()).into(ivDaLiBao);
                        Glide.with(getActivity()).applyDefaultRequestOptions(GlideShowImageUtils.showBannerCelve()).load(response.body().data.get(0).getSelf_shop_img()).into(ivZiJian);
                        //   Glide.with(getActivity()).load(response.body().data.get(0).getTao_shop_img()).into(tianMaoOrTaoBao);
                        //     Glide.with(getActivity()).load(response.body().data.get(0).getJindong_shop_img()).into(ivJd);

                        Glide.with(getActivity()).applyDefaultRequestOptions(GlideShowImageUtils.showBannerCelve()).load(response.body().data.get(0).getUnion_shop_img()).into(tianMaoOrTaoBao);
//                        tianMaoOrTaoBao.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });

                        taobaoPicture = response.body().data.get(0).getTao_shop_img();
                        jingdongPicture = response.body().data.get(0).getJindong_shop_img();
                        pinduoduoPicture = response.body().data.get(0).getPin_shop_img();
                        ivZiJian.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ZiJianShopMallActivity.actionStart(getActivity());
                            }
                        });

                        if (response.body().data.get(0).is_activity == null) {
                            return;
                        }
                        if (response.body().data.get(0).is_activity.equals("1")) {
                            return;
                        }
                        setHuoDong(response.body().data.get(0).getActivity());
                    }

                    @Override
                    public void onError(Response<AppResponse<Home.DataBean>> response) {

                        AlertUtil.t(getActivity(), response.getException().getMessage());
                    }
                });
    }

    // public String shiFouYanzheng;

    public void getYaoQingNet(Context cnt) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04341");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(cnt).getAppToken());
        // map.put("shop_product_id", productId);
        //map.put("wares_id", warseId);

        Log.i("taoken_gg", UserManager.getManager(cnt).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<TuiGuangMaModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(cnt)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuiGuangMaModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TuiGuangMaModel.DataBean>> response) {

                        if (response.body().data.get(0).getDisplay().equals("0")) {
                            //没有上级
                            //shiFouYanzheng = "0";
                            PreferenceHelper.getInstance(getActivity()).putString(App.SHIFOUYOUSHANGJI, "0");
                        } else {
                            //有上级
                            //shiFouYanzheng = "1";
                            PreferenceHelper.getInstance(getActivity()).putString(App.SHIFOUYOUSHANGJI, "1");
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<TuiGuangMaModel.DataBean>> response) {
                        AlertUtil.t(cnt, response.getException().getMessage());
                    }
                });
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

                    stopLocation();
                } else {

                    //"gps_x=45.666043" + "&" + "gps_y=126.605713";
                    // x 纬度 y 经度
                    PreferenceHelper.getInstance(getActivity()).putString(WEIDU, "45.666043");
                    PreferenceHelper.getInstance(getActivity()).putString(JINGDU, "126.605713");
                    PreferenceHelper.getInstance(getActivity()).putString(AppConfig.LOCATION_CITY_NAME, "哈尔滨");
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
                PreferenceHelper.getInstance(getActivity()).putString(AppConfig.LOCATION_CITY_NAME, "哈尔滨");
                Log.i("Location_result", "定位失败");
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
        //初始化client
        locationClient = new AMapLocationClient(getActivity().getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(gaodeDingWeiListener);
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

    @Override
    public void onStart() {
        super.onStart();

    }

    /**
     * is_activity	是否显示活动 1.没活动 2.有活动
     * img_url	图url
     * img_width	图宽
     * img_height	图高
     * html_url	跳转地址url（app用）
     * activity_type_id	类型 1.商品 2.广告
     * wares_id	商品id
     * shop_product_id	套餐id
     * is_share	是否分享 1.分享 2.不分享
     * share_title	分享标题
     * share_detail	分享描述
     * share_url	分享链接
     * share_img	分享图片
     */
    private String strFirst = "0";//0第一次 1第二次

    private void setHuoDong(List<Home.DataBean.activity> activity) {

        if (activity.size() == 0) {
            return;
        }
        if (strFirst.equals("1")) {
            return;
        }
        strFirst = "1";


        HuoDongTanCengActivity.actionStart(getActivity(), activity);


    }
}


