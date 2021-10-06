package com.yiyang.cn.activity.a_yiyang.activity.falvfuwu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.adapter.AYiyangAdapter;
import com.yiyang.cn.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class FalvfuwuActivity extends BaseActivity {


    @BindView(R.id.ll_zhaolvshi)
    LinearLayout ll_zhaolvshi;
    @BindView(R.id.ll_falvfuwu)
    LinearLayout ll_falvfuwu;
    @BindView(R.id.ll_mianfeizixun)
    LinearLayout ll_mianfeizixun;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_falvfuwu;
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, FalvfuwuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("法律服务");
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        initAdapter();
    }

    private void initAdapter() {
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        AYiyangAdapter adapter = new AYiyangAdapter(R.layout.yiyang_item_falvfuwu, strings);
        rv_content.setLayoutManager(new LinearLayoutManager(mContext));
        rv_content.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;
                String targetId ="217";
                String instName = "李医生";
                Bundle bundle = new Bundle();
                bundle.putString("dianpuming", instName);
                bundle.putString("inst_accid", targetId);
                bundle.putString("shoptype", "2");
                RongIM.getInstance().startConversation(mContext, conversationType, targetId, instName, bundle);
            }
        });
    }

    @OnClick({R.id.ll_zhaolvshi, R.id.ll_falvfuwu, R.id.ll_mianfeizixun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_zhaolvshi:
                break;
            case R.id.ll_falvfuwu:
                break;
            case R.id.ll_mianfeizixun:
                break;
        }
    }
}
