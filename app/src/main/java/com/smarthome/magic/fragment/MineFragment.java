package com.smarthome.magic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.flyco.roundview.RoundRelativeLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.SettingActivity;
import com.smarthome.magic.activity.dingdan.MyOrderActivity;
import com.smarthome.magic.activity.tuangou.KaQuanActivity;
import com.smarthome.magic.activity.wode_page.AboutUsActivity;
import com.smarthome.magic.activity.wode_page.MyQianBaoActivity;
import com.smarthome.magic.activity.wode_page.TuiGuangMaActivity;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppEvent;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.MineModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class MineFragment extends BaseFragment implements Observer {

    Unbinder unbinder;
    @BindView(R.id.riv_image)
    CircleImageView rivImage;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_shezhi)
    ImageView ivShezhi;
    @BindView(R.id.iv_gouwuche)
    ImageView ivGouwuche;
    @BindView(R.id.tv_shoucangjia_number)
    TextView tvShoucangjiaNumber;
    @BindView(R.id.tv_shoucangjia)
    TextView tvShoucangjia;
    @BindView(R.id.tv_guanzhu_dianpu)
    TextView tvGuanzhuDianpu;
    @BindView(R.id.tv_guanzhu_dianpu_num)
    TextView tvGuanzhuDianpuNum;
    @BindView(R.id.tv_zhanghu_jifen)
    TextView tvZhanghuJifen;
    @BindView(R.id.tv_zhanghu_jifen_number)
    TextView tvZhanghuJifenNumber;
    @BindView(R.id.tv_kajuan)
    TextView tvKajuan;
    @BindView(R.id.iv_mine_icon_qianbao)
    ImageView ivMineIconQianbao;
    @BindView(R.id.tv_wodeqianbao)
    TextView tvWodeqianbao;
    @BindView(R.id.rlv_qianbao)
    RoundRelativeLayout rlvQianbao;
    @BindView(R.id.tv_dingdan)
    TextView tvDingdan;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_daifukuan)
    ImageView ivDaifukuan;
    @BindView(R.id.iv_daifahuo)
    ImageView ivDaifahuo;
    @BindView(R.id.tv_daifahuo)
    TextView tvDaifahuo;
    @BindView(R.id.iv_daishouhuo)
    ImageView ivDaishouhuo;
    @BindView(R.id.iv_pingjia)
    ImageView ivPingjia;
    @BindView(R.id.tv_pingjia)
    TextView tvPingjia;
    @BindView(R.id.iv_daodian)
    ImageView ivDaodian;
    @BindView(R.id.rlv_dingdan)
    RoundRelativeLayout rlvDingdan;
    @BindView(R.id.tv_ershou_che)
    TextView tvErshouChe;
    @BindView(R.id.iv_che_yuyue)
    ImageView ivCheYuyue;
    @BindView(R.id.iv_chejindu)
    ImageView ivChejindu;
    @BindView(R.id.tv_chejindu)
    TextView tvChejindu;
    @BindView(R.id.iv_maiche_jindu)
    ImageView ivMaicheJindu;
    @BindView(R.id.tv_maiche_jindu)
    TextView tvMaicheJindu;
    @BindView(R.id.iv_xiaofeijilu)
    ImageView ivXiaofeijilu;
    @BindView(R.id.tv_shouhou)
    TextView tvShouhou;
    @BindView(R.id.iv_quanbu_dingdan)
    ImageView ivQuanbuDingdan;
    @BindView(R.id.rlv_ershouche)
    RoundRelativeLayout rlvErshouche;
    @BindView(R.id.tv_zhanghu_chongzhi)
    TextView tvZhanghuChongzhi;
    @BindView(R.id.iv_zhanghu_chongzhi)
    ImageView ivZhanghuChongzhi;
    @BindView(R.id.iv_kapianchongzhi)
    ImageView ivKapianchongzhi;
    @BindView(R.id.tv_kapianchongzhi)
    TextView tvKapianchongzhi;
    @BindView(R.id.iv_chongzhi_jilu)
    ImageView ivChongzhiJilu;
    @BindView(R.id.tv_chongzhi_jilu)
    TextView tvChongzhiJilu;
    @BindView(R.id.iv_shouhou_fuwu)
    ImageView ivShouhouFuwu;
    @BindView(R.id.tv_xiaofeijilu)
    TextView tvXiaofeijilu;
    @BindView(R.id.iv_xiche_erwei)
    ImageView ivXicheErwei;
    @BindView(R.id.rlv_xiche)
    RoundRelativeLayout rlvXiche;
    @BindView(R.id.iv_tuiguangma)
    ImageView ivTuiguangma;
    @BindView(R.id.tv_tuiguangma)
    TextView tvTuiguangma;
    @BindView(R.id.iv_dailishang)
    ImageView ivDailishang;
    @BindView(R.id.iv_about_us)
    ImageView ivAboutUs;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.rlv_about_us)
    RoundRelativeLayout rlvAboutUs;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv_qianbao_enter)
    ImageView ivQianbaoEnter;
    @BindView(R.id.ll_shoucangjia)
    LinearLayout llShoucangjia;
    @BindView(R.id.ll_guanzhudianpu)
    LinearLayout llGuanzhudianpu;
    @BindView(R.id.ll_zhanghujifen)
    LinearLayout llZhanghujifen;
    @BindView(R.id.ll_kaquan)
    LinearLayout llKaquan;
    @BindView(R.id.tv_kajuan_number)
    TextView tvKajuanNumber;
    @BindView(R.id.tv_daifukuan)
    TextView tvDaifukuan;
    @BindView(R.id.tv_daishouhuo)
    TextView tvDaishouhuo;
    @BindView(R.id.tv_daodian)
    TextView tvDaodian;
    @BindView(R.id.constrain1)
    ConstraintLayout constrain1;
    @BindView(R.id.constrain2)
    ConstraintLayout constrain2;
    @BindView(R.id.constrain3)
    ConstraintLayout constrain3;


    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_fragment_wode;
    }

    @Override
    protected void initView(View rootView) {
        rootView.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件
        AppEvent.getClassEvent().addObserver(this);
        initData();
        unbinder = ButterKnife.bind(this, rootView);
    }

    public void initData() {
        getNet();

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_PAY_SUCCESS_REFRESH_WODE) {
                    getNet();

                }

            }
        }));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        llGuanzhudianpu.setOnClickListener(this);
