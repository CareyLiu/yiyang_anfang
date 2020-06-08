package com.smarthome.magic.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.CarBrand;
import com.smarthome.magic.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sl on 2019/6/18.
 *
 */

public class HandAddActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.et_number)
    EditText mEtNumber;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;

    @Override
    public void onCreate(Bundle savedInstanceStateundle) {
        super.onCreate(savedInstanceStateundle);
        setContentView(R.layout.activity_hand_add);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);
        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.rl_back, R.id.bt_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.bt_submit:
                requestData();
                break;
        }
    }


    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03105");
        map.put("key", Constant.KEY);
        map.put("token", UserManager.getManager(HandAddActivity.this).getAppToken());
        map.put("user_car_type", "1");
        map.put("ccid",mEtNumber.getText().toString());
        map.put("car_brand_id_one",PreferenceHelper.getInstance(HandAddActivity.this).getString("brand_id",""));
        map.put("car_brand_name_one",PreferenceHelper.getInstance(HandAddActivity.this).getString("brand_name",""));
        map.put("car_brand_url_one",PreferenceHelper.getInstance(HandAddActivity.this).getString("brand_pic",""));
        map.put("car_brand_id_two",PreferenceHelper.getInstance(HandAddActivity.this).getString("mode_id",""));
        map.put("car_brand_name_two",PreferenceHelper.getInstance(HandAddActivity.this).getString("mode_name",""));
        map.put("car_brand_url_two",PreferenceHelper.getInstance(HandAddActivity.this).getString("mode_pic",""));
        Gson gson = new Gson();
        OkGo.<AppResponse<CarBrand.DataBean>>post(Constant.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CarBrand.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<CarBrand.DataBean>> response) {
                            showToast(response.body().msg);

                    }

                    @Override
                    public void onError(Response<AppResponse<CarBrand.DataBean>> response) {
                        AlertUtil.t(HandAddActivity.this,response.getException().getMessage());
                    }
                });
    }

}
