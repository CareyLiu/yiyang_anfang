package com.smarthome.magic.activity.tongcheng58;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tongcheng58.adapter.TcGongjiangAdapter;
import com.smarthome.magic.activity.tongcheng58.model.TcGongjiangModel;
import com.smarthome.magic.adapter.NewsFragmentPagerAdapter;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.view.CustomViewPager;
import com.smarthome.magic.view.magicindicator.MagicIndicator;
import com.smarthome.magic.view.magicindicator.ViewPagerHelper;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.smarthome.magic.view.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class GongJiangLieBiaoNewActivity extends BaseActivity {
    @BindView(R.id.magic_indicator4)
    MagicIndicator magicIndicator4;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;


    private String service_type;
    private String x_jingdu;
    private String y_weidu;
    private int page_number;

    private TcGongjiangModel.DataBean gongjiangBean;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private List<TcGongjiangModel.DataBean.IconListBean> iconList = new ArrayList<>();

    private List<TcGongjiangModel.DataBean.CraftsManListBean> craftsManList = new ArrayList<>();
    private TcGongjiangAdapter adapter;
    private boolean isFirst;


    @Override
    public int getContentViewResId() {
        return R.layout.layout_gongjiang_liebiao;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("工匠列表");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String service_type) {
        Intent intent = new Intent(context, GongJiangLieBiaoNewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("service_type", service_type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStart();
        initAdapter();
        getData();
        initSM();
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                lordData();
            }
        });
    }

    private void initAdapter() {
        adapter = new TcGongjiangAdapter(R.layout.tongcheng_item_gongjiang, craftsManList);
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        View view = View.inflate(mContext, R.layout.empty_view, null);
        ImageView noneImage = view.findViewById(R.id.iv_image);
        noneImage.setBackgroundResource(R.mipmap.shop_pic_none);
        adapter.setEmptyView(view);
    }

    private void initVpg() {
        isFirst = false;
        iconList = gongjiangBean.getIconList();
        setThisAdapter();
        initMagicIndicator1();
        for (int i = 0; i < iconList.size(); i++) {
            String serviceType = iconList.get(i).getService_type();
            if (service_type.equals(serviceType)) {
                viewPager.setCurrentItem(i);
            }
        }
    }

    private void initStart() {
        service_type = getIntent().getStringExtra("service_type");
        x_jingdu = PreferenceHelper.getInstance(mContext).getString(App.JINGDU, "");
        y_weidu = PreferenceHelper.getInstance(mContext).getString(App.WEIDU, "");
        page_number = 0;
        isFirst = true;
    }

    private void getData() {
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", "17005");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("service_type", service_type);
        map.put("x", x_jingdu);
        map.put("y", y_weidu);
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<TcGongjiangModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TcGongjiangModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcGongjiangModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            gongjiangBean = response.body().data.get(0);
                            if (isFirst) {
                                initVpg();
                            }

                            craftsManList = gongjiangBean.getCraftsManList();
                            adapter.setNewData(craftsManList);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    private void setThisAdapter() {
        int count = iconList.size();
        for (int i = 0; i < count; i++) {
            Bundle data = new Bundle();
            data.putString("title", iconList.get(i).getService_type_name());
            TongchengHomeItemFragment newfragment = new TongchengHomeItemFragment();
            newfragment.setArguments(data);
            fragments.add(newfragment);
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mAdapetr);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initMagicIndicator1() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return iconList == null ? 0 : iconList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Y.getColor(R.color.text_color3));
                simplePagerTitleView.setSelectedColor(Y.getColor(R.color.text_red));
                simplePagerTitleView.setText(iconList.get(index).getService_type_name());
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setNet(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(Y.getColor(R.color.text_red));
                return linePagerIndicator;
            }
        });
        magicIndicator4.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator4, viewPager);
    }

    private void setNet(int index) {
        viewPager.setCurrentItem(index);
        service_type = iconList.get(index).getService_type();
        getData();
    }

    private void lordData() {
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", "17005");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("service_type", service_type);
        map.put("x", x_jingdu);
        map.put("y", y_weidu);
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<TcGongjiangModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TcGongjiangModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcGongjiangModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            gongjiangBean = response.body().data.get(0);
                            List<TcGongjiangModel.DataBean.CraftsManListBean> craftsManListSS = gongjiangBean.getCraftsManList();
                            craftsManList.addAll(craftsManListSS);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }
}
