package com.smarthome.magic.activity.wode_page.bazinew;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.smarthome.magic.activity.wode_page.bazinew.model.DanganModel;
import com.smarthome.magic.activity.wode_page.bazinew.model.PaipanModel;
import com.smarthome.magic.activity.wode_page.bazinew.utils.TimeUtils;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DanganNewActivity extends BaziBaseActivity {

    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.iv_nan)
    ImageView iv_nan;
    @BindView(R.id.ll_select_nan)
    LinearLayout ll_select_nan;
    @BindView(R.id.iv_nv)
    ImageView iv_nv;
    @BindView(R.id.ll_select_nv)
    LinearLayout ll_select_nv;
    @BindView(R.id.iv_yang)
    ImageView iv_yang;
    @BindView(R.id.ll_select_yang)
    LinearLayout ll_select_yang;
    @BindView(R.id.iv_yin)
    ImageView iv_yin;
    @BindView(R.id.ll_select_yin)
    LinearLayout ll_select_yin;
    @BindView(R.id.iv_runyue_n)
    ImageView iv_runyue_n;
    @BindView(R.id.ll_select_runyue_n)
    LinearLayout ll_select_runyue_n;
    @BindView(R.id.iv_runyue_s)
    ImageView iv_runyue_s;
    @BindView(R.id.ll_select_runyue_s)
    LinearLayout ll_select_runyue_s;
    @BindView(R.id.ll_runyue)
    LinearLayout ll_runyue;
    @BindView(R.id.view_runyue)
    View view_runyue;
    @BindView(R.id.tv_data)
    TextView tv_data;
    @BindView(R.id.ll_data)
    LinearLayout ll_data;
    @BindView(R.id.tv_shichen)
    TextView tv_shichen;
    @BindView(R.id.ll_shichen)
    LinearLayout ll_shichen;
    @BindView(R.id.bt_paipan)
    Button bt_paipan;

    private String sex;
    private String li;
    private String runyue;
    private String birthdayData;
    private String shichen;
    private String name;
    private OptionsPickerView shiPicker;
    private TimePickerView timePicker;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_dangan;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("新增命盘");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        sex = "1";
        li = "1";
        runyue = "0";
    }

    @OnClick({R.id.ll_select_nan, R.id.ll_select_nv, R.id.ll_select_yang, R.id.ll_select_yin, R.id.ll_select_runyue_n, R.id.ll_select_runyue_s, R.id.ll_data, R.id.ll_shichen, R.id.bt_paipan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_select_nan:
                selectSex("1");
                break;
            case R.id.ll_select_nv:
                selectSex("2");
                break;
            case R.id.ll_select_yang:
                selectLi("1");
                break;
            case R.id.ll_select_yin:
                selectLi("2");
                break;
            case R.id.ll_select_runyue_n:
                selectRunyue("0");
                break;
            case R.id.ll_select_runyue_s:
                selectRunyue("1");
                break;
            case R.id.ll_data:
                selectData();
                break;
            case R.id.ll_shichen:
                selectTime();
                break;
            case R.id.bt_paipan:
                clickPaipan();
                break;
        }
    }

    private void selectTime() {
        if (shiPicker == null) {
            List<String> shichenList = new ArrayList<>();
            for (int i = 0; i < 24; i++) {
                if (i < 10) {
                    shichenList.add("0" + i);
                } else {
                    shichenList.add("" + i);
                }
            }

            //条件选择器
            shiPicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    shichen = shichenList.get(options1);
                    tv_shichen.setText(shichen);
                }
            }).build();
            shiPicker.setPicker(shichenList);
        }
        shiPicker.show();
    }

    private void selectData() {
        if (timePicker == null) {
            //时间选择器
            timePicker = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    birthdayData = TimeUtils.getData(date);
                    tv_data.setText(birthdayData);
                }
            }).build();
        }
        timePicker.show();
    }

    private void selectRunyue(String runyue) {
        this.runyue = runyue;
        iv_runyue_n.setImageResource(R.mipmap.ziwei_icon_circle_grey);
        iv_runyue_s.setImageResource(R.mipmap.ziwei_icon_circle_grey);
        switch (runyue) {
            case "0":
                iv_runyue_n.setImageResource(R.mipmap.ziwei_icon_circle_purple);
                break;
            case "1":
                iv_runyue_s.setImageResource(R.mipmap.ziwei_icon_circle_purple);
                break;
        }
    }

    private void selectLi(String li) {
        this.li = li;
        iv_yang.setImageResource(R.mipmap.ziwei_icon_circle_grey);
        iv_yin.setImageResource(R.mipmap.ziwei_icon_circle_grey);
        switch (li) {
            case "1":
                iv_yang.setImageResource(R.mipmap.ziwei_icon_circle_purple);
                ll_runyue.setVisibility(View.GONE);
                view_runyue.setVisibility(View.GONE);
                selectRunyue("0");
                break;
            case "2":
                iv_yin.setImageResource(R.mipmap.ziwei_icon_circle_purple);
                ll_runyue.setVisibility(View.VISIBLE);
                view_runyue.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void selectSex(String sex) {
        this.sex = sex;
        iv_nan.setImageResource(R.mipmap.ziwei_icon_circle_grey);
        iv_nv.setImageResource(R.mipmap.ziwei_icon_circle_grey);
        switch (sex) {
            case "1":
                iv_nan.setImageResource(R.mipmap.ziwei_icon_circle_purple);
                break;
            case "2":
                iv_nv.setImageResource(R.mipmap.ziwei_icon_circle_purple);
                break;
        }
    }


    private void clickPaipan() {
        name = ed_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            t("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(birthdayData)) {
            t("请选择日期");
            return;
        }

        if (TextUtils.isEmpty(shichen)) {
            t("请选择时间");
            return;
        }

        getNet();
    }

    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11011");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("name", name);
        map.put("birthday", birthdayData);
        map.put("birthhour", shichen);
        map.put("sex", sex + "");
        map.put("birthday_type", li + "");
        map.put("isleap", runyue + "");


        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<PaipanModel.DataBean>>post(Urls.BAZIAPPUSER)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<PaipanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<PaipanModel.DataBean>> response) {
                        t("我成功了");
                        finish();
                    }
                });
    }
}