package com.smarthome.magic.adapter;


import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.R;
import com.smarthome.magic.model.ZhiNengJiaJu_0007Model;

import java.util.List;

public class OneImageAdapter extends BaseQuickAdapter<ZhiNengJiaJu_0007Model.DataBean, BaseViewHolder> {

    public OneImageAdapter(int layoutResId, @Nullable List<ZhiNengJiaJu_0007Model.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhiNengJiaJu_0007Model.DataBean item) {
        Glide.with(mContext).load(item.device_type_pic).into((ImageView) helper.getView(R.id.iv_image));
        helper.addOnClickListener(R.id.iv_image);
    }
}
