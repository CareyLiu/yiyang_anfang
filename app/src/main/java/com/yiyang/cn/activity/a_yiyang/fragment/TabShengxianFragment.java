package com.yiyang.cn.activity.a_yiyang.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMapOptions;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.util.Y;
import com.yiyang.cn.activity.a_yiyang.adapter.AnfangAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.activity.a_yiyang.model.AnfangModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabShengxianFragment extends BaseFragment {
    @BindView(R.id.tv_select_name)
    TextView tv_select_name;
    @BindView(R.id.tv_title_baojing)
    TextView tv_title_baojing;
    @BindView(R.id.tv_title_rizhi)
    TextView tv_title_rizhi;
    @BindView(R.id.rv_baojing)
    RecyclerView rv_baojing;
    @BindView(R.id.ll_group_baojing)
    LinearLayout ll_group_baojing;
    @BindView(R.id.ll_group_rizhi)
    LinearLayout ll_group_rizhi;
    @BindView(R.id.iv_select_name)
    ImageView iv_select_name;
    @BindView(R.id.iv_saoyisao)
    ImageView iv_saoyisao;
    @BindView(R.id.line_title_baojing)
    View line_title_baojing;
    @BindView(R.id.rl_title_baojing)
    RelativeLayout rl_title_baojing;
    @BindView(R.id.line_title_rizhi)
    View line_title_rizhi;
    @BindView(R.id.rl_title_rizhi)
    RelativeLayout rl_title_rizhi;
    @BindView(R.id.map)
    MapView mMapView;

    private List<AnfangModel> anfangModels;
    private AnfangAdapter anfangAdapter;
    private AMap aMap;
    private Bundle savedInstanceState;

    public TabShengxianFragment(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .statusBarColor(R.color.white)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.yiyang_frag_tab_shengxian;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        selectBaojing();
        initMap();
        initAdapter();
    }

    private void initMap() {
        aMap = mMapView.getMap();
        //UiSettings ????????????????????????????????????????????????????????????logo????????????????????????.....
        UiSettings settings = aMap.getUiSettings();

        //????????????????????????,???????????????LocationSource??????
        // aMap.setLocationSource(this);

        // ????????????????????????????????????????????????????????????????????????????????????
        settings.setMyLocationButtonEnabled(false);

        //???????????????
        //settings.setCompassEnabled(true);

        //aMap.getCameraPosition(); ???????????????????????????????????????


        //??????????????????
        settings.setZoomControlsEnabled(true);
        //??????logo????????????????????????????????????????????????logo???settings.setLogoLeftMargin(9000)???
        settings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
        //????????????????????????????????????
        settings.setScaleControlsEnabled(true);
        //?????????????????????
        //float scale = aMap.getScalePerPixel();

        //aMap.setMyLocationEnabled(true);//???????????????????????????????????????,?????????flase
        mMapView.onCreate(savedInstanceState);
    }

    private void initAdapter() {
        anfangModels = new ArrayList<>();
        anfangAdapter = new AnfangAdapter(R.layout.yiyang_item_anfang, anfangModels);
        rv_baojing.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_baojing.setAdapter(anfangAdapter);

        anfangModels.add(new AnfangModel(R.mipmap.jujiaanfang, "????????????", "???????????????2021-09-08", 1));
        anfangModels.add(new AnfangModel(R.mipmap.shengmingtizhengdian, "???????????????", "???????????????2021-09-08", 1));
        anfangModels.add(new AnfangModel(R.mipmap.zhinengshouhuan, "????????????", "???????????????2021-09-08", 2));
        anfangModels.add(new AnfangModel(R.mipmap.meiqibaojingqi, "???????????????", "???????????????2021-09-08", 1));
        anfangModels.add(new AnfangModel(R.mipmap.yanwubaojingqi, "???????????????", "???????????????2021-09-08", 1));
        anfangAdapter.setNewData(anfangModels);
        anfangAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.tv_select_name, R.id.iv_select_name, R.id.iv_saoyisao, R.id.rl_title_baojing, R.id.rl_title_rizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_name:
                break;
            case R.id.iv_select_name:
                break;
            case R.id.iv_saoyisao:
                break;
            case R.id.rl_title_baojing:
                selectBaojing();
                break;
            case R.id.rl_title_rizhi:
                selectRizhi();
                break;
        }
    }

    private void selectBaojing() {
        tv_title_baojing.setTextColor(Y.getColor(R.color.color_1));
        line_title_baojing.setVisibility(View.VISIBLE);

        tv_title_rizhi.setTextColor(Y.getColor(R.color.color_c));
        line_title_rizhi.setVisibility(View.INVISIBLE);

        ll_group_baojing.setVisibility(View.VISIBLE);
        ll_group_rizhi.setVisibility(View.GONE);
    }


    private void selectRizhi() {
        tv_title_rizhi.setTextColor(Y.getColor(R.color.color_1));
        line_title_rizhi.setVisibility(View.VISIBLE);

        tv_title_baojing.setTextColor(Y.getColor(R.color.color_c));
        line_title_baojing.setVisibility(View.INVISIBLE);


        ll_group_rizhi.setVisibility(View.VISIBLE);
        ll_group_baojing.setVisibility(View.GONE);
    }
}
