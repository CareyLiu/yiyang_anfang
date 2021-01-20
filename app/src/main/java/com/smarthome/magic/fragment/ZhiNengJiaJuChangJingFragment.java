package com.smarthome.magic.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.smarthome.magic.activity.zhinengjiaju.ChuangJianZhiNengActivity;
import com.smarthome.magic.activity.zhinengjiaju.ZhiNengChangJingDetailsActivity;
import com.smarthome.magic.activity.zhinengjiaju.peinet.PeiWangYinDaoPageActivity;
import com.smarthome.magic.adapter.ZhiNengChangJingAdapter;
import com.smarthome.magic.app.AppConfig;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ChangJingModel;
import com.smarthome.magic.model.ChangJingXiangQingModel;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengJiaJuChangJingFragment extends BaseFragment {


    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    List<ChangJingModel.DataBean.SceneBean> mDatas = new ArrayList<>();

    ZhiNengChangJingAdapter zhiNengChangJingAdapter;

    @Override
    protected void initLogic() {


        initListView();
        getnet();

    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16001");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingModel.DataBean>> response) {
                        mDatas.clear();
                        mDatas.addAll(response.body().data.get(0).getScene());
                        zhiNengChangJingAdapter.notifyDataSetChanged();
                        PreferenceHelper.getInstance(getActivity()).putString(AppConfig.FAMILY_ID, response.body().data.get(0).getFamily_id());
                    }
                });
    }

    private void initListView() {


        zhiNengChangJingAdapter = new ZhiNengChangJingAdapter(R.layout.item_zhinengjiaju_changjing, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(zhiNengChangJingAdapter);
        View view = View.inflate(getActivity(), R.layout.layout_zhinengchangjing_bottom, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuangJianZhiNengActivity.actionStart(getActivity());
            }
        });
        zhiNengChangJingAdapter.addFooterView(view);
        zhiNengChangJingAdapter.setNewData(mDatas);

        zhiNengChangJingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rrl_main:
                        ZhiNengChangJingDetailsActivity.actionStart(getActivity(), mDatas.get(position).getScene_type(), mDatas.get(position).getScene_id(), mDatas.get(position).getFamily_id());

                        break;
                    case R.id.iv_zhixing:
                        //UIHelper.ToastMessage(getActivity(), "点击一键执行");
                        zhiXingMingLing(mDatas.get(position).getScene_id());
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_changjing;
    }

    @Override
    protected void initView(View rootView) {

    }

    public static ZhiNengJiaJuChangJingFragment newInstance(Bundle bundle) {
        ZhiNengJiaJuChangJingFragment fragment = new ZhiNengJiaJuChangJingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void zhiXingMingLing(String scene_id) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16057");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("scene_id", scene_id);

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        UIHelper.ToastMessage(getActivity(), "命令执行成功");

                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        Log.i("cuifahuo", str);
                        UIHelper.ToastMessage(getActivity(), str);
                    }
                });
    }
}
