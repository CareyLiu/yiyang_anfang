package com.smarthome.magic.activity.shuinuan;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarthome.magic.R;

import java.util.List;


public class GuzhangDialog extends Dialog implements View.OnClickListener {

    public LinearLayout ll_guzhang;
    public TextView bt_clear;


    protected boolean dismissAfterClick = true;
    private Guzhang mListener;

    public GuzhangDialog setmListener(Guzhang mListener) {
        this.mListener = mListener;
        return this;
    }

    public GuzhangDialog(Context context, Guzhang mListener) {
        this(context, R.style.dialogBaseBlur);
        this.mListener = mListener;
    }

    public GuzhangDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    private void init() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(R.layout.dialog_shuinuan_guzhang);
        bt_clear = findViewById(R.id.bt_clear);
        ll_guzhang = findViewById(R.id.ll_guzhang);
        bt_clear.setOnClickListener(this);
    }


    public void setGuzhang(List<String> guzhangs) {
        ll_guzhang.removeAllViews();
        for (int i = 0; i < guzhangs.size(); i++) {
            View view = View.inflate(getContext(), R.layout.item_shuinuan_guzhuang, null);
            TextView tv_content = view.findViewById(R.id.tv_content);
            tv_content.setText(guzhangs.get(i));
            ll_guzhang.addView(view);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == bt_clear) {
            clickConfirm(v);
        }
    }

    private void clickConfirm(View v) {
        if (mListener != null) {
            mListener.onClickConfirm(v, GuzhangDialog.this);
        }
        dismissAfterClick();
    }

    protected void dismissAfterClick() {
        if (dismissAfterClick) {
            dismiss();
        }
    }

    /**
     * 设置是否点击按钮后自动关闭窗口,默认true(是)
     *
     * @param dismissAfterClick
     * @return
     */
    public GuzhangDialog setDismissAfterClick(boolean dismissAfterClick) {
        this.dismissAfterClick = dismissAfterClick;
        return this;
    }


    public interface Guzhang {
        void onClickConfirm(View v, GuzhangDialog dialog);

        void onDismiss(GuzhangDialog dialog);
    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (mListener != null) {
            mListener.onDismiss(GuzhangDialog.this);
        }
    }

    public void  showDD(List<String> guzhangs){
        setGuzhang(guzhangs);
        show();
    }
}
