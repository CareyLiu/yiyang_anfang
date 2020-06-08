package com.smarthome.magic.activity.tuangou;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.project_A.tuangou.TuanGouShangJiaDetailsInter;

public abstract class AbStracTuanGouShangJiaDetails extends BaseActivity implements TuanGouShangJiaDetailsInter {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    void getTurn() {
        setList();
        setHeader();

    }
}
