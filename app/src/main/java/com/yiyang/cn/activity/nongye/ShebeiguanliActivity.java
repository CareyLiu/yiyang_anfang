package com.yiyang.cn.activity.nongye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.adapter.ShebeiguanliAdapter;
import com.yiyang.cn.activity.nongye.model.ShebeiguanliModel;
import com.yiyang.cn.app.BaseActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShebeiguanliActivity extends BaseActivity {


    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    private List<ShebeiguanliModel> shebeiModels = new ArrayList<>();
    private ShebeiguanliAdapter shebeiguanliAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.nongye_act_shebeiguanli;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String name) {
        Intent intent = new Intent(context, ShebeiguanliActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        tv_title.setText(getIntent().getStringExtra("name"));
        initAdapdter();
    }

    private void initAdapdter() {
        shebeiModels.add(new ShebeiguanliModel("光控", "12:00", R.mipmap.guangkong));
        shebeiModels.add(new ShebeiguanliModel("温度", "12:00", R.mipmap.wendu));
        shebeiModels.add(new ShebeiguanliModel("通风", "12:00", R.mipmap.tongfeng));
        shebeiModels.add(new ShebeiguanliModel("滴灌", "12:00", R.mipmap.diguan));
        shebeiModels.add(new ShebeiguanliModel("大棚被子", "12:00", R.mipmap.beizi));
        shebeiModels.add(new ShebeiguanliModel("营养液", "12:00", R.mipmap.yingyangye));
        shebeiModels.add(new ShebeiguanliModel("灭虫", "12:00", R.mipmap.miechong));
        shebeiModels.add(new ShebeiguanliModel("灭草", "12:00", R.mipmap.miechao));
        shebeiModels.add(new ShebeiguanliModel("农机", "12:00", R.mipmap.nongji));
        shebeiModels.add(new ShebeiguanliModel("农具", "12:00", R.mipmap.nongju));

        shebeiguanliAdapter = new ShebeiguanliAdapter(R.layout.nongye_item_shebeiguanli, shebeiModels);
        rv_content.setLayoutManager(new GridLayoutManager(mContext, 2));
        rv_content.setAdapter(shebeiguanliAdapter);
        rv_content.setFocusable(false);

        shebeiguanliAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShebeiguanliModel model = shebeiModels.get(position);
                switch (view.getId()) {
                    case R.id.iv_switch:
                        clickSwitch(model, position);
                        break;
                    case R.id.iv_set:
                        clickTimeSet(model, position);
                        break;
                }
            }
        });
    }

    private void clickTimeSet(ShebeiguanliModel model, int position) {
        //时间选择器
        boolean[] select = {false, false, false, true, true, false};
        TimePickerView timePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                String chooseHour;
                String chooseMin;

                int hours = date.getHours();
                if (hours < 10) {
                    chooseHour = "0" + hours;
                } else {
                    chooseHour = "" + hours;
                }

                int minutes = date.getMinutes();
                if (minutes < 10) {
                    chooseMin = "0" + minutes;
                } else {
                    chooseMin = "" + minutes;
                }

                model.setTime(chooseHour + ":" + chooseMin);
                shebeiModels.set(position, model);
                shebeiguanliAdapter.setNewData(shebeiModels);
                shebeiguanliAdapter.notifyDataSetChanged();
            }
        }).setType(select).build();
        timePicker.show();
    }

    private void clickSwitch(ShebeiguanliModel model, int position) {
        model.setKai(!model.isKai());
        shebeiModels.set(position, model);
        shebeiguanliAdapter.setNewData(shebeiModels);
        shebeiguanliAdapter.notifyDataSetChanged();
    }
}
