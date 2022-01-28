package com.yiyang.cn.activity.a_yiyang.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.ChanpinshuyuanActivity;
import com.yiyang.cn.activity.nongye.ChongqingActivity;
import com.yiyang.cn.activity.nongye.DianzishangwuActivity;
import com.yiyang.cn.activity.nongye.JIdiActivity;
import com.yiyang.cn.activity.nongye.MiaoqingjianceActivity;
import com.yiyang.cn.activity.nongye.NongshijihuaActivity;
import com.yiyang.cn.activity.nongye.QixiangjianceActivity;
import com.yiyang.cn.activity.nongye.ShijiankongActivity;
import com.yiyang.cn.activity.nongye.ShishujuActivity;
import com.yiyang.cn.activity.nongye.ZaihaiyujingActivity;
import com.yiyang.cn.activity.nongye.adapter.TabJidiAdapter;
import com.yiyang.cn.activity.nongye.model.TabJidiModel;
import com.yiyang.cn.activity.nongye.utils.OnNongyeClickListener;
import com.yiyang.cn.activity.shengming.SmHomeActivity;
import com.yiyang.cn.activity.shengming.shengmingmodel.CreateSession;
import com.yiyang.cn.activity.shengming.utils.UrlUtils;
import com.yiyang.cn.basicmvp.BaseFragment;
import com.yiyang.cn.callback.JsonCallback;
import com.yiyang.cn.config.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabNongyeFragment extends BaseFragment {


    @BindView(R.id.tv_du)
    TextView tv_du;
    @BindView(R.id.tv_dufuhao)
    TextView tv_dufuhao;
    @BindView(R.id.iv_tianqi)
    ImageView iv_tianqi;
    @BindView(R.id.tv_tianqi)
    TextView tv_tianqi;
    @BindView(R.id.ll_tianqi)
    LinearLayout ll_tianqi;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;
    @BindView(R.id.ll_ssjk)
    LinearLayout llSsjk;
    @BindView(R.id.ll_sssj)
    LinearLayout llSssj;
    @BindView(R.id.ll_mqbj)
    LinearLayout llMqbj;
    @BindView(R.id.ll_cqjc)
    LinearLayout llCqjc;
    @BindView(R.id.ll_zqjc)
    LinearLayout llZqjc;
    @BindView(R.id.ll_qxhj)
    LinearLayout llQxhj;
    @BindView(R.id.ll_ncpsy)
    LinearLayout llNcpsy;
    @BindView(R.id.ll_dzsw)
    LinearLayout llDzsw;
    @BindView(R.id.ll_tjc)
    LinearLayout llTjc;
    @BindView(R.id.ll_nongshijihua)
    LinearLayout llNongshijihua;


    private List<TabJidiModel> jidiModels = new ArrayList<>();
    private TabJidiAdapter jidiAdapter;

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.yiyang_frag_tab_nongye;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        initAdapter();
    }

    private void initAdapter() {
        jidiModels.add(new TabJidiModel("玉米基地", "10"));
        jidiModels.add(new TabJidiModel("土豆基地", "11"));
        jidiModels.add(new TabJidiModel("高粱基地", "12"));
        jidiModels.add(new TabJidiModel("水稻基地", "13"));
        jidiModels.add(new TabJidiModel("大豆基地", "14"));
        jidiModels.add(new TabJidiModel("小麦基地", "15"));

        jidiAdapter = new TabJidiAdapter(jidiModels, getContext());
        rv_content.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rv_content.setAdapter(jidiAdapter);
        jidiAdapter.setListener(new OnNongyeClickListener() {
            @Override
            public void onClick(int pos) {
                jidiAdapter.setCount(pos);
                JIdiActivity.actionStart(getContext());
            }
        });
    }

    @OnClick({R.id.ll_nongshijihua, R.id.ll_ssjk, R.id.ll_sssj, R.id.ll_mqbj, R.id.ll_cqjc, R.id.ll_zqjc, R.id.ll_qxhj, R.id.ll_ncpsy, R.id.ll_dzsw, R.id.ll_tjc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_ssjk:
                ShijiankongActivity.actionStart(getContext());
                break;
            case R.id.ll_sssj:
                ShishujuActivity.actionStart(getContext(), "实时数据");
                break;
            case R.id.ll_mqbj:
                MiaoqingjianceActivity.actionStart(getContext(), "全部");
                break;
            case R.id.ll_cqjc:
                ChongqingActivity.actionStart(getContext());
                break;
            case R.id.ll_zqjc:
                ZaihaiyujingActivity.actionStart(getContext());
                break;
            case R.id.ll_qxhj:
                QixiangjianceActivity.actionStart(getContext());
                break;
            case R.id.ll_ncpsy:
                ChanpinshuyuanActivity.actionStart(getContext());
                break;
            case R.id.ll_dzsw:
                DianzishangwuActivity.actionStart(getContext());
                break;
            case R.id.ll_tjc:
                ShishujuActivity.actionStart(getContext(), "碳监测");
                break;
            case R.id.ll_nongshijihua:
                NongshijihuaActivity.actionStart(getContext());
//                getNet();
                break;
        }
    }
}
