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
import com.smarthome.magic.activity.zhinengjiaju.function.MenCiActivity;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_Success;
import com.smarthome.magic.dialog.ZhiNengFamilyAddDIalog;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.SuiYiTieModel;
import com.smarthome.magic.model.ZhiNengFamilyEditBean;
import com.smarthome.magic.model.ZhiiNengRoomDeviceRoomBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengJiaJuZhuangZhiSetting extends BaseActivity {
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

    private String device_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        device_id = getIntent().getStringExtra("device_id");
        getnet();
        tvRoomDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDevice();
            }
        });
    }


    @Override
    public int getContentViewResId() {
        return R.layout.zhinengjiaju_setting;
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
    public static void actionStart(Context context, String device_id) {
        Intent intent = new Intent(context, ZhiNengJiaJuZhuangZhiSetting.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_id", device_id);
        context.startActivity(intent);
    }

    private String family_id = "";

    @Override
    protected void onResume() {
        super.onResume();
        getnet();
    }

    private Response<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>> response1;

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16035");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("device_id", device_id);
        Gson gson = new Gson();
        String a = gson.toJson(map);
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>> response) {
                        // showLoadSuccess();
                        response1 = response;
                        tvFamilyName.setText(response.body().data.get(0).getFamily_name());
                        tvShebeiming.setText(response.body().data.get(0).getDevice_name());
                        tvShebeileixing.setText(response.body().data.get(0).getDevice_type_name());

                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>> response) {
                        String str = response.getException().getMessage();
                        Log.i("cuifahuo", str);
                        String[] str1 = str.split("：");
                        UIHelper.ToastMessage(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<ZhiiNengRoomDeviceRoomBean.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        //showLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    private void deleteDevice() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "16034");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("family_id", response1.body().data.get(0).getFamily_id());
        map.put("device_id", response1.body().data.get(0).getDevice_id());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            MyCarCaoZuoDialog_Success myCarCaoZuoDialog_success = new MyCarCaoZuoDialog_Success(ZhiNengJiaJuZhuangZhiSetting.this,
                                    "成功", "设备删除成功", "好的", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {
                                    finish();
                                }
                            });
                            myCarCaoZuoDialog_success.show();
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        String str = response.getException().getMessage();
                        Log.i("cuifahuo", str);
                        MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(mContext,
                                "提示", response.getException().getMessage(), "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {

                            }
                        });
                        myCarCaoZuoDialog_caoZuoTIshi.show();
                    }

                });
    }

}
