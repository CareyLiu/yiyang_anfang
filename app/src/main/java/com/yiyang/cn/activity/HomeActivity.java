package com.yiyang.cn.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.heytap.msp.push.HeytapPushManager;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.rairmmd.andmqtt.MqttSubscribe;
import com.tuya.smart.wrapper.api.TuyaWrapper;
import com.vivo.push.PushClient;
import com.yiyang.cn.R;
import com.yiyang.cn.util.Y;
import com.yiyang.cn.activity.zhinengjiaju.RenTiGanYingActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.LouShuiActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.MenCiActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.MenSuoActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.SosActivity;
import com.yiyang.cn.activity.zhinengjiaju.function.YanGanActivity;
import com.yiyang.cn.app.AppConfig;
import com.yiyang.cn.app.AppManager;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.MyApplication;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.dialog.newdia.TishiDialog;
import com.yiyang.cn.activity.a_yiyang.fragment.TabHomeFragment;
import com.yiyang.cn.activity.a_yiyang.fragment.TabShengxianFragment;
import com.yiyang.cn.activity.a_yiyang.fragment.TabWodeFragment;
import com.yiyang.cn.activity.a_yiyang.fragment.TabXiaoxiFragment;
import com.yiyang.cn.fragment.znjj.ZhiNengJiaJuFragment;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.DongTaiShiTiZhuangTaiModel;
import com.yiyang.cn.model.ZhiNengJiaJuNotifyJson;
import com.yiyang.cn.util.AppToast;
import com.yiyang.cn.util.SoundPoolUtils;
import com.yiyang.cn.util.YuYinChuLiTool;
import com.yiyang.cn.view.NoScrollViewPager;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.yiyang.cn.config.MyApplication.CAR_NOTIFY;
import static com.yiyang.cn.config.MyApplication.context;
import static com.yiyang.cn.config.MyApplication.getUser_id;
import static com.yiyang.cn.get_net.Urls.ZHINENGJIAJU;


public class HomeActivity extends BaseActivity {

    @BindView(R.id.vpg_content)
    NoScrollViewPager vpg_content;
    @BindView(R.id.iv_main_shouye)
    ImageView iv_main_shouye;
    @BindView(R.id.tv_main_shouye)
    TextView tv_main_shouye;
    @BindView(R.id.ll_main_shouye)
    LinearLayout ll_main_shouye;
    @BindView(R.id.iv_main_anfang)
    ImageView iv_main_anfang;
    @BindView(R.id.tv_main_anfang)
    TextView tv_main_anfang;
    @BindView(R.id.ll_main_anfang)
    LinearLayout ll_main_anfang;
    @BindView(R.id.iv_main_xiaoxi)
    ImageView iv_main_xiaoxi;
    @BindView(R.id.tv_main_xiaoxi)
    TextView tv_main_xiaoxi;
    @BindView(R.id.ll_main_xiaoxi)
    LinearLayout ll_main_xiaoxi;
    @BindView(R.id.iv_main_wode)
    ImageView iv_main_wode;
    @BindView(R.id.tv_main_wode)
    TextView tv_main_wode;
    @BindView(R.id.ll_main_wode)
    LinearLayout ll_main_wode;
    @BindView(R.id.iv_main_shengxian)
    ImageView iv_main_shengxian;
    @BindView(R.id.tv_main_shengxian)
    TextView tv_main_shengxian;
    @BindView(R.id.ll_main_shengxian)
    LinearLayout ll_main_shengxian;

    private List<String> roomList = new ArrayList<>();
    private List<String> deviceList = new ArrayList<>();

    private boolean isExit;
    private TishiDialog tishiDialog;

    private Runnable runnable;
    private Handler handler;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        StatusBarUtil.setLightMode(this);
        PushClient.getInstance(context).isSupport();
        HeytapPushManager.isSupportPush();

        TuyaWrapper.onLogin();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        initView(savedInstanceState);

        dognTaiShiTiUrl();

