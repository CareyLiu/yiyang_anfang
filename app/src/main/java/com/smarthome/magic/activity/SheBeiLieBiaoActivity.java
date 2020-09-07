package com.smarthome.magic.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.SheBeiListAdapter;
import com.smarthome.magic.app.AppManager;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.AlarmClass;
import com.smarthome.magic.model.SheBeiLieBieListModel;
import com.smarthome.magic.model.SheBeiModel;
import com.smarthome.magic.tools.NetworkUtils;
import com.smarthome.magic.util.AlertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class SheBeiLieBiaoActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;
    private SheBeiListAdapter sheBeiListAdapter;
    private List<SheBeiModel> mDatas = new ArrayList<>();
    private String device_type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device_type = getIntent().getStringExtra("device_type");
        sheBeiListAdapter = new SheBeiListAdapter(R.layout.item_shebei, R.layout.item_shebei_header, mDatas);

//        carListAdapter = new CarList1Adapter(carList);
        rlvList.setLayoutManager(new LinearLayoutManager(mContext));
        rlvList.setAdapter(sheBeiListAdapter);


        sheBeiListAdapter.notifyDataSetChanged();
        sheBeiListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        if (mDatas.get(position).device_name.contains("风暖")) {
                            PreferenceHelper.getInstance(mContext).putString("ccid", mDatas.get(position).ccid);
                            int i = mDatas.get(position).ccid.length() - 1;
                            String str = String.valueOf(mDatas.get(position).ccid.charAt(i));
                            Log.i("serverId", str);
                            PreferenceHelper.getInstance(mContext).putString("car_server_id", str + "/");
                            if (NetworkUtils.isConnected(mContext)) {
                                Activity currentActivity = AppManager.getAppManager().currentActivity();
                                if (currentActivity != null) {
                                    startActivity(new Intent(mContext, WindHeaterActivity.class));
                                }
                            } else {
                                UIHelper.ToastMessage(mContext, "请连接网络后重新尝试");
                            }
                        } else {
                            // UIHelper.ToastMessage(getActivity(),"getActivity");
                        }

                        break;
                }
            }
        });
        getSheBeiData(device_type);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_shebei_liebiao;
    }

    public void getSheBeiData(String str) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03510");
        map.put("key", Urls.key);
        map.put("user_car_type", "1");
        map.put("device_type", str);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<SheBeiLieBieListModel.DataBean>>post(Urls.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SheBeiLieBieListModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SheBeiLieBieListModel.DataBean>> response) {
                        mDatas.clear();
                        for (int i = 0; i < response.body().data.size(); i++) {
                            SheBeiModel sheBeiModel = new SheBeiModel(true, response.body().data.get(i).getControl_device_name());
                            // Log.i("name", response.body().data.get(i).getControl_device_name());
                            mDatas.add(sheBeiModel);
                            for (int j = 0; j < response.body().data.get(i).getControl_device_list().size(); j++) {
                                // Log.i("name--shebei", response.body().data.get(i).getControl_device_list().get(j).getDevice_name());
                                SheBeiModel sheBeiModel1 = new SheBeiModel(false, response.body().data.get(i).getControl_device_name());
                                SheBeiLieBieListModel.DataBean.ControlDeviceListBean bean = response.body().data.get(i).getControl_device_list().get(j);
                                sheBeiModel1.ccid = bean.getCcid();
                                sheBeiModel1.device_img_url = bean.getDevice_img_url();
                                sheBeiModel1.device_name = bean.getDevice_name();
                                sheBeiModel1.validity_state = bean.getValidity_state();
                                sheBeiModel1.validity_term = bean.getValidity_term();
                                sheBeiModel1.validity_time = bean.getValidity_time();
                                mDatas.add(sheBeiModel1);
                            }
                        }

                        if (mDatas.size() == 0) {
                            View view = View.inflate(mContext, R.layout.online_empty_view, null);
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
                        AlertUtil.t(mContext, response.getException().getMessage());

                    }
                });

//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        DataIn in = new DataIn();
//        in.code = "03064";
//        in.key = Urls.key;
//        in.user_car_type = "1";
//        in.token = UserManager.getManager(getActivity()).getAppToken();
//        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, new Gson().toJson(in));
//        final Request request = new Request.Builder().url(Urls.SERVER_URL + "wit/app/user").post(body).build();
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

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_type) {
        Intent intent = new Intent(context, SheBeiLieBiaoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_type", device_type);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设备列表");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }
}
