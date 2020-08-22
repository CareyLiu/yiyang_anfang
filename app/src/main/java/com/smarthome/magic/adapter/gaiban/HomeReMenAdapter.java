package com.smarthome.magic.adapter.gaiban;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.model.Home;
import com.smarthome.magic.util.DensityUtils;
import com.smarthome.magic.view.CornerTransform;

import java.util.List;

public class HomeReMenAdapter extends BaseQuickAdapter<Home.DataBean.IndexShowListBean, com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder> {
    public HomeReMenAdapter(int layoutResId, @Nullable List<Home.DataBean.IndexShowListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder helper, Home.DataBean.IndexShowListBean item) {

        CornerTransform transformation = new CornerTransform(mContext, 15);
//只是绘制左上角和右上角圆角
        transformation.setExceptCorner(false, false, true, true);

        Glide.with(mContext)
                .asBitmap()
                .skipMemoryCache(true)
                .load(item.getIndex_photo_url())
                .transform(transformation)
                .into((ImageView) helper.getView(R.id.iv_product));
        helper.setText(R.id.tv_title, item.getWares_name());
        helper.setText(R.id.tv_hongbao, "直降" + item.getMoney_lower() + "元");
        helper.setText(R.id.tv_jiage, "¥" + item.getMoney_now());
    }
}
