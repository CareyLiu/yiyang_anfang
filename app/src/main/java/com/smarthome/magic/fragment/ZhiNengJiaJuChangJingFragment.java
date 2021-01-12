package com.smarthome.magic.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.zhinengjiaju.ChuangJianZhiNengActivity;
import com.smarthome.magic.activity.zhinengjiaju.ZhiNengChangJingDetailsActivity;
import com.smarthome.magic.adapter.ZhiNengChangJingAdapter;
import com.smarthome.magic.basicmvp.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ZhiNengJiaJuChangJingFragment extends BaseFragment {


    @BindView(R.id.rlv_list)
    RecyclerView rlvList;
    List<String> mDatas = new ArrayList<>();

    ZhiNengChangJingAdapter zhiNengChangJingAdapter;

    @Override
    protected void initLogic() {
        initListView();
    }

    private void initListView() {
        mDatas.add("1");
        mDatas.add("1");
        mDatas.add("1");
        mDatas.add("1");
        mDatas.add("1");
        mDatas.add("1");
        mDatas.add("1");
        mDatas.add("1");
        mDatas.add("2");

        zhiNengChangJingAdapter = new ZhiNengChangJingAdapter(R.layout.item_zhinengjiaju_changjing, mDatas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rlvList.setLayoutManager(linearLayoutManager);
        rlvList.setAdapter(zhiNengChangJingAdapter);
        View view = View.inflate(getActivity(), R.layout.layout_zhinengchangjing_bottom, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuangJianZhiNengActivity.actionStart(getActivity());
            }
        });
        zhiNengChangJingAdapter.addFooterView(view);
        zhiNengChangJingAdapter.setNewData(mDatas);

        zhiNengChangJingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rrl_main:
                        ZhiNengChangJingDetailsActivity.actionStart(getActivity());
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_changjing;
    }

    @Override
    protected void initView(View rootView) {

    }

    public static ZhiNengJiaJuChangJingFragment newInstance(Bundle bundle) {
        ZhiNengJiaJuChangJingFragment fragment = new ZhiNengJiaJuChangJingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
