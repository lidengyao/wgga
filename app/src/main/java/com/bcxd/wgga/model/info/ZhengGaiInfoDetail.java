package com.bcxd.wgga.model.info;


import java.io.Serializable;


public class ZhengGaiInfoDetail implements Serializable {

        /**
         * zid : 66
         * pid : 29
         * fileno : 编号1
         * title : 标题1
         * dutyunit : 责任1
         * description : 描述1
         * createtime : 2019-03-10 17:45:51
         * creatorid : 1
         * creatorname : null
         * creator : 签发1
         * attachnum : null
         * feedback : null
         * dealuserid : null
         * status : 0
         * dealtime : null
         * pname : null
         */

        private int zid;
        private int pid;
        private String fileno;
        private String title;
        private String dutyunit;
        private String description;
        private String createtime;
        private int creatorid;
        private Object creatorname;
        private String creator;
        private Object attachnum;
        private Object feedback;
        private Object dealuserid;
        private int status;
        private String dealtime;
        private Object pname;

        public int getZid() {
            return zid;
        }

        public void setZid(int zid) {
            this.zid = zid;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getFileno() {
            return fileno;
        }

        public void setFileno(String fileno) {
            this.fileno = fileno;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDutyunit() {
            return dutyunit;
        }

        public void setDutyunit(String dutyunit) {
            this.dutyunit = dutyunit;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getCreatorid() {
            return creatorid;
        }

        public void setCreatorid(int creatorid) {
            this.creatorid = creatorid;
        }

        public Object getCreatorname() {
            return creatorname;
        }

        public void setCreatorname(Object creatorname) {
            this.creatorname = creatorname;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public Object getAttachnum() {
            return attachnum;
        }

        public void setAttachnum(Object attachnum) {
            this.attachnum = attachnum;
        }

        public Object getFeedback() {
            return feedback;
        }

        public void setFeedback(Object feedback) {
            this.feedback = feedback;
        }

        public Object getDealuserid() {
            return dealuserid;
        }

        public void setDealuserid(Object dealuserid) {
            this.dealuserid = dealuserid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDealtime() {
            return dealtime;
        }

        public void setDealtime(String dealtime) {
            this.dealtime = dealtime;
        }

        public Object getPname() {
            return pname;
        }

        public void setPname(Object pname) {
            this.pname = pname;
        }

}



