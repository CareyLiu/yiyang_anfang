package com.smarthome.magic.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.widget.RelativeLayout;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.RuleListAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;

import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.LoginUser;
import com.smarthome.magic.service.HeaterMqttService;
import com.smarthome.magic.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.smarthome.magic.get_net.Urls.SERVER_URL;

/**
 * Created by Sl on 2019/6/10.
 * 四合一登录
 */


public class SelectLoginActivity extends BaseActivity {
    @BindView(R.id.rl_close)
    RelativeLayout rlClose;
    @BindView(R.id.list)
    LRecyclerView list;

    RuleListAdapter ruleListAdapter;
    LRecyclerViewAdapter lRecyclerViewAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login);
        ButterKnife.bind(this);

        ruleListAdapter = new RuleListAdapter(this);
        ruleListAdapter.setDataList(LoginActivity.userlist);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(ruleListAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(lRecyclerViewAdapter);
        list.refreshComplete(10);

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                login(LoginActivity.userlist.get(position).getSubsystem_id(),
                        LoginActivity.userlist.get(position).getUser_id_key(),
                        LoginActivity.userlist.get(position).getPower_state());
            }
        });

    }

    @OnClick({R.id.rl_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }


    /**
     * §6.5	车主端，代理商端，维修端，商家端四端合一登陆
     * <p>
     * code	请求码(00051)
     * key	身份标识
     * power_state	登录类型（1.客户 2.修配厂3.智能家居代理商 4.商城商家）
     * subsystem_id	子系统id(写死)（那个子系统就传那个）
     * 维修厂端传witwork  代理商端传wit 商家端传jcz
     * 车主端没有，传空
     * user_id_key	加密的用户id
     * phone_model	手机型号
     */
    private void login(String subsystem_id, String user_id_key, final String power_state) {

        Map<String, String> map = new HashMap<>();
        map.put("code", "00051");
        map.put("key", Urls.key);
        map.put("subsystem_id", subsystem_id);
        map.put("user_id_key", user_id_key);
        map.put("power_state", power_state);
        Gson gson = new Gson();
        OkGo.<AppResponse<LoginUser.DataBean>>post(SERVER_URL + "index/login")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<LoginUser.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<LoginUser.DataBean>> response) {
                        UserManager.getManager(SelectLoginActivity.this).saveUser(response.body().data.get(0));
                        // HeaterMqttService.subscribe();
                        PreferenceHelper.getInstance(getApplication()).putString("power_state", power_state);
                        switch (power_state) {
                            case "1"://车主
                                startActivity(new Intent(SelectLoginActivity.this, HomeActivity.class));
                                break;
                            case "2"://维修厂
                                break;
                            case "3"://代理商
                                startActivity(new Intent(SelectLoginActivity.this, ServiceActivity.class));
                                break;

                        }


                    }

                    @Override
                    public void onError(Response<AppResponse<LoginUser.DataBean>> response) {
                        AlertUtil.t(SelectLoginActivity.this, response.getException().getMessage());
                    }
                });


    }


}
