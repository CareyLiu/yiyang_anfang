package com.smarthome.magic.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.ZhiNengFamilyMemberDetailActivity;
import com.smarthome.magic.adapter.ZhiNengTuBiaoAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.app.RxBus;
import com.smarthome.magic.app.RxUtils;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_Success;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengChangJingIconModel;
import com.smarthome.magic.model.ZhiNengFamilyEditBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;
import static com.smarthome.magic.get_net.Urls.ZHINENGJIJU_ZNJJ;

public class ChangJingTuBiaoActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    private ZhiNengTuBiaoAdapter zhiNengTuBiaoAdapter;
    private List<ZhiNengChangJingIconModel.DataBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNet();

        zhiNengTuBiaoAdapter = new ZhiNengTuBiaoAdapter(R.layout.item_zhinengtubiao_icon, mDatas);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 5);
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(zhiNengTuBiaoAdapter);
        zhiNengTuBiaoAdapter.setNewData(mDatas);
        zhiNengTuBiaoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Notice notice = new Notice();
                notice.type = ConstanceValue.MSG_ZHINENGJIAJU_ICON;
                notice.content = mDatas.get(position).getImg_url();
                sendRx(notice);
                finish();
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_changjing_icon;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("场景图标");
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
        Intent intent = new Intent(context, ChangJingTuBiaoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16065");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengChangJingIconModel.DataBean>>post(ZHINENGJIJU_ZNJJ)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengChangJingIconModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengChangJingIconModel.DataBean>> response) {
                        mDatas.clear();
                        mDatas.addAll(response.body().data);
                        zhiNengTuBiaoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengChangJingIconModel.DataBean>> response) {
                        super.onError(response);
                    }
                });
    }
}
