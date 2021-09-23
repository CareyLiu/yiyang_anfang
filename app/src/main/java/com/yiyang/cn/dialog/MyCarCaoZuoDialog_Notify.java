package com.yiyang.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.yiyang.cn.R;

public class MyCarCaoZuoDialog_Notify extends Dialog {

    private View theView;
    private Context context;

    public MyCarCaoZuoDialog_Notify(@NonNull Context context, OnDialogItemClickListener listener) {
        super(context, R.style.turntable_dialog);
        this.context = context;
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theView = inflater.inflate(R.layout.dialog_caozuo_notify, null);
        TextView tvLft = theView.findViewById(R.id.tv_left);
        tvLft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickLeft();
                dismiss();
            }
        });

        TextView tvRight = theView.findViewById(R.id.tv_right);
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickRight();
                dismiss();
            }
        });
        setContentView(theView);
    }

    private OnDialogItemClickListener listener;

    public interface OnDialogItemClickListener {
        void clickLeft();

        void clickRight();
    }


}
