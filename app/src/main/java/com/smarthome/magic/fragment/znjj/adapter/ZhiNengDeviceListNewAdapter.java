package com.smarthome.magic.fragment.znjj.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.fragment.znjj.model.ZhiNengModel;
import com.smarthome.magic.util.GlideShowImageUtils;

import java.util.List;

import androidx.annotation.Nullable;

public class ZhiNengDeviceListNewAdapter extends BaseQuickAdapter<ZhiNengModel.DataBean.DeviceBean, BaseViewHolder> {

    public ZhiNengDeviceListNewAdapter(int layoutResId, @Nullable List<ZhiNengModel.DataBean.DeviceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengModel.DataBean.DeviceBean item) {
        Glide.with(mContext).applyDefaultRequestOptions(GlideShowImageUtils.showZhengFangXing()).load(item.getDevice_type_pic()).into((ImageView) helper.getView(R.id.iv_device));

        helper.setText(R.id.tv_device_name, item.getDevice_name());
        helper.setText(R.id.tv_room_name, item.getRoom_name());
        if (!StringUtils.isEmpty(item.getTy_device_ccid())) {
            //设备在线
            helper.setBackgroundRes(R.id.iv_state, R.drawable.bg_zhineng_device_online);
            helper.setText(R.id.tv_state, "设备在线");
        } else {

            if (item.getOnline_state() == null) {
                //设备离线
                helper.setBackgroundRes(R.id.iv_state, R.drawable.bg_zhineng_device_offline);
                helper.setText(R.id.tv_state, "设备离线");
            } else if (item.getOnline_state().equals("1")) {
                //设备在线
                helper.setBackgroundRes(R.id.iv_state, R.drawable.bg_zhineng_device_online);
                helper.setText(R.id.tv_state, "设备在线");
            } else {
                //设备离线
                helper.setBackgroundRes(R.id.iv_state, R.drawable.bg_zhineng_device_offline);
                helper.setText(R.id.tv_state, "设备离线");
            }
        }


        if (item.getDevice_type().equals("11") || item.getDevice_type().equals("12") || item.getDevice_type().equals("13")
                || item.getDevice_type().equals("14") || item.getDevice_type().equals("15")) {
            helper.getView(R.id.iv_switch).setVisibility(View.INVISIBLE);
        } else {
            String ty_device_ccid = item.getTy_device_ccid();
            if (TextUtils.isEmpty(ty_device_ccid)) {
                helper.getView(R.id.iv_switch).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.iv_switch).setVisibility(View.INVISIBLE);
            }
        }

        ImageView ivImage = helper.getView(R.id.iv_switch);

        if (item.getWork_state().equals("1")) {
            helper.setBackgroundRes(R.id.iv_switch, R.mipmap.img_device_switch_open);
            ivImage.setVisibility(View.VISIBLE);
        } else if (item.getWork_state().equals("2")) {
            helper.setBackgroundRes(R.id.iv_switch, R.mipmap.img_device_switch_close);
            ivImage.setVisibility(View.VISIBLE);
        } else if (item.getWork_state().equals("3")) {
            ivImage.setVisibility(View.INVISIBLE);
        }
        helper.addOnClickListener(R.id.ll_content);
        helper.addOnClickListener(R.id.iv_switch);
    }
}
