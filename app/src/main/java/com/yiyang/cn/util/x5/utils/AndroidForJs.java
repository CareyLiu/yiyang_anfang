package com.yiyang.cn.util.x5.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.PhoneCheckActivity;
import com.yiyang.cn.activity.ShopCartActivity;
import com.yiyang.cn.activity.fenxiang_tuisong.HuoDongTanCengActivity;
import com.yiyang.cn.activity.fenxiang_tuisong.ShouYeFenXiangActivity;
import com.yiyang.cn.activity.saoma.SaoMaZhiFuActivity;
import com.yiyang.cn.activity.wode_page.MyQianBaoActivity;
import com.yiyang.cn.activity.wode_page.TiXianActivity;
import com.yiyang.cn.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.yiyang.cn.adapter.xin_tuanyou.XinTuanYouShengChengDingDanActivity;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.config.Wetch_S;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.DaiLiShangQianBaoModel;
import com.yiyang.cn.model.Home;
import com.yiyang.cn.model.MenSuoModel;
import com.yiyang.cn.model.SaoMaPayModel;
import com.yiyang.cn.model.SaoMaWeiXinPayModel;
import com.yiyang.cn.model.SaoMaZhiFuBaoPayModel;
import com.yiyang.cn.pay_about.alipay.PayResult;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Florent on 02/10/13.
 */
public class AndroidForJs {
    public final static String tag = AndroidForJs.class.getSimpleName();
    private Context mContext;
    private Activity ac;
    String str;
    int iflag = 1;
    public static int shareType = 0;


    private Gson gson;

    //title ??????
    public static final String title_action = "broadcast.action.title";

    //?????????????????? ??????
    public static final String getluckyurl_action = "broadcast.action.getluckyurl";

    //????????? ????????? ??????
    public static final String switchhome_action = "broadcast.action.switchhome";


    long personId = -1;

    public AndroidForJs(Context context) {
        this.mContext = context;
        this.ac = (Activity) context;
        gson = new Gson();
    }

    //JS??????java
    @android.webkit.JavascriptInterface
    public void openImage(String img) {
        String[] imgs = img.split(",");
        ArrayList<String> imgsUrl = new ArrayList<String>();
        for (String s : imgs) {
            imgsUrl.add(s);
            Log.e("-------URL---------", s);
        }
        Intent intent = new Intent();
        intent.putStringArrayListExtra("infos", imgsUrl);
        // intent.setClass(mContext, ImageShowActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    private String formId;

    //JS??????java
    @JavascriptInterface
    public void jsToAppPayAction(String para) {
        Bundle bundle = new Bundle();

        //  String str = bundle.getString("jsToAppPayAction ");
        Log.i("jsToAppPayAction", para);
//        if (cmd.equals("jsToAppPayAction")) {//
//            //????????????
//            //SaoMaZhiFuActivity.actionStart();
//            Log.i("jsToAppPayAction", para);
//        }

        //SaoMaZhiFuActivity.actionStart(mContext, null, null, null, null);

        SaoMaPayModel saoMaPayModel = new Gson().fromJson(para, SaoMaPayModel.class);
        PreferenceHelper.getInstance(mContext).putString(App.SAOMA_PAY, "saoma_pay");
        if (saoMaPayModel.getType().equals("1")) {//??????????????????

            SaoMaZhiFuBaoPayModel saoMaZhiFuBaoPayModel = new Gson().fromJson(para, SaoMaZhiFuBaoPayModel.class);
            payV2(saoMaZhiFuBaoPayModel.getPay());//???????????????????????????????????????


        } else if (saoMaPayModel.getType().equals("2")) {//???????????????
            SaoMaWeiXinPayModel saoMaWeiXinPayModel = new Gson().fromJson(para, SaoMaWeiXinPayModel.class);
            SaoMaWeiXinPayModel.PayBean payBean = saoMaWeiXinPayModel.getPay();


            goToWeChatPay(saoMaWeiXinPayModel);
        }


    }

    @JavascriptInterface
    public void jsToAppMap(String startLat) {
        //??????????????????????????????????????????????????????????????????????????????Intent?????????activity?????????

        Log.i("isToAppConkOut", startLat);
//        if (TextUtils.isEmpty(startLat) || TextUtils.isEmpty(startLng) || TextUtils.isEmpty(endLat)
//                || TextUtils.isEmpty(endLng)) {//?????????????????????????????????????????????
//            Toast.makeText(activity, "?????????????????????", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setTitle("??????");
//        builder.setMessage("????????????????????????\n???????????????:" +
//                startLat + "    " + startLng +
//                "\n???????????????:" + endLat + "    " + endLng);
//
//        builder.setPositiveButton("??????",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//        builder.setCancelable(false);
//        builder.show();

    }

    //JS??????java
    @JavascriptInterface
    public void jsToAppProductAction(String para) {
        Bundle bundle = new Bundle();

        //  String str = bundle.getString("jsToAppPayAction ");
        Log.i("jsToAppProductAction", para);
//        if (cmd.equals("jsToAppPayAction")) {//
//            //????????????
//            //SaoMaZhiFuActivity.actionStart();
//            Log.i("jsToAppPayAction", para);
//        }
        MenSuoModel menSuoModel = new Gson().fromJson(para, MenSuoModel.class);
        //SaoMaZhiFuActivity.actionStart(mContext, null, null, null, null);


        if (menSuoModel.getWares_id() != null && menSuoModel.getShop_product_id() != null) {
            ZiJianShopMallDetailsActivity.actionStart(mContext, menSuoModel.getShop_product_id(), menSuoModel.getWares_id());
        }
    }

    //JS??????java
    @JavascriptInterface
    public void jsToAppDlsTXActionn(String para) {


        getDaiLiQianBaoNet();

    }

    //JS??????java
    @JavascriptInterface
    public void jsToAppTyShare(String para) {
//        UIHelper.ToastMessage(mContext, para);
//        Log.i("app_to_share", para);
        Home.DataBean.activity activity = new Gson().fromJson(para, Home.DataBean.activity.class);
        ShouYeFenXiangActivity.actionStart(mContext, activity);
    }
    //???????????????

    /**
     * ?????????????????????
     */
    public void payV2(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) mContext);
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

