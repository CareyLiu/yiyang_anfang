package com.yiyang.cn.fragment.yiyang;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.AddJiarenActivity;
import com.yiyang.cn.activity.a_yiyang.JigouyanglaoActivity;
import com.yiyang.cn.activity.a_yiyang.JigouyanglaoWdActivity;
import com.yiyang.cn.activity.a_yiyang.YiyangTuTActivity;
import com.yiyang.cn.activity.a_yiyang.YySetAactivity;
import com.yiyang.cn.activity.dingdan.MyOrderActivity;
import com.yiyang.cn.activity.wode_page.MyQianBaoActivity;
import com.yiyang.cn.app.App;
import com.yiyang.cn.app.ConstanceValue;
import com.yiyang.cn.app.Notice;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.AppResponse;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.config.UserManager;
import com.yiyang.cn.get_net.Urls;
import com.yiyang.cn.model.MineModel;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class TabWodeFragment extends BaseFragment {


    @BindView(R.id.iv_set)
    ImageView ivSet;
    @BindView(R.id.iv_erweima)
    ImageView ivErweima;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_shimingzhi)
    TextView tvShimingzhi;
    @BindView(R.id.ll_wd_fuwu)
    LinearLayout llWdFuwu;
    @BindView(R.id.ll_wd_huodong)
    LinearLayout llWdHuodong;
    @BindView(R.id.ll_wd_jiankang)
    LinearLayout llWdJiankang;
    @BindView(R.id.ll_wd_jigou)
    LinearLayout llWdJigou;
    @BindView(R.id.ll_qianbao)
    LinearLayout llQianbao;
    @BindView(R.id.ll_daifukuan)
    LinearLayout llDaifukuan;
    @BindView(R.id.ll_daifahuo)
    LinearLayout llDaifahuo;
    @BindView(R.id.ll_daishouhuo)
    LinearLayout llDaishouhuo;
    @BindView(R.id.ll_pingjia)
    LinearLayout llPingjia;
    @BindView(R.id.ll_dingdan)
    LinearLayout llDingdan;

    @BindView(R.id.iv_add_jiaren)
    ImageView iv_add_jiaren;

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.yiyang_frag_tab_wode;
    }

    @Override
    protected void initView(View rootView) {
        initData();
    }

    @OnClick({R.id.iv_add_jiaren, R.id.ll_dingdan, R.id.iv_set, R.id.iv_erweima, R.id.iv_head, R.id.ll_wd_fuwu, R.id.ll_wd_huodong, R.id.ll_wd_jiankang, R.id.ll_wd_jigou, R.id.ll_qianbao, R.id.ll_daifukuan, R.id.ll_daifahuo, R.id.ll_daishouhuo, R.id.ll_pingjia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_jiaren:
                AddJiarenActivity.actionStart(getContext());
                break;
            case R.id.iv_set:
                YySetAactivity.actionStart(getContext());
                break;
            case R.id.iv_erweima:
                break;
            case R.id.iv_head:
                break;
            case R.id.ll_wd_fuwu:
                break;
            case R.id.ll_wd_huodong:
                YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_hujiaozhongxin);
                break;
            case R.id.ll_wd_jiankang:
                YiyangTuTActivity.actionStart(getContext(), R.mipmap.act_jiankangshuju);
                break;
            case R.id.ll_wd_jigou:
                JigouyanglaoWdActivity.actionStart(getContext());
                break;
            case R.id.ll_qianbao:
                MyQianBaoActivity.actionStart(getActivity());
                break;
            case R.id.ll_daifukuan:
                MyOrderActivity.actionStart(getActivity(), "待付款");
                break;
            case R.id.ll_daifahuo:
                MyOrderActivity.actionStart(getActivity(), "待发货");
                break;
            case R.id.ll_daishouhuo:
                MyOrderActivity.actionStart(getActivity(), "待收货");
                break;
            case R.id.ll_pingjia:
                MyOrderActivity.actionStart(getActivity(), "待评价");
                break;
            case R.id.ll_dingdan:
                MyOrderActivity.actionStart(getActivity(), "");
                break;
        }
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
                        MineModel.DataBean dataBean = response.body().data.get(0);
                        Glide.with(getActivity()).load(response.body().data.get(0).getUser_img_url()).apply(RequestOptions.circleCropTransform()).into(ivHead);
                        tvPhone.setText(dataBean.getUser_name() + "  " + dataBean.getUser_phone());

                        PreferenceHelper.getInstance(getActivity()).putString(App.CUNCHUBIND_ALIPAY, response.body().data.get(0).getAlipay_number_check());
                        PreferenceHelper.getInstance(getActivity()).putString(App.CUNCHU_ZHIFUMIMA, response.body().data.get(0).getPay_pwd_check());//1 已经设置 2 未设置
                        PreferenceHelper.getInstance(getActivity()).putString(App.CUNCHUBIND_WEIXINPAY, response.body().data.get(0).getWx_pay_number_check());//1 已经设置 2 未设置
                        PreferenceHelper.getInstance(getActivity()).putString(App.CUN_GEREN_TOUXIANG, response.body().data.get(0).getUser_img_url());
                    }

                    @Override
                    public void onError(Response<AppResponse<MineModel.DataBean>> response) {
                        super.onError(response);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getNet();
    }
}
