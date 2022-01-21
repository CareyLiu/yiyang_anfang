package com.yiyang.cn.activity.nongye.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.yiyang.cn.R;

import androidx.annotation.Nullable;

public class SelectTabView extends LinearLayout {//选择的标题栏

    private Context context;
    private TextView tv_title;
    private View line;

    public SelectTabView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SelectTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public SelectTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_select_tab, this, true);
        tv_title = view.findViewById(R.id.tv_title);
        line = view.findViewById(R.id.line);
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setSelect(boolean isSelect) {
        if (isSelect) {
            tv_title.setTextColor(com.yiyang.cn.util.Y.getColor(R.color.color_nongye_main));
            line.setVisibility(VISIBLE);
        } else {
            tv_title.setTextColor(com.yiyang.cn.util.Y.getColor(R.color.color_6));
            line.setVisibility(INVISIBLE);
        }
    }
}
