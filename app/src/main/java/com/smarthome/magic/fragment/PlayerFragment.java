package com.smarthome.magic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarthome.magic.R;

import java.util.Observable;
import java.util.Observer;


/**
 * Created by Administrator on 2018/3/29 0029.
 *
 */

public class PlayerFragment extends BaseFragment implements Observer {
    private static final String TAG = "HomeFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        view.setClickable(true);// 防止点击穿透，底层的fragment响应上层点击触摸事件

        initView(view);
        initData();
        return view;
    }

    public void initData() {


    }

    private void initView(View view) {


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void update(Observable o, Object arg) {

        if (arg.equals("update")) {
            initData();
        }
    }


}
