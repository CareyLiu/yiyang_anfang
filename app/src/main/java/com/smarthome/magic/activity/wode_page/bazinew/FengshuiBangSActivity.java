package com.smarthome.magic.activity.wode_page.bazinew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.smarthome.magic.activity.wode_page.bazinew.model.FengshuiDetails;
import com.smarthome.magic.activity.wode_page.bazinew.model.FengshuiModel;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FengshuiBangSActivity extends BaziBaseActivity {

    @BindView(R.id.tv_title_name)
    TextView tv_title_name;
    @BindView(R.id.iv_baijian)
    ImageView iv_baijian;
    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.iv_switch)
    ImageView iv_switch;
    @BindView(R.id.iv_yuanshi)
    ImageView iv_yuanshi;
    @BindView(R.id.bt_paipan)
    Button bt_paipan;

    private String mingpan_id;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_fengshui_bangs;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("已绑定");
    }

    public static void actionStart(Context context, String mingpan_id) {
        Intent intent = new Intent(context, FengshuiBangSActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mingpan_id", mingpan_id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mingpan_id = getIntent().getStringExtra("mingpan_id");
        getNet();
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11022");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", mingpan_id);
        Gson gson = new Gson();
        OkGo.<AppResponse<FengshuiDetails.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<FengshuiDetails.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<FengshuiDetails.DataBean>> response) {
                        showLoadSuccess();
                        FengshuiDetails.DataBean dataBean = response.body().data.get(0);
                        String ls_jx = dataBean.getLs_jx();
                        if (ls_jx.equals("1")) {
                            iv_yuanshi.setImageResource(R.mipmap.baijian_pic_ji);
                        } else {
                            iv_yuanshi.setImageResource(R.mipmap.baijian_pic_xiong);
                        }


                        tv_title_name.setText(dataBean.getGoods_name());

                    }

                    @Override
                    public void onStart(Request<AppResponse<FengshuiDetails.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }

                    @Override
                    public void onError(Response<AppResponse<FengshuiDetails.DataBean>> response) {
                        super.onError(response);
                        showLoadFailed();
                    }
                });
    }

    @OnClick({R.id.iv_switch, R.id.bt_paipan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_switch:
                break;
            case R.id.bt_paipan:
                break;
        }
    }
}
