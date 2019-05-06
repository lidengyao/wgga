package com.bcxd.wgga.model.info;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lidengyao on 2016-10-10 0010.
 */

public class XiaoXiInfo {
    /**
     * records : [{"mid":3,"uid":1,"mtype":2,"mdesc":"测试消息3","bid":3,"status":0,"createtime":"2018-12-24 13:44:28"},{"mid":2,"uid":1,"mtype":1,"mdesc":"测试消息2","bid":2,"status":1,"createtime":"2018-12-24 13:44:17"},{"mid":1,"uid":1,"mtype":1,"mdesc":"测试消息1","bid":1,"status":0,"createtime":"2018-12-24 13:44:11"}]
     * total : 3
     * size : 20
     * current : 0
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

    public static class RecordsBean implements Serializable {
        /**
         * mid : 3
         * uid : 1
         * mtype : 2
         * mdesc : 测试消息3
         * bid : 3
         * status : 0
         * createtime : 2018-12-24 13:44:28
         */

        private int mid;
        private int uid;
        private int mtype;
        private String mdesc;
        private int bid;
        private int status;
        private String createtime;
        private int sid;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getMtype() {
            return mtype;
        }

        public void setMtype(int mtype) {
            this.mtype = mtype;
        }

        public String getMdesc() {
            return mdesc;
        }

        public void setMdesc(String mdesc) {
            this.mdesc = mdesc;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
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
}



