package com.smarthome.magic.activity.gaiban;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
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
import com.smarthome.magic.activity.DefaultX5WebView_HaveNameActivity;
import com.smarthome.magic.activity.PlumbingHeaterActivity;
import com.smarthome.magic.activity.TuanYouWebView;
import com.smarthome.magic.activity.WebViewActivity;
import com.smarthome.magic.activity.fenxiang_tuisong.HuoDongTanCengActivity;
import com.smarthome.magic.activity.gouwuche.GouWuCheActivity;
import com.smarthome.magic.activity.homepage.DaLiBaoActivity;
import com.smarthome.magic.activity.jd_taobao_pinduoduo.TaoBao_Jd_PinDuoDuoActivity;
import com.smarthome.magic.activity.saoma.ScanActivity;
import com.smarthome.magic.activity.tuangou.TuanGouShangJiaListActivity;
import com.smarthome.magic.activity.xin_tuanyou.TuanYouList;
import com.smarthome.magic.activity.zijian_shangcheng.FenLeiThirdActivity;
import com.smarthome.magic.activity.zijian_shangcheng.ZiJianShopMallActivity;
import com.smarthome.magic.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.smarthome.magic.adapter.ChiHeWanLeListAdapter;
import com.smarthome.magic.adapter.DirectAdapter;
import com.smarthome.magic.adapter.HotGoodsAdapter;
import com.smarthome.magic.adapter.ZhiKongListAdapter;
import com.smarthome.magic.adapter.ZhiNengDeviceListAdapter;
import com.smarthome.magic.adapter.gaiban.HomeReMenAdapter;
import com.smarthome.magic.adapter.gaiban.HomeZiYingAdapter;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.Radius_GlideImageLoader;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.fragment.MyFragmentPagerAdapter;
import com.smarthome.magic.fragment.RemenFragment;
import com.smarthome.magic.fragment.ZiYingFragment;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.Home;
import com.smarthome.magic.model.TuiGuangMaModel;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.GlideShowImageUtils;
import com.smarthome.magic.util.GridAverageUIDecoration;
import com.smarthome.magic.util.Utils;
import com.smarthome.magic.view.MenDianViewPager;
import com.smarthome.magic.view.ObservableScrollView;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.app.App.JINGDU;
import static com.smarthome.magic.app.App.WEIDU;
import static com.smarthome.magic.app.App.get;
import static com.smarthome.magic.get_net.Urls.HOME_PICTURE;

/**
 * Created by Administrator on 2018/3/29 0029.
 */
public class HomeFragment_New extends BaseFragment implements ObservableScrollView.ScrollViewListener, View.OnClickListener {
    private static final String TAG = "HomeFragment";


    ImageView ivGouwuche;


    private View view;
    private Unbinder unbinder;
    private ImageView iv_taoke;
    HotGoodsAdapter hotGoodsAdapter = null;//热门商品适配器
    LRecyclerViewAdapter hotLRecyclerViewAdapter = null;
    List<Home.DataBean.IndexShowListBean> remenListBean = new ArrayList<>();
    List<Home.DataBean.ShopListBean> groupList = new ArrayList<>();
    DirectAdapter directAdapter = null;//直供商品适配器
    LRecyclerViewAdapter directLRecyclerViewAdapter = null;
    List<Home.DataBean.ProShowListBean> ziYingListBean = new ArrayList<>();
    public int choosePostion = 99999;

    LinearLayout llMain;
    // LRecyclerView groupRecyclerView;

    Banner banner;


    private View topPanel, middlePanel;
    private int topHeight;
    private ConstraintLayout clZiYing_Middle, clReMen_Middle;
    private ConstraintLayout clZiYing_Top, clReMen_Top;
    private LinearLayout llBackground_Top, llBackground_Middle;
    private TextView tvZiYingTop, tvRemTop, tvZiYingZhiGongTop, tvReMenShangPinTop;
    private TextView tvZiYingMiddle, tvReMenMiddle, tvZiYingZhiGongMiddle, tvReMenShangPinMiddle;


    private View viewLineTop, viewLineMiddle, remenViewLineTop;
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

