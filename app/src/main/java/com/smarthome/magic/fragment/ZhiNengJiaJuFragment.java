package com.smarthome.magic.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.basicmvp.BaseFragment;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;


/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class ZhiNengJiaJuFragment extends BaseFragment  {
    private static final String TAG = "ZhiNengJiaJuFragment";
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;


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
        initData();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        srLSmart.setEnableLoadMore(false);
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }


}
