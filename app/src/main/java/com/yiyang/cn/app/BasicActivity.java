package com.yiyang.cn.app;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.basicmvp.BasicModel;
import com.yiyang.cn.basicmvp.BasicPresenter;
import com.yiyang.cn.util.TUtil;


public abstract class BasicActivity<T extends BasicPresenter, E extends BasicModel> extends BasicSupportActivity {
    /**
     * 在使用自定义toolbar时候的根布局 =toolBarView+childView
     */
    protected View rootView;//在使用自定义toolbar时候的根布局 =toolBarView+childView
    public T mPresenter;
    public E mModel;
    public Context mContext;

    protected Toolbar mToolbar;
    protected TextView tv_title, tv_rightTitle, tv_leftTitle;
    protected ImageView iv_rightTitle, iv_leftTitle;
    protected View viewLine;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (null != getIntent().getExtras()) {
            getBundleExtras(extras);
        }
        setContentView(getContentViewResId());
        initImmersion();
        hideInput();
        if (showToolBar()) {
            mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
            if (null != mToolbar) {
                setSupportActionBar(mToolbar);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
//                initTitle();
                initToolbar();
            }
        }
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        this.initPresenter();
    }


    /**
     * 初始化toolbar可重写覆盖自定的toolbar,base中实现的是通用的toolbar
     */
    protected void initToolbar() {
        mToolbar.setTitle("");
        // mToolbar.setTitleTextColor(getResources().getColor(R.color.black));
        tv_title = (TextView) rootView.findViewById(R.id.toolbar_title);
        tv_title.setVisibility(View.VISIBLE);
        tv_rightTitle = (TextView) rootView.findViewById(R.id.tv_toolbar_right);
        tv_rightTitle.setVisibility(View.GONE);
        iv_rightTitle = (ImageView) rootView.findViewById(R.id.iv_toolbar_right);
        tv_leftTitle = (TextView) rootView.findViewById(R.id.tv_toolbar_left);
        iv_leftTitle = (ImageView) rootView.findViewById(R.id.iv_toolbar_left);
        viewLine = rootView.findViewById(R.id.view_line);
        if (showToolBar()) {
            viewLine.setVisibility(View.VISIBLE);
        }

        mToolbar.collapseActionView();
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.navigationicon);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (layoutResID == 0) {
            throw new RuntimeException("layoutResID==-1 have u create your layout?");
        }
        if (showToolBar() && getToolBarResId() != -1) {
            //如果需要显示自定义toolbar,并且资源id存在的情况下，实例化baseView;
            rootView = LayoutInflater.from(this).inflate(toolbarCover() ?
                    R.layout.basic_toolbar_cover : R.layout.basic_layout, null, false);//根布局
            ViewStub mVs_toolbar = (ViewStub) rootView.findViewById(R.id.vs_toolbar);//toolbar容器
            FrameLayout fl_container = (FrameLayout) rootView.findViewById(R.id.fl_container);//子布局容器
            mVs_toolbar.setLayoutResource(getToolBarResId());//toolbar资源id
            mVs_toolbar.inflate();//填充toolbar
//            mToolbar = (Toolbar) mVs_toolbar.inflate();
            LayoutInflater.from(this).inflate(layoutResID, fl_container, true);//子布局
            setContentView(rootView);
        } else {
            //不显示通用toolbar
            super.setContentView(layoutResID);
        }
    }

    /**
     * 初始化沉浸式ColorFilterImageView
     */
    public void initImmersion() {
    }

    /**
     * 获取contentView 资源id
     */
    public abstract int getContentViewResId();

    /**
     * 是否显示通用toolBar
     */
    public boolean showToolBar() {
        return false;
    }

    /**
     * 是否显示toolbar 下面边线
     */
    public boolean showToolBarLine() {
        return false;
    }


    //获取自定义toolbarview 资源id 默认为-1，showToolBar()方法必须返回true才有效
    public int getToolBarResId() {
        return R.layout.basic_common_toolbar;
    }

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * 关闭软键盘
     */
    public void hideInput() {
        if (this.getCurrentFocus() == null) return;
        ((InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rootView = null;
        AppManager.getAppManager().finishActivity(this);

    }

    /**
     * toolbar是否覆盖在内容区上方
     *
     * @return false 不覆盖  true 覆盖
     */
    protected boolean toolbarCover() {
        return false;
    }

    /**
     * 简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
     */
    public void initPresenter() {
    }

}
