package com.smarthome.magic.activity.fenxiang_tuisong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.TuanYouTuiGuangAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.TuiGuangTongJiModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class TuanYouTuiGuangActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    private TuanYouTuiGuangAdapter tuanYouTuiGuangAdapter;

    private List<TuiGuangTongJiModel.DataBean.PromotersListBean> mDatas;

    private TextView tvJinYiGeYue;
    private TextView tvJinQiTian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlvList.setLayoutManager(new LinearLayoutManager(mContext));
        mDatas = new ArrayList<>();
        tuanYouTuiGuangAdapter = new TuanYouTuiGuangAdapter(R.layout.item_tuanyou_tuiguang, mDatas);

        View view = View.inflate(mContext, R.layout.tuanyou_tuiguang_header, null);
        tvJinYiGeYue = view.findViewById(R.id.tv_jinyigeyue);
        tvJinQiTian = view.findViewById(R.id.tv_jinqitian);

        tuanYouTuiGuangAdapter.setHeaderView(view);
        rlvList.setAdapter(tuanYouTuiGuangAdapter);
        tuanYouTuiGuangAdapter.setNewData(mDatas);
        getNet();

    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tuanyou_tuiguang;
    }

    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04342");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<TuiGuangTongJiModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user ")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuiGuangTongJiModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TuiGuangTongJiModel.DataBean>> response) {
                        mDatas.addAll(response.body().data.get(0).getPromoters_list());
                        tuanYouTuiGuangAdapter.notifyDataSetChanged();
                        tvJinYiGeYue.setText(response.body().data.get(0).getAgent_num_month());
                        tvJinQiTian.setText(response.body().data.get(0).getAgent_num_week());
                    }

                    @Override
                    public void onError(Response<AppResponse<TuiGuangTongJiModel.DataBean>> response) {
                    }

                });
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("我的推广");
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
        Intent intent = new Intent(context, TuanYouTuiGuangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
