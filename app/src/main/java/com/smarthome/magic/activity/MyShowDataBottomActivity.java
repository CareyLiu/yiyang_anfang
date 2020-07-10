package com.smarthome.magic.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.itheima.wheelpicker.WheelPicker;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;

import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.LingPeiJianModel;
import com.smarthome.magic.util.AlertUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MyShowDataBottomActivity extends Activity {

    String strBuJian;// 什么部件
    @BindView(R.id.tv_cannel)
    TextView tvCannel;
    @BindView(R.id.tv_enter)
    TextView tvEnter;
    @BindView(R.id.wheel_picker1)
    WheelPicker wheelPicker1;
    @BindView(R.id.wheel_picker2)
    WheelPicker wheelPicker2;
    @BindView(R.id.wheel_picker3)
    WheelPicker wheelPicker3;


    List<LingPeiJianModel.DataBean> mList;//整体数据

    List<LingPeiJianModel.DataBean.ClBeanX> mXingHaoList;//型号
    List<LingPeiJianModel.DataBean.ClBeanX.ClBean> mGuiGeList;//规格
    @BindView(R.id.view)
    View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_show_lunzi_kongjian);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            strBuJian = getIntent().getStringExtra("strBuJian");
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        wheelPicker2.setItemTextColor(R.color.black);
        wheelPicker2.setSelectedItemTextColor(R.color.black_313131);
        wheelPicker2.setCyclic(false);


        wheelPicker1.setItemTextColor(R.color.black);
        wheelPicker1.setSelectedItemTextColor(R.color.black_313131);
        wheelPicker1.setCyclic(false);


        wheelPicker3.setItemTextColor(R.color.black);
        wheelPicker3.setSelectedItemTextColor(R.color.black_313131);
        wheelPicker3.setCyclic(false);


        if (strBuJian.equals("2") || strBuJian.equals("3")) {//显示三个  123
            wheelPicker2.setVisibility(View.VISIBLE);


        } else if (strBuJian.equals("9") || strBuJian.equals("10")) {//显示1个 留第二个

            wheelPicker2.setVisibility(View.VISIBLE);
            wheelPicker1.setVisibility(View.GONE);
            wheelPicker3.setVisibility(View.GONE);
        } else { //显示两个 留13

        }
        requestData11();
        tvCannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                finish();
            }
        });
        tvEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定

                // 接通网络

                if (strBuJian.equals("1")) {//点活塞

                    Map<String, String> map = new HashMap<>();
                    map.put("code", "03206");
                    map.put("key", Urls.key);
                    map.put("token", UserManager.getManager(MyShowDataBottomActivity.this).getAppToken());

                    map.put("ignition_all_name", "");//点火塞总名称
                    map.put("ignition_id_one", "");//点火塞总名称
                    map.put("ignition_name_one", "");//点火塞id(一级)
                    map.put("ignition_id_two", "");//点火塞id(厂家id)
                    map.put("ignition_name_two", "");//（点火塞）厂家名称
                    map.put("ignition_id_three", "");//点火塞id(电压id)
                    map.put("ignition_name_three", "");//（点火塞）电压名称


                    Gson gson = new Gson();

                    OkGo.<AppResponse>post(Urls.SERVER_URL + "wit/app/user")
                            .tag(this)//
                            .upJson(gson.toJson(map))
                            .execute(new JsonCallback<AppResponse>() {
                                @Override
                                public void onSuccess(final Response<AppResponse> response) {
                                    if (response.body().msg_code.equals("0000")) {
                                        UIHelper.ToastMessage(MyShowDataBottomActivity.this, "大气压参数输入成功", Toast.LENGTH_SHORT);

                                        finish();
                                    } else if (response.body().msg_code.equals("0001")) {
                                        UIHelper.ToastMessage(MyShowDataBottomActivity.this, response.body().msg, Toast.LENGTH_SHORT);
                                    }

                                }

                                @Override
                                public void onError(Response<AppResponse> response) {
                                    AlertUtil.t(MyShowDataBottomActivity.this, response.getException().getMessage());
                                }
                            });
                }
            }
        });

    }

    String id;//设备id
    String buJianName;//部件名
    List<String> mBujianMingList;//部件名 list
    List<String> mGuiGeList_NeiBu;//型号list
    List<String> mThreeList;//第三列数据

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String strBuJian) {
        Intent intent = new Intent(context, MyShowDataBottomActivity.class);
        intent.putExtra("strBuJian", strBuJian);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * { "code":"00005", "key":"20180305124455yu", "type_id":"instruction_type"}
     */
    public void requestData11() {//请求的参数
        Map<String, String> map = new HashMap<>();
        map.put("code", "00005");
        map.put("key", Urls.key);
        //map.put("token", UserManager.getManager(this).getAppToken());
        map.put("type_id", "zhu_parts_factory");
        Gson gson = new Gson();
        OkGo.<AppResponse<LingPeiJianModel.DataBean>>post(Urls.SERVER_URL + "msg")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<LingPeiJianModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<LingPeiJianModel.DataBean>> response) {


                        LingPeiJianModel.DataBean dataBean = response.body().data.get(0);
                        mList = response.body().data;

                        int buJian = Integer.parseInt(getIntent().getStringExtra("strBuJian"));
                        for (int i = 0; i < mList.size() - 1; i++) {

                            switch (buJian) {
                                case 0://点活塞
                                    //mBujianMingList.clear();
                                    mXingHaoList = mList.get(0).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mGuiGeList = mXingHaoList.get(j).getCl();
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }

                                    //解析并获得相应的规格list
                                    for (int k = 0; k < mGuiGeList.size(); k++) {
                                        mGuiGeList_NeiBu.add(mGuiGeList.get(k).getPar_name());
                                    }

                                    wheelPicker1.setData(mBujianMingList);
                                    wheelPicker3.setData(mGuiGeList_NeiBu);


                                    break;
                                case 1:

                                    //mBujianMingList.clear();
                                    mXingHaoList = mList.get(1).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mGuiGeList = mXingHaoList.get(j).getCl();
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }

                                    //解析并获得相应的规格list
                                    for (int k = 0; k < mGuiGeList.size(); k++) {
                                        mGuiGeList_NeiBu.add(mGuiGeList.get(k).getPar_name());
                                    }

                                    wheelPicker1.setData(mBujianMingList);
                                    wheelPicker3.setData(mGuiGeList_NeiBu);

                                    break;
                                case 2:
                                    //mBujianMingList.clear();
                                    mXingHaoList = mList.get(2).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mGuiGeList = mXingHaoList.get(j).getCl();
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }

                                    //解析并获得相应的规格list
                                    for (int k = 0; k < mGuiGeList.size(); k++) {
                                        mGuiGeList_NeiBu.add(mGuiGeList.get(k).getPar_name());
                                    }

                                    mThreeList = new ArrayList<>();

                                    for (int m = 0; m < mGuiGeList.get(0).getCl().size(); m++) {
                                        mThreeList.add(mGuiGeList.get(0).getCl().get(m).getPar_name());

                                    }

                                    wheelPicker1.setData(mBujianMingList);


                                    wheelPicker2.setData(mGuiGeList_NeiBu);


                                    wheelPicker3.setData(mThreeList);


                                    //   wheelPicker1.notify();
                                    //   wheelPicker2.notify();
                                    // wheelPicker3.notify();
                                    //   break;
                                case 3:

                                    //mBujianMingList.clear();
                                    mXingHaoList = mList.get(3).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mGuiGeList = mXingHaoList.get(j).getCl();
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }

                                    //解析并获得相应的规格list
                                    for (int k = 0; k < mGuiGeList.size(); k++) {
                                        mGuiGeList_NeiBu.add(mGuiGeList.get(k).getPar_name());
                                    }

                                    mThreeList = new ArrayList<>();

                                    for (int m = 0; m < mGuiGeList.get(0).getCl().size(); m++) {
                                        mThreeList.add(mGuiGeList.get(0).getCl().get(m).getPar_name());

                                    }

                                    wheelPicker1.setData(mBujianMingList);


                                    wheelPicker2.setData(mGuiGeList_NeiBu);

                                    wheelPicker3.setData(mThreeList);


                                    //  wheelPicker1.notify();
                                    //  wheelPicker2.notify();
                                    //  wheelPicker3.notify();

                                    break;
                                case 4:
                                    //mBujianMingList.clear();
                                    mXingHaoList = mList.get(4).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mGuiGeList = mXingHaoList.get(j).getCl();
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }

                                    //解析并获得相应的规格list
                                    for (int k = 0; k < mGuiGeList.size(); k++) {
                                        mGuiGeList_NeiBu.add(mGuiGeList.get(k).getPar_name());
                                    }

                                    wheelPicker1.setData(mBujianMingList);


                                    wheelPicker3.setData(mGuiGeList_NeiBu);


                                    break;
                                case 5:
                                    //mBujianMingList.clear();
                                    mXingHaoList = mList.get(5).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mGuiGeList = mXingHaoList.get(j).getCl();
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }

                                    //解析并获得相应的规格list
                                    for (int k = 0; k < mGuiGeList.size(); k++) {
                                        mGuiGeList_NeiBu.add(mGuiGeList.get(k).getPar_name());
                                    }

                                    wheelPicker1.setData(mBujianMingList);


                                    wheelPicker3.setData(mGuiGeList_NeiBu);

                                    break;
                                case 6:
                                    //mBujianMingList.clear();
                                    mXingHaoList = mList.get(6).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mGuiGeList = mXingHaoList.get(j).getCl();
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }

                                    //解析并获得相应的规格list
                                    for (int k = 0; k < mGuiGeList.size(); k++) {
                                        mGuiGeList_NeiBu.add(mGuiGeList.get(k).getPar_name());
                                    }

                                    wheelPicker1.setData(mBujianMingList);


                                    wheelPicker3.setData(mGuiGeList_NeiBu);

                                    break;
                                case 7:
                                    //mBujianMingList.clear();
                                    mXingHaoList = mList.get(7).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mGuiGeList = mXingHaoList.get(j).getCl();
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }

                                    //解析并获得相应的规格list
                                    for (int k = 0; k < mGuiGeList.size(); k++) {
                                        mGuiGeList_NeiBu.add(mGuiGeList.get(k).getPar_name());
                                    }

                                    wheelPicker1.setData(mBujianMingList);


                                    wheelPicker3.setData(mGuiGeList_NeiBu);

                                    break;
                                case 8:
                                    //mBujianMingList.clear();
                                    mXingHaoList = mList.get(8).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mGuiGeList = mXingHaoList.get(j).getCl();
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }

                                    //解析并获得相应的规格list
                                    for (int k = 0; k < mGuiGeList.size(); k++) {
                                        mGuiGeList_NeiBu.add(mGuiGeList.get(k).getPar_name());
                                    }

                                    wheelPicker1.setData(mBujianMingList);


                                    wheelPicker3.setData(mGuiGeList_NeiBu);

                                    break;
                                case 9:
                                    mXingHaoList = mList.get(9).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }

                                    wheelPicker2.setData(mBujianMingList);


                                    break;
                                case 10:
                                    mXingHaoList = mList.get(10).getCl();
                                    mBujianMingList = new ArrayList<>();
                                    mGuiGeList_NeiBu = new ArrayList<>();
                                    for (int j = 0; j < mXingHaoList.size(); j++) {
                                        mBujianMingList.add(mXingHaoList.get(j).getPar_name());
                                    }


                                    wheelPicker2.setData(mBujianMingList);


                                    break;
                            }

                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<LingPeiJianModel.DataBean>> response) {

                    }
                });
    }

//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        DataLingPeiJianIn in = new DataLingPeiJianIn();
//        in.code = "00005";
//        in.key = Urls.key;
//        in.type_id = "zhu_parts_factory";
//
//        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, new Gson().toJson(in));
//        final Request request = new Request.Builder().url(Urls.SERVER_URL + "msg").post(body).build();
//        Log.i("lingpeijianbody",new Gson().toJson(request.url()));
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                LogUtil.i("lingpeijianbody", new Gson().toJson(response.body()));
//
//            }
//        });
}

