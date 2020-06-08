package com.smarthome.magic.model;

import java.util.List;

public class TuanGouYouHuiJuanModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"user_agio_id":"447","of_user_id":"379","create_time":"2020-04-08","agio_detail":"麦香村西饼屋专享","residue_times":"10","usable_times":"10","inst_agio_id":"33","agio_title":"40元美食免费券","inst_id":"263","agio_state":"1","user_time":"2019-12-10","agio_moneys":"40"},{"user_agio_id":"446","of_user_id":"379","create_time":"2020-04-08","agio_detail":"麦香村西饼屋专享","residue_times":"30","usable_times":"30","inst_agio_id":"32","agio_title":"20元美食免费券","inst_id":"263","agio_state":"1","user_time":"2019-11-30","agio_moneys":"20"},{"user_agio_id":"445","of_user_id":"379","create_time":"2020-04-08","agio_detail":"麦香村西饼屋专享","residue_times":"60","usable_times":"60","inst_agio_id":"31","agio_title":"50元美食优惠券","inst_id":"263","agio_state":"1","user_time":"2020-12-22","agio_moneys":"50"},{"user_agio_id":"444","of_user_id":"379","create_time":"2020-04-08","agio_detail":"麦香村西饼屋专享","residue_times":"30","usable_times":"30","inst_agio_id":"30","agio_title":"30元美食免费券","inst_id":"263","agio_state":"1","user_time":"2019-11-12","agio_moneys":"30"}]
     */

    private String msg_code;
    private String msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_agio_id : 447
         * of_user_id : 379
         * create_time : 2020-04-08
         * agio_detail : 麦香村西饼屋专享
         * residue_times : 10
         * usable_times : 10
         * inst_agio_id : 33
         * agio_title : 40元美食免费券
         * inst_id : 263
         * agio_state : 1
         * user_time : 2019-12-10
         * agio_moneys : 40
         */

        private String user_agio_id;
        private String of_user_id;
        private String create_time;
        private String agio_detail;
        private String residue_times;
        private String usable_times;
        private String inst_agio_id;
        private String agio_title;
        private String inst_id;
        private String agio_state;
        private String user_time;
        private String agio_moneys;

        public String getUser_agio_id() {
            return user_agio_id;
        }

        public void setUser_agio_id(String user_agio_id) {
            this.user_agio_id = user_agio_id;
        }

        public String getOf_user_id() {
            return of_user_id;
        }

        public void setOf_user_id(String of_user_id) {
            this.of_user_id = of_user_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getAgio_detail() {
            return agio_detail;
        }

        public void setAgio_detail(String agio_detail) {
            this.agio_detail = agio_detail;
        }

        public String getResidue_times() {
            return residue_times;
        }

        public void setResidue_times(String residue_times) {
            this.residue_times = residue_times;
        }

        public String getUsable_times() {
            return usable_times;
        }

        public void setUsable_times(String usable_times) {
            this.usable_times = usable_times;
        }

        public String getInst_agio_id() {
            return inst_agio_id;
        }

        public void setInst_agio_id(String inst_agio_id) {
            this.inst_agio_id = inst_agio_id;
        }

        public String getAgio_title() {
            return agio_title;
        }

        public void setAgio_title(String agio_title) {
            this.agio_title = agio_title;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getAgio_state() {
            return agio_state;
        }

        public void setAgio_state(String agio_state) {
            this.agio_state = agio_state;
        }

        public String getUser_time() {
            return user_time;
        }

        public void setUser_time(String user_time) {
            this.user_time = user_time;
        }

        public String getAgio_moneys() {
            return agio_moneys;
        }

        public void setAgio_moneys(String agio_moneys) {
            this.agio_moneys = agio_moneys;
        }
    }
}
