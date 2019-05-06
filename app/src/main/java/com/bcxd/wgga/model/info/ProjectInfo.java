package com.bcxd.wgga.model.info;


import java.io.Serializable;

public class ProjectInfo implements Serializable {
    /**
     * pid : 7
     * pname : 南片区项目
     * ptype : 2
     * upid : 2
     * level : 2
     * idx : 1
     * createtime : 2019-01-02 21:23:52
     * sid : 1
     */

    private int pid;
    private String pname;
    private int ptype;
    private int upid;
    private int level;
    private int idx;
    private String createtime;
    private String vpname;
    private String epname;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPtype() {
        return ptype;
    }

    public void setPtype(int ptype) {
        this.ptype = ptype;
    }

    public int getUpid() {
        return upid;
    }

    public void setUpid(int upid) {
        this.upid = upid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getVpname() {
        return vpname;
    }

    public void setVpname(String vpname) {
        this.vpname = vpname;
    }

    public String getEpname() {
        return epname;
    }

    public void setEpname(String epname) {
        this.epname = epname;
    }
}

