package com.smarthome.magic.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.amap.api.services.core.LatLonPoint;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.DefaultX5WebView_HaveNameActivity;
import com.smarthome.magic.activity.SettingActivity;
import com.smarthome.magic.activity.ShuRuInterView;
import com.smarthome.magic.activity.homepage.DaLiBaoActivity;
import com.smarthome.magic.activity.tuangou.TuanGouShangJiaListActivity;
import com.smarthome.magic.activity.zijian_shangcheng.FenLeiThirdActivity;
import com.smarthome.magic.activity.zijian_shangcheng.ZiJianShopMallActivity;
import com.smarthome.magic.activity.zijian_shangcheng.ZiJianShopMallDetailsActivity;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.LordingDialog;
import com.smarthome.magic.dialog.TongYongShuRuDIalog;
import com.smarthome.magic.dialog.TongYongShuRuEditTextDIalog;
import com.smarthome.magic.dialog.newdia.FaBuDialog;
import com.smarthome.magic.dialog.newdia.FaBuLanMuDialog;
import com.smarthome.magic.dialog.newdia.TishiDialog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.BianMinFaBuBean;
import com.smarthome.magic.model.Home;
import com.smarthome.magic.model.JingYingXiangBean;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.GlideShowImageUtils;
import com.youth.banner.listener.OnBannerListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.app.App.JINGDU;
import static com.smarthome.magic.app.App.WEIDU;
import static com.smarthome.magic.get_net.Urls.HOME_PICTURE;
import static com.smarthome.magic.get_net.Urls.TONGCHENG;

public class BianMinFaBuActivity extends BaseActivity {
    @BindView(R.id.tv_tian_xie_biao_ti)
    TextView tvTianXieBiaoTi;
    @BindView(R.id.rl_fabubiaoti)
    RelativeLayout rlFabubiaoti;
    @BindView(R.id.tv_qingxuanze)
    TextView tvQingxuanze;
    @BindView(R.id.rl_fabulanmu)
    RelativeLayout rlFabulanmu;
    @BindView(R.id.rl_miaoshu_neirong)
    RelativeLayout rlMiaoshuNeirong;
    @BindView(R.id.tv_shuru_lianxiren)
    TextView tvShuruLianxiren;
    @BindView(R.id.rl_xiangxidizhi)
    RelativeLayout rlXiangxidizhi;
    @BindView(R.id.tv_xuanze)
    TextView tvXuanze;
    @BindView(R.id.iv_none_sel)
    ImageView ivNoneSel;
    TongYongShuRuDIalog shuRuDIalog;
    @BindView(R.id.tv_shurugonggao)
    TextView tvShurugonggao;
    @BindView(R.id.tv_xiangqingtu)
    TextView tvXiangqingtu;
    @BindView(R.id.rl_xiangqingtu_huashu)
    RelativeLayout rlXiangqingtuHuashu;

    String biaoTi = "";//标题
    String faBuLanMu = "";//发布栏目
    String miaoShuNeiRong = "";//描述内容
    String lianXiRen = "";//联系人
    String xiangXiDiZhi = "";//详细地址
    @BindView(R.id.tv_xiangxidizhi)
    TextView tvXiangxidizhi;
    @BindView(R.id.rl_xuanze)
    RelativeLayout rlXuanze;
    @BindView(R.id.tv_tongyi_huashu)
    TextView tvTongyiHuashu;
    @BindView(R.id.tv_fabuxuzhi)
    TextView tvFabuxuzhi;
    @BindView(R.id.rl_baocunxinxi)
    RoundRelativeLayout rlBaocunxinxi;
    public BianMinFaBuBean bianMinFaBuBean = new BianMinFaBuBean();
    private OptionsPickerView pvOptions;
    private int index = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_BIANMINFABU_HUICHUANDIZHI) {
                    //bianMinFaBuBean.diZhi = (String) message.content;
                    List<Object> list = (List<Object>) message.content;

                    bianMinFaBuBean.diZhi = (String) list.get(0);
                    LatLonPoint latLonPoint = (LatLonPoint) list.get(1);

                    bianMinFaBuBean.weiDu = String.valueOf(latLonPoint.getLatitude());
                    bianMinFaBuBean.jingDu = String.valueOf(latLonPoint.getLongitude());

                    tvXiangxidizhi.setText((CharSequence) list.get(0));
                }
            }
        }));


        rlFabubiaoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {
                        shuRuDIalog.dismiss();
                    }

                    @Override
                    public void submit(String str) {
                        tvTianXieBiaoTi.setText(str);
                        //   bianJiChangJingMingCheng(str);
                        biaoTi = str;
                        bianMinFaBuBean.biaoTi = biaoTi;
                    }
                }, "请输入标题");
                shuRuDIalog.show();
            }
        });

        rlFabulanmu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FaBuLanMuDialog faBuLanMuDialog = new FaBuLanMuDialog(BianMinFaBuActivity.this);
