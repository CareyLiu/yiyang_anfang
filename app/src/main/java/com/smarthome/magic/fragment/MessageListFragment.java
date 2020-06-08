package com.smarthome.magic.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.model.Response;
import com.smarthome.magic.R;
import com.smarthome.magic.adapter.MessageListAdapter;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.callback.JsonCallback;
import com.smarthome.magic.config.AppResponse;
import com.smarthome.magic.config.PreferenceHelper;
import com.smarthome.magic.get_net.Urls;
import com.smarthome.magic.model.MessageListBean;
import com.smarthome.magic.model.MessageModel;
import com.smarthome.magic.util.NetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;


public class MessageListFragment extends BaseFragment {

    private RecyclerView recyclerView;
    List<MessageModel.DataBean> mDatas = new ArrayList<>();
    private MessageListAdapter messageListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void getNet() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "03003");
        map.put("key", Urls.key);
        map.put("token", PreferenceHelper.getInstance(getActivity()).getString("app_token", "0"));
        if (strTitle.equals("全部")) {

        } else if (strTitle.equals("未读")) {
            map.put("notify_read", "1");
        } else if (strTitle.equals("已读")) {
            map.put("notify_read", "2");

        } else if (strTitle.equals("已处理")) {
            map.put("notify_state", "2");
        } else if (strTitle.equals("未处理")) {
            map.put("notify_state", "2");
        }
        NetUtils<MessageModel.DataBean> netUtils = new NetUtils<>();
        netUtils.requestData(map, Urls.MESSAGE_URL, getActivity(), new JsonCallback<AppResponse<MessageModel.DataBean>>() {
            @Override
            public void onSuccess(Response<AppResponse<MessageModel.DataBean>> response) {
                List<MessageModel.DataBean> dataBean = new ArrayList<>();
                dataBean.addAll(response.body().data);
                if (dataBean.size() > 0) {
                    for (int i = 0; i < dataBean.size(); i++) {
                        Log.i("dataBean", dataBean.get(i).getNotify_text());

                    }
                    messageListAdapter.setNewData(dataBean);
                    messageListAdapter.notifyDataSetChanged();


                } else {
                    recyclerView.setVisibility(View.GONE);
                    ivNone.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onError(Response<AppResponse<MessageModel.DataBean>> response) {
                super.onError(response);
            }
        });
    }

    String strTitle;
    ImageView ivNone;

    @Override
    protected void initLogic() {
        Bundle args = getArguments();
        strTitle = args.getString("title");
        getNet();
    }

    RelativeLayout rlMain;
    View view;

    @Override
    protected void immersionInit(ImmersionBar mImmersionBar) {
        mImmersionBar
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    protected boolean immersionEnabled() {
        return true;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.messagelist;
    }

    @Override
    protected void initView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rlMain = rootView.findViewById(R.id.rl_main);
        ivNone = rootView.findViewById(R.id.iv_none);
        view = rootView.findViewById(R.id.view);
        initAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messageListAdapter = new MessageListAdapter(R.layout.item_messagelist, mDatas);
        messageListAdapter.openLoadAnimation();//默认为渐显效果
        recyclerView.setAdapter(messageListAdapter);
    }
}

