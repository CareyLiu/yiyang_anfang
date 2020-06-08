package com.smarthome.magic.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.OwnerInfo;
import com.smarthome.magic.util.AlertUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartsInfoActivity extends BaseActivity {


    @BindView(R.id.back)
    LinearLayout mBack;
    @BindView(R.id.item_plug)
    LinearLayout mItemPlug;
    @BindView(R.id.item_sensor)
    LinearLayout mItemSensor;
    @BindView(R.id.item_pump)
    LinearLayout mItemPump;
    @BindView(R.id.item_fan)
    LinearLayout mItemFan;
    @BindView(R.id.item_burner)
    LinearLayout mItemBurner;
    @BindView(R.id.item_control)
    LinearLayout mItemControl;
    @BindView(R.id.item_switch)
    LinearLayout mItemSwitch;
    @BindView(R.id.item_radiator)
    LinearLayout mItemRadiator;
    @BindView(R.id.item_line)
    LinearLayout mItemLine;
    @BindView(R.id.item_plastic)
    LinearLayout mItemPlastic;
    @BindView(R.id.item_rubber)
    LinearLayout mItemRubber;
    @BindView(R.id.tv_plug)
    TextView mTvPlug;
    @BindView(R.id.tv_sensor)
    TextView mTvSensor;
    @BindView(R.id.tv_pump)
    TextView mTvPump;
    @BindView(R.id.tv_fan)
    TextView mTvFan;
    @BindView(R.id.tv_burner)
    TextView mTvBurner;
    @BindView(R.id.tv_board)
    TextView mTvBoard;
    @BindView(R.id.tv_switch)
    TextView mTvSwitch;
    @BindView(R.id.tv_heat)
    TextView mTvHeat;
    @BindView(R.id.tv_line)
    TextView mTvLine;
    @BindView(R.id.tv_shell)
    TextView mTvShell;
    @BindView(R.id.tv_rubber)
    TextView mTvRubber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts_info);
        ButterKnife.bind(this);
        requestData();
    }


    @OnClick({R.id.back, R.id.item_plug, R.id.item_sensor, R.id.item_pump, R.id.item_fan, R.id.item_burner, R.id.item_control, R.id.item_switch, R.id.item_radiator, R.id.item_line, R.id.item_plastic, R.id.item_rubber})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.item_plug:
                MyShowDataBottomActivity.actionStart(this, "0");
                break;
            case R.id.item_sensor:
                MyShowDataBottomActivity.actionStart(this, "1");
                break;
            case R.id.item_pump:
                MyShowDataBottomActivity.actionStart(this, "2");
                break;
            case R.id.item_fan:
                MyShowDataBottomActivity.actionStart(this, "3");
                break;
            case R.id.item_burner:
                MyShowDataBottomActivity.actionStart(this, "4");
                break;
            case R.id.item_control:
                MyShowDataBottomActivity.actionStart(this, "5");
                break;
            case R.id.item_switch:
                MyShowDataBottomActivity.actionStart(this, "6");
                break;
            case R.id.item_radiator:
                MyShowDataBottomActivity.actionStart(this, "7");
                break;
            case R.id.item_line:
                MyShowDataBottomActivity.actionStart(this, "8");
                break;
            case R.id.item_plastic:
                MyShowDataBottomActivity.actionStart(this, "9");
                break;
            case R.id.item_rubber:
                MyShowDataBottomActivity.actionStart(this, "10");
                break;
        }

    }

    String id;// 设备id；
    String dianHuoSaiId;//点活塞id

    public void requestData() {

        Map<String, String> map = new HashMap<>();
        map.put("code", "03205");
        map.put("key", Constant.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(this).getString("car_id", ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<OwnerInfo.DataBean>>post(Constant.SERVER_URL + "wit/app/zhu")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<OwnerInfo.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<OwnerInfo.DataBean>> response) {
                        mTvBoard.setText(response.body().data.get(0).getDash_board_all_name());
                        mTvBurner.setText(response.body().data.get(0).getFirebox_all_name());
                        mTvFan.setText(response.body().data.get(0).getDraught_fan_all_name());
                        mTvHeat.setText(response.body().data.get(0).getRadiator_all_name());
                        mTvLine.setText(response.body().data.get(0).getWiring_harness_all_name());
                        mTvPlug.setText(response.body().data.get(0).getIgnition_all_name());
                        mTvPump.setText(response.body().data.get(0).getOil_pump_all_name());
                        mTvRubber.setText(response.body().data.get(0).getVulcanizate_all_name());
                        mTvSensor.setText(response.body().data.get(0).getOutlet_sensor_all_name());
                        mTvShell.setText(response.body().data.get(0).getOutermost_shell_all_name());
                        mTvSwitch.setText(response.body().data.get(0).getControl_switch_all_name());


                    }

                    @Override
                    public void onError(Response<AppResponse<OwnerInfo.DataBean>> response) {
                        AlertUtil.t(PartsInfoActivity.this, response.getException().getMessage());
                    }
                });
    }


}
