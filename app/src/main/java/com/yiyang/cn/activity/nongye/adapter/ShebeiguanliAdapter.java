package com.yiyang.cn.activity.nongye.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.model.ShebeiguanliModel;

import java.util.List;

import androidx.annotation.Nullable;

public class ShebeiguanliAdapter extends BaseQuickAdapter<ShebeiguanliModel, BaseViewHolder> {

    public ShebeiguanliAdapter(int layoutResId, @Nullable List<ShebeiguanliModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShebeiguanliModel item) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        ImageView iv_switch = helper.getView(R.id.iv_switch);
        ImageView iv_set = helper.getView(R.id.iv_set);

        helper.setText(R.id.tv_name, item.getName());
        iv_icon.setImageResource(item.getSrcId());

        if (item.isKai()) {
            iv_switch.setImageResource(R.mipmap.nongye_kaiguan_sel);
            iv_set.setImageResource(R.mipmap.nongye_shezhi_sel);
            helper.setText(R.id.tv_time, item.getTime() + "开");
        } else {
            iv_switch.setImageResource(R.mipmap.nongye_kaiguan_nor);
            iv_set.setImageResource(R.mipmap.nongye_shezhi_nor);
            helper.setText(R.id.tv_time, item.getTime() + "关");
        }

        helper.addOnClickListener(R.id.iv_switch);
        helper.addOnClickListener(R.id.iv_set);
    }
}
