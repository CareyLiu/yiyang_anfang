package com.smarthome.magic.activity.tongcheng58;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.wode_page.AboutUsActivity;
import com.smarthome.magic.adapter.ChooseFuWuAdapter;
import com.smarthome.magic.app.BaseActivity;
import com.smarthome.magic.app.UIHelper;
import com.smarthome.magic.model.FuWuGongZhongBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChooseFuWuGongZhongActivity extends BaseActivity {
    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    ChooseFuWuAdapter chooseFuWuAdapter;

    List<FuWuGongZhongBean> mDatas = new ArrayList<>();

    private int xuanZhongNum = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chooseFuWuAdapter = new ChooseFuWuAdapter(R.layout.item_fuwu_gongzhong, mDatas);

        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
        rlvList.setLayoutManager(manager);

        for (int i = 0; i < 10; i++) {
            FuWuGongZhongBean fuWuGongZhongBean = new FuWuGongZhongBean();
            fuWuGongZhongBean.type = "0";
            mDatas.add(fuWuGongZhongBean);
        }
        rlvList.setAdapter(chooseFuWuAdapter);

        chooseFuWuAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_main:


                        if (mDatas.get(position).type.equals("0")) {
                            if (xuanZhongNum == 5) {
                                UIHelper.ToastMessage(mContext, "只能选择5个项目哦");
                                return;
                            }
                            mDatas.get(position).type = "1";
                            xuanZhongNum = xuanZhongNum + 1;
                        } else {
                            mDatas.get(position).type = "0";
                            xuanZhongNum = xuanZhongNum - 1;
                        }
                        chooseFuWuAdapter.notifyDataSetChanged();
                        break;

                }
            }
        });
    }

    @Override
    public int getContentViewResId() {
        return R.layout.layout_choose_fuwu_gongzhong;
    }

    @Override
    public boolean showToolBarLine() {
        return true;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("选择服务工种");
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

    /**
     * 用于其他Activty跳转到该Activity
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChooseFuWuGongZhongActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
