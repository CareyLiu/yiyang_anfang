package com.smarthome.magic.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.smarthome.magic.R;

public class MyCarCaoZuoDialog_Success extends Dialog {

    private View theView;
    private Activity context;

    TextView tvOk;

    String str, str2;

    public MyCarCaoZuoDialog_Success(@NonNull Activity context, String str, String str2) {
        super(context, R.style.turntable_dialog);
        this.context = context;
        this.str = str;
        this.str2 = str2;
    }

    public MyCarCaoZuoDialog_Success(@NonNull Activity context) {
        super(context, R.style.turntable_dialog);
        this.context = context;
        this.str = str;
        this.str2 = str2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theView = inflater.inflate(R.layout.dialog_caozuo_success, null);

        TextView tvCaozuocheng = theView.findViewById(R.id.tv_caozuochegngong);
        TextView tvCaoZuoChengGongHuaShu = theView.findViewById(R.id.tv_caozuochenggonghuashu);

        if (str != null) {
            tvCaozuocheng.setText(str);
            tvCaoZuoChengGongHuaShu.setText(str2);
        }


        tvOk = theView.findViewById(R.id.tv_ok);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(theView);
    }
}