    ImageView ziYingZhiGon;
    ImageView reMenShangPin;
    ImageView ivJd;
    LocationManager locationManager;
    ProgressDialog progressDialog;


    ConstraintLayout ll_shagnchengzhuanqu;

    private String rimenOrZiYing = "0"; //0 自营直供 1 热门商品
    ConstraintLayout clQuanBu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化定位
        initLocation();
        startLocation();
    }
//
//    public void getNet_check() {
//
//        Map<String, String> map = new HashMap<>();
//        map.put("code", "00002");
//        map.put("key", Urls.key);
//        map.put("version_no", getAppVersionName(getActivity()));
//        map.put("version_type", "6");
//        map.put("version_code", getAppVersionCode(getActivity()));
//        Gson gson = new Gson();
//        OkGo.<AppResponse<CheckModel.DataBean>>post("https://jy.hljsdkj.com/msg")
//                .tag(this)//
//                .upJson(gson.toJson(map))
//                .execute(new JsonCallback<AppResponse<CheckModel.DataBean>>() {
//                    @Override
//                    public void onSuccess(final Response<AppResponse<CheckModel.DataBean>> response) {
//                        /**
//                         * update : Yes
//                         * new_version : xxxxx
//                         * apk_url : http://cdn.the.url.of.apk/or/patch
//                         * update_log : xxxx
//                         * delta : false
//                         * new_md5 : xxxxxxxxxxxxxx
//                         * target_size : 601132
//                         */
//
//                        Log.i("check_up", new Gson().toJson(response.body()));
//
//                        if (response.body().data.get(0).getIsnew().equals("2")) {
//                            UpdateManager.getUpdateManager().checkAppUpdate(getActivity(), false, response.body().data.get(0));
//
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onError(Response<AppResponse<CheckModel.DataBean>> response) {
//                        AlertUtil.t(getActivity(), response.getException().getMessage());
//                    }
//                });
//    }


//    private String getAppVersionName(Context context) {
//        String versionName = "";
//        try {
//            PackageManager packageManager = context.getPackageManager();
//            PackageInfo packageInfo = packageManager.getPackageInfo("com.smarthome.magic", 0);
//            versionName = packageInfo.versionName;
//            if (TextUtils.isEmpty(versionName)) {
//                return "";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return versionName;
//    }
//
//    private String getAppVersionCode(Context context) {
//        String versionName = "";
//        try {
//            PackageManager packageManager = context.getPackageManager();
//            PackageInfo packageInfo = packageManager.getPackageInfo("com.smarthome.magic", 0);
//            versionName = String.valueOf(packageInfo.versionCode);
//            if (TextUtils.isEmpty(versionName)) {
//                return "";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return versionName;
//    }

    SmartRefreshLayout smartRefreshLayout;
    ObservableScrollView nestedScrollView;
    RelativeLayout rl_bottom;


    RecyclerView rlv_ziYing;
    HomeZiYingAdapter homeZiYingAdapter;
    RecyclerView rlvRemen;
    HomeReMenAdapter homeReMenAdapter;

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_homefragment_new;
    }

    @Override
    protected void initView(View view) {

        nestedScrollView = view.findViewById(R.id.scrollView);
        topPanel = view.findViewById(R.id.topPanel);
        middlePanel = view.findViewById(R.id.middlePanel);
        clZiYing_Top = topPanel.findViewById(R.id.cl_ziying);
        clReMen_Top = topPanel.findViewById(R.id.cl_remen);
        clZiYing_Middle = middlePanel.findViewById(R.id.cl_ziying);
        clReMen_Middle = middlePanel.findViewById(R.id.cl_remen);
        tvZiYingTop = topPanel.findViewById(R.id.tv_ziying);
        tvRemTop = topPanel.findViewById(R.id.tv_remen);
        llMain = view.findViewById(R.id.ll_main);

        banner = view.findViewById(R.id.banner);
        ivDaLiBao = view.findViewById(R.id.iv_dalibao);
        clQuanBu = view.findViewById(R.id.cl_quanbu);
        ll_shagnchengzhuanqu = view.findViewById(R.id.ll_shagnchengzhuanqu);

        ziYingZhiGon = view.findViewById(R.id.iv_ziying);
        reMenShangPin = view.findViewById(R.id.iv_remen);
        ivGouwuche = view.findViewById(R.id.iv_gouwuche);
        LottieAnimationView animationView = view.findViewById(R.id.animation_view);
        animationView.setAnimation("freec3_data.json");
        animationView.setImageAssetsFolder("gifs/");
        animationView.loop(true);
        animationView.playAnimation();

        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaLiBaoActivity.actionStart(getActivity());
            }
        });
        rl_bottom = view.findViewById(R.id.rl_bottom);
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

        /**
         *     private TextView tvZiYingTop, tvRemTop, tvZiYingZhiGongTop, tvReMenShangPinTop;
         *     private TextView tvZiYingMiddle, tvReMenMiddle, tvZiYingZhiGongMiddle, tvReMenShangPinMiddle;
         */


        tvZiYingZhiGongTop = topPanel.findViewById(R.id.ziyingzhigong);
        tvReMenShangPinTop = topPanel.findViewById(R.id.remenshangpin);


        tvZiYingMiddle = middlePanel.findViewById(R.id.tv_ziying);
        tvReMenMiddle = middlePanel.findViewById(R.id.tv_remen);
        tvZiYingZhiGongMiddle = middlePanel.findViewById(R.id.ziyingzhigong);
        tvReMenShangPinMiddle = middlePanel.findViewById(R.id.remenshangpin);
        //吃喝玩乐相关列表
        chiHeWanLeList = view.findViewById(R.id.rv_chihe_wanle_list);
        zhiKongList = view.findViewById(R.id.zhikong_list);
        ivZiJian = view.findViewById(R.id.iv_zijian);
        tianMaoOrTaoBao = view.findViewById(R.id.iv_tianmao_or_taobao);
        nestedScrollView.setScrollViewListener(this);
        rlv_ziYing = view.findViewById(R.id.rlv_ziying_or_remen);
        viewLineTop = topPanel.findViewById(R.id.view_line);
        // viewLineMiddle = middlePanel.findViewById(R.id.view_line);
        rlvRemen = view.findViewById(R.id.rlv_remen);
        remenViewLineTop = topPanel.findViewById(R.id.view_line_remen);
        clZiYing_Top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(getActivity(), "点击了自营");
                rlv_ziYing.setVisibility(View.VISIBLE);
                rlvRemen.setVisibility(View.GONE);
                setZiYingOrReMenLine("0");
                rimenOrZiYing = "0";
            }
        });
        clReMen_Top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UIHelper.ToastMessage(getActivity(), "点击了热门");
                rlv_ziYing.setVisibility(View.GONE);
                rlvRemen.setVisibility(View.VISIBLE);
                setZiYingOrReMenLine("1");
                rimenOrZiYing = "1";
            }
        });

        clReMen_Middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(getActivity(), "点击了热门");
                rlv_ziYing.setVisibility(View.GONE);
                rlvRemen.setVisibility(View.VISIBLE);
                setZiYingOrReMenLine("1");
                rimenOrZiYing = "1";
            }
        });

        clZiYing_Middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(getActivity(), "点击了自营");
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

        ivGouwuche.setOnClickListener(new View.OnClickListener() {
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


//        header = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_header, (ViewGroup) Objects.requireNonNull(getActivity()).findViewById(android.R.id.content), false);
//        footer = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_footer, (ViewGroup) getActivity().findViewById(android.R.id.content), false);
//
//        TextView tvMore = header.findViewById(R.id.tv_pinpai_more);
//        ImageView ivMore = header.findViewById(R.id.iv_more);
//
//        tvMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FenLeiThirdActivity.actionStart(getActivity(), "品牌直供", "2");
//            }
//        });
//
//        ivMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FenLeiThirdActivity.actionStart(getActivity(), "品牌直供", "2");
//            }
//        });
//
//
//
//        tvRemenMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FenLeiThirdActivity.actionStart(getActivity(), "热门商品", "1");
//            }
//        });
//
//        ivRemenMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FenLeiThirdActivity.actionStart(getActivity(), "热门商品", "1");
//            }
//        });


        // directRecyclerView = header.findViewById(R.id.list);//品牌制造商
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

                            //YanShiActivity.actionStart(getActivity());
                        } else {
                            Toast.makeText(getActivity(), "该应用需要赋予访问相机的权限，不开启将无法正常工作！", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                //FenXiangTuiSongActivity.actionStart(getActivity());

            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        GridLayoutManager gridLayoutManagerChi_He = new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false);
        // gridLayoutManagerChi_He.setOrientation(LinearLayoutManager.VERTICAL);
        //   revisionRecycler.setLayoutManager(gridLayoutManager);

        chiHeWanLeList.setLayoutManager(gridLayoutManagerChi_He);


        chiHeWanLeListAdapter = new ChiHeWanLeListAdapter(R.layout.item_chihewanle, chiHeWanLeListBeans);
        chiHeWanLeListAdapter.openLoadAnimation();//默认为渐显效果
        chiHeWanLeList.setAdapter(chiHeWanLeListAdapter);


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


        // wind_heater = header.findViewById(R.id.wind_heater);
        // hydronic = header.findViewById(R.id.hydronic);
        //  start_engine = header.findViewById(R.id.start_engine);
        //  vehicle_info = header.findViewById(R.id.vehicle_info);

        //  wind_heater.setOnClickListener(this);
        //  hydronic.setOnClickListener(this);
        //  start_engine.setOnClickListener(this);
        //   vehicle_info.setOnClickListener(this);


        GridItemDecoration divider = new GridItemDecoration.Builder(getActivity())
                .setHorizontal(R.dimen.default_divider_padding_5dp)
                .setVertical(R.dimen.default_divider_padding_5dp)
                .setColorResource(R.color.white)
                .build();
//        directRecyclerView.addItemDecoration(divider);
//
//
//        directRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        // groupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        hotLRecyclerViewAdapter = new LRecyclerViewAdapter(hotGoodsAdapter);
        directLRecyclerViewAdapter = new LRecyclerViewAdapter(directAdapter);
//        directRecyclerView.setAdapter(directLRecyclerViewAdapter);
//        directRecyclerView.setLoadMoreEnabled(false);
//        directRecyclerView.setPullRefreshEnabled(false);


        //设置图片加载器
        banner.setImageLoader(new Radius_GlideImageLoader());
        getData();
        //   getNet();
        hotLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                ZiJianShopMallDetailsActivity.actionStart(getActivity(), remenListBean.get(position).getShop_product_id(), remenListBean.get(position).getWares_id());

                // startActivity(new Intent(getActivity(), GoosDetailsActivity.class)
                //       .putExtra("shop_product_id", hotList.get(position).getShop_product_id())
                //     .putExtra("wares_id", hotList.get(position).getWares_id()));
            }
        });


        homeZiYingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ZiJianShopMallDetailsActivity.actionStart(getActivity(), ziYingListBean.get(position).getShop_product_id(), ziYingListBean.get(position).getWares_id());

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
        setZiYingOrReMenLine("0");
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

                        remenListBean = response.body().data.get(0).getIndexShowList();
                        groupList = response.body().data.get(0).getShopList();
                        ziYingListBean = response.body().data.get(0).getProShowList();
                        JiaMiToken = response.body().data.get(0).getI();
                        // intellectListBeanList = response.body().data.get(0).getIntellectList();
                        //  chiHeWanLeListBeans = response.body().data.get(0).getIconList();

                        homeZiYingAdapter.setNewData(ziYingListBean);
                        homeZiYingAdapter.notifyDataSetChanged();


                        homeReMenAdapter.setNewData(remenListBean);
                        homeReMenAdapter.notifyDataSetChanged();


                        //  groupRecyclerView.refreshComplete(REQUEST_COUNT);

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
                            ll_shagnchengzhuanqu.setVisibility(View.VISIBLE);
                        } else {
                            ll_shagnchengzhuanqu.setVisibility(View.GONE);
                            ivDaLiBao.setVisibility(View.VISIBLE);
                        }

                        RoundedCorners roundedCorners = new RoundedCorners(1);
                        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                        //大礼包
                        Glide.with(getActivity()).applyDefaultRequestOptions(GlideShowImageUtils.showBannerCelve()).load(response.body().data.get(0).getGift_img()).into(ivDaLiBao);


                        Glide.with(getActivity()).applyDefaultRequestOptions(options).load(response.body().data.get(0).getWaresTypeList().get(0).getImg_url()).into(ivZiJian);


                        Glide.with(getActivity()).applyDefaultRequestOptions(options).load(response.body().data.get(0).getWaresTypeList().get(1).getImg_url()).into(ziYingZhiGon);
                        Glide.with(getActivity()).applyDefaultRequestOptions(options).load(response.body().data.get(0).getWaresTypeList().get(3).getImg_url()).into(reMenShangPin);

                        //   Glide.with(getActivity()).load(response.body().data.get(0).getTao_shop_img()).into(tianMaoOrTaoBao);
                        //     Glide.with(getActivity()).load(response.body().data.get(0).getJindong_shop_img()).into(ivJd);

                        Glide.with(getActivity()).applyDefaultRequestOptions(GlideShowImageUtils.showZhengFangXing()).load(response.body().data.get(0).getWaresTypeList().get(2).getImg_url()).into(tianMaoOrTaoBao);
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

                        ziYingZhiGon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                FenLeiThirdActivity.actionStart(getActivity(), "品牌直供", "2");
                            }
                        });
                        reMenShangPin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FenLeiThirdActivity.actionStart(getActivity(), "热门商品", "1");

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

    //是否展示viewline
    private String strViewLine; // 0不展示 1展示

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        int[] location = new int[2];
        clZiYing_Middle.getLocationOnScreen(location);
        int locationY = location[1];
        Log.e("locationY", locationY + " " + "topHeight的值是：" + topHeight);

        if (locationY <= topHeight && (topPanel.getVisibility() == View.GONE || topPanel.getVisibility() == View.INVISIBLE)) {
            topPanel.setVisibility(View.VISIBLE);
            tvRemTop.setVisibility(View.GONE);
            tvZiYingTop.setVisibility(View.GONE);
            //  llBackground_Top.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
            clReMen_Top.setBackgroundResource(R.color.white);
            clZiYing_Top.setBackgroundResource(R.color.white);
            strViewLine = "1";
            //  viewLineTop.setVisibility(View.VISIBLE);
            rl_bottom.setVisibility(View.VISIBLE);
        }

        if (locationY > topHeight && topPanel.getVisibility() == View.VISIBLE) {
            topPanel.setVisibility(View.GONE);
            tvRemTop.setVisibility(View.VISIBLE);
            tvZiYingTop.setVisibility(View.VISIBLE);
            //llBackground_Top.setBackgroundColor(getActivity().getResources().getColor(R.color.grayfff5f5f5));
            clReMen_Top.setBackgroundResource(R.color.grayfff5f5f5);
            clZiYing_Top.setBackgroundResource(R.color.grayfff5f5f5);
            strViewLine = "0";
            // viewLineTop.setVisibility(View.GONE);
            rl_bottom.setVisibility(View.GONE);

        }


    }


//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        Rect frame = new Rect();
//        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        int statusBarHeight = frame.top;//状态栏高度
//
//        int titleBarHeight = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();//标题栏高度
//        topHeight = titleBarHeight + statusBarHeight;
//    }


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
//
//            if (strViewLine.equals("0")) {
//                viewLineTop.setVisibility(View.GONE);
//                remenViewLineTop.setVisibility(View.GONE);
//            } else {
//                viewLineTop.setVisibility(View.VISIBLE);
//                remenViewLineTop.setVisibility(View.GONE);
//            }

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
//            if (strViewLine.equals("0")) {
//                viewLineTop.setVisibility(View.GONE);
//                remenViewLineTop.setVisibility(View.GONE);
//            } else {
//                viewLineTop.setVisibility(View.GONE);
//                remenViewLineTop.setVisibility(View.VISIBLE);
//            }


            viewLineTop.setVisibility(View.GONE);
            remenViewLineTop.setVisibility(View.VISIBLE);


        }

    }
}


