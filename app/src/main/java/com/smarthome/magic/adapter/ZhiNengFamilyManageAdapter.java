package com.smarthome.magic.adapter;

import androidx.annotation.Nullable;

import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.model.ZhiNengFamilyManageBean;
import com.smarthome.magic.model.ZhiNengHomeBean;
import com.smarthome.magic.model.ZhiNengHomeListBean;

import java.util.List;

public class ZhiNengFamilyManageAdapter extends BaseQuickAdapter<ZhiNengHomeListBean.DataBean, BaseViewHolder> {
    public ZhiNengFamilyManageAdapter(int layoutResId, @Nullable List<ZhiNengHomeListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengHomeListBean.DataBean item) {
        if (item.getMember_type().equals("1")) {
            helper.setText(R.id.tv_family_name, item.getFamily_name());
        } else {
            helper.setText(R.id.tv_family_name, item.getFamily_name() + "(共享家庭)");
        }
        helper.setText(R.id.tv_room_num, item.getRoom_num() + "个房间");
        helper.setText(R.id.tv_device_num, item.getDevice_num() + "个设备");
        helper.addOnClickListener(R.id.ll_content);
    }
}
