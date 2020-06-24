package com.smarthome.magic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.ZhiNengFamilyManageAdapter;
import com.smarthome.magic.adapter.ZhiNengFamilyManageDetailAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.ConstanceValue;
import com.smarthome.magic.app.Notice;
import com.smarthome.magic.baseadapter.baserecyclerviewadapterhelper.BaseQuickAdapter;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_Success;
import com.smarthome.magic.dialog.ZhiNengFamilyAddDIalog;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengFamilyEditBean;
import com.smarthome.magic.model.ZhiNengFamilyMAnageDetailBean;
import com.smarthome.magic.model.ZhiNengHomeListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengFamilyManageDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_family_name)
    RelativeLayout rl_family_name;
    @BindView(R.id.tv_family_name)
    TextView tv_family_name;
    @BindView(R.id.rl_family_address)
    RelativeLayout rl_family_address;
    @BindView(R.id.tv_family_address)
    TextView tv_family_address;
    @BindView(R.id.tv_family_device_num)
    TextView tv_family_device_num;
    @BindView(R.id.rl_family_room_num)
    RelativeLayout rl_family_room_num;
    @BindView(R.id.tv_family_room_num)
    TextView tv_family_room_num;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_family_delete)
    TextView tv_family_delete;
    private LinearLayout ll_add_share;
    private Context context = ZhiNengFamilyManageDetailActivity.this;
    private ZhiNengFamilyMAnageDetailBean.DataBean dataBean;
    private List<ZhiNengFamilyMAnageDetailBean.DataBean.MemberBean> memberBeans = new ArrayList<>();
    private ZhiNengFamilyManageDetailAdapter zhiNengFamilyManageDetailAdapter;
    private View footerView;
    private String family_id = "";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhineng_family_manage_detail;
    }

    @Override
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightMode(this);
        initToolbar();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        family_id = getIntent().getStringExtra("family_id");
        if (family_id == null) {
            family_id = "";
        }
        getnet();
    }

    private void initView() {
        footerView = LayoutInflater.from(context).inflate(R.layout.activity_zhineng_family_manage_footer, null);
        ll_add_share = footerView.findViewById(R.id.ll_add_share);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        zhiNengFamilyManageDetailAdapter = new ZhiNengFamilyManageDetailAdapter(R.layout.item_zhineng_family_manage_detail, memberBeans);
        recyclerView.setAdapter(zhiNengFamilyManageDetailAdapter);
        rl_family_name.setOnClickListener(this);
        rl_family_address.setOnClickListener(this);
        rl_family_room_num.setOnClickListener(this);
        tv_family_delete.setOnClickListener(this);
        ll_add_share.setOnClickListener(this);
        zhiNengFamilyManageDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ZhiNengFamilyMAnageDetailBean.DataBean.MemberBean memberBean = (ZhiNengFamilyMAnageDetailBean.DataBean.MemberBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("member_type", dataBean.getMember_type());
                bundle.putString("family_id", dataBean.getFamily_id());
                bundle.putParcelable("member", memberBean);
                startActivity(new Intent(context, ZhiNengFamilyMemberDetailActivity.class).putExtras(bundle));
            }
        });
        _subscriptions.add(toObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Notice>() {
            @Override
            public void call(Notice message) {
                if (message.type == ConstanceValue.MSG_FAMILY_MANAGE_CHANGENAME) {
                    changeFamily(message.content.toString());
                }
            }
        }));
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("家庭管理");
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_family_name:
                ZhiNengFamilyAddDIalog zhiNengFamilyAddDIalog = new ZhiNengFamilyAddDIalog(context, ConstanceValue.MSG_FAMILY_MANAGE_CHANGENAME);
                zhiNengFamilyAddDIalog.show();
                break;
            case R.id.rl_family_address:
                Toast.makeText(mContext, "开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_family_room_num:
                Bundle bundle1 = new Bundle();
                bundle1.putString("family_id", family_id);
                bundle1.putString("member_type", dataBean.getMember_type());
                startActivity(new Intent(context, ZhiNengRoomManageActivity.class).putExtras(bundle1));
                break;
            case R.id.ll_add_share:
                Bundle bundle = new Bundle();
                bundle.putString("family_id", family_id);
                startActivity(new Intent(context, ZhiNengFamilyAddShareActivity.class).putExtras(bundle));
                break;
            case R.id.tv_family_delete:
                MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengFamilyManageDetailActivity.this,
                        "提示", "确定要删除该家庭吗？", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {

                    }

                    @Override
                    public void clickRight() {
                        deleteFamily();
                    }
                });
                myCarCaoZuoDialog_caoZuoTIshi.show();
                break;
        }
    }

    private void getnet() {
        //访问网络获取数据 下面的列表数据
        Map<String, String> map = new HashMap<>();
        map.put("code", "16015");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("family_id", family_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengFamilyMAnageDetailBean.DataBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyMAnageDetailBean.DataBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyMAnageDetailBean.DataBean>> response) {
                        dataBean = response.body().data.get(0);
                        tv_family_name.setText(dataBean.getFamily_name());
                        tv_family_device_num.setText(dataBean.getDevice_num() + "个设备");
                        tv_family_room_num.setText(dataBean.getRoom_num() + "个房间");
                        if (dataBean.getMember_type().equals("1")) {
                            tv_family_delete.setText("删除家庭");
                            if (zhiNengFamilyManageDetailAdapter.getFooterViewsCount() == 0) {
                                zhiNengFamilyManageDetailAdapter.addFooterView(footerView);
                            }
                        } else {
                            tv_family_delete.setText("退出家庭");
                        }
                        memberBeans.clear();
                        memberBeans.addAll(dataBean.getMember());
                        zhiNengFamilyManageDetailAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 修改家庭名字
     */
    private void changeFamily(String family_name) {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16012");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("family_id", family_id);
        map.put("family_name", family_name);
        map.put("old_name", dataBean.getFamily_name());
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        if (response.body().msg.equals("ok")) {
                            tv_family_name.setText(family_name);
                            MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(ZhiNengFamilyManageDetailActivity.this,
                                    "成功", "修改成功", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {

                                }
                            });
                            dialog_success.show();
                        }
                    }

                    @Override
                    public void onError(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        String str = response.getException().getMessage();
                        Log.i("cuifahuo", str);
                        String[] str1 = str.split("：");
                        if (str1.length == 3) {
                            MyCarCaoZuoDialog_CaoZuoTIshi myCarCaoZuoDialog_caoZuoTIshi = new MyCarCaoZuoDialog_CaoZuoTIshi(context,
                                    "提示", str1[2], "知道了", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {

                                }
                            });
                            myCarCaoZuoDialog_caoZuoTIshi.show();
                        }
                    }
                });
    }

    /**
     * 退出/删除家庭
     */
    private void deleteFamily() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16019");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("family_id", family_id);
        Gson gson = new Gson();
        Log.e("map_data", gson.toJson(map));
        OkGo.<AppResponse<ZhiNengFamilyEditBean>>post(ZHINENGJIAJU)
                .tag(this)//
                .upJson(gson.toJson(map))
                .execute(new JsonCallback<AppResponse<ZhiNengFamilyEditBean>>() {
                    @Override
                    public void onSuccess(Response<AppResponse<ZhiNengFamilyEditBean>> response) {
                        if (response.body().msg.equals("ok")) {
                            String title = "";
                            if (dataBean.getMember_type().equals("1")) {
                                title = "删除家庭";
                            } else {
                                title = "退出家庭";
                            }
                            MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(ZhiNengFamilyManageDetailActivity.this,
                                    "成功", title, new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
                                @Override
                                public void clickLeft() {

                                }

                                @Override
                                public void clickRight() {
                                    finish();
                                }
                            });
                            dialog_success.show();
                        }
                    }
                });
    }
}
