package com.smarthome.magic.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.utils.manager.TuyaHomeManager;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.UserManager;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_CaoZuoTIshi;
import com.smarthome.magic.dialog.MyCarCaoZuoDialog_Success;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.ZhiNengFamilyEditBean;
import com.smarthome.magic.model.ZhiNengFamilyMAnageDetailBean;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.bean.MemberBean;
import com.tuya.smart.home.sdk.callback.ITuyaGetMemberListCallback;
import com.tuya.smart.sdk.api.IResultCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.smarthome.magic.get_net.Urls.ZHINENGJIAJU;

public class ZhiNengFamilyMemberDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_delete)
    TextView tv_delete;
    private Context context = ZhiNengFamilyMemberDetailActivity.this;
    private String member_type = "";
    private String member_id = "";
    private String family_id = "";
    private ZhiNengFamilyMAnageDetailBean.DataBean.MemberBean memberBean;
    private String member_phone;
    private long tuya_memberId;
    private String ty_family_id;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_zhineng_family_member_detail;
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
        initMember();
    }

    private void initMember() {
        TuyaHomeSdk.getMemberInstance().queryMemberList(Y.getLong(ty_family_id), new ITuyaGetMemberListCallback() {
            @Override
            public void onSuccess(List<MemberBean> memberBeans) {
                for (int i = 0; i < memberBeans.size(); i++) {
                    MemberBean memberBean = memberBeans.get(i);
                    String account = memberBean.getAccount();
                    Y.e("账号是多少啊啊啊 " + account + "   " + memberBean.getMemberId());
                }
            }

            @Override
            public void onError(String errorCode, String error) {
                Y.e("我失败了么啊啊啊" + error);
            }
        });
    }

    private void initView() {
        tv_delete.setOnClickListener(this);
        member_type = getIntent().getStringExtra("member_type");
        family_id = getIntent().getStringExtra("family_id");
        ty_family_id = getIntent().getStringExtra("ty_family_id");
        memberBean = getIntent().getParcelableExtra("member");
        member_id = memberBean.getMember_id();
        tv_name.setText(memberBean.getUser_name());
        member_phone = memberBean.getMember_phone();
        tuya_memberId = Y.getLong(memberBean.getTy_member_id());
        tv_phone.setText(member_phone);
        tv_type.setText(memberBean.getMember_type_name());
        if (member_type.equals("1")) {
            if (memberBean.getMember_type_name().equals("管理员")) {
                tv_delete.setVisibility(View.GONE);
            } else {
                tv_delete.setVisibility(View.VISIBLE);
            }
        } else {
            tv_delete.setVisibility(View.GONE);
        }
        Y.e("涂鸦成员ID是多少" + tuya_memberId);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("成员信息");
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
            case R.id.tv_delete:
                MyCarCaoZuoDialog_CaoZuoTIshi dialog_success = new MyCarCaoZuoDialog_CaoZuoTIshi(ZhiNengFamilyMemberDetailActivity.this, "提示", "确定删除该成员？", "取消", "确定", new MyCarCaoZuoDialog_CaoZuoTIshi.OnDialogItemClickListener() {
                    @Override
                    public void clickLeft() {

                    }

                    @Override
                    public void clickRight() {
                        deleteMember();
                    }
                });
                dialog_success.show();
                break;
        }
    }

    /**
     * 移除成员
     */
    private void deleteMember() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "16018");
        map.put("key", Urls.key);
        map.put("token", UserManager.getManager(context).getAppToken());
        map.put("member_id", member_id);
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
                            MyCarCaoZuoDialog_Success dialog_success = new MyCarCaoZuoDialog_Success(ZhiNengFamilyMemberDetailActivity.this, "成功", "已成功移除该家庭成员", new MyCarCaoZuoDialog_Success.OnDialogItemClickListener() {
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
                        removeMember(tuya_memberId);
                    }
                });
    }

    private void removeMember(long memberId) {
        TuyaHomeSdk.getMemberInstance().removeMember(memberId, new IResultCallback() {
            @Override
            public void onSuccess() {
                Y.e("删除成功");
            }

            @Override
            public void onError(String code, String error) {
                Y.e("删除失败" + code + "   " + error);
            }
        });
    }
}
