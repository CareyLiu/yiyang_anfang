package com.smarthome.magic.activity.tuya_device.wangguan.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.tuya_device.utils.TuyaConfig;
import com.smarthome.magic.activity.tuya_device.wangguan.model.ZishebeiModel;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.List;

import androidx.annotation.Nullable;

public class WangguanAdapter extends BaseQuickAdapter<ZishebeiModel.DataBean, BaseViewHolder> {
    public WangguanAdapter(int layoutResId, @Nullable List<ZishebeiModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZishebeiModel.DataBean deviceBean) {
        Glide.with(mContext).load(deviceBean.getDevice_type_pic()).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_device_name, deviceBean.getDevice_name());
        helper.setText(R.id.tv_room_name, deviceBean.getRoom_name());
        ImageView iv_switch = helper.getView(R.id.iv_switch);
        iv_switch.setImageResource(R.mipmap.img_device_switch_open);

        String category = deviceBean.getDevice_type();
        if (category.equals(TuyaConfig.CATEGORY_SWITCH)) {
            iv_switch.setVisibility(View.VISIBLE);
        } else {
            iv_switch.setVisibility(View.GONE);
        }
    }
}
