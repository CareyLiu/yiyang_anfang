package com.smarthome.magic.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.model.MessageModel;
import com.smarthome.magic.model.ZhiNengHomeBean;

import java.util.List;

public class ZhiNengRoomListAdapter extends BaseQuickAdapter<ZhiNengHomeBean.DataBean.RoomBean, BaseViewHolder> {
    public ZhiNengRoomListAdapter(int layoutResId, @Nullable List<ZhiNengHomeBean.DataBean.RoomBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengHomeBean.DataBean.RoomBean item) {
        helper.setText(R.id.tv_room_name, item.getRoom_name());
        helper.setText(R.id.tv_device_num, item.getDevice_number() + "个设备");
    }
}
