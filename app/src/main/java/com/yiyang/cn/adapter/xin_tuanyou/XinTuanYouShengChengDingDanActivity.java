package com.yiyang.cn.adapter.xin_tuanyou;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alipay.sdk.app.PayTask;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.tuangou.TuanGouDiYongQuanActivity;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.XinTuanYouShengChengDingDanBean;
import com.yiyang.cn.model.YuZhiFuModel;
import com.yiyang.cn.model.YuZhiFuModel_AliPay;
import com.yiyang.cn.pay_about.alipay.PayResult;
import com.yiyang.cn.util.PaySuccessUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.get_net.Urls.HOME_PICTURE_HOME;

public class XinTuanYouShengChengDingDanActivity extends BaseActivity {

    @BindView(R.id.tv_hongbaodikou_huashu)
    TextView tvHongbaodikouHuashu;
    @BindView(R.id.iv_choose)
    ImageView ivChoose;
    @BindView(R.id.view_line_1)
    View viewLine1;
    @BindView(R.id.tv_danqian_dikou)
    TextView tvDanqianDikou;
    @BindView(R.id.tv_dikoujine)
    TextView tvDikoujine;
    @BindView(R.id.rl_3)
    RoundRelativeLayout rl3;
    @BindView(R.id.tv_zanwu)
    TextView tvZanwu;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.rl_main_2)
    RelativeLayout rlMain2;
    @BindView(R.id.view_line2)
    View viewLine2;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_sheng)
    TextView tvSheng;
    @BindView(R.id.cl_main_3)
    ConstraintLayout clMain3;
    @BindView(R.id.rl_2)
    RoundRelativeLayout rl2;
    @BindView(R.id.view_line5)
    View viewLine5;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.iv_icon_1)
    ImageView ivIcon1;
    @BindView(R.id.iv_weixin_choose)
    ImageView ivWeixinChoose;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_icon_2)
    ImageView ivIcon2;
    @BindView(R.id.iv_zhifubao_choose)
    ImageView ivZhifubaoChoose;
    @BindView(R.id.view_weixin)
    View viewWeixin;
    @BindView(R.id.view_zhifubao)
    View viewZhifubao;
    @BindView(R.id.tv_heji)
    TextView tvHeji;
    @BindView(R.id.tv_queren_pay)
    TextView tvQuerenPay;
    @BindView(R.id.iv_mingxi)
    ImageView ivMingxi;
    @BindView(R.id.tv_mingxi)
    TextView tvMingxi;

    @BindView(R.id.tv_youhao_qianghao)
    TextView tvYouhaoQianghao;
    @BindView(R.id.tv_zhijiang)
    TextView tvZhijiang;

    @BindView(R.id.cl_main_4)
    ConstraintLayout clMain4;
    @BindView(R.id.tv_weixin)
    TextView tvWeixin;
    @BindView(R.id.tv_zhifubao)
    TextView tvZhifubao;

    private String userHongBao = "2";//1 ?????? 2 ???
    private IWXAPI api;
    YuZhiFuModel.DataBean dataBean;
    private String appId;//??????id ????????????

    private String diYongQuan;
    String pay_id = "";//????????????-- 1 ????????? 2 ??????
    String payType = "4";//1 ????????? 4 ??????
    private String inst_id;

    String shenMYou;
    String shenMYouHao;
    String shenMQiangHao;
    String shenMJIne;
    String shengShu;
    private String selectHongBaoFlag = "1";//?????? ????????????  1?????? 0 ?????????
    Response<AppResponse<XinTuanYouShengChengDingDanBean.DataBean>> response;
    BigDecimal diYongQuanDecimeal;
    private ProgressDialog progressDialog;
    String ali_pay;//?????????????????????
    String wx_pay;//??????????????????
    String is_buy;//????????????????????????

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        shenMYou = getIntent().getStringExtra("shenmYou");
        shenMYouHao = getIntent().getStringExtra("shenmYouHao");
        shenMQiangHao = getIntent().getStringExtra("shenmQiangHao");
        shenMJIne = getIntent().getStringExtra("shenmJinE");
        shengShu = getIntent().getStringExtra("shengShu");
        inst_id = getIntent().getStringExtra("inst_id");

        tvYouhaoQianghao.setText(shenMYouHao + " " + shenMQiangHao + "??????");
        progressDialog = new ProgressDialog(mContext);//?????????????????????????????????dialog??????;

        // tvMoney.setText("??" + shenMJIne);


        viewWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  wx_pay	1.??????????????????  2.?????????????????????

                if (wx_pay.equals("2")) {
                    UIHelper.ToastMessage(mContext, "?????????????????????????????????");
                    return;
                }
                pay_id = "2";//????????????-- 1 ????????? 2 ??????
                payType = "4";//1 ????????? 4 ??????

                //  ivIcon1.setBackgroundResource(R.mipmap.dingdan_icon_duihao);
                ivZhifubaoChoose.setVisibility(View.INVISIBLE);
                ivWeixinChoose.setVisibility(View.VISIBLE);
                // getWeiXinOrZhiFuBao();
            }
        });

        viewZhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ali_pay.equals("2")) {
                    UIHelper.ToastMessage(mContext, "????????????????????????????????????");
                    return;
                }
                pay_id = "1";//????????????-- 1 ????????? 2 ??????
                payType = "1";//1 ????????? 4 ??????

                ivZhifubaoChoose.setVisibility(View.VISIBLE);
                ivWeixinChoose.setVisibility(View.INVISIBLE);
                //ivWeixinChoose.setBackgroundResource(R.mipmap.dingdan_icon_duihao);
                // getWeiXinOrZhiFuBao();
            }
        });

        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (is_buy.equals("2")) {
                    UIHelper.ToastMessage(mContext, "????????????????????????????????????????????????");
                    return;
                }
                if (userHongBao.equals("2")) {
                    ivChoose.setVisibility(View.INVISIBLE);
                    userHongBao = "1";//??????
                    tvDanqianDikou.setVisibility(View.GONE);
                    tvDikoujine.setVisibility(View.GONE);
                    selectHongBaoFlag = "0";

                    if (heJiJinEDecimeal.compareTo(BigDecimal.ZERO) == -1) {
                        tvHeji.setText("???????????" + "0.01");
                    } else {

                        tvHeji.setText("???????????" + heJiJinEDecimeal.toString());
                    }


                } else {
                    ivChoose.setVisibility(View.VISIBLE);
                    userHongBao = "2";//???

                    tvDanqianDikou.setVisibility(View.VISIBLE);
                    tvDikoujine.setVisibility(View.VISIBLE);
                    selectHongBaoFlag = "1";

                    // tvHeji.setText("???????????" + finalDecimal.toString());


                    if (heJiJinEDecimeal.compareTo(BigDecimal.ZERO) == -1) {
                        tvHeji.setText("???????????" + "0.01");
                    } else {

                        tvHeji.setText("???????????" + heJiJinEDecimeal.toString());
                    }


                    tvZanwu.setText("0");

                    if (response.body().data.get(0).getCount().equals("0")) {
                        tvZanwu.setText("????????????");
                    } else {
                        tvZanwu.setText(response.body().data.get(0).getCount() + "??????");
                    }
                }

            }
        });


        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_buy.equals("2")) {
                    UIHelper.ToastMessage(mContext, "???????????????????????????????????????????????????");
                    return;
                }
                if (selectHongBaoFlag.equals("1")) {
                    UIHelper.ToastMessage(XinTuanYouShengChengDingDanActivity.this, "??????????????????,?????????????????????");
                } else {


                    if (tvZanwu.getText().toString().equals("????????????")) {

                    } else {
                        TuanGouDiYongQuanActivity.actionStart(XinTuanYouShengChengDingDanActivity.this, inst_id, shenMJIne, "");

                    }

                }
            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DIYONGQUAN) {
                    diYongQuan = (String) message.content;
                    String[] strings = diYongQuan.split(",");
                    diYongQuan = strings[0];
                    diYongQuanId = strings[1];
                    tvZanwu.setText("????????????" + diYongQuan + "???");
                    diYongQuanDecimeal = new BigDecimal(diYongQuan);
                    BigDecimal showJinEDecimal = heJiJinEDecimeal.subtract(diYongQuanDecimeal);

                    if (showJinEDecimal.compareTo(BigDecimal.ZERO) == -1) {
                        tvHeji.setText("??????" + "??" + "0.01");
                    } else {
                        tvHeji.setText("???????????" + showJinEDecimal.toString());
                    }


                }
            }
        }));
        getNet();


        //getWeiXinOrZhiFuBao();

