package com.smarthome.magic.activity.xiupeichang.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.xiupeichang.model.XpcPingjiaModel;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.util.GlideShowImageUtils;

import java.util.List;

import androidx.annotation.Nullable;

public class XiupeichangPingjiaAdapter extends BaseQuickAdapter<XpcPingjiaModel.DataBean, BaseViewHolder> {
    public XiupeichangPingjiaAdapter(int layoutResId, @Nullable List<XpcPingjiaModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, XpcPingjiaModel.DataBean item) {
        ImageView iv_head = (ImageView) helper.getView(R.id.iv_head);
        Glide.with(mContext).load(item.getUser_img_url()).apply(GlideShowImageUtils.showFace()).into(iv_head);

        helper.setText(R.id.tv_name, item.getUser_name());
        helper.setText(R.id.tv_data, item.getUser_to_time() + "评价");
        helper.setText(R.id.tv_pinglun, item.getUser_to_text());
        helper.setText(R.id.tv_shangpin_details, "综合评分：" + item.getUser_to_score());
    }
}
