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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.newdia.FaBuDialog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.BianMinXinXiModel;
import com.smarthome.magic.model.GongJiangXinXiModel;
import com.smarthome.magic.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.smarthome.magic.app.App.JINGDU;
import static com.smarthome.magic.app.App.WEIDU;
import static com.smarthome.magic.get_net.Urls.TONGCHENG;

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
        irId = getIntent().getStringExtra("irId");
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

        getData();
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
    public static void actionStart(Context context, String irId) {
        Intent intent = new Intent(context, GongJiangXinXiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("irId", irId);
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

    public String irId;
    public String operate_type = "1";

    public void getData() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17008");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("x", PreferenceHelper.getInstance(mContext).getString(WEIDU, ""));
        map.put("y", PreferenceHelper.getInstance(mContext).getString(JINGDU, ""));
        map.put("ir_id", irId);
        map.put("operate_type", operate_type);
        Gson gson = new Gson();
        OkGo.<AppResponse<GongJiangXinXiModel.DataBean>>post(TONGCHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GongJiangXinXiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<GongJiangXinXiModel.DataBean>> response) {
                        Logger.d(gson.toJson(response.body()));
                        //irId = response.body().data.get(0).getIr_id();
//                        //标题
//                        tvTitleName.setText(response.body().data.get(0).getIr_title());
//
//                        //距离和地址
//                        if (!StringUtils.isEmpty(response.body().data.get(0).getMeter())) {
//                            tvDizhi.setText(response.body().data.get(0).getMeter() + "km" + "  " + response.body().data.get(0).getAddr());
//                        } else {
//                            tvDizhi.setText(response.body().data.get(0).getAddr());
//                        }
//                        lianxiFangshi.setText(response.body().data.get(0).getIr_contact_name() + response.body().data.get(0).getIr_contact_phone());
//
//                        tvJianjie.setText(response.body().data.get(0).getIr_validity());
//
//                        for (int i = 0; i < response.body().data.get(0).getImgList().size(); i++) {
//                            View view = View.inflate(mContext, R.layout.item_big_image, null);
//                            ImageView iv = view.findViewById(R.id.iv_img);
//
//                            if (!StringUtils.isEmpty(response.body().data.get(0).getImgList().get(i).getHeight())) {
//                                RequestOptions requestOptions = new RequestOptions();
//                                requestOptions.override(Integer.valueOf(response.body().data.get(0).getImgList().get(i).getWidth()), Integer.valueOf(response.body().data.get(0).getImgList().get(i).getHeight()));
//                                Glide.with(mContext).load(response.body().data.get(0).getImgList().get(i).getIr_img_url()).apply(requestOptions).into(iv);
//                            }
//                            linearLayout.addView(view);
//                        }
//                        tvJujueYuanyin.setText(response.body().data.get(0).getIr_manage_text());
                    }

                    @Override
                    public void onError(Response<AppResponse<GongJiangXinXiModel.DataBean>> response) {

                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<GongJiangXinXiModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }


}
