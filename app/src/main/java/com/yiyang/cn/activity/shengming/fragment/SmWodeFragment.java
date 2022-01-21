package com.yiyang.cn.activity.shengming.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shengming.SmSetActivity;
import com.yiyang.cn.basicmvp.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SmWodeFragment extends BaseFragment {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.iv_set)
    ImageView iv_set;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_name)
    TextView tv_name;

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
        return R.layout.shengming_frag_wode;
    }

    @Override
    protected void initView(View rootView) {

    }

    @OnClick({R.id.iv_back, R.id.iv_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.iv_set:
                clickSet();
                break;
        }
    }

    private void clickSet() {
        SmSetActivity.actionStart(getContext());
    }
}
