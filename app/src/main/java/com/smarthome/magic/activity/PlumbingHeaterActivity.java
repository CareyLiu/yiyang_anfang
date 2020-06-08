package com.smarthome.magic.activity;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.Constant;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.model.CarDetails;
import com.smarthome.magic.model.DataIn;
import com.smarthome.magic.model.HostModel;
import com.smarthome.magic.model.SerializableMap;
import com.smarthome.magic.model.SmartDevice_0;
import com.smarthome.magic.model.SmartDevice_car_0364;
import com.smarthome.magic.service.WitMqttFormatService;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.ConstantUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PlumbingHeaterActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "WindHeaterActivity";
    public Handler mHandler;
    public static Handler stateHandler;

    List<SmartDevice_car_0364.DataBean> carList = new ArrayList<>();//车联网列表数据源
    private static int progressValue;
    DrawerLayout drawer;
    int gear = 0;
    WitMqttFormatService witMqttFormatService;
    ValueAnimator valueAnimator;
    ImageView ivBrandPic;
    TextView tvBrandName, tvCarNumber;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_heater_host)
    RelativeLayout ivHeaterHost;
    //  @BindView(R.id.tv_wd)
    //  TextView tvWd;
    // @BindView(R.id.arcProgressBar)
    // ArcProgressBar arcProgressBar;
    @BindView(R.id.btn_heater_close)
    ImageView btnHeaterClose;
    //   @BindView(R.id.frameLayout)
