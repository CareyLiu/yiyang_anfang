package com.smarthome.magic.adapter;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.smarthome.magic.R;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseViewHolder;
import com.smarthome.magic.model.Home;

import org.w3c.dom.Text;

import java.util.List;

public class FenLeiHomeLeftListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public int strPosition;

    public FenLeiHomeLeftListAdapter(int layoutResId, @Nullable List<String> data, String strPosition) {
        super(layoutResId, data);
        this.strPosition = Integer.parseInt(strPosition);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (helper.getLayoutPosition() == strPosition) {
            //  helper.getView(R.id.tv_title).setSelected(true);
            TextView tvTitle = helper.getView(R.id.tv_title);
            tvTitle.setTextColor(mContext.getResources().getColor(R.color.red_61832));
            helper.getView(R.id.constrain).setBackgroundColor(mContext.getResources().getColor(R.color.white));
        } else {
            TextView tvTitle = helper.getView(R.id.tv_title);
            tvTitle.setTextColor(mContext.getResources().getColor(R.color.black_ff232323));
            helper.getView(R.id.constrain).setBackgroundColor(mContext.getResources().getColor(R.color.grayfff5f5f5));
        }
        helper.setText(R.id.tv_title, item);
        helper.addOnClickListener(R.id.constrain);
    }
}
