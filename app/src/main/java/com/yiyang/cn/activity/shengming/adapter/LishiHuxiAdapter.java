package com.yiyang.cn.activity.shengming.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.shengming.shengmingmodel.HistoryHrRrData;
import com.yiyang.cn.util.Y;

import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

public class LishiHuxiAdapter extends BaseQuickAdapter<HistoryHrRrData.DataBean.RrDataBean, BaseViewHolder> {
    public LishiHuxiAdapter(int layoutResId, @Nullable List<HistoryHrRrData.DataBean.RrDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryHrRrData.DataBean.RrDataBean item) {
        helper.setText(R.id.tv_cishu, item.getValue() + "次/分");
        Date time = Y.parseServerTime(item.getTime(), "yyyyMMddHHmmss");
        helper.setText(R.id.tv_time,Y.getTime(time));

        ImageView iv_icon = helper.getView(R.id.iv_icon);
        iv_icon.setImageResource(R.mipmap.shengming_fei_icon);
    }
}
