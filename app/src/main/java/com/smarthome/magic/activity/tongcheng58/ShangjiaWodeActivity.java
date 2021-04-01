package com.smarthome.magic.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.tongcheng58.model.ShangjiaDetailModel;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.Radius_GlideImageLoader;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.view.AutoNextLineLinearlayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class ShangjiaWodeActivity extends BaseActivity {

    @BindView(R.id.iv_shenhe_state)
    ImageView iv_shenhe_state;
    @BindView(R.id.tv_shenhe_state)
    TextView tv_shenhe_state;
    @BindView(R.id.ll_shenhe_state)
    LinearLayout ll_shenhe_state;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_name)
    TextView tv_name;
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

    private String ir_id;
    private String x_jingdu;
    private String y_weidu;
    private ShangjiaDetailModel.DataBean dataBean;

    @Override
    public int getContentViewResId() {
        return R.layout.tongcheng_act_shangjia_wode;
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
        Intent intent = new Intent(context, ShangjiaWodeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("ir_id", ir_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        map.put("operate_type", "2");
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
                            tv_juli_address.setText(dataBean.getAddr());
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
}
