package com.bcxd.wgga.model.bean;

import java.util.List;

public class JianChaUpdateBean {

    public String context;
    public int csid;
    public int dealuser;
    public int status;
    private List<PicturelistBean> picturelist;


    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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
