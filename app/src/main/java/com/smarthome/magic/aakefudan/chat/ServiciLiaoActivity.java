package com.smarthome.magic.aakefudan.chat;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.interfaces.MapCameraMessage;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.smarthome.magic.R;
import com.smarthome.magic.aakefudan.adapter.XiuliAdapter;
import com.smarthome.magic.aakefudan.model.ZixunModel;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.MyApplication;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.DaohangDialog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.NavigationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ServiciLiaoActivity extends BaseActivity {

    @BindView(R.id.rl_main_title)
    RelativeLayout rl_main_title;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.rl_menu)
    RelativeLayout rl_menu;
    @BindView(R.id.tv_title_name)
    TextView tv_title_name;

    private ZixunModel.DataBean zixunModel;
    private List<ZixunModel.DataBean.ListBean> weixiuList = new ArrayList<>();
    private XiuliAdapter xiuliAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_kefu_conversation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        // 添加会话界面
        initHuihua();
        initHuidiao();
        initView();

//        mMapView = view.findViewById(R.id.map);
    }

    private void initHuihua() {
        ConversationFragment conversationFragment = new ConversationFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, conversationFragment);
        transaction.commit();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_RONGYUN_STATE) {
                    RongIMClient.ConnectionStatusListener.ConnectionStatus status = (RongIMClient.ConnectionStatusListener.ConnectionStatus) message.content;
                    if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONN_USER_BLOCKED)) {
                        //* 用户被开发者后台封禁
                        // notice.content = status.CONN_USER_BLOCKED;
                        UIHelper.ToastMessage(mContext, "该商户已被封禁");
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED)) {
                        //连接成功
                        showLoadSuccess();
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTING)) {
                        //连接中
                        // notice.content = status.CONNECTING;
                        showLoading();
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                        //断开连接
                        // notice.content = status.DISCONNECTED;
                        showLoadFailed();
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT)) {
                        //用户账户在其他设备登录，本机会被踢掉线。
                        UIHelper.ToastMessage(mContext, "用户账户在其他设备登录，本机会被踢掉线。");
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.NETWORK_UNAVAILABLE)) {
                        //网络不可用
                        showLoadFailed();
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.SERVER_INVALID)) {
                        //TOKEN_INCORRECT
                        //连接失败
                        UIHelper.ToastMessage(mContext, "连接失败");
                    } else if (status.getMessage().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.TOKEN_INCORRECT)) {
                        //Token 不正确
                        //notice.content = status.TOKEN_INCORRECT;
                    }
                } else if (message.type == ConstanceValue.MSG_SERVICE_CHAT) {
                    MyMessage model = (MyMessage) message.content;
                    DaohangDialog dialog = new DaohangDialog(mContext, model);
                    dialog.setCilck(new DaohangDialog.OnDaohangCilck() {
                        @Override
                        public void click(MyMessage message) {
                            try {
                                String lat_x = message.getLat_x();
                                String lon_y = message.getLon_y();
                                Double x = Double.valueOf(lat_x);
                                Double y = Double.valueOf(lon_y);
                                LatLng latLng = new LatLng(x, y);
                                NavigationUtils.Navigation(latLng);
                            } catch (Exception e) {
                                com.smarthome.magic.app.UIHelper.ToastMessage(MyApplication.getApp().getApplicationContext(), "请下载高德后重新尝试", Toast.LENGTH_SHORT);
                            }

                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }
            }
        }));
    }

    private void initView() {
        String service_form_id = getIntent().getStringExtra("service_form_id");
        String str = getIntent().getStringExtra("dianpuming");
        String inst_accid = getIntent().getStringExtra("inst_accid");
        tv_title_name.setText(str);
        getLiaoTian(mContext, service_form_id);

        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showZixun();
            }
        });
    }

    public void getLiaoTian(Context context, String inst_accid) {
        Map<String, String> map = new HashMap<>();
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("code", "03318");
        map.put("key", Urls.key);
        map.put("service_form_id", inst_accid);

        Gson gson = new Gson();
        gson.toJson(map);
        OkGo.<AppResponse<ZixunModel.DataBean>>
                post(Urls.SERVER_URL + "wit/app/car/witAgent").
                tag(context).
                upJson(gson.toJson(map)).
                execute(new JsonCallback<AppResponse<ZixunModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZixunModel.DataBean>> response) {
                        zixunModel = response.body().data.get(0);
                        weixiuList = zixunModel.getList();
                    }

                    @Override
                    public void onError(Response<AppResponse<ZixunModel.DataBean>> response) {
                        AlertUtil.t(context, response.getException().getMessage());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        showLoadSuccess();
                    }

                    @Override
                    public void onStart(Request<AppResponse<ZixunModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                    }
                });
    }

    private PopupWindow popKeshi;
    private ZixunModel.DataBean.ListBean model;

    private void showZixun() {
        if (zixunModel == null && weixiuList.size() > 0) {
            return;
        }

        if (popKeshi == null) {
            View view = getLayoutInflater().inflate(R.layout.pop_kefu_zixun, null);
            popKeshi = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
            popKeshi.setFocusable(true);
            popKeshi.setOutsideTouchable(true);
            popKeshi.setAnimationStyle(android.R.style.Animation_Dialog); //使用系统的
            popKeshi.showAsDropDown(rl_main_title);


            mMapView = view.findViewById(R.id.map);

            RecyclerView rv_mendian = view.findViewById(R.id.rv_mendian);
            model = weixiuList.get(0);
            model.setSelect(true);
            weixiuList.set(0, model);

            xiuliAdapter = new XiuliAdapter(R.layout.a_item_service_weixiu, weixiuList);
            rv_mendian.setLayoutManager(new LinearLayoutManager(mContext));
            rv_mendian.setAdapter(xiuliAdapter);
            xiuliAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    for (int i = 0; i < weixiuList.size(); i++) {
                        ZixunModel.DataBean.ListBean listBean = weixiuList.get(i);
                        if (i == position) {
                            listBean.setSelect(true);
                            model = listBean;
                        } else {
                            listBean.setSelect(false);
                        }
                        weixiuList.set(i, listBean);
                        xiuliAdapter.setNewData(weixiuList);
                        xiuliAdapter.notifyDataSetChanged();
                    }
                }
            });

            TextView bt_ok = view.findViewById(R.id.bt_ok);
            TextView tv_master_name = view.findViewById(R.id.tv_master_name);
            TextView tv_master_num = view.findViewById(R.id.tv_master_num);
            TextView tv_master_guzhang = view.findViewById(R.id.tv_master_guzhang);

            tv_master_name.setText("车主姓名：" + zixunModel.getCar_user_name());
            tv_master_num.setText("车牌号码：" + zixunModel.getPlate_number());
            tv_master_guzhang.setText(zixunModel.getAction_name());

            initMap();

            bt_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendXiaoxi(model);

