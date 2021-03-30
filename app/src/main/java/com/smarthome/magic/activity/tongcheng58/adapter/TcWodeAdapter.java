package com.smarthome.magic.activity.tongcheng58.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.activity.tongcheng58.model.TcWodeModel;

import java.util.List;

import androidx.annotation.Nullable;

public class TcWodeAdapter extends BaseQuickAdapter<TcWodeModel.DataBean.InforListBean, BaseViewHolder> {
    public TcWodeAdapter(int layoutResId, @Nullable List<TcWodeModel.DataBean.InforListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TcWodeModel.DataBean.InforListBean item) {

    }
}
