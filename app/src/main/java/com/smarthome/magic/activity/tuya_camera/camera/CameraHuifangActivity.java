package com.smarthome.magic.activity.tuya_camera.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_camera.camera.model.TuyaPlayDataModel;
import com.smarthome.magic.activity.tuya_camera.camera.model.TuyaPlayPieceModel;
import com.smarthome.magic.activity.tuya_camera.utils.TuyaDeviceManager;
import com.smarthome.magic.app.BaseActivity;
import com.tuya.smart.camera.camerasdk.typlayer.callback.AbsP2pCameraListener;
import com.tuya.smart.camera.camerasdk.typlayer.callback.OperationDelegateCallBack;
import com.tuya.smart.camera.middleware.p2p.ITuyaSmartCameraP2P;
import com.tuya.smart.camera.middleware.p2p.TuyaSmartCameraP2PFactory;
import com.tuya.smart.camera.middleware.widget.AbsVideoViewCallback;
import com.tuya.smart.camera.middleware.widget.TuyaCameraView;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CameraHuifangActivity extends BaseActivity{


    @BindView(R.id.camera_video_view)
    TuyaCameraView mVideoView;
    @BindView(R.id.iv_switch_shengyin)
    ImageView ivSwitchShengyin;
    @BindView(R.id.iv_switch_quanping)
    ImageView ivSwitchQuanping;
    @BindView(R.id.iv_play)
    ImageView ivPlay;
    @BindView(R.id.rl_play)
    RelativeLayout rlPlay;



    private ITuyaSmartCameraP2P mCameraP2P;
    private int p2pType;
    private DeviceBean deviceBeen;
    private TuyaPlayPieceModel playPieceModel;
    private List<TuyaPlayPieceModel.ItemsBean> items = new ArrayList<>();
    private int playCount;
    private boolean isPlayback;

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, int p2pType) {
        Intent intent = new Intent(context, CameraHuifangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("p2pType", p2pType);
        context.startActivity(intent);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.act_device_camera_huifang;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("回放");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.back_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
        initCamera();
    }


    private void init() {
        p2pType = TuyaDeviceManager.getDeviceManager().getP2pType();
        deviceBeen = TuyaDeviceManager.getDeviceManager().getDeviceBeen();
    }


    private void initCamera() {
        mCameraP2P = TuyaSmartCameraP2PFactory.createCameraP2P(p2pType, deviceBeen.getDevId());
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

        cameraConnect();


    }

    private void cameraConnect() {
        showProgressDialog();
        mCameraP2P.connect(deviceBeen.getDevId(), new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                getPlyaData();
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                dismissProgressDialog();
            }
        });
    }

    private void getPlyaData() {
        mCameraP2P.queryRecordDaysByMonth(2020, 12, new OperationDelegateCallBack() {
            @Override
            public void onSuccess(int sessionId, int requestId, String data) {
                Y.e("获取播放日期 " + data);
                TuyaPlayDataModel model = JSON.parseObject(data, TuyaPlayDataModel.class);
                List<String> dataDays = model.getDataDays();
                Y.e("我是多少啊啊发 " + dataDays.size());
                if (dataDays.size() > 0) {
                    int day = Y.getInt(dataDays.get(dataDays.size() - 1));
                    mCameraP2P.queryRecordTimeSliceByDay(2020, 12, day, new OperationDelegateCallBack() {
                        @Override
                        public void onSuccess(int sessionId, int requestId, String data) {
                            dismissProgressDialog();
                            playPieceModel = JSON.parseObject(data, TuyaPlayPieceModel.class);
                            items = playPieceModel.getItems();
                            statPlay(0);
                        }

                        @Override
                        public void onFailure(int sessionId, int requestId, int errCode) {
                            dismissProgressDialog();
                            Y.e("获取片段失败   " + errCode);
                        }
                    });
                }
            }

            @Override
            public void onFailure(int sessionId, int requestId, int errCode) {
                dismissProgressDialog();
                Y.e("获取日期失败");
            }
        });
    }

    private void statPlay(int playCount) {
        this.playCount = playCount;
        TuyaPlayPieceModel.ItemsBean timePieceBean = items.get(playCount);
        mCameraP2P.startPlayBack(timePieceBean.getStartTime(),
                timePieceBean.getEndTime(),
                timePieceBean.getStartTime(), new OperationDelegateCallBack() {
                    @Override
                    public void onSuccess(int sessionId, int requestId, String data) {
                        isPlayback = true;
                    }

                    @Override
                    public void onFailure(int sessionId, int requestId, int errCode) {
                        isPlayback = false;
                    }
                }, new OperationDelegateCallBack() {
                    @Override
                    public void onSuccess(int sessionId, int requestId, String data) {
                        isPlayback = false;
                    }

                    @Override
                    public void onFailure(int sessionId, int requestId, int errCode) {
                        isPlayback = false;
                    }
                });
    }
}
