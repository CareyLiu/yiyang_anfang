package com.smarthome.magic.activity.tongcheng58;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;

public class GongJiangRuZhuActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewResId() {
        return super.getContentViewResId();
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("工匠入驻");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }

}
