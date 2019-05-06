package com.bcxd.wgga.model.bean;

import java.util.List;

public class JianChaBean {

    /**
     * classify : 1
     * createtime : 2019-01-04T03:52:05.328Z
     * csid : 1
     * dealuser : 1
     * desc : 建筑垃圾堆积
     * picturelist : [{"bigpicid":1,"smallpicid":1}]
     * pid : 1
     * position : 工地东南角
     * shottime : 2019-01-04T03:52:05.328Z
     * sid : 1
     * status : 0
     */

    private String classify;
    private String createtime;
    private int csid;
    private int dealuser;
    private String desc;
    private int pid;
    private String position;
    private String shottime;
    private int sid;
    private int status;
    private List<PicturelistBean> picturelist;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getCsid() {
        return csid;
    }

    public void setCsid(int csid) {
        this.csid = csid;
    }

    public int getDealuser() {
        return dealuser;
    }

    public void setDealuser(int dealuser) {
        this.dealuser = dealuser;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getShottime() {
        return shottime;
    }

    public void setShottime(String shottime) {
        this.shottime = shottime;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PicturelistBean> getPicturelist() {
        return picturelist;
    }

    public void setPicturelist(List<PicturelistBean> picturelist) {
        this.picturelist = picturelist;
    }

    public static class PicturelistBean {
        /**
         * bigpicid : 1
         * smallpicid : 1
         */

        private int bigpicid;
        private int smallpicid;

        public int getBigpicid() {
            return bigpicid;
        }

        public void setBigpicid(int bigpicid) {
            this.bigpicid = bigpicid;
        }

        public int getSmallpicid() {
            return smallpicid;
        }

        public void setSmallpicid(int smallpicid) {
            this.smallpicid = smallpicid;
        }
    }
}
