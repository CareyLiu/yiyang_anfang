package com.smarthome.magic.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.jaeger.library.StatusBarUtil;
import com.rairmmd.andmqtt.AndMqtt;
import com.rairmmd.andmqtt.MqttPublish;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.gaiban.HomeFragment_New;
import com.smarthome.magic.activity.zhinengjiaju.function.LouShuiActivity;
import com.smarthome.magic.activity.zhinengjiaju.function.MenCiActivity;
import com.smarthome.magic.activity.zhinengjiaju.function.MenSuoActivity;
import com.smarthome.magic.activity.zhinengjiaju.function.SosActivity;
import com.smarthome.magic.activity.zhinengjiaju.function.YanGanActivity;
import com.smarthome.magic.app.AppManager;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.config.AudioFocusManager;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_Notify;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.fragment.MessagerFragment;
import com.smarthome.magic.fragment.MineFragment;
import com.smarthome.magic.fragment.OnlineFragment;
import com.smarthome.magic.fragment.ZhiNengJiaJuFragment;
import com.smarthome.magic.model.AlarmClass;
import com.smarthome.magic.model.ZhiNengJiaJuNotifyJson;
import com.smarthome.magic.util.AppToast;
import com.smarthome.magic.util.SoundPoolUtils;
import com.smarthome.magic.view.NoScrollViewPager;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.config.MyApplication.CAR_NOTIFY;
import static com.smarthome.magic.config.MyApplication.getAppContext;


public class HomeActivity extends BaseActivity {


    BottomNavigationViewEx mBnve;
    @BindView(R.id.vp)
    NoScrollViewPager mVp;
    @BindView(R.id.activity_with_view_pager)
    RelativeLayout activityWithViewPager;
    @BindView(R.id.layout_bg)
    LinearLayout layoutBg;
    private boolean isExit;

