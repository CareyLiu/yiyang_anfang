package com.yiyang.cn.activity.a_yiyang.model;

public class JigouRoomModel {
    private int imgID;
    private String name;
    private String jieshao;
    private String jiage;

    public JigouRoomModel(int imgID, String name, String jieshao, String jiage) {
        this.imgID = imgID;
        this.name = name;
        this.jieshao = jieshao;
        this.jiage = jiage;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJieshao() {
        return jieshao;
    }

    public void setJieshao(String jieshao) {
        this.jieshao = jieshao;
    }

    public String getJiage() {
        return jiage;
    }

    public void setJiage(String jiage) {
        this.jiage = jiage;
    }
}
