package com.yiyang.cn.activity.nongye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.view.SelectTabView;
import com.yiyang.cn.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShishujuActivity extends BaseActivity {

    @BindView(R.id.ll_title)
    LinearLayout ll_title;
    @BindView(R.id.iv_shujutu)
    ImageView iv_shujutu;

    private List<SelectTabView> tabViews = new ArrayList<>();
    private List<String> title = new ArrayList<>();

    @Override
    public int getContentViewResId() {
        return R.layout.nongye_act_title_biao;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String name) {
        Intent intent = new Intent(context, ShishujuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initView();
        initTitle();
    }

    private void initView() {
        String name = getIntent().getStringExtra("name");
        tv_title.setText(name);
        if (name.equals("实时数据")) {
            iv_shujutu.setImageResource(R.mipmap.shuju);
        } else {
            iv_shujutu.setImageResource(R.mipmap.tanshuju);
        }
    }

    private void initTitle() {
        title.add("农业基地1");
        title.add("农业基地2");
        title.add("农业基地3");
        title.add("农业基地4");
        title.add("农业基地5");

        for (int i = 0; i < title.size(); i++) {
            SelectTabView tabView = new SelectTabView(mContext);
            tabView.setTitle(title.get(i));
            if (i == 0) {
                tabView.setSelect(true);
            } else {
                tabView.setSelect(false);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ll_title.addView(tabView, params);
            tabViews.add(tabView);
            int finalI = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickTitle(finalI);
                }
            });
        }
    }

    private void clickTitle(int pos) {
        for (int i = 0; i < tabViews.size(); i++) {
            SelectTabView selectTabView = tabViews.get(i);
            if (i == pos) {
                selectTabView.setSelect(true);
            } else {
                selectTabView.setSelect(false);
            }
        }
    }

}