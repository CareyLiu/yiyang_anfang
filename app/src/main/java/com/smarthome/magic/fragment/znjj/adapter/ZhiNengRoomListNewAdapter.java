package com.smarthome.magic.fragment.znjj.adapter;

import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.fragment.znjj.model.ZhiNengModel;
import com.smarthome.magic.model.ZhiNengHomeBean;

import java.util.List;

import androidx.annotation.Nullable;

public class ZhiNengRoomListNewAdapter extends BaseQuickAdapter<ZhiNengModel.DataBean.RoomBean, BaseViewHolder> {
    public ZhiNengRoomListNewAdapter(int layoutResId, @Nullable List<ZhiNengModel.DataBean.RoomBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengModel.DataBean.RoomBean item) {
        helper.setText(R.id.tv_room_name, item.getRoom_name());
        helper.setText(R.id.tv_device_num, item.getDevice_number() + "个设备");
        helper.addOnClickListener(R.id.ll_content);
    }
}
