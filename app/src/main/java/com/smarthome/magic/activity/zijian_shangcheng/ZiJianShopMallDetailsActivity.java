package com.smarthome.magic.activity.zijian_shangcheng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.CashAccountActivity;
import com.smarthome.magic.activity.ChooseTaoCanActivity;
import com.smarthome.magic.activity.WebViewActivity;
import com.smarthome.magic.activity.gouwuche.GouWuCheActivity;
import com.smarthome.magic.adapter.ZiJianPingLunAdapter;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.GlideImageLoader;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.GoodsDetails_f;
import com.smarthome.magic.project_A.zijian_interface.ZijianDetailsInterface;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.phoneview.sample.ImageShowActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.smarthome.magic.util.DensityUtils.getScreenWidth;

public class ZiJianShopMallDetailsActivity extends BaseActivity implements ZijianDetailsInterface {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;

    View headerView;//header 头
    View footerView;//footer 尾

    ZiJianPingLunAdapter ziJianPingLunAdapter;

    List<GoodsDetails_f.DataBean.AssListBean> assListBeans = new ArrayList<>();
    @BindView(R.id.iv_dianpu_iamge)
    ImageView ivDianpuIamge;
    @BindView(R.id.tv_dianpu)
    TextView tvDianpu;
    @BindView(R.id.iv_kefu_image)
    ImageView ivKefuImage;
    @BindView(R.id.iv_shoucang_iamge)
    ImageView ivShoucangIamge;
    @BindView(R.id.ll_jiaru_gouwuche)
    LinearLayout llJiaruGouwuche;
    @BindView(R.id.ll_liji_goumai)
    LinearLayout llLijiGoumai;
    private Response<AppResponse<GoodsDetails_f.DataBean>> response;
    GoodsDetails_f.DataBean dataBean;
    private String productId;
    private String warseId;
    private String title;
    private String shopName;//店铺名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_zi_jian_shop_mall_details);
        productId = this.getIntent().getStringExtra("productId");
        warseId = this.getIntent().getStringExtra("wareId");
        ButterKnife.bind(this);
        getNet();

        PreferenceHelper.getInstance(ZiJianShopMallDetailsActivity.this).putString(App.PRODUCTID, warseId);
        //setClick();


    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("详情");
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
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setBackgroundResource(R.mipmap.shop_xiangqing_gouwuche_slip);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(ZiJianShopMallDetailsActivity.this, "添加到购物车");
                UIHelper.ToastMessage(ZiJianShopMallDetailsActivity.this, "功能开发中");
                //GouWuCheActivity.actionStart(ZiJianShopMallDetailsActivity.this);
            }
        });

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zi_jian_shop_mall_details;
    }


    @Override
    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04133");
        map.put("key", Constant.KEY);
        map.put("shop_product_id", productId);
        map.put("wares_id", warseId);
        Gson gson = new Gson();
        OkGo.<AppResponse<GoodsDetails_f.DataBean>>post(Constant.SERVER_URL + "shop_new/app")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GoodsDetails_f.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GoodsDetails_f.DataBean>> response) {
                        ZiJianShopMallDetailsActivity.this.response = response;
                        assListBeans.addAll(response.body().data.get(0).getAssList());

                        if (assListBeans.size() == 0) {
                            //      View view = View.inflate(ZiJianShopMallDetailsActivity.this, R.layout.layout_none_ass, null);
                            //    ziJianPingLunAdapter.setEmptyView(view);
                            GoodsDetails_f.DataBean.AssListBean assListBean = new GoodsDetails_f.DataBean.AssListBean();
                            assListBean.setNonDataShow("1");
                            assListBeans.add(assListBean);

                        }
                        loadList();
                        //ziJianPingLunAdapter.notifyDataSetChanged();
                        setHeader();
                        //ziJianPingLunAdapter.notifyDataSetChanged();
                        setFooter();
                        setClick();
                    }

                    @Override
                    public void onError(Response<AppResponse<GoodsDetails_f.DataBean>> response) {
                        AlertUtil.t(ZiJianShopMallDetailsActivity.this, response.getException().getMessage());
                    }
                });

    }

    @Override
    public void loadList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rlvList.setLayoutManager(linearLayoutManager);
        ziJianPingLunAdapter = new ZiJianPingLunAdapter(assListBeans);
        // ziJianPingLunAdapter.notifyDataSetChanged();
        rlvList.setAdapter(ziJianPingLunAdapter);
    }

    Banner banner;
    RelativeLayout rlTaoCan;
    RelativeLayout rlCanshu;

    @Override
    public void setHeader() {
        headerView = View.inflate(this, R.layout.zijian_shopmall_header, null);
        banner = headerView.findViewById(R.id.banner);
        GoodsDetails_f.DataBean dataBean = response.body().data.get(0);
        TextView tvSeeMore = headerView.findViewById(R.id.tv_see_more);
        ImageView ivSeeMore = headerView.findViewById(R.id.iv_see_more_back);
        TextView tvPrice = headerView.findViewById(R.id.tv_price);
        TextView tvTitle = headerView.findViewById(R.id.tv_title);
        TextView tvKuaidi = headerView.findViewById(R.id.tv_kuaidi);
        TextView tvYueXiao = headerView.findViewById(R.id.tv_yuexiao);
        TextView tvDiZhi = headerView.findViewById(R.id.tv_dizhi);
        rlTaoCan = headerView.findViewById(R.id.rl_taocan);
        rlCanshu = headerView.findViewById(R.id.rl_canshu);
        List<String> items = new ArrayList<>();
        if (response.body().data != null) {
            for (int i = 0; i < response.body().data.get(0).getBannerList().size(); i++) {
                items.add(response.body().data.get(0).getBannerList().get(i).getImg_url());
            }
        }

        tvPrice.setText("¥" + response.body().data.get(0).getMoney_begin() + "-" + response.body().data.get(0).getMoney_end());
        tvTitle.setText(response.body().data.get(0).getShop_product_title());
        String kuaidi = response.body().data.get(0).getWares_go_type();
        //     消费方式：1.邮递/到店
        //     2.邮递 3.到店

        if (kuaidi.equals("1")) {
            tvKuaidi.setText("邮递/到店");
        } else if (kuaidi.equals("2")) {
            tvKuaidi.setText("邮递");
        } else if (kuaidi.equals("3")) {
            tvKuaidi.setText("到店");
        }

        PreferenceHelper.getInstance(ZiJianShopMallDetailsActivity.this).putString(App.KUAIDITYPE, kuaidi);

        PreferenceHelper.getInstance(ZiJianShopMallDetailsActivity.this).putString(App.KUAIDIFEI, dataBean.getWares_money_go());

        tvYueXiao.setText(dataBean.getWares_sales_volume());
        tvDiZhi.setText(dataBean.getAddr());
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        if (banner != null) {
            //设置图片集合
            banner.setImages(items);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    //startActivity(new Intent(ZiJianShopMallDetailsActivity.this, WebViewActivity.class).putExtra("url", response.body().data.get(0).getBannerList().get(position).getImg_url()));
                    ArrayList<String> list = new ArrayList<>();
                    list.add(response.body().data.get(0).getBannerList().get(position).getImg_url());
                    ImageShowActivity.actionStart(ZiJianShopMallDetailsActivity.this, list);

                }
            });
        }


        if (response.body().data.get(0).getAssList().size() == 0) {
            ivSeeMore.setVisibility(View.GONE);
            tvSeeMore.setVisibility(View.GONE);
        }

        ziJianPingLunAdapter.addHeaderView(headerView);
    }


    @Override
    public void setFooter() {
        footerView = View.inflate(this, R.layout.zijian_shopmall_footer, null);
        LinearLayout linearLayout = footerView.findViewById(R.id.ll_footer);
        List<GoodsDetails_f.DataBean.DetailImgListBean> productListBeans = response.body().data.get(0).getDetailImgList();
        for (int i = 0; i < productListBeans.size(); i++) {
            //  ImageView iv = new ImageView(ZiJianShopMallDetailsActivity.this);

            View view = View.inflate(ZiJianShopMallDetailsActivity.this, R.layout.item_big_image, null);
            ImageView iv = view.findViewById(R.id.iv_img);
//
//            int screenWidth = getScreenWidth(this); // 获取屏幕宽度
//            ViewGroup.LayoutParams para;
//            para = iv.getLayoutParams();
//
//            Log.d("zijianshopmalldetail", "layout height0: " + para.height);
//            Log.d("zijianshopmalldetail", "layout width0: " + para.width);
//
//            para.height = Integer.parseInt(productListBeans.get(i).getImg_height());
//            para.width = screenWidth;
//            iv.setLayoutParams(para);

            if (!StringUtils.isEmpty(productListBeans.get(i).getImg_height())) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.override(Integer.valueOf(productListBeans.get(i).getImg_width()), Integer.valueOf(productListBeans.get(i).getImg_height()));
                Glide.with(ZiJianShopMallDetailsActivity.this).load(productListBeans.get(i).getImg_url()).apply(requestOptions).into(iv);
            }
            linearLayout.addView(view);
        }
        ziJianPingLunAdapter.addFooterView(footerView);
        ziJianPingLunAdapter.notifyDataSetChanged();
    }

    @Override
    public void setClick() {
        ivDianpuIamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击店铺
            }
        });
        ivKefuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //客服
            }
        });
        ivShoucangIamge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //收藏

                //收藏接口
            }
        });
        llJiaruGouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加入购物车
                dataBean = response.body().data.get(0);
                ChooseTaoCanActivity.actionStart(ZiJianShopMallDetailsActivity.this, dataBean, "1", dataBean.getMoney_begin(), dataBean.getMoney_end(), response.body().data.get(0).getShop_product_title(), dataBean.getInst_name(), dataBean.getInst_logo_url(), response.body().data.get(0).getWares_go_type(), "0");
            }
        });
        llLijiGoumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //立即购买
                dataBean = response.body().data.get(0);
                ChooseTaoCanActivity.actionStart(ZiJianShopMallDetailsActivity.this, dataBean, "1", dataBean.getMoney_begin(), dataBean.getMoney_end(), response.body().data.get(0).getShop_product_title(), dataBean.getInst_name(), dataBean.getInst_logo_url(), response.body().data.get(0).getWares_go_type(), "1");
            }
        });
        rlTaoCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean = response.body().data.get(0);
                ChooseTaoCanActivity.actionStart(ZiJianShopMallDetailsActivity.this, dataBean, "1", dataBean.getMoney_begin(), dataBean.getMoney_end(), response.body().data.get(0).getShop_product_title(), dataBean.getInst_name(), dataBean.getInst_logo_url(), response.body().data.get(0).getWares_go_type(), "0");

            }
        });
        rlCanshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean = response.body().data.get(0);
                ChooseTaoCanActivity.actionStart(ZiJianShopMallDetailsActivity.this, dataBean, "2", dataBean.getMoney_begin(), dataBean.getMoney_end(), response.body().data.get(0).getShop_product_title(), dataBean.getInst_name(), dataBean.getInst_logo_url(), response.body().data.get(0).getWares_go_type(), "0");
            }
        });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String productId, String wareId) {
        Intent intent = new Intent(context, ZiJianShopMallDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("productId", productId);
        intent.putExtra("wareId", wareId);
        context.startActivity(intent);
    }

}
