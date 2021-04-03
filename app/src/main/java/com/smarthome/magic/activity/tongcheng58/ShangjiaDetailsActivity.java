package com.smarthome.magic.activity.tongcheng58;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.LatLng;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.DefaultX5WebView_HaveNameActivity;
import com.smarthome.magic.activity.fenxiang_tuisong.ShareActivity;
import com.smarthome.magic.activity.fenxiang_tuisong.ShouYeFenXiang_Url_Activity;
import com.smarthome.magic.activity.homepage.DaLiBaoActivity;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tongcheng58.model.ShangjiaDetailModel;
import com.smarthome.magic.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.MyApplication;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.Radius_GlideImageLoader;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.util.NavigationUtils;
import com.smarthome.magic.view.AutoNextLineLinearlayout;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

import static com.smarthome.magic.activity.fenxiang_tuisong.FenXiangTuiSongActivity.IMAGE_SIZE;
import static com.smarthome.magic.config.Wetch_S.APP_ID;

public class ShangjiaDetailsActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_juli_address)
    TextView tv_juli_address;
    @BindView(R.id.tv_name_phone)
    TextView tv_name_phone;
    @BindView(R.id.tv_yingye_time)
    TextView tv_yingye_time;
    @BindView(R.id.ll_dianneisheshi_add)
    AutoNextLineLinearlayout ll_dianneisheshi_add;
    @BindView(R.id.tv_gonggao)
    TextView tv_gonggao;
    @BindView(R.id.tv_jieshao)
    TextView tv_jieshao;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.bt_phone)
    RoundRelativeLayout bt_phone;
    @BindView(R.id.bt_weixin)
    RoundRelativeLayout bt_weixin;
    @BindView(R.id.bt_daohang)
    RoundRelativeLayout bt_daohang;
    @BindView(R.id.bt_fenxiang)
    RoundRelativeLayout bt_fenxiang;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private String ir_id;
    private String x_jingdu;
    private String y_weidu;
    private ShangjiaDetailModel.DataBean dataBean;
    private IWXAPI api;

    @Override
    public int getContentViewResId() {
        return R.layout.tongcheng_act_shangjia_details;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("店铺详情");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void actionStart(Context context, String ir_id) {
        Intent intent = new Intent(context, ShangjiaDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ir_id", ir_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);//创建一个实例
        api.registerApp(APP_ID);//注册实例
        ir_id = getIntent().getStringExtra("ir_id");
        x_jingdu = PreferenceHelper.getInstance(mContext).getString(App.JINGDU, "");
        y_weidu = PreferenceHelper.getInstance(mContext).getString(App.WEIDU, "");
        initSM();
        getData();
    }

    private void initBanner() {
        List<ShangjiaDetailModel.DataBean.ImgListBean> imgList = dataBean.getImgList();
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < imgList.size(); i++) {
            imgs.add(imgList.get(i).getIr_img_url());
        }
        banner.setImageLoader(new Radius_GlideImageLoader());
        banner.setImages(imgs);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }


    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "17007");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("x", x_jingdu);
        map.put("y", y_weidu);
        map.put("ir_id", ir_id);
        map.put("operate_type", "1");
        Gson gson = new Gson();
        OkGo.<AppResponse<ShangjiaDetailModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ShangjiaDetailModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ShangjiaDetailModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            dataBean = response.body().data.get(0);
                            tv_name.setText(dataBean.getIr_inst_name());
                            tv_juli_address.setText(dataBean.getMeter() + "km  " + dataBean.getAddr());
                            tv_name_phone.setText(dataBean.getIr_contact_phone());
                            tv_yingye_time.setText(dataBean.getIr_inst_open_time() + "-" + dataBean.getIr_inst_close_time());

                            tv_gonggao.setText(dataBean.getIr_inst_notice());
                            tv_jieshao.setText(dataBean.getIr_validity());

                            initBanner();

                            ll_dianneisheshi_add.removeAllViews();
                            List<ShangjiaDetailModel.DataBean.InstDeviceListBean> inst_device_list = dataBean.getInst_device_list();
                            for (int i = 0; i < inst_device_list.size(); i++) {
                                View view = View.inflate(mContext, R.layout.tongcheng_item_shangjia_details_tag, null);
                                TextView tv_tag = view.findViewById(R.id.tv_tag);
                                tv_tag.setText(inst_device_list.get(i).getInst_device_name());
                                ll_dianneisheshi_add.addView(view);
                            }
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                    }
                });
    }

    @OnClick({R.id.bt_phone, R.id.bt_weixin, R.id.bt_daohang, R.id.bt_fenxiang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_phone:
                clickPhone();
                break;
            case R.id.bt_weixin:
                copyContentToClipboard();
                break;
            case R.id.bt_daohang:
                clickDaohang();
                break;
            case R.id.bt_fenxiang:
                clickShare();
                break;
        }
    }

    private void clickDaohang() {
        if (dataBean != null) {
            try {
                double x = Y.getDouble(dataBean.getX());
                double y = Y.getDouble(dataBean.getY());
                LatLng latLng = new LatLng(x, y);
                NavigationUtils.Navigation(latLng);
            } catch (Exception e) {
                com.smarthome.magic.app.UIHelper.ToastMessage(MyApplication.getApp().getApplicationContext(), "请下载高德后重新尝试", Toast.LENGTH_SHORT);
            }
        }
    }

    private void clickShare() {
        if (dataBean != null) {
            ShareActivity.actionStart(mContext,
                    dataBean.getShare_title(),
                    dataBean.getShare_detail(),
                    dataBean.getShare_url(),
                    dataBean.getShare_img());
        }
    }

    private void clickPhone() {
        String ir_contact_phone = dataBean.getIr_contact_phone();
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + ir_contact_phone);
                intent.setData(data);
                startActivity(intent);
            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.setTextTitle("拨打电话");
        dialog.setTextContent(ir_contact_phone);
        dialog.show();
    }


    /**
     * 复制内容到剪贴板
     */
    public void copyContentToClipboard() {
        if (dataBean != null) {
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", dataBean.getIr_wx_number());
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);

            UIHelper.ToastMessage(mContext, "已复制微信号");
        }
    }


}
