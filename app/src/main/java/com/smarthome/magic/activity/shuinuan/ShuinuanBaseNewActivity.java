package com.smarthome.magic.activity.shuinuan;

import android.os.Bundle;
import android.view.View;


import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.dialog.LordingDialog;
import com.smarthome.magic.dialog.newdia.TishiDialog;

import androidx.annotation.Nullable;

public class ShuinuanBaseNewActivity extends BaseActivity {

    public static String SN_Send;//"wh/hardware/";
    public static String SN_Accept;//"wh/app/";
    public static String ccid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void chenggong() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
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
        dialog.show();
    }

    private LordingDialog lordingDialog;

    public void showProgressDialog() {
        showProgressDialog("");
    }

    public void showProgressDialog(String msg) {
        if (lordingDialog == null) {
            lordingDialog = new LordingDialog(mContext);
        }
        lordingDialog.setTextMsg(msg);

        if (!lordingDialog.isShowing()) {
            lordingDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (lordingDialog != null) {
            try {
                lordingDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
