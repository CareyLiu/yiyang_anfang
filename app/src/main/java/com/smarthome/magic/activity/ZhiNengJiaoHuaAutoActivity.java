package com.smarthome.magic.activity;import android.content.Context;import android.content.Intent;import android.os.Bundle;import android.util.Log;import android.view.View;import android.widget.ImageView;import android.widget.LinearLayout;import android.widget.RelativeLayout;import android.widget.TextView;import android.widget.Toast;import androidx.annotation.NonNull;import com.google.gson.Gson;import com.jaeger.library.StatusBarUtil;import com.lzy.okgo.OkGo;import com.lzy.okgo.model.Response;import com.lzy.okgo.request.base.Request;import com.rairmmd.andmqtt.AndMqtt;import com.rairmmd.andmqtt.MqttSubscribe;import com.scwang.smartrefresh.layout.SmartRefreshLayout;import com.scwang.smartrefresh.layout.api.RefreshLayout;import com.scwang.smartrefresh.layout.listener.OnRefreshListener;import com.smarthome.magic.R;import com.smarthome.magic.app.App;import com.smarthome.magic.app.BaseActivity;import com.smarthome.magic.app.ConstanceValue;import com.smarthome.magic.app.Notice;import com.smarthome.magic.app.UIHelper;import com.smarthome.magic.callback.JsonCallback;import com.smarthome.magic.common.StringUtils;import com.smarthome.magic.config.AppResponse;import com.smarthome.magic.config.PreferenceHelper;import com.smarthome.magic.config.UserManager;import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;import com.smarthome.magic.dialog.MyCarCaoZuoDialog_Success;import com.smarthome.magic.dialog.ZhiNengFamilyAddDIalog;import com.smarthome.magic.dialog.ZhiNengRoomDeviceAutoJiaohuaDialog;import com.smarthome.magic.get_net.Urls;import com.smarthome.magic.inter.OnItemDialogClickListener;import com.smarthome.magic.model.ZhiNengFamilyEditBean;import com.smarthome.magic.model.ZhiNengWeiYuBean;import com.smarthome.magic.mqtt_zhiling.ZnjjMqttMingLing;import com.smarthome.magic.util.DoMqttValue;import com.suke.widget.SwitchButton;import org.eclipse.paho.client.mqttv3.IMqttActionListener;import org.eclipse.paho.client.mqttv3.IMqttToken;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;import butterknife.BindView;import rx.android.schedulers.AndroidSchedulers;import rx.functions.Action1;import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;public class ZhiNengJiaoHuaAutoActivity extends BaseActivity implements View.OnClickListener {    @BindView(R.id.iv_lijiweiyu)    ImageView ivLijiweiyu;    @BindView(R.id.tv_lijiweiyu)    TextView tvLijiweiyu;    @BindView(R.id.rl_jiaohua)    RelativeLayout rlJiaohua;    @BindView(R.id.iv_tingzhiweiyu)    ImageView ivTingzhiweiyu;    @BindView(R.id.tv_tingzhiweiyu)    TextView tvTingzhiweiyu;    @BindView(R.id.rl_tingzhijiaohua)    RelativeLayout rlTingzhijiaohua;    @BindView(R.id.tv_auto_switch_title)    TextView tvAutoSwitchTitle;    @BindView(R.id.auto_switch_button)    SwitchButton autoSwitchButton;    @BindView(R.id.rl_auto_switch)    RelativeLayout rlAutoSwitch;    @BindView(R.id.tv_jiaohua_preset_time)    TextView tvJiaohuaPresetTime;    @BindView(R.id.rl_jiaohua_preset_time)    RelativeLayout rlJiaohuaPresetTime;    @BindView(R.id.tv_jiaohua_interval_time)    TextView tvJiaohuaIntervalTime;    @BindView(R.id.rl_jiaohua_interval_time)    RelativeLayout rlJiaohuaIntervalTime;    @BindView(R.id.tv_jiaohua_interval_time_type)    TextView tvJiaohuaIntervalTimeType;    @BindView(R.id.tv_jiaohua_tijiao)    TextView tvJiaohuaTijiao;    @BindView(R.id.ll_auto_jiaohua_config)    LinearLayout llAutoJiaohuaConfig;    @BindView(R.id.tv_model_title)    TextView tvModelTitle;    @BindView(R.id.switch_ertong)    SwitchButton switchErtong;    @BindView(R.id.rl_ertongmoshi)    RelativeLayout rlErtongmoshi;    @BindView(R.id.tv_family_title)    TextView tvFamilyTitle;    @BindView(R.id.tv_family)    TextView tvFamily;    @BindView(R.id.tv_room_title)    TextView tvRoomTitle;    @BindView(R.id.tv_room)    TextView tvRoom;    @BindView(R.id.img_room_into)    ImageView imgRoomInto;    @BindView(R.id.rl_room)    RelativeLayout rlRoom;    @BindView(R.id.tv_device_name_title)    TextView tvDeviceNameTitle;    @BindView(R.id.tv_device_name)    TextView tvDeviceName;    @BindView(R.id.img_device_into)    ImageView imgDeviceInto;    @BindView(R.id.rl_device_name)    RelativeLayout rlDeviceName;    @BindView(R.id.tv_device_type_title)    TextView tvDeviceTypeTitle;    @BindView(R.id.tv_device_type)    TextView tvDeviceType;    @BindView(R.id.tv_device_state_title)    TextView tvDeviceStateTitle;    @BindView(R.id.tv_device_state)    TextView tvDeviceState;    @BindView(R.id.rl_device_state)    RelativeLayout rlDeviceState;    @BindView(R.id.iv_tishi)    ImageView ivTishi;    @BindView(R.id.tv_noti)    TextView tvNoti;    @BindView(R.id.tv_room_delete)    TextView tvRoomDelete;    @BindView(R.id.srL_smart)    SmartRefreshLayout srLSmart;    @BindView(R.id.tv_jiaohua_kaishishijian)    TextView tvJiaohuaKaishishijian;    @BindView(R.id.rl_jiaohua_kaishishijian)    RelativeLayout rlJiaohuaKaishishijian;    private Context context = ZhiNengJiaoHuaAutoActivity.this;    private String device_id = "";    private String device_type = "";    private String member_type = "";    private String device_ccid = "";//ccid    private String workState = "";//工作状态是否开启    private ZhiNengWeiYuBean.DataBean dataBean;    private boolean autoState = false;    private boolean switchState = false;    private boolean ertongSwitchState = false;    public ZnjjMqttMingLing mqttMingLing = null;    private String erTongMoShi = "0";// 儿童模式默认关闭    @Override    public int getContentViewResId() {        return R.layout.activity_zhineng_jiaohua_detail;    }    @Override    public void initImmersion() {        mImmersionBar.with(this).statusBarColor(R.color.white).init();    }    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        StatusBarUtil.setLightMode(this);        PreferenceHelper.getInstance(mContext).putString(App.CHOOSE_KONGZHI_XIANGMU, DoMqttValue.ZHINENGJIAJU);        initToolbar();        initView();        getnet();        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {            @Override            public void call(Notice message) {            }        }));        rlJiaohua.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                UIHelper.ToastMessage(mContext, "执行喂鱼");                String quanshu = PreferenceHelper.getInstance(mContext).getString(App.WEIYUQUANSHU, "01");                mqttMingLing.setWeiYuAction(device_ccid, "01", "01", new IMqttActionListener() {                    @Override                    public void onSuccess(IMqttToken asyncActionToken) {                        //ivYushi.setVisibility(View.VISIBLE);                        //Glide.with(mContext).load(R.drawable.yushi).into(ivYushi);                    }                    @Override                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {                        UIHelper.ToastMessage(mContext, "未发送指令");                    }                });            }        });        rlTingzhijiaohua.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                UIHelper.ToastMessage(mContext, "停止喂鱼");                mqttMingLing.setAction(device_ccid, "02", new IMqttActionListener() {                    @Override                    public void onSuccess(IMqttToken asyncActionToken) {                        // ivYushi.setVisibility(View.GONE);                    }                    @Override                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {                        UIHelper.ToastMessage(mContext, "未发送指令");                    }                });            }        });    }////    private void sheZhiWeiYu(String strKaiQiOrGuanBi) {////        /**//         * @param zhuangZhiId   装置id//         * @param erTongMoShi   儿童模式 1.开2.关（开启状态物理按键失效）//         * @param ziDongMoShi   自动模式 1.开启2.关闭//         * @param kaiShiShiJian 开始时间  0101**  56位补齐//         * @param shiJianJianGe 时间间隔  01h 或 01day//         * @param listener      监听//         *///        String weiyuTime = tvWeiyuPresetTime.getText().toString();//        String shijianJianGe = tvWeiyuIntervalTime.getText().toString() + "h";////        Log.i("shijianJianGe", shijianJianGe);////        weiyuTime = weiyuTime.replace(":", "");//        Log.i("tv_weiyu_preset_time", weiyuTime);////    }    private void initView() {        device_id = getIntent().getStringExtra("device_id");        if (device_id == null) {            device_id = "";        }        device_type = getIntent().getStringExtra("device_type");        if (device_type == null) {            device_type = "";        }        member_type = getIntent().getStringExtra("member_type");        if (member_type == null) {            member_type = "";        }        srLSmart.setOnRefreshListener(new OnRefreshListener() {            @Override            public void onRefresh(@NonNull RefreshLayout refreshLayout) {                getnet();            }        });        srLSmart.setEnableLoadMore(false);        rlRoom.setOnClickListener(this);        rlDeviceName.setOnClickListener(this);        rlJiaohuaIntervalTime.setOnClickListener(this);        rlJiaohuaPresetTime.setOnClickListener(this);        tvRoomDelete.setOnClickListener(this);        tvJiaohuaTijiao.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                //sheZhiWeiYu("1");                //  setWeiYuNet();                setJiaohuaTiJiao();            }        });        autoSwitchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {            @Override            public void onCheckedChanged(SwitchButton view, boolean isChecked) {                if (isChecked) {                    if (!switchState) {                        String kaiqitishi = "";                        kaiqitishi = "开启自动浇花？默认每天自动浇花30秒，您可修改时间哦";                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengJiaoHuaAutoActivity.this,                                "提示", kaiqitishi, "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {                            @Override                            public void clickLeft() {                                autoSwitchButton.setChecked(false);                                switchState = false;                                llAutoJiaohuaConfig.setVisibility(View.GONE);                            }                            @Override                            public void clickRight() {                                autoSwitchButton.setChecked(true);                                switchState = true;                                llAutoJiaohuaConfig.setVisibility(View.VISIBLE);                            }                        });                        myCarCaoZuoDialog_caoZuoTIshi.show();                    }                }            }        });        switchErtong.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {            @Override            public void onCheckedChanged(SwitchButton view, boolean isChecked) {                if (isChecked) {                    if (!ertongSwitchState) {                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengJiaoHuaAutoActivity.this,                                "提示", "您要开启儿童模式吗？开启后，设备物理按键失效", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {                            @Override                            public void clickLeft() {                                switchErtong.setChecked(false);                                ertongSwitchState = false;                                erTongMoShi = "0";                                /**                                 * 儿童模式 M09030101.（喂鱼、浇花装置使用，开启时物理按键失效）                                 * M09 : 命令码                                 * 0301：装置id                                 * 01：开启；  02：关闭                                 */                                /**                                 * @param zhuangZhiId   装置id                                 * @param caoZuoFangShi 方式 打开或关闭                                 * @return                                 */                                mqttMingLing.setErTongMoShi(device_id, "0", new IMqttActionListener() {                                    @Override                                    public void onSuccess(IMqttToken asyncActionToken) {                                        UIHelper.ToastMessage(mContext, "关闭儿童模式");                                    }                                    @Override                                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {                                    }                                });                            }                            @Override                            public void clickRight() {                                switchErtong.setChecked(true);                                ertongSwitchState = true;                                erTongMoShi = "1";                                /**                                 * 儿童模式 M09030101.（喂鱼、浇花装置使用，开启时物理按键失效）                                 * M09 : 命令码                                 * 0301：装置id                                 * 01：开启；  02：关闭                                 */                                /**                                 * @param zhuangZhiId   装置id                                 * @param caoZuoFangShi 方式 打开或关闭                                 * @return                                 */                                mqttMingLing.setErTongMoShi(device_id, "1", new IMqttActionListener() {                                    @Override                                    public void onSuccess(IMqttToken asyncActionToken) {                                        UIHelper.ToastMessage(mContext, "打开儿童模式成功");                                    }                                    @Override                                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {                                    }                                });                            }                        });                        myCarCaoZuoDialog_caoZuoTIshi.show();                    }                } else {                    if (ertongSwitchState) {                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengJiaoHuaAutoActivity.this,                                "提示", "您要关闭儿童模式吗？关闭后，设备物理按键恢复", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {                            @Override                            public void clickLeft() {                                switchErtong.setChecked(true);                                ertongSwitchState = true;                                erTongMoShi = "0";                            }                            @Override                            public void clickRight() {                                switchErtong.setChecked(false);                                ertongSwitchState = false;                                erTongMoShi = "1";                            }                        });                        myCarCaoZuoDialog_caoZuoTIshi.show();                    }                }            }        });        tvAutoSwitchTitle.setText("自动喂鱼设置");        // llOnlineState.setVisibility(View.VISIBLE);        rlAutoSwitch.setVisibility(View.VISIBLE);        rlErtongmoshi.setVisibility(View.VISIBLE);        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {            @Override            public void call(Notice message) {                if (message.type == ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME) {                    changeDevice(message.content.toString());                }            }        }));    }    @Override    public boolean showToolBar() {        return true;    }    @Override    protected void initToolbar() {        super.initToolbar();        tv_title.setText("设备详情");        tv_title.setTextSize(17);        tv_title.setTextColor(getResources().getColor(R.color.black));        mToolbar.setNavigationIcon(R.mipmap.backbutton);        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                finish();            }        });    }    @Override    public void onClick(View v) {        switch (v.getId()) {            case R.id.iv_auto:                break;            case R.id.rl_room:                Bundle bundle = new Bundle();                bundle.putString("device_id", dataBean.getDevice_id());                bundle.putString("family_id", dataBean.getFamily_id());                startActivity(new Intent(context, ZhiNengRoomManageActivity.class).putExtras(bundle));                break;            case R.id.rl_device_name:                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(context, ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME);                zhiNengFamilyAddDIalog.show();                break;            case R.id.tv_room_delete:                MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,                        "提示", "确定要删除设备吗？", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {                    @Override                    public void clickLeft() {                    }                    @Override                    public void clickRight() {                        if (member_type.equals("1")) {                            if (dataBean.getDevice_type().equals("0")) {                                //删除主控设备                                deleteMainDevice();                            } else {                                //删除设备                                deleteDevice();                            }                        } else {                            Toast.makeText(context, "操作失败，需要管理员身份", Toast.LENGTH_SHORT).show();                        }                    }                });                myCarCaoZuoDialog_caoZuoTIshi.show();                break;            case R.id.rl_jiaohua_kaishishijian:                //设置喂鱼开始时间                List<String> weiyuPresetTimeList = new ArrayList<>();                for (int i = 0; i < 24; i++) {                    weiyuPresetTimeList.add(String.format("%02d", i) + ":00");                }                ZhiNengRoomDeviceAutoJiaohuaDialog weiyuPresetTime = new ZhiNengRoomDeviceAutoJiaohuaDialog(context,                        "设置自动浇花开始时间");                weiyuPresetTime.setPicker(weiyuPresetTimeList);                weiyuPresetTime.show();                weiyuPresetTime.setOnItemDialogClickListener(new OnItemDialogClickListener() {                    @Override                    public void onClick(View view, int first, int second, int third) {                        tvJiaohuaKaishishijian.setText(weiyuPresetTimeList.get(first));                    }                });                break;            case R.id.rl_jiaohua_preset_time:                //设置开始浇花时长                List<String> jiaohuaPresetTimeList = new ArrayList<>();                for (int i = 1; i < 1000; i++) {                    jiaohuaPresetTimeList.add(String.format("%03d", i));                }                ZhiNengRoomDeviceAutoJiaohuaDialog jiaohuaPresetTime = new ZhiNengRoomDeviceAutoJiaohuaDialog(context,                        "设置自动浇花时长");                jiaohuaPresetTime.setPicker(jiaohuaPresetTimeList);                jiaohuaPresetTime.show();                jiaohuaPresetTime.setOnItemDialogClickListener(new OnItemDialogClickListener() {                    @Override                    public void onClick(View view, int first, int second, int third) {                        tvJiaohuaPresetTime.setText(jiaohuaPresetTimeList.get(first));                    }                });                break;            case R.id.rl_jiaohua_interval_time:                //设置浇花时间间隔                List<String> timeList = new ArrayList<>();                for (int i = 1; i < 100; i++) {                    timeList.add(String.format("%02d", i));                }                List<String> twoList = new ArrayList<>();                twoList.add("小时");                twoList.add("天");                ZhiNengRoomDeviceAutoJiaohuaDialog jiaohuaIntervalTime = new ZhiNengRoomDeviceAutoJiaohuaDialog(context,                        "设置自动浇花时间间隔");                jiaohuaIntervalTime.setPicker(timeList, twoList);                jiaohuaIntervalTime.show();                jiaohuaIntervalTime.setOnItemDialogClickListener(new OnItemDialogClickListener() {                    @Override                    public void onClick(View view, int first, int second, int third) {                        tvJiaohuaIntervalTime.setText(timeList.get(first));                        tvJiaohuaIntervalTimeType.setText(twoList.get(second));                    }                });                break;        }    }    String nowData;    private void getnet() {        //访问网络获取数据 下面的列表数据        Map<String, String> map = new HashMap<>();        map.put("code", "16035");        map.put("key", Urls.key);        map.put("token", UserManager.getManager(context).getAppToken());        map.put("device_id", device_id);        Gson gson = new Gson();        String a = gson.toJson(map);        Log.e("map_data", gson.toJson(map));        OkGo.<AppResponse<ZhiNengWeiYuBean.DataBean>>post(ZHINENGJIAJU)                .tag(this)//                .upJson(gson.toJson(map))                .execute(new JsonCallback<AppResponse<ZhiNengWeiYuBean.DataBean>>() {                    @Override                    public void onSuccess(Response<AppResponse<ZhiNengWeiYuBean.DataBean>> response) {                        if (srLSmart != null) {                            srLSmart.setEnableRefresh(true);                            srLSmart.finishRefresh();                            srLSmart.setEnableLoadMore(false);                        }                        if (response.body().msg.equals("ok")) {                            dataBean = response.body().data.get(0);                            tvFamily.setText(dataBean.getFamily_name());                            tvRoom.setText(dataBean.getRoom_name());                            tvDeviceName.setText(dataBean.getDevice_name());                            tvDeviceType.setText(dataBean.getDevice_name());                            device_ccid = dataBean.getDevice_ccid();                            mqttMingLing = new ZnjjMqttMingLing(mContext, dataBean.getDevice_ccid_up(), dataBean.getServer_id());                            nowData = "zn/app/" + dataBean.getServer_id() + dataBean.getDevice_ccid_up();                            AndMqtt.getInstance().subscribe(new MqttSubscribe()                                    .setTopic(nowData)                                    .setQos(2), new IMqttActionListener() {                                @Override                                public void onSuccess(IMqttToken asyncActionToken) {                                    Log.i("Rair", "订阅的地址:  " + nowData);                                }                                @Override                                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {                                    Log.i("CAR_NOTIFY", "(MainActivity.java:68)-onFailure:-&gt;订阅失败");                                }                            });                            mqttMingLing.subscribeShiShiXinXi(new IMqttActionListener() {                                @Override                                public void onSuccess(IMqttToken asyncActionToken) {                                    Log.i("Rair", "请求实时数据");                                }                                @Override                                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {                                }                            });                            if (!StringUtils.isEmpty(response.body().data.get(0).getFj_time())){                                tvJiaohuaPresetTime.setText(response.body().data.get(0).getFj_times());                            }                            if (!StringUtils.isEmpty(response.body().data.get(0).getFj_interval())){                                tvJiaohuaIntervalTime.setText(response.body().data.get(0).getFj_interval());                            }                            if (!StringUtils.isEmpty(response.body().data.get(0).getFj_times())){                                tvJiaohuaKaishishijian.setText(response.body().data.get(0).getFj_time());                            }                        }                        showLoadSuccess();                    }                    @Override                    public void onError(Response<AppResponse<ZhiNengWeiYuBean.DataBean>> response) {                        String str = response.getException().getMessage();                        UIHelper.ToastMessage(mContext, response.getException().getMessage());                    }                    @Override                    public void onStart(Request<AppResponse<ZhiNengWeiYuBean.DataBean>, ? extends Request> request) {                        super.onStart(request);                        showLoading();                    }                    @Override                    public void onFinish() {                        super.onFinish();                    }                });    }    /**     * 修改设备名字     */    private void changeDevice(String deviceName) {        Map<String, String> map = new HashMap<>();        map.put("code", "16033");        map.put("key", Urls.key);        map.put("token", UserManager.getManager(context).getAppToken());        map.put("family_id", dataBean.getFamily_id());        map.put("device_id", dataBean.getDevice_id());        map.put("old_name", dataBean.getDevice_name());        map.put("device_name", deviceName);        Gson gson = new Gson();        Log.e("map_data", gson.toJson(map));        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)                .tag(this)//                .upJson(gson.toJson(map))                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {                    @Override                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {                        if (response.body().msg_code.equals("0000")) {                            tvDeviceName.setText(deviceName);                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(ZhiNengJiaoHuaAutoActivity.this,                                    "成功", "名字修改成功咯", "好的", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {                                @Override                                public void clickLeft() {                                }                                @Override                                public void clickRight() {                                }                            });                            myCarCaoZuoDialog_success.show();                        }                    }                    @Override                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {                        String str = response.getException().getMessage();                        Log.i("cuifahuo", str);                        String[] str1 = str.split("：");                        if (str1.length == 3) {                            MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,                                    "提示", str1[2], "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {                                @Override                                public void clickLeft() {                                }                                @Override                                public void clickRight() {                                }                            });                            myCarCaoZuoDialog_caoZuoTIshi.show();                        }                    }                });    }    /**     * 删除主控设备     */    private void deleteMainDevice() {        Map<String, String> map = new HashMap<>();        map.put("code", "16037");        map.put("key", Urls.key);        map.put("token", UserManager.getManager(context).getAppToken());        map.put("device_ccid", dataBean.getDevice_ccid());        Gson gson = new Gson();        Log.e("map_data", gson.toJson(map));        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)                .tag(this)//                .upJson(gson.toJson(map))                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {                    @Override                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {                        if (response.body().msg_code.equals("0000")) {                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(ZhiNengJiaoHuaAutoActivity.this,                                    "成功", "设备删除成功", "好的", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {                                @Override                                public void clickLeft() {                                }                                @Override                                public void clickRight() {                                    finish();                                }                            });                            myCarCaoZuoDialog_success.show();                        }                    }                    @Override                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {                        String str = response.getException().getMessage();                        Log.i("cuifahuo", str);                        String[] str1 = str.split("：");                        if (str1.length == 3) {                            MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,                                    "提示", str1[2], "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {                                @Override                                public void clickLeft() {                                }                                @Override                                public void clickRight() {                                }                            });                            myCarCaoZuoDialog_caoZuoTIshi.show();                        }                    }                });    }    /**     * 删除设备     */    private void deleteDevice() {        Map<String, String> map = new HashMap<>();        map.put("code", "16034");        map.put("key", Urls.key);        map.put("token", UserManager.getManager(context).getAppToken());        map.put("family_id", dataBean.getFamily_id());        map.put("device_id", dataBean.getDevice_id());        Gson gson = new Gson();        Log.e("map_data", gson.toJson(map));        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)                .tag(this)//                .upJson(gson.toJson(map))                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {                    @Override                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {                        if (response.body().msg_code.equals("0000")) {                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(ZhiNengJiaoHuaAutoActivity.this,                                    "成功", "设备删除成功", "好的", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {                                @Override                                public void clickLeft() {                                }                                @Override                                public void clickRight() {                                    finish();                                }                            });                            myCarCaoZuoDialog_success.show();                        }                    }                    @Override                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {                        String str = response.getException().getMessage();                        Log.i("cuifahuo", str);                        String[] str1 = str.split("：");                        if (str1.length == 3) {                            MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,                                    "提示", str1[2], "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {                                @Override                                public void clickLeft() {                                }                                @Override                                public void clickRight() {                                }                            });                            myCarCaoZuoDialog_caoZuoTIshi.show();                        }                    }                });    }    /**     * 用于其他Activty跳转到该Activity     *     * @param context     */    public static void actionStart(Context context, String device_id, String device_type) {        Intent intent = new Intent(context, ZhiNengJiaoHuaAutoActivity.class);        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);        intent.putExtra("device_id", device_id);        intent.putExtra("device_type", device_type);        context.startActivity(intent);    }    private void setJiaohuaTiJiao() {        //访问网络获取数据 下面的列表数据        Map<String, String> map = new HashMap<>();        map.put("code", "16039");        map.put("key", Urls.key);        map.put("token", UserManager.getManager(context).getAppToken());        map.put("device_id", device_id);        map.put("fj_times", tvJiaohuaPresetTime.getText().toString().trim());        String leixing = null;        if (tvJiaohuaIntervalTimeType.getText().toString().trim().equals("天")) {            leixing = "d";        } else if (tvJiaohuaIntervalTimeType.getText().toString().trim().equals("小时")) {            leixing = "h";        }        //        map.put("fj_interval", tvJiaohuaIntervalTime.getText().toString().trim() + leixing);        map.put("fj_time", tvJiaohuaKaishishijian.getText().toString().trim());        Gson gson = new Gson();        String a = gson.toJson(map);        Log.e("map_data", gson.toJson(map));        OkGo.<AppResponse<ZhiNengWeiYuBean.DataBean>>post(ZHINENGJIAJU)                .tag(this)//                .upJson(gson.toJson(map))                .execute(new JsonCallback<AppResponse<ZhiNengWeiYuBean.DataBean>>() {                    @Override                    public void onSuccess(Response<AppResponse<ZhiNengWeiYuBean.DataBean>> response) {                        UIHelper.ToastMessage(mContext, "提交设置成功");                        showLoadSuccess();                    }                    @Override                    public void onError(Response<AppResponse<ZhiNengWeiYuBean.DataBean>> response) {                        UIHelper.ToastMessage(mContext, response.getException().getMessage());                    }                    @Override                    public void onStart(Request<AppResponse<ZhiNengWeiYuBean.DataBean>, ? extends Request> request) {                        super.onStart(request);                        showLoading();                    }                    @Override                    public void onFinish() {                        super.onFinish();                    }                });    }}