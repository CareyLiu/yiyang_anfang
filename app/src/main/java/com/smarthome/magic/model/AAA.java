package com.smarthome.magic.model;

import java.util.List;

public class AAA {
    /**
     * msg_code : 0000
     * msg : ok
     * row_num : 1
     * data : [{"taocan_list":[{"menu_detail_id":"40","menu_pay":"28.00","menu_text":"发文字呀","menu_count":"1"},{"menu_detail_id":"44","menu_pay":"12.00","menu_text":"QWERTY","menu_count":"1"}]}]
     */

    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

    public String getMsg_code() {
        return msg_code;
    }

    public void setMsg_code(String msg_code) {
        this.msg_code = msg_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRow_num() {
        return row_num;
    }

    public void setRow_num(String row_num) {
        this.row_num = row_num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

    }
}
