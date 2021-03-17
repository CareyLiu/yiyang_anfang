package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.app.BaseActivity;

public class WanNengYaoKongQiSet extends BaseActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.layout_wanneng_yaokongqi;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("设置");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void actionStart(Context context, String device_ccid) {
        Intent intent = new Intent(context, WanNengYaoKongQiSet.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("device_ccid", device_ccid);
        context.startActivity(intent);
    }
}
