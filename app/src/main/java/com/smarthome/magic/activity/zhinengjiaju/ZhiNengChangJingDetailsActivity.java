package com.smarthome.magic.activity.zhinengjiaju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flyco.roundview.RoundRelativeLayout;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.ZhiNengChangJingSheBeiAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ZhiNengChangJingDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.rl_yijianzhixing)
    RoundRelativeLayout rlYijianzhixing;
    @BindView(R.id.rrl_shebei)
    RecyclerView rrlShebei;
    List<String> mDatas = new ArrayList<>();

    ZhiNengChangJingSheBeiAdapter zhiNengChangJingSheBeiAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecycleView();
    }

    private void initRecycleView() {
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");
        mDatas.add("");

        zhiNengChangJingSheBeiAdapter = new ZhiNengChangJingSheBeiAdapter(R.layout.item_zhinengchangjing_shebei, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rrlShebei.setLayoutManager(linearLayoutManager);
        rrlShebei.setAdapter(zhiNengChangJingSheBeiAdapter);


        View view = View.inflate(mContext, R.layout.item_zhinengchangjing_shebei_fotter, null);
        zhiNengChangJingSheBeiAdapter.addFooterView(view);
        zhiNengChangJingSheBeiAdapter.setNewData(mDatas);

    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_zhinengchangjing_details;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("场景");
        tv_title.setTextSize(17);
        tv_title.setTextColor(getResources().getColor(R.color.black));
        tv_rightTitle.setVisibility(View.VISIBLE);
        tv_rightTitle.setText("编辑");
        tv_rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage(mContext, "编辑");
            }
        });
        mToolbar.setNavigationIcon(R.mipmap.backbutton);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imm.hideSoftInputFromWindow(findViewById(R.id.cl_layout).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });
    }


    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ZhiNengChangJingDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
