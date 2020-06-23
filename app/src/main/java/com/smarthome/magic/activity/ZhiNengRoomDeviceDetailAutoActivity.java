package com.smarthome.magic.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengRoomManageCreatBean;
import com.smarthome.magic.model.ZhiNengRoomManageSettingBean;
import com.smarthome.magic.model.ZhiiNengRoomDeviceRoomBean;
import com.suke.widget.SwitchButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengRoomDeviceDetailAutoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.iv_auto)
    ImageView iv_auto;
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
    @BindView(R.id.rl_device_state)
    RelativeLayout rl_device_state;
    @BindView(R.id.tv_auto_switch_title)
    TextView tv_auto_switch_title;
    @BindView(R.id.auto_switch_button)
    SwitchButton auto_switch_button;
    @BindView(R.id.tv_noti)
    TextView tv_noti;
    @BindView(R.id.rl_switch)
    RelativeLayout rl_switch;
    @BindView(R.id.rl_auto_switch)
    RelativeLayout rl_auto_switch;
    @BindView(R.id.ll_auto_weiyu_config)
    LinearLayout ll_auto_weiyu_config;
    @BindView(R.id.ll_auto_jiaohua_config)
    LinearLayout ll_auto_jiaohua_config;
    @BindView(R.id.rl_ertongmoshi)
    RelativeLayout rl_ertongmoshi;
    @BindView(R.id.switch_ertong)
    SwitchButton switch_ertong;
    @BindView(R.id.ll_online_state)
    LinearLayout ll_online_state;

    private Context context = ZhiNengRoomDeviceDetailAutoActivity.this;
    private String device_id = "";
    private String device_type = "";
    private ZhiiNengRoomDeviceRoomBean.DataBean dataBean;
    private boolean autoState = false;
    private boolean switchState = false;
    private boolean ertongSwitchState = false;

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
        getnet();
    }

    private void initView() {
        device_id = getIntent().getStringExtra("device_id");
        if (device_id == null) {
            device_id = "";
        }
        device_type = getIntent().getStringExtra("device_type");
        if (device_type == null) {
            device_type = "";
        }
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getnet();
            }
        });
        srLSmart.setEnableLoadMore(false);
        iv_auto.setOnClickListener(this);
        auto_switch_button.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    if (!switchState) {
                        String kaiqitishi = "";
                        if (device_type.equals("03")) {
                            kaiqitishi = "开启自动喂鱼？默认每天8点自动喂鱼，您可修改时间哦";
                        } else {
                            kaiqitishi = "开启自动浇花？默认每天自动浇花30秒，您可修改时间哦";
                        }
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomDeviceDetailAutoActivity.this,
                                "提示", kaiqitishi, "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {
                                auto_switch_button.setChecked(false);
                                switchState = false;
                                if (device_type.equals("03")) {
                                    ll_auto_weiyu_config.setVisibility(View.GONE);
                                } else {
                                    ll_auto_jiaohua_config.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void clickRight() {
                                auto_switch_button.setChecked(true);
                                switchState = true;
                                if (device_type.equals("03")) {
                                    ll_auto_weiyu_config.setVisibility(View.VISIBLE);
                                } else {
                                    ll_auto_jiaohua_config.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    }
                } else {
                    if (switchState) {
                        String guanbitishi = "";
                        if (device_type.equals("03")) {
                            guanbitishi = "您确定要关闭自动喂鱼吗？";
                        } else {
                            guanbitishi = "您确定要关闭自动浇花吗？";
                        }
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomDeviceDetailAutoActivity.this,
                                "提示", guanbitishi, "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {
                                auto_switch_button.setChecked(true);
                                switchState = true;
                                if (device_type.equals("03")) {
                                    ll_auto_weiyu_config.setVisibility(View.VISIBLE);
                                } else {
                                    ll_auto_jiaohua_config.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void clickRight() {
                                auto_switch_button.setChecked(false);
                                switchState = false;
                                if (device_type.equals("03")) {
                                    ll_auto_weiyu_config.setVisibility(View.GONE);
                                } else {
                                    ll_auto_jiaohua_config.setVisibility(View.GONE);
                                }
                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    }
                }
            }
        });

        switch_ertong.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    if (!ertongSwitchState) {
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomDeviceDetailAutoActivity.this,
                                "提示", "您要开启儿童模式吗？开启后，设备物理按键失效", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {
                                switch_ertong.setChecked(false);
                                ertongSwitchState = false;
                            }

                            @Override
                            public void clickRight() {
                                switch_ertong.setChecked(true);
                                ertongSwitchState = true;
                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    }
                } else {
                    if (ertongSwitchState) {
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomDeviceDetailAutoActivity.this,
                                "提示", "您要关闭儿童模式吗？关闭后，设备物理按键恢复", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {
                                switch_ertong.setChecked(true);
                                ertongSwitchState = true;
                            }

                            @Override
                            public void clickRight() {
                                switch_ertong.setChecked(false);
                                ertongSwitchState = false;
                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    }
                }
            }
        });

        if (device_type.equals("0") || device_type.equals("60")) {
            rl_switch.setVisibility(View.GONE);
        } else if (device_type.equals("03")) {
            tv_auto_switch_title.setText("自动喂鱼设置");
            ll_online_state.setVisibility(View.VISIBLE);
            iv_auto.setBackgroundResource(R.mipmap.img_lijiweiyu);
            rl_auto_switch.setVisibility(View.VISIBLE);
            rl_ertongmoshi.setVisibility(View.VISIBLE);
        } else if (device_type.equals("04")) {
            tv_auto_switch_title.setText("自动浇花设置");
            ll_online_state.setVisibility(View.VISIBLE);
            iv_auto.setBackgroundResource(R.mipmap.img_lijijiaohua);
            rl_auto_switch.setVisibility(View.VISIBLE);
            rl_ertongmoshi.setVisibility(View.VISIBLE);
        } else if (device_type.equals("11") || device_type.equals("12") || device_type.equals("13")
                || device_type.equals("14") || device_type.equals("15")) {
            rl_device_state.setVisibility(View.VISIBLE);
        } else {
            rl_switch.setVisibility(View.VISIBLE);
        }
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
            case R.id.iv_auto:
                if (autoState) {
                    autoState = false;
                    if (dataBean.getDevice_type().equals("03")) {
                        iv_auto.setBackgroundResource(R.mipmap.img_lijiweiyu);
                    } else {
                        iv_auto.setBackgroundResource(R.mipmap.img_lijijiaohua);
                    }
                } else {
                    autoState = true;
                    if (dataBean.getDevice_type().equals("03")) {
                        iv_auto.setBackgroundResource(R.mipmap.img_tingzhiweiyu);
                    } else {
                        iv_auto.setBackgroundResource(R.mipmap.img_tingzhijiaohua);
                    }
                }
                break;
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
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>> response) {
                        if (srLSmart != null) {
                            srLSmart.setEnableRefresh(true);
                            srLSmart.finishRefresh();
                            srLSmart.setEnableLoadMore(false);
                        }
                        if (response.body().msg.equals("ok")) {
                            dataBean = response.body().data.get(0);
                            Glide.with(mContext).load(dataBean.getDevice_type_pic()).into(iv_head);
                            tv_family.setText(dataBean.getFamily_name());
                            tv_room.setText(dataBean.getRoom_name());
                            tv_device_name.setText(dataBean.getDevice_name());
                            tv_device_type.setText(dataBean.getDevice_name());
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>> response) {
                        String str = response.getException().getMessage();
                        Log.i("cuifahuo", str);
                        String[] str1 = str.split("：");
                        if (str1.length == 3) {
                            if (srLSmart != null) {
                                srLSmart.setEnableRefresh(true);
                                srLSmart.finishRefresh();
                                srLSmart.setEnableLoadMore(false);
                            }
                            Toast.makeText(context, str1[2], Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
