package com.bcxd.wgga.model.info;


import java.io.Serializable;


public class BaoJingInfoDetail implements Serializable {
    /**
     * createtime : 2019-03-10T13:20:58.429Z
     * currentvalue : string
     * devicesn : string
     * did : 0
     * mtype : 0
     * pid : 0
     * processtime : 2019-03-10T13:20:58.429Z
     * rid : 0
     * ruledescription : string
     * ruleid : 0
     * rulestr : string
     * sid : 0
     * sitename : string
     * status : 0
     */

    private String createtime;
    private String currentvalue;
    private String devicesn;
    private int did;
    private int mtype;
    private int pid;
    private String processtime;
    private int rid;
    private String ruledescription;
    private int ruleid;
    private String rulestr;
    private int sid;
    private String sitename;
    private int status;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCurrentvalue() {
        return currentvalue;
    }

    public void setCurrentvalue(String currentvalue) {
        this.currentvalue = currentvalue;
    }

    public String getDevicesn() {
        return devicesn;
    }

    public void setDevicesn(String devicesn) {
        this.devicesn = devicesn;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getMtype() {
        return mtype;
    }

    public void setMtype(int mtype) {
        this.mtype = mtype;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProcesstime() {
        return processtime;
    }

    public void setProcesstime(String processtime) {
        this.processtime = processtime;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRuledescription() {
        return ruledescription;
    }

    public void setRuledescription(String ruledescription) {
        this.ruledescription = ruledescription;
    }

    public int getRuleid() {
        return ruleid;
    }

    public void setRuleid(int ruleid) {
        this.ruleid = ruleid;
    }

    public String getRulestr() {
        return rulestr;
    }

    public void setRulestr(String rulestr) {
        this.rulestr = rulestr;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}


