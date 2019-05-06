package com.bcxd.wgga.model.info;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ZhengGaiInfo implements Serializable {


    /**
     * records : [{"zid":66,"pid":29,"fileno":"编号1","title":"标题1","dutyunit":"责任1","description":"描述1","createtime":"2019-03-10 17:45:51","creatorid":1,"creatorname":"规划处_领导","creator":"签发1","attachnum":null,"feedback":null,"dealuserid":34,"status":1,"dealtime":"2019-03-10 21:44:50","ztype":1,"ztypename":"质量","pname":"测试项目111","dutyList":[]},{"zid":67,"pid":29,"fileno":"20190310001","title":"渣土整改","dutyunit":"中一建","description":"渣土太多了","createtime":"2019-03-10 20:46:45","creatorid":1,"creatorname":"规划处_领导","creator":"规划处","attachnum":null,"feedback":null,"dealuserid":null,"status":0,"dealtime":null,"ztype":1,"ztypename":"质量","pname":"测试项目111","dutyList":[]},{"zid":68,"pid":29,"fileno":"20190002","title":"标题2","dutyunit":"责任单位2","description":"描述2","createtime":"2019-03-10 21:11:22","creatorid":1,"creatorname":"规划处_领导","creator":"签发人2","attachnum":null,"feedback":null,"dealuserid":null,"status":0,"dealtime":null,"ztype":1,"ztypename":"质量","pname":"测试项目111","dutyList":[]},{"zid":69,"pid":29,"fileno":"编号3","title":"标题3","dutyunit":"单位3","description":"描述3","createtime":"2019-03-10 21:13:25","creatorid":1,"creatorname":"规划处_领导","creator":"千人3","attachnum":null,"feedback":null,"dealuserid":null,"status":1,"dealtime":null,"ztype":1,"ztypename":"质量","pname":"测试项目111","dutyList":[]},{"zid":70,"pid":29,"fileno":"19001","title":"标题4","dutyunit":"单位4","description":"描述4","createtime":"2019-03-10 21:16:33","creatorid":1,"creatorname":"规划处_领导","creator":"人4","attachnum":null,"feedback":null,"dealuserid":null,"status":0,"dealtime":null,"ztype":1,"ztypename":"质量","pname":"测试项目111","dutyList":[]}]
     * total : 5
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

    public static class RecordsBean implements Serializable {
        /**
         * zid : 66
         * pid : 29
         * fileno : 编号1
         * title : 标题1
         * dutyunit : 责任1
         * description : 描述1
         * createtime : 2019-03-10 17:45:51
         * creatorid : 1
         * creatorname : 规划处_领导
         * creator : 签发1
         * attachnum : null
         * feedback : null
         * dealuserid : 34
         * status : 1
         * dealtime : 2019-03-10 21:44:50
         * ztype : 1
         * ztypename : 质量
         * pname : 测试项目111
         * dutyList : []
         */

        private int zid;
        private int pid;
        private String fileno;
        private String title;
        private String dutyunit;
        private String description;
        private String createtime;
        private int creatorid;
        private String creatorname;
        private String creator;
        private Object attachnum;
        private Object feedback;
        private int dealuserid;
        private int status;
        private String dealtime;
        private int ztype;
        private String ztypename;
        private String pname;
        private List<?> dutyList;

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

        public String getCreatorname() {
            return creatorname;
        }

        public void setCreatorname(String creatorname) {
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

        public int getDealuserid() {
            return dealuserid;
        }

        public void setDealuserid(int dealuserid) {
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

        public int getZtype() {
            return ztype;
        }

        public void setZtype(int ztype) {
            this.ztype = ztype;
        }

        public String getZtypename() {
            return ztypename;
        }

        public void setZtypename(String ztypename) {
            this.ztypename = ztypename;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public List<?> getDutyList() {
            return dutyList;
        }

        public void setDutyList(List<?> dutyList) {
            this.dutyList = dutyList;
        }
    }

}



