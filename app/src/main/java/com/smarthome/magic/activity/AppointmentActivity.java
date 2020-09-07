package com.smarthome.magic.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;
import com.smarthome.magic.R;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppointmentActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;

    @BindView(R.id.fl_date)
    FrameLayout flDate;
    @BindView(R.id.cb_sunday)
    CheckBox cbSunday;
    @BindView(R.id.cb_monday)
    CheckBox cbMonday;
    @BindView(R.id.cb_tuesday)
    CheckBox cbTuesday;
    @BindView(R.id.cb_wednesday)
    CheckBox cbWednesday;
    @BindView(R.id.cb_thursday)
    CheckBox cbThursday;
    @BindView(R.id.cb_friday)
    CheckBox cbFriday;
    @BindView(R.id.cb_saturday)
    CheckBox cbSaturday;
    @BindView(R.id.fl_time)
    FrameLayout flTime;


    private TimePickerView pvDate, pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ButterKnife.bind(this);

        //时间选择器
        pvDate = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {

                    }
                })
                .setType(new boolean[]{false, false, false, true, true, false})
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.WHITE)
                .setBgColor(0x00ffffff)
                .setOutSideColor(0x00ffffff)
                .setTextColorCenter(getResources().getColor(R.color.white))
                .setTextColorOut(getResources().getColor(R.color.white))
                .setDividerType(WheelView.DividerType.WRAP)
                .setContentTextSize(20)
                .setDecorView(flDate)//非dialog模式下,设置ViewGroup, pickerView将会添加到这个ViewGroup中
                .setOutSideCancelable(false)
                .build();
        pvDate.show();
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null

            }
        })
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {

                    }
                })
                .setType(new boolean[]{false, false, false, true, true, false})
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.WHITE)
                .setBgColor(0x00ffffff)
                .setOutSideColor(0x00ffffff)
                .setTextColorCenter(getResources().getColor(R.color.white))
                .setTextColorOut(getResources().getColor(R.color.white))
                .setDividerType(WheelView.DividerType.WRAP)
                .setContentTextSize(20)
                .setDecorView(flTime)//非dialog模式下,设置ViewGroup, pickerView将会添加到这个ViewGroup中
                .setOutSideCancelable(false)
                .build();
        pvTime.show();

    }

    @OnClick({R.id.rl_back, R.id.btn_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_setting:
                showToast("设置成功");
                break;

        }
    }

    //设置定时
    public void setDingShi() {

    }
}
