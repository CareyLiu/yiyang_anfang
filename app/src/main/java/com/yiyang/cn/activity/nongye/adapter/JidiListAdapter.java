package com.yiyang.cn.activity.nongye.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.model.JidiListModel;

import java.util.List;

import androidx.annotation.Nullable;

public class JidiListAdapter extends BaseQuickAdapter<JidiListModel,BaseViewHolder> {

    public JidiListAdapter(int layoutResId, @Nullable List<JidiListModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JidiListModel item) {
        if (!TextUtils.isEmpty(item.getName())){
            helper.setText(R.id.tv_name,item.getName());
        }
    }
}
