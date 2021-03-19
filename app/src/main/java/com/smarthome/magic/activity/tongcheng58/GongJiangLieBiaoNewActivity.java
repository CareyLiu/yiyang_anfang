package com.smarthome.magic.activity.tongcheng58;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smarthome.magic.R;
import com.smarthome.magic.adapter.NewsFragmentPagerAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.fragment.taoke_shangcheng.TaoKeMallListFragment;
import com.smarthome.magic.model.TaoKeTitleListModel;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GongJiangLieBiaoNewActivity extends BaseActivity {


    @BindView(R.id.magic_indicator4)
    MagicIndicator magicIndicator4;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;

    List<String> dataBeanList = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBeanList.add("家居维修");
        dataBeanList.add("同城快递");
        dataBeanList.add("沙发清洗");
        dataBeanList.add("家居拆装");
        setThisAdapter();
        initMagicIndicator1(dataBeanList);
    }

    private void setThisAdapter() {
        // messageListFragments.clear();//清空
        int count = dataBeanList.size();
        for (int i = 0; i < count; i++) {
            Bundle data = new Bundle();
            data.putString("title", dataBeanList.get(i));
            GongJiangLieBiaoFragment gongJiangLieBiaoFragment = new GongJiangLieBiaoFragment();
            gongJiangLieBiaoFragment.setArguments(data);
            fragments.add(gongJiangLieBiaoFragment);
        }
        NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(mAdapetr);

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initMagicIndicator1(final List<String> list) {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator4);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.black_222222));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.pinkff002f));
                simplePagerTitleView.setText(list.get(index));
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
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(getResources().getColor(R.color.pinkff002f));
                return linePagerIndicator;
            }
        });
         commonNavigator.setAdjustMode(true);
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
        commonNavigator.notifyDataSetChanged();

    }


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
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, GongJiangLieBiaoNewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
