package com.yiyang.cn.activity.nongye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.model.TabJidiModel;
import com.yiyang.cn.activity.nongye.utils.OnNongyeClickListener;
import com.yiyang.cn.util.Y;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TabJidiAdapter extends RecyclerView.Adapter<TabJidiAdapter.TabJidiHodler> {

    private List<TabJidiModel> models;
    private Context context;
    private int count;
    private OnNongyeClickListener listener;

    public TabJidiAdapter(List<TabJidiModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    public void setListener(OnNongyeClickListener listener) {
        this.listener = listener;
    }

    public void setCount(int count) {
        this.count = count;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TabJidiHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nongye_item_tabjidi, parent, false);
        TabJidiHodler hodler = new TabJidiHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull TabJidiHodler holder, int position) {
        if (models != null && models.size() > position) {
            TabJidiModel tabJidiModel = models.get(position);
            if (position == 0) {
                holder.view_zhanwei.setVisibility(View.VISIBLE);
            } else {
                holder.view_zhanwei.setVisibility(View.GONE);
            }

            if (count == position) {
                holder.ll_main.setBackgroundResource(R.drawable.yiyang_bg_nongye_radius_8);
                holder.tv_shebei_num.setBackgroundResource(R.drawable.yiyang_bg_white_radius_2);
                holder.tv_jidi_name.setTextColor(Y.getColor(R.color.white));
                holder.tv_jidi_state.setTextColor(Y.getColor(R.color.white));
                holder.tv_shebei_num.setTextColor(Y.getColor(R.color.color_nongye_main));
                holder.iv_jidi_icon.setImageResource(R.mipmap.nongye_tab_jidi_sel);
            } else {
                holder.ll_main.setBackgroundResource(R.drawable.yiyang_bg_white_radius_8);
                holder.tv_shebei_num.setBackgroundResource(R.drawable.yiyang_bg_nongye_radius_2);
                holder.tv_jidi_name.setTextColor(Y.getColor(R.color.color_3));
                holder.tv_jidi_state.setTextColor(Y.getColor(R.color.color_3));
                holder.tv_shebei_num.setTextColor(Y.getColor(R.color.white));
                holder.iv_jidi_icon.setImageResource(R.mipmap.nongye_tab_jidi_nor);
            }

            holder.tv_jidi_name.setText(tabJidiModel.getName());
            holder.tv_shebei_num.setText(tabJidiModel.getNum());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class TabJidiHodler extends RecyclerView.ViewHolder {
        private ImageView iv_jidi_icon;
        private TextView tv_jidi_name;
        private TextView tv_shebei_num;
        private TextView tv_jidi_state;
        private View view_zhanwei;
        private View ll_main;


        public TabJidiHodler(@NonNull View itemView) {
            super(itemView);
            iv_jidi_icon = itemView.findViewById(R.id.iv_jidi_icon);
            tv_jidi_name = itemView.findViewById(R.id.tv_jidi_name);
            tv_shebei_num = itemView.findViewById(R.id.tv_shebei_num);
            tv_jidi_state = itemView.findViewById(R.id.tv_jidi_state);
            view_zhanwei = itemView.findViewById(R.id.view_zhanwei);
            ll_main = itemView.findViewById(R.id.ll_main);
        }
    }
}
