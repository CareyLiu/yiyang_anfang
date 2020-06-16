package com.smarthome.magic.activity.wode_page;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.readystatesoftware.chuck.internal.ui.MainActivity;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.PhoneCheckActivity;
import com.smarthome.magic.activity.SettingActivity;
import com.smarthome.magic.adapter.MyQianBaoAdapter;
import com.smarthome.magic.app.App;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.common.StringUtils;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.MyQianBaoModel;
import com.smarthome.magic.model.MyQianBaoXianFeiMingXiModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.smarthome.magic.get_net.Urls.HOME_PICTURE_HOME;

public class MyQianBaoActivity extends BaseActivity {

    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    MyQianBaoAdapter myQianBaoAdapter;
    List<MyQianBaoXianFeiMingXiModel.DataBean.CunsumerListBean> stringList = new ArrayList<>();
    Response<AppResponse<MyQianBaoModel.DataBean>> response;
    @BindView(R.id.iv_bacground)
    ImageView ivBacground;
    @BindView(R.id.tv_ketixianjine)
    TextView tvKetixianjine;
    @BindView(R.id.tv_ketixian_price)
    TextView tvKetixianPrice;
    @BindView(R.id.tv_kedikou)
    TextView tvKedikou;
    @BindView(R.id.tv_kedikou_price)
    TextView tvKedikouPrice;
    @BindView(R.id.tv_dongjiejine_price)
    TextView tvDongjiejinePrice;
    @BindView(R.id.tv_dongjiejine)
    TextView tvDongjiejine;
    @BindView(R.id.ll_tixian)
    LinearLayout llTixian;
    @BindView(R.id.cl_back)
    ConstraintLayout clBack;
    @BindView(R.id.tv_mingxi)
    TextView tvMingxi;
    @BindView(R.id.tv_zhichu)
    TextView tvZhichu;
    @BindView(R.id.view_line)
    View viewLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rlvList.setLayoutManager(new LinearLayoutManager(MyQianBaoActivity.this));
        myQianBaoAdapter = new MyQianBaoAdapter(R.layout.item_my_qianbao, stringList);
        rlvList.setAdapter(myQianBaoAdapter);
        myQianBaoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        // MyQianBaoDetailsActivity.actionStart(MyQianBaoActivity.this, myQianBaoAdapter.getData().get(position));
                        break;
                }
            }
        });

        getNet();
        llTixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkAliPay = PreferenceHelper.getInstance(MyQianBaoActivity.this).getString(App.CUNCHUBIND_ALIPAY, "0x11");
                if (checkAliPay.equals("1")) {//已经设置
                    if (StringUtils.isEmpty(response.body().data.get(0).getMoney_use())) {
                        response.body().data.get(0).setMoney_use("0.00");
                    }
                    BigDecimal bigDecimal = new BigDecimal(response.body().data.get(0).getMoney_use());

                    if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
                        //跳正常页面
                        TiXianActivity.actionStart(MyQianBaoActivity.this, response.body().data.get(0).getMoney_use(),"0");
                    } else {
                        UIHelper.ToastMessage(MyQianBaoActivity.this, "当前不可提现");
                    }
                } else {//2 未设置
                    showTwo();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getNet();
    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_my_qian_bao;
    }


    public void getNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "04203");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(MyQianBaoActivity.this).getAppToken());

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<MyQianBaoModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MyQianBaoModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MyQianBaoModel.DataBean>> response) {
                        MyQianBaoActivity.this.response = response;
                        tvKetixianPrice.setText("¥" + response.body().data.get(0).getMoney_use());
                        tvKedikou.setText("¥" + response.body().data.get(0).getRed_money_use());
                        tvDongjiejinePrice.setText("¥" + response.body().data.get(0).getRed_money_lock());
                        getMingXiNet();
                    }
                });
    }

    public void getMingXiNet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "04141");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(MyQianBaoActivity.this).getAppToken());

        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<MyQianBaoXianFeiMingXiModel.DataBean>>post(HOME_PICTURE_HOME)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<MyQianBaoXianFeiMingXiModel.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<MyQianBaoXianFeiMingXiModel.DataBean>> response) {
                        stringList.clear();
                        stringList.addAll(response.body().data.get(0).getCunsumerList());
                        myQianBaoAdapter.setNewData(stringList);
                        myQianBaoAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MyQianBaoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("钱包");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }


    @Override
    public boolean showToolBar() {
        return true;
    }

    private AlertDialog.Builder builder;

    /**
     * 两个按钮的 dialog
     */
    private void showTwo() {

        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.logi_icon).setTitle("账号绑定")
                .setMessage("是否前去绑定支付宝账号").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        startActivity(new Intent(MyQianBaoActivity.this, PhoneCheckActivity.class).putExtra("mod_id", "0008"));
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
        builder.create().show();
    }
}


