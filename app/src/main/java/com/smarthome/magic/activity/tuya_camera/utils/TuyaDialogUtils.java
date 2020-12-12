package com.smarthome.magic.activity.tuya_camera.utils;

import android.content.Context;
import android.view.View;

import com.smarthome.magic.activity.tuya_camera.dialog.TishiNewDialog;

public class TuyaDialogUtils {

    public static void t(Context context,String  msg){
        TishiNewDialog tishiDialog=new TishiNewDialog(context, new TishiNewDialog.TishiDialogListener() {
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
        tishiDialog.setTextCancel("");
        tishiDialog.setTextCont(msg);
        tishiDialog.show();
    }

}
