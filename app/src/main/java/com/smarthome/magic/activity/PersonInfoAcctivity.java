package com.smarthome.magic.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppEvent;
import com.smarthome.magic.config.AppResponse;

import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ServiceInfo;
import com.smarthome.magic.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonInfoAcctivity extends BaseActivity implements Observer {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info);
        AppEvent.getClassEvent().addObserver(this);
        ButterKnife.bind(this);
        requestData();
    }

    @OnClick({R.id.rl_back, R.id.tv_nick_name, R.id.tv_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_nick_name:
                startActivity(new Intent(this,ReviseNicknameActivity.class).putExtra("nick_name",tvNickName.getText()));
                break;
            case R.id.tv_phone:
                AlertUtil.t(this,"手机号码不能修改");
                break;
        }
    }

    public void requestData(){
        Map<String, String> map = new HashMap<>();
        map.put("code", "03314");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(PersonInfoAcctivity.this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<ServiceInfo.DataBean>>post(Urls.SERVER_URL + "wit/app/car/witAgent")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ServiceInfo.DataBean>>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onSuccess(final Response<AppResponse<ServiceInfo.DataBean>> response) {
                       tvNickName.setText(response.body().data.get(0).getUser_name());
                       tvPhone.setText(response.body().data.get(0).getUser_phone());

                    }

                    @Override
                    public void onError(Response<AppResponse<ServiceInfo.DataBean>> response) {
                        AlertUtil.t(PersonInfoAcctivity.this,response.getException().getMessage());
                    }
                });
    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("update_nick"))
        requestData();

    }
}
