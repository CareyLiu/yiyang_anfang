package com.smarthome.magic.activity.tongcheng58.model;

import java.util.List;

public class TcWodeModel {


    /**
     * next : 0
     * msg_code : 0000
     * msg : ok
     * row_num : 2
     * data : [{"infor_list":[{"ir_title":"咕哒之家","ir_audit_state":"1","ir_img_url":"http://yjn-znjj.oss-cn-hangzhou.aliyuncs.com/20210329165142000001.jpg","ir_audit_state_name":"审核中","ir_id":"16"},{"ir_title":"国尚公司","ir_audit_state":"2","ir_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016","ir_audit_state_name":"已发布","ir_id":"12"}],"user_name":"咕哒王","user_phone":"15114684672"}]
     */

    private String next;
    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

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
        /**
         * infor_list : [{"ir_title":"咕哒之家","ir_audit_state":"1","ir_img_url":"http://yjn-znjj.oss-cn-hangzhou.aliyuncs.com/20210329165142000001.jpg","ir_audit_state_name":"审核中","ir_id":"16"},{"ir_title":"国尚公司","ir_audit_state":"2","ir_img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=12016","ir_audit_state_name":"已发布","ir_id":"12"}]
         * user_name : 咕哒王
         * user_phone : 15114684672
         */

        private String user_name;
        private String user_phone;
        private List<InforListBean> infor_list;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public List<InforListBean> getInfor_list() {
            return infor_list;
        }

        public void setInfor_list(List<InforListBean> infor_list) {
            this.infor_list = infor_list;
        }

        public static class InforListBean {
            /**
             * ir_title : 咕哒之家
             * ir_audit_state : 1
             * ir_img_url : http://yjn-znjj.oss-cn-hangzhou.aliyuncs.com/20210329165142000001.jpg
             * ir_audit_state_name : 审核中
             * ir_id : 16
             */

            private String ir_title;
            private String ir_audit_state;
            private String ir_img_url;
            private String ir_audit_state_name;
            private String ir_id;

            public String getIr_title() {
                return ir_title;
            }

            public void setIr_title(String ir_title) {
                this.ir_title = ir_title;
            }

            public String getIr_audit_state() {
                return ir_audit_state;
            }

            public void setIr_audit_state(String ir_audit_state) {
                this.ir_audit_state = ir_audit_state;
            }

            public String getIr_img_url() {
                return ir_img_url;
            }

            public void setIr_img_url(String ir_img_url) {
                this.ir_img_url = ir_img_url;
            }

            public String getIr_audit_state_name() {
                return ir_audit_state_name;
            }

            public void setIr_audit_state_name(String ir_audit_state_name) {
                this.ir_audit_state_name = ir_audit_state_name;
            }

            public String getIr_id() {
                return ir_id;
            }

            public void setIr_id(String ir_id) {
                this.ir_id = ir_id;
            }
        }
    }
}
