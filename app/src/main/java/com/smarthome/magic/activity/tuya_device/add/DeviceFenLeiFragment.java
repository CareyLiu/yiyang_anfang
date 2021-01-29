package com.smarthome.magic.activity.tuya_device.add;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.shuinuan.Y;
import com.smarthome.magic.activity.tuya_device.add.model.DeviceListModel;
import com.smarthome.magic.activity.zijian_shangcheng.FenLeiThirdActivity;
import com.smarthome.magic.adapter.FenLeiRightAdapter;
import com.smarthome.magic.basicmvp.BaseFragment;
import com.smarthome.magic.model.FenLeiContentModel;
import com.smarthome.magic.model.ZiJianFenLeiBean;
import com.smarthome.magic.project_A.zijian_interface.FenLeiContenInterface;
import com.smarthome.magic.util.GridSectionAverageGapItemDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DeviceFenLeiFragment extends BaseFragment implements FenLeiContenInterface {
    private List<DeviceListModel.DataBean.ItemBeanX> itemBeanXES = new ArrayList<>();
    private RecyclerView rlvList;
    private FenLeiRightAdapter fenLeiRightAdapter;
    private List<FenLeiContentModel> mDatas;

    // boolean flag = true;// true 是第一个 false 不是第一个
    private String one_item;
    private String two_item;

    @Override
    public void getNet() {
        //无需访问网络
        Bundle bundle = getArguments();
        itemBeanXES = (List<DeviceListModel.DataBean.ItemBeanX>) bundle.getSerializable("info");
        one_item = bundle.getString("one_item");
        if (itemBeanXES.size() > 0) {
            mDatas = new ArrayList<>();
            for (int i = 0; i < itemBeanXES.size(); i++) {
                FenLeiContentModel fenLeiContentModel = new FenLeiContentModel(true, itemBeanXES.get(i).getItem_name());
                fenLeiContentModel.one_item = one_item;
                fenLeiContentModel.two_item = itemBeanXES.get(i).getItem_id();
                two_item = itemBeanXES.get(i).getItem_id();
                mDatas.add(fenLeiContentModel);
                for (int j = 0; j < itemBeanXES.get(i).getItem().size(); j++) {
                    DeviceListModel.DataBean.ItemBeanX.ItemBean itemBean = itemBeanXES.get(i).getItem().get(j);
                    FenLeiContentModel contentModel = new FenLeiContentModel(false, "");
                    contentModel.setImg_url(itemBean.getImg_url());
                    contentModel.setItem_id(itemBean.getItem_id());
                    contentModel.setItem_name(itemBean.getItem_name());
                    contentModel.two_item = two_item;
                    contentModel.one_item = one_item;

                    mDatas.add(contentModel);
                }
            }
            //有数据
            loadList();
        } else {
            //无数据
        }
    }

    @Override
    public void loadList() {
        fenLeiRightAdapter = new FenLeiRightAdapter(mDatas);
        rlvList.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rlvList.addItemDecoration(new GridSectionAverageGapItemDecoration(10, 10, 20, 15));
        rlvList.setAdapter(fenLeiRightAdapter);

        fenLeiRightAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.constrain:
                        FenLeiContentModel fenLeiContentModel = (FenLeiContentModel) adapter.getData().get(position);
                        FenLeiThirdActivity.actionStart(getActivity(), fenLeiContentModel);
                        break;
                }
            }
        });
    }

    @Override
    public void getCanshu() {

    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_fenleiright_content;
    }

    @Override
    protected void initView(View rootView) {
        rlvList = rootView.findViewById(R.id.rlv_list);
        getNet();
    }

    @Override
    public boolean onBackPressedSupport() {
        getActivity().finish();
        return super.onBackPressedSupport();
    }
}
