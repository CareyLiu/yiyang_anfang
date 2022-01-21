package com.yiyang.cn.activity.nongye.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.model.DianzishangwuModel;

import java.util.List;

import androidx.annotation.Nullable;

public class DianzishangwuAdapter extends BaseQuickAdapter<DianzishangwuModel, BaseViewHolder> {

    public DianzishangwuAdapter(int layoutResId, @Nullable List<DianzishangwuModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DianzishangwuModel item) {
        ImageView iv_main=helper.getView(R.id.iv_main);
        iv_main.setImageResource(item.getSrcId());
        helper.setText(R.id.tv_address_name,item.getAddress());
        helper.setText(R.id.tv_name,item.getName());

    }
}