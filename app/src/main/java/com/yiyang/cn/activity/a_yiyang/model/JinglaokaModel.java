package com.yiyang.cn.activity.a_yiyang.model;

import java.io.Serializable;

public class JinglaokaModel implements Serializable {
    private String headImgPath;
    private String name;
    private String sex;
    private String card;
    private String phone;
    private String haveCard;

    public String getHeadImgPath() {
        return headImgPath;
    }

    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHaveCard() {
        return haveCard;
    }

    public void setHaveCard(String haveCard) {
        this.haveCard = haveCard;
    }
}
