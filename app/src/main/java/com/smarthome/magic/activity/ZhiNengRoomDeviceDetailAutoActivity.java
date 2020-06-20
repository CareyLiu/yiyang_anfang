package com.smarthome.magic.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengRoomManageSettingBean;
import com.smarthome.magic.model.ZhiiNengRoomDeviceRoomBean;
import com.suke.widget.SwitchButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengRoomDeviceDetailAutoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_family)
    TextView tv_family;
    @BindView(R.id.rl_room)
    RelativeLayout rl_room;
    @BindView(R.id.tv_room)
    TextView tv_room;
    @BindView(R.id.rl_device_name)
    RelativeLayout rl_device_name;
    @BindView(R.id.tv_device_name)
    TextView tv_device_name;
    @BindView(R.id.tv_device_type)
    TextView tv_device_type;
    @BindView(R.id.tv_switch_title)
    TextView tv_switch_title;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.tv_noti)
    TextView tv_noti;

    private Context context = ZhiNengRoomDeviceDetailAutoActivity.this;
    private String device_id = "";
    private ZhiiNengRoomDeviceRoomBean.DataBean dataBean;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhineng_room_device_detail;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightMode(this);
        initToolbar();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        device_id = getIntent().getStringExtra("device_id");
        if (device_id == null) {
            device_id = "";
        }
        getnet();
    }

    private void initView() {

    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设备详情");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_room:

                break;
            case R.id.rl_device_name:
                break;
        }
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16035");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("device_id", device_id);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>> response) {
                        if (response.body().msg.equals("ok")) {
                            dataBean = response.body().data.get(0);
                            Glide.with(mContext).load(dataBean.getDevice_type_pic()).into(iv_head);
                            tv_family.setText(dataBean.getFamily_name());
                            tv_room.setText(dataBean.getRoom_name());
                            tv_device_name.setText(dataBean.getDevice_name());
                            tv_device_type.setText(dataBean.getDevice_name());

                        }
                    }
                });
    }
}
