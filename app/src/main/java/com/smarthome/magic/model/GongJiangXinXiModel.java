package com.smarthome.magic.model;

import java.util.List;

public class GongJiangXinXiModel {
    /**
     * msg_code : 0000
     * msg : ok
     * data : [{"imgList":[{"ir_img_url":"http://yjn-znjj.oss-cn-hangzhou.aliyuncs.com/20210401112739000001.jpg","create_time":"2021-04-01","ir_rotation_img_id":"60","width":"2236","ir_img_id":"12098","ir_id":"23","height":"2236"}]}]
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
        private List<ImgListBean> imgList;

        public List<ImgListBean> getImgList() {
            return imgList;
        }

        public void setImgList(List<ImgListBean> imgList) {
            this.imgList = imgList;
        }

        public static class ImgListBean {
            /**
             * ir_img_url : http://yjn-znjj.oss-cn-hangzhou.aliyuncs.com/20210401112739000001.jpg
             * create_time : 2021-04-01
             * ir_rotation_img_id : 60
             * width : 2236
             * ir_img_id : 12098
             * ir_id : 23
             * height : 2236
             */

            private String ir_img_url;
            private String create_time;
            private String ir_rotation_img_id;
            private String width;
            private String ir_img_id;
            private String ir_id;
            private String height;

            public String getIr_img_url() {
                return ir_img_url;
            }

            public void setIr_img_url(String ir_img_url) {
                this.ir_img_url = ir_img_url;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getIr_rotation_img_id() {
                return ir_rotation_img_id;
            }

            public void setIr_rotation_img_id(String ir_rotation_img_id) {
                this.ir_rotation_img_id = ir_rotation_img_id;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getIr_img_id() {
                return ir_img_id;
            }

            public void setIr_img_id(String ir_img_id) {
                this.ir_img_id = ir_img_id;
            }

            public String getIr_id() {
                return ir_id;
            }

            public void setIr_id(String ir_id) {
                this.ir_id = ir_id;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }
        }
    }
}
