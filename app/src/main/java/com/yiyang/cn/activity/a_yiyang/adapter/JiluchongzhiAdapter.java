package com.yiyang.cn.activity.a_yiyang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yiyang.cn.R;

import java.util.List;

public class JiluchongzhiAdapter extends BaseAdapter {
    private Context context;
    private List list;

    public JiluchongzhiAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.yiyang_item_jinglaoka_chongzhi, null);
        return view;
    }
}
