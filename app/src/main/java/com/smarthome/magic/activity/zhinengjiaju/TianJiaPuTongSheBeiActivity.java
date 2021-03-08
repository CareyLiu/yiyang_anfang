package com.smarthome.magic.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.OneImageAdapter;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.FenLeiContentModel;
import com.smarthome.magic.model.ZhiNengFamilyEditBean;
import com.smarthome.magic.model.ZhiNengJiaJu_0007Model;
import com.smarthome.magic.mqtt_zhiling.ZnjjMqttMingLing;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.app.ConstanceValue.MSG_PEIWANG_SUCCESS;
import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class TianJiaPuTongSheBeiActivity extends BaseActivity {
    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.rlv_shebeilist)
    RecyclerView rlvShebeilist;
    OneImageAdapter oneImageAdapter;

    List<ZhiNengJiaJu_0007Model.MatchListBean> mDatas = new ArrayList<>();
    @BindView(R.id.btn_moni)
    Button btnMoni;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    @BindView(R.id.tv_huashu)
    TextView tvHuashu;
    @BindView(R.id.rl_tuichu)
    RoundRelativeLayout rlTuichu;
    @BindView(R.id.ll_main_tianjia)
    RelativeLayout llMainTianjia;
    private String ccid;
    private String serverId;
    ZhiNengJiaJu_0007Model zhiNengJiaJu_0007Model;
    private String cd_device_ccid;
    private String ccid_up;
    private String zhuji_device_ccid_up;
    private FenLeiContentModel fenLeiContentModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ccid = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");
        serverId = PreferenceHelper.getInstance(mContext).getString(AppConfig.SERVERID, "");
        zhuji_device_ccid_up = PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, "");

        fenLeiContentModel = (FenLeiContentModel) getIntent().getSerializableExtra("fenLeiContentModel");
        animationView.setAnimation("lottie.json");
        animationView.setImageAssetsFolder("images/");
        animationView.loop(true);
        animationView.playAnimation();

        oneImageAdapter = new OneImageAdapter(R.layout.item_rlv_shebeilist, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rlvShebeilist.setLayoutManager(linearLayoutManager);
        rlvShebeilist.setAdapter(oneImageAdapter);

//        oneImageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                switch (view.getId()) {
//                    case R.id.iv_image:
//                        cd_device_ccid = mDatas.get(position).cd_decice_ccid;
//                        zhuangZhiLeixing = "";
//                        zhuangZhiLeiXingXingHao = "";
//                        TishiDialog tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
//                            @Override
//                            public void onClickCancel(View v, TishiDialog dialog) {
//                            }
//
//                            @Override
//                            public void onClickConfirm(View v, TishiDialog dialog) {
//                                tianJiaSheBeiNet();
//                            }
//
//                            @Override
//                            public void onDismiss(TishiDialog dialog) {
//                            }
//                        });
//                        tishiDialog.setTextContent("已找到您要添加的设备，是否添加此设备？");
//                        tishiDialog.show();
//                        break;
//                }
//            }
//        });


//        rrlXiayibu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //模拟获得列表
//                Notice n = new Notice();
//                n.type = ConstanceValue.MSG_TIANJIASHEBEI;
//
//                ZhiNengJiaJu_0007Model zhiNengJiaJuNotifyJson = new ZhiNengJiaJu_0007Model();
//                ZhiNengJiaJu_0007Model.DataBean dataBean = new ZhiNengJiaJu_0007Model.DataBean();
//                dataBean.device_type_pic = "https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11920";
//
//                List<ZhiNengJiaJu_0007Model.DataBean> list = new ArrayList<>();
//                list.add(dataBean);
//                zhiNengJiaJuNotifyJson.setData(list);
//                n.content = zhiNengJiaJuNotifyJson;
//                RxBus.getDefault().sendRx(n);
//            }
//        });

        zhuangZhiLeixing = fenLeiContentModel.type;
        zhuangZhiLeiXingXingHao = fenLeiContentModel.category;
        sendMqttTianJiaSheBei();
        jieShouMqttTianJiaSheBei();

        btnMoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice n = new Notice();
                n.type = ConstanceValue.MSG_TIANJIASHEBEI;
                RxBus.getDefault().sendRx(n);
//            }
            }
        });

    }

    private ZnjjMqttMingLing znjjMqttMingLing;

    private String zhuangZhiLeixing;//装置类型
    private String zhuangZhiLeiXingXingHao;//装置类型的型号

    public void sendMqttTianJiaSheBei() {

        znjjMqttMingLing = new ZnjjMqttMingLing(mContext);

        znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(zhuji_device_ccid_up, serverId, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {

            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
        // TODO: 2021/2/2 添加的命令待赋值
        String str = "M12" + zhuangZhiLeixing + zhuangZhiLeiXingXingHao + "2";
        Log.i("Rair", str);


        znjjMqttMingLing.tianJiaSheBei(zhuji_device_ccid_up, serverId, str, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });


    }

    TishiDialog tishiDialog;

    public void jieShouMqttTianJiaSheBei() {

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_TIANJIASHEBEI) {
                    llMainTianjia.setVisibility(View.VISIBLE);
                    zhiNengJiaJu_0007Model = (ZhiNengJiaJu_0007Model) message.content;
                    //不做判断

                    for (int i = 0; i < zhiNengJiaJu_0007Model.getMatch_list().size(); i++) {
                        mDatas.add(zhiNengJiaJu_0007Model.getMatch_list().get(i));
                    }
                    oneImageAdapter.notifyDataSetChanged();

                    rlTuichu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });

                    Notice notice = new Notice();
                    notice.type = MSG_PEIWANG_SUCCESS;
                    RxBus.getDefault().sendRx(notice);

                    Notice notice1 = new Notice();
                    notice1.type = ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN;
                    sendRx(notice1);


