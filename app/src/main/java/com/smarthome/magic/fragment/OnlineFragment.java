package com.smarthome.magic.fragment;

import android.content.Intent;
import android.util.Log;
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
import com.smarthome.magic.R;
import com.smarthome.magic.activity.CarBrandActivity;
import com.smarthome.magic.activity.WindHeaterActivity;
import com.smarthome.magic.adapter.CarList1Adapter;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.SmartDevice_car_0364;
import com.smarthome.magic.util.AlertUtil;

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

import static com.smarthome.magic.config.MyApplication.CARBOX_GETNOW;
import static com.smarthome.magic.config.MyApplication.CAR_CTROL;
import static com.smarthome.magic.config.MyApplication.CAR_NOTIFY;
import static com.smarthome.magic.config.MyApplication.getCcid;
import static com.smarthome.magic.config.MyApplication.getServer_id;


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
        getData();
        carListAdapter = new CarList1Adapter(carList);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList.setAdapter(carListAdapter);
        getData();
        carListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PreferenceHelper.getInstance(getActivity()).putString("ccid", carList.get(position).getCcid());
                PreferenceHelper.getInstance(getActivity()).putString("car_id", carList.get(position).getUser_car_id());
                PreferenceHelper.getInstance(getActivity()).putString("atmos", carList.get(position).getZhu_apc());
                PreferenceHelper.getInstance(getActivity()).putString("latitude", carList.get(position).getGaode_x());
                PreferenceHelper.getInstance(getActivity()).putString("longitude", carList.get(position).getGaode_y());
                PreferenceHelper.getInstance(getActivity()).putString("car_server_id", carList.get(position).getServer_id());
                PreferenceHelper.getInstance(getActivity()).putString("car_number", carList.get(position).getPlate_number());
                PreferenceHelper.getInstance(getActivity()).putString("name", carList.get(position).getCar_brand_name());


                startActivity(new Intent(getActivity(), WindHeaterActivity.class));


                CAR_NOTIFY = "wit/server/" + getServer_id() + getCcid();
                Log.i("getInformation", "CAR_NOTIFY     " + CAR_NOTIFY);

                CAR_CTROL = "wit/cbox/hardware/" + getServer_id() + getCcid();
                Log.i("getInformation", "CAR_CTROL     " + CAR_CTROL);

                CARBOX_GETNOW = "wit/cbox/app/" + getServer_id() + getCcid();
                Log.i("getInformation", "CAR_CTROL     " + CARBOX_GETNOW);


                /**
                 * 向客户订阅地址发送车辆时时数据 汽车盒子刚刚启动->接入互联网->订阅本身地址->自己给自己订阅的地址发送心跳,发送，自己接收到了g.说明自己和MQTT连接在线
                 */
            }
        });

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarBrandActivity.actionStart(getActivity());

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
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
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
        map.put("key", Constant.KEY);
        map.put("user_car_type", "1");
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<SmartDevice_car_0364.DataBean>>post(Constant.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SmartDevice_car_0364.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SmartDevice_car_0364.DataBean>> response) {
                        srLSmart.finishRefresh();
                        carList.clear();
                        carList.addAll(response.body().data);

                        if (carList.size() == 0) {
                            View view = View.inflate(getActivity(), R.layout.online_empty_view, null);
                            carListAdapter.setHeaderView(view);
                        }
                        carListAdapter.notifyDataSetChanged();
                    }


                    @Override
                    public void onError(Response<AppResponse<SmartDevice_car_0364.DataBean>> response) {
                        AlertUtil.t(getActivity(), response.getException().getMessage());
                    }
                });

//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        DataIn in = new DataIn();
//        in.code = "03064";
//        in.key = Constant.KEY;
//        in.user_car_type = "1";
//        in.token = UserManager.getManager(getActivity()).getAppToken();
//        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, new Gson().toJson(in));
//        final Request request = new Request.Builder().url(Constant.SERVER_URL + "wit/app/user").post(body).build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Gson gson = new Gson();
//                SmartDevice_car_0364 bean = gson.fromJson(response.body().string(), SmartDevice_car_0364.class);
//
//
//                carList.clear();
//                carList.addAll(bean.getData());
//
//                getActivity().runOnUiThread(new Runnable() {
//                    public void run() {
//                        if (carList.size() == 0) {
//                            View view = View.inflate(getActivity(), R.layout.online_empty_view, null);
//                            carListAdapter.setHeaderView(view);
//                        }
//                        carListAdapter.notifyDataSetChanged();
//                    }
//                });
//
//            }
//        });

    }

}