//                    if (zixunModel.getAction().equals("2")) {
//                        AlertUtil.t(mContext, "咨询已完成");
//                    } else {
//                        sendXiaoxi(model);
//                    }
                }
            });

            ImageView iv_close = view.findViewById(R.id.iv_close);
            iv_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popKeshi.dismiss();
                }
            });

            WindowManager.LayoutParams attributes = getWindow().getAttributes(); //获取程序的WINDOW属性
            attributes.alpha = 0.5f;  //0.1-1.0
            getWindow().setAttributes(attributes);// 在把所有属性重新设置回去

            popKeshi.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams attributes = getWindow().getAttributes(); //获取程序的WINDOW属性
                    attributes.alpha = 1.0F;  //0.1-1.0
                    getWindow().setAttributes(attributes);// 在把所有属性重新设置回去

//                if (mMapView!=null){
//                    mMapView.onDestroy();
//                }
                }
            });
        } else {
            popKeshi.showAsDropDown(rl_main_title);
        }
    }

    private void sendXiaoxi(ZixunModel.DataBean.ListBean listBean) {
        showLoadSuccess();
        String customRepairDis = listBean.getMeter();
        String customRepairName = listBean.getInst_name();
        String customRepairUrl = listBean.getUrl();
        String addr = listBean.getAddr();
        String lat_x = listBean.getX();
        String lon_y = listBean.getY();
        String targetId = zixunModel.getOf_user_accid();

        Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
        String pushContent = "修配厂推荐";
        String pushData = "修配厂推荐";
        Map<String, String> map = new HashMap<>();
        map.put("customTitle", "修配厂推荐");
        map.put("customRepairUrl", customRepairUrl);
        map.put("customRepairDis", customRepairDis);
        map.put("customRepairName", customRepairName);
        map.put("addr", addr);
        map.put("lat_x", lat_x);
        map.put("lon_y", lon_y);

        Gson gson = new Gson();
        String s = gson.toJson(map);
        MyMessage messageContent = new MyMessage(s.getBytes());
        Message message = Message.obtain(targetId, conversationType, messageContent);
        RongIM.getInstance().sendMessage(message, pushContent, pushData, new IRongCallback.ISendMessageCallback() {
            /**
             * 消息发送前回调, 回调时消息已存储数据库
             * @param message 已存库的消息体
             */
            @Override
            public void onAttached(Message message) {

            }

            /**
             * 消息发送成功。
             * @param message 发送成功后的消息体
             */
            @Override
            public void onSuccess(Message message) {
                showLoadSuccess();
                AlertUtil.t(mContext, "发送成功");
                if (popKeshi != null) {
                    popKeshi.dismiss();
                }
            }

            /**
             * 消息发送失败
             * @param message   发送失败的消息体
             * @param errorCode 具体的错误
             */
            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                showLoadSuccess();
                AlertUtil.t(mContext, message.toString());
                if (popKeshi != null) {
                    popKeshi.dismiss();
                }
            }
        });
    }

    private Bundle savedInstanceState;
    private AMap aMap;
    MapView mMapView;

    private void initMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            //UiSettings 主要是对地图上的控件的管理，比如指南针、logo位置（不能隐藏）.....
            UiSettings settings = aMap.getUiSettings();

            //设置了定位的监听,这里要实现LocationSource接口
            // aMap.setLocationSource(this);

            // 是否显示定位按钮（据我所知不能更改，知道的麻烦告我一声）
            settings.setMyLocationButtonEnabled(false);

            //添加指南针
            //settings.setCompassEnabled(true);

            //aMap.getCameraPosition(); 方法可以获取地图的旋转角度


            //管理缩放控件
            settings.setZoomControlsEnabled(true);
            //设置logo位置，左下，底部居中，右下（隐藏logo：settings.setLogoLeftMargin(9000)）
            settings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
            //设置显示地图的默认比例尺
            settings.setScaleControlsEnabled(true);
            //每像素代表几米
            //float scale = aMap.getScalePerPixel();

