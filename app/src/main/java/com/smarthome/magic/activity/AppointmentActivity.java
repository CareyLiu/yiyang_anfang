package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.CarDetails;
import com.smarthome.magic.util.AlertUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    @BindView(R.id.btn_setting)
    Button btnSetting;

    private String ccid;
    private TimePickerView pvDate, pvTime;
    private String chooseHour = "";//小时
    private String chooseMin = "";//分钟

    @Override
    public int getContentViewResId() {
        return R.layout.activity_appointment;
    }


    @Override
    public void initImmersion() {
        //super.initImmersion();
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.titleBar(R.id.rl_main).init();

    }

    WheelView min;
    WheelView hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_appointment);
        ButterKnife.bind(this);
        ccid = getIntent().getStringExtra("ccid");
        //时间选择器

        pvDate = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                format.format(date);
//                Log.i("choosetime", format.format(date));
            }


        })
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {

                        min = v.findViewById(R.id.min);
                        hour = v.findViewById(R.id.hour);

                        min.setOnItemSelectedListener(new OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(int index) {
                                Log.i("min_min", String.valueOf(index));
                                chooseMin = String.valueOf(index);
                            }
                        });


                        hour.setOnItemSelectedListener(new OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(int index) {
                                Log.i("hour_hour", String.valueOf(index));
                                chooseHour = String.valueOf(index);
                            }
                        });


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

        chooseMin = String.valueOf(min.getCurrentItem());
        chooseHour = String.valueOf(hour.getCurrentItem());



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
                //showToast("设置成功");

                setDingShi(ccid);
                break;

        }
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context, String str) {

        Intent intent = new Intent(context, AppointmentActivity.class);
        intent.putExtra("ccid", str);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    //设置定时
    public void setDingShi(String ccid) {


        Map<String, String> map = new HashMap<>();
        map.put("code", "03200");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("ccid", ccid);

        String str = null;

        if (cbSunday.isChecked()) {
            str = "1";
        } else {
            str = "0";
        }

        if (cbMonday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }

        if (cbTuesday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }

        if (cbWednesday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }
        if (cbThursday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }
        if (cbFriday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }
        if (cbSaturday.isChecked()) {
            str = str + "1";
        } else {
            str = str + "0";
        }

        str = str + chooseHour;
        str = str + chooseMin;
        map.put("time", str);


        Log.i("riqi", str);
        Gson gson = new Gson();
        OkGo.<AppResponse>post(Urls.DINGSHI)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse>() {
                    @Override
                    public void onSuccess(final Response<AppResponse> response) {

                        UIHelper.ToastMessage(mContext,"设置成功");
                    }

                    @Override
                    public void onError(Response<AppResponse> response) {
                        AlertUtil.t(AppointmentActivity.this, response.getException().getMessage());
                    }
                });
    }


}
