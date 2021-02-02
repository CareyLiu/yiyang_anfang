package com.smarthome.magic.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
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
import com.suke.widget.SwitchButton;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.HomeBean;
import com.tuya.smart.home.sdk.bean.WeatherBean;
import com.tuya.smart.home.sdk.callback.ITuyaHomeResultCallback;
import com.umeng.commonsdk.UMConfigure;

import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengJiaJuChangJingFragment extends BaseFragment {


    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    List<ChangJingModel.DataBean.SceneBean> mDatas = new ArrayList<>();

    ZhiNengChangJingAdapter zhiNengChangJingAdapter;
    private String guanLiYuan = "";//0不是 1 是

    @Override
    protected void initLogic() {
        guanLiYuan = PreferenceHelper.getInstance(getActivity()).getString(AppConfig.ZHINENGJIAJUGUANLIYUAN, "0");

        initListView();
        getnet();

        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_ZHINENGJIAJU_SHOUYE_SHUAXIN) {
                    getnet();
                }
            }
        }));

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
                        if (response.body().data.get(0).getScene() != null) {
                            mDatas.clear();
                            mDatas.addAll(response.body().data.get(0).getScene());

                            zhiNengChangJingAdapter.notifyDataSetChanged();
                            PreferenceHelper.getInstance(getActivity()).putString(AppConfig.FAMILY_ID, response.body().data.get(0).getFamily_id());
                        }

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
                if (guanLiYuan.equals("1")) {
                    ChuangJianZhiNengActivity.actionStart(getActivity());
                } else {
                    UIHelper.ToastMessage(getActivity(), "您不是管理员无此权限");
                }
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
                    case R.id.view:
                        SwitchButton switchButton = (SwitchButton) zhiNengChangJingAdapter.getViewByPosition(rlvList, position, R.id.csw_switch_button);
                        if (switchButton.isChecked()) {
                            kaiOrGuanBiChangJing(mDatas.get(position).getScene_id(), mDatas.get(position).getFamily_id(), "3");
                            switchButton.setChecked(false);
                        } else {
                            kaiOrGuanBiChangJing(mDatas.get(position).getScene_id(), mDatas.get(position).getFamily_id(), "2");
                            switchButton.setChecked(true);
                        }
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
                        UIHelper.ToastMessage(getActivity(), str);
                    }
                });
    }

    private void kaiOrGuanBiChangJing(String scene_id, String familyId, String scene_state) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16060");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(getActivity()).getAppToken());
        map.put("scene_id", scene_id);
        map.put("family_id", familyId);
        map.put("scene_state", scene_state);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ChangJingXiangQingModel.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ChangJingXiangQingModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        // UIHelper.ToastMessage(getActivity(), "场景切换成功");
                        getnet();
                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        UIHelper.ToastMessage(getActivity(), str);
                    }
                });
    }
}
