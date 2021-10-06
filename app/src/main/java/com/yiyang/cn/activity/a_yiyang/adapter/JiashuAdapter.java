package com.yiyang.cn.activity.a_yiyang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yiyang.cn.R;
import com.yiyang.cn.activity.a_yiyang.model.JiashuModel;
import com.yiyang.cn.inter.OnItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JiashuAdapter extends RecyclerView.Adapter<JiashuAdapter.JiashuViewHolder> {
    private Context context;
    private List<JiashuModel> jiashuModels;
    private OnItemClickListener listener;

    public JiashuAdapter(Context context, List<JiashuModel> jiashuModels) {
        this.context = context;
        this.jiashuModels = jiashuModels;
    }

    public void setJiashuModels(List<JiashuModel> jiashuModels) {
        this.jiashuModels = jiashuModels;
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public JiashuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.yiyang_item_jiashudangan, null, false);
        JiashuViewHolder holder = new JiashuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JiashuViewHolder holder, int position) {
        if (jiashuModels != null && jiashuModels.size() > position) {
            JiashuModel model = jiashuModels.get(position);
            Glide.with(context)
                    .load(model.getHeadImgPath())
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.iv_head);

            holder.tv_name.setText(model.getName());

            if (jiashuModels.size() - 1 == position) {
                holder.view_line.setVisibility(View.GONE);
            } else {
                holder.view_line.setVisibility(View.VISIBLE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(holder.itemView, position);
                    }
                }
            });

            holder.ll_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(holder.ll_main, position);
                    }
                }
            });

            boolean pinggu = model.isPinggu();
            if (pinggu){
                holder.tv_pinggu.setVisibility(View.VISIBLE);
            }else {
                holder.tv_pinggu.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return jiashuModels.size();
    }

    protected class JiashuViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_head;
        private TextView tv_name;
        private TextView tv_pinggu;
        private View view_line;
        private LinearLayout ll_main;

        public JiashuViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_head = itemView.findViewById(R.id.iv_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_pinggu = itemView.findViewById(R.id.tv_pinggu);
            view_line = itemView.findViewById(R.id.view_line);
            ll_main = itemView.findViewById(R.id.ll_main);
        }
    }
}
