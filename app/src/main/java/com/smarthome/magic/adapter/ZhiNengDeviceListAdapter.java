package com.smarthome.magic.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.model.ZhiNengHomeBean;

import java.util.ArrayList;
import java.util.List;

public class ZhiNengDeviceListAdapter extends BaseQuickAdapter<ZhiNengHomeBean.DataBean.DeviceBean, BaseViewHolder> {

    public ZhiNengDeviceListAdapter(int layoutResId, @Nullable List<ZhiNengHomeBean.DataBean.DeviceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengHomeBean.DataBean.DeviceBean item) {
        Glide.with(mContext).load(item.getDevice_type_pic()).into((ImageView) helper.getView(R.id.img_device));
        helper.setText(R.id.tv_room_name, item.getDevice_name());
        if (item.getOnline_state().equals("1")) {
            //设备在线
            helper.setBackgroundRes(R.id.img_state, R.drawable.bg_zhineng_device_online);
            helper.setText(R.id.tv_state, "在线");
        } else {
            //设备离线
            helper.setBackgroundRes(R.id.img_state, R.drawable.bg_zhineng_device_offline);
            helper.setText(R.id.tv_state, "离线");
        }
    }
}
