package com.smarthome.magic.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.ShangJiaNearbyAdapter;
import com.smarthome.magic.adapter.tuangou.TuanGouShangJiaDetailsAdapter;
import com.smarthome.magic.adapter.tuangou.TuanGouShangJiaHighScoreListAdapter;
import com.smarthome.magic.adapter.tuangou.TuanGouShangJiaListAdapter;
import com.smarthome.magic.app.App;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.model.TuanGouShangJiaDetailsModel;
import com.smarthome.magic.util.AlertUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.smarthome.magic.get_net.Urls.HOME_PICTURE;
import static com.smarthome.magic.get_net.Urls.LIBAOLIST;

public class TuanGouShangJiaDetailsActivity extends AbStracTuanGouShangJiaDetails {

    private String inst_id;
    String shangJiaId;
    Response<AppResponse<TuanGouShangJiaDetailsModel.DataBean>> response;
    TuanGouShangJiaDetailsAdapter tuanGouShangJiaDetailsAdapter;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;

    List<TuanGouShangJiaDetailsModel.DataBean.FavourableListBean> favourableListBeans = new ArrayList<>();
    List<TuanGouShangJiaDetailsModel.DataBean.HighScoreListBean> highScoreListBeans = new ArrayList<>();

    private String typeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tuan_gou_shang_jia_details);
        shangJiaId = getIntent().getStringExtra("shangJiaID");
        typeID = getIntent().getStringExtra("typeID");
        getNet();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tuan_gou_shang_jia_details;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    void getTurn() {
        super.getTurn();
    }


    @Override
    public void getNet() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "08012");
        map.put("key", Constant.KEY);
        //map.put("token", UserManager.getManager(TuanGouShangJiaDetailsActivity.this).getAppToken());
        map.put("inst_id", shangJiaId);
        // map.put("x", PreferenceHelper.getInstance(TuanGouShangJiaDetailsActivity.this).getString(App.WEIDU, ""));
        //  map.put("y", PreferenceHelper.getInstance(TuanGouShangJiaDetailsActivity.this).getString(App.JINGDU, ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<TuanGouShangJiaDetailsModel.DataBean>>post(LIBAOLIST)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouShangJiaDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouShangJiaDetailsModel.DataBean>> response) {
                        TuanGouShangJiaDetailsActivity.this.response = response;
                        favourableListBeans = response.body().data.get(0).getFavourableList();
                        highScoreListBeans = response.body().data.get(0).getHighScoreList();
                        neighborListBeans = response.body().data.get(0).getNeighborList();
                        inst_id = response.body().data.get(0).getStoreList().getInst_id();
                        getTurn();
                        setFotter();
                        tuanGouShangJiaDetailsAdapter.setNewData(favourableListBeans);
                        tuanGouShangJiaDetailsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<TuanGouShangJiaDetailsModel.DataBean>> response) {
                        AlertUtil.t(TuanGouShangJiaDetailsActivity.this, response.getException().getMessage());
                    }
                });
    }

    TuanGouShangJiaDetailsModel.DataBean.StoreListBean storeListBean;

    @Override
    public void setHeader() {
        View view = View.inflate(TuanGouShangJiaDetailsActivity.this, R.layout.layout_tuangou_shangjia_details_header, null);

        ImageView banner = view.findViewById(R.id.banner);
        TextView tvShopName = view.findViewById(R.id.tv_shop_name);
        AppCompatRatingBar ratingBar = view.findViewById(R.id.star);
        TextView tvRenJun = view.findViewById(R.id.tv_renjun);
        TextView tvAddr = view.findViewById(R.id.tv_addr);
        RoundTextView roundTextView = view.findViewById(R.id.rtv_maidan);
        roundTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.isEmpty(inst_id)) {
                    TuanGouMaiDanActivity.actionStart(TuanGouShangJiaDetailsActivity.this, inst_id,typeID);
                }
            }
        });


        storeListBean = response.body().data.get(0).getStoreList();
        Glide.with(TuanGouShangJiaDetailsActivity.this).load(storeListBean.getInst_photo_url()).into(banner);
        tvShopName.setText(storeListBean.getInst_name());
        ratingBar.setRating(Float.parseFloat(storeListBean.getInst_number()));

        tvRenJun.setText(storeListBean.getAverage_cost());
        tvAddr.setText(storeListBean.getAddr_all());

        tuanGouShangJiaDetailsAdapter.addHeaderView(view);
    }

    @Override
    public void setList() {
        tuanGouShangJiaDetailsAdapter = new TuanGouShangJiaDetailsAdapter(R.layout.item_tuangou_taocan, favourableListBeans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        tuanGouShangJiaDetailsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rtv_qianggou:

                        TuanGouShangPinDetailsActivity.actionStart(TuanGouShangJiaDetailsActivity.this, tuanGouShangJiaDetailsAdapter.getData().get(position).getInst_id(), tuanGouShangJiaDetailsAdapter.getData().get(position).getWares_id(), storeListBean.getInst_name(), storeListBean.getInst_photo_url(), storeListBean.getInst_number(), storeListBean.getAddr_all());
                        break;
                }
            }
        });
        rlvList.setAdapter(tuanGouShangJiaDetailsAdapter);

    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String shangjiaId, String typeID) {
        Intent intent = new Intent(context, TuanGouShangJiaDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("shangJiaID", shangjiaId);
        intent.putExtra("typeID", typeID);
        context.startActivity(intent);

    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("商家详情");
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

    List<TuanGouShangJiaDetailsModel.DataBean.NeighborListBean> neighborListBeans = new ArrayList<>();

    public void setFotter() {
        View view = View.inflate(TuanGouShangJiaDetailsActivity.this, R.layout.layout_shangjia_details_footer, null);

        RecyclerView rcvList1 = view.findViewById(R.id.rcv_list1);
        RecyclerView rcvList2 = view.findViewById(R.id.rcv_list2);


        rcvList1.setLayoutManager(new LinearLayoutManager(TuanGouShangJiaDetailsActivity.this));
        final ShangJiaNearbyAdapter shangJiaNearbyAdapter = new ShangJiaNearbyAdapter(R.layout.item_tuangou_nearby, neighborListBeans);
        rcvList1.setAdapter(shangJiaNearbyAdapter);


        //初始化一下
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        rcvList2.setLayoutManager(gridLayoutManager);

        final TuanGouShangJiaHighScoreListAdapter tuanGouShangJiaListAdapter = new TuanGouShangJiaHighScoreListAdapter(R.layout.item_shangjia, highScoreListBeans);
        //  taoKeListAdapter = new TaoKeListAdapter(R.layout.layout_taokeshop, dataBeanList);
        tuanGouShangJiaListAdapter.openLoadAnimation();//默认为渐显效果
        rcvList2.setAdapter(tuanGouShangJiaListAdapter);

        shangJiaNearbyAdapter.setNewData(neighborListBeans);
        shangJiaNearbyAdapter.notifyDataSetChanged();

        tuanGouShangJiaListAdapter.setNewData(highScoreListBeans);
        tuanGouShangJiaListAdapter.notifyDataSetChanged();

        tuanGouShangJiaListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        TuanGouShangJiaDetailsActivity.actionStart(TuanGouShangJiaDetailsActivity.this, tuanGouShangJiaListAdapter.getData().get(position).getInst_id(),typeID);
                        break;
                }
            }
        });
        shangJiaNearbyAdapter.setOnItemChildClickListener(new com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    /**
                     *        tvShopName.setText(storeListBean.getInst_name());
                     *         ratingBar.setRating(Float.parseFloat(storeListBean.getInst_number()));
                     *         tvRenJun.setText("人均：" + storeListBean.getAverage_cost());
                     *         tvAddr.setText(storeListBean.getAddr_all());
                     */
                    case R.id.constrain:
                        TuanGouShangPinDetailsActivity.actionStart(TuanGouShangJiaDetailsActivity.this, shangJiaNearbyAdapter.getData().get(position).getInst_id(), shangJiaNearbyAdapter.getData().get(position).getWares_id(), storeListBean.getInst_name(), storeListBean.getInst_photo_url(), storeListBean.getInst_number(), storeListBean.getAddr_all());
                        break;
                }
            }
        });
        tuanGouShangJiaDetailsAdapter.addFooterView(view);


    }
}
