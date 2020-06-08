package com.smarthome.magic.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.roundview.RoundRelativeLayout;
import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.HOME_PICTURE_HOME;

public class TuanGouMaiDanDingDanActivity extends AbTuanGouShengChengDingDan {


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
    @BindView(R.id.iv_choose)
    ImageView ivChoose;


    private String money;
    private String inst_id;//店家id
    private String userHongBao = "";//1 不用 2 用
    private String shopType;
    private String diYongQuan;//抵用券
    private String selectHongBaoFlag = "1";//选中 默认选中  1选中 0 未选中
    private String diYongQuanID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tuan_gou_sheng_cheng_ding_dan);
        money = getIntent().getStringExtra("money");

        inst_id = getIntent().getStringExtra("inst_id");
        shopType = getIntent().getStringExtra("typeID");
        rtvJine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectHongBaoFlag.equals("1")) {
                    TuanGouMaiDanZhiFuActivity.actionStart(TuanGouMaiDanDingDanActivity.this, inst_id, money, userHongBao, "");
                } else {
                    TuanGouMaiDanZhiFuActivity.actionStart(TuanGouMaiDanDingDanActivity.this, inst_id, money, userHongBao, diYongQuanID);
                }
            }
        });
        getNet();
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userHongBao.equals("2")) {
                    ivChoose.setVisibility(View.INVISIBLE);
                    userHongBao = "1";//不用
                    tvDanqianDikou.setVisibility(View.GONE);
                    tvDikoujine.setVisibility(View.GONE);
                    selectHongBaoFlag = "0";
                } else {
                    ivChoose.setVisibility(View.VISIBLE);
                    userHongBao = "2";//用

                    tvDanqianDikou.setVisibility(View.VISIBLE);
                    tvDikoujine.setVisibility(View.VISIBLE);
                    selectHongBaoFlag = "1";
                    tvZanwu.setText("暂无可用");
                }

            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectHongBaoFlag.equals("1")) {
                    UIHelper.ToastMessage(TuanGouMaiDanDingDanActivity.this, "使用余额抵扣,无法使用抵用券");
                } else {

                    TuanGouDiYongQuanActivity.actionStart(TuanGouMaiDanDingDanActivity.this, inst_id, money, shopType);
                }
            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DIYONGQUAN) {
                    diYongQuan = (String) message.content;
                    tvZanwu.setText("抵用券减" + diYongQuan + "元");
                }
            }
        }));
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tuan_gou_maidan_sheng_cheng_ding_dan;
    }

    Response<AppResponse<TuanGouShengChengDingDanBean.DataBean>> response;

    @Override
    public void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "08025");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(TuanGouMaiDanDingDanActivity.this).getAppToken());
        map.put("money", money);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<TuanGouShengChengDingDanBean.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouShengChengDingDanBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouShengChengDingDanBean.DataBean>> response) {
                        TuanGouMaiDanDingDanActivity.this.response = response;
                        showPage();

                    }
                });
    }

    @Override
    public void showPage() {
        //if (response.body().data.get(0).getMoney())
        tvDikoujine.setText(response.body().data.get(0).getAvailable_balance());
        tvMoney.setText("¥" + money);
        String strPhone = PreferenceHelper.getInstance(TuanGouMaiDanDingDanActivity.this).getString("user_phone", "");
        tvShoujihaoNumber.setText(strPhone);
        if (response.body().data.get(0).getAvailable_balance() == null) {
            response.body().data.get(0).setAvailable_balance("0.00");
        }
        BigDecimal bigDecimal = new BigDecimal(response.body().data.get(0).getAvailable_balance() + "");

        if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
            selectHongBaoFlag = "0";
            ivChoose.setVisibility(View.INVISIBLE);
            userHongBao = "1";//不用
            tvDanqianDikou.setVisibility(View.GONE);
            tvDikoujine.setVisibility(View.GONE);
            selectHongBaoFlag = "0";
        } else {
            selectHongBaoFlag = "1";
            ivChoose.setVisibility(View.VISIBLE);
            userHongBao = "2";//用

            tvDanqianDikou.setVisibility(View.VISIBLE);
            tvDikoujine.setVisibility(View.VISIBLE);

        }

        if (response.body().data.get(0).getCount().equals("0")) {
            tvZanwu.setText("暂无可用");
        } else {
            tvZanwu.setText(response.body().data.get(0).getCount());
        }

    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String money, String inst_id, String typeID) {
        Intent intent = new Intent(context, TuanGouMaiDanDingDanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("money", money);
        intent.putExtra("inst_id", inst_id);
        intent.putExtra("typeID", typeID);
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
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }
}
