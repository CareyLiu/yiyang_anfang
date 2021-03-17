package com.smarthome.magic.adapter;


import androidx.annotation.Nullable;

import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.model.FenLeiThirdModel;

import java.util.List;

public class GongJiangListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public GongJiangListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.rl_item);
    }
}