//                    ZhiNengJiaJu_0007Model zhiNengJiaJu_0007Model = new ZhiNengJiaJu_0007Model();
//
//
//                    List<ZhiNengJiaJu_0007Model.MatchListBean> matchListBeans = new ArrayList<>();
//                    ZhiNengJiaJu_0007Model.MatchListBean matchListBean = new ZhiNengJiaJu_0007Model.MatchListBean();
//
//                    matchListBean.setDevice_type_pic("https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11709");
//
//                    ZhiNengJiaJu_0007Model.MatchListBean matchListBean1 = new ZhiNengJiaJu_0007Model.MatchListBean();
//
//                    matchListBean1.setDevice_type_pic("https://shop.hljsdkj.com/Frame/uploadFile/showImg?file_id=11709");
//
//                    matchListBeans.add(matchListBean);
//                    matchListBeans.add(matchListBean1);
//
//
//                    zhiNengJiaJu_0007Model.setMatch_list(matchListBeans);
//
//                    View view = View.inflate(mContext, R.layout.layout_duogeshebei_tianjiachenggong, null);
//                    RecyclerView recyclerView = view.findViewById(R.id.rlv_list);
//
//
//                    oneImageAdapter = new OneImageAdapter(R.layout.item_rlv_shebeilist, mDatas);
//                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//                    linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//                    recyclerView.setLayoutManager(linearLayoutManager);
//                    recyclerView.setAdapter(oneImageAdapter);
//                    oneImageAdapter.setNewData(zhiNengJiaJu_0007Model.getMatch_list());
//
//
//                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
//                    SnackbarUtils.addView(view, layoutParams);

                } else if (message.type == ConstanceValue.MSG_PEIWANG_ERROR) {
                    tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                        @Override
                        public void onClickCancel(View v, TishiDialog dialog) {

                        }

                        @Override
                        public void onClickConfirm(View v, TishiDialog dialog) {
                            tishiDialog.dismiss();
                        }

                        @Override
                        public void onDismiss(TishiDialog dialog) {

                        }
                    });
                    tishiDialog.setTextContent((String) message.content);
                    //tishiDialog.setTextCancel("退出");
                    tishiDialog.setTextCancel("");
                    tishiDialog.setTextConfirm("退出");
                    tishiDialog.show();
                }
            }
        }));
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tianjia_putongshebei;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("添加设备");

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
    public static void actionStart(Context context, FenLeiContentModel fenLeiContentModel) {
        Intent intent = new Intent(context, TianJiaPuTongSheBeiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("fenLeiContentModel", fenLeiContentModel);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (znjjMqttMingLing != null) {
            znjjMqttMingLing.subscribeAppShiShiXinXi_WithCanShu(ccid, serverId, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        }
    }

    /**
     *
     */
    private void tianJiaSheBeiNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16041");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("add_device_type", "2");
        map.put("mc_decice_ccid", PreferenceHelper.getInstance(mContext).getString(AppConfig.DEVICECCID, ""));
        map.put("cd_device_ccid", cd_device_ccid);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        UIHelper.ToastMessage(mContext, "设备添加成功");
                        finish();
                    }
                });
    }
}
