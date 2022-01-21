package com.yiyang.cn.activity.nongye.model;

public class MiaoqingModel {
    private String name;
    private String type;
    private String id;
    private boolean yichang;

    public MiaoqingModel(String name, String type, String id, boolean yichang) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.yichang = yichang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isYichang() {
        return yichang;
    }

    public void setYichang(boolean yichang) {
        this.yichang = yichang;
    }
}
