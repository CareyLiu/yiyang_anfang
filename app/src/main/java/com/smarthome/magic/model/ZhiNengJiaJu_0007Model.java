package com.smarthome.magic.model;

import java.util.List;

public class ZhiNengJiaJu_0007Model {
    //0007所需要的字段
    public List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        public String device_name;
        public String cd_decice_ccid;
        public String device_type_pic;
        public String server_id;
    }
}
