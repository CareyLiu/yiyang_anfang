package com.yiyang.cn.activity.a_yiyang.activity.jigou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.adapter.JiagouRoomAdapter;
import com.yiyang.cn.activity.a_yiyang.model.JigouRoomModel;
import com.yiyang.cn.app.BaseActivity;
import com.yiyang.cn.dialog.newdia.TishiDialog;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JigouDetailsActivity extends BaseActivity {

    @BindView(R.id.rv_room)
    RecyclerView rv_room;
    @BindView(R.id.bt_yuyue)
    Button bt_yuyue;

    private List<JigouRoomModel> roomModels = new ArrayList<>();
    private JiagouRoomAdapter roomAdapter;

    @Override
    public int getContentViewResId() {
        return R.layout.yiyang_act_jigouyanglao_details;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JigouDetailsActivity.class);
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
        tv_title.setText("机构详情");
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
        roomModels.add(new JigouRoomModel(R.mipmap.yiyang_jigou_room1, "三人间", "三个床位的标准住房...", "￥2500/月"));
        roomModels.add(new JigouRoomModel(R.mipmap.yiyang_jigou_room2, "二人间", "二个床位的标准住房...", "￥3500/月"));
        roomModels.add(new JigouRoomModel(R.mipmap.yiyang_jigou_room3, "单人间", "标准单人间，住房...", "￥4500/月"));
        roomModels.add(new JigouRoomModel(R.mipmap.yiyang_jigou_room4, "套房", "一室一厅一卫", "￥5500/月"));
        roomAdapter = new JiagouRoomAdapter(R.layout.yiyang_item_jigou_room, roomModels);
        rv_room.setLayoutManager(new GridLayoutManager(mContext, 2));
        rv_room.setAdapter(roomAdapter);
        roomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                JigouRoomActivity.actionStart(mContext);
            }
        });
    }


    @OnClick(R.id.bt_yuyue)
    public void onViewClicked() {
        TishiDialog dialog = new TishiDialog(mContext, TishiDialog.TYPE_SUCESS, new TishiDialog.TishiDialogListener() {
            @Override
            public void onClickCancel(View v, TishiDialog dialog) {

            }

            @Override
            public void onClickConfirm(View v, TishiDialog dialog) {

            }

            @Override
            public void onDismiss(TishiDialog dialog) {

            }
        });
        dialog.setTextContent("预约成功");
        dialog.show();
    }
}
