package com.yiyang.cn.activity.a_yiyang.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.activity.a_yiyang.model.XiaoxiModel;

import java.util.List;

import androidx.annotation.Nullable;

public class ZaixianyishengAdapter extends BaseQuickAdapter<XiaoxiModel, BaseViewHolder> {
    public ZaixianyishengAdapter(int layoutResId, @Nullable List<XiaoxiModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XiaoxiModel item) {

    }
}
