package com.smarthome.magic.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.NewsFragmentPagerAdapter;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.view.CustomViewPager;
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

import org.jaaksi.pickerview.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;


/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class ZhiNengJiaJuFragment extends BaseFragment {
    private static final String TAG = "ZhiNengJiaJuFragment";
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewPager)
    NoSlidingViewPager viewPager;


    private List<String> tabs = new ArrayList<>();
    private NewsFragmentPagerAdapter viewPagerAdapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private ZhiNengDeviceFragment zhiNengDeviceFragment;
    private ZhiNengRoomFragment zhiNengRoomFragment;

    @Override
    protected void initLogic() {

    }

    public void initData() {

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

    private void initViewpager() {
        viewPager.setScroll(false);
        zhiNengDeviceFragment = ZhiNengDeviceFragment.newInstance(null);
        fragmentList.add(zhiNengDeviceFragment);
        zhiNengRoomFragment = ZhiNengRoomFragment.newInstance(null);
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
        initViewpager();
        initMagicIndicator();
        initData();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }


}
