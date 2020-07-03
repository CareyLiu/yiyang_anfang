package com.smarthome.magic.activity.wode_page.bazinew;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.smarthome.magic.activity.wode_page.bazinew.dialog.JiesuoDialog;
import com.smarthome.magic.activity.wode_page.bazinew.model.YunshiModel;
import com.smarthome.magic.activity.wode_page.bazinew.utils.TimeUtils;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YunshiChuanyiActivity extends BaziBaseActivity {

    @BindView(R.id.tv_name_sex)
    TextView tv_name_sex;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.tv_data_jiexi)
    TextView tv_data_jiexi;
    @BindView(R.id.tv_yunshi)
    TextView tv_yunshi;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.ll_jiesuo)
    LinearLayout ll_jiesuo;
    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    @BindView(R.id.tv_select_data)
    TextView tv_select_data;
    @BindView(R.id.ll_data)
    LinearLayout ll_data;

    private int code;
    private String mingpan_id;
    private String ex_type;
    private String ex_factor;
    private int year;
    private int month;
    private int day;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_yunshi_chuanyi;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("运势");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        code = getIntent().getIntExtra("code", 0);
        mingpan_id = getIntent().getStringExtra("mingpan_id");

        tv_title.setText("穿衣指数");
        tv_yunshi.setText("穿衣指数");
        ex_type = "4";
        year = TimeUtils.getYear();
        month = TimeUtils.getMonth();
        day = TimeUtils.getDay();

        String monthS = "";
        String dayS = "";
        if (month >= 10) {
            monthS = "" + month;
        } else {
            monthS = "0" + month;
        }
        if (day >= 10) {
            dayS = "" + day;
        } else {
            dayS = "0" + day;
        }
        ex_factor = "" + year + monthS + dayS;
        tv_select_data.setText(year + "-" + monthS + "-" + dayS);

        getNet();
    }


    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11025");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", mingpan_id);
        map.put("ex_type", ex_type);
        map.put("ex_factor", ex_factor);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<YunshiModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<YunshiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<YunshiModel.DataBean>> response) {
                        List<YunshiModel.DataBean> data = response.body().data;
                        if (data != null && data.size() > 0) {
                            YunshiModel.DataBean bean = data.get(0);
                            tv_name_sex.setText(bean.getName() + "  " + bean.getSex());
                            tv_birthday.setText(bean.getBirthday());
                            tv_data_jiexi.setText(bean.getTime_text());

                            String lock = bean.getLock();
                            if ("1".equals(lock)) {
                                ll_data.setVisibility(View.VISIBLE);
                                ll_jiesuo.setVisibility(View.GONE);
                                tv_content.setText(bean.getEx_text());
                            } else {
                                ll_data.setVisibility(View.GONE);
                                ll_jiesuo.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<YunshiModel.DataBean>> response) {
                        super.onError(response);
                        tv_content.setVisibility(View.GONE);
                        ll_jiesuo.setVisibility(View.GONE);
                        String msg = response.getException().getMessage();
                        String[] msgToast = msg.split("：");
                        if (msgToast.length == 3) {
                            t(msgToast[2]);
                        }
                    }
                });
    }

    @OnClick({R.id.ll_jiesuo, R.id.iv_left, R.id.iv_right, R.id.tv_select_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_jiesuo:
                clickJiesuo();
                break;
            case R.id.iv_left:
                clickLeft();
                break;
            case R.id.iv_right:
                clickRight();
                break;
            case R.id.tv_select_data:
                selectData();
                break;
        }
    }

    private void clickJiesuo() {
        JiesuoDialog dialog = new JiesuoDialog(this, 1);
        dialog.show();
    }

    private void selectData() {

    }

    private void clickRight() {
        int nowDay = TimeUtils.getMonthLastDay(year, month);
        if (day < nowDay) {
            day++;
        } else {
            if (month < 12) {
                month++;
            } else {
                year++;
                month = 1;
            }
            day = 1;
        }

        String monthS = "";
        String dayS = "";
        if (month >= 10) {
            monthS = "" + month;
        } else {
            monthS = "0" + month;
        }
        if (day >= 10) {
            dayS = "" + day;
        } else {
            dayS = "0" + day;
        }
        ex_factor = "" + year + monthS + dayS;
        tv_select_data.setText(year + "-" + monthS + "-" + dayS);

        getNet();
    }

    private void clickLeft() {
        if (day > 1) {
            day--;
        } else {
            if (month > 1) {
                month--;
            } else {
                year--;
                month = 12;
            }
            day = TimeUtils.getMonthLastDay(year, month);
        }

        String monthS = "";
        String dayS = "";
        if (month >= 10) {
            monthS = "" + month;
        } else {
            monthS = "0" + month;
        }
        if (day >= 10) {
            dayS = "" + day;
        } else {
            dayS = "0" + day;
        }
        ex_factor = "" + year + monthS + dayS;
        tv_select_data.setText(year + "-" + monthS + "-" + dayS);

        getNet();
    }
}
