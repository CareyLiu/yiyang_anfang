package com.smarthome.magic.activity.wode_page.bazinew;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.bazinew.base.BaziBaseActivity;

public class YunshiActivity extends BaziBaseActivity {


    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_dangan;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("运势");
    }

}
