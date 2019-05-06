package com.bcxd.wgga.model.info;


import java.io.Serializable;
import java.util.ArrayList;


public class BaoJingInfo implements Serializable {
    /**
     * records : [{"rid":901,"did":17,"devicesn":"JH00250000010958","ruleid":4,"currentvalue":"{\"atmospheric\":\"0.0\",\"devicesn\":\"JH00250000010958\",\"devicetime\":1549793601000,\"did\":17,\"humidity\":\"86.8\",\"noise\":\"43.6\",\"pm10\":\"31.3\",\"pm25\":\"23.8\",\"rid\":14,\"status\":1,\"temperature\":\"0.7\",\"tsp\":\"0.0\",\"updatetime\":1549793650632,\"winddirection\":\"315\",\"windspeed\":\"1.5\"}","sid":8,"status":0,"processtime":null,"sitename":"国际路跨河桥","mtype":2,"rulestr":null,"ruledescription":null,"pid":8,"createtime":"2019-02-10 18:14:11"}]
     * total : 1
     * size : 10
     * current : 1
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private int pages;
    private ArrayList<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public ArrayList<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable{
        /**
         * rid : 901
         * did : 17
         * devicesn : JH00250000010958
         * ruleid : 4
         * currentvalue : {"atmospheric":"0.0","devicesn":"JH00250000010958","devicetime":1549793601000,"did":17,"humidity":"86.8","noise":"43.6","pm10":"31.3","pm25":"23.8","rid":14,"status":1,"temperature":"0.7","tsp":"0.0","updatetime":1549793650632,"winddirection":"315","windspeed":"1.5"}
         * sid : 8
         * status : 0
         * processtime : null
         * sitename : 国际路跨河桥
         * mtype : 2
         * rulestr : null
         * ruledescription : null
         * pid : 8
         * createtime : 2019-02-10 18:14:11
         */

        private int rid;
        private int did;
        private String devicesn;
        private int ruleid;
        private String currentvalue;
        private int sid;
        private int status;
        private String processtime;
        private String sitename;
        private int mtype;
        private Object rulestr;
        private String ruledescription;
        private int pid;
        private String createtime;

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public int getDid() {
            return did;
        }

        public void setDid(int did) {
            this.did = did;
        }

        public String getDevicesn() {
            return devicesn;
        }

        public void setDevicesn(String devicesn) {
            this.devicesn = devicesn;
        }

        public int getRuleid() {
            return ruleid;
        }

        public void setRuleid(int ruleid) {
            this.ruleid = ruleid;
        }

        public String getCurrentvalue() {
            return currentvalue;
        }

        public void setCurrentvalue(String currentvalue) {
            this.currentvalue = currentvalue;
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

        public String getProcesstime() {
            return processtime;
        }

        public void setProcesstime(String processtime) {
            this.processtime = processtime;
        }

        public String getSitename() {
            return sitename;
        }

        public void setSitename(String sitename) {
            this.sitename = sitename;
        }

        public int getMtype() {
            return mtype;
        }

        public void setMtype(int mtype) {
            this.mtype = mtype;
        }

        public Object getRulestr() {
            return rulestr;
        }

        public void setRulestr(Object rulestr) {
            this.rulestr = rulestr;
        }

        public String getRuledescription() {
            return ruledescription;
        }

        public void setRuledescription(String ruledescription) {
            this.ruledescription = ruledescription;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}