//    FrameLayout frameLayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    ImageView mZhenShuinuan;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuinuan);
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
                .statusBarColor(R.color.transparent)     //状态栏颜色，不写默认透明色
                .navigationBarColor(R.color.transparent) //导航栏颜色，不写默认黑色
                .barColor(R.color.transparent).init();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        ivBrandPic = navigationView.getHeaderView(0).findViewById(R.id.iv_brand_pic);
        tvBrandName = navigationView.getHeaderView(0).findViewById(R.id.tv_brand_name);
        tvCarNumber = navigationView.getHeaderView(0).findViewById(R.id.tv_car_number);
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbarTitle.setText("水暖加热器");
        @SuppressLint("ResourceType") ColorStateList csl = getResources().getColorStateList(R.drawable.menu_item_color);
        navigationView.setItemTextColor(csl);
        initHandler();
        //  arcProgressBar.setHandler(mHandler);
        //   arcProgressBar.setOpen(true);
        AnimationDrawable animationDrawable;
        animationDrawable = (AnimationDrawable) ivHeaterHost.getBackground();
        animationDrawable.start();
        mZhenShuinuan = findViewById(R.id.zhen_shuinuan);


        //mZhenShuinuan.setPivotX(mZhenShuinuan.getWidth() / 2);
        // mZhenShuinuan.setPivotY(mZhenShuinuan.getHeight() / 2);//支点在图片中心
        mZhenShuinuan.setRotation(-180);


        OkHttpClient mOkHttpClient = new OkHttpClient();
        DataIn in = new DataIn();
        in.code = "03064";
        in.key = Constant.KEY;
        in.user_car_type = "1";
        in.token = UserManager.getManager(this).getAppToken();
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(in));
        final Request request = new Request.Builder().url(Constant.SERVER_URL + "wit/app/user").post(body).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                Gson gson = new Gson();
                SmartDevice_0 bean = gson.fromJson(response.body().string(), SmartDevice_0.class);

                System.out.println(bean.getData().size()+"");
                for (int i = 0; i < bean.getData().size(); i++) {
                   System.out.println(bean.getData().get(i).getCar_brand_name());
               }


            }
        });


    }


    @SuppressLint("HandlerLeak")
    private void initHandler() {
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case ConstantUtil.MSG_HEATER_ROGRESS_VALUE_CHANGE:
                        handleProgressMsg(msg);
                        break;
                }
                super.handleMessage(msg);
            }
        };

        stateHandler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                switch (msg.what) {
                    case ConstantUtil.MSG_HEATER_MANUAL_ORDER:
                        //手动开机
                        openMode(b.getString("oper_wendu"), b.getString("oper_dang"));
                        break;
                    case ConstantUtil.MSG_HEATER_SHUTDOWN_ORDER:
                        //关机
                        closeMode(b.getString("oper_wendu"));
                        break;
                    case ConstantUtil.MSG_UPDATE_GEAR_ORDER:
                        //通知当前档位
                        AlertUtil.t(PlumbingHeaterActivity.this, "当前档位" + gear);
                        break;

                    case ConstantUtil.MSG_HEATER_ACTUAL_DATA:
                        //加载实时数据
                        SerializableMap serializableMap = (SerializableMap) b.get("map");
                        Log.d("version", serializableMap.getMap().get("version_feng"));
                        HostModel.version = serializableMap.getMap().get("version_feng");


                }
                super.handleMessage(msg);
            }
        };
    }


    private void handleProgressMsg(Message msg) {
        Bundle b = msg.getData();
        progressValue = b.getInt("progress_value");
        int PROGRESSDATABUFF = progressValue;
        if (PROGRESSDATABUFF <= 20) {
            gear = 1;
        } else if ((PROGRESSDATABUFF > 20) && (PROGRESSDATABUFF <= 40)) {
            gear = 2;
        } else if ((PROGRESSDATABUFF > 40) && (PROGRESSDATABUFF <= 60)) {
            gear = 3;
        } else if ((PROGRESSDATABUFF > 60) && (PROGRESSDATABUFF <= 80)) {
            gear = 4;
        } else if ((PROGRESSDATABUFF > 80) && (PROGRESSDATABUFF <= 100)) {
            gear = 5;
        } else {
            gear = 3;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.heater_menu_option, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
//                moveTaskToBack(false);
//                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                break;
            case R.id.action_settings:
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawers();
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_bluetooth:
                //切换蓝牙模式
                showToast("暂不支持");
                break;
            case R.id.nav_trajectory:
                //历史轨迹
                startActivity(new Intent(this, HistoryLocusActivity.class));
                break;
            case R.id.nav_location:
                //定位
                startActivity(new Intent(this, LocationActivity.class));
                break;
            case R.id.nav_timing:
                //定时
                startActivity(new Intent(this, AppointmentActivity.class));
                break;
            case R.id.nav_record:
                //维修记录
                startActivity(new Intent(this, RepairOrderAcitivity.class));
                break;
            case R.id.nav_state:
                //加热器状态
                startActivity(new Intent(this, DeviceStateActivity.class));
                break;
            case R.id.nav_report:
                //故障报警
                startActivity(new Intent(this, DiagnosisActivity.class));
                break;
            case R.id.nav_corral:
                //地理围栏
                startActivity(new Intent(this, WebViewActivity.class).
                        putExtra("url", PreferenceHelper.getInstance(this).getString("fence_url", "")).putExtra("title", "地理围栏"));
                break;
            case R.id.nav_setting:
                startActivity(new Intent(this, HeaterSettingActivity.class));
                //设置
                break;
        }


        return false;
    }

    public void requestData() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03107");
        map.put("key", Constant.KEY);
        map.put("token", UserManager.getManager(this).getAppToken());
        map.put("user_car_id", PreferenceHelper.getInstance(this).getString("car_id", ""));
        Gson gson = new Gson();
        OkGo.<AppResponse<CarDetails.DataBean>>post(Constant.SERVER_URL + "wit/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<CarDetails.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<CarDetails.DataBean>> response) {
                        PreferenceHelper.getInstance(PlumbingHeaterActivity.this).putString("fence_url", response.body().data.get(0).getWeilan_url());
                        Glide.with(PlumbingHeaterActivity.this).load(response.body().data.get(0).getCar_brand_url_one()).into(ivBrandPic);
                        tvBrandName.setText(response.body().data.get(0).getCar_brand_name_one());
                        tvCarNumber.setText(response.body().data.get(0).getPlate_number());
                    }

                    @Override
                    public void onError(Response<AppResponse<CarDetails.DataBean>> response) {
                        AlertUtil.t(PlumbingHeaterActivity.this, response.getException().getMessage());
                    }
                });
    }


    public void openMode(String temp, String gear) {
        //  arcProgressBar.setOpen(true);
        btnHeaterClose.setBackground(getResources().getDrawable(R.drawable.bg_heater_close_btn_on));
        //  tvWd.setText(temp);
        progressValue = Integer.parseInt(gear) * 100 / 5;
        valueAnimator = ValueAnimator.ofInt(0, progressValue);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //  arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

    public void closeMode(String temp) {
        //  arcProgressBar.setOpen(false);
        // tvWd.setText(temp);
        btnHeaterClose.setBackground(getResources().getDrawable(R.drawable.bg_heater_close_btn_off));
        valueAnimator = ValueAnimator.ofInt(progressValue, 0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //   arcProgressBar.setCurrentProgress((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }


}
