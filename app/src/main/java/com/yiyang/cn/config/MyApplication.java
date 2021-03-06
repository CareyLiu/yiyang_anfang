package com.yiyang.cn.config;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.multidex.MultiDexApplication;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;

import com.billy.android.loading.Gloading;
import com.bulong.rudeness.RudenessScreenHelper;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttConnect;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.rairmmd.andmqtt.MqttUnSubscribe;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.LoginActivity;
import com.yiyang.cn.activity.tuya_device.utils.BizBundleFamilyServiceImpl;
import com.yiyang.cn.adapter.view.GlobalAdapter;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.CodeClass;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.HardWareValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.RxBus;
import com.yiyang.cn.app.RxUtils;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.PeiWangErrorModel;
import com.yiyang.cn.model.PeiwangOtherModel;
import com.yiyang.cn.model.ZhiNengJiaJuNotifyJson;
import com.yiyang.cn.model.ZhiNengJiaJu_0007Model;
import com.yiyang.cn.model.ZhiNengJiaJu_0009Model;
import com.yiyang.cn.util.DoMqttValue;
import com.yiyang.cn.util.JinChengUtils;
import com.yiyang.cn.util.SerializeUtil;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.tuya.smart.api.router.UrlBuilder;
import com.tuya.smart.api.service.RouteEventListener;
import com.tuya.smart.api.service.ServiceEventListener;
import com.tuya.smart.commonbiz.bizbundle.family.api.AbsBizBundleFamilyService;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.optimus.sdk.TuyaOptimusSdk;
import com.tuya.smart.wrapper.api.TuyaWrapper;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.xiaomi.mipush.sdk.MiPushClient;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.jaaksi.pickerview.picker.BasePicker;
import org.jaaksi.pickerview.util.Util;
import org.jaaksi.pickerview.widget.DefaultCenterDecoration;
import org.jaaksi.pickerview.widget.PickerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import okhttp3.OkHttpClient;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static com.yiyang.cn.get_net.Urls.MESSAGE_URL;

public class MyApplication extends MultiDexApplication {
    protected static final String TAG = "MyApplication";
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    //??????????????????
    public static int windowHeight;
    public static int windowWidth;

    public static Activity activity_main;


    //String mqttUrl = "tcp://192.168.1.127";//????????????
    String mqttUrl = "tcp://mqtt.hljsdkj.com";//??????
    //String mqttUrl = "tcp://ggw.hljsdkj.com";//ggw

    public String getMqttUrl() {
        if (Urls.SERVER_URL.equals("https://shop.hljsdkj.com/")) {
            return "tcp://mqtt.hljsdkj.com";
        } else {
            return "tcp://ggw.hljsdkj.com";
//            return "tcp://mqtt.hljsdkj.com";
        }
    }

    public static List<String> mqttDingyue = new ArrayList<>();

    public static String getServer_id() {
        car_server_id = PreferenceHelper.getInstance(context).getString("car_server_id", "");
        return car_server_id;
    }

    public static String getCcid() {
        ccid = PreferenceHelper.getInstance(context).getString("ccid", "");
        return ccid;
    }

    public static String getUser_id() {
        user_id = PreferenceHelper.getInstance(context).getString("of_user_id", "");
        return user_id;
    }


    public static String car_server_id;
    public static String user_id;
    public static String ccid;
    public static MyApplication application;
    /**
     * ????????????????????????????????????????????? ????????????????????????->???????????????->??????????????????->??????????????????????????????????????????,???????????????????????????g.???????????????MQTT????????????
     */
    public static String CARBOX_GETNOW;//??????????????????

    /**
     * wit/app/??????id ??????
     */

    public static String CARBOX_JINGBAO;//??????????????????


    /**
     * 1.app????????????APP????????????????????????
     */

    public static String CAR_NOTIFY;//??????????????????


    public static String CAR_CTROL;//2.3.2	app??????????????????

    public static String CAR_MAIN;//???????????????

    /**
     * ??????Application
     */
    public static MyApplication getApp() {
        return application;
    }


    private MutableLiveData<String> mBroadcastData;

