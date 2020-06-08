package com.smarthome.magic.adapter;

import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.model.TuanGouShangJiaDetailsModel;

import java.util.List;

public class ShangJiaNearbyAdapter extends BaseQuickAdapter<TuanGouShangJiaDetailsModel.DataBean.NeighborListBean, BaseViewHolder> {
    public ShangJiaNearbyAdapter(int layoutResId, @Nullable List<TuanGouShangJiaDetailsModel.DataBean.NeighborListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuanGouShangJiaDetailsModel.DataBean.NeighborListBean item) {
        Glide.with(mContext).load(item.getImg_url()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getInst_name());
        helper.setText(R.id.tv_shuoming, item.getShop_detail());
        helper.setText(R.id.tv_money, item.getShop_money_now());
        helper.setText(R.id.tv_distance, item.getArea_name()+" "+item.getDistance()+"km");
        helper.setText(R.id.tv_yishou, "已售： " + item.getPay_count());
        helper.addOnClickListener(R.id.constrain);
    }
}
