package com.smarthome.magic.activity.zhinengjiaju.peinet;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.espressif.iot.esptouch.EsptouchTask;
import com.espressif.iot.esptouch.IEsptouchResult;
import com.espressif.iot.esptouch.IEsptouchTask;
import com.espressif.iot.esptouch.util.ByteUtil;
import com.espressif.iot.esptouch.util.TouchNetUtil;
import com.flyco.roundview.RoundLinearLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.zhinengjiaju.peinet.v1.EspTouchActivity;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.MyApplication;
import com.smarthome.magic.config.PreferenceHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ZhiNengJiaJuPeiWangActivity extends EspTouchActivityAbsBase {

    private static final String TAG = EspTouchActivity.class.getSimpleName();
    private static final int REQUEST_PERMISSION = 0x01;
    @BindView(R.id.tv_huashu1)
    TextView tvHuashu1;
    @BindView(R.id.ll_huashu2)
    LinearLayout llHuashu2;
    @BindView(R.id.tv_huashu3)
    TextView tvHuashu3;
    @BindView(R.id.tv_wifi_ming)
    TextView tvWifiMing;
    @BindView(R.id.ll_wifi_name)
    LinearLayout llWifiName;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.iv_icon_mima)
    ImageView ivIconMima;

    @BindView(R.id.iv_see_mima)
    ImageView ivSeeMima;
    @BindView(R.id.ll_wifi_mima)
    RelativeLayout llWifiMima;
    @BindView(R.id.view_line_1)
    View viewLine1;
    @BindView(R.id.tv_huashu4)
    TextView tvHuashu4;
    @BindView(R.id.rll_kaishilianjie)
    RoundLinearLayout rllKaishilianjie;
    @BindView(R.id.et_wifi_mima)
    EditText etWifiMima;
    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.shebeilianjie)
    TextView shebeilianjie;
    @BindView(R.id.tv_peiduiguochegn)
    TextView tvPeiduiguochegn;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.cl_shebei_lianjie)
    ConstraintLayout clShebeiLianjie;
    @BindView(R.id.cl_zhanghaomima)
    ConstraintLayout clZhanghaomima;
    @BindView(R.id.iv_shibai)
    ImageView ivShibai;
    @BindView(R.id.tv_shibai)
    TextView tvShibai;
    @BindView(R.id.tv_shibaiyuanyin)
    TextView tvShibaiyuanyin;
    @BindView(R.id.ll_1234)
    LinearLayout ll1234;
    @BindView(R.id.rll_kaishilianjie_shibaihou)
    RoundLinearLayout rllKaishilianjieShibaihou;
    @BindView(R.id.cl_shibai)
    ConstraintLayout clShibai;
    @BindView(R.id.iv_peiwang_mima)
    ImageView ivPeiwangMima;
    @BindView(R.id.shibai_bangzhu)
    TextView shibaiBangzhu;


    public EsptouchAsyncTask4 mTask;

    @Override
    protected String getEspTouchVersion() {
        return getString(R.string.esptouch1_about_version, IEsptouchTask.ESPTOUCH_VERSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onWifiChanged();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.esptouch1_location_permission_title)
                        .setMessage(R.string.esptouch1_location_permission_message)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.ok, (dialog, which) -> finish())
                        .show();
            }

            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            requestPermissions(permissions, REQUEST_PERMISSION);
        }

        MyApplication.getInstance().observeBroadcast(this, broadcast -> {
            Log.d(TAG, "onCreate: Broadcast=" + broadcast);
            onWifiChanged();
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        rllKaishilianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.isEmpty(etWifiMima.getText().toString())) {
                    setZhuangTaiZhanShi("2");
                    executeEsptouch();
                } else {
                    UIHelper.ToastMessage(mContext, "请输入您的配网密码");
                }
            }
        });

        ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_buxianshimima);

        ivSeeMima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (seeMiMa.equals("0")) {
                    seeMiMa = "1";
                    ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_xianshimima);
                    etWifiMima.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    seeMiMa = "0";
                    ivSeeMima.setBackgroundResource(R.mipmap.peiwang_icon_buxianshimima);
                    etWifiMima.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        animationView.setAnimation("lottie.json");
        animationView.setImageAssetsFolder("images/");
        animationView.loop(true);
        animationView.playAnimation();

        setZhuangTaiZhanShi("1");
        String wifiMiMa = PreferenceHelper.getInstance(mContext).getString(AppConfig.PEIWANG_MIMA, "");
        if (!StringUtils.isEmpty(wifiMiMa)) {
            etWifiMima.setText(wifiMiMa);
        }

        rllKaishilianjieShibaihou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setZhuangTaiZhanShi("1");
            }
        });
        shibaiBangzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeiWangHelpActivity.actionStart(mContext);
            }
        });
    }

    private String seeMiMa = "0";//0 隐藏 1显示

    private void executeEsptouch() {

        byte[] ssid = ByteUtil.getBytesByString(mSsid.toString());
        CharSequence pwdStr = etWifiMima.getText().toString();


        byte[] password = pwdStr == null ? null : ByteUtil.getBytesByString(pwdStr.toString());
        byte[] bssid = TouchNetUtil.parseBssid2bytes(mBssid.toString());
        CharSequence devCountStr = String.valueOf(1);
        byte[] deviceCount = devCountStr == null ? new byte[0] : devCountStr.toString().getBytes();
        byte[] broadcast = {1};

        if (mTask != null) {
            mTask.cancelEsptouch();
        }
        mTask = new EsptouchAsyncTask4(this);
        mTask.execute(ssid, bssid, password, deviceCount, broadcast);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhi_neng_jia_ju_pei_wang;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("配网");
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
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ZhiNengJiaJuPeiWangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private class EsptouchAsyncTask4 extends AsyncTask<byte[], IEsptouchResult, List<IEsptouchResult>> {
        private WeakReference<ZhiNengJiaJuPeiWangActivity> mActivity;

        private final Object mLock = new Object();
        private ProgressDialog mProgressDialog;
        private AlertDialog mResultDialog;
        private IEsptouchTask mEsptouchTask;

        EsptouchAsyncTask4(ZhiNengJiaJuPeiWangActivity activity) {
            mActivity = new WeakReference<>(activity);

        }

        public void cancelEsptouch() {
            cancel(true);
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
            if (mResultDialog != null) {
                mResultDialog.dismiss();
            }
            if (mEsptouchTask != null) {
                mEsptouchTask.interrupt();
            }
        }

        @Override
        protected void onPreExecute() {
            ZhiNengJiaJuPeiWangActivity activity = mActivity.get();
            activity.setZhuangTaiZhanShi("2");


            mProgressDialog = new ProgressDialog(activity);
//            mProgressDialog.setMessage(activity.getString(R.string.esptouch1_configuring_message));
//            mProgressDialog.setCanceledOnTouchOutside(false);
//            mProgressDialog.setOnCancelListener(dialog -> {
//                synchronized (mLock) {
//                    if (mEsptouchTask != null) {
//                        mEsptouchTask.interrupt();
//                    }
//                }
//            });
//            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, activity.getText(android.R.string.cancel),
//                    (dialog, which) -> {
//                        synchronized (mLock) {
//                            if (mEsptouchTask != null) {
//                                mEsptouchTask.interrupt();
//                            }
//                        }
//                    });
//            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(IEsptouchResult... values) {
            Log.i("ZhiNengJiaJuPeiWang", "onProgressUpdate" + values[0].getBssid() + values[0].getInetAddress());
            Activity context = mActivity.get();
            if (context != null) {
//                mResultDialog = new AlertDialog.Builder(activity)
//                        .setTitle(R.string.esptouch1_configure_result_success)
//                        //.setItems(resultMsgList.toArray(items), null)
//                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                PreferenceHelper.getInstance(activity).putString(AppConfig.PEIWANG_MIMA, activity.etWifiMima.getText().toString());
//                                mTask.mResultDialog.dismiss();
//                                activity.finish();
//
//                            }
//                        })
//                        .show();
//                mResultDialog.setCanceledOnTouchOutside(false);
                Toast.makeText(context, "配网成功", Toast.LENGTH_SHORT).show();
                context.finish();
            }

        }

        @Override
        protected List<IEsptouchResult> doInBackground(byte[]... params) {
            ZhiNengJiaJuPeiWangActivity activity = mActivity.get();
            int taskResultCount;
            synchronized (mLock) {
                byte[] apSsid = params[0];
                byte[] apBssid = params[1];
                byte[] apPassword = params[2];
                byte[] deviceCountData = params[3];
                byte[] broadcastData = params[4];
                taskResultCount = deviceCountData.length == 0 ? -1 : Integer.parseInt(new String(deviceCountData));
                Context context = activity.getApplicationContext();
                mEsptouchTask = new EsptouchTask(apSsid, apBssid, apPassword, context);
                mEsptouchTask.setPackageBroadcast(broadcastData[0] == 1);
                mEsptouchTask.setEsptouchListener(this::publishProgress);
            }
            return mEsptouchTask.executeForResults(taskResultCount);
        }

        @Override
        protected void onPostExecute(List<IEsptouchResult> result) {
            ZhiNengJiaJuPeiWangActivity activity = mActivity.get();
            Log.i("ZhiNengJiaJuPeiWang", "onProgressUpdate" + result.get(0).getBssid() + result.get(0).getInetAddress());
            activity.mTask = null;
            mProgressDialog.dismiss();
            if (result == null) {
                mResultDialog = new AlertDialog.Builder(activity)
                        .setMessage(R.string.esptouch1_configure_result_failed_port)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
                mResultDialog.setCanceledOnTouchOutside(false);
                return;
            }

            // check whether the task is cancelled and no results received
            IEsptouchResult firstResult = result.get(0);
            if (firstResult.isCancelled()) {
                return;
            }
            // the task received some results including cancelled while
            // executing before receiving enough results

            if (!firstResult.isSuc()) {
//                mResultDialog = new AlertDialog.Builder(activity)
//                        .setMessage(R.string.esptouch1_configure_result_failed)
//                        .setPositiveButton(android.R.string.ok, null)
//                        .show();
//                mResultDialog.setCanceledOnTouchOutside(false);

                activity.setZhuangTaiZhanShi("0");
                return;
            }

            ArrayList<CharSequence> resultMsgList = new ArrayList<>(result.size());
            for (IEsptouchResult touchResult : result) {
                String message = activity.getString(R.string.esptouch1_configure_result_success_item,
                        touchResult.getBssid(), touchResult.getInetAddress().getHostAddress());
                resultMsgList.add(message);
            }
            CharSequence[] items = new CharSequence[resultMsgList.size()];
            mResultDialog = new AlertDialog.Builder(activity)
                    .setTitle(R.string.esptouch1_configure_result_success)
                    //.setItems(resultMsgList.toArray(items), null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PreferenceHelper.getInstance(activity).putString(AppConfig.PEIWANG_MIMA, activity.etWifiMima.getText().toString());
                            mEsptouchTask.interrupt();

                            if (mResultDialog.isShowing()) {
                                mResultDialog.dismiss();
                            }
                            activity.finish();

                        }
                    })
                    .show();
            mResultDialog.setCanceledOnTouchOutside(false);


        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    CharSequence message;
    CharSequence mSsid;
    byte[] ssidBytes;
    CharSequence mBssid;

    private void onWifiChanged() {
        StateResult stateResult = check();

        message = stateResult.message;
        mSsid = stateResult.ssid;
        ssidBytes = stateResult.ssidBytes;
        mBssid = stateResult.bssid;


        if (mSsid != null || mSsid.length() != 0) {
            tvWifiMing.setText(mSsid.toString());
        }

        if (stateResult.wifiConnected) {
            if (stateResult.is5G) {
                // mViewModel.message = getString(R.string.esptouch1_wifi_5g_message);
                UIHelper.ToastMessage(mContext, R.string.esptouch1_wifi_5g_message);
            }
        } else {
            if (mTask != null) {
                mTask.cancelEsptouch();
                mTask = null;
                new AlertDialog.Builder(mContext)
                        .setMessage(R.string.esptouch1_configure_wifi_change_message)
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
            }
        }
    }

    private StateResult check() {
        StateResult result = checkPermission();
        if (!result.permissionGranted) {
            return result;
        }
        result = checkLocation();
        result.permissionGranted = true;
        if (result.locationRequirement) {
            return result;
        }
        result = checkWifi();
        result.permissionGranted = true;
        result.locationRequirement = false;
        return result;
    }

    /**
     * @param str 0连接失败 1开始连接页面 2连接中
     */
    private void setZhuangTaiZhanShi(String str) {
        if (str.equals("0")) {
            clShibai.setVisibility(View.VISIBLE);
            clShebeiLianjie.setVisibility(View.GONE);
            clZhanghaomima.setVisibility(View.GONE);
        } else if (str.equals("1")) {
            clShibai.setVisibility(View.GONE);
            clShebeiLianjie.setVisibility(View.GONE);
            clZhanghaomima.setVisibility(View.VISIBLE);
        } else if (str.equals("2")) {
            clShibai.setVisibility(View.GONE);
            clShebeiLianjie.setVisibility(View.VISIBLE);
            clZhanghaomima.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTask != null) {
            mTask.cancelEsptouch();
        }
    }
}
