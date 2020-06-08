package com.smarthome.magic.adapter.tuangou;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.smarthome.magic.R;
import com.smarthome.magic.model.TuanGouYouHuiJuanModel;

import java.util.List;

public class TuanGouYouHuiJuanAdapter extends BaseQuickAdapter<TuanGouYouHuiJuanModel.DataBean, BaseViewHolder> {

    public TuanGouYouHuiJuanAdapter(int layoutResId, @Nullable List<TuanGouYouHuiJuanModel.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TuanGouYouHuiJuanModel.DataBean item) {
        helper.setText(R.id.tv_money, "¥" + item.getAgio_moneys());
        helper.setText(R.id.tv_title, item.getAgio_title());
        helper.setText(R.id.tv_date, item.getUser_time() + "到期");
        helper.setText(R.id.tv_shengyu_cishu, "剩余" + item.getResidue_times() + "次");
        helper.addOnClickListener(R.id.constrain);
    }
}
