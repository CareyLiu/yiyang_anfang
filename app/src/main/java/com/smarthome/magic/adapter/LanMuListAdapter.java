package com.smarthome.magic.adapter;

import androidx.annotation.Nullable;

import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;

import java.util.List;

public class LanMuListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public LanMuListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_text, item);
    }
}
