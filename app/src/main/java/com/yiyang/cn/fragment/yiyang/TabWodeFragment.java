package com.yiyang.cn.fragment.yiyang;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.YySetAactivity;
import com.yiyang.cn.basicmvp.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

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

    }

    @OnClick({R.id.iv_set, R.id.iv_erweima, R.id.iv_head, R.id.ll_wd_fuwu, R.id.ll_wd_huodong, R.id.ll_wd_jiankang, R.id.ll_wd_jigou, R.id.ll_qianbao, R.id.ll_daifukuan, R.id.ll_daifahuo, R.id.ll_daishouhuo, R.id.ll_pingjia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                break;
            case R.id.ll_wd_jiankang:
                break;
            case R.id.ll_wd_jigou:
                break;
            case R.id.ll_qianbao:
                break;
            case R.id.ll_daifukuan:
                break;
            case R.id.ll_daifahuo:
                break;
            case R.id.ll_daishouhuo:
                break;
            case R.id.ll_pingjia:
                break;
        }
    }
}
