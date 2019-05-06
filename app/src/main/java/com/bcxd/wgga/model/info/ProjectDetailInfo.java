package com.bcxd.wgga.model.info;


import java.io.Serializable;
import java.math.BigDecimal;


public class ProjectDetailInfo implements Serializable {
    /**
     * pid : 20
     * pname : 建渣处理厂
     * address : 南京建渣处理厂
     * approvedfileno : null
     * approveddepart : null
     * projecttype : 0
     * safetysupervisedepart : null
     * dustsupervisedepart : null
     * dirtsupervisedepart : null
     * starttime : 2019-01-15
     * endtime : 2019-01-30
     * investamount : null
     * jiananamount : 0
     * constructunit : null
     * dutyname : null
     * workunit : null
     * projectmanager : null
     * jianliunit : null
     * jianliengineer : null
     * equipoperatingunit : null
     * normalized : 0
     */

    private int pid;
    private String pname;
    private String address;
    private String approvedfileno;
    private String approveddepart;
    private String projecttype;
    private String safetysupervisedepart;
    private String dustsupervisedepart;
    private String dirtsupervisedepart;
    private String starttime;
    private String endtime;
    private BigDecimal investamount;
    private BigDecimal jiananamount;
    private String constructunit;
    private String dutyname;
    private String workunit;
    private String projectmanager;
    private String jianliunit;
    private String jianliengineer;
    private String equipoperatingunit;
    private int normalized;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApprovedfileno() {
        return approvedfileno;
    }

    public void setApprovedfileno(String approvedfileno) {
        this.approvedfileno = approvedfileno;
    }

    public String getApproveddepart() {
        return approveddepart;
    }

    public void setApproveddepart(String approveddepart) {
        this.approveddepart = approveddepart;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getSafetysupervisedepart() {
        return safetysupervisedepart;
    }

    public void setSafetysupervisedepart(String safetysupervisedepart) {
        this.safetysupervisedepart = safetysupervisedepart;
    }

    public String getDustsupervisedepart() {
        return dustsupervisedepart;
    }

    public void setDustsupervisedepart(String dustsupervisedepart) {
        this.dustsupervisedepart = dustsupervisedepart;
    }

    public String getDirtsupervisedepart() {
        return dirtsupervisedepart;
    }

    public void setDirtsupervisedepart(String dirtsupervisedepart) {
        this.dirtsupervisedepart = dirtsupervisedepart;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public BigDecimal getInvestamount() {
        return investamount;
    }

    public void setInvestamount(BigDecimal investamount) {
        if (investamount == null)
            this.investamount = new BigDecimal(0);
        this.investamount = investamount;
    }

    public BigDecimal getJiananamount() {
        return jiananamount;
    }

    public void setJiananamount(BigDecimal jiananamount) {
        this.jiananamount = jiananamount;
    }

    public String getConstructunit() {
        return constructunit;
    }

    public void setConstructunit(String constructunit) {
        this.constructunit = constructunit;
    }

    public String getDutyname() {
        return dutyname;
    }

    public void setDutyname(String dutyname) {
        this.dutyname = dutyname;
    }

    public String getWorkunit() {
        return workunit;
    }

    public void setWorkunit(String workunit) {
        this.workunit = workunit;
    }

    public String getProjectmanager() {
        return projectmanager;
    }

    public void setProjectmanager(String projectmanager) {
        this.projectmanager = projectmanager;
    }

    public String getJianliunit() {
        return jianliunit;
    }

    public void setJianliunit(String jianliunit) {
        this.jianliunit = jianliunit;
    }

    public String getJianliengineer() {
        return jianliengineer;
    }

    public void setJianliengineer(String jianliengineer) {
        this.jianliengineer = jianliengineer;
    }

    public String getEquipoperatingunit() {
        return equipoperatingunit;
    }

    public void setEquipoperatingunit(String equipoperatingunit) {
        this.equipoperatingunit = equipoperatingunit;
    }

    public int getNormalized() {
        return normalized;
    }

    public void setNormalized(int normalized) {
        this.normalized = normalized;
    }
}

