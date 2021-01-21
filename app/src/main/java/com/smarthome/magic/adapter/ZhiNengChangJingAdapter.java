package com.smarthome.magic.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ChangJingModel;
import com.smarthome.magic.model.ChangJingXiangQingModel;
import com.suke.widget.SwitchButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengChangJingAdapter extends BaseQuickAdapter<ChangJingModel.DataBean.SceneBean, BaseViewHolder> {
    public ZhiNengChangJingAdapter(int layoutResId, @Nullable List<ChangJingModel.DataBean.SceneBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChangJingModel.DataBean.SceneBean item) {
        helper.addOnClickListener(R.id.rrl_main);
        helper.addOnClickListener(R.id.iv_zhixing);
        Glide.with(mContext).load(item.getScene_pic()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getScene_title());
        TextView tvJianJie = helper.getView(R.id.tv_text);
        ImageView doImage = helper.getView(R.id.iv_zhixing);
        /**
         * 场景类型：1.一键执行  2.定时 3.动作触发
         */
        if (item.getScene_type().equals("1")) {
            tvJianJie.setText("一键执行");

        } else if (item.getScene_type().equals("2")) {

            tvJianJie.setText("定时");
        } else if (item.getScene_type().equals("3")) {

            tvJianJie.setText("动作触发");
        }
        SwitchButton switchButton = helper.getView(R.id.csw_switch_button);
        if (item.getScene_state().equals("2")) {
            switchButton.setChecked(true);
            doImage.setVisibility(View.VISIBLE);
        } else if (item.getScene_state().equals("3")) {
            switchButton.setChecked(false);
            doImage.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.view);


    }

    private void kaiOrGuanBiChangJing(String scene_id, String familyId, String scene_state) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16060");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(mContext).getAppToken());
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

                    }

                    @Override
                    public void onError(Response<AppResponse<ChangJingXiangQingModel.DataBean>> response) {
                        String str = response.getException().getMessage();
                        Log.i("cuifahuo", str);
                        UIHelper.ToastMessage(mContext, str);
                    }
                });
    }
}
