package com.yiyang.cn.model;

import java.util.List;

public class AddressListModel {
    /**
     * available_balance : 10.00
     * msg_code : 0000
     * msg : ok
     * row_num : 2
     * data : [{"area_name":"南关区","of_user_id":"455","create_time":"2020-04-15","user_name":"孙海龙","addr_all":"吉林省长春市南关区学府绿景苑5栋2单元502","area_id":"230103","province_name":"吉林省","users_addr_id":"184","addr_check":"1","user_addr_state":"1","city_name":"长春市","province_id":"23","x":"","user_phone":"17645185187","y":"","id":"184","addr":"学府绿景苑5栋2单元502","city_id":"2301"},{"area_name":"南关区","of_user_id":"455","create_time":"2019-05-16","user_name":"海龙","addr_all":"吉林省长春市南关区电缆街219号，神灯科技有限公司，209软件研发部","area_id":"230103","province_name":"吉林省","users_addr_id":"134","addr_check":"2","user_addr_state":"1","city_name":"长春市","province_id":"23","x":"","user_phone":"17645185187","y":"","id":"134","addr":"电缆街219号，神灯科技有限公司，209软件研发部","city_id":"2301"}]
     */

    private String available_balance;
    private String msg_code;
    private String msg;
    private String row_num;
    private List<DataBean> data;

    public String getAvailable_balance() {
        return available_balance;
    }

    public void setAvailable_balance(String available_balance) {
        this.available_balance = available_balance;
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
         * area_name : 南关区
         * of_user_id : 455
         * create_time : 2020-04-15
         * user_name : 孙海龙
         * addr_all : 吉林省长春市南关区学府绿景苑5栋2单元502
         * area_id : 230103
         * province_name : 吉林省
         * users_addr_id : 184
         * addr_check : 1
         * user_addr_state : 1
         * city_name : 长春市
         * province_id : 23
         * x :
         * user_phone : 17645185187
         * y :
         * id : 184
         * addr : 学府绿景苑5栋2单元502
         * city_id : 2301
         */

        private String area_name;
        private String of_user_id;
        private String create_time;
        private String user_name;
        private String addr_all;
        private String area_id;
        private String province_name;
        private String users_addr_id;
        private String addr_check;
        private String user_addr_state;
        private String city_name;
        private String province_id;
        private String x;
        private String user_phone;
        private String y;
        private String id;
        private String addr;
        private String city_id;




        public String getAvailable_balance() {
            return available_balance;
        }

        public void setAvailable_balance(String available_balance) {
            this.available_balance = available_balance;
        }

        private String available_balance;


        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAddr_all() {
            return addr_all;
        }

        public void setAddr_all(String addr_all) {
            this.addr_all = addr_all;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getUsers_addr_id() {
            return users_addr_id;
        }

        public void setUsers_addr_id(String users_addr_id) {
            this.users_addr_id = users_addr_id;
        }

        public String getAddr_check() {
            return addr_check;
        }

        public void setAddr_check(String addr_check) {
            this.addr_check = addr_check;
        }

        public String getUser_addr_state() {
            return user_addr_state;
        }

        public void setUser_addr_state(String user_addr_state) {
            this.user_addr_state = user_addr_state;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }
    }
}
