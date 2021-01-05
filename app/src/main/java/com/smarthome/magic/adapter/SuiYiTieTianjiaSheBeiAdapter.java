package com.smarthome.magic.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.R;
import com.smarthome.magic.model.SuiYiTieSheBeiLieBiaoModel;

import java.util.List;

public class SuiYiTieTianjiaSheBeiAdapter extends BaseQuickAdapter<SuiYiTieSheBeiLieBiaoModel.DataBean, BaseViewHolder> {

    public SuiYiTieTianjiaSheBeiAdapter(int layoutResId, @Nullable List<SuiYiTieSheBeiLieBiaoModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SuiYiTieSheBeiLieBiaoModel.DataBean item) {
        helper.setText(R.id.tv_name, item.getDevice_name());

        if (item.flag.equals("0")) {
            Glide.with(mContext).load(R.mipmap.tuya_faxian_icon_selector_sel).into((ImageView) helper.getView(R.id.iv_choose));
        } else if (item.flag.equals("1")) {
            Glide.with(mContext).load(R.mipmap.tuya_faxian_icon_selector_sel_1).into((ImageView) helper.getView(R.id.iv_choose));
        }
        helper.addOnClickListener(R.id.ll_main);
    }

}