    private static final int SDK_PAY_FLAG = 1;
    private String orderId;

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
                        UIHelper.ToastMessage(mContext, "????????????", Toast.LENGTH_SHORT);
                        //finish();
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_SAOMASUCCESS;
                        //  n.content = message.toString();
                        RxBus.getDefault().sendRx(n);

                    } else {
                        // ???????????????????????????????????????????????????????????????????????????
                        Toast.makeText(mContext, "????????????", Toast.LENGTH_SHORT).show();
                        //finish();

                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_SAOMAFAILE;
                        //  n.content = message.toString();
                        RxBus.getDefault().sendRx(n);
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };


    /**
     * ????????????
     *
     * @param out
     */
    private void goToWeChatPay(SaoMaWeiXinPayModel out) {
        IWXAPI api;
        api = WXAPIFactory.createWXAPI(mContext, out.getPay().getAppid());
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

    public void getDaiLiQianBaoNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04344");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());

        Gson gson = new Gson();
        OkGo.<AppResponse<DaiLiShangQianBaoModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user ")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DaiLiShangQianBaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<DaiLiShangQianBaoModel.DataBean>> response) {
                        /**
                         * msg_code	?????????
                         * msg	????????????
                         * data	????????????
                         * user_money	???????????????
                         * inst_money_cash	???????????????
                         * userable_red	???????????????
                         * pay_num	???????????????????????????????????????
                         * pwd_pay	??????????????????????????????0?????????1?????????
                         * userName	?????????
                         * pay_name	?????????????????????
                         */


//                        String checkAliPay = PreferenceHelper.getInstance(mContext).getString(App.CUNCHUBIND_ALIPAY, "0x11");
//                        if (checkAliPay.equals("1")) {//????????????
//                            if (StringUtils.isEmpty(response.body().data.get(0).getUser_money())) {
//                                response.body().data.get(0).setUser_money("0.00");
//                            }
//                            BigDecimal bigDecimal = new BigDecimal(response.body().data.get(0).getUser_money());
//
//                            if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
//                                //???????????????
//                                TiXianActivity.actionStart(mContext, response.body().data.get(0).getUser_money(),"1");
//                            } else {
//                                UIHelper.ToastMessage(mContext, "??????????????????");
//                            }
//                        } else {//2 ?????????
//                            showTwo();
//                        }
                        showWeiXinOrZhiFuBaoSelect(response);

                    }


                    @Override
                    public void onError(Response<AppResponse<DaiLiShangQianBaoModel.DataBean>> response) {

                    }

                });
    }

    /**
     * ??????????????? dialog
     */
    private void showTwo() {

        builder = new AlertDialog.Builder(mContext).setIcon(R.mipmap.logi_icon).setTitle("????????????")
                .setMessage("?????????????????????????????????").setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mContext.startActivity(new Intent(mContext, PhoneCheckActivity.class).putExtra("mod_id", "0008"));
                    }
                }).setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }

    private AlertDialog.Builder builder;

    private int choice = 0;

    /**
     * ?????? dialog
     */
    private void showWeiXinOrZhiFuBaoSelect(Response<AppResponse<DaiLiShangQianBaoModel.DataBean>> response) {

        //?????????????????????
        final String[] items = {"??????", "?????????"};
        builder = new AlertDialog.Builder(mContext).setIcon(R.mipmap.ic_launcher).setTitle("????????????????????????")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice = i;


                    }
                }).setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (choice == 0) {//??????

                            String weixinPay = PreferenceHelper.getInstance(mContext).getString(App.CUNCHUBIND_WEIXINPAY, "0x11");
                            if (weixinPay.equals("1")) {
                                TiXianActivity.actionStart(mContext, response.body().data.get(0).getUser_money(), "1", "2");
                            } else {
                                IWXAPI api;
                                api = WXAPIFactory.createWXAPI(mContext, Wetch_S.APP_ID);
                                SendAuth.Req req = new SendAuth.Req();
                                req.scope = "snsapi_userinfo";
                                req.state = "wechat_sdk_demo_test";
                                api.sendReq(req);
                            }

                        } else {//?????????
                            String checkAliPay = PreferenceHelper.getInstance(mContext).getString(App.CUNCHUBIND_ALIPAY, "0x11");
                            if (checkAliPay.equals("1")) {//????????????

                                TiXianActivity.actionStart(mContext, response.body().data.get(0).getUser_money(), "1", "1");

                            } else {//2 ?????????
                                showTwo();
                            }

                        }
                    }
                });
        builder.create().show();
    }
}

