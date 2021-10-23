package com.yiyang.cn.activity.a_yiyang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundRelativeLayout;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.adapter.AYiyangAdapter;
import com.yiyang.cn.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CaigouActivity extends BaseActivity {


    @BindView(R.id.iv_icon_sousuo)
    ImageView iv_icon_sousuo;
    @BindView(R.id.rrl_sousuo)
    RoundRelativeLayout rrl_sousuo;
    @BindView(R.id.view_tab_quanbu)
    View view_tab_quanbu;
    @BindView(R.id.rl_tab_quanbu)
    RelativeLayout rl_tab_quanbu;
    @BindView(R.id.view_tab_jinxingzhong)
    View view_tab_jinxingzhong;
    @BindView(R.id.rl_tab_jinxingzhong)
    RelativeLayout rl_tab_jinxingzhong;
    @BindView(R.id.view_tab_yiguoqi)
    View view_tab_yiguoqi;
    @BindView(R.id.rl_tab_yiguoqi)
    RelativeLayout rl_tab_yiguoqi;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_caigou;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CaigouActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("政府采购");
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
        initAdapter();
    }


    private void initAdapter() {
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        AYiyangAdapter adapter = new AYiyangAdapter(R.layout.yiyang_item_caigou, strings);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @OnClick({R.id.rrl_sousuo, R.id.rl_tab_quanbu, R.id.rl_tab_jinxingzhong, R.id.rl_tab_yiguoqi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rrl_sousuo:
                KeShiLieBiaoSearchActivity.actionStart(mContext);
                break;
            case R.id.rl_tab_quanbu:
                select(0);
                break;
            case R.id.rl_tab_jinxingzhong:
                select(1);
                break;
            case R.id.rl_tab_yiguoqi:
                select(2);
                break;
        }
    }

    private void select(int i) {
        view_tab_quanbu.setVisibility(View.GONE);
        view_tab_jinxingzhong.setVisibility(View.GONE);
        view_tab_yiguoqi.setVisibility(View.GONE);
        switch (i) {
            case 0:
                view_tab_quanbu.setVisibility(View.VISIBLE);
                break;
            case 1:
                view_tab_jinxingzhong.setVisibility(View.VISIBLE);
                break;
            case 2:
                view_tab_yiguoqi.setVisibility(View.VISIBLE);
                break;
        }
    }
}