//                faBuLanMuDialog.show();
                // faBuLanMu();


                pvOptions.show();

            }
        });


        ShuRuInterView shuRuInterView = new ShuRuInterView() {
            @Override
            public void cannel() {

            }

            @Override
            public void submit(String str) {
                tvShurugonggao.setText(str);
                miaoShuNeiRong = str;
                bianMinFaBuBean.neiRongMiaoShu = miaoShuNeiRong;
            }
        };
        rlMiaoshuNeirong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongYongShuRuEditTextDIalog tongYongShuRuEditTextDIalog = new TongYongShuRuEditTextDIalog(mContext, shuRuInterView, "请输入您要发布的信息");
                tongYongShuRuEditTextDIalog.show();
            }
        });


        tvShuruLianxiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
                    @Override
                    public void cannel() {

                    }

                    @Override
                    public void submit(String str) {
                        tvShuruLianxiren.setText(str);
                        bianMinFaBuBean.lianXiDianHua = str;
                    }
                }, "请输入联系人");
                tongYongShuRuDIalog.show();
            }
        });

        rlXiangxidizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, PoiKeywordSearchActivity.class);
                startActivity(intent);

//                TongYongShuRuDIalog tongYongShuRuDIalog = new TongYongShuRuDIalog(mContext, new ShuRuInterView() {
//                    @Override
//                    public void cannel() {
//
//                    }
//
//                    @Override
//                    public void submit(String str) {
//                        tvXiangxidizhi.setText(str);
//                        xiangXiDiZhi = str;
//
//                    }
//                }, "详细地址");

            }
        });
        ivNoneSel.setBackgroundResource(R.mipmap.weixuanze);

        rlXuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ivNoneSel.setBackgroundResource(R.mipmap.yixuanze);
                if (xuanZe.equals("0")) {
                    xuanZe = "1";
                    ivNoneSel.setBackgroundResource(R.mipmap.yixuanze);
                } else if (xuanZe.equals("1")) {
                    ivNoneSel.setBackgroundResource(R.mipmap.weixuanze);
                    xuanZe = "0";
                }
            }
        });
        rlBaocunxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xuanZe.equals("0")) {
                    UIHelper.ToastMessage(mContext, "请您先选择阅读再进行保存信息操作");
                } else if (xuanZe.equals("1")) {
                    //UIHelper.ToastMessage(mContext, "");
                    saveData();
                }
            }
        });

        getJingYingXiang();
    }


    private void zhengHeLanMuData() {


    }

    private TishiDialog tishiDialog;

    public void saveData() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17001");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("x", PreferenceHelper.getInstance(mContext).getString(WEIDU, ""));
        map.put("y", PreferenceHelper.getInstance(mContext).getString(JINGDU, ""));
        map.put("ir_type", "3");

        //便民发布标题
        map.put("ir_title", bianMinFaBuBean.biaoTi);
        //便民发布栏目类型
        map.put("ir_column_type", bianMinFaBuBean.lanMuLeiXing);
        //便民发布栏目类型名称
        map.put("ir_column_type_name", bianMinFaBuBean.lanMuLeiXingMingCheng);
        //便民发布栏目类别
        map.put("ir_column_category", bianMinFaBuBean.lanMuLeiBie);
        //便民发布栏目类别名称
        map.put("ir_column_category_name", bianMinFaBuBean.lanMuLeiBieMingCheng);
        //pro
        map.put("pro", bianMinFaBuBean.proBeanList);
        //信息简介/内容描述
        map.put("ir_validity", bianMinFaBuBean.neiRongMiaoShu);
        //地址
        map.put("addr", bianMinFaBuBean.diZhi);
        //联系电话
        map.put("ir_contact_phone", bianMinFaBuBean.lianXiDianHua);
        //微信号
        map.put("ir_wx_number", bianMinFaBuBean.weiXinHao);

        Gson gson = new Gson();
        OkGo.<AppResponse<Home.DataBean>>post(TONGCHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Home.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<Home.DataBean>> response) {

                        Logger.d(gson.toJson(response.body()));
                        tishiDialog = new TishiDialog(mContext, 3, new TishiDialog.TishiDialogListener() {
                            @Override
                            public void onClickCancel(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onClickConfirm(View v, TishiDialog dialog) {

                            }

                            @Override
                            public void onDismiss(TishiDialog dialog) {

                            }
                        });
                        tishiDialog.setTextContent("已提交成功");
                        tishiDialog.setTextCancel("");
                        tishiDialog.show();
                    }

                    @Override
                    public void onError(Response<AppResponse<Home.DataBean>> response) {

                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<Home.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });


    }

    private List<Object> dataBeanList = new ArrayList<>();
    private List<List<Object>> dataBeanList1 = new ArrayList<>();

    public void getJingYingXiang() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "17003");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        map.put("itemtype_id", "4");
        Gson gson = new Gson();
        OkGo.<AppResponse<JingYingXiangBean.DataBean>>post(TONGCHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<JingYingXiangBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<JingYingXiangBean.DataBean>> response) {


                        pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                            @Override
                            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                //返回的分别是三个级别的选中位置
//                                UIHelper.ToastMessage(mContext, dataBeanList.get(options1) + "");
//                                UIHelper.ToastMessage(mContext, dataBeanList1.get(options2) + "");

                                Log.i("BianMinFaBuActivity", "dataBeanList的值" + dataBeanList.get(options1) + "");
                                Log.i("BianMinFaBuActivity", "dataBeanList1的值" + dataBeanList1.get(options2) + "");

                                for (int i = 0; i < response.body().data.size(); i++) {
                                    if (response.body().data.get(i).getItem_name().equals(dataBeanList.get(options1))) {
                                        bianMinFaBuBean.lanMuLeiBie = response.body().data.get(i).getItem_id();
                                        bianMinFaBuBean.lanMuLeiBieMingCheng = response.body().data.get(i).getItem_name();

                                        for (int j = 0; j < response.body().data.get(i).getItem().size(); j++) {
                                            bianMinFaBuBean.lanMuLeiXing = response.body().data.get(i).getItem().get(j).getItem_id();
                                            bianMinFaBuBean.lanMuLeiXingMingCheng = response.body().data.get(i).getItem().get(j).getItem_name();
                                        }
                                    }
                                }


                            }
                        }).build();

                        pvOptions.setTitleText("请选择栏目内容");

                        for (int i = 0; i < response.body().data.size(); i++) {
//                            JingYingXiangBean.DataBean jingYingXiangBean = new JingYingXiangBean.DataBean();
//                            jingYingXiangBean.setItem_name(response.body().data.get(i).getItem_name());
//                            jingYingXiangBean.setItem_id(response.body().data.get(i).getItem_id());

                            for (int j = 0; j < response.body().data.get(i).getItem().size(); j++) {
                                List<Object> list = new ArrayList<>();
                                list.add(response.body().data.get(i).getItem().get(j).getItem_name());
                                dataBeanList1.add(list);

                            }

                            dataBeanList.add(response.body().data.get(i).getItem_name());
                        }


                        pvOptions.setPicker(dataBeanList, dataBeanList1);
                        pvOptions.setSelectOptions(index);
                    }

                    @Override
                    public void onError(Response<AppResponse<JingYingXiangBean.DataBean>> response) {

                        AlertUtil.t(mContext, response.getException().getMessage());
                    }

                    @Override
                    public void onStart(Request<AppResponse<JingYingXiangBean.DataBean>, ? extends Request> request) {
                        super.onStart(request);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });


    }

    private String xuanZe = "0";//0为选择 1选择

    private void faBuLanMu() {
        FaBuLanMuDialog faBuDialog = new FaBuLanMuDialog(mContext, new FaBuLanMuDialog.FaBuDialogListener() {
            @Override
            public void shangJiaFaBu() {
                UIHelper.ToastMessage(mContext, "shangjiafabu");
            }

            @Override
            public void gongJiangFaBu() {

                UIHelper.ToastMessage(mContext, "gongjiang");
            }

            @Override
            public void bianMinFabu() {
                //UIHelper.ToastMessage(mContext, "便民");
                BianMinFaBuActivity.actionStart(mContext);
            }
        });
        faBuDialog.show();

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = faBuDialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        faBuDialog.getWindow().setAttributes(lp);
    }


    @Override
    public int getContentViewResId() {
        return R.layout.layout_bianmin_fabu;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("便民发布");
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
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BianMinFaBuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
