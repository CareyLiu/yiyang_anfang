package com.smarthome.magic.activity.tongcheng58;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tongcheng58.model.TcWodeModel;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TongchengTabWodeFragment extends BaseFragment {

    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.line_title1)
    View lineTitle1;
    @BindView(R.id.ll_title1)
    RelativeLayout llTitle1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.line_title2)
    View lineTitle2;
    @BindView(R.id.ll_title2)
    RelativeLayout llTitle2;
    @BindView(R.id.tv_title3)
    TextView tvTitle3;
    @BindView(R.id.line_title3)
    View lineTitle3;
    @BindView(R.id.ll_title3)
    RelativeLayout llTitle3;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.rv_name)
    TextView rvName;
    @BindView(R.id.iv_gongjing)
    ImageView ivGongjing;
    @BindView(R.id.iv_shangjia)
    ImageView ivShangjia;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    private String ir_type;
    private int page_number;
    private TcWodeModel.DataBean wodeBean;
    private List<TcWodeModel.DataBean.InforListBean> infor_list;

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.tongcheng_frag_main_wode;
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initStart();
        return rootView;
    }

    private void initStart() {
        ir_type = "2";
        initAdapter();
        initSM();
        getData();
    }

    private void initAdapter() {

    }

    private void initSM() {
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                lordData();
            }
        });
    }

    private void getData() {
        page_number = 0;
        Map<String, String> map = new HashMap<>();
        map.put("code", "17010");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("ir_type", ir_type);
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<TcWodeModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TcWodeModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcWodeModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000")) {
                            wodeBean = response.body().data.get(0);
                            infor_list = wodeBean.getInfor_list();
//                            itemAdapter.setNewData(shopListBeans);
//                            itemAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    private void lordData() {
        page_number++;
        Map<String, String> map = new HashMap<>();
        map.put("code", "17010");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getContext()).getAppToken());
        map.put("ir_type", ir_type);
        map.put("page_number", page_number + "");
        Gson gson = new Gson();
        OkGo.<AppResponse<TcWodeModel.DataBean>>post(Urls.TONG_CHENG)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TcWodeModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TcWodeModel.DataBean>> response) {
                        if (response.body().msg_code.equals("0000"))
                            wodeBean = response.body().data.get(0);
                        List<TcWodeModel.DataBean.InforListBean> inforList = wodeBean.getInfor_list();
                        infor_list.addAll(inforList);

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadMore();
                    }
                });
    }

    @OnClick({R.id.ll_title1, R.id.ll_title2, R.id.ll_title3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title1:
                select(0);
                break;
            case R.id.ll_title2:
                select(1);
                break;
            case R.id.ll_title3:
                select(2);
                break;
        }
    }

    private void select(int pos) {
        tvTitle1.setTextColor(Y.getColor(R.color.text_color3));
        tvTitle2.setTextColor(Y.getColor(R.color.text_color3));
        tvTitle3.setTextColor(Y.getColor(R.color.text_color3));
        lineTitle1.setVisibility(View.GONE);
        lineTitle2.setVisibility(View.GONE);
        lineTitle3.setVisibility(View.GONE);

        if (pos == 0) {
            tvTitle1.setTextColor(Y.getColor(R.color.text_red));
            lineTitle1.setVisibility(View.VISIBLE);
            ir_type = "2";
        } else if (pos == 1) {
            tvTitle2.setTextColor(Y.getColor(R.color.text_red));
            lineTitle2.setVisibility(View.VISIBLE);
            ir_type = "1";
        } else {
            tvTitle3.setTextColor(Y.getColor(R.color.text_red));
            lineTitle3.setVisibility(View.VISIBLE);
            ir_type = "3";
        }

        getData();
    }
}
