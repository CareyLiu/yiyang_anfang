package com.yiyang.cn.activity.nongye.model;

import java.io.Serializable;

public class DianzishangwuModel implements Serializable {

    private int srcId;
    private String name;
    private String address;
    private String money;

    public DianzishangwuModel(int srcId, String name, String address, String money) {
        this.srcId = srcId;
        this.name = name;
        this.address = address;
        this.money = money;
    }

    public int getSrcId() {
        return srcId;
    }

    public void setSrcId(int srcId) {
        this.srcId = srcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