    private Map<String, Object> mCacheMap;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }

            switch (action) {
                case WifiManager.NETWORK_STATE_CHANGED_ACTION:
                case LocationManager.PROVIDERS_CHANGED_ACTION:
                    mBroadcastData.setValue(action);
                    break;
            }
        }
    };

    DoMqttValue doMqttValue;
    String doMqtt = "0";//0 ?????????mqtt???????????? 1??????mqtt????????????

    public void onCreate() {


        PreferenceHelper.getInstance(this).removeKey(App.CHOOSE_KONGZHI_XIANGMU);
        StringBuffer param = new StringBuffer();
        param.append("appid=" + getString(R.string.app_id));
        param.append(",");
        // ????????????v5+
        param.append(SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC);
        SpeechUtility.createUtility(MyApplication.this, param.toString());
        super.onCreate();
        //????????????????????????
        int designWidth = 720;
        new RudenessScreenHelper(this, designWidth).activate();

        JPushInterface.setDebugMode(true);    // ??????????????????,????????????????????????
        JPushInterface.init(this);
        MiPushClient.getRegId(getApplicationContext());
        doMqttValue = new DoMqttValue();
        context = getApplicationContext();
        initRongYun();
        initLifecycle();
        initWindow();
        initDefaultPicker();
        initOkgo();
        initTuya();//??????????????????
        initYoumeng();//????????????

        // ??????????????????
        String packageName = context.getPackageName();
// ?????????????????????
        String processName = JinChengUtils.getProcessName();
// ???????????????????????????
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// ?????????Bugly


        Bugly.init(getApplicationContext(), "28ca17503e", false);
        AndMqtt.getInstance().init(MyApplication.this);

        CompositeSubscription _subscriptions = new CompositeSubscription();
        _subscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(_subscriptions);

        _subscriptions.add(RxBus.getDefault().toObservable(Notice.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_UNSUB_MQTT) {//???????????? application ?????????????????????mqtt ?????????????????????
                    // AndMqtt.getInstance().init(MyApplication.this);
                    if (AndMqtt.getInstance().isConnect()) {

                        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CARBOX_JINGBAO), new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;??????????????????");
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;??????????????????");
                                Log.i("Rair", "(MainActivity.java)");
                            }
                        });

                        AndMqtt.getInstance().unSubscribe(new MqttUnSubscribe().setTopic(CAR_NOTIFY), new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                Log.i("Rair", "(MainActivity.java:93)-onSuccess:-&gt;??????????????????");
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                Log.i("Rair", "(MainActivity.java:98)-onFailure:-&gt;??????????????????");
                            }
                        });


                    }
                } else if (message.type == ConstanceValue.MSG_CONNET_MQTT) {

                    setMqttConnect();

                } else if (message.type == ConstanceValue.MSG_RONGYUN_CHONGZHI) {
                    initRongYun();
                }
            }
        }));

        setMqttConnect();

        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                // ???????????????????????????????????????????????????
                // ????????????????????????????????????
                AlibcTradeSDK.setSyncForTaoke(true);


                // ?????????????????????????????????????????????????????????????????????????????????show??????????????????
                //   AlibcTradeSDK.setTaokeParams(taokeParams);
                Log.i("AlibcTradeSDK", "success");
            }

            @Override
            public void onFailure(int code, String msg) {
                //??????????????????????????????code???msg?????????????????????????????????????????????

                Log.i("AlibcTradeSDK", "fail" + "code:" + code + "msg:" + msg);
            }
        });

        application = this;

        mCacheMap = new HashMap<>();

        mBroadcastData = new MutableLiveData<>();
        IntentFilter filter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            filter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION);
//        }
        registerReceiver(mReceiver, filter);

        //view
        Gloading.initDefault(new GlobalAdapter());
        Logger.addLogAdapter(new AndroidLogAdapter());

//        List<String> roomList = new ArrayList<>();
//        roomList.add("??????");
//        roomList.add("??????");
//        List<String> sheBeiList = new ArrayList<>();
//        sheBeiList.add("??????");
//        sheBeiList.add("?????????");

        //  new ShangChuanDongTaiShiTiTool(context, roomList, sheBeiList);
    }

    private void initYoumeng() {
        UMConfigure.init(this, "5fdff473842ba953b890dd98", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "a51f307db82a88eaa46cf1cef4009316");
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String token) {

            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });

        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                new Handler(getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {

                    }
                });
            }

