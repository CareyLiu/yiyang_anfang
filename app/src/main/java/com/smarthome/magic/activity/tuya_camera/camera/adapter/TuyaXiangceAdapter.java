package com.smarthome.magic.activity.tuya_camera.camera.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

public class TuyaXiangceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public TuyaXiangceAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).into((ImageView) helper.getView(R.id.iv_pic));
    }
}