    private SparseIntArray items;
    AlarmClass alarmClass;
    private int i = 0;
    TishiDialog tishiDialog;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightMode(this);
        ButterKnife.bind(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        mBnve = findViewById(R.id.bnve);
        initView();
        initData();
        initEvent();

        com.smarthome.magic.app.AppManager.getAppManager().addActivity(this);

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice notice) {
                if (notice.type == ConstanceValue.MSG_GUZHANG_SHOUYE) {

                    String message = (String) notice.content;
                    Gson gson = new Gson();
                    alarmClass = gson.fromJson(message.toString(), AlarmClass.class);


                    Log.i("alarmClass", alarmClass.changjia_name + alarmClass.sell_phone);
//
//                    if (player != null) {
//                        player.stop();
//                        player.release();
//                        audioFocusManage.releaseTheAudioFocus();
//                        player = null;
//                    }

                    if (alarmClass.type.equals("1")) {
                        switch (alarmClass.sound) {

                            case "chSound1.mp3":
                                // SoundPoolUtils.soundPool(mContext,R.raw.ch_sound1);
                                playMusic(R.raw.ch_sound1);
                                break;
                            case "chSound2.mp3":
                                playMusic(R.raw.ch_sound2);
                                break;
                            case "chSound3.mp3":
                                playMusic(R.raw.ch_sound3);
                                break;
                            case "chSound4.mp3":
                                playMusic(R.raw.ch_sound4);
                                break;
                            case "chSound5.mp3":
                                playMusic(R.raw.ch_sound5);
                                break;
                            case "chSound6.mp3":
                                playMusic(R.raw.ch_sound6);
                                break;
                            case "chSound8.mp3":
                                playMusic(R.raw.ch_sound8);
                                break;
                            case "chSound9.mp3":
                                playMusic(R.raw.ch_sound9);
                                break;
                            case "chSound10.mp3":
                                playMusic(R.raw.ch_sound10);
                                break;
                            case "chSound11.mp3":
                                playMusic(R.raw.ch_sound11);
                                break;
                            case "chSound18.mp3":
                                playMusic(R.raw.ch_sound18);
                                break;
                        }
                    } else if (alarmClass.type.equals("5")) {
                        if (alarmClass.code.equals("jyj_0006")) {
                            tishiDialog = new TishiDialog(mContext, 1, new TishiDialog.TishiDialogListener() {
                                @Override
                                public void onClickCancel(View v, TishiDialog dialog) {
                                }

                                @Override
                                public void onClickConfirm(View v, TishiDialog dialog) {
                                    if (alarmClass.device_type.equals("12")) {
                                        MenCiActivity.actionStart(mContext, alarmClass.device_id);
                                    } else if (alarmClass.device_type.equals("11")) {
                                        YanGanActivity.actionStart(mContext, alarmClass.device_id);
                                    } else if (alarmClass.device_type.equals("15")) {
                                        SosActivity.actionStart(mContext, alarmClass.device_id);
                                        SoundPoolUtils.soundPool(mContext, R.raw.baojingyin_1);
                                    } else if (alarmClass.device_type.equals("05")) {
                                        MenSuoActivity.actionStart(mContext, alarmClass.device_id);
                                    } else if (alarmClass.device_type.equals("13")) {
                                        LouShuiActivity.actionStart(mContext, alarmClass.device_id);
                                    }
                                }

                                @Override
                                public void onDismiss(TishiDialog dialog) {

                                }
                            });
                            tishiDialog.setTextContent("您的家庭有新的状况，是否前去查看?");
                            tishiDialog.show();

                        }
                    }

                } else if (notice.type == ConstanceValue.MSG_GOTOXIAOXI) {
                    mVp.setCurrentItem(3, false);
                } else if (notice.type == ConstanceValue.MSG_P) {
                    handler.removeCallbacks(runnable);
                } else if (notice.type == ConstanceValue.MSG_ZHINENGJIAJU) {
                    mVp.setCurrentItem(1, false);
                } else if (notice.type == ConstanceValue.MSG_ZHINENGJIAJU_MENCI) {
                    if (tishiDialog != null && tishiDialog.isShowing()) {
                        return;
                    }
                    /**
                     / 00 主机 01.灯 02.插座 03.喂鱼 04.浇花 05门锁 06.空调电视(开关，加风，减风，讯飞语音配置)
                     / 07.车库门  08.开关 09.晾衣架 10.窗磁 11.烟雾报警 12.门磁 13.漏水14.雷达
                     / 15.紧急开关 16.窗帘 17.电视(开关，加减音量，加减亮暗，讯飞语音配置) 18.摄像头
                     / 19.空气检测 20.温湿度检测 21.煤气管道关闭 22.自来水管道关闭 23.宠物喂食 24.宠物喂水
                     / 25.智能手环 26.排风 27背景音乐显示控制 28.电视遥控 29.空气净化 30.体质检测
                     / 31.光敏控制 32.燃气报警 33.风扇 34.雷达
                     */
                    ZhiNengJiaJuNotifyJson zhiNengJiaJuNotifyJson = new ZhiNengJiaJuNotifyJson();
                    zhiNengJiaJuNotifyJson = (ZhiNengJiaJuNotifyJson) notice.content;
                    ZhiNengJiaJuNotifyJson finalZhiNengJiaJuNotifyJson = zhiNengJiaJuNotifyJson;
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
                                SosActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                            } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("05")) {
                                MenSuoActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                            } else if (finalZhiNengJiaJuNotifyJson1.getDevice_type().equals("13")) {
                                LouShuiActivity.actionStart(mContext, finalZhiNengJiaJuNotifyJson1.getDevice_id());
                            }
                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });
                    tishiDialog.setTextContent("您的家庭有新的状况，是否前去查看?");
                    tishiDialog.show();
                }
            }
        }));


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                AndMqtt.getInstance().publish(new MqttPublish()
                        .setMsg("O.")
                        .setQos(2).setRetained(false)
                        .setTopic(CAR_NOTIFY), new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        Log.i("Rair", "订阅O.成功");

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Log.i("Rair", "(MainActivity.java:84)-onFailure:-&gt;发布失败");
                    }
                });
                handler.postDelayed(this, 5000);
            }
        };

        handler.postDelayed(runnable, 5000);
    }

    public MediaPlayer player;
    public AudioFocusManager audioFocusManage;
    public int position;
    Runnable runnable;

    public void playMusic(int res) {
        boolean flag = false;

        Activity currentActivity = AppManager.getAppManager().currentActivity();
        if (currentActivity != null) {
            if (!currentActivity.getClass().getSimpleName().equals(DiagnosisActivity.class.getSimpleName())) {
                MyCarCaoZuoDialog_Notify myCarCaoZuoDialog_notify = new MyCarCaoZuoDialog_Notify(getAppContext(), new MyCarCaoZuoDialog_Notify.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {
                        // player.stop();
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }

                    }

                    @Override
                    public void clickRight() {
                        DiagnosisActivity.actionStart(HomeActivity.this, alarmClass);
                        //SoundPoolUtils.soundPool.release();
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }

                    }
                }
                );

                myCarCaoZuoDialog_notify.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
                myCarCaoZuoDialog_notify.show();
                myCarCaoZuoDialog_notify.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (SoundPoolUtils.soundPool != null) {
                            SoundPoolUtils.soundPool.release();
                        }
                    }
                });

            } else {
                flag = true;
            }
        }

        if (flag) {
            return;
        }

        SoundPoolUtils.soundPool(mContext, res);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                AppToast.makeShortToast(this, "再按一次返回键退出");
                isExit = true;
                new Thread() {
                    public void run() {
                        SystemClock.sleep(3000);
                        isExit = false;
                    }

                }.start();
                return true;
            }
