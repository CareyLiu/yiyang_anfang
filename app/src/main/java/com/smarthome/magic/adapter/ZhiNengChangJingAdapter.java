package com.smarthome.magic.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.R;
import com.smarthome.magic.model.ChangJingModel;

import java.util.List;

public class ZhiNengChangJingAdapter extends BaseQuickAdapter<ChangJingModel.DataBean.SceneBean, BaseViewHolder> {
    public ZhiNengChangJingAdapter(int layoutResId, @Nullable List<ChangJingModel.DataBean.SceneBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChangJingModel.DataBean.SceneBean item) {
        helper.addOnClickListener(R.id.rrl_main);
        helper.addOnClickListener(R.id.iv_zhixing);
        Glide.with(mContext).load(item.getScene_pic()).into((ImageView) helper.getView(R.id.iv_image));
        helper.setText(R.id.tv_name, item.getScene_title());
        TextView tvJianJie = helper.getView(R.id.tv_text);
        /**
         * 场景类型：1.一键执行  2.定时 3.动作触发
         */
        if (item.getScene_type().equals("1")) {
            tvJianJie.setText("一键执行");

        } else if (item.getScene_type().equals("2")) {

            tvJianJie.setText("定时");
        } else if (item.getScene_type().equals("3")) {

            tvJianJie.setText("动作触发");
        }
    }
}
