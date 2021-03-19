package com.smarthome.magic.activity.tongcheng58;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.flyco.roundview.RoundRelativeLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.dialog.newdia.FaBuDialog;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class GongJiangXinXiActivity extends BaseActivity {
    @BindView(R.id.iv_image)
    CircleImageView ivImage;
    @BindView(R.id.tv_gongjiang_ming)
    TextView tvGongjiangMing;
    @BindView(R.id.ll_tag)
    LinearLayout llTag;
    @BindView(R.id.rl_item)
    RelativeLayout rlItem;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rl_dadianhua)
    RoundRelativeLayout rlDadianhua;
    @BindView(R.id.rl_jiaweixin)
    RoundRelativeLayout rlJiaweixin;
    @BindView(R.id.rl_yuefuwu)
    RoundRelativeLayout rlYuefuwu;
    @BindView(R.id.rl_fenxiang)
    RoundRelativeLayout rlFenxiang;
    String phoneNumber = "13888888888888";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smartRefreshLayout.setEnableLoadMore(false);
        rlDadianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "打电话");
                callPhone(phoneNumber);
            }
        });
        rlJiaweixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UIHelper.ToastMessage(mContext, "加微信");
                copyContentToClipboard("11", mContext);
            }
        });
        rlYuefuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "约服务");
            }
        });
        rlFenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "分享");
            }
        });

        FaBuDialog faBuDialog = new FaBuDialog(mContext, new FaBuDialog.FaBuDialogListener() {
            @Override
            public void shangJiaFaBu() {
                UIHelper.ToastMessage(mContext, "shangjiafabu");
            }

            @Override
            public void gongJiangFaBu() {

                UIHelper.ToastMessage(mContext, "gongjiang");
            }

            @Override
            public void bianMinFabu() {
                //UIHelper.ToastMessage(mContext, "便民");
                BianMinFaBuActivity.actionStart(mContext);
            }
        });
        faBuDialog.show();

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = faBuDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        faBuDialog.getWindow().setAttributes(lp);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_gongjiang_xinxi;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("工匠信息");
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

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, GongJiangXinXiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    /**
     * 复制内容到剪贴板
     *
     * @param content
     * @param context
     */
    public void copyContentToClipboard(String content, Context context) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", content);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }


}
