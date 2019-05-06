package com.bcxd.wgga.model.info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lidengyao on 2016-10-10 0010.
 */
public class JianChaDetailInfo {
    /**
     * check : {"csid":26,"sid":1,"sname":"北片区工地1","pid":null,"projectname":null,"shottime":"2019-01-04 14:56:57","classify":32,"classifyname":null,"position":"None","desc":"5","createtime":"2019-01-04 00:00:00","status":1,"piclist":null}
     * checkLogList : [{"fid":28,"csid":26,"dealuser":1,"dealusername":"administrator","acttime":"2019-01-04 00:00:00","context":"无备注","piclist":[{"did":135,"pcode":"7c4d5179262342fda768205f8f03962d","pcode2":null,"url":"http://app.hxsoft.net:10021/images/7c4d5179262342fda768205f8f03962d.jpg","url2":null,"status":1,"idx":1},{"did":136,"pcode":"db69762164bc424eb9ad04f35a501a75","pcode2":null,"url":"http://app.hxsoft.net:10021/images/db69762164bc424eb9ad04f35a501a75.jpg","url2":null,"status":1,"idx":2}]},{"fid":31,"csid":26,"dealuser":1,"dealusername":"administrator","acttime":"2019-01-04 00:00:00","context":"非常好","piclist":[]}]
     */

    private CheckBean check;
    private List<CheckLogListBean> checkLogList;

    public CheckBean getCheck() {
        return check;
    }

    public void setCheck(CheckBean check) {
        this.check = check;
    }

    public List<CheckLogListBean> getCheckLogList() {
        return checkLogList;
    }

    public void setCheckLogList(List<CheckLogListBean> checkLogList) {
        this.checkLogList = checkLogList;
    }

    public static class CheckBean {
        /**
         * csid : 26
         * sid : 1
         * sname : 北片区工地1
         * pid : null
         * projectname : null
         * shottime : 2019-01-04 14:56:57
         * classify : 32
         * classifyname : null
         * position : None
         * desc : 5
         * createtime : 2019-01-04 00:00:00
         * status : 1
         * piclist : null
         */

        private int csid;
        private int sid;
        private String sname;
        private int pid;
        private String projectname;
        private String shottime;
        private int classify;
        private String classifyname;
        private String position;
        private String desc;
        private String createtime;
        private int status;
        private ArrayList<PiclistBean> piclist;

        private String dealusername;

        public String getDealusername() {
            return dealusername;
        }

        public void setDealusername(String dealusername) {
            this.dealusername = dealusername;
        }

        public int getCsid() {
            return csid;
        }

        public void setCsid(int csid) {
            this.csid = csid;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getProjectname() {
            return projectname;
        }

        public void setProjectname(String projectname) {
            this.projectname = projectname;
        }

        public String getShottime() {
            return shottime;
        }

        public void setShottime(String shottime) {
            this.shottime = shottime;
        }

        public int getClassify() {
            return classify;
        }

        public void setClassify(int classify) {
            this.classify = classify;
        }

        public String getClassifyname() {
            return classifyname;
        }

        public void setClassifyname(String classifyname) {
            this.classifyname = classifyname;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public ArrayList<PiclistBean> getPiclist() {
            return piclist;
        }

        public void setPiclist(ArrayList<PiclistBean> piclist) {
            if (piclist == null)
                this.piclist = new ArrayList<PiclistBean>();
            else
                this.piclist = piclist;
        }

        public static class PiclistBean {
            /**
             * did : 135
             * pcode : 7c4d5179262342fda768205f8f03962d
             * pcode2 : null
             * url : http://app.hxsoft.net:10021/images/7c4d5179262342fda768205f8f03962d.jpg
             * url2 : null
             * status : 1
             * idx : 1
             */

            private int did;
            private String pcode;
            private String pcode2;
            private String url;
            private String url2;
            private int status;
            private int idx;

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public String getPcode() {
                return pcode;
            }

            public void setPcode(String pcode) {
                this.pcode = pcode;
            }

            public String getPcode2() {
                return pcode2;
            }

            public void setPcode2(String pcode2) {
                this.pcode2 = pcode2;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrl2() {
                return url2;
            }

            public void setUrl2(String url2) {
                this.url2 = url2;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIdx() {
                return idx;
            }

            public void setIdx(int idx) {
                this.idx = idx;
            }
        }
    }

    public static class CheckLogListBean {
        /**
         * fid : 28
         * csid : 26
         * dealuser : 1
         * dealusername : administrator
         * acttime : 2019-01-04 00:00:00
         * context : 无备注
         * piclist : [{"did":135,"pcode":"7c4d5179262342fda768205f8f03962d","pcode2":null,"url":"http://app.hxsoft.net:10021/images/7c4d5179262342fda768205f8f03962d.jpg","url2":null,"status":1,"idx":1},{"did":136,"pcode":"db69762164bc424eb9ad04f35a501a75","pcode2":null,"url":"http://app.hxsoft.net:10021/images/db69762164bc424eb9ad04f35a501a75.jpg","url2":null,"status":1,"idx":2}]
         */

        private int fid;
        private int csid;
        private int dealuser;
        private String dealusername;
        private String acttime;
        private String context;
        private List<PiclistBean> piclist;

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
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

        public String getDealusername() {
            return dealusername;
        }

        public void setDealusername(String dealusername) {
            this.dealusername = dealusername;
        }

        public String getActtime() {
            return acttime;
        }

        public void setActtime(String acttime) {
            this.acttime = acttime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public List<PiclistBean> getPiclist() {
            return piclist;
        }

        public void setPiclist(List<PiclistBean> piclist) {
            this.piclist = piclist;
        }

        public static class PiclistBean {
            /**
             * did : 135
             * pcode : 7c4d5179262342fda768205f8f03962d
             * pcode2 : null
             * url : http://app.hxsoft.net:10021/images/7c4d5179262342fda768205f8f03962d.jpg
             * url2 : null
             * status : 1
             * idx : 1
             */

            private int did;
            private String pcode;
            private String pcode2;
            private String url;
            private String url2;
            private int status;
            private int idx;

            public int getDid() {
                return did;
            }

            public void setDid(int did) {
                this.did = did;
            }

            public String getPcode() {
                return pcode;
            }

            public void setPcode(String pcode) {
                this.pcode = pcode;
            }

            public String getPcode2() {
                return pcode2;
            }

            public void setPcode2(String pcode2) {
                this.pcode2 = pcode2;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrl2() {
                return url2;
            }

            public void setUrl2(String url2) {
                this.url2 = url2;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIdx() {
                return idx;
            }

            public void setIdx(int idx) {
                this.idx = idx;
            }
        }
    }
}





