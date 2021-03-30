package com.smarthome.magic.activity.tongcheng58;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tongcheng58.adapter.TcBianminAdapter;
import com.smarthome.magic.activity.tongcheng58.model.TcBianminModel;
import com.smarthome.magic.adapter.NewsFragmentPagerAdapter;
import com.smarthome.magic.app.App;
import com.smarthome.magic.basicmvp.BaseFragment;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TongchengTabBianminFragment extends BaseFragment {
    @BindView(R.id.magic_title)
    MagicIndicator magic_title;
    @BindView(R.id.viewpager)
    CustomViewPager viewpager;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private String ir_column_type;
    private String x_jingdu;
    private String y_weidu;
    private int page_number;
    private boolean isFirst;

    private TcBianminModel.DataBean bianminBean;
    private List<TcBianminModel.DataBean.IrNoticeTypeListBean> irNoticeTypeList = new ArrayList<>();
    private ArrayList<Fragment> messageListFragments = new ArrayList<>();

    private List<TcBianminModel.DataBean.IrNoticeListBean> irNoticeList = new ArrayList<>();
    private TcBianminAdapter adapter;

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.tongcheng_frag_main_bianmin;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initStart();
        return rootView;
    }

    private void initStart() {
        x_jingdu = PreferenceHelper.getInstance(getActivity()).getString(App.JINGDU, "");
        y_weidu = PreferenceHelper.getInstance(getActivity()).getString(App.WEIDU, "");
        page_number = 0;
        isFirst = true;
        ir_column_type = "1852";

        initAdapter();
        initSM();
        getData();
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
        adapter = new TcBianminAdapter(R.layout.tongcheng_item_bianmin, irNoticeList);
        rv_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_list.setAdapter(adapter);
    }

    private void getData() {
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", "17009");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("ir_column_type", ir_column_type);
        map.put("x", x_jingdu);
        map.put("y", y_weidu);
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<TcBianminModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TcBianminModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcBianminModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            bianminBean = response.body().data.get(0);
                            if (isFirst) {
                                initVpg();
                            }

                            irNoticeList = bianminBean.getIrNoticeList();
                            adapter.setNewData(irNoticeList);
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

    private void initVpg() {
        isFirst = false;
        irNoticeTypeList = bianminBean.getIrNoticeTypeList();
        for (int i = 0; i < irNoticeTypeList.size(); i++) {
            TcBianminModel.DataBean.IrNoticeTypeListBean typeListBean = irNoticeTypeList.get(i);
            Bundle data = new Bundle();
            data.putString("title", typeListBean.getIr_column_type_name());
            TongchengHomeItemFragment newfragment = new TongchengHomeItemFragment();
            newfragment.setArguments(data);
            messageListFragments.add(newfragment);
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getChildFragmentManager(), messageListFragments);
        viewpager.setAdapter(mAdapetr);
        initMagicIndicator();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return irNoticeTypeList == null ? 0 : irNoticeTypeList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Y.getColor(R.color.text_color3));
                simplePagerTitleView.setSelectedColor(Y.getColor(R.color.text_red));
                simplePagerTitleView.setText(irNoticeTypeList.get(index).getIr_column_type_name());
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
        magic_title.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magic_title, viewpager);
    }

    private void setNet(int index) {
        viewpager.setCurrentItem(index);
        ir_column_type = irNoticeTypeList.get(index).getIr_column_type();
        getData();
    }

    private void lordData() {
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", "17009");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("ir_column_type", ir_column_type);
        map.put("x", x_jingdu);
        map.put("y", y_weidu);
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<TcBianminModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TcBianminModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcBianminModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            bianminBean = response.body().data.get(0);
                            List<TcBianminModel.DataBean.IrNoticeListBean> list = bianminBean.getIrNoticeList();
                            irNoticeList.addAll(list);
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