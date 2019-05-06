package com.bcxd.wgga.model.info;


import java.io.Serializable;

public class apppicInfo implements Serializable {
    /**
     * apid : 1
     * did : 504
     * pid : 0
     * title : 首页图片
     * createtime : 1551751285000
     * ptype : 1
     * picurl : http://app.hxsoft.net:10031/images/3e9aa36178f04e808fee120940c3f247.jpg
     */

    private int apid;
    private int did;
    private int pid;
    private String title;
    private String createtime;
    private int ptype;
    private String picurl;

    public int getApid() {
        return apid;
    }

    public void setApid(int apid) {
        this.apid = apid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}


