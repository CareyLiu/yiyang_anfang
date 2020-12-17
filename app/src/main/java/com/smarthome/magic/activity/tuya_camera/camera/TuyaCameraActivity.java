package com.smarthome.magic.activity.tuya_camera.camera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_camera.camera.adapter.TuyaKongzhiAdapter;
import com.smarthome.magic.activity.tuya_camera.camera.model.TuyaKongzhiModel;
import com.smarthome.magic.activity.tuya_camera.dialog.TishiNewDialog;
import com.smarthome.magic.activity.tuya_camera.utils.TuyaDeviceManager;
import com.smarthome.magic.activity.tuya_camera.utils.TuyaDialogUtils;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.config.PreferenceHelper;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.tuya.smart.camera.camerasdk.typlayer.callback.AbsP2pCameraListener;
import com.tuya.smart.camera.camerasdk.typlayer.callback.OperationDelegateCallBack;
import com.tuya.smart.camera.middleware.p2p.ITuyaSmartCameraP2P;
import com.tuya.smart.camera.middleware.p2p.TuyaSmartCameraP2PFactory;
import com.tuya.smart.camera.middleware.widget.AbsVideoViewCallback;
import com.tuya.smart.camera.middleware.widget.TuyaCameraView;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;
import com.tuya.smart.sdk.api.IResultCallback;
import com.tuya.smart.sdk.api.ITuyaDevice;
import com.tuya.smart.sdk.api.WifiSignalListener;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuyasmart.camera.devicecontrol.ITuyaCameraDevice;
import com.tuyasmart.camera.devicecontrol.TuyaCameraDeviceControlSDK;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class TuyaCameraActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    //    @BindView(R.id.camera_video_view)
    TuyaCameraView mVideoView;
    @BindView(R.id.rv_kongzhi)
    RecyclerView rv_kongzhi;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.bt_up)
    RelativeLayout bt_up;
    @BindView(R.id.bt_down)
    RelativeLayout bt_down;
    @BindView(R.id.bt_left)
    RelativeLayout bt_left;
    @BindView(R.id.bt_right)
    RelativeLayout bt_right;
    @BindView(R.id.ll_fangxiang)
    LinearLayout ll_fangxiang;
    @BindView(R.id.iv_switch_shengyin)
    ImageView iv_switch_shengyin;
    @BindView(R.id.iv_switch_quanping)
    ImageView iv_switch_quanping;
    @BindView(R.id.tv_switch_qingxidu)
    TextView tv_switch_qingxidu;
    @BindView(R.id.tv_xinhao)
    TextView tv_xinhao;
    @BindView(R.id.fl_camera)
    FrameLayout fl_camera;
    @BindView(R.id.ll_main)
    LinearLayout ll_main;
    @BindView(R.id.fl_quanping)
    FrameLayout fl_quanping;
    @BindView(R.id.bt_chonglian)
    TextView bt_chonglian;
    @BindView(R.id.iv_switch_shengyin_qp)
    ImageView iv_switch_shengyin_qp;
    @BindView(R.id.iv_switch_quanping_qp)
    ImageView iv_switch_quanping_qp;
    @BindView(R.id.tv_switch_qingxidu_qp)
    TextView tv_switch_qingxidu_qp;
    @BindView(R.id.rl_quanping)
    RelativeLayout rl_quanping;

    private int p2pType;
    private DeviceBean mDeviceBeen;
    private ITuyaDevice mDevice;
    private ITuyaSmartCameraP2P mCameraP2P;

    private List<TuyaKongzhiModel> kongzhis = new ArrayList<>();
    private TuyaKongzhiAdapter adapter;
    private int kongzhiPositton;

    private boolean isKaiqishengyin;
    private String qingxidu;
    private ITuyaCameraDevice mTuyaCameraDevice;
    private boolean isOpenFangxiang;
    private boolean isPlay;
    private boolean isOnline;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String member_type, String device_id, String ty_device_ccid, String old_name, String room_name) {
        Intent intent = new Intent(context, TuyaCameraActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("member_type", member_type);
        intent.putExtra("device_id", device_id);
        intent.putExtra("ty_device_ccid", ty_device_ccid);
        intent.putExtra("old_name", old_name);
        intent.putExtra("room_name", room_name);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("摄像头");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        iv_rightTitle.setVisibility(View.VISIBLE);
        iv_rightTitle.setImageResource(R.mipmap.mine_shezhi);
        iv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set();
            }
        });
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpenFangxiang) {
                    closeFangxiang();
                } else {
                    finish();
                }
            }
        });
    }

    private void set() {//移除设备
        CameraSetActivity.actionStart(mContext,
                getIntent().getStringExtra("member_type"),
                getIntent().getStringExtra("device_id"),
                getIntent().getStringExtra("old_name"),
                getIntent().getStringExtra("room_name")
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
        initAdapter();
        initHuidiao();
        intHomeInfo();
    }

    private void intHomeInfo() {
        String ty_device_ccid = getIntent().getStringExtra("ty_device_ccid");
        long homeId = PreferenceHelper.getInstance(mContext).getLong(AppConfig.TUYA_HOME_ID, 0);
        TuyaHomeSdk.newHomeInstance(homeId).getHomeDetail(new ITuyaHomeResultCallback() {
            @Override
            public void onSuccess(HomeBean bean) {
                List<DeviceBean> deviceList = bean.getDeviceList();
                for (int i = 0; i < deviceList.size(); i++) {
                    DeviceBean deviceBean = deviceList.get(i);
                    if (ty_device_ccid.equals(deviceBean.getDevId())) {
                        TuyaDeviceManager.getDeviceManager().initDevice(deviceBean);
                        initCamera();
                        return;
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                TuyaDialogUtils.t(mContext, "获取设备信息失败");
            }
        });
    }

    private void lixianle() {
        bt_chonglian.setVisibility(View.VISIBLE);
        dissCameraConnect();
        TuyaDialogUtils.t(mContext, "设备已离线，请检查设备网络！", new TishiNewDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiNewDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiNewDialog dialog) {

            }

            @Override
            public void onDismiss(TishiNewDialog dialog) {
            }
        });
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_DEVICE_DATA) {
                    if (message.devId.equals(mDeviceBeen.getDevId())) {
                        getData((String) message.content);
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_REMOVED) {
                    String devId = TuyaDeviceManager.getDeviceManager().getDeviceBeen().getDevId();
                    String ccc = message.devId;
                    if (ccc.equals(devId)) {
                        TishiNewDialog dialog = new TishiNewDialog(mContext, new TishiNewDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiNewDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiNewDialog dialog) {

                            }

                            @Override
                            public void onDismiss(TishiNewDialog dialog) {
                                finish();
                            }
                        });
                        dialog.setTextCont("设备已被移除");
                        dialog.setTextConfirm("确定");
                        dialog.setTextCancel("");
                        dialog.show();
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_STATUSCHANGED) {
                    if (message.devId.equals(mDeviceBeen.getDevId())) {
                        isOnline = (boolean) message.content;
                        if (!isOnline) {
                            lixianle();
                        }
                    }
                } else if (message.type == ConstanceValue.MSG_DEVICE_NETWORKSTATUSCHANGED) {


                } else if (message.type == ConstanceValue.MSG_DEVICE_DEVINFOUPDATE) {


                } else if (message.type == ConstanceValue.MSG_CAMERA_FAIL) {
                    TuyaDialogUtils.t(mContext, (String) message.content);
                }
            }
        }));
    }

    private void init() {
        bt_up.setOnClickListener(this);
        bt_down.setOnClickListener(this);
        bt_left.setOnClickListener(this);
        bt_right.setOnClickListener(this);
        bt_up.setOnTouchListener(this);
        bt_down.setOnTouchListener(this);
        bt_left.setOnTouchListener(this);
        bt_right.setOnTouchListener(this);
    }

    private void initCamera() {
        p2pType = TuyaDeviceManager.getDeviceManager().getP2pType();
        mDeviceBeen = TuyaDeviceManager.getDeviceManager().getDeviceBeen();
        mDevice = TuyaDeviceManager.getDeviceManager().getDevice();
        mTuyaCameraDevice = TuyaCameraDeviceControlSDK.getCameraDeviceInstance(mDeviceBeen.getDevId());
        mCameraP2P = TuyaSmartCameraP2PFactory.createCameraP2P(p2pType, mDeviceBeen.getDevId());
        mVideoView = new TuyaCameraView(mContext);
        mVideoView.setViewCallback(new AbsVideoViewCallback() {
            @Override
            public void onCreated(Object o) {
                super.onCreated(o);
                //渲染视图构造完成时回调
                if (null != mCameraP2P) {
                    mCameraP2P.generateCameraView(o);
                }
            }

            @Override
            public void videoViewClick() {
                super.videoViewClick();
                //点击视图时回调
            }

            @Override
            public void startCameraMove(int cameraDirection) {
                super.startCameraMove(cameraDirection);
                //触发视图滑动操作时回调
                //滑动方向。"0"代表上，"2"代表右，"4"代表下，"6"代表左
            }

            @Override
            public void onActionUP() {
                super.onActionUP();
                //点击视图后手指抬起时回调
            }
        });

        mVideoView.createVideoView(p2pType);
        mCameraP2P.registerP2PCameraListener(new AbsP2pCameraListener() {
            @Override
            public void onSessionStatusChanged(Object o, int i, int i1) {
                super.onSessionStatusChanged(o, i, i1);
            }
        });

        fl_camera.addView(mVideoView);
        cameraConnect();
        isPlay = true;
    }

    private void initAdapter() {
        kongzhis.add(new TuyaKongzhiModel(R.mipmap.tuya_shexiangtou_icon_paizhao, "拍照"));
        kongzhis.add(new TuyaKongzhiModel(R.mipmap.tuya_shexiangtou_icon_jianghua, R.mipmap.tuya_shexiangtou_icon_jianghua_sel, "讲话"));
        kongzhis.add(new TuyaKongzhiModel(R.mipmap.tuya_shexiangtou_icon_luzhi, R.mipmap.tuya_shexiangtou_icon_luzhi_sel, "录制"));
        kongzhis.add(new TuyaKongzhiModel(R.mipmap.tuya_shexiangtou_icon_huifang, "回放"));
        kongzhis.add(new TuyaKongzhiModel(R.mipmap.tuya_shexiangtou_icon_yuncunchu, "云存储"));
        kongzhis.add(new TuyaKongzhiModel(R.mipmap.tuya_shexiangtou_icon_fangxiang, "方向"));
        kongzhis.add(new TuyaKongzhiModel(R.mipmap.tuya_shexiangtou_icon_baojing, "报警"));
        kongzhis.add(new TuyaKongzhiModel(R.mipmap.tuya_shexiangtou_icon_xiangce, "相册"));
        adapter = new TuyaKongzhiAdapter(R.layout.item_camera_kongzhi, kongzhis);
        rv_kongzhi.setLayoutManager(new GridLayoutManager(mContext, 3));
        rv_kongzhi.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                kongzhiPositton = position;
                String name = kongzhis.get(position).getName();
                if (name.equals("拍照")) {
                    clickPaizhao();
                } else if (name.equals("讲话")) {
                    clickJianghua();
                } else if (name.equals("录制")) {
                    clickLuzhi();
                } else if (name.equals("回放")) {
                    CameraHuifangActivity.actionStart(mContext, p2pType);
                } else if (name.equals("云存储")) {

                } else if (name.equals("方向")) {
                    openFangxiang();
                } else if (name.equals("报警")) {
                    CameraSetBaojingActivity.actionStart(mContext);
                } else if (name.equals("相册")) {
                    CameraXiangceActivity.actionStart(mContext);
                }
            }
        });
    }

    private void setAdapterCanClikc(boolean canClikc) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < kongzhis.size(); i++) {
                    TuyaKongzhiModel tuyaKongzhiModel = kongzhis.get(i);
                    tuyaKongzhiModel.setCanClick(canClikc);
                    kongzhis.set(i, tuyaKongzhiModel);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    //拍照功能
    private void clickPaizhao() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) { // 在android 6.0之前会默认返回true
                    paizhao();
                } else {
                    Y.tLong("拍照功能需要赋予访问本地存储的权限，不开启将无法使用该功能！");
                }
            }
        });
    }

    private void paizhao() {//拍照
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Camera/";
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        mCameraP2P.snapshot(path, mContext, new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                dismissProgressDialog();
                TuyaDialogUtils.t(mContext, "拍摄成功");
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                showFailure();
            }
        });
    }

    //讲话功能
    private void clickJianghua() {
        TuyaKongzhiModel model = kongzhis.get(kongzhiPositton);
        if (model.isSelect()) {
            stopJianghua(model);
        } else {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.request(Manifest.permission.RECORD_AUDIO).subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean granted) {
                    if (granted) { // 在android 6.0之前会默认返回true
                        startJianghua(model);
                    } else {
                        Y.tLong("开启对讲功能需要赋予录音的权限，不开启将无法使用该功能！");
                    }
                }
            });
        }
    }

    private void startJianghua(TuyaKongzhiModel model) {//开始讲话
        mCameraP2P.startAudioTalk(new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                upData(model);
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                showFailure();
            }
        });
    }

    private void stopJianghua(TuyaKongzhiModel model) {//停止讲话
        mCameraP2P.stopAudioTalk(new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                upData(model);
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                showFailure();
                upData(model);
            }
        });
    }

    //录制功能
    private void clickLuzhi() {
        TuyaKongzhiModel model = kongzhis.get(kongzhiPositton);
        if (model.isSelect()) {
            stopLuzhi(model);
        } else {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean granted) {
                    if (granted) { // 在android 6.0之前会默认返回true
                        startLuzhi(model);
                    } else {
                        Y.tLong("录制功能需要赋予访问本地存储的权限，不开启将无法使用该功能！");
                    }
                }
            });
        }
    }

    private void startLuzhi(TuyaKongzhiModel model) {//开始录制
        String picPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Camera/";
        File file = new File(picPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".mp4";
        String videoPath = picPath + fileName;
        mCameraP2P.startRecordLocalMp4(videoPath, mContext, new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                upData(model);
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                showFailure();
            }
        });

    }

    private void stopLuzhi(TuyaKongzhiModel model) {//停止录制
        mCameraP2P.stopRecordLocalMp4(new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                upData(model);
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                showFailure();
                upData(model);
            }
        });
    }

    private void cameraConnect() {
        showProgressDialog();
        mCameraP2P.connect(mDeviceBeen.getDevId(), new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                startPlay();
                getXinhao();
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                dismissProgressDialog();
                TuyaDialogUtils.t(mContext, "设备连接失败");
                bt_chonglian.setVisibility(View.VISIBLE);
            }
        });
    }

    private void getXinhao() {
        mDevice.requestWifiSignal(new WifiSignalListener() {

            @Override
            public void onSignalValueFind(String signal) {
                Y.e("我在执行着信号强度么  " + signal);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_xinhao.setText("信号:" + signal + "%");
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Y.e("信号强度失败了：  " + errorMsg);
            }
        });
    }

    private void startPlay() {//开始播放实时视频成功
        if (mCameraP2P != null) {
            mCameraP2P.startPreview(new OperationDelegateCallBack() {
                @Override
                public void onSuccess(int sessionId, int requestId, String data) {
                    dismissProgressDialog();
                    getQingxidu();
                    setAdapterCanClikc(true);
                    bt_chonglian.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(int sessionId, int requestId, int errCode) {
                    dismissProgressDialog();
                    TuyaDialogUtils.t(mContext, "播放实时视频失败");
                    bt_chonglian.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void dissCameraConnect() {
        closeFangxiang();
//        if (mCameraP2P != null) {
//            mCameraP2P.disconnect(new OperationDelegateCallBack() {
//                @Override
//                public void onSuccess(int i, int i1, String s) {
//
//                }
//
//                @Override
//                public void onFailure(int i, int i1, int i2) {
//
//                }
//            });
//        }
        stopPlay();
    }

    private void stopPlay() {//停止播放
        if (mCameraP2P != null) {
            mCameraP2P.stopPreview(new OperationDelegateCallBack() {
                @Override
                public void onSuccess(int sessionId, int requestId, String data) {
                    dismissProgressDialog();
                    setAdapterCanClikc(false);
                }

                @Override
                public void onFailure(int sessionId, int requestId, int errCode) {
                    dismissProgressDialog();
                }
            });
        }
    }

    private void clickFangxiang(String fangxiang) {//方向
        Map<String, String> dps = new HashMap<>();
        dps.put("119", fangxiang);
        mDevice.publishDps(TuyaDeviceManager.getDeviceManager().getJosnString(dps), new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                Y.t("操作失败" + error);
            }

            @Override
            public void onSuccess() {
                Y.e("操作成功" + fangxiang);
            }
        });
    }

    private void stopZhuandong() {//停止转动
        Map<String, Boolean> dps = new HashMap<>();
        dps.put("116", false);
        mDevice.publishDps(TuyaDeviceManager.getDeviceManager().getJosnString(dps), new IResultCallback() {
            @Override
            public void onError(String code, String error) {
                Y.t("操作失败" + error);
            }

            @Override
            public void onSuccess() {
                Y.e("停止转动");
            }
        });
    }

    public void getData(String dpStr) {
        JSONObject jsonObject = JSON.parseObject(dpStr);
        Set<String> strings = jsonObject.keySet();
        Iterator<String> it = strings.iterator();
        while (it.hasNext()) {
            // 获得key
            String key = it.next();
            String value = jsonObject.getString(key);
            jieData(key, value, jsonObject);
        }
    }

    private void jieData(String key, String value, JSONObject jsonObject) {
        Y.e("解析出的数据:  " + "key: " + key + ",value:" + value);
    }

    @OnClick({R.id.bt_chonglian, R.id.iv_switch_shengyin, R.id.iv_switch_shengyin_qp, R.id.iv_switch_quanping, R.id.iv_switch_quanping_qp, R.id.tv_switch_qingxidu, R.id.tv_switch_qingxidu_qp, R.id.rl_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_switch_shengyin:
            case R.id.iv_switch_shengyin_qp:
                clickShengyin();
                break;
            case R.id.iv_switch_quanping:
            case R.id.iv_switch_quanping_qp:
                clickQuanping();
                break;
            case R.id.tv_switch_qingxidu:
            case R.id.tv_switch_qingxidu_qp:
                swichQingxidu();
                break;
            case R.id.rl_back:
                closeFangxiang();
                break;
            case R.id.bt_chonglian:
                cameraConnect();
                break;
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private void clickQuanping() {
        if (getScreenOrientation(this) == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    public int getScreenOrientation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int orientation;
        // if the device's natural orientation is portrait:
        if ((rotation == Surface.ROTATION_0
                || rotation == Surface.ROTATION_180) && height > width ||
                (rotation == Surface.ROTATION_90
                        || rotation == Surface.ROTATION_270) && width > height) {
            switch (rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_180:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                case Surface.ROTATION_270:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
            }
        }
        // if the device's natural orientation is landscape or if the device
        // is square:
        else {
            switch (rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_180:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                case Surface.ROTATION_270:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
            }
        }

        return orientation;
    }


    private void closeFangxiang() {
        isOpenFangxiang = false;
        ll_fangxiang.setVisibility(View.GONE);
        rv_kongzhi.setVisibility(View.VISIBLE);
    }

    private void openFangxiang() {
        isOpenFangxiang = true;
        rv_kongzhi.setVisibility(View.GONE);
        ll_fangxiang.setVisibility(View.VISIBLE);
    }

    //声音功能
    private void clickShengyin() {
        showProgressDialog();
        if (isKaiqishengyin) {
            iv_switch_shengyin.setImageResource(R.mipmap.tuya_shexiangtou_icon_jingyin);
            iv_switch_shengyin_qp.setImageResource(R.mipmap.tuya_shexiangtou_icon_jingyin);
            guanbishengyin();
        } else {
            iv_switch_shengyin.setImageResource(R.mipmap.tuya_shexiangtou_icon_you_shengyin);
            iv_switch_shengyin_qp.setImageResource(R.mipmap.tuya_shexiangtou_icon_you_shengyin);
            kaiqiShengyin();
        }
        isKaiqishengyin = !isKaiqishengyin;
    }

    private void kaiqiShengyin() {//开启声音
        mCameraP2P.setMute(0, new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                dismissProgressDialog();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv_switch_shengyin.setImageResource(R.mipmap.tuya_shexiangtou_icon_you_shengyin);
                        iv_switch_shengyin_qp.setImageResource(R.mipmap.tuya_shexiangtou_icon_you_shengyin);
                        isKaiqishengyin = true;
                    }
                });
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                showFailure();
            }
        });
    }

    private void guanbishengyin() {//关闭声音
        mCameraP2P.setMute(1, new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                dismissProgressDialog();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv_switch_shengyin.setImageResource(R.mipmap.tuya_shexiangtou_icon_jingyin);
                        iv_switch_shengyin_qp.setImageResource(R.mipmap.tuya_shexiangtou_icon_jingyin);
                        isKaiqishengyin = false;
                    }
                });
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                showFailure();
            }
        });
    }

    private void getQingxidu() {//获取清晰度
        mCameraP2P.getVideoClarity(new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                qingxidu = data;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (qingxidu.equals("4")) {
                            tv_switch_qingxidu.setText("高清");
                            tv_switch_qingxidu_qp.setText("高清");
                        } else {
                            tv_switch_qingxidu.setText("标清");
                            tv_switch_qingxidu_qp.setText("标清");
                        }
                    }
                });
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                dismissProgressDialog();
                qingxidu = "4";
                tv_switch_qingxidu.setText("高清");
                tv_switch_qingxidu_qp.setText("高清");
            }
        });
    }

    private void swichQingxidu() {//切换清晰度
        showProgressDialog();
        if (qingxidu.equals("4")) {
            mCameraP2P.setVideoClarity(2, new OperationDelegateCallBack() {
                @Override
                public void onSuccess(int i, int i1, String s) {
                    dismissProgressDialog();
                    qingxidu = "2";
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_switch_qingxidu.setText("标清");
                            tv_switch_qingxidu_qp.setText("标清");
                        }
                    });
                }

                @Override
                public void onFailure(int i, int i1, int i2) {
                    showFailure();
                }
            });
        } else {
            mCameraP2P.setVideoClarity(4, new OperationDelegateCallBack() {
                @Override
                public void onSuccess(int i, int i1, String s) {
                    dismissProgressDialog();
                    qingxidu = "4";
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_switch_qingxidu.setText("高清");
                            tv_switch_qingxidu_qp.setText("高清");
                        }
                    });
                }

                @Override
                public void onFailure(int i, int i1, int i2) {
                    showFailure();
                }
            });
        }
    }

    private void showFailure() {//出错
        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_CAMERA_FAIL;
        notice.content = "网络异常";
        RxBus.getDefault().sendRx(notice);
    }

    private void showFailure(String msg) {//出错
        Notice notice = new Notice();
        notice.type = ConstanceValue.MSG_CAMERA_FAIL;
        notice.content = msg;
        RxBus.getDefault().sendRx(notice);
    }

    private void upData(TuyaKongzhiModel model) {//更新数据
        dismissProgressDialog();
        model.setSelect(!model.isSelect());
        kongzhis.set(kongzhiPositton, model);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (view.getId() == R.id.bt_up) {
                clickFangxiang("0");
            } else if (view.getId() == R.id.bt_down) {
                clickFangxiang("4");
            } else if (view.getId() == R.id.bt_left) {
                clickFangxiang("6");
            } else if (view.getId() == R.id.bt_right) {
                clickFangxiang("2");
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            Y.e("我抬起来了并停止转动");
            if (view.getId() == R.id.bt_up) {
                stopZhuandong();
            } else if (view.getId() == R.id.bt_down) {
                stopZhuandong();
            } else if (view.getId() == R.id.bt_left) {
                stopZhuandong();
            } else if (view.getId() == R.id.bt_right) {
                stopZhuandong();
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mCameraP2P) {
            mCameraP2P.removeOnP2PCameraListener();
            mCameraP2P.destroyP2P();
        }
        TuyaDeviceManager.getDeviceManager().unRegisterDevListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCameraP2P != null && isPlay) {
            cameraConnect();
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onBackPressedSupport() {
        if (getScreenOrientation(this) == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            if (isOpenFangxiang) {
                closeFangxiang();
            } else {
                finish();
            }
        }
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            Y.e("我是竖屏阿飞烦烦烦");
            ll_main.setVisibility(View.VISIBLE);
            rl_quanping.setVisibility(View.GONE);
            fl_quanping.removeAllViews();
            fl_camera.addView(mVideoView);
            mToolbar.setVisibility(View.VISIBLE);
            setNotFullScreen();
        } else {
            mToolbar.setVisibility(View.GONE);
            closeFangxiang();
            Y.e("我是横屏开飞机的收费电视 ");
            ll_main.setVisibility(View.GONE);
            rl_quanping.setVisibility(View.VISIBLE);
            fl_camera.removeAllViews();
            fl_quanping.addView(mVideoView);
            setFullScreen();
        }
    }
}
