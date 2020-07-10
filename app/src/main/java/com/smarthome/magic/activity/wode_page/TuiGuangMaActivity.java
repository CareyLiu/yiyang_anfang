package com.smarthome.magic.activity.wode_page;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.taokeshagncheng.QueRenDingDanActivity;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;

import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.TuiGuangMaModel;
import com.smarthome.magic.util.AlertUtil;
import com.smarthome.magic.util.Tools;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class TuiGuangMaActivity extends BaseActivity {

    @BindView(R.id.iv_erweima)
    ImageView ivErweima;
    @BindView(R.id.tv_yonghu_yaoqing)
    TextView tvYonghuYaoqing;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.iv_4)
    ImageView iv4;
    @BindView(R.id.iv_5)
    ImageView iv5;
    @BindView(R.id.iv_6)
    ImageView iv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_tui_guang_ma);
    //    initToolbar();
        getNet();
    }


    @Override
    public int getContentViewResId() {
        return R.layout.activity_tui_guang_ma;
    }

    Response<AppResponse<TuiGuangMaModel.DataBean>> response;

    private void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04341");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(TuiGuangMaActivity.this).getAppToken());
        // map.put("shop_product_id", productId);
        //map.put("wares_id", warseId);

        Log.i("taoken_gg", UserManager.getManager(TuiGuangMaActivity.this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<TuiGuangMaModel.DataBean>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<TuiGuangMaModel.DataBean>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<TuiGuangMaModel.DataBean>> response) {
                        TuiGuangMaActivity.this.response = response;
                        Bitmap b = BitmapFactory.decodeResource(TuiGuangMaActivity.this.getResources(), R.drawable.juyijia_logo);
                        Bitmap bitmap = Tools.createQRImage(TuiGuangMaActivity.this, response.body().data.get(0).getReferral_code_url(), b);
                        ivErweima.setImageBitmap(bitmap);

                        tvYonghuYaoqing.setText("邀请码：" + response.body().data.get(0).getInvitation_code() + "(点击复制)");

                        tvYonghuYaoqing.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ClipboardManager copy = (ClipboardManager) TuiGuangMaActivity.this
                                        .getSystemService(Context.CLIPBOARD_SERVICE);
                                copy.setText(response.body().data.get(0).getInvitation_code());
                                UIHelper.ToastMessage(TuiGuangMaActivity.this, "复制成功");
                            }
                        });


                        if (response.body().data.get(0).getDisplay().equals("0")) {
                            tv_rightTitle.setVisibility(View.VISIBLE);
                            tv_rightTitle.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showInput();
                                }
                            });
                        } else {
                            tv_rightTitle.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onError(Response<AppResponse<TuiGuangMaModel.DataBean>> response) {
                        AlertUtil.t(TuiGuangMaActivity.this, response.getException().getMessage());
                    }
                });
    }


    private void getNet_butian(String et) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "04343");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(TuiGuangMaActivity.this).getAppToken());
        // map.put("shop_product_id", productId);
        //map.put("wares_id", warseId);
        map.put("invitation_code", et);

        Log.i("taoken_gg", UserManager.getManager(TuiGuangMaActivity.this).getAppToken());
        Gson gson = new Gson();
        OkGo.<AppResponse<Object>>post(Urls.SERVER_URL + "shop_new/app/user")
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<Object>>() {
                    @Override
                    public void onSuccess(final Response<AppResponse<Object>> response) {
                        UIHelper.ToastMessage(TuiGuangMaActivity.this, "补填成功");

                        tv_rightTitle.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Response<AppResponse<Object>> response) {
                        AlertUtil.t(TuiGuangMaActivity.this, response.getException().getMessage());
                    }
                });
    }

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TuiGuangMaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private AlertDialog.Builder builder;

    /**
     * 一个输入框的 dialog
     */
    private void showInput() {
        final EditText editText = new EditText(this);
        builder = new AlertDialog.Builder(this).setTitle("邀请码设置").setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getNet_butian(editText.getText().toString());
                    }
                });
        builder.create().show();
    }

    @Override
    public boolean showToolBar() {
        return true;
    }


    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("邀请码");
        tv_title.setTextSize(17);

        tv_title.setTextColor(getResources().getColor(R.color.black));
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        tv_rightTitle.setText("补填");




        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }
}
