package com.smarthome.magic.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.smarthome.magic.R;

public class TiemDialog extends Dialog {

    public TiemDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.layout_time_dialog);
    }
}
