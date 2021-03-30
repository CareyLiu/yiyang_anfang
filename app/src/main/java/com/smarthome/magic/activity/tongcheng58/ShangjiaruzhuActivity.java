package com.smarthome.magic.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.services.core.LatLonPoint;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tongcheng58.model.TcBannerModel;
import com.smarthome.magic.activity.tongcheng58.model.TcHomeModel;
import com.smarthome.magic.activity.tongcheng58.model.TcLeimuModel;
import com.smarthome.magic.activity.tongcheng58.model.TcSheshiModel;
import com.smarthome.magic.activity.tongcheng58.model.TcUpLoadModel;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.get_net.Urls;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ShangjiaruzhuActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {


    @BindView(R.id.iv_logo_add)
    ImageView ivLogoAdd;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.tv_hangyefenlei)
    TextView tvHangyefenlei;
    @BindView(R.id.ll_hangyefenlei)
    LinearLayout llHangyefenlei;
    @BindView(R.id.tv_yingye_time)
    TextView tvYingyeTime;
    @BindView(R.id.ll_yingye_time)
    LinearLayout llYingyeTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_adress)
    LinearLayout llAdress;
    @BindView(R.id.ed_weixinhao)
    EditText edWeixinhao;
    @BindView(R.id.ed_shangjiagonggao)
    TextView edShangjiagonggao;
    @BindView(R.id.ed_shangjiajieshao)
    TextView edShangjiajieshao;
    @BindView(R.id.iv_banner_add)
    ImageView ivBannerAdd;
    @BindView(R.id.ed_zhekou)
    EditText edZhekou;
    @BindView(R.id.tv_stop_time)
    TextView tvStopTime;
    @BindView(R.id.ll_qizhi_time)
    LinearLayout llStopTime;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.ll_ruzhu_time)
    LinearLayout llStartTime;
    @BindView(R.id.iv_xieyi)
    ImageView ivXieyi;
    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;
    @BindView(R.id.bt_save)
    TextView btSave;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.iv_select1)
    ImageView ivSelect1;
    @BindView(R.id.tv_select1)
    TextView tvSelect1;
    @BindView(R.id.ll_select1)
    LinearLayout llSelect1;
    @BindView(R.id.iv_select2)
    ImageView ivSelect2;
    @BindView(R.id.tv_select2)
    TextView tvSelect2;
    @BindView(R.id.ll_select2)
    LinearLayout llSelect2;
    @BindView(R.id.iv_select3)
    ImageView ivSelect3;
    @BindView(R.id.tv_select3)
    TextView tvSelect3;
    @BindView(R.id.ll_select3)
    LinearLayout llSelect3;
    @BindView(R.id.iv_select4)
    ImageView ivSelect4;
    @BindView(R.id.tv_select4)
    TextView tvSelect4;
    @BindView(R.id.ll_select4)
    LinearLayout llSelect4;
    @BindView(R.id.iv_select5)
    ImageView ivSelect5;
    @BindView(R.id.tv_select5)
    TextView tvSelect5;
    @BindView(R.id.ll_select5)
    LinearLayout llSelect5;
    @BindView(R.id.ll_shangjiagonggao)
    LinearLayout llShangjiagonggao;
    @BindView(R.id.ll_shangjiajieshao)
    LinearLayout llShangjiajieshao;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private String ir_inst_logo;                    //商家logo
    private String ir_inst_logo_id;                 //商家logo的id
    private String ir_inst_name;                    //商家名称
    private String ir_industry_type;                //公司行业类型
    private String ir_industry_type_name;           //公司行业类型名称
    private String ir_industry_category;            //公司行业类别
    private String ir_industry_category_name;       //公司行业类别名称

    private String ir_inst_open_time;               //开业时间
    private String ir_inst_close_time;              //闭业时间
    private String ir_inst_begin_time;              //公司信息发布开始时间
    private String ir_inst_end_time;                //公司信息发布结束时间
    private String ir_contact_phone;                //联系电话
    private String x;                               //纬度
    private String y;                               //经度
    private String addr;                            //地址
    private String ir_wx_number;                    //微信号
    private String ir_inst_notice;                  //公司公告
    private String ir_validity;                     //公司简介
    private String ir_agio;                         //折扣
    private String ir_inst_device;                  //店内设备（多选传1,2,3,4）
    private String ir_inst_settled_time;            //公司入驻时间
    private boolean isXieyi;
    private List<TcBannerModel> bannerModels = new ArrayList<>();

    private int type = 0;//  1.ir_inst_logo  2.banner
    private OptionsPickerView<Object> leimuPicker;
    private List<TcLeimuModel.DataBean> leimuModels;

    final static private String gonggao = "gonggao";
    final static private String jieshao = "jieshao";

    @Override
    public int getContentViewResId() {
        return R.layout.tongcheng_act_shangjiaruzhu;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("商家入驻");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ShangjiaruzhuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
        initStart();
        initHuidiao();
    }

    private void initHuidiao() {
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_BIANMINFABU_HUICHUANDIZHI) {
                    List<Object> list = (List<Object>) message.content;

                    String dizhi = (String) list.get(0);

                    LatLonPoint latLonPoint = (LatLonPoint) list.get(1);
                    String x = String.valueOf(latLonPoint.getLatitude());
                    String y = String.valueOf(latLonPoint.getLongitude());

                } else if (message.type == ConstanceValue.MSG_TONGYONG_INPUT) {
                    String content = (String) message.content;
                    String input_type = message.input_type;
                    if (input_type.equals(gonggao)) {
                        edShangjiagonggao.setText(content);
                    } else if (input_type.equals(jieshao)) {
                        edShangjiajieshao.setText(content);
                    }
                }
            }
        }));
    }

    private void initStart() {
        ir_inst_open_time = "";
        ir_inst_close_time = "";
        ir_inst_begin_time = "";
        ir_inst_end_time = "";

        isXieyi = false;

        x = PreferenceHelper.getInstance(mContext).getString(App.JINGDU, "");
        y = PreferenceHelper.getInstance(mContext).getString(App.WEIDU, "");

        getSheshi();
    }

    private void getSheshi() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "17013");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("screening_condition", "shop_device");
        Gson gson = new Gson();
        OkGo.<AppResponse<TcSheshiModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TcSheshiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TcSheshiModel.DataBean>> response) {
                        ir_inst_device = "1,2";
                    }
                });
    }


    @OnClick({R.id.ll_shangjiagonggao, R.id.ll_shangjiajieshao, R.id.iv_logo_add, R.id.ll_hangyefenlei, R.id.ll_yingye_time, R.id.ll_adress, R.id.iv_banner_add, R.id.ll_qizhi_time, R.id.ll_ruzhu_time, R.id.iv_xieyi, R.id.tv_xieyi, R.id.bt_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_logo_add:
                getPicture(1);
                break;
            case R.id.ll_hangyefenlei:
                clickFenlei();
                break;
            case R.id.ll_yingye_time:
                showYingyeDialog();
                break;
            case R.id.ll_adress:
                tvAddress.setText("神灯科技");
                break;
            case R.id.iv_banner_add:
                bannerModels.add(new TcBannerModel("12053", "http://yjn-znjj.oss-cn-hangzhou.aliyuncs.com/20210329163025000001.jpg"));
                Y.t("添加了轮播图" + bannerModels.size());
                break;
            case R.id.ll_qizhi_time:
                showQizhiDialog();
                break;
            case R.id.ll_ruzhu_time:
                clickRuzhuTime();
                break;
            case R.id.iv_xieyi:
                clickXiyiSelect();
                break;
            case R.id.tv_xieyi:

                break;
            case R.id.bt_save:
                save();
                break;
            case R.id.ll_shangjiagonggao:
                TcInputActivity.actionStart(mContext, gonggao);
                break;
            case R.id.ll_shangjiajieshao:
                TcInputActivity.actionStart(mContext, jieshao);
                break;
        }
    }

    private void clickRuzhuTime() {
        String[] items = {"3个月", "6个月", "12个月"};
        ActionSheetDialog dialog = new ActionSheetDialog(mContext, items, null);
        dialog.isTitleShow(false);
        dialog.cancelText("完成");
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        ir_inst_settled_time = "3";
                        break;
                    case 1:
                        ir_inst_settled_time = "6";
                        break;
                    case 2:
                        ir_inst_settled_time = "12";
                        break;
                }
                tvStartTime.setText(ir_inst_settled_time + "个月");
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void save() {
        ir_inst_name = edName.getText().toString();
        ir_wx_number = edWeixinhao.getText().toString();
        ir_contact_phone = edPhone.getText().toString();
        ir_agio = edZhekou.getText().toString();
        addr = tvAddress.getText().toString();
        ir_inst_notice = edShangjiagonggao.getText().toString();
        ir_validity = edShangjiajieshao.getText().toString();

        if (TextUtils.isEmpty(ir_inst_logo)) {
            Y.t("请上传企业标志");
            return;
        }

        if (TextUtils.isEmpty(ir_inst_name)) {
            Y.t("请输入商家名称");
            return;
        }

        if (TextUtils.isEmpty(ir_industry_type)) {
            Y.t("请选择行业分类");
            return;
        }

        if (TextUtils.isEmpty(addr)) {
            Y.t("请选择详细地址");
            return;
        }

        if (TextUtils.isEmpty(ir_contact_phone)) {
            Y.t("请输入联系电话");
            return;
        }

        if (TextUtils.isEmpty(ir_inst_device)) {
            Y.t("请选择店内设施");
            return;
        }

        if (bannerModels.size() == 0) {
            Y.t("请上传轮播图");
            return;
        }

        if (TextUtils.isEmpty(ir_inst_settled_time)) {
            Y.t("请选择入驻时间");
            return;
        }

        if (!isXieyi) {
            Y.t("请同意商家发布须知");
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "17001");
        jsonObject.put("key", Urls.key);
        jsonObject.put("token", UserManager.getManager(mContext).getAppToken());
        jsonObject.put("ir_type", "2");
        jsonObject.put("ir_inst_logo", ir_inst_logo);
        jsonObject.put("ir_inst_logo_id", ir_inst_logo_id);
        jsonObject.put("ir_inst_name", ir_inst_name);
        jsonObject.put("ir_industry_type", ir_industry_type);
        jsonObject.put("ir_industry_type_name", ir_industry_type_name);
        jsonObject.put("ir_industry_category", ir_industry_category);
        jsonObject.put("ir_industry_category_name", ir_industry_category_name);
        jsonObject.put("ir_contact_phone", ir_contact_phone);
        jsonObject.put("x", x);
        jsonObject.put("y", y);
        jsonObject.put("addr", addr);
        jsonObject.put("ir_wx_number", ir_wx_number);
        jsonObject.put("pro", bannerModels);
        jsonObject.put("ir_validity", ir_validity);
        jsonObject.put("ir_inst_notice", ir_inst_notice);
        jsonObject.put("ir_agio", ir_agio);
        jsonObject.put("ir_inst_device", ir_inst_device);
        jsonObject.put("ir_inst_settled_time", ir_inst_settled_time);

        if (TextUtils.isEmpty(ir_inst_open_time) || TextUtils.isEmpty(ir_inst_close_time)) {
            jsonObject.put("ir_inst_open_time", "");
            jsonObject.put("ir_inst_close_time", "");
        } else {
            jsonObject.put("ir_inst_open_time", ir_inst_open_time);
            jsonObject.put("ir_inst_close_time", ir_inst_close_time);
        }

        if (TextUtils.isEmpty(ir_inst_begin_time) || TextUtils.isEmpty(ir_inst_end_time)) {
            jsonObject.put("ir_inst_begin_time", "");
            jsonObject.put("ir_inst_end_time", "");
        } else {
            jsonObject.put("ir_inst_begin_time", ir_inst_begin_time);
            jsonObject.put("ir_inst_end_time", ir_inst_end_time);
        }


        showProgressDialog();
        OkGo.<AppResponse<TcHomeModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(jsonObject.toJSONString())
                .execute(new JsonCallback<AppResponse<TcHomeModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcHomeModel.DataBean>> response) {
                        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {
                                finish();

                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        dialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<TcHomeModel.DataBean>> response) {
                        super.onError(response);
                        Y.tError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    private void clickXiyiSelect() {
        isXieyi = !isXieyi;
        if (isXieyi) {
            ivXieyi.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel_1);
        } else {
            ivXieyi.setImageResource(R.mipmap.tuya_faxian_icon_selector_sel);
        }
    }

    private void showQizhiDialog() {
        String[] items = {"开始时间", "结束时间"};
        ActionSheetDialog dialog = new ActionSheetDialog(mContext, items, null);
        dialog.isTitleShow(false);
        dialog.cancelText("完成");
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        clickData(0);
                        break;
                    case 1:
                        clickData(1);
                        break;
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showYingyeDialog() {
        String[] items = {"开业时间", "闭业时间"};
        ActionSheetDialog dialog = new ActionSheetDialog(mContext, items, null);
        dialog.isTitleShow(false);
        dialog.cancelText("完成");
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        clickTime(0);
                        break;
                    case 1:
                        clickTime(1);
                        break;
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void clickFenlei() {
        if (leimuPicker == null) {
            showProgressDialog();
            getLeimu();
        } else {
            leimuPicker.show();
        }
    }

    private void getLeimu() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "17003");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("itemtype_id", "5");
        Gson gson = new Gson();
        OkGo.<AppResponse<TcLeimuModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TcLeimuModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TcLeimuModel.DataBean>> response) {
                        leimuModels = response.body().data;
                        initLeimu();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });
    }

    private void initLeimu() {
        if (leimuModels.size() > 0) {
            List<Object> names1 = new ArrayList<>();
            List<List<Object>> names2 = new ArrayList<>();

            for (int i = 0; i < leimuModels.size(); i++) {
                TcLeimuModel.DataBean dataBean = leimuModels.get(i);
                names1.add(dataBean.getItem_name());
                List<TcLeimuModel.DataBean.ItemBean> item = dataBean.getItem();
                List<Object> names2Beans = new ArrayList<>();
                for (int j = 0; j < item.size(); j++) {
                    TcLeimuModel.DataBean.ItemBean bean = item.get(j);
                    names2Beans.add(bean.getItem_name());
                }
                names2.add(names2Beans);
            }


            //条件选择器
            leimuPicker = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    if (leimuModels != null && leimuModels.size() > 0) {

                        TcLeimuModel.DataBean dataBean = leimuModels.get(options1);
                        ir_industry_type = dataBean.getItem_id();
                        ir_industry_type_name = dataBean.getItem_name();

                        TcLeimuModel.DataBean.ItemBean itemBean = dataBean.getItem().get(option2);
                        ir_industry_category = itemBean.getItem_id();
                        ir_industry_category_name = itemBean.getItem_name();

                        tvHangyefenlei.setText(ir_industry_type_name + " - " + ir_industry_category_name);
                    }
                }
            }).build();

            leimuPicker.setPicker(names1, names2);
            leimuPicker.show();
        }
    }

    private void clickTime(int pos) {
        boolean[] select = {false, false, false, true, true, false};
        TimePickerView timePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                int hours = date.getHours();
                int minutes = date.getMinutes();
                String time;
                if (hours < 10) {
                    if (minutes < 10) {
                        time = "0" + hours + ":" + "0" + minutes;
                    } else {
                        time = "0" + hours + ":" + minutes;
                    }
                } else {
                    if (minutes < 10) {
                        time = hours + ":" + "0" + minutes;
                    } else {
                        time = hours + ":" + minutes;
                    }
                }
                if (pos == 0) {
                    ir_inst_open_time = time;
                } else {
                    ir_inst_close_time = time;
                }
                tvYingyeTime.setText(ir_inst_open_time + " 至 " + ir_inst_close_time);
            }
        })
                .setType(select)// 默认全部显示
                .build();
        timePicker.show();
    }

    private void clickData(int pos) {
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(new Date());
        Calendar endTime = Calendar.getInstance();
        endTime.set(2100, 12, 31);
        boolean[] select = {true, true, true, false, false, false};
        TimePickerView timePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                String dataS = Y.getData(date);
                if (pos == 0) {
                    ir_inst_begin_time = dataS;
                } else {
                    ir_inst_end_time = dataS;
                }
                tvStopTime.setText(ir_inst_begin_time + " 至 " + ir_inst_end_time);
            }
        })
                .setType(select)// 默认全部显示
                .setRangDate(startTime, endTime)
                .build();
        timePicker.show();
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(ShangjiaruzhuActivity.this, this));
        }
        return takePhoto;
    }

    private void getPicture(int pos) {
        type = pos;
        String[] items = {"拍照", "相册"};
        final ActionSheetDialog dialog = new ActionSheetDialog(mContext, items, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                Uri imageUri = Uri.fromFile(file);
                switch (position) {
                    case 0:
                        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                        break;
                    case 1:
                        takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                        break;
                }
                dialog.dismiss();
            }
        });
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void takeSuccess(TResult result) {
        //此处使用原图路径，不压缩
        File file = new File(result.getImage().getOriginalPath());
        OkGo.<AppResponse<TcUpLoadModel.DataBean>>post(Urls.TONG_CHENG_UPLOAD)
                .tag(this)//
                .isMultipart(true)
                .params("key", Urls.key)
                .params("token", UserManager.getManager(mContext).getAppToken())
                .params("type", "1")
                .params("file", file)
                .execute(new JsonCallback<AppResponse<TcUpLoadModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcUpLoadModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            if (type == 1) {
                                Glide.with(mContext).load(file.getPath()).into(ivLogoAdd);
                                TcUpLoadModel.DataBean dataBean = response.body().data.get(0);
                                ir_inst_logo = dataBean.getImg_url();
                                ir_inst_logo_id = dataBean.getImg_id();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<TcUpLoadModel.DataBean>> response) {
                        super.onError(response);
                        Y.tError(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }

                    @Override
                    public void onStart(Request<AppResponse<TcUpLoadModel.DataBean>, ? extends Request> request) {
                        super.onStart(request);
                        showProgressDialog();
                    }
                });
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 配置takerPhoto参数
     */
    public CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(800).setAspectY(800);
        return builder.create();
    }
}
