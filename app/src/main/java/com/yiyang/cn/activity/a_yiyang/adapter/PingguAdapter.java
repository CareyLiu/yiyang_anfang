package com.yiyang.cn.activity.a_yiyang.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.model.JiashuModel;

import java.util.List;

import androidx.annotation.Nullable;


public class PingguAdapter extends BaseQuickAdapter<JiashuModel, BaseViewHolder> {

    public PingguAdapter(int layoutResId, @Nullable List<JiashuModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JiashuModel item) {
        helper.setText(R.id.tv_name, "评估人：" + item.getName());
        helper.setText(R.id.tv_sex, "性别：" + item.getSex());
        helper.setText(R.id.tv_birthday, "生日：" + item.getBirthday());

        ImageView iv_head = helper.getView(R.id.iv_head);
        Glide.with(mContext)
                .load(item.getHeadImgPath())
                .apply(RequestOptions.circleCropTransform())
                .into(iv_head);

        boolean pinggu = item.isPinggu();
        if (pinggu) {
            helper.setText(R.id.tv_pinggu, "评估等级：3级");
        } else {
            helper.setText(R.id.tv_pinggu, "评估等级：暂未评估");
        }
    }
}