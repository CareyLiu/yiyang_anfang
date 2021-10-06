package com.yiyang.cn.activity.a_yiyang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.HomeActivity;
import com.yiyang.cn.activity.a_yiyang.fragment.TabHomeFragment;
import com.yiyang.cn.activity.a_yiyang.fragment.TabShengxianFragment;
import com.yiyang.cn.activity.a_yiyang.fragment.TabWodeFragment;
import com.yiyang.cn.activity.a_yiyang.fragment.TabXiaoxiFragment;
import com.yiyang.cn.activity.a_yiyang.model.JiashuModel;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.fragment.znjj.ZhiNengJiaJuFragment;
import com.yiyang.cn.util.Y;
import com.yiyang.cn.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ZhinengActivity extends BaseActivity {

    @BindView(R.id.vpg_content)
    NoScrollViewPager vpg_content;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_zhineng;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ZhinengActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        List<Fragment> fragments = new ArrayList<>(1);
        ZhiNengJiaJuFragment tabAnfangFragment = new ZhiNengJiaJuFragment();

        fragments.add(tabAnfangFragment);

        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        vpg_content.setOffscreenPageLimit(5);
        vpg_content.setScroll(false);
        vpg_content.setAdapter(adapter);


        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_YIYANG_ZNJJ_BACK) {
                    finish();
                }
            }
        }));
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }
}