//        llKaquan.setOnClickListener(this);
//        llShoucangjia.setOnClickListener(this);
//        llZhanghujifen.setOnClickListener(this);


    }


    MineModel.DataBean dataBean;
    private String aliPayCheck;//

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("code", "04201");


        Gson gson = new Gson();
        OkGo.<AppResponse<MineModel.DataBean>>post(Urls.HOME_PICTURE_HOME)
                .tag(getActivity())//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MineModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MineModel.DataBean>> response) {
                        Log.i("MineFragment", "success");
                        //  MineModel.DataBean dataBean = new MineModel.DataBean();
                        dataBean = response.body().data.get(0);
                        Glide.with(getActivity()).load(response.body().data.get(0).getUser_img_url()).into(rivImage);
                        tvName.setText(dataBean.getUser_name());
                        tvPhone.setText(dataBean.getUser_phone());
                        tvShoucangjiaNumber.setText(dataBean.getCollect_ware_count());
                        tvGuanzhuDianpuNum.setText(dataBean.getCollect_shop_count());
                        tvZhanghuJifenNumber.setText("0");
                        tvKajuanNumber.setText(dataBean.getVoucher_count());

                        PreferenceHelper.getInstance(getActivity()).putString(App.CUNCHUBIND_ALIPAY, response.body().data.get(0).getAlipay_number_check());
                        PreferenceHelper.getInstance(getActivity()).putString(App.CUNCHU_ZHIFUMIMA, response.body().data.get(0).getPay_pwd_check());//1 已经设置 2 未设置

                        if (!StringUtils.isEmpty(dataBean.getReferral_code_url())) {
                            PreferenceHelper.getInstance(getActivity()).putString(App.SHIFOUYOUSHANGJI, "1");//1 有上级
                        } else {
                            PreferenceHelper.getInstance(getActivity()).putString(App.SHIFOUYOUSHANGJI, "0");//没有上级
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<MineModel.DataBean>> response) {
                        super.onError(response);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void update(Observable o, Object arg) {

        if (arg.equals("update")) {
            initData();
        }
    }


    @OnClick({R.id.riv_image, R.id.tv_name, R.id.iv_shezhi, R.id.iv_gouwuche, R.id.tv_shoucangjia_number,
            R.id.tv_shoucangjia, R.id.tv_guanzhu_dianpu, R.id.tv_guanzhu_dianpu_num, R.id.tv_zhanghu_jifen,
            R.id.tv_zhanghu_jifen_number, R.id.tv_kajuan, R.id.iv_mine_icon_qianbao, R.id.tv_wodeqianbao,
            R.id.rlv_qianbao, R.id.tv_dingdan, R.id.tv_all, R.id.iv_back, R.id.iv_daifukuan, R.id.iv_daifahuo,
            R.id.tv_daifahuo, R.id.iv_daishouhuo, R.id.iv_pingjia, R.id.tv_pingjia, R.id.iv_daodian, R.id.rlv_dingdan,
            R.id.tv_ershou_che, R.id.iv_che_yuyue, R.id.iv_chejindu, R.id.tv_chejindu, R.id.iv_maiche_jindu,
            R.id.tv_maiche_jindu, R.id.iv_xiaofeijilu, R.id.tv_shouhou, R.id.iv_quanbu_dingdan, R.id.rlv_ershouche, R.id.tv_zhanghu_chongzhi,
            R.id.iv_zhanghu_chongzhi, R.id.iv_kapianchongzhi, R.id.tv_kapianchongzhi, R.id.iv_chongzhi_jilu, R.id.tv_chongzhi_jilu, R.id.iv_shouhou_fuwu,
            R.id.tv_xiaofeijilu, R.id.iv_xiche_erwei, R.id.rlv_xiche, R.id.iv_tuiguangma, R.id.tv_tuiguangma, R.id.iv_dailishang, R.id.iv_about_us,
            R.id.view, R.id.rlv_about_us, R.id.ll_kaquan, R.id.tv_daifukuan, R.id.tv_daishouhuo, R.id.tv_daodian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.riv_image:
                break;
            case R.id.tv_name:
                break;
            case R.id.iv_shezhi:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.iv_gouwuche:
                break;
            case R.id.tv_shoucangjia_number:
                break;
            case R.id.tv_shoucangjia:
                break;
            case R.id.tv_guanzhu_dianpu:
                break;
            case R.id.tv_guanzhu_dianpu_num:
                break;
            case R.id.tv_zhanghu_jifen:
                break;
            case R.id.tv_zhanghu_jifen_number:
                break;
            case R.id.tv_kajuan:
                break;
            case R.id.iv_mine_icon_qianbao:
                break;
            case R.id.tv_wodeqianbao:
                break;
            case R.id.rlv_qianbao:
                MyQianBaoActivity.actionStart(getActivity());
                break;
            case R.id.tv_dingdan:
                break;
            case R.id.tv_all:
                //UIHelper.ToastMessage(getActivity(), "功能模块开发中，敬请期待");
                MyOrderActivity.actionStart(getActivity(), "");
                break;
            case R.id.iv_back:
                break;
            case R.id.iv_daifukuan:
            case R.id.tv_daifukuan:
                MyOrderActivity.actionStart(getActivity(), "待付款");
                break;
            case R.id.iv_daifahuo:
            case R.id.tv_daifahuo:
                MyOrderActivity.actionStart(getActivity(), "待发货");
                break;
            case R.id.iv_daishouhuo:
            case R.id.tv_daishouhuo:
                MyOrderActivity.actionStart(getActivity(), "待收货");
                break;
            case R.id.iv_pingjia:
            case R.id.tv_pingjia:
                MyOrderActivity.actionStart(getActivity(), "待评价");
                break;
            case R.id.iv_daodian:
            case R.id.tv_daodian:
                MyOrderActivity.actionStart(getActivity(), "到店");
                break;
            case R.id.rlv_dingdan:
                break;
            case R.id.tv_ershou_che:
                break;
            case R.id.iv_che_yuyue:
                break;
            case R.id.iv_chejindu:
                break;
            case R.id.tv_chejindu:
                break;
            case R.id.iv_maiche_jindu:
                break;
            case R.id.tv_maiche_jindu:
                break;
            case R.id.iv_xiaofeijilu:
                break;
            case R.id.tv_shouhou:
                break;
            case R.id.iv_quanbu_dingdan:
                break;
            case R.id.rlv_ershouche:
                break;
            case R.id.tv_zhanghu_chongzhi:
                break;
            case R.id.iv_zhanghu_chongzhi:
                break;
            case R.id.iv_kapianchongzhi:
                break;
            case R.id.tv_kapianchongzhi:
                break;
            case R.id.iv_chongzhi_jilu:
                break;
            case R.id.tv_chongzhi_jilu:
                break;
            case R.id.iv_shouhou_fuwu:
                break;
            case R.id.tv_xiaofeijilu:
                break;
            case R.id.iv_xiche_erwei:
                break;
            case R.id.rlv_xiche:
                break;
            case R.id.iv_tuiguangma:
                //UIHelper.ToastMessage(getActivity(), "进入二维码");

                if (null == dataBean.getReferral_code_url()) {
                    return;
                }
                if (!StringUtils.isEmpty(dataBean.getReferral_code_url())) {
                    TuiGuangMaActivity.actionStart(getActivity());
                } else {
                    UIHelper.ToastMessage(getActivity(), "请先购买商品，方可获得自己的推广码");
                }

                break;
            case R.id.tv_tuiguangma:
                break;
            case R.id.iv_dailishang:
                break;
            case R.id.iv_about_us:
                AboutUsActivity.actionStart(getActivity());
                break;
            case R.id.view:
                break;
            case R.id.rlv_about_us:
                break;
            case R.id.ll_shoucangjia://收藏夹
                break;
            case R.id.ll_guanzhudianpu://关注店铺
                break;
            case R.id.ll_zhanghujifen://账户积分
                break;
            case R.id.ll_kaquan://卡券
                KaQuanActivity.actionStart(getActivity());
                //   UIHelper.ToastMessage(getActivity(), "点击了卡券");
                break;
        }
    }

}
