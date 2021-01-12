package com.smarthome.magic.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class ZhiNengTuBiaoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ZhiNengTuBiaoAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }

}
