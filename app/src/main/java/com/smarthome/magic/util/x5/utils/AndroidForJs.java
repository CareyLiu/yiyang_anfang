package com.smarthome.magic.util.x5.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.smarthome.magic.activity.saoma.SaoMaZhiFuActivity;
import com.smarthome.magic.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.smarthome.magic.adapter.xin_tuanyou.XinTuanYouShengChengDingDanActivity;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.model.MenSuoModel;
import com.smarthome.magic.model.SaoMaPayModel;
import com.smarthome.magic.model.SaoMaWeiXinPayModel;
import com.smarthome.magic.model.SaoMaZhiFuBaoPayModel;
import com.smarthome.magic.model.YuZhiFuModel;
import com.smarthome.magic.pay_about.alipay.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
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

    //title 广播
    public static final String title_action = "broadcast.action.title";

    //幸运汇重定向 广播
    public static final String getluckyurl_action = "broadcast.action.getluckyurl";

    //开心汇 重定向 广播
    public static final String switchhome_action = "broadcast.action.switchhome";


    long personId = -1;

    public AndroidForJs(Context context) {
        this.mContext = context;
        this.ac = (Activity) context;
        gson = new Gson();
    }

    //JS请求java
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

    //JS请求java
    @JavascriptInterface
    public void jsToAppPayAction(String para) {
        Bundle bundle = new Bundle();

        //  String str = bundle.getString("jsToAppPayAction ");
        Log.i("jsToAppPayAction", para);
//        if (cmd.equals("jsToAppPayAction")) {//
//            //调起支付
//            //SaoMaZhiFuActivity.actionStart();
//            Log.i("jsToAppPayAction", para);
//        }

        //SaoMaZhiFuActivity.actionStart(mContext, null, null, null, null);

        SaoMaPayModel saoMaPayModel = new Gson().fromJson(para, SaoMaPayModel.class);
        PreferenceHelper.getInstance(mContext).putString(App.SAOMA_PAY, "saoma_pay");
        if (saoMaPayModel.getType().equals("1")) {//调支付宝支付

            SaoMaZhiFuBaoPayModel saoMaZhiFuBaoPayModel = new Gson().fromJson(para, SaoMaZhiFuBaoPayModel.class);
            payV2(saoMaZhiFuBaoPayModel.getPay());//这里填写后台返回的支付信息


        } else if (saoMaPayModel.getType().equals("2")) {//调微信支付
            SaoMaWeiXinPayModel saoMaWeiXinPayModel = new Gson().fromJson(para, SaoMaWeiXinPayModel.class);
            SaoMaWeiXinPayModel.PayBean payBean = saoMaWeiXinPayModel.getPay();


            goToWeChatPay(saoMaWeiXinPayModel);
        }


    }

    //JS请求java
    @JavascriptInterface
    public void jsToAppProductAction(String para) {
        Bundle bundle = new Bundle();

        //  String str = bundle.getString("jsToAppPayAction ");
        Log.i("jsToAppProductAction", para);
//        if (cmd.equals("jsToAppPayAction")) {//
//            //调起支付
//            //SaoMaZhiFuActivity.actionStart();
//            Log.i("jsToAppPayAction", para);
//        }
        MenSuoModel menSuoModel = new Gson().fromJson(para, MenSuoModel.class);
        //SaoMaZhiFuActivity.actionStart(mContext, null, null, null, null);


        if (menSuoModel.getWares_id() != null && menSuoModel.getShop_product_id() != null) {
            ZiJianShopMallDetailsActivity.actionStart(mContext, menSuoModel.getShop_product_id(), menSuoModel.getWares_id());
        }
    }

    //支付宝支付

    /**
     * 支付宝支付业务
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
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        Notice n = new Notice();
//                        n.type = ConstanceValue.MSG_DALIBAO_SUCCESS;
//                        //  n.content = message.toString();
//                        RxBus.getDefault().sendRx(n);
//                        finish();
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //   Toast.makeText(DaLiBaoZhiFuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        //   finish();
                        // 通过商品购买正常确认订单支付回调  包括常规商品 大礼包  抢货拼手快商品

                        // MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(DaLiBaoZhiFuActivity.this, "支付成功", "恭喜您支付成功");
                        // dialog_success.show();
                        UIHelper.ToastMessage(mContext, "支付成功", Toast.LENGTH_SHORT);
                        //finish();
                        Notice n = new Notice();
                        n.type = ConstanceValue.MSG_SAOMASUCCESS;
                        //  n.content = message.toString();
                        RxBus.getDefault().sendRx(n);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
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
     * 微信支付
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


}
