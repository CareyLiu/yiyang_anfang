package com.smarthome.magic.activity.tuya_device.camera.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.tuya_device.camera.model.TuyaFilesModel;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

public class TuyaXiangceAdapter extends BaseQuickAdapter<TuyaFilesModel, BaseViewHolder> {

    private boolean isSeletMode;

    public TuyaXiangceAdapter(int layoutResId, @Nullable List<TuyaFilesModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuyaFilesModel item) {
        Glide.with(mContext).load(item.getFilePath()).into((ImageView) helper.getView(R.id.iv_pic));

        ImageView iv_select = helper.getView(R.id.iv_select);
        if (isSeletMode) {
            iv_select.setVisibility(View.VISIBLE);
        } else {
            iv_select.setVisibility(View.GONE);
        }

        if (item.isSelect()) {
            iv_select.setImageResource(R.mipmap.kaquan_select_s);
        } else {
            iv_select.setImageResource(R.mipmap.kaquan_select_n);
        }
    }

    public void setSeletMode(boolean seletMode) {
        isSeletMode = seletMode;
        notifyDataSetChanged();
    }
}
