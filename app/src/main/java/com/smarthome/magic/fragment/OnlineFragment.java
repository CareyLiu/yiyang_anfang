package com.smarthome.magic.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.CarBrandActivity;
import com.smarthome.magic.activity.CarListActivity;
import com.smarthome.magic.activity.ControCarActivity;
import com.smarthome.magic.activity.PlumbingHeaterActivity;
import com.smarthome.magic.activity.WindHeaterActivity;
import com.smarthome.magic.adapter.CarList1Adapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.DataIn;
import com.smarthome.magic.model.SmartDevice_car_0364;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        ImmersionBar.with(this).statusBarColor(R.color.black).init();
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

    }

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar.with(this).statusBarDarkFont(true)    .fitsSystemWindows(true).statusBarColor(R.color.white).init();
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
//        Map<String, String> map = new HashMap<>();
//        map.put("code", "03064");
//        map.put("key", Constant.KEY);
//        map.put("user_car_type", "1");
//        map.put("token", UserManager.getManager(this).getAppToken());
//        Gson gson = new Gson();
//        OkGo.<AppResponse<SmartDevice_car_0364.DataBean>>post(Constant.SERVER_URL + "wit/app/user")
//                .tag(this)//
//                .upJson(gson.toJson(map))
//                .execute(new JsonCallback<AppResponse<SmartDevice_car_0364.DataBean>>() {
//                    @Override
//                    public void onSuccess(final Response<AppResponse<SmartDevice_car_0364.DataBean>> response) {
//                        carList = response.body().data;
//                        carListAdapter.addAll(carList);
//                        mList.refreshComplete(10);
//                        lRecyclerViewAdapter.notifyDataSetChanged();
//
//                    }
//
//                    @Override
//                    public void onError(Response<AppResponse<SmartDevice_car_0364.DataBean>> response) {
//                        AlertUtil.t(CarListActivity.this, response.getException().getMessage());
//                    }
//                });

        OkHttpClient mOkHttpClient = new OkHttpClient();
        DataIn in = new DataIn();
        in.code = "03064";
        in.key = Constant.KEY;
        in.user_car_type = "1";
        in.token = UserManager.getManager(getActivity()).getAppToken();
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(in));
        final Request request = new Request.Builder().url(Constant.SERVER_URL + "wit/app/user").post(body).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                SmartDevice_car_0364 bean = gson.fromJson(response.body().string(), SmartDevice_car_0364.class);

                System.out.println(bean.getData().size() + "");
                for (int i = 0; i < bean.getData().size(); i++) {
                    System.out.println(bean.getData().get(i).getCar_brand_name());
                }
                carList.clear();
                carList.addAll(bean.getData());

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (carList.size() == 0) {
                            View view = View.inflate(getActivity(), R.layout.online_empty_view, null);
                            carListAdapter.setHeaderView(view);
                        }
                        carListAdapter.notifyDataSetChanged();
                    }
                });

            }
        });

    }

}
