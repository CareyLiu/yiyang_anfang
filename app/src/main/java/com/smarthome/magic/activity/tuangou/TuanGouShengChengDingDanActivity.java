package com.smarthome.magic.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.roundview.RoundRelativeLayout;
import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.TuanGouShengChengDingDanBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.smarthome.magic.get_net.Urls.HOME_PICTURE_HOME;

public class TuanGouShengChengDingDanActivity extends AbTuanGouShengChengDingDan {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_xianshitui)
    TextView tvXianshitui;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.iv_jia)
    ImageView ivJia;
    @BindView(R.id.tv_geshu)
    TextView tvGeshu;
    @BindView(R.id.iv_jian)
    ImageView ivJian;
    @BindView(R.id.tv_xiaoji)
    TextView tvXiaoji;
    @BindView(R.id.rl_1)
    RoundRelativeLayout rl1;
    @BindView(R.id.tv_zanwu)
    TextView tvZanwu;
    @BindView(R.id.rl_2)
    RoundRelativeLayout rl2;
    @BindView(R.id.tv_hongbaodikou_huashu)
    TextView tvHongbaodikouHuashu;
    @BindView(R.id.view_line_1)
    View viewLine1;
    @BindView(R.id.tv_danqian_dikou)
    TextView tvDanqianDikou;
    @BindView(R.id.tv_dikoujine)
    TextView tvDikoujine;
    @BindView(R.id.rl_3)
    RoundRelativeLayout rl3;
    @BindView(R.id.tv_shifujine)
    TextView tvShifujine;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.view_line_2)
    View viewLine2;
    @BindView(R.id.tv_shoujihao)
    TextView tvShoujihao;
    @BindView(R.id.tv_shoujihao_number)
    TextView tvShoujihaoNumber;
    @BindView(R.id.rl_4)
    RoundRelativeLayout rl4;
    @BindView(R.id.rtv_jine)
    RoundTextView rtvJine;
    @BindView(R.id.tv_jine)
    TextView tvJine;
    @BindView(R.id.tv_xiaoji_price)
    TextView tvXiaojiPrice;
    @BindView(R.id.iv_choose)
    ImageView ivChoose;

    private String money;
    private String inst_id;
    private String image;
    private String tvName1;
    private String shopType;
    private String war_id;

    private TuanGouShengChengDingDanBean.DataBean dataBean;

    private String userHongBao = "1";//0什么也不用  1用抵用券  2用红包
    private String diYongQuan;//抵用券
    private String diYongQuanID;
    private String available_balance;//红包金额
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        getNet();
    }

    private void init() {
        inst_id = getIntent().getStringExtra("inst_id");
        money = getIntent().getStringExtra("money");
        war_id = getIntent().getStringExtra("war_id");
        tvName1 = getIntent().getStringExtra("tvName");
        image = getIntent().getStringExtra("image");
        shopType = getIntent().getStringExtra("shopType");

        shuLiangBigDecimal = new BigDecimal("1");
        danjiaBigDecimal = new BigDecimal(money);

        Glide.with(TuanGouShengChengDingDanActivity.this).load(image).into(ivImage);
        tvName.setText(tvName1);


        rtvJine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TuanGouZhiFuActivity.actionStart(TuanGouShengChengDingDanActivity.this, inst_id, tvGeshu.getText().toString().trim(), userHongBao, war_id);
            }
        });

        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userHongBao.equals("2")) {
                    ivChoose.setVisibility(View.INVISIBLE);
                    userHongBao = "1";//不用

                    tvDanqianDikou.setVisibility(View.GONE);
                    tvDikoujine.setVisibility(View.GONE);
                } else {
                    ivChoose.setVisibility(View.VISIBLE);
                    userHongBao = "2";//用
                    tvDanqianDikou.setVisibility(View.VISIBLE);
                    tvDikoujine.setVisibility(View.VISIBLE);
                }
            }
        });

        ivJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuLiangBigDecimal = new BigDecimal(tvGeshu.getText().toString().trim());
                setJiSuanShuLiang(shuLiangBigDecimal, danjiaBigDecimal, "1");
            }
        });
        ivJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuLiangBigDecimal = new BigDecimal(tvGeshu.getText().toString().trim());
                setJiSuanShuLiang(shuLiangBigDecimal, danjiaBigDecimal, "0");
            }
        });
    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_tuan_gou_sheng_cheng_ding_dan;
    }

    Response<AppResponse<TuanGouShengChengDingDanBean.DataBean>> response;


    @Override
    public void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "08015");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(TuanGouShengChengDingDanActivity.this).getAppToken());
        map.put("money", money);
        map.put("shopType", shopType);
        map.put("inst_id", inst_id);
        map.put("wares_id", war_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<TuanGouShengChengDingDanBean.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouShengChengDingDanBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouShengChengDingDanBean.DataBean>> response) {
                        dataBean = response.body().data.get(0);
                        showPage();
                    }
                });
    }

    @Override
    public void showPage() {
        available_balance = dataBean.getAvailable_balance();
        count = Y.getInt(dataBean.getCount());
        if (count > 0) {
            tvZanwu.setText(count + "张可用");
        } else {
            tvZanwu.setText("暂无可用红包");
        }

        String strPhone = PreferenceHelper.getInstance(TuanGouShengChengDingDanActivity.this).getString("user_phone", "");
        tvShoujihaoNumber.setText(strPhone);

        if (TextUtils.isEmpty(available_balance)) {
            available_balance = "0.00";
        }
        tvDikoujine.setText(available_balance);

        BigDecimal hongBao = new BigDecimal(available_balance);
        if (hongBao.compareTo(BigDecimal.ZERO) == 0) {
            userHongBao = "0";//什么也不用
            ivChoose.setVisibility(View.INVISIBLE);
            tvDanqianDikou.setVisibility(View.GONE);
            tvDikoujine.setVisibility(View.GONE);
        } else {
            userHongBao = "2";//用红包
            ivChoose.setVisibility(View.VISIBLE);
            tvDanqianDikou.setVisibility(View.VISIBLE);
            tvDikoujine.setVisibility(View.VISIBLE);
//            moneyShiji = moneyZong.subtract(available_balanceB);
//            setMoney();
        }

        tvXiaojiPrice.setText("¥ " + money);
        tvMoney.setText("¥" + money);
        tvJine.setText("¥" + money);
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String inst_id, String money, String image, String tvName, String shopType, String warse_id) {
        Intent intent = new Intent(context, TuanGouShengChengDingDanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("inst_id", inst_id);
        intent.putExtra("money", money);
        intent.putExtra("image", image);
        intent.putExtra("tvName", tvName);
        intent.putExtra("shopType", shopType);
        intent.putExtra("war_id", warse_id);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("生成订单");
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

    /**
     * @param shuLiang 数量
     * @param danJia   单价
     * @param type     0 减 1加
     */
    private BigDecimal shuLiangBigDecimal;
    private BigDecimal danjiaBigDecimal;

    private void setJiSuanShuLiang(BigDecimal shuLiangBigDecimal, BigDecimal danjiaBigDecimal, String type) {
        BigDecimal finalDecimal = new BigDecimal("0");
        if (type.equals("0")) {
            if (shuLiangBigDecimal.compareTo(new BigDecimal("1")) == 0) {
                Y.t("最小购买数量为1");
                return;
            } else {
                shuLiangBigDecimal = shuLiangBigDecimal.subtract(new BigDecimal("1"));
                finalDecimal = danjiaBigDecimal.multiply(shuLiangBigDecimal);
                tvGeshu.setText(shuLiangBigDecimal.toString().trim());
            }
        } else if (type.equals("1")) {
            shuLiangBigDecimal = shuLiangBigDecimal.add(new BigDecimal("1"));
            finalDecimal = shuLiangBigDecimal.multiply(danjiaBigDecimal);
            tvGeshu.setText(shuLiangBigDecimal.toString());
        }

        tvXiaojiPrice.setText(finalDecimal.toString() + "");
        tvMoney.setText("¥" + finalDecimal.toString());
        rtvJine.setText("¥" + finalDecimal.toString() + "生成订单");
    }
}
