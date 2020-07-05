package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.GuZhangDetailsModel;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class CheLianWangNoticeActvity extends BaseActivity {
    String notifyId;
    @BindView(R.id.tv_yonghuming)
    TextView tvYonghuming;
    @BindView(R.id.tv_cheliang_xinghao)
    TextView tvCheliangXinghao;
    @BindView(R.id.tv_chepaihaoma)
    TextView tvChepaihaoma;
    @BindView(R.id.tv_fashengshijian)
    TextView tvFashengshijian;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.cl_1)
    ConstraintLayout cl1;
    @BindView(R.id.view_line1)
    View viewLine1;
    @BindView(R.id.tv_anzhuangshijian)
    TextView tvAnzhuangshijian;
    @BindView(R.id.tv_xinghao)
    TextView tvXinghao;
    @BindView(R.id.tv_shouhou_dianhua)
    TextView tvShouhouDianhua;
    @BindView(R.id.tv_changjia)
    TextView tvChangjia;
    @BindView(R.id.cl_2)
    ConstraintLayout cl2;
    @BindView(R.id.view_line_2)
    View viewLine2;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.iv_icon_2)
    ImageView ivIcon2;
    @BindView(R.id.cl_3)
    ConstraintLayout cl3;
    @BindView(R.id.tv_guanzhang_info)
    TextView tvGuanzhangInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notifyId = getIntent().getStringExtra("notifyId");
        requestData();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_che_lian_wang_notice_actvity;
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String notifyId) {
        Intent intent = new Intent(context, CheLianWangNoticeActvity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("notifyId", notifyId);
        context.startActivity(intent);
    }

    public void requestData() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "03004");
        map.put("key", Constant.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("notify_id", notifyId);
        Gson gson = new Gson();
        OkGo.<AppResponse<GuZhangDetailsModel.DataBean>>post(Constant.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<GuZhangDetailsModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<GuZhangDetailsModel.DataBean>> response) {
                        GuZhangDetailsModel.DataBean dataBean = response.body().data.get(0);
                        tvYonghuming.setText(dataBean.getUser_name());
                        tvCheliangXinghao.setText("车辆型号：" + dataBean.getCar_brand_name());
                        tvChepaihaoma.setText("车牌号码：" + dataBean.getPlate_number());
                        tvFashengshijian.setText("发生时间：" + dataBean.getCreate_time());
                        tvAddress.setText("发生地点：" + dataBean.getGps_addr());
                        tvAnzhuangshijian.setText("安装时间：" + dataBean.getInstall_time());
                        tvXinghao.setText("型号：" + dataBean.getXinghao());
                        tvShouhouDianhua.setText("售后电话" + dataBean.getSell_phone());
                        tvChangjia.setText("厂家：" + dataBean.getChangjia_name());
                        tvGuanzhangInfo.setText(dataBean.getFailure_name());
                    }

                    @Override
                    public void onError(Response<AppResponse<GuZhangDetailsModel.DataBean>> response) {
                        //  AlertUtil.t(AtmosActivity.this, response.getException().getMessage());
                    }
                });
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("详情");
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
}
