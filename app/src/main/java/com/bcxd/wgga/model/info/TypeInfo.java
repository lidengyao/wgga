package com.bcxd.wgga.model.info;


public class TypeInfo {
    /**
     * vid : 31
     * tcode : CHECK_QUESTION_KIND
     * vname : 施工问题
     * cvalue : 1
     * idx : 1
     * status : 1
     * createtime : 2018-12-24 21:44:54
     */

    private int vid;
    private String tcode;
    private String vname;
    private String cvalue;
    private int idx;
    private int status;
    private String createtime;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getTcode() {
        return tcode;
    }

    public void setTcode(String tcode) {
        this.tcode = tcode;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}

