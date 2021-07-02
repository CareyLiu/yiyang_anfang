package com.smarthome.magic.fragment.znjj.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.common.UIHelper;
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
        helper.setIsRecyclable(false);
        Glide.with(mContext).applyDefaultRequestOptions(GlideShowImageUtils.showZhengFangXing()).load(item.getDevice_type_pic()).into((ImageView) helper.getView(R.id.iv_device));
        helper.setText(R.id.tv_device_name, item.getDevice_name());
        helper.setText(R.id.tv_room_name, item.getRoom_name());

        String ty_device_ccid = item.getTy_device_ccid();
        String online_state = item.getOnline_state();

        if (!TextUtils.isEmpty(ty_device_ccid)) {//设备在线
            helper.setBackgroundRes(R.id.iv_state, R.drawable.bg_zhineng_device_online);
            helper.setText(R.id.tv_state, "设备在线");
        } else {
            if (TextUtils.isEmpty(online_state)) {//设备离线
                helper.setBackgroundRes(R.id.iv_state, R.drawable.bg_zhineng_device_offline);
                helper.setText(R.id.tv_state, "设备离线");
            } else if (online_state.equals("1")) {//设备在线
                helper.setBackgroundRes(R.id.iv_state, R.drawable.bg_zhineng_device_online);
                helper.setText(R.id.tv_state, "设备在线");
            } else {//设备离线
                helper.setBackgroundRes(R.id.iv_state, R.drawable.bg_zhineng_device_offline);
                helper.setText(R.id.tv_state, "设备离线");
            }
        }

        ImageView ivImage = helper.getView(R.id.iv_switch);
        if (item.getDevice_type().equals("01") || item.getDevice_type().equals("02")) {

        }
        if (item.getWork_state().equals("1")) {
            helper.setBackgroundRes(R.id.iv_switch, R.mipmap.img_device_switch_open);
            ivImage.setVisibility(View.VISIBLE);
        } else if (item.getWork_state().equals("2")) {
            helper.setBackgroundRes(R.id.iv_switch, R.mipmap.img_device_switch_close);
            ivImage.setVisibility(View.VISIBLE);
        } else if (item.getWork_state().equals("3")) {
            ivImage.setVisibility(View.INVISIBLE);
        } else {
            ivImage.setVisibility(View.INVISIBLE);
        }
        helper.addOnClickListener(R.id.ll_content);
        helper.addOnClickListener(R.id.iv_switch);
    }
}
