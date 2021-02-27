package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.smarthome.magic.dialog.ZhiNengFamilyAddDIalog;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.SuiYiTieModel;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class SuiYiTieSetting extends BaseActivity {
    @BindView(R.id.tv_family_name)
    TextView tvFamilyName;
    @BindView(R.id.tv_shebeiming)
    TextView tvShebeiming;
    @BindView(R.id.iv_shebeimingcheng)
    ImageView ivShebeimingcheng;
    @BindView(R.id.tv_shebeileixing)
    TextView tvShebeileixing;
    @BindView(R.id.tv_guanlianshebei)
    TextView tvGuanlianshebei;
    @BindView(R.id.tv_room_delete)
    TextView tvRoomDelete;
    @BindView(R.id.rl_shebeimingcheng)
    RelativeLayout rlShebeimingcheng;
    @BindView(R.id.iv_guanlian_icon)
    ImageView ivGuanlianIcon;
    @BindView(R.id.rl_guanlian)
    RelativeLayout rlGuanlian;
    private String device_ccid;
    private String device_ccidup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device_ccid = getIntent().getStringExtra("device_ccid");
        device_ccidup = getIntent().getStringExtra("device_ccidup");
        getnet();
        tvRoomDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(mContext,
                        "提示", "确定要删除设备吗？", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {
                    }

                    @Override
                    public void clickRight() {

                        //删除设备
                        shanChuSuiYiTie();


                    }

                });
                myCarCaoZuoDialog_caoZuoTIshi.show();
            }
        });
        rlShebeimingcheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(mContext, ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME);
                zhiNengFamilyAddDIalog.show();
            }
        });

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ROOM_DEVICE_CHANGENAME) {
                    xiuGaiMing(String.valueOf(message.content));
                }
            }
        }));


    }

    private void deleteDevice() {

    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_suiyitie_setting;
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设置");
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


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String device_ccid, String device_ccidup) {
        Intent intent = new Intent(context, SuiYiTieSetting.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        intent.putExtra("device_ccidup", device_ccidup);
        context.startActivity(intent);
    }

    private String family_id = "";

    @Override
    protected void onResume() {
        super.onResume();
        getnet();
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16052");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("device_ccid_up", device_ccidup);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<SuiYiTieModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SuiYiTieModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        // showLoadSuccess();

                        if (response.body().data.get(0).getBinding_type().equals("1")) {
                            tvGuanlianshebei.setText(response.body().data.get(0).getDevice_list().get(0).getBinding_device_name());
                            String str = response.body().data.get(0).getDevice_list().get(0).getBinding_device_name();
                            family_id = response.body().data.get(0).getDevice_list().get(0).getFamily_id();
                            if (StringUtils.isEmpty(str)) {

                            } else {
                                tvGuanlianshebei.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_CAOZUO, new TishiDialog.TishiDialogListener() {
                                            @Override
                                            public void onClickCancel(View v, TishiDialog dialog) {

                                            }

                                            @Override
                                            public void onClickConfirm(View v, TishiDialog dialog) {
                                                jiBangSuiYiTie();
                                            }

                                            @Override
                                            public void onDismiss(TishiDialog dialog) {

                                            }
                                        });
                                        dialog.setTextContent("是否与当前设备解绑");
                                        dialog.setTextCancel("否");
                                        dialog.setTextConfirm("是");
                                        dialog.show();
                                    }
                                });


                            }
                        } else if (response.body().data.get(0).getBinding_type().equals("2")) {
                            rlGuanlian.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SuiYiTieTianJiaSheBeiActivity.actionStart(mContext, "", device_ccid, device_ccidup, "1", device_ccid);
                                }
                            });
                        }
                        if (StringUtils.isEmpty(response.body().data.get(0).getBinding_type())) {
                            rlGuanlian.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SuiYiTieTianJiaSheBeiActivity.actionStart(mContext, "", device_ccid, device_ccidup, "1", device_ccid);
                                }
                            });
                            tvGuanlianshebei.setText("无");
                        }
                        tvFamilyName.setText(response.body().data.get(0).getFamily_name());
                        tvShebeiming.setText(response.body().data.get(0).getDevice_name());
                        tvShebeileixing.setText(response.body().data.get(0).getDevice_type_name());
                        family_id = response.body().data.get(0).getDevice_list().get(0).getFamily_id();

                    }

                    @Override
                    public void onError(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<SuiYiTieModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        //showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private void jiBangSuiYiTie() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16051");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("device_ccid_up", device_ccidup);

        map.put("unbinding_type", "1");
        map.put("family_id", family_id);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<SuiYiTieModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SuiYiTieModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "解除绑定成功");
                        getnet();
                    }

                    @Override
                    public void onError(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<SuiYiTieModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        //showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private void xiuGaiMing(String suiYiTieName) {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16054");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("device_ccid_up", device_ccidup);
        map.put("device_name", suiYiTieName);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<SuiYiTieModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SuiYiTieModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "随意贴名称修改成功");
                        getnet();
                    }

                    @Override
                    public void onError(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);
                    }

                    @Override
                    public void onStart(Request<AppResponse<SuiYiTieModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        //showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private void shanChuSuiYiTie() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16055");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_ccid", device_ccid);
        map.put("device_ccid_up", device_ccidup);
        map.put("family_id", family_id);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<SuiYiTieModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<SuiYiTieModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        UIHelper.ToastMessage(mContext, "随意贴解绑成功");
                        getnet();
                    }

                    @Override
                    public void onError(Response<AppResponse<SuiYiTieModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(mContext, str);
                    }

                    @Override
                    public void onStart(Request<AppResponse<SuiYiTieModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        //showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }
}
