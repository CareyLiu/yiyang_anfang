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

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.bazinew.base.BaziBaseActivity;
import com.smarthome.magic.activity.wode_page.bazinew.model.DanganModel;
import com.smarthome.magic.activity.wode_page.bazinew.model.PaipanModel;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DanganEditActivity extends BaziBaseActivity {

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
    @BindView(R.id.bt_moren)
    Button bt_moren;
    @BindView(R.id.bt_delete)
    Button bt_delete;

    private DanganModel.DataBean model;
    private String sex;
    private String li;
    private String runyue;
    private String data;
    private String shichen;
    private String name;

    @Override
    public int getContentViewResId() {
        return R.layout.bazi_activity_dangan_edit;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("编辑排盘");
        tv_rightTitle.setText("排盘");
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMinpan();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        model = (DanganModel.DataBean) getIntent().getSerializableExtra("model");

        name = model.getName();
        data = model.getBirthday();
        shichen = model.getBirthhour();

        selectSex(model.getSex());
        selectLi(model.getBirthday_type());
        selectRunyue(model.getIsleap());

        ed_name.setText(name);
        tv_data.setText(data);
        tv_shichen.setText(shichen);
    }

    @OnClick({R.id.ll_select_nan, R.id.ll_select_nv, R.id.ll_select_yang, R.id.ll_select_yin, R.id.ll_select_runyue_n, R.id.ll_select_runyue_s, R.id.ll_data, R.id.ll_shichen, R.id.bt_moren, R.id.bt_delete})
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

                break;
            case R.id.ll_shichen:

                break;
            case R.id.bt_moren:
                setMoren();
                break;
            case R.id.bt_delete:
                deleteMinpan();
                break;
        }
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

    private void editMinpan() {
        name = ed_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            t("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(data)) {
            t("请选择日期");
            return;
        }

        if (TextUtils.isEmpty(shichen)) {
            t("请选择时间");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("code", "11012");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", model.getMingpan_id());
        map.put("name", name);
        map.put("birthday", data);
        map.put("birthhour", shichen);
        map.put("sex", sex + "");
        map.put("birthday_type", li + "");
        map.put("isleap", runyue + "");

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<PaipanModel.DataBean>>post(Urls.BAZIAPP)
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


    private void setMoren() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11019");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", model.getMingpan_id());
        map.put("mingpan_user", "1");

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<PaipanModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<PaipanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<PaipanModel.DataBean>> response) {
                        t(response.body().msg);
                    }
                });
    }

    private void deleteMinpan() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "11015");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("mingpan_id", model.getMingpan_id());

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<PaipanModel.DataBean>>post(Urls.BAZIAPP)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<PaipanModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<PaipanModel.DataBean>> response) {
                        t(response.body().msg);
                        finish();
                    }
                });
    }
}