//            @Override
//            public Notification getNotification(Context context, UMessage msg) {
//                Notification.Builder builder = new Notification.Builder(context).setContentTitle(msg.title).setContentText(msg.text).setDefaults(Notification.DEFAULT_ALL);
//                return builder.build();
//            }
        };

        mPushAgent.setMessageHandler(messageHandler);
    }

    private void initTuya() {//??????????????????
//        TuyaHomeSdk.init(this);
//        TuyaHomeSdk.setDebugMode(true);
        Fresco.initialize(this);
        TuyaHomeSdk.init(this);
        TuyaWrapper.init(this, new RouteEventListener() {
            @Override
            public void onFaild(int errorCode, UrlBuilder urlBuilder) {
                // urlBuilder.target is a router address, urlBuilder.params is a router params
                // urlBuilder.target ??????????????? urlBuilder.params ????????????
                Log.e("router not implement", errorCode + "  ????????????  " + urlBuilder.target + "  ?????????  " + urlBuilder.params.toString());
            }
        }, new ServiceEventListener() {
            @Override
            public void onFaild(String serviceName) {
                Log.e("service not implement", serviceName);
            }
        });
        TuyaOptimusSdk.init(this);

        // register family service???mall bizbundle don't have to implement it.
        // ????????????????????????????????????????????????????????????
        TuyaWrapper.registerService(AbsBizBundleFamilyService.class, new BizBundleFamilyServiceImpl());
    }

    private void initRongYun() {
        // ?????????. ????????? Application ??????????????????.
        String appKey = "cpj2xarlct6en";
        RongIM.init(context, appKey);

        String rongYunToken = UserManager.getManager(context).getRongYun();
        if (!StringUtils.isEmpty(rongYunToken)) {
            connectRongYun(rongYunToken);

            RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageWrapperListener() {
                /**
                 * ?????????????????????????????????
                 * ??????:
                 * 1. ????????????????????????????????????????????? 200 ?????????????????????????????????????????????????????????????????????????????????
                 * 2. hasPackage ???????????????????????????????????????left ?????????????????????????????????????????? App ???????????????????????????
                 * ?????????????????????????????????
                 * 1. hasPackage ??? left ?????? 0???
                 * 2. hasPackage ??? 0 ???????????????????????????????????????200???????????????left ??? 0 ????????????????????????????????????????????????????????????
                 *
                 * @param message    ????????????????????????
                 * @param left       ?????????????????????????????????????????????????????????
                 * @param hasPackage ????????????????????????????????????????????????
                 * @param offline    ????????????????????????
                 * @return ????????????????????? ?????? App ??????????????????????????? true; ???????????? false ??? SDK ?????????
                 */
                @Override
                public boolean onReceived(final Message message, final int left, boolean hasPackage, boolean offline) {
                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_RONGYUN_REVICE;
                    // notice.content = status.
                    //* ??????????????????????????????
                    notice.content = message;
                    RxBus.getDefault().sendRx(notice);
                    return false;
                }
            });

            RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
                /**
                 * ????????????????????????
                 * @param status ?????????
                 * CONN_USER_BLOCKED
                 * ??????????????????????????????
                 * CONNECTED
                 * ???????????????
                 * CONNECTING
                 * ????????????
                 * DISCONNECTED
                 * ???????????????
                 * KICKED_OFFLINE_BY_OTHER_CLIENT
                 * ????????????????????????????????????????????????????????????
                 * NETWORK_UNAVAILABLE
                 * ??????????????????
                 * SERVER_INVALID
                 * ?????????????????????????????????
                 * TOKEN_INCORRECT
                 * Token ????????????
                 */
                @Override
                public void onChanged(ConnectionStatus status) {

                    Log.i("rongyun", status.getMessage());
                    if (status.getValue() == 3) {

                        Intent intent = new Intent(getAppContext(), LoginActivity.class);
                        intent.putExtra("token_guoqi", "token_guoqi");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }

                    Notice notice = new Notice();
                    notice.type = ConstanceValue.MSG_RONGYUN_STATE;
                    // notice.content = status.
                    //* ??????????????????????????????
                    notice.content = status;
                    RxBus.getDefault().sendRx(notice);


                }
            });
        }

    }

    public void connectRongYun(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallbackEx() {
            /**
             * ???????????????.
             * @param code ?????????????????????. DATABASE_OPEN_SUCCESS ?????????????????????; DATABASE_OPEN_ERROR ?????????????????????
             */
            @Override
            public void OnDatabaseOpened(RongIMClient.DatabaseOpenStatus code) {
                Log.i("rongYun", "?????????????????????");
            }

            /**
             * token ??????
             */
            @Override
            public void onTokenIncorrect() {
                Log.i("rongYun", "token ??????");
            }

            /**
             * ????????????
             * @param userId ???????????? ID
             */
            @Override
            public void onSuccess(String userId) {
                //UIHelper.ToastMessage(mContext, "??????????????????");
                Log.i("rongYun", "??????????????????");
                PreferenceHelper.getInstance(getApplicationContext()).putString(AppConfig.RONGYUN_TOKEN, token);


            }

            /**
             * ????????????
             * @param errorCode ?????????
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.i("rongYun", "??????????????????");
            }
        });

    }


    public static MyApplication getInstance() {
        return application;
    }

    public void observeBroadcast(LifecycleOwner owner, Observer<String> observer) {
        mBroadcastData.observe(owner, observer);
    }

    public void observeBroadcastForever(Observer<String> observer) {
        mBroadcastData.observeForever(observer);
    }

    public void removeBroadcastObserver(Observer<String> observer) {
        mBroadcastData.removeObserver(observer);
    }

    public void putCache(String key, Object value) {
        mCacheMap.put(key, value);
    }

    public Object takeCache(String key) {
        return mCacheMap.remove(key);
    }

    public void setMqttConnect() {
        /**
         * ????????????????????????????????????????????? ????????????????????????->???????????????->??????????????????->??????????????????????????????????????????,???????????????????????????g.???????????????MQTT????????????
         */

        CARBOX_JINGBAO = "wit/app/" + getUser_id();
        Log.i("getInformation", "CARBOX_JINGBAO   " + CARBOX_JINGBAO);

        CARBOX_GETNOW = "wit/cbox/app/" + getServer_id() + getCcid();
        Log.i("getInformation", "CARBOX_GETNOW   " + CARBOX_GETNOW);

        CAR_NOTIFY = "wit/server/" + "01/" + getUser_id();
        Log.i("getInformation", "CAR_NOTIFY     " + CAR_NOTIFY);

        CAR_CTROL = "wit/cbox/hardware/" + getServer_id() + getCcid();

        if (!StringUtils.isEmpty(getUser_id())) {  //???????????????mqtt  ?????????????????? userId ?????????????????? ????????????????????????
            if (isAppProcess()) {
                AndMqtt.getInstance().init(this);
                MqttConnect builder = new MqttConnect();
                builder.setClientId(HardWareValue.CLIENT_ID + getUser_id() + System.currentTimeMillis())//???????????????
                        .setPort(9096)
//                        .setPort(9093)
                        .setAutoReconnect(true)
                        .setCleanSession(true)
                        .setKeepAlive(60)
                        .setCleanSession(true)
                        .setLastWill("K.", "wit/server/01/" + getUser_id(), 2, true)
                        .setUserName("witandroid")
                        .setUserPassword("aG678om34buysdi")
                        .setServer(getMqttUrl()).setTimeout(1);

                builder.setMessageListener(new MqttCallbackExtended() {
                    @Override
                    public void connectComplete(boolean reconnect, String serverURI) {
                        Log.i("Rair", "(MainActivity.java:29)-connectComplete:-&gt;????????????");
                        sendRx(ConstanceValue.MSG_MQTT_CONNECTCOMPLETE, "");
                        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                                .setTopic(CARBOX_JINGBAO)
                                .setQos(2), new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                Log.i("Rair", "???????????? ??????" + CARBOX_JINGBAO);
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                Log.i("Rair", "(MainActivity.java:68)-onFailure:-&gt;????????????");
                            }
                        });


                        Log.i("rair", "????????????????????????" + mqttDingyue.size() + "");
                        for (int i = 0; i < mqttDingyue.size(); i++) {
                            Log.i("rair", "?????????????????????" + mqttDingyue.get(i));
                            AndMqtt.getInstance().subscribe(new MqttSubscribe()
                                    .setTopic(mqttDingyue.get(i))
                                    .setQos(2), new IMqttActionListener() {
                                @Override
                                public void onSuccess(IMqttToken asyncActionToken) {

                                }

                                @Override
                                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                                }
                            });
                        }

                        Notice notice = new Notice();
                        notice.type = ConstanceValue.MSG_NETWORK_CHANGE;
                        RxBus.getDefault().sendRx(notice);
                    }

                    @Override
                    public void connectionLost(Throwable cause) {
                        Log.i("Rair", "(MainActivity.java:34)-connectionLost:-&gt;????????????" + cause.getMessage().toString());
                        //UIHelper.ToastMessage(context, "??????????????????????????????", Toast.LENGTH_SHORT);
                        sendRx(ConstanceValue.MSG_MQTT_CONNECTLOST, "");
                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        if (doMqtt.equals("0")) {
                            return;
                        }
                        System.out.println("Rair ??????????????????" + topic + "  ????????????????????????  " + message.toString());
                        if (message.toString().contains("{")) {
                            //???????????? code
                            Gson gson = new Gson();
                            CodeClass codeClass = gson.fromJson(message.toString(), CodeClass.class);
                            if (codeClass.code.equals("q")) {
                                //q ???????????????
                            } else if (codeClass.code.equals("o")) {

                                //??????
                                Notice n = new Notice();
                                n.type = ConstanceValue.MSG_GUZHANG_SHOUYE;
                                n.content = message.toString();
                                RxBus.getDefault().sendRx(n);

                            } else if (codeClass.code.equals("jyj_0006")) {
                                Notice n = new Notice();
                                n.type = ConstanceValue.MSG_ZHINENGJIAJU_MENCI;
                                ZhiNengJiaJuNotifyJson zhiNengJiaJuNotifyJson = new Gson().fromJson(message.toString(), ZhiNengJiaJuNotifyJson.class);
                                n.content = zhiNengJiaJuNotifyJson;
                                RxBus.getDefault().sendRx(n);
                            } else if (codeClass.code.equals("jyj_0007")) {
                                Notice n = new Notice();
                                n.type = ConstanceValue.MSG_TIANJIASHEBEI;
                                ZhiNengJiaJu_0007Model zhiNengJiaJuNotifyJson = new Gson().fromJson(message.toString(), ZhiNengJiaJu_0007Model.class);
                                n.content = zhiNengJiaJuNotifyJson;
                                RxBus.getDefault().sendRx(n);
                            } else if (codeClass.code.equals("jyj_0009")) {
                                Notice n = new Notice();
                                n.type = ConstanceValue.MSG_TIANJIAZHUJI;
                                ZhiNengJiaJu_0009Model zhiNengJiaJuNotifyJson = new Gson().fromJson(message.toString(), ZhiNengJiaJu_0009Model.class);
                                n.content = zhiNengJiaJuNotifyJson;
                                RxBus.getDefault().sendRx(n);
                                //
                            } else if (codeClass.code.equals("jyj_0008")) {
                                Notice n = new Notice();
                                n.type = ConstanceValue.MSG_PEIWANG_ERROR;
                                PeiWangErrorModel peiWangErrorModel = new Gson().fromJson(message.toString(), PeiWangErrorModel.class);
                                n.content = peiWangErrorModel.notify_text;
                                RxBus.getDefault().sendRx(n);
                            } else if (codeClass.code.equals("jyj_0010")) {
                                // ?????????????????????????????????????????????
                                Notice n = new Notice();
                                n.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                                RxBus.getDefault().sendRx(n);
                                PeiWangErrorModel peiWangErrorModel = new Gson().fromJson(message.toString(), PeiWangErrorModel.class);

                                TishiDialog tishiDialog = new TishiDialog(AppManager.getAppManager().currentActivity(), 3, new TishiDialog.TishiDialogListener() {
                                    @Override
                                    public void onClickCancel(View v, TishiDialog dialog) {

                                    }

                                    @Override
                                    public void onClickConfirm(View v, TishiDialog dialog) {

                                    }

                                    @Override
                                    public void onDismiss(TishiDialog dialog) {

                                    }
                                });
                                tishiDialog.setTextContent(peiWangErrorModel.notify_text);
                                tishiDialog.setTextCancel("");
                                tishiDialog.setTextConfirm("??????");
                                tishiDialog.show();
                            } else if (codeClass.code.equals("jyj_0011")) {// ??????????????????
                                PeiwangOtherModel peiwangOtherModel = new Gson().fromJson(message.toString(), PeiwangOtherModel.class);
                                Notice n = new Notice();
                                n.type = ConstanceValue.MSG_ZHUJIBANG_OTHER;
                                n.content = peiwangOtherModel;
                                RxBus.getDefault().sendRx(n);
                            }

                        } else if (message.toString().contains("p.")) {
                            Notice n = new Notice();
                            n.type = ConstanceValue.MSG_P;
                            RxBus.getDefault().sendRx(n);
                        } else {
                            doMqttValue.doValue(context, topic, message.toString());
                        }
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {
                        try {
                            Log.i("Rair", "????????????????????? " + token.getMessage().toString());
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                        sendRx(ConstanceValue.MSG_MQTT_CONNECTCOMPLETE, "");
                    }
                });

                AndMqtt.getInstance().connect(builder, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "(MainActivity.java:51)-onSuccess:-&gt;????????????");

                        AndMqtt.getInstance().subscribe(new MqttSubscribe()
                                .setTopic(CARBOX_JINGBAO)
                                .setQos(2), new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                Log.i("Rair", "???????????? ??????" + CARBOX_JINGBAO);
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                Log.i("Rair", "(MainActivity.java:68)-onFailure:-&gt;????????????");
                            }
                        });

                        sendRx(ConstanceValue.MSG_MQTT_CONNECT_CHONGLIAN_ONSUCCESS, "");

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:56)-onFailure:-&gt;????????????" + exception.getMessage() + "..." + exception.getLocalizedMessage());
                        //System.out.println("exception.getMessage" + exception.getMessage());
                        sendRx(ConstanceValue.MSG_MQTT_CONNECT_CHONGLIAN_ONFAILE, "");
                    }
                });
            }
        }
    }


    public boolean containsProperty(String key) {
        Properties props = getProperties();
        return props.containsKey(key);
    }

    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    public String getProperty(String key) {
        return AppConfig.getAppConfig(this).get(key);
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    Activity nowActivity;

    private void initLifecycle() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (activity.getParent() != null) {//???????????????????????????????????????
                    context = activity.getParent();

                } else {
                    context = activity;
                }
                activity_main = activity;
                Log.i(TAG, "ONACTIVITYCREATED  activityName:" + activity_main.getClass().getSimpleName());
                if (activity_main.getClass().getSimpleName().equals("LoginActivity")) {
                    doMqtt = "0";
                } else {
                    doMqtt = "1";
                }

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (activity.getParent() != null) {
                    context = activity.getParent();
                } else {
                    context = activity;
                }
                activity_main = activity;
                Log.i(TAG, "onActivityStarted  activityName:" + activity_main.getClass().getSimpleName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (activity.getParent() != null) {
                    context = activity.getParent();
                } else {
                    context = activity;
                }
                activity_main = activity;
                Log.i(TAG, "onActivityResumed  activityName:" + activity_main.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                if (activity.getParent() != null) {
                    context = activity.getParent();
                } else {
                    context = activity;
                }
                Log.i(TAG, "onActivityPaused  activityName:" + activity_main.getClass().getSimpleName());

            }

            @Override
            public void onActivityStopped(Activity activity) {
                if (activity.getParent() != null) {
                    context = activity.getParent();
                } else {
                    context = activity;
                }
                Log.i(TAG, "onActivityStopped  activityName:" + activity_main.getClass().getSimpleName());

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                if (activity.getParent() != null) {
                    context = activity.getParent();
                } else {
                    context = activity;
                }
                Log.i(TAG, "onActivitySaveInstanceState  activityName:" + activity_main.getClass().getSimpleName());
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (activity.getParent() != null) {
                    context = activity.getParent();
                } else {
                    context = activity;
                }
                Log.i(TAG, "onActivityDestroyed  activityName:" + activity_main.getClass().getSimpleName());
            }
            //.....
        });

    }


    private void initDefaultPicker() {
        // ??????????????????????????????????????????????????????????????????app???????????????Picker.
        // BasePickerView
        PickerView.sDefaultVisibleItemCount = 3;
        PickerView.sDefaultItemSize = 50;
        PickerView.sDefaultIsCirculation = true;

        // PickerView
        PickerView.sOutTextSize = 18;
        PickerView.sCenterTextSize = 18;
        PickerView.sCenterColor = getResources().getColor(R.color.blue_light);
        PickerView.sOutColor = Color.GRAY;

        // BasePicker
        int padding = Util.dip2px(this, 20);
        BasePicker.sDefaultPaddingRect = new Rect(padding, padding, padding, padding);
        BasePicker.sDefaultPickerBackgroundColor = Color.WHITE;

        // DefaultCenterDecoration
        DefaultCenterDecoration.sDefaultLineWidth = 1;
        DefaultCenterDecoration.sDefaultLineColor = Color.RED;
        //DefaultCenterDecoration.sDefaultDrawable = new ColorDrawable(Color.WHITE);
        int leftMargin = Util.dip2px(this, 10);
        int topMargin = Util.dip2px(this, 2);
        DefaultCenterDecoration.sDefaultMarginRect =
                new Rect(leftMargin, -topMargin, leftMargin, -topMargin);
    }

    private void initOkgo() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log????????????????????????log?????????????????????
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log????????????????????????log???????????????????????????
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        //???????????????????????????
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //???????????????????????????
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //???????????????????????????
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        OkGo.getInstance().init(this)                       //?????????????????????
                .setOkHttpClient(builder.build())               //????????????OkHttpClient??????????????????????????????
                .setCacheMode(CacheMode.NO_CACHE)               //???????????????????????????????????????????????????????????????
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //????????????????????????????????????????????????????????????
                .setRetryCount(3);                         //?????????????????????????????????????????????????????????????????????????????????4???(???????????????????????????????????????)???????????????????????????0
