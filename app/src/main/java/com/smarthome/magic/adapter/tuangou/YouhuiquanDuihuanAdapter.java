package com.smarthome.magic.adapter.tuangou;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.R;
import com.smarthome.magic.model.TuanGouYouHuiJuanModel;
import com.smarthome.magic.model.YouhuijuanDuihuanModel;

import java.util.List;

import androidx.annotation.Nullable;

public class YouhuiquanDuihuanAdapter extends BaseQuickAdapter<YouhuijuanDuihuanModel.DataBean, BaseViewHolder> {

    public YouhuiquanDuihuanAdapter(int layoutResId, @Nullable List<YouhuijuanDuihuanModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, YouhuijuanDuihuanModel.DataBean item) {
        helper.setText(R.id.tv_name, item.getInst_name());
        ImageView iv_select = helper.getView(R.id.iv_select);
        if (item.isSelect()) {
            iv_select.setImageResource(R.mipmap.kaquan_select_s);
        } else {
            iv_select.setImageResource(R.mipmap.kaquan_select_n);
        }
    }

}
