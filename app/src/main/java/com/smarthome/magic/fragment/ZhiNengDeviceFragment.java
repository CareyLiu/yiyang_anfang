package com.smarthome.magic.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.ZhiNengChuangLianActivity;
import com.smarthome.magic.activity.ZhiNengDianDengActivity;
import com.smarthome.magic.activity.ZhiNengJiajuWeiYuAutoActivity;
import com.smarthome.magic.activity.ZhiNengJiaoHuaAutoActivity;
import com.smarthome.magic.activity.ZhiNengRoomDeviceDetailAutoActivity;
import com.smarthome.magic.activity.zckt.AirConditionerActivity;
import com.smarthome.magic.activity.zhinengjiaju.peinet.PeiWangYinDaoPageActivity;
import com.smarthome.magic.activity.zhinengjiaju.peinet.v1.EspTouchActivity;
import com.smarthome.magic.adapter.ZhiNengDeviceListAdapter;
import com.smarthome.magic.app.AppManager;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.mqtt_zhiling.ZnjjMqttMingLing;
import com.smarthome.magic.tools.NetworkUtils;
import com.smarthome.magic.util.GridAverageUIDecoration;
import com.smarthome.magic.util.GridSectionAverageGapItemDecoration;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.smarthome.magic.view.RecycleItemSpance;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.jaaksi.pickerview.util.Util;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class ZhiNengDeviceFragment extends BaseFragment {

    private View viewLayout;
    private LinearLayout ll_content_bg;
    private RecyclerView recyclerView;
    private ZhiNengDeviceListAdapter zhiNengDeviceListAdapter;
    private List<ZhiNengHomeBean.DataBean.DeviceBean> dataBean = new ArrayList<>();
    private String member_type = "";
    public ZnjjMqttMingLing mqttMingLing = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewLayout = inflater.inflate(R.layout.fragment_zhineng_device, container, false);
        initView(viewLayout);
        return viewLayout;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_zhineng_device;
    }

    public static ZhiNengDeviceFragment newInstance(Bundle bundle) {
        ZhiNengDeviceFragment fragment = new ZhiNengDeviceFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    public void initView(View view) {
        ll_content_bg = view.findViewById(R.id.ll_content_bg);
        recyclerView = view.findViewById(R.id.recyclerView);
//        recyclerView.addItemDecoration(new RecycleItemSpance(20, 2));
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.addItemDecoration(new GridAverageUIDecoration(14, 10));

        recyclerView.setLayoutManager(layoutManager);
        zhiNengDeviceListAdapter = new ZhiNengDeviceListAdapter(R.layout.item_zhineng_device, dataBean);

        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.activity_zhineng_device_none, null);
        TextView tvBangDingZhuJi = view1.findViewById(R.id.tv_bangdingzhuji);
        tvBangDingZhuJi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeiWangYinDaoPageActivity.actionStart(getActivity());
            }
        });
        zhiNengDeviceListAdapter.setEmptyView(view1);

        zhiNengDeviceListAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(zhiNengDeviceListAdapter);

        zhiNengDeviceListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_switch:

                        ZhiNengHomeBean.DataBean.DeviceBean bean = (ZhiNengHomeBean.DataBean.DeviceBean) adapter.getData().get(position);
                        mqttMingLing = new ZnjjMqttMingLing(getActivity(), bean.getDevice_ccid_up(), bean.getServer_id());
                        if (bean.getDevice_type().equals("01")) {

                            if (bean.getWork_state().equals("1")) {

                                mqttMingLing.setAction(bean.getDevice_ccid(), "02", new IMqttActionListener() {
                                    @Override
                                    public void onSuccess(IMqttToken asyncActionToken) {
                                        //UIHelper.ToastMessage(mContext, "当前装置开启");

                                        List<String> stringList = new ArrayList<>();
                                        stringList.add(bean.getDevice_ccid());
                                        stringList.add("2");

                                        Notice notice = new Notice();
                                        notice.type = ConstanceValue.MSG_ZHINENGJIAJUKAIDENG;
                                        notice.content = stringList;
                                        Log.i("Rair", notice.content.toString());
                                        RxBus.getDefault().sendRx(notice);
                                    }

                                    @Override
                                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                        UIHelper.ToastMessage(getActivity(), "未发送指令");
                                    }
                                });

                            }

                            if (bean.getWork_state().equals("2")) {

                                mqttMingLing.setAction(bean.getDevice_ccid(), "01", new IMqttActionListener() {
                                    @Override
                                    public void onSuccess(IMqttToken asyncActionToken) {
                                        //UIHelper.ToastMessage(mContext, "当前装置开启");

                                        List<String> stringList = new ArrayList<>();
                                        stringList.add(bean.getDevice_ccid());
                                        stringList.add("1");

                                        Notice notice = new Notice();
                                        notice.type = ConstanceValue.MSG_ZHINENGJIAJUKAIDENG;
                                        notice.content = stringList;
                                        Log.i("Rair", notice.content.toString());
                                        RxBus.getDefault().sendRx(notice);
                                    }

                                    @Override
                                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                                        UIHelper.ToastMessage(getActivity(), "未发送指令");
                                    }
                                });

                            }
                        } else if (bean.getDevice_type().equals("16")) {
//                            if (bean.getWork_state().equals("1")) {//开
//                                mqttMingLing.setAction(bean.getDevice_ccid(), "02", new IMqttActionListener() {
//                                    @Override
//                                    public void onSuccess(IMqttToken asyncActionToken) {
//                                        // UIHelper.ToastMessage(mContext, "执行成功");
//
//                                    }
//
//                                    @Override
//                                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//
//                                    }
//                                });
//                            }
//
//                            if (bean.getWork_state().equals("2")) {//关
//                                mqttMingLing.setAction(bean.getDevice_ccid(), "01", new IMqttActionListener() {
//                                    @Override
//                                    public void onSuccess(IMqttToken asyncActionToken) {
//                                        // UIHelper.ToastMessage(mContext, "执行成功");
//
//                                    }
//
//                                    @Override
//                                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//
//                                    }
//                                });
//                            }


                        }
                        break;
                    case R.id.ll_content:
                        ZhiNengHomeBean.DataBean.DeviceBean deviceBean = (ZhiNengHomeBean.DataBean.DeviceBean) adapter.getItem(position);
                        if (deviceBean.getDevice_type().equals("20")) {
                            String ccid = deviceBean.getDevice_ccid();
                            PreferenceHelper.getInstance(getContext()).putString("ccid", ccid);
                            PreferenceHelper.getInstance(getContext()).putString("share_type", "1");
                            PreferenceHelper.getInstance(getContext()).putString("sim_ccid_save_type", "1");
                            if (NetworkUtils.isConnected(getActivity())) {
                                Activity currentActivity = AppManager.getAppManager().currentActivity();
                                if (currentActivity != null) {
                                    AirConditionerActivity.actionStart(getActivity(), ccid, "智能空调");
                                }
                            } else {
                                UIHelper.ToastMessage(getActivity(), "请连接网络后重新尝试");
                            }
                        } else if (deviceBean.getDevice_type().equals("16")) {
                            ZhiNengChuangLianActivity.actionStart(getActivity(), deviceBean.getDevice_id());
                        } else if (deviceBean.getDevice_type().equals("01")) {
                            ZhiNengDianDengActivity.actionStart(getActivity(), deviceBean.getDevice_id(), deviceBean.getDevice_type());
                        } else if (deviceBean.getDevice_type().equals("03")) {
                            ZhiNengJiajuWeiYuAutoActivity.actionStart(getActivity(), deviceBean.getDevice_id(), deviceBean.getDevice_type());
                        } else if (deviceBean.getDevice_type().equals("04")) {
                            ZhiNengJiaoHuaAutoActivity.actionStart(getActivity(), deviceBean.getDevice_id(), deviceBean.getDevice_type());
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString("device_id", deviceBean.getDevice_id());
                            bundle.putString("device_type", deviceBean.getDevice_type());
                            bundle.putString("member_type", member_type);
                            bundle.putString("work_state", deviceBean.getWork_state());
                            startActivity(new Intent(getActivity(), ZhiNengRoomDeviceDetailAutoActivity.class).putExtras(bundle));
                        }
                        break;
                }

            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ZHINENGJIAJUKAIDENG) {
                    List<String> messageList = (List<String>) message.content;
                    String zhuangZhiId = messageList.get(0);
                    String kaiGuanDengZhuangTai = messageList.get(1);
                    for (int i = 0; i < dataBean.size(); i++) {
                        if (dataBean.get(i).getDevice_ccid().equals(zhuangZhiId)) {
                            dataBean.get(i).setWork_state(kaiGuanDengZhuangTai);
                        }
                    }
                    zhiNengDeviceListAdapter.notifyDataSetChanged();
                }
            }
        }));


    }

    public void onRefresh() {
        if (getArguments() != null) {
            List<ZhiNengHomeBean.DataBean.DeviceBean> device = getArguments().getParcelableArrayList("device");
            String strPhone = PreferenceHelper.getInstance(getActivity()).getString("user_phone", "");
            if (strPhone.equals("15114684672") || strPhone.equals("17645185187")) {
                ZhiNengHomeBean.DataBean.DeviceBean kongtiaoBean = new ZhiNengHomeBean.DataBean.DeviceBean();
                kongtiaoBean.setDevice_ccid("kkkkkkkkkkkkkkkk90120018");
                kongtiaoBean.setDevice_name("智能空調");
                kongtiaoBean.setDevice_type("20");
                kongtiaoBean.setDevice_type_pic("https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11711");
                kongtiaoBean.setOnline_state("1");
                kongtiaoBean.setServer_id("8/");
                kongtiaoBean.setRoom_name("默认房间");
                kongtiaoBean.setWork_state("3");
                device.add(kongtiaoBean);
            }
            member_type = getArguments().getString("member_type");
            dataBean.clear();
            dataBean.addAll(device);
            if (zhiNengDeviceListAdapter != null) {
                zhiNengDeviceListAdapter.notifyDataSetChanged();
            }

        }

        if (recyclerView != null) {
            for (int i = 0; i < recyclerView.getItemDecorationCount(); i++) {
                recyclerView.removeItemDecorationAt(i);
            }
            if (dataBean.size() == 0) {
                recyclerView.addItemDecoration(new GridAverageUIDecoration(0, 10));
            } else {
                recyclerView.addItemDecoration(new GridAverageUIDecoration(14, 10));
            }
        }

    }
}
