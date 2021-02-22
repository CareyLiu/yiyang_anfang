package com.smarthome.magic.dialog.newdia;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarthome.magic.R;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.util.TimeCount;


public class TishiPhoneDialog extends Dialog implements View.OnClickListener {
    public final static int TYPE_XIAOXI = 1;
    public final static int TYPE_FAILED = 2;
    public final static int TYPE_CAOZUO = 3;
    public final static int TYPE_SUCESS = 4;
    public final static int TYPE_DELETE = 5;

    public TextView tv_content;
    public TextView tv_title;
    public TextView tv_cancel;
    public TextView tv_confirm;
    public ImageView iv_img;
    public TextView tvGetCode;
    private TimeCount timeCount;

    private TishiDialogListener mListener;
    protected boolean dismissAfterClick = true;//是否点击按钮后关闭
    private int type;//1.消息推送    2.操作失败    3.操作提示    4.操作成功    5.删除

    public TishiPhoneDialog(Context context, int type, TishiDialogListener mListener) {
        this(context, R.style.dialogBaseBlur);
        this.mListener = mListener;
        this.type = type;
        init();
    }

    private TishiPhoneDialog(Context context, int theme) {
        super(context, theme);
    }

    private void init() {
        setContentView(R.layout.dialog_phone_tishi);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        iv_img = findViewById(R.id.iv_img);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_confirm = findViewById(R.id.tv_confirm);
        tvGetCode = findViewById(R.id.get_code);

        tv_cancel.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
        tvGetCode.setOnClickListener(this);

        timeCount = new TimeCount(60000, 1000, tvGetCode);
    }

    // ---------------------------text
    public TishiPhoneDialog setTextTitle(String text) {//提示标题
        tv_title.setText(text);
        return this;
    }

    public TishiPhoneDialog setTextCancel(String text) {//取消按钮  传空字符串隐藏
        if (TextUtils.isEmpty(text)) {
            tv_cancel.setVisibility(View.GONE);
        } else {
            tv_cancel.setVisibility(View.VISIBLE);
            tv_cancel.setText(text);
        }
        return this;
    }

    public TishiPhoneDialog setTextConfirm(String text) {//确定按钮
        tv_confirm.setText(text);
        return this;
    }

    public TishiPhoneDialog setTextContent(String text) {//提示内容
        tv_content.setText(text);
        return this;
    }

    public TishiPhoneDialog setImgPic(int imageResource) {//设置本地图片
        iv_img.setImageResource(imageResource);
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == tv_cancel) {
            clickCancel(v);
        } else if (v == tv_confirm) {
            clickConfirm(v);
        }
    }

    private void clickCancel(View v) {//取消按钮
        if (mListener != null) {
            mListener.onClickCancel(v, TishiPhoneDialog.this);
        }
        dismissAfterClick();
    }

    private void clickConfirm(View v) {//确定按钮
        if (mListener != null) {
            mListener.onClickConfirm(v, TishiPhoneDialog.this);
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
     */
    public TishiPhoneDialog setDismissAfterClick(boolean dismissAfterClick) {
        this.dismissAfterClick = dismissAfterClick;
        return this;
    }


    public interface TishiDialogListener {
        void onClickCancel(View v, TishiPhoneDialog dialog);

        void onClickConfirm(View v, TishiPhoneDialog dialog);

        void onDismiss(TishiPhoneDialog dialog);

        void onSendYanZhengMa(View v, TishiPhoneDialog dialog);

    }


    @Override
    public void dismiss() {
        super.dismiss();
        if (mListener != null) {
            mListener.onDismiss(TishiPhoneDialog.this);
        }
    }

    public void startCode() {
        timeCount.start();
    }

    public void errorCode() {
        timeCount.cancel();
        timeCount.onFinish();
    }
}
