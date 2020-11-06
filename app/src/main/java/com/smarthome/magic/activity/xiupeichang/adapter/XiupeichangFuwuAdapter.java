package com.smarthome.magic.activity.xiupeichang.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.xiupeichang.model.XpcFuwuModel;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.util.GlideShowImageUtils;

import java.util.List;

import androidx.annotation.Nullable;

public class XiupeichangFuwuAdapter extends BaseQuickAdapter<XpcFuwuModel.DataBean, BaseViewHolder> {
    public XiupeichangFuwuAdapter(int layoutResId, @Nullable List<XpcFuwuModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XpcFuwuModel.DataBean item) {
        ImageView iv_img = (ImageView) helper.getView(R.id.iv_img);
        Glide.with(mContext).load(item.getImg_url()).apply(GlideShowImageUtils.showZhengFangXing()).into(iv_img);
        helper.setText(R.id.tv_name, item.getShop_title());
        helper.setText(R.id.tv_content, item.getShop_detail());
        helper.setText(R.id.tv_money, "¥" + item.getShop_money_now());
        helper.setText(R.id.tv_money_yuan, "¥" + item.getShop_money_old());
    }
}
