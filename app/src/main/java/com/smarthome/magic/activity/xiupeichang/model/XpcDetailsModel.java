package com.smarthome.magic.activity.xiupeichang.model;

import java.util.List;

public class XpcDetailsModel {


    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"week_five":"","lunboList":[{"inst_state":"1","img_order":"1","create_time":"2020-11-05","img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=11690","inst_id":"229","img_id":"11690","inst_img_id":"148"}],"inst_photo_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=11677","meter":"9.8km","inst_number":"5.0","inst_number_name":"5.0分","week_two":"","addr_all":"黑龙江省哈尔滨市道里区抚顺街道荣盛汽配","average_cost":"","inst_phone":"15244771234","value_1":"7","week_four":"","week_one":"","week_three":"","week_seven":"","x":"45.755646","y":"126.622154","inst_id":"229","inst_name":"荣盛汽配","time":"","week_six":""}]
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
         * week_five :
         * lunboList : [{"inst_state":"1","img_order":"1","create_time":"2020-11-05","img_url":"https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=11690","inst_id":"229","img_id":"11690","inst_img_id":"148"}]
         * inst_photo_url : https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=11677
         * meter : 9.8km
         * inst_number : 5.0
         * inst_number_name : 5.0分
         * week_two :
         * addr_all : 黑龙江省哈尔滨市道里区抚顺街道荣盛汽配
         * average_cost :
         * inst_phone : 15244771234
         * value_1 : 7
         * week_four :
         * week_one :
         * week_three :
         * week_seven :
         * x : 45.755646
         * y : 126.622154
         * inst_id : 229
         * inst_name : 荣盛汽配
         * time :
         * week_six :
         */

        private String week_five;
        private String inst_photo_url;
        private String meter;
        private String inst_number;
        private String inst_number_name;
        private String week_two;
        private String addr_all;
        private String average_cost;
        private String inst_phone;
        private String value_1;
        private String week_four;
        private String week_one;
        private String week_three;
        private String week_seven;
        private String x;
        private String y;
        private String inst_id;
        private String inst_name;
        private String time;
        private String week_six;
        private List<LunboListBean> lunboList;

        public String getWeek_five() {
            return week_five;
        }

        public void setWeek_five(String week_five) {
            this.week_five = week_five;
        }

        public String getInst_photo_url() {
            return inst_photo_url;
        }

        public void setInst_photo_url(String inst_photo_url) {
            this.inst_photo_url = inst_photo_url;
        }

        public String getMeter() {
            return meter;
        }

        public void setMeter(String meter) {
            this.meter = meter;
        }

        public String getInst_number() {
            return inst_number;
        }

        public void setInst_number(String inst_number) {
            this.inst_number = inst_number;
        }

        public String getInst_number_name() {
            return inst_number_name;
        }

        public void setInst_number_name(String inst_number_name) {
            this.inst_number_name = inst_number_name;
        }

        public String getWeek_two() {
            return week_two;
        }

        public void setWeek_two(String week_two) {
            this.week_two = week_two;
        }

        public String getAddr_all() {
            return addr_all;
        }

        public void setAddr_all(String addr_all) {
            this.addr_all = addr_all;
        }

        public String getAverage_cost() {
            return average_cost;
        }

        public void setAverage_cost(String average_cost) {
            this.average_cost = average_cost;
        }

        public String getInst_phone() {
            return inst_phone;
        }

        public void setInst_phone(String inst_phone) {
            this.inst_phone = inst_phone;
        }

        public String getValue_1() {
            return value_1;
        }

        public void setValue_1(String value_1) {
            this.value_1 = value_1;
        }

        public String getWeek_four() {
            return week_four;
        }

        public void setWeek_four(String week_four) {
            this.week_four = week_four;
        }

        public String getWeek_one() {
            return week_one;
        }

        public void setWeek_one(String week_one) {
            this.week_one = week_one;
        }

        public String getWeek_three() {
            return week_three;
        }

        public void setWeek_three(String week_three) {
            this.week_three = week_three;
        }

        public String getWeek_seven() {
            return week_seven;
        }

        public void setWeek_seven(String week_seven) {
            this.week_seven = week_seven;
        }

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        public String getInst_id() {
            return inst_id;
        }

        public void setInst_id(String inst_id) {
            this.inst_id = inst_id;
        }

        public String getInst_name() {
            return inst_name;
        }

        public void setInst_name(String inst_name) {
            this.inst_name = inst_name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWeek_six() {
            return week_six;
        }

        public void setWeek_six(String week_six) {
            this.week_six = week_six;
        }

        public List<LunboListBean> getLunboList() {
            return lunboList;
        }

        public void setLunboList(List<LunboListBean> lunboList) {
            this.lunboList = lunboList;
        }

        public static class LunboListBean {
            /**
             * inst_state : 1
             * img_order : 1
             * create_time : 2020-11-05
             * img_url : https://test.hljsdkj.com/Frame/uploadFile/showImg?file_id=11690
             * inst_id : 229
             * img_id : 11690
             * inst_img_id : 148
             */

            private String inst_state;
            private String img_order;
            private String create_time;
            private String img_url;
            private String inst_id;
            private String img_id;
            private String inst_img_id;

            public String getInst_state() {
                return inst_state;
            }

            public void setInst_state(String inst_state) {
                this.inst_state = inst_state;
            }

            public String getImg_order() {
                return img_order;
            }

            public void setImg_order(String img_order) {
                this.img_order = img_order;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getInst_id() {
                return inst_id;
            }

            public void setInst_id(String inst_id) {
                this.inst_id = inst_id;
            }

            public String getImg_id() {
                return img_id;
            }

            public void setImg_id(String img_id) {
                this.img_id = img_id;
            }

            public String getInst_img_id() {
                return inst_img_id;
            }

            public void setInst_img_id(String inst_img_id) {
                this.inst_img_id = inst_img_id;
            }
        }
    }
}
