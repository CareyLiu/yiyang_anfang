package com.yiyang.cn.activity.saoma;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.DefaultX5WebViewActivity;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.model.H5JiaoHuModel;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
import rx.functions.Action1;

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {
    private static final String tag = ScanActivity.class.getSimpleName();
    @BindView(R.id.zxingview)
    ZBarView mQRCodeView;
    @BindView(R.id.capture_flash)
    ImageView captureFlash;


    private String companyid;
    Long personId;
    private String roleId;
    boolean flag = true;
    boolean input_flag = false;

    private String myCode = null;
    private String Sn = null;

    private Camera camera;
    private Camera.Parameters parameter;
    ProgressDialog waitdialog;

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ScanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        mQRCodeView.startSpot();
        mQRCodeView.setDelegate(this);
        // mQRCodeView.setResultHandler(this);

    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_scan1;
    }


    private void light() {
        if (flag) {
            flag = false;
            // 开闪光灯
            mQRCodeView.openFlashlight();
            captureFlash.setTag(null);
            captureFlash.setBackgroundResource(R.drawable.flash_open);
        } else {
            flag = true;
            // 关闪光灯
            mQRCodeView.closeFlashlight();
            captureFlash.setTag("1");
            captureFlash.setBackgroundResource(R.drawable.flash_default);
        }
    }


    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.e(tag, result);
        myCode = result;
        waitdialog = ProgressDialog.show(ScanActivity.this, null, "已扫描，正在处理···", true, true);
        waitdialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                mQRCodeView.stopSpot();
            }
        });

        //  UIHelper.ToastMessage(ScanActivity.this, "您已经收到了二维码 code:" + result);
        vibrate();
        // mQRCodeView.startSpot();


        if (result.startsWith("http") || result.startsWith("https")) {
            result = result + "&token=" + UserManager.getManager(ScanActivity.this).getAppToken();
            DefaultX5WebViewActivity.actionStart(ScanActivity.this, result);
            waitdialog.dismiss();
            ScanActivity.this.finish();

        } else {
            UIHelper.ToastMessage(ScanActivity.this, result);
            waitdialog.dismiss();
        }


    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(tag, "打开相机出错");
        mQRCodeView.startCamera();
    }

    /**
     * 扫描结果对话框
     *
     * @param msg
     */
    public void showDialog(final String msg) {
        new AlertDialog.Builder(ScanActivity.this).setTitle("扫描结果").setMessage(msg)
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        waitdialog.dismiss();
                        dialog.dismiss();
                        mQRCodeView.startSpotAndShowRect();
                    }
                }).show();
    }


    @OnClick({R.id.capture_flash})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.capture_flash:
                light();
                break;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        mQRCodeView.stopCamera();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (waitdialog != null) {
            waitdialog.dismiss();
        }
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("扫一扫智慧医养收款码");
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

    @Override
    public boolean showToolBar() {
        return true;
    }

}
