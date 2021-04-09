package com.smarthome.magic.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.fragment.LineChartFragment;

public class TuBiaoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()    //
                .beginTransaction()
                .add(R.id.ll_main, new LineChartFragment())   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_tubiao;
    }

}