//            AppManager.AppExit();
            AppManager.getAppManager().finishAllActivity();
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void finish() {
        super.finish();
    }


    /**
     * change BottomNavigationViewEx style
     */
    private void initView() {


        mBnve.enableAnimation(false);
        mBnve.enableShiftingMode(false);
        mBnve.enableItemShiftingMode(false);
    }

    /**
     * create fragments
     */
    private void initData() {
        List<Fragment> fragments = new ArrayList<>(5);
        items = new SparseIntArray(5);

        HomeFragment_New homeFragment = new HomeFragment_New();
        ZhiNengJiaJuFragment zhiNengJiaJuFragment = new ZhiNengJiaJuFragment();
        OnlineFragment onlineFragment = new OnlineFragment();
        MessagerFragment messagerFragment = new MessagerFragment();
        MineFragment mineFragment = new MineFragment();
        fragments.add(homeFragment);
        //   fragments.add(playerFragment);
        fragments.add(zhiNengJiaJuFragment);
        fragments.add(onlineFragment);
        fragments.add(messagerFragment);
        fragments.add(mineFragment);
//
        items.put(R.id.i_home, 0);
        items.put(R.id.i_zhinengjiaju, 1);
        items.put(R.id.i_car_online, 2);
        items.put(R.id.i_message, 3);
        items.put(R.id.i_mine, 4);

        // set adapter
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        //禁用懒加载，不然每次切换页面都会重新获取数据
        mVp.setOffscreenPageLimit(4);
        //viewPage禁止滑动
        mVp.setScroll(false);
        mVp.setAdapter(adapter);
    }

    /**
     * set listeners
     */
    private void initEvent() {
        // set listener to change the current item of view pager when click bottom nav item
        mBnve.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int position = items.get(item.getItemId());
                //   if (position == 3) {
                //  layoutBg.setBackground(getResources().getDrawable(R.color.app_bg));
                // StatusBarUtil.setDarkMode(HomeActivity.this);
                //    } else {
                //   layoutBg.setBackground(getResources().getDrawable(R.color.white));
                //   StatusBarUtil.setLightMode(HomeActivity.this);
                // }

                if (previousPosition != position) {
                    previousPosition = position;
                    mVp.setCurrentItem(position, false);
                }
                return true;
            }
        });

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBnve.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private boolean flag = true;
    Handler handler;

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();

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


    public static HomeActivity getInstance() {
        return new HomeActivity();
    }


}
