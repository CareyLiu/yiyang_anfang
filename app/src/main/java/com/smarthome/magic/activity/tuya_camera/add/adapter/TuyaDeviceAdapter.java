package com.smarthome.magic.activity.tuya_camera.add.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.smarthome.magic.R;
import com.smarthome.magic.activity.tuya_camera.utils.OnItemClickListener;
import com.tuya.smart.sdk.bean.DeviceBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TuyaDeviceAdapter extends RecyclerView.Adapter<TuyaDeviceAdapter.ViewHodler> {
    private List<DeviceBean> deviceBeans;
    private Context context;
    private OnItemClickListener listener;

    public TuyaDeviceAdapter(List<DeviceBean> deviceBeans, Context context) {
        this.deviceBeans = deviceBeans;
        this.context = context;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tuya_device_search, parent, false);
        ViewHodler hodler = new ViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        if (deviceBeans != null && deviceBeans.size() > position) {
            DeviceBean deviceBean = deviceBeans.get(position);
            Glide.with(context).load(deviceBean.getIconUrl()).into(holder.iv_device);
            holder.ll_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null){
                        listener.onItmeCilck(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return deviceBeans.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        private ImageView iv_device;
        private RelativeLayout ll_main;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            iv_device = itemView.findViewById(R.id.iv_device);
            ll_main = itemView.findViewById(R.id.ll_main);
        }
    }
}
