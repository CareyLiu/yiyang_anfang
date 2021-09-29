package com.yiyang.cn.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.util.Y;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.app.UIHelper;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.common.StringUtils;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.Message;
import com.yiyang.cn.util.TimeCount;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FengNuanJieBangActivity extends BaseActivity {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.ed_code)
    EditText ed_code;
    @BindView(R.id.tv_yzm)
    TextView tv_yzm;
    @BindView(R.id.bt_ok)
    Button bt_ok;

    private TimeCount timeCount;
    private String user_phone;
    private String smsId;

    @Override
    public boolean showToolBar() {
        return false;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_shuinuan_jie;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, FengNuanJieBangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        user_phone = PreferenceHelper.getInstance(mContext).getString("user_phone", "");
        tv_phone.setText(user_phone);
        timeCount = new TimeCount(60000, 1000, tv_yzm);

    }

    @OnClick({R.id.tv_yzm, R.id.bt_ok, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_yzm:
                get_code();
                break;
            case R.id.bt_ok:
                jiebang();
                break;
            case R.id.back:
                finish();
                break;

        }
    }

    private void jiebang() {
        String code = ed_code.getText().toString();


        if (StringUtils.isEmpty(smsId)) {
            UIHelper.ToastMessage(mContext, "请获取验证码");
            return;
        }

        if (StringUtils.isEmpty(code)) {
            UIHelper.ToastMessage(mContext, "请输入验证码");
            return;
        }

        String ccid = PreferenceHelper.getInstance(this).getString("ccid", "");


        Map<String, String> map = new HashMap<>();
        map.put("code", "03202");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("ccid", ccid);
        map.put("sms_id", smsId);
        map.put("sms_code", code);
        map.put("is_platform", "1");
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(Urls.SERVER_URL + "fn/common")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {

                        UIHelper.ToastMessage(mContext, "解绑成功");
                        finish();
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        Y.tError(response);
                    }
                });

    }

    private void get_code() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "00001");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_phone", user_phone);
        map.put("mod_id", "0326");
        Gson gson = new Gson();
        OkGo.<AppResponse<Message.DataBean>>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Message.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Message.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "验证码获取成功");
                        timeCount.start();
                        if (response.body().data.size() > 0)
                            smsId = response.body().data.get(0).getSms_id();
                    }

                    @Override
                    public void onError(Response<AppResponse<Message.DataBean>> response) {
                        timeCount.cancel();
                        timeCount.onFinish();

                        Y.tError(response);
                    }
                });
    }
}
