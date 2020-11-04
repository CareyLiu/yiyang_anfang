package com.smarthome.magic.activity.xiupeichang;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smarthome.magic.R;
import com.smarthome.magic.activity.SheBeiSetActivity;
import com.smarthome.magic.activity.tuangou.TuanGouMaiDanActivity;
import com.smarthome.magic.activity.xiupeichang.adapter.XiupeichangFuwuAdapter;
import com.smarthome.magic.activity.xiupeichang.adapter.XiupeichangPingjiaAdapter;
import com.smarthome.magic.activity.xiupeichang.view.XiupeichangTagsView;
import com.smarthome.magic.app.BaseActivity;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class XiupeichangShangActivity extends BaseActivity {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_yingye_time)
    TextView tv_yingye_time;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rating_bar)
    AppCompatRatingBar rating_bar;
    @BindView(R.id.ll_lianxi)
    LinearLayout ll_lianxi;
    @BindView(R.id.tv_juli)
    TextView tv_juli;
    @BindView(R.id.ll_daohang)
    LinearLayout ll_daohang;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.ll_tag)
    LinearLayout ll_tag;
    @BindView(R.id.bt_maidan)
    TextView bt_maidan;
    @BindView(R.id.tv_select_fuwu)
    TextView tv_select_fuwu;
    @BindView(R.id.vv_select_fuwu)
    View vv_select_fuwu;
    @BindView(R.id.rl_select_fuwu)
    RelativeLayout rl_select_fuwu;
    @BindView(R.id.tv_select_pingjia)
    TextView tv_select_pingjia;
    @BindView(R.id.vv_select_pingjia)
    View vv_select_pingjia;
    @BindView(R.id.rl_select_pingjia)
    RelativeLayout rl_select_pingjia;
    @BindView(R.id.rv_fuwu)
    RecyclerView rv_fuwu;
    @BindView(R.id.rv_pingjia)
    RecyclerView rv_pingjia;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_xiupeichang_shangjia;
    }

    @Override
    public boolean showToolBar() {
        return true;
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        tv_title.setText("修配厂");
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
    public void initImmersion() {
        mImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    /**
     * 用于其他Activty跳转到该Activity
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, XiupeichangShangActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();
        addTag();
    }

    private void addTag() {
        List<String> tags = new ArrayList<>();
        tags.add("8.5折优惠");
        tags.add("美食优惠");

        ll_tag.removeAllViews();
        for (int i = 0; i < tags.size(); i++) {
            XiupeichangTagsView tagsView = new XiupeichangTagsView(mContext);
            tagsView.setTag(tags.get(i));
            ll_tag.addView(tagsView);
        }
    }

    private void initAdapter() {
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("");
        strings.add("");
        XiupeichangFuwuAdapter fuwuAdapter = new XiupeichangFuwuAdapter(R.layout.item_xiupeichang_fuwu, strings);
        rv_fuwu.setLayoutManager(new LinearLayoutManager(mContext));
        rv_fuwu.setAdapter(fuwuAdapter);

        List<String> stringsP = new ArrayList<>();
        stringsP.add("");
        stringsP.add("");
        stringsP.add("");
        stringsP.add("");
        XiupeichangPingjiaAdapter pingjiaAdapter = new XiupeichangPingjiaAdapter(R.layout.item_xiupeichang_pingjia, stringsP);
        rv_pingjia.setLayoutManager(new LinearLayoutManager(mContext));
        rv_pingjia.setAdapter(pingjiaAdapter);
    }

    @OnClick({R.id.bt_maidan, R.id.rl_select_fuwu, R.id.rl_select_pingjia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_maidan:
                TuanGouMaiDanActivity.actionStart(mContext, "1", "1");
                break;
            case R.id.rl_select_fuwu:
                clickFuwu();
                break;
            case R.id.rl_select_pingjia:
                clickPingjia();
                break;
        }
    }

    private void clickFuwu() {
        vv_select_fuwu.setVisibility(View.VISIBLE);
        vv_select_pingjia.setVisibility(View.INVISIBLE);

        tv_select_fuwu.setTextColor(Color.parseColor("#FC0100"));
        tv_select_pingjia.setTextColor(Color.parseColor("#333333"));

        rv_fuwu.setVisibility(View.VISIBLE);
        rv_pingjia.setVisibility(View.GONE);
    }

    private void clickPingjia() {
        vv_select_fuwu.setVisibility(View.INVISIBLE);
        vv_select_pingjia.setVisibility(View.VISIBLE);

        tv_select_fuwu.setTextColor(Color.parseColor("#333333"));
        tv_select_pingjia.setTextColor(Color.parseColor("#FC0100"));

        rv_fuwu.setVisibility(View.GONE);
        rv_pingjia.setVisibility(View.VISIBLE);
    }
}