//            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase

            for (int i = 0; i < weixiuList.size(); i++) {
                ZixunModel.DataBean.ListBean listBean = weixiuList.get(i);
                String inst_name = listBean.getInst_name();
                String x_begin = listBean.getX();
                String y_begin = listBean.getY();
                Double x = Double.valueOf(x_begin);
                Double y = Double.valueOf(y_begin);
                LatLng marker1 = new LatLng(x, y);
                if (listBean.getType().equals("1")) {
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.cai_xiu_s));
                    com.amap.api.maps2d.model.Marker marker = aMap.addMarker(new MarkerOptions().position(marker1).icon(bitmapDescriptor).title(inst_name));
                } else {
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.cai_xiu_n));
                    com.amap.api.maps2d.model.Marker marker = aMap.addMarker(new MarkerOptions().position(marker1).icon(bitmapDescriptor).title(inst_name));
                }
            }

            String x_begin = zixunModel.getX_begin();
            String y_begin = zixunModel.getY_begin();
            Double x = Double.valueOf(x_begin);
            Double y = Double.valueOf(y_begin);
            LatLng carMarker = new LatLng(x, y);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(carMarker, 15, 0, 30));
            aMap.moveCamera(cameraUpdate);

            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.car_master_weizhi));
            com.amap.api.maps2d.model.Marker marker = aMap.addMarker(new MarkerOptions().position(carMarker).icon(bitmapDescriptor).title(zixunModel.getCar_user_name()));
        }
        mMapView.onCreate(savedInstanceState);
        aMap.setOnMarkerClickListener(mMarkerListener);
    }


    /**
     * 设置marker的点击事件
     */
    AMap.OnMarkerClickListener mMarkerListener = new AMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            if (marker.isInfoWindowShown()) {
                marker.hideInfoWindow();
            } else {
                marker.showInfoWindow();
            }

//            //UIHelper.ToastMessage(mContext, "点击了这个");
            return true; // 返回:true 表示点击marker 后marker 不会移动到地图中心；返回false 表示点击marker 后marker 会自动移动到地图中心
        }
    };
}