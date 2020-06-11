package com.smarthome.magic.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.gouwuche.GouWuCheQueRenDingDanActivity;
import com.smarthome.magic.activity.taokeshagncheng.QueRenDingDanActivity;
import com.smarthome.magic.adapter.TaoCanAdapter;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.GoodsDetails_f;
import com.smarthome.magic.model.GouWuCheZhengHeModel;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.phoneview.sample.ImageShowActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseTaoCanActivity extends Activity {

    GoodsDetails_f.DataBean dataBean;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_choose_yansefenlei)
    TextView tvChooseYansefenlei;
    @BindView(R.id.view_line1)
    View viewLine1;
    @BindView(R.id.tv_goumai_number)
    TextView tvGoumaiNumber;
    @BindView(R.id.tv_jia)
    TextView tvJia;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.rtv_enter)
    RoundTextView rtvEnter;
    List<GoodsDetails_f.DataBean.ProductListBean> productListBeans;
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.constrain)
    ConstraintLayout constrain;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.tv_yanse)
    TextView tvYanse;
    String canshu;//1 规格 2 参数
    @BindView(R.id.constrain1)
    ConstraintLayout constrain1;
    @BindView(R.id.tv_canshu)
    TextView tvCanshu;
    @BindView(R.id.rl_guige)
    RelativeLayout rlGuige;
    @BindView(R.id.rtv_text)
    RoundTextView rtvText;
    @BindView(R.id.tv_jian)
    TextView tvJian;
    private String position;

    private String moneyBegin;
    private String moneyEnd;
    private String count = "1";
    private String title;
    private String shopName;
    private String imageUrl;
    private String wares_go_type;
    private String leixing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_tao_can);
        ButterKnife.bind(this);
        moneyBegin = getIntent().getStringExtra("moneyBegin");
        moneyEnd = getIntent().getStringExtra("moneyEnd");
        title = getIntent().getStringExtra("title");
        shopName = getIntent().getStringExtra("shopName");
        imageUrl = getIntent().getStringExtra("imageUrl");
        wares_go_type = getIntent().getStringExtra("wares_go_type");
        //ConstraintLayout constraintLayout = findViewById(R.id.constrain);
        // constraintLayout.setBackground();
        constrain.getBackground().setAlpha(200);// 0~255透明度值
        leixing = getIntent().getStringExtra("leixing");

        constrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> imageList = new ArrayList<>();
                for (int i = 0; i < productListBeans.size(); i++) {
                    if (productListBeans.get(i).getSelect().equals("1")) {
                        imageList.add(productListBeans.get(i).getIndex_photo_url());
                    }
                }

                ImageShowActivity.actionStart(ChooseTaoCanActivity.this, imageList);

            }
        });
        constrain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlGuige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rtvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.dataBean = (GoodsDetails_f.DataBean) getIntent().getSerializableExtra("dataBean");
        rtvEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leixing.equals("0")) {
                    jiaRuGouWuChe();
                } else {
                    //  UIHelper.ToastMessage(ChooseTaoCanActivity.this, "版本调试中，敬请期待");
                    //QueRenDingDanActivity.actionStart(ChooseTaoCanActivity.this, dataBean, position, count, title, shopName, imageUrl, wares_go_type);
                    //zhengHeList();
                    GouWuCheQueRenDingDanActivity.actionStart(ChooseTaoCanActivity.this, zhengHeList());
                }

            }


        });
        canshu = getIntent().getStringExtra("canshu");
        if (canshu.equals("1")) {//展示第一个

            constrain1.setVisibility(View.VISIBLE);
            rlGuige.setVisibility(View.GONE);

        } else if (canshu.equals("2")) {//展示第二个

            constrain1.setVisibility(View.GONE);
            rlGuige.setVisibility(View.VISIBLE);
            tvCanshu.setText(dataBean.getShop_product_norms());
        }
        productListBeans = new ArrayList<>();
        productListBeans.addAll(dataBean.getProductList());
        //    productListBeans.get(0).setFlag(true);
        position = "0";
        productListBeans.get(Integer.parseInt(position)).setSelect("1");
        Glide.with(ChooseTaoCanActivity.this).load(productListBeans.get(0).getIndex_photo_url()).into(ivImage);
        tvPrice.setText("¥" + productListBeans.get(0).getMoney_now());
        tvKucun.setText("库存（" + productListBeans.get(0).getShop_product_count() + ")");
        tvYanse.setText(productListBeans.get(0).getProduct_title());

        tvNumber.setText("1");
        setclick();
        initAdapter();
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    /**
     * @param context
     * @param dataBean
     * @param canshu
     * @param moneyBegin
     * @param moneyEnd
     * @param title
     * @param shopName
     * @param imageUrl
     * @param wares_go_type
     * @param leixing       0 加入购物车 1立即购买
     */
    public static void actionStart(Context context, GoodsDetails_f.DataBean dataBean, String canshu, String moneyBegin, String moneyEnd, String title, String shopName, String imageUrl, String wares_go_type, String leixing) {
        Intent intent = new Intent(context, ChooseTaoCanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("dataBean", dataBean);
        intent.putExtra("canshu", canshu);
        intent.putExtra("moneyBegin", moneyBegin);
        intent.putExtra("moneyEnd", moneyEnd);
        intent.putExtra("title", title);
        intent.putExtra("shopName", shopName);
        intent.putExtra("imageUrl", imageUrl);
        intent.putExtra("wares_go_type", wares_go_type);
        intent.putExtra("leixing", leixing);
        context.startActivity(intent);
    }

    private void setclick() {
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(tvNumber.getText().toString()) + 1;
                tvNumber.setText(count + "");
                ChooseTaoCanActivity.this.count = String.valueOf(count);
            }
        });

        tvJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tvNumber.getText().toString()) == 1) {
                    UIHelper.ToastMessage(ChooseTaoCanActivity.this, "请至少选中一件", Toast.LENGTH_SHORT);
                } else {
                    int count = Integer.parseInt(tvNumber.getText().toString()) - 1;
                    tvNumber.setText(count + "");
                    ChooseTaoCanActivity.this.count = String.valueOf(count);
                }

            }
        });
    }

    TaoCanAdapter taoCanAdapter;

    public void initAdapter() {
        taoCanAdapter = new TaoCanAdapter(R.layout.item_taocan, productListBeans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChooseTaoCanActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(taoCanAdapter);
        taoCanAdapter.notifyDataSetChanged();
        taoCanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        ChooseTaoCanActivity.this.position = String.valueOf(position);
                        ChooseTaoCanActivity.this.position = String.valueOf(position);
                        for (int i = 0; i < productListBeans.size(); i++) {
                            if (position == i) {

                                productListBeans.get(i).setSelect("1");
                            } else {
                                productListBeans.get(i).setSelect("0");
                            }
                        }


                        Glide.with(ChooseTaoCanActivity.this).load(productListBeans.get(position).getIndex_photo_url()).into(ivImage);
                        tvPrice.setText(productListBeans.get(position).getMoney_now());
                        tvKucun.setText("库存（" + productListBeans.get(position).getShop_product_count() + ")");
                        tvYanse.setText(productListBeans.get(position).getProduct_title());

                        //展示图片 和地址
                        taoCanAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }


    private String taoCanId;//套餐id
    private String payCount;//商品个数


    private void jiaRuGouWuChe() {
        /**
         * {
         * "code":"04151",
         *  "key":"20180305124455yu",
         *  "token":"1547278M08673900O000D000P000c0",
         *  "wares_id":"9",
         *  "shop_product_id":"1",
         *  "pay_count":"1"
         * }
         */

        for (int i = 0; i < productListBeans.size(); i++) {
            if (productListBeans.get(i).getSelect().equals("1")) {
                taoCanId = productListBeans.get(i).getShop_product_id();
                break;
            }

        }
        Map<String, String> map = new HashMap<>();
        map.put("code", "04151");
        map.put("key", Constant.KEY);
        map.put("token", UserManager.getManager(getApplication()).getAppToken());
        map.put("wares_id", dataBean.getWares_id());
        if (!StringUtils.isEmpty(taoCanId)) {
            map.put("shop_product_id", taoCanId);
        }
        map.put("pay_count", tvNumber.getText().toString());

        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(Urls.HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Object>> response) {
                        UIHelper.ToastMessage(ChooseTaoCanActivity.this, "购物车添加成功");
                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        AlertUtil.t(getApplication(), response.getException().getMessage());

                    }
                });

    }

    private List<GouWuCheZhengHeModel> zhengHeList() {

        List<GouWuCheZhengHeModel> list = new ArrayList<>();

        GouWuCheZhengHeModel gouWuCheZhengHeModel = new GouWuCheZhengHeModel(true, dataBean.getInst_name());
        /**
         *     private String shopcart_id;
         *     private String inst_id;
         *     private String inst_name;
         *     private String inst_logo_url;
         *     private String subsystem_id;
         */

        gouWuCheZhengHeModel.setInst_id(dataBean.getInst_id());
        gouWuCheZhengHeModel.setInst_name(dataBean.getInst_name());
        gouWuCheZhengHeModel.setInst_logo_url(dataBean.getInst_logo_url());

        /**
         *     private String shop_product_title;
         *     private String shop_product_id;
         *     private String wares_id;
         *     private String form_product_id;
         *     private String disabled_cause;
         *     private String wares_money_go;
         *     private String product_title;
         *     private String index_photo_url;
         *     private String form_product_money;
         *     private String pay_count;
         *     private String form_product_state;
         *     private String wares_go_type;
         *     private String bottomYuanJiao = "0";//0不是圆角 1 是圆角
         *     private String isSelect = "0";//默认没有选中
         *     private String liuyan = "";
         */

        list.add(gouWuCheZhengHeModel);


        GouWuCheZhengHeModel gouWuCheZhengHeModel1 = new GouWuCheZhengHeModel(false, "");
        /**
         *     private String shopcart_id;
         *     private String inst_id;
         *     private String inst_name;
         *     private String inst_logo_url;
         *     private String subsystem_id;
         */

//        gouWuCheZhengHeModel.setInst_id(dataBean.getInst_id());
//        gouWuCheZhengHeModel.setInst_name(dataBean.getInst_name());
//        gouWuCheZhengHeModel.setInst_logo_url(dataBean.getInst_logo_url());

        /**
         *     private String shop_product_title;
         *     private String shop_product_id;
         *     private String wares_id;
         *     private String form_product_id;
         *     private String disabled_cause;
         *     private String wares_money_go;
         *     private String product_title;
         *     private String index_photo_url;
         *     private String form_product_money;
         *     private String pay_count;
         *     private String form_product_state;
         *     private String wares_go_type;
         *     private String bottomYuanJiao = "0";//0不是圆角 1 是圆角
         *     private String isSelect = "0";//默认没有选中
         *     private String liuyan = "";
         */
        gouWuCheZhengHeModel1.setIndex_photo_url(dataBean.getIndex_photo_url());
        gouWuCheZhengHeModel1.setShop_product_title(dataBean.getShop_product_title());
        gouWuCheZhengHeModel1.setShop_product_id(dataBean.getProductList().get(Integer.parseInt(position)).getShop_product_id());
        gouWuCheZhengHeModel1.setWares_id(dataBean.getWares_id());
        gouWuCheZhengHeModel1.setWares_money_go(dataBean.getWares_money_go());
        gouWuCheZhengHeModel1.setShop_product_title(dataBean.getShop_product_title());
        gouWuCheZhengHeModel1.setProduct_title(dataBean.getProductList().get(Integer.parseInt(position)).getProduct_title());
        gouWuCheZhengHeModel1.setForm_product_money(dataBean.getProductList().get(Integer.parseInt(position)).getMoney_now());
        gouWuCheZhengHeModel1.setPay_count(count);
        gouWuCheZhengHeModel1.setWares_go_type(wares_go_type);
        gouWuCheZhengHeModel1.setBottomYuanJiao("1");
        gouWuCheZhengHeModel1.setIsSelect("1");
        list.add(gouWuCheZhengHeModel1);
        return list;
    }
}
