package com.smarthome.magic.activity.tuangou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.tuangou.TuanGouYouHuiJuanAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.TuanGouYouHuiJuanModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KaQuanActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        getNet();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_tuan_gou_di_yong_quan;
    }

    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(KaQuanActivity.this).getAppToken());
        map.put("code", "08024");
        Gson gson = new Gson();
        OkGo.<AppResponse<TuanGouYouHuiJuanModel.DataBean>>post(Urls.HOME_PICTURE_HOME)
                .tag(KaQuanActivity.this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuanGouYouHuiJuanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<TuanGouYouHuiJuanModel.DataBean>> response) {
                        dataBeans.addAll(response.body().data);
                        setRlvList();
                    }

                    @Override
                    public void onError(Response<AppResponse<TuanGouYouHuiJuanModel.DataBean>> response) {
                        super.onError(response);
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
        tv_title.setText("抵用券");
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
        Intent intent = new Intent(context, KaQuanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    TuanGouYouHuiJuanAdapter tuanGouYouHuiJuanAdapter;
    List<TuanGouYouHuiJuanModel.DataBean> dataBeans = new ArrayList<>();

    public void setRlvList() {
        rlvList.setLayoutManager(new LinearLayoutManager(KaQuanActivity.this));
        tuanGouYouHuiJuanAdapter = new TuanGouYouHuiJuanAdapter(R.layout.item_diyongquan, dataBeans);
        rlvList.setAdapter(tuanGouYouHuiJuanAdapter);
        tuanGouYouHuiJuanAdapter.setNewData(dataBeans);
        tuanGouYouHuiJuanAdapter.notifyDataSetChanged();
        tuanGouYouHuiJuanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
//                        Notice n1 = new Notice();
//                        n1.type = ConstanceValue.MSG_DIYONGQUAN;
//                        n1.content = tuanGouYouHuiJuanAdapter.getData().get(position).getAgio_moneys();
//                        RxBus.getDefault().sendRx(n1);
//                        finish();

                        break;
                }
            }
        });
    }
}