//				.addCommonParams(params);                       //??????????????????



    }

    /*
        ????????????
     */
    private void initWindow() {
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        windowWidth = mDisplayMetrics.widthPixels;
        windowHeight = mDisplayMetrics.heightPixels;
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//????????????
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//????????????
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//????????????
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    public static Context getAppContext() {
        return context;
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * ????????????????????????app??????
     *
     * @return
     */


    public boolean isAppProcess() {
        String processName = JinChengUtils.getProcessName();
        if (processName == null || !processName.equalsIgnoreCase(this.getPackageName())) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * * ????????????byte[]???object??????String???</br>
     * * ??????????????????Object???????????????byte[],?????????jdk??????????????????</br>
     * * ??????????????????????????????????????????????????????????????????String??????Object???????????????????????????????????????
     */
    private Object unSerializeObjOrString(byte[] bytes) {
        // ??????6?????????????????????????????????,????????????????????????????????????byte[]??????8???
        if (bytes.length < 6) {
            return SerializeUtil.Byte2String(bytes);
        }

        String protocol = Integer.toHexString(bytes[0] & 0x000000ff) + Integer.toHexString(bytes[1] & 0x000000ff);

        // ?????????jdk???????????????
        if ("ACED".equals(protocol.toUpperCase())) {
            Object obj = SerializeUtil.unserializeObj(bytes);

            if (obj != null) {
                return obj;
            }

            // ?????????????????????????????????null
            else {
                return SerializeUtil.Byte2String(bytes);
            }
        }

        // ?????????????????????byte[]????????????
        return SerializeUtil.Byte2String(bytes);

    }


    @Override
    public void onTerminate() {
        // ???????????????????????????
        Log.d(TAG, "onTerminate");
        super.onTerminate();
        if (AndMqtt.getInstance().getMqttClient().isConnected()) {
            try {
                AndMqtt.getInstance().getMqttClient().disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        unregisterReceiver(mReceiver);

    }

    public void setHuLvJingBao(List<CarCode> code) {
        //???????????????????????? ?????????????????????
        Map<String, Object> map = new HashMap<>();
        map.put("code", "03221");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getApp()).getAppToken());
        map.put("where", code);

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse>post(MESSAGE_URL)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(Response<AppResponse> response) {

                    }
                });
    }

    public class CarCode {
        String car_code;
    }

    public void sendRx(int strValue, String strContent) {
        Notice n = new Notice();
        n.type = strValue;
//                            n.content = message.toString();
        n.content = strContent;
        RxBus.getDefault().sendRx(n);
    }


}


