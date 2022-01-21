package com.yiyang.cn.activity.nongye.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.model.MiaoqingModel;
import com.yiyang.cn.util.Y;

import java.util.List;

import androidx.annotation.Nullable;

public class MiaoqingAdapter extends BaseQuickAdapter<MiaoqingModel, BaseViewHolder> {
    public MiaoqingAdapter(int layoutResId, @Nullable List<MiaoqingModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MiaoqingModel item) {
        helper.setText(R.id.tv_name, item.getName() + "-" + item.getType());
        helper.setText(R.id.tv_id, "ID:" + item.getId());

        TextView tv_yichang = helper.getView(R.id.tv_yichang);
        if (item.isYichang()) {
            tv_yichang.setBackgroundColor(Y.getColor(R.color.red));
            tv_yichang.setText("异常");
        } else {
            tv_yichang.setBackgroundColor(Y.getColor(R.color.color_nongye_main));
            tv_yichang.setText("正常");
        }
    }
}
