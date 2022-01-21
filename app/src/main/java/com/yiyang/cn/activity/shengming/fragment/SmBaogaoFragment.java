package com.yiyang.cn.activity.shengming.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shengming.shengmingmodel.MacSleepReport;
import com.yiyang.cn.activity.shengming.utils.UrlUtils;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.PreferenceHelper;
import com.yiyang.cn.util.Y;

import java.util.List;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;

public class SmBaogaoFragment extends BaseFragment {


    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.bt_more)
    TextView bt_more;
    @BindView(R.id.tv_xinlv_avg)
    TextView tv_xinlv_avg;
    @BindView(R.id.tv_huxi_avg)
    TextView tv_huxi_avg;
    @BindView(R.id.tv_xinlv_max)
    TextView tv_xinlv_max;
    @BindView(R.id.tv_huxi_max)
    TextView tv_huxi_max;
    @BindView(R.id.tv_xinlv_min)
    TextView tv_xinlv_min;
    @BindView(R.id.tv_huxi_min)
    TextView tv_huxi_min;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private String sessionId;

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.shengming_frag_baogao;
    }

    @Override
    protected void initView(View rootView) {
        sessionId = PreferenceHelper.getInstance(getContext()).getString("sm_sessionId", "");
        showProgressDialog();
        getNet();
        initSM();
    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNet();
            }
        });
    }

    private void getNet() {
        String timestamp = System.currentTimeMillis() + "";
        String ltoken = UrlUtils.getLtoken(sessionId, timestamp);
        String date = "20220113";
        OkGo.<MacSleepReport>get(UrlUtils.getMacSleepReport)
                .params("sessionId", sessionId)
                .params("timestamp", timestamp)
                .params("ltoken", ltoken)
                .params("mac", UrlUtils.MAC)
                .params("date", date)
                .tag(this)
                .execute(new JsonCallback<MacSleepReport>() {
                    @Override
                    public void onSuccess(Response<MacSleepReport> response) {
                        MacSleepReport.DataBean data = response.body().getData();
                        MacSleepReport.DataBean.SleepDataBeanX sleepMain = data.getSleepData();
                        List<MacSleepReport.DataBean.SleepDataBeanX.SleepDataBean> sleepList = sleepMain.getSleepData();
                        int qingxing = 0;
                        int qianshui = 0;
                        int shenshui = 0;
                        int kuaisuzhayan = 0;
                        for (int i = 0; i < sleepList.size(); i++) {
                            MacSleepReport.DataBean.SleepDataBeanX.SleepDataBean sleepDataBean = sleepList.get(i);
                            int value = sleepDataBean.getValue();
                            String s = sleepDataBean.getS();
                            String e = sleepDataBean.getE();
                            if (value == 0) {
                                qingxing++;
                                Y.e("我处于清醒状态  " + s + "  到   " + e);
                            } else if (value == 1 || value == 2) {
                                qianshui++;
                                Y.e("我处于浅睡状态  " + s + "  到   " + e);
                            } else if (value == 3 || value == 4) {
                                shenshui++;
                                Y.e("我处于深睡状态  " + s + "  到   " + e);
                            } else if (value == 5) {
                                kuaisuzhayan++;
                                Y.e("快速眼动期  " + s + "  到   " + e);
                            }
                        }
                        Y.e(sleepMain.toString());

                        MacSleepReport.DataBean.HrDataBeanX hrDataMain = data.getHrData();
                        tv_xinlv_avg.setText(hrDataMain.getAvg() + "次/分");
                        tv_xinlv_max.setText(hrDataMain.getMax() + "次/分");
                        tv_xinlv_min.setText(hrDataMain.getMin() + "次/分");

                        MacSleepReport.DataBean.RrDataBeanX rrDataMain = data.getRrData();
                        tv_huxi_avg.setText(rrDataMain.getAvg() + "次/分");
                        tv_huxi_max.setText(rrDataMain.getMax() + "次/分");
                        tv_huxi_min.setText(rrDataMain.getMin() + "次/分");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        dismissProgressDialog();
                    }
                });

    }

    @OnClick({R.id.iv_back, R.id.bt_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.bt_more:
                break;
        }
    }
}
