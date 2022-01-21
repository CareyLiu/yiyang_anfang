package com.yiyang.cn.activity.nongye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiyang.cn.R;
import com.yiyang.cn.activity.nongye.model.ShuyuanDetailsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShuyuanDetailsAdapter extends RecyclerView.Adapter<ShuyuanDetailsAdapter.ShuyuanHolder> {
    private List<ShuyuanDetailsModel> detailsModels;
    private Context context;

    public ShuyuanDetailsAdapter(List<ShuyuanDetailsModel> detailsModels, Context context) {
        this.detailsModels = detailsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ShuyuanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.nongye_item_chanpinshuyuan_details,parent,false);
        ShuyuanHolder holder=new ShuyuanHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShuyuanHolder holder, int position) {
        ShuyuanDetailsModel model = detailsModels.get(position);
        if (position==0){
            holder.view_line_shang.setVisibility(View.INVISIBLE);
            holder.view_line_xia.setVisibility(View.VISIBLE);
        }else if (position==detailsModels.size()-1){
            holder.view_line_shang.setVisibility(View.VISIBLE);
            holder.view_line_xia.setVisibility(View.INVISIBLE);
        }else {
            holder.view_line_shang.setVisibility(View.VISIBLE);
            holder.view_line_xia.setVisibility(View.VISIBLE);
        }

        holder.tv_name.setText(model.getName());
        holder.tv_time.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return detailsModels.size();
    }

    public class ShuyuanHolder extends RecyclerView.ViewHolder {

        private View view_line_shang;
        private View view_line_xia;
        private TextView tv_name;
        private TextView tv_time;

        public ShuyuanHolder(@NonNull View itemView) {
            super(itemView);
            view_line_shang=itemView.findViewById(R.id.view_line_shang);
            view_line_xia=itemView.findViewById(R.id.view_line_xia);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_time=itemView.findViewById(R.id.tv_time);
        }
    }
}