        initHandler();
        initHuidiao();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice notice) {
                if (notice.type == ConstanceValue.MSG_ZHINENGJIAJU) {
                    select(1);
                } else if (notice.type == ConstanceValue.MSG_GOTOXIAOXI) {
                    select(2);
                } else if (notice.type == ConstanceValue.MSG_P) {
                    handler.removeCallbacks(runnable);
                } else if (notice.type == ConstanceValue.MSG_ZHINENGJIAJU_MENCI) {
                    zhiNengJiaJuCaoZuo(notice);
                }
                if (notice.type == ConstanceValue.MSG_CAOZUODONGTAISHITI) {
                    dognTaiShiTiUrl();
                } else if (notice.type == ConstanceValue.MSG_XIUGAIDONGTAISHITIFINISH) {
                    xiuGaiDongTaiShiTiFinish();
                } else if (notice.type == ConstanceValue.MSG_YIYANG_ZNJJ_BACK) {
                    select(0);
                }
            }
        }));
    }

    private void zhiNengJiaJuCaoZuo(Notice notice) {
        if (tishiDialog != null && tishiDialog.isShowing()) {
            return;
        }
        /**
         / 00 ?????? 01.??? 02.?????? 03.?????? 04.?????? 05?????? 06.????????????(?????????????????????????????????????????????)
         / 07.?????????  08.?????? 09.????????? 10.?????? 11.???????????? 12.?????? 13.??????14.??????
         / 15.???????????? 16.?????? 17.??????(?????????????????????????????????????????????????????????) 18.?????????
         / 19.???????????? 20.??????????????? 21.?????????????????? 22.????????????????????? 23.???????????? 24.????????????
         / 25.???????????? 26.?????? 27???????????????????????? 28.???????????? 29.???????????? 30.????????????
         / 31.???????????? 32.???????????? 33.?????? 34.??????
         */
        ZhiNengJiaJuNotifyJson zhiNengJiaJuNotifyJson = (ZhiNengJiaJuNotifyJson) notice.content;
        ZhiNengJiaJuNotifyJson finalZhiNengJiaJuNotifyJson1 = zhiNengJiaJuNotifyJson;

        tishiDialog = new TishiDialog(mContext, 1, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {
            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("12")) {
                    MenCiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("11")) {
                    YanGanActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("15")) {
                    SosActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id(), true);
                } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("05")) {
                    MenSuoActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("13")) {
                    LouShuiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("34")) {
                    RenTiGanYingActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                }
                tishiDialog.dismiss();
            }

            @Override
            public void onDismiss(TishiDialog dialog) {
            }
        });


        if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("12")) {
            tishiDialog.setTextContent("??????????????????????????????????????????????????????????");
            //MenCiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("11")) {
            tishiDialog.setTextContent("???????????????????????????????????????????????????????????????????");
            //YanGanActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("15")) {
            tishiDialog.setTextContent("???????????????sos?????????????????????????????????????????????????");
            //SosActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id(), true);
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("05")) {
            tishiDialog.setTextContent("??????????????????????????????????????????????????????????");
            //MenSuoActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("13")) {
            tishiDialog.setTextContent("??????????????????????????????????????????????????????????");
            //LouShuiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("34")) {
            tishiDialog.setTextContent("????????????????????????????????????????????????????????????????");
            //RenTiGanYingActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
        } else {
            tishiDialog.setTextContent("?????????????????????????????????????????????????");
        }
        String simpleName = MyApplication.getApp().activity_main.getClass().getSimpleName();
        boolean menciFlag = !simpleName.equals(MenCiActivity.class.getSimpleName());
        boolean yanganFlag = !simpleName.equals(YanGanActivity.class.getSimpleName());
        boolean sosFlag = !simpleName.equals(SosActivity.class.getSimpleName());
        boolean loushuiFalg = !simpleName.equals(LouShuiActivity.class.getSimpleName());

        if (menciFlag && yanganFlag && sosFlag && loushuiFalg) {
            if (tishiDialog != null && !tishiDialog.isShowing()) {
                tishiDialog.show();
                String strBaoJingYin = PreferenceHelper.getInstance(mContext).getString(AppConfig.BAOJING_YANGAN, "2");
                if (strBaoJingYin.equals("0")) {

                } else {
                    SoundPoolUtils.soundPool(mContext, R.raw.baojingyin3);
                }
            }
        }
    }

    private void initHandler() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //???????????????
                    AndMqtt.getInstance().publish(new MqttPublish()
                            .setMsg("O.")
                            .setQos(2).setRetained(false)
                            .setTopic(CAR_NOTIFY), new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
                            Log.i("Rair", "??????O.??????");
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;????????????");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(runnable, 5000);

        if (AndMqtt.getInstance().isConnect()) {
            AndMqtt.getInstance().subscribe(new MqttSubscribe()
                    .setTopic("wit/server/01/" + getUser_id())
                    .setQos(2), new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        }
    }

    private void dognTaiShiTiUrl() {
        //???????????????????????? ?????????????????????
        Map<String, String> map = new HashMap<>();
        map.put("code", "16069");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        String str = PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, "");
        if (!StringUtils.isEmpty(str)) {
            map.put("family_id", PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, ""));
        } else {
            return;
        }
        Gson gson = new Gson();
        OkGo.<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>> response) {
                        roomList.clear();
                        deviceList.clear();

                        if (response.body().data.get(0).getRoom_list().size() > 0) {
                            for (int i = 0; i < response.body().data.get(0).getRoom_list().size(); i++) {
                                roomList.add(response.body().data.get(0).getRoom_list().get(i).getName());
                            }
                        }

                        if (response.body().data.get(0).getDevice_list().size() > 0) {
                            for (int i = 0; i < response.body().data.get(0).getDevice_list().size(); i++) {
                                deviceList.add(response.body().data.get(0).getDevice_list().get(i).getName());
                            }
                        }

                        String firstInstallDongTaiShiTi = PreferenceHelper.getInstance(mContext).getString(AppConfig.FIRSTINSTALLDONGTAISHITI, "1");

                        if (firstInstallDongTaiShiTi.equals("0")) {//?????????
                            if (!response.body().change_state.equals("1")) {//1.??????????????? 2.?????????
                                new YuYinChuLiTool(context, roomList, deviceList);
                            }
                        } else if (firstInstallDongTaiShiTi.equals("1")) {//??????
                            new YuYinChuLiTool(context, roomList, deviceList);
                        }
                        PreferenceHelper.getInstance(mContext).putString(AppConfig.FIRSTINSTALLDONGTAISHITI, "0");
                    }

                    @Override
                    public void onError(Response<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }
                });
    }

    private void xiuGaiDongTaiShiTiFinish() {
        //???????????????????????? ?????????????????????
        Map<String, String> map = new HashMap<>();
        map.put("code", "16070");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("family_id", PreferenceHelper.getInstance(mContext).getString(AppConfig.FAMILY_ID, ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>> response) {

                    }

                    @Override
                    public void onError(Response<AppResponse<DongTaiShiTiZhuangTaiModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }
                });
    }

    private void initView(Bundle savedInstanceState) {
        List<Fragment> fragments = new ArrayList<>(5);
        TabHomeFragment tabHomeFragment = new TabHomeFragment();
        ZhiNengJiaJuFragment tabAnfangFragment = new ZhiNengJiaJuFragment();
        TabShengxianFragment shengxianFragment = new TabShengxianFragment(savedInstanceState);
        TabXiaoxiFragment tabXiaoxiFragment = new TabXiaoxiFragment();
        TabWodeFragment tabWodeFragment = new TabWodeFragment();

        fragments.add(tabHomeFragment);
        fragments.add(tabAnfangFragment);
        fragments.add(tabXiaoxiFragment);
        fragments.add(tabWodeFragment);
        fragments.add(shengxianFragment);

        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        vpg_content.setOffscreenPageLimit(5);
        vpg_content.setScroll(false);
        vpg_content.setAdapter(adapter);
    }

    @OnClick({R.id.ll_main_shouye, R.id.ll_main_anfang, R.id.ll_main_shengxian, R.id.ll_main_xiaoxi, R.id.ll_main_wode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_shouye:
                select(0);
                break;
            case R.id.ll_main_anfang:
                select(1);
                break;
            case R.id.ll_main_shengxian:
                select(4);
                break;
            case R.id.ll_main_xiaoxi:
                select(2);
                break;
            case R.id.ll_main_wode:
                select(3);
                break;
        }
    }

    private void select(int item) {
        vpg_content.setCurrentItem(item);
        iv_main_shouye.setImageResource(R.mipmap.yiyang_main_shouye_nor);
        iv_main_anfang.setImageResource(R.mipmap.yiyang_main_anfang_nor);
        iv_main_shengxian.setImageResource(R.mipmap.yiyang_main_shengxian_nor);
        iv_main_xiaoxi.setImageResource(R.mipmap.yiyang_main_xiaoxi_nor);
        iv_main_wode.setImageResource(R.mipmap.yiyang_main_wd_nor);

        tv_main_shouye.setTextColor(Y.getColor(R.color.color_3));
        tv_main_anfang.setTextColor(Y.getColor(R.color.color_3));
        tv_main_shengxian.setTextColor(Y.getColor(R.color.color_3));
        tv_main_xiaoxi.setTextColor(Y.getColor(R.color.color_3));
        tv_main_wode.setTextColor(Y.getColor(R.color.color_3));

        switch (item) {
            case 0:
                iv_main_shouye.setImageResource(R.mipmap.yiyang_main_shouye_sel);
                tv_main_shouye.setTextColor(Y.getColor(R.color.color_main_yiyang));
                break;
            case 1:
                iv_main_anfang.setImageResource(R.mipmap.yiyang_main_anfang_sel);
                tv_main_anfang.setTextColor(Y.getColor(R.color.color_main_yiyang));
                break;
            case 4:
                iv_main_shengxian.setImageResource(R.mipmap.yiyang_main_shengxian_sel);
                tv_main_shengxian.setTextColor(Y.getColor(R.color.color_main_yiyang));
                break;
            case 2:
                iv_main_xiaoxi.setImageResource(R.mipmap.yiyang_main_xiaoxi_sel);
                tv_main_xiaoxi.setTextColor(Y.getColor(R.color.color_main_yiyang));
                break;
            case 3:
                iv_main_wode.setImageResource(R.mipmap.yiyang_main_wd_sel);
                tv_main_wode.setTextColor(Y.getColor(R.color.color_main_yiyang));
                break;
        }
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                AppToast.makeShortToast(this, "???????????????????????????");
                isExit = true;
                new Thread() {
                    public void run() {
                        SystemClock.sleep(3000);
                        isExit = false;
                    }

                }.start();
                return true;
            }
            AppManager.getAppManager().finishAllActivity();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
