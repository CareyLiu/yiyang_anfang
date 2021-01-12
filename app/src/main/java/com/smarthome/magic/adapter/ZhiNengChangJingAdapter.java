package com.smarthome.magic.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.R;

import java.util.List;

public class ZhiNengChangJingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ZhiNengChangJingAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.rrl_main);
    }
}
