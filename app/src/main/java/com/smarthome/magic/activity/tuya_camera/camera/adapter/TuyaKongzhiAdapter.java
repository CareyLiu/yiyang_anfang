package com.smarthome.magic.activity.tuya_camera.camera.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.tuya_camera.camera.model.TuyaKongzhiModel;

import java.util.List;

import androidx.annotation.Nullable;

public class TuyaKongzhiAdapter extends BaseQuickAdapter<TuyaKongzhiModel, BaseViewHolder> {
    public TuyaKongzhiAdapter(int layoutResId, @Nullable List<TuyaKongzhiModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuyaKongzhiModel item) {
        helper.setText(R.id.tv_kongzhi, item.getName());
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        if (item.isSelect()) {
            iv_icon.setImageResource(item.getIconIdS());
        } else {
            iv_icon.setImageResource(item.getIconId());
        }

    }
}
