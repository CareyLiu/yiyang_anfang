package com.smarthome.magic.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.model.TuiGuangTongJiModel;

import java.util.List;

public class TuanYouTuiGuangAdapter extends BaseQuickAdapter<TuiGuangTongJiModel.DataBean.PromotersListBean, BaseViewHolder> {
    public TuanYouTuiGuangAdapter(int layoutResId, @Nullable List<TuiGuangTongJiModel.DataBean.PromotersListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuiGuangTongJiModel.DataBean.PromotersListBean item) {

        Glide.with(mContext).load(item.getUser_img_url()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getUser_name());
        helper.setText(R.id.tv_phone, item.getUser_phone());
        helper.setText(R.id.tv_riqi, item.getCreate_time());

    }
}
