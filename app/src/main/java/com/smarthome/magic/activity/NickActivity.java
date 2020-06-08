package com.smarthome.magic.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppEvent;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.CarDetails;
import com.smarthome.magic.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NickActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_nickname);
        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);

    }

    @OnClick({R.id.rl_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_confirm:
                requestData();
                break;
        }
    }


    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00701");
        map.put("key", Constant.KEY);
        map.put("of_user_id", UserManager.getManager(this).getUserId());
        map.put("update_type", "1");
        map.put("user_name",etNickname.getText().toString());
        Gson gson = new Gson();
        OkGo.<AppResponse>post(Constant.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(final Response<AppResponse> response) {
                        AlertUtil.t(NickActivity.this,response.body().msg);
                        AppEvent.setMessage("update");
                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        AlertUtil.t(NickActivity.this, response.getException().getMessage());
                    }
                });
    }


}
