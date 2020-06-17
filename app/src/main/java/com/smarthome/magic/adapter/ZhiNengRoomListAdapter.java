package com.smarthome.magic.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.model.MessageModel;

import java.util.List;

public class ZhiNengRoomListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ZhiNengRoomListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }

}
