package com.smarthome.magic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.MyQianBaoActivity;
import com.smarthome.magic.app.App;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.Message;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.TimeCount;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneCheckActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private TimeCount timeCount;
    private String smsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_check);


        ButterKnife.bind(this);
        StatusBarUtil.setLightMode(this);

        TextView tvShouJiYanZheng = findViewById(R.id.tv_shoujiyanzheng);
        if (getIntent().getStringExtra("mod_id").equals("0006")) {
            tvShouJiYanZheng.setText("支付密码");
        }
        timeCount = new TimeCount(60000, 1000, tvGetCode);
        tvPhone.setText(PreferenceHelper.getInstance(this).getString("user_phone", ""));
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 4) {
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.bg_shape_app));
                    btnSubmit.setEnabled(true);
                } else {
                    btnSubmit.setBackground(getResources().getDrawable(R.drawable.bg_shape_app_disabled));
                    btnSubmit.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.tv_get_code, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_get_code:
                getCode(getIntent().getStringExtra("mod_id"));
                break;
            case R.id.btn_submit:
                requestData();
                break;
        }
    }


    /**
     * 获取短信验证码
     */
    private void getCode(String modeId) {

        Map<String, String> map = new HashMap<>();
        map.put("code", "00001");
        map.put("key", Constant.KEY);
        map.put("token", UserManager.getManager(getApplicationContext()).getAppToken());
        map.put("user_phone", PreferenceHelper.getInstance(PhoneCheckActivity.this).getString("user_phone", ""));
        map.put("mod_id", modeId);
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(Constant.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        showToast(response.body().msg);
                        timeCount.start();
                        smsId = response.body().data.get(0).getSms_id();
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        AlertUtil.t(PhoneCheckActivity.this, response.getException().getMessage());
                        timeCount.cancel();
                        timeCount.onFinish();
                    }
                });

    }


    /**
     * 提交验证
     */
    private void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00006");
        map.put("key", Constant.KEY);
        map.put("sms_id", smsId);
        map.put("sms_code", etCode.getText().toString());
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(Constant.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        AlertUtil.t(PhoneCheckActivity.this, response.body().msg);
                        switch (getIntent().getStringExtra("mod_id")) {
                            case "0006"://修改支付密码
                                startActivity(new Intent(PhoneCheckActivity.this, RevisePayActivity.class).putExtra("sms_id", smsId).putExtra("sms_code", etCode.getText().toString()));
                                PreferenceHelper.getInstance(PhoneCheckActivity.this).putString(App.CUNCHU_ZHIFUMIMA, "1");
                                finish();
                                break;
                            case "0007"://修改登录密码
                                startActivity(new Intent(PhoneCheckActivity.this, ReviseLoginActivity.class).putExtra("sms_id", smsId).putExtra("sms_code", etCode.getText().toString()));
                                break;
                            case "0008"://修改提现账号
                                startActivity(new Intent(PhoneCheckActivity.this, CashAccountActivity.class).putExtra("sms_id", smsId).putExtra("sms_code", etCode.getText().toString()));

                                finish();
                                break;
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        AlertUtil.t(PhoneCheckActivity.this, response.getException().getMessage());

                    }
                });

    }

}