//        tvQuerenPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });


        tvQuerenPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * is_buy	1.????????????  2.???????????????
                 */
                if (is_buy.equals("1")) {

                } else if (is_buy.equals("2")) {
                    UIHelper.ToastMessage(mContext, "??????????????????????????????");
                    return;
                }

                PreferenceHelper.getInstance(XinTuanYouShengChengDingDanActivity.this).putString(App.XINTUANYOU_PAY, "XINTUANYOU_PAY");
                progressDialog.setMessage("??????????????????????????????...");
                progressDialog.show();
                getWeiXinOrZhiFuBao();

            }
        });


        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_XINTUANYOU_PAY) {
                    // x5WebView.loadUrl("javascript:java_js('appToJsPaySuccess')");
                    //  form_id = "";
                    PaySuccessUtils.getNet(XinTuanYouShengChengDingDanActivity.this, form_id);
                    finish();
                } else if (message.type == ConstanceValue.MSG_XINTUANYOU_PAY_FAIL) {
                    PaySuccessUtils.getNetFail(XinTuanYouShengChengDingDanActivity.this, form_id);
                    finish();
                }
            }
        }));
    }

    String form_id = "";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_xin_tuan_you_sheng_cheng_ding_dan;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("????????????");
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


    /**
     * ?????????????????????
     */
    public void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(XinTuanYouShengChengDingDanActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);

            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * ????????????
     *
     * @param out
     */
    private void goToWeChatPay(YuZhiFuModel.DataBean out) {
        api = WXAPIFactory.createWXAPI(XinTuanYouShengChengDingDanActivity.this, dataBean.getPay().getAppid());
        api.registerApp(out.getPay().getAppid());

        PayReq req = new PayReq();

        req.appId = out.getPay().getAppid();
        req.partnerId = out.getPay().getPartnerid();
        req.prepayId = out.getPay().getPrepayid();
        req.timeStamp = out.getPay().getTimestamp();
        req.nonceStr = out.getPay().getNoncestr();
        req.sign = out.getPay().getSign();
        req.packageValue = out.getPay().getPackageX();

        api.sendReq(req);
    }


    private static final int SDK_PAY_FLAG = 1;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                     */
                    String resultInfo = payResult.getResult();// ?????????????????????????????????
                    String resultStatus = payResult.getResultStatus();
                    // ??????resultStatus ???9000?????????????????????
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        Notice n = new Notice();
//                        n.type = ConstanceValue.MSG_DALIBAO_SUCCESS;
//                        //  n.content = message.toString();
//                        RxBus.getDefault().sendRx(n);
//                        finish();
                        // ??????????????????????????????????????????????????????????????????????????????
                        //   Toast.makeText(DaLiBaoZhiFuActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                        //   finish();
                        // ????????????????????????????????????????????????  ?????????????????? ?????????  ?????????????????????

                        // MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(DaLiBaoZhiFuActivity.this, "????????????", "?????????????????????");
                        // dialog_success.show();

                        PaySuccessUtils.getNet(XinTuanYouShengChengDingDanActivity.this, appId);
                        UIHelper.ToastMessage(XinTuanYouShengChengDingDanActivity.this, "????????????", Toast.LENGTH_SHORT);
                        finish();

                    } else {
                        // ???????????????????????????????????????????????????????????????????????????
                        Toast.makeText(XinTuanYouShengChengDingDanActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                        PaySuccessUtils.getNetFail(XinTuanYouShengChengDingDanActivity.this, form_id);
                        finish();
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    private String diYongQuanId;

    TishiDialog tishiDialog;
    private void getWeiXinOrZhiFuBao() {
        if (StringUtils.isEmpty(pay_id)) {
            UIHelper.ToastMessage(mContext, "???????????????????????????");
            progressDialog.dismiss();
            return;
        }
        if (pay_id.equals("1")) {//1?????????

            //???????????????????????????\

            /**
             * {
             *   "key":"20180305124455yu",
             *  "token":"1234353453453456",
             *  "operate_id":"12",
             *  "operate_type":"1",
             *  "pay_id":"1"
             * }
             *
             *
             * inst_id	?????????id		???
             * oilNo	??????		???
             * gunNo	??????		???
             * money	??????????????????????????????????????????????????????????????????????????????		???
             * user_agio_id	???id		???
             * deduction_type	????????????????????????1.?????? 2.???		???
             */

            Map<String, String> map = new HashMap<>();
            map.put("key", Urls.key);
            map.put("token", UserManager.getManager(XinTuanYouShengChengDingDanActivity.this).getAppToken());
            map.put("operate_type", "30");
            map.put("pay_id", pay_id);
            map.put("pay_type", payType);


            map.put("inst_id", inst_id);
            map.put("oilNo", shenMYouHao.substring(0, shenMYouHao.length() - 1));
            map.put("gunNo", shenMQiangHao);
            map.put("money", privoidJinE);

            if (selectHongBaoFlag.equals("0")) {
                map.put("user_agio_id", diYongQuanId);
            }

            //   map.put("pay_type", payType);


            map.put("deduction_type", userHongBao);
//        NetUtils<Object> netUtils = new NetUtils<>();
//        netUtils.requestData(map, Urls.DALIBAO_PAY, DaLiBaoZhiFuActivity.this, new JsonCallback<AppResponse<Object>>() {
//            @Override
//            public void onSuccess(Response<AppResponse<Object>> response) {
//
//            }
//
//            @Override
//            public void onError(Response<AppResponse<Object>> response) {
//                super.onError(response);
//                //UIHelper.ToastMessage(DaLiBaoZhiFuActivity.this, response.message());
//            }
//        });


            // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
            Gson gson = new Gson();
            OkGo.<AppResponse<YuZhiFuModel_AliPay.DataBean>>post(Urls.DALIBAO_PAY)
                    .tag(XinTuanYouShengChengDingDanActivity.this)//
                    .upJson(gson.toJson(map))
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel_AliPay.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {

                            appId = response.body().data.get(0).getPay();

                            form_id = response.body().data.get(0).getOut_trade_no();
                            payV2(appId);//???????????????????????????????????????

                            progressDialog.dismiss();
                        }

                        @Override
                        public void onError(Response<AppResponse<YuZhiFuModel_AliPay.DataBean>> response) {
                            super.onError(response);

                            //  UIHelper.ToastMessage(getActivity(), response.body().msg);
                            String str = response.getException().getMessage();

                            String[] str1 = str.split("???");

                            if (str1.length == 3) {
                                UIHelper.ToastMessage(mContext, str1[2]);
                            }
                        }
                    });

        } else {//2??????

            //???????????????????????????\

            /**
             * {
             *   "key":"20180305124455yu",
             *  "token":"1234353453453456",
             *  "operate_id":"12",
             *  "operate_type":"1",
             *  "pay_id":"1"
             * }
             */

            Map<String, String> map = new HashMap<>();
            map.put("key", Urls.key);
            map.put("token", UserManager.getManager(XinTuanYouShengChengDingDanActivity.this).getAppToken());
            map.put("operate_type", "30");
            map.put("pay_id", pay_id);
            map.put("pay_type", payType);


            map.put("inst_id", inst_id);
            map.put("oilNo", shenMYouHao.substring(0, shenMYouHao.length() - 1));
            map.put("gunNo", shenMQiangHao);
            map.put("money", privoidJinE);

            if (selectHongBaoFlag.equals("0")) {
                map.put("user_agio_id", diYongQuanId);
            }

            //   map.put("pay_type", payType);


            map.put("deduction_type", userHongBao);
//        NetUtils<Object> netUtils = new NetUtils<>();
//        netUtils.requestData(map, Urls.DALIBAO_PAY, DaLiBaoZhiFuActivity.this, new JsonCallback<AppResponse<Object>>() {
//            @Override
//            public void onSuccess(Response<AppResponse<Object>> response) {
//
//            }
//
//            @Override
//            public void onError(Response<AppResponse<Object>> response) {
//                super.onError(response);
//                //UIHelper.ToastMessage(DaLiBaoZhiFuActivity.this, response.message());
//            }
//        });


            // if (NetworkUtils.isNetAvailable(DaLiBaoZhiFuActivity.this)) {
            Gson gson = new Gson();
            OkGo.<AppResponse<YuZhiFuModel.DataBean>>post(Urls.DALIBAO_PAY)
                    .tag(XinTuanYouShengChengDingDanActivity.this)//
                    .upJson(gson.toJson(map))
                    .execute(new JsonCallback<AppResponse<YuZhiFuModel.DataBean>>() {
                        @Override
                        public void onSuccess(Response<AppResponse<YuZhiFuModel.DataBean>> response) {

                            //   appId = response.body().data.get(0).getPay().getAppid();
                            dataBean = response.body().data.get(0);
                            api = WXAPIFactory.createWXAPI(XinTuanYouShengChengDingDanActivity.this, dataBean.getPay().getAppid());

                            form_id = dataBean.getPay().getOut_trade_no();
                            goToWeChatPay(dataBean);
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onError(Response<AppResponse<YuZhiFuModel.DataBean>> response) {
                            super.onError(response);
                            progressDialog.dismiss();
                            //  UIHelper.ToastMessage(getActivity(), response.body().msg);
                            String str = response.getException().getMessage();

                            tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                                @Override
                                public void onClickCancel(View v, TishiDialog dialog) {
                                    tishiDialog.dismiss();
                                }

                                @Override
                                public void onClickConfirm(View v, TishiDialog dialog) {

                                    finish();
                                }

                                @Override
                                public void onDismiss(TishiDialog dialog) {

                                }
                            });
                            tishiDialog.setTextContent(str);
                            tishiDialog.show();
                        }
                    });

        }

    }
    //????????????

    /**
     * ????????????Activty????????????Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String shemYou, String shemYouHao, String shenmQiangHao, String shenmJinE, String shengShu, String inst_id) {
        Intent intent = new Intent(context, XinTuanYouShengChengDingDanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("shenmYou", shemYou);
        intent.putExtra("shenmYouHao", shemYouHao);
        intent.putExtra("shenmQiangHao", shenmQiangHao);
        intent.putExtra("shenmJinE", shenmJinE);
        intent.putExtra("shengShu", shengShu);
        intent.putExtra("inst_id", inst_id);
        context.startActivity(intent);
    }


    private String privoidJinE = "";
    private String zhiJiangJIne = "";//????????????
    BigDecimal finalDecimal;
    BigDecimal heJiJinEDecimeal;
    BigDecimal hongBaoYuEDecimeal;

    private void getNet() {
        //???????????????????????? ?????????????????????
        Map<String, String> map = new HashMap<>();
        map.put("code", "04247");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(XinTuanYouShengChengDingDanActivity.this).getAppToken());
        map.put("money", shenMJIne);
        map.put("gunNo", shenMQiangHao);
        map.put("inst_id", inst_id);
        map.put("oilNo", shenMYouHao.substring(0, shenMYouHao.length() - 1));

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<XinTuanYouShengChengDingDanBean.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<XinTuanYouShengChengDingDanBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<XinTuanYouShengChengDingDanBean.DataBean>> response) {

                        XinTuanYouShengChengDingDanActivity.this.response = response;
                        showPage();


                        privoidJinE = response.body().data.get(0).getPaid_money();

                        // tvHeji.setText("??????" + "??" + response.body().data.get(0).getPaid_money());

                        heJiJinEDecimeal = new BigDecimal(response.body().data.get(0).getPaid_money());

                        hongBaoYuEDecimeal = new BigDecimal(response.body().data.get(0).getAvailable_balance());


                        finalDecimal = heJiJinEDecimeal.subtract(hongBaoYuEDecimeal);


                        if (finalDecimal.compareTo(BigDecimal.ZERO) == -1) {
                            tvHeji.setText("??????" + "??" + "0.01");
                        } else {
                            tvHeji.setText("??????" + finalDecimal.toString());
                        }


                        zhiJiangJIne = response.body().data.get(0).getReduction_money();
                        tvZhijiang.setText(zhiJiangJIne);

                        tvSheng.setText("???" + response.body().data.get(0).getLitre() + "L");

                        wx_pay = response.body().data.get(0).getWx_pay();
                        ali_pay = response.body().data.get(0).getAli_pay();
                        is_buy = response.body().data.get(0).getIs_buy();


                        if (ali_pay.equals("1")) {
                            tvZhifubao.setText("???????????????(?????????????????????)");
                            //  ivZhifubaoChoose.setVisibility(View.VISIBLE);

                        } else if (ali_pay.equals("2")) {
                            tvZhifubao.setText("???????????????(????????????????????????)");
                            // ivZhifubaoChoose.setVisibility(View.GONE);
                        }

                        if (wx_pay.equals("1")) {
                            tvWeixin.setText("????????????(?????????????????????)");
                            // ivWeixinChoose.setVisibility(View.VISIBLE);

                        } else if (wx_pay.equals("2")) {
                            tvWeixin.setText("????????????(????????????????????????)");
                            // ivZhifubaoChoose.setVisibility(View.GONE);
                        }

                        if (is_buy.equals("1")) {


                        } else if (is_buy.equals("2")) {
                            ivWeixinChoose.setVisibility(View.GONE);
                            ivZhifubaoChoose.setVisibility(View.GONE);
                        }


                    }
                });
    }


    private void showPage() {
        tvDikoujine.setText(response.body().data.get(0).getAvailable_balance());
        tvMoney.setText("??" + shenMJIne);
        //  String strPhone = PreferenceHelper.getInstance(XinTuanYouShengChengDingDanActivity.this).getString("user_phone", "");
        //tvShoujihaoNumber.setText(strPhone);
        if (response.body().data.get(0).getAvailable_balance() == null) {
            response.body().data.get(0).setAvailable_balance("0.00");
        }

        if (StringUtils.isEmpty(response.body().data.get(0).getAvailable_balance())) {
            response.body().data.get(0).setAvailable_balance("0.00");
        }

        BigDecimal bigDecimal = new BigDecimal(response.body().data.get(0).getAvailable_balance() + "");

        if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
            //  selectHongBaoFlag = "0";
            ivChoose.setVisibility(View.INVISIBLE);
            userHongBao = "1";//??????
            tvDanqianDikou.setVisibility(View.GONE);
            tvDikoujine.setVisibility(View.GONE);
            selectHongBaoFlag = "0";
        } else {
            selectHongBaoFlag = "1";
            ivChoose.setVisibility(View.VISIBLE);
            userHongBao = "2";//???

            tvDanqianDikou.setVisibility(View.VISIBLE);
            tvDikoujine.setVisibility(View.VISIBLE);

        }

        if (response.body().data.get(0).getCount().equals("0")) {
            tvZanwu.setText("????????????");


        } else {
            tvZanwu.setText(response.body().data.get(0).getCount() + "??????");
        }

    }


}

