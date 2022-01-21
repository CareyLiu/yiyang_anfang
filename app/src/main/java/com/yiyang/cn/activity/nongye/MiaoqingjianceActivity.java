package com.yiyang.cn.activity.nongye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flyco.roundview.RoundRelativeLayout;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.adapter.MiaoqingAdapter;
import com.yiyang.cn.activity.nongye.model.JidiListModel;
import com.yiyang.cn.activity.nongye.model.MiaoqingModel;
import com.yiyang.cn.activity.nongye.view.SelectTabView;
import com.yiyang.cn.app.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MiaoqingjianceActivity extends BaseActivity {


    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.ll_title)
    LinearLayout ll_title;
    @BindView(R.id.rrl_sousuo)
    RoundRelativeLayout rrlSousuo;

    private List<MiaoqingModel> miaoqingModelsZong = new ArrayList<>();
    private List<MiaoqingModel> miaoqingModelsNew = new ArrayList<>();

    private List<JidiListModel> jidiListModels = new ArrayList<>();
    private MiaoqingAdapter miaoqingAdapter;

    private List<SelectTabView> tabViews = new ArrayList<>();
    private List<String> title = new ArrayList<>();

    @Override
    public int getContentViewResId() {
        return R.layout.nongye_act_miaoqingjiance;
    }


    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context, String search) {
        Intent intent = new Intent(context, MiaoqingjianceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("search", search);
        context.startActivity(intent);
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("苗情/病菌监测");
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
        initTitle();
        initMiaoqingModels();
        search(getIntent().getStringExtra("search"));
    }

    private void initTitle() {
        title.add("全部");
        title.add("玉米");
        title.add("土豆");
        title.add("高粱");
        title.add("水稻");
        title.add("大豆");
        title.add("小麦");

        for (int i = 0; i < title.size(); i++) {
            SelectTabView tabView = new SelectTabView(mContext);
            tabView.setTitle(title.get(i));
            if (i == 0) {
                tabView.setSelect(true);
            } else {
                tabView.setSelect(false);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ll_title.addView(tabView, params);
            tabViews.add(tabView);
            int finalI = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickTitle(finalI);
                }
            });
        }
    }

    private void clickTitle(int pos) {
        for (int i = 0; i < tabViews.size(); i++) {
            SelectTabView selectTabView = tabViews.get(i);
            if (i == pos) {
                selectTabView.setSelect(true);
                search(title.get(i));
            } else {
                selectTabView.setSelect(false);
            }
        }
    }

    private void initAdapdter() {
        miaoqingAdapter = new MiaoqingAdapter(R.layout.nongye_item_miaoqingjiance, miaoqingModelsNew);
        rv_content.setLayoutManager(new GridLayoutManager(mContext, 2));
        rv_content.setAdapter(miaoqingAdapter);
        rv_content.setFocusable(false);
    }

    private void initMiaoqingModels() {
        miaoqingModelsZong.add(new MiaoqingModel("农业基地1", "玉米", "000001", true));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地1", "土豆", "000001", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地1", "高粱", "000001", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地1", "水稻", "000001", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地1", "大豆", "000001", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地1", "小麦", "000001", false));

        miaoqingModelsZong.add(new MiaoqingModel("农业基地2", "玉米", "000002", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地2", "土豆", "000002", true));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地2", "高粱", "000002", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地2", "水稻", "000002", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地2", "大豆", "000002", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地2", "小麦", "000002", false));

        miaoqingModelsZong.add(new MiaoqingModel("农业基地3", "玉米", "000003", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地3", "土豆", "000003", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地3", "高粱", "000003", true));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地3", "水稻", "000003", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地3", "大豆", "000003", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地3", "小麦", "000003", false));

        miaoqingModelsZong.add(new MiaoqingModel("农业基地4", "玉米", "000004", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地4", "土豆", "000004", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地4", "高粱", "000004", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地4", "水稻", "000004", true));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地4", "大豆", "000004", false));
        miaoqingModelsZong.add(new MiaoqingModel("农业基地4", "小麦", "000004", false));
    }

    @OnClick(R.id.rrl_sousuo)
    public void onViewClicked() {
        MiaoqingSearchActivity.actionStart(mContext);
    }

    private void search(String text) {
        miaoqingModelsNew.clear();
        if (text.equals("全部")) {
            for (int i = 0; i < miaoqingModelsZong.size(); i++) {
                miaoqingModelsNew.add(miaoqingModelsZong.get(i));
            }
        } else {
            for (int i = 0; i < miaoqingModelsZong.size(); i++) {
                MiaoqingModel model = miaoqingModelsZong.get(i);
                String id = model.getId();
                String type = model.getType();
                String name = model.getName();
                if (id.equals(text) || type.equals(text) || name.equals(text)) {
                    miaoqingModelsNew.add(model);
                }
            }
        }
        if (miaoqingAdapter != null) {
            miaoqingAdapter.setNewData(miaoqingModelsNew);
            miaoqingAdapter.notifyDataSetChanged();
        } else {
            initAdapdter();
        }
    }
}