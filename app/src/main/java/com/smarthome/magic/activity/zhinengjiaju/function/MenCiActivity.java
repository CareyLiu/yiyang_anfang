package com.smarthome.magic.activity.zhinengjiaju.function;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;

import butterknife.BindView;

//智能家居 门磁
public class MenCiActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    @BindView(R.id.srL_smart)
    SmartRefreshLayout srLSmart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        srLSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
        //header 设备在线和离线
//        if (onlineState.equals("1")) {
//            tvShebeiZaixianHuashu.setText("设备在线");
//            ivShebeiZaixianzhuangtaiImg.setBackgroundResource(R.drawable.bg_zhineng_device_online);
//
//        } else if (onlineState.equals("2")) {
//            tvShebeiZaixianHuashu.setText("设备离线");
//            ivShebeiZaixianzhuangtaiImg.setBackgroundResource(R.drawable.bg_zhineng_device_offline);
//        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_menci;
    }


}
