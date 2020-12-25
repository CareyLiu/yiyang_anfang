package com.smarthome.magic.activity.tuya_device.device.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.tuya_device.device.model.DpModel;

import java.util.List;

import androidx.annotation.Nullable;

public class MenciAdapter extends BaseQuickAdapter<DpModel.DpsBean, BaseViewHolder> {
    public MenciAdapter(int layoutResId, @Nullable List<DpModel.DpsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DpModel.DpsBean item) {
        String timeStr = item.getTimeStr();
        helper.setText(R.id.tv_time, timeStr);
        String value = item.getValue();
        if (value.equals("true")) {
            helper.setText(R.id.tv_caozuo, "门窗打开");
        } else {
            helper.setText(R.id.tv_caozuo, "门窗关闭");
        }
    }
}
