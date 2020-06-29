package com.smarthome.magic.adapter;

import androidx.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.R;
import com.smarthome.magic.model.GoodsDetails_f;

import java.util.List;

public class TaoCanAdapter extends BaseQuickAdapter<GoodsDetails_f.DataBean.ProductListBean, BaseViewHolder> {


    public TaoCanAdapter(int layoutResId, @Nullable List<GoodsDetails_f.DataBean.ProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsDetails_f.DataBean.ProductListBean item) {
        helper.setText(R.id.tv_text, item.getProduct_title());
        helper.addOnClickListener(R.id.constrain);

        if (item.getSelect().equals("1")) {
            TextView tv = helper.getView(R.id.tv_text);
            tv.setBackgroundResource(R.drawable.background_select);
        } else {
            TextView tv = helper.getView(R.id.tv_text);
            tv.setBackgroundResource(R.drawable.background_noselect);
        }
    }
}
