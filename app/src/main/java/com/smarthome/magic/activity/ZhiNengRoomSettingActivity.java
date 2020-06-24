package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.ZhiNengRoomManageSettingAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_Success;
import com.smarthome.magic.dialog.ZhiNengFamilyAddDIalog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.AddressModel;
import com.smarthome.magic.model.ZhiNengFamilyEditBean;
import com.smarthome.magic.model.ZhiNengRoomManageBean;
import com.smarthome.magic.model.ZhiNengRoomManageSettingBean;
import com.smarthome.magic.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengRoomSettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rl_room_name)
    RelativeLayout rl_room_name;
    @BindView(R.id.tv_room_name)
    TextView tv_room_name;
    @BindView(R.id.tv_room_num)
    TextView tv_room_num;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_room_delete)
    TextView tv_room_delete;
    private Context context = ZhiNengRoomSettingActivity.this;
    private String member_type = "";
    private String room_id = "";
    private String family_id = "";
    private String room_name = "";
    private List<ZhiNengRoomManageSettingBean.DataBean> dataBeanList = new ArrayList<>();
    private ZhiNengRoomManageSettingAdapter zhiNengRoomManageSettingAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhineng_room_setting;
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
        member_type = getIntent().getStringExtra("member_type");
        if (member_type == null) {
            member_type = "";
        }
        room_id = getIntent().getStringExtra("room_id");
        if (room_id == null) {
            room_id = "";
        }
        family_id = getIntent().getStringExtra("family_id");
        if (family_id == null) {
            family_id = "";
        }
        room_name = getIntent().getStringExtra("room_name");
        if (room_name == null) {
            room_name = "";
        }
        getnet();
    }

    private void initView() {
        rl_room_name.setOnClickListener(this);
        tv_room_delete.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        zhiNengRoomManageSettingAdapter = new ZhiNengRoomManageSettingAdapter(R.layout.item_zhineng_room_setting, dataBeanList);
        zhiNengRoomManageSettingAdapter.setEmptyView(LayoutInflater.from(context).inflate(R.layout.activity_zhineng_room_setting_none, null));
        zhiNengRoomManageSettingAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(zhiNengRoomManageSettingAdapter);

        zhiNengRoomManageSettingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZhiNengRoomManageSettingBean.DataBean dataBean = (ZhiNengRoomManageSettingBean.DataBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("device_id", dataBean.getDevice_id());
                bundle.putString("device_type", dataBean.getDevice_type());
                bundle.putString("member_type",member_type);
                startActivity(new Intent(context, ZhiNengRoomDeviceDetailAutoActivity.class).putExtras(bundle));
            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ROOM_MANAGE_CHANGENAME) {
                    changeRoom(message.content.toString());
                }
            }
        }));
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("房间设置");
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
            case R.id.rl_room_name:
                if (member_type.equals("1")) {
                    //管理员身份，可以编辑房间名字
                    if (room_id.equals("0")) {
                        //默认房间不能修改名字
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomSettingActivity.this,
                                "提示", "默认房间无法修改名字", "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {

                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    } else {
                        ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(context, ConstanceValue.MSG_ROOM_MANAGE_CHANGENAME);
                        zhiNengFamilyAddDIalog.show();
                    }
                } else {
                    //成员身份，不可以编辑房间名字
                    MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomSettingActivity.this,
                            "提示", "共享家庭成员无权限", "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                        @Override
                        public void clickLeft() {

                        }

                        @Override
                        public void clickRight() {

                        }
                    });
                    myCarCaoZuoDialog_caoZuoTIshi.show();
                }
                break;
            case R.id.tv_room_delete:
                if (member_type.equals("1")) {
                    //管理员身份，可以删除房间
                    if (room_id.equals("0")) {
                        //默认房间不能修改名字
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomSettingActivity.this,
                                "提示", "默认房间无法删除", "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {

                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    } else {
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomSettingActivity.this,
                                "提示", "确定要删除该房间吗？", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {
                                deleteRoom();
                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    }
                } else {
                    //成员身份，不可以删除房间
                    MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengRoomSettingActivity.this,
                            "提示", "共享家庭成员无权限", "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                        @Override
                        public void clickLeft() {

                        }

                        @Override
                        public void clickRight() {

                        }
                    });
                    myCarCaoZuoDialog_caoZuoTIshi.show();
                }
                break;
        }
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16026");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("room_id", room_id);
        map.put("family_id", family_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengRoomManageSettingBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengRoomManageSettingBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengRoomManageSettingBean.DataBean>> response) {
                        dataBeanList.clear();
                        dataBeanList.addAll(response.body().data);
                        tv_room_name.setText(room_name);
                        tv_room_num.setText("该房间有" + dataBeanList.size() + "个设备");
                        zhiNengRoomManageSettingAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 修改房间名字
     */
    private void changeRoom(String roomName) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16021");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("family_id", family_id);
        map.put("room_name", roomName);
        if (!room_id.isEmpty()) {
            map.put("room_id", room_id);
        }
        if (!room_name.isEmpty()) {
            map.put("old_name", room_name);
        }
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));

        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        if (response.body().msg.equals("ok")) {
                            room_name = roomName;
                            tv_room_name.setText(room_name);
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        String str = response.getException().getMessage();
                        Log.i("cuifahuo", str);
                        String[] str1 = str.split("：");
                        if (str1.length == 3) {
                            MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,
                                    "提示", str1[2], "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {

                                }
                            });
                            myCarCaoZuoDialog_caoZuoTIshi.show();
                        }
                    }
                });
    }

    /**
     * 删除房间
     */
    private void deleteRoom() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16027");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("room_id", room_id);
        map.put("family_id", family_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));

        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        if (response.body().msg.equals("ok")) {
                            MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(ZhiNengRoomSettingActivity.this,
                                    "成功", "您已经成功删除该房间", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {
                                    finish();
                                }
                            });
                            dialog_success.show();
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        String str = response.getException().getMessage();
                        Log.i("cuifahuo", str);
                        String[] str1 = str.split("：");
                        if (str1.length == 3) {
                            MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,
                                    "提示", str1[2], "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {

                                }
                            });
                            myCarCaoZuoDialog_caoZuoTIshi.show();
                        }
                    }
                });
    }
}
