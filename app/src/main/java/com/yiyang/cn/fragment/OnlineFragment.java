package com.yiyang.cn.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.BindBoxActivity;
import com.yiyang.cn.adapter.CarList1Adapter;
import com.yiyang.cn.adapter.SheBeiListAdapter;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;

import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.SheBeiLieBieListModel;
import com.yiyang.cn.model.SheBeiModel;
import com.yiyang.cn.model.SmartDevice_car_0364;
import com.yiyang.cn.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class OnlineFragment extends BaseFragment implements Observer {
    private static final String TAG = "OnlineFragment";

    Unbinder unbinder;
    @BindView(R.id.list)
    RecyclerView mList;
    CarList1Adapter carListAdapter;//车联网列表适配器
    List<SmartDevice_car_0364.DataBean> carList = new ArrayList<>();//车联网列表数据源
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.rl_main)
    LinearLayout rlMain;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    private SheBeiListAdapter sheBeiListAdapter;
    private List<SheBeiModel> mDatas = new ArrayList<>();
    private String mqtt_connect_state;
    private String mqtt_connect_prompt;

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_online_list;
    }

    @Override
    protected void initView(View view) {
        view.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件
        unbinder = ButterKnife.bind(this, view);
        sheBeiListAdapter = new SheBeiListAdapter(R.layout.item_shebei, R.layout.item_shebei_header, mDatas);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList.setAdapter(sheBeiListAdapter);
        sheBeiListAdapter.notifyDataSetChanged();
        sheBeiListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        getSheBeiData();

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BindBoxActivity.actionStart(getActivity());
            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ADD_CHELIANG_SUCCESS) {
                    //车辆添加成功
                    srLSmart.autoRefresh();

                }
            }
        }));

        srLSmart.setEnableRefresh(true);
        srLSmart.setEnableLoadMore(false);
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getSheBeiData();
            }
        });


    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).statusBarColor(R.color.white).init();
    }


    @Override
    protected boolean immersionEnabled() {
        return true;
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        getSheBeiData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("update")) {

        }
    }

    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03064");
        map.put("key", Urls.key);
        map.put("user_car_type", "1");
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<SmartDevice_car_0364.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SmartDevice_car_0364.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SmartDevice_car_0364.DataBean>> response) {
                        srLSmart.finishRefresh();
                        carList.clear();
                        carList.addAll(response.body().data);
                        if (carListAdapter != null) {
                            return;
                        }

                        if (carList.size() == 0) {
                            View view = View.inflate(getActivity(), R.layout.online_empty_view, null);
                            carListAdapter.setHeaderView(view);
                        } else {
                            carListAdapter.removeAllHeaderView();
                        }
                        carListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<SmartDevice_car_0364.DataBean>> response) {
                        AlertUtil.t(getActivity(), response.getException().getMessage());
                    }
                });
    }

    public void getSheBeiData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03510");
        map.put("key", Urls.key);
        map.put("user_car_type", "1");
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<SheBeiLieBieListModel.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SheBeiLieBieListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SheBeiLieBieListModel.DataBean>> response) {
                        mqtt_connect_state = response.body().mqtt_connect_state;
                        mqtt_connect_prompt = response.body().mqtt_connect_prompt;
                        mDatas.clear();
                        for (int i = 0; i < response.body().data.size(); i++) {
                            SheBeiModel sheBeiModel = new SheBeiModel(true, response.body().data.get(i).getControl_device_name());
                            mDatas.add(sheBeiModel);
                            for (int j = 0; j < response.body().data.get(i).getControl_device_list().size(); j++) {
                                SheBeiModel sheBeiModel1 = new SheBeiModel(false, response.body().data.get(i).getControl_device_name());
                                SheBeiLieBieListModel.DataBean.ControlDeviceListBean bean = response.body().data.get(i).getControl_device_list().get(j);
                                sheBeiModel1.ccid = bean.getCcid();
                                sheBeiModel1.device_img_url = bean.getDevice_img_url();
                                sheBeiModel1.device_name = bean.getDevice_name();
                                sheBeiModel1.validity_state = bean.getValidity_state();
                                sheBeiModel1.validity_term = bean.getValidity_term();
                                sheBeiModel1.validity_time = bean.getValidity_time();
                                sheBeiModel1.device_type = response.body().data.get(i).getControl_type_id();
                                sheBeiModel1.sim_ccid_save_type = bean.sim_ccid_save_type;
                                sheBeiModel1.share_type = bean.getShare_type();
                                mDatas.add(sheBeiModel1);
                            }
                        }

                        if (mDatas.size() == 0) {
                            View view = View.inflate(getActivity(), R.layout.online_empty_view, null);
                            sheBeiListAdapter.setHeaderView(view);
                        } else {
                            sheBeiListAdapter.removeAllHeaderView();
                        }
                        sheBeiListAdapter.setNewData(mDatas);
                        sheBeiListAdapter.notifyDataSetChanged();
                        srLSmart.finishRefresh();
                    }

                    @Override
                    public void onError(Response<AppResponse<SheBeiLieBieListModel.DataBean>> response) {
                        AlertUtil.t(getActivity(), response.getException().getMessage());
                    }
                });
    }
}
