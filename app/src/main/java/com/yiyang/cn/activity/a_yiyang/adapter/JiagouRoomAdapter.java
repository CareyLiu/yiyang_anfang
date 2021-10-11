package com.yiyang.cn.activity.a_yiyang.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.model.JigouRoomModel;

import java.util.List;

import androidx.annotation.Nullable;

public class JiagouRoomAdapter extends BaseQuickAdapter<JigouRoomModel, BaseViewHolder> {


    public JiagouRoomAdapter(int layoutResId, @Nullable List<JigouRoomModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JigouRoomModel item) {
        ImageView iv_room = helper.getView(R.id.iv_room);
        iv_room.setImageResource(item.getImgID());
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_jieshao,item.getJieshao());
        helper.setText(R.id.tv_jiage,item.getJiage());
    }
}