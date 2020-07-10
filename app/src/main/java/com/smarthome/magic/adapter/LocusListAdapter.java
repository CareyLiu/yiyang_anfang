package com.smarthome.magic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.HistoryLocusActivity;
import com.smarthome.magic.activity.LocusActivity;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;

import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.Locus;
import com.smarthome.magic.model.SmartDevices;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sl on 2019/6/12.
 *
 */

public class LocusListAdapter extends ListBaseAdapter<Locus.DataBean> {
    public LocusListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_locus;
    }


    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {
        LinearLayout layout_see = holder.getView(R.id.layout_see);
        TextView tv_address = holder.getView(R.id.tv_address);
        TextView tv_time = holder.getView(R.id.tv_time);

        tv_address.setText(getDataList().get(position).getGps_addr());
        tv_time.setText(getDataList().get(position).getCreate_time());

        layout_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转轨迹地图
                mContext.startActivity(new Intent(mContext, LocusActivity.class).putExtra("time", getDataList().get(position).getCreate_time()));
            }
        });

    }
}
