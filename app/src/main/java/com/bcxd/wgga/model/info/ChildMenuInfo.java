package com.bcxd.wgga.model.info;

import java.util.List;


public class ChildMenuInfo {
    /**
     * id : 135
     * parentId : 0
     * moduleType : 100
     * orderSort : 1
     * title : 常用
     * ruleType : 0
     * commonUse : 0
     * uri : /toxic/common
     * img : https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg
     * createdTime : null
     * updatedTime : null
     * rules : [{"id":111,"parentId":3,"moduleType":100,"orderSort":0,"title":"购买证","ruleType":0,"commonUse":1,"uri":"/toxic/g","img":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg","createdTime":null,"updatedTime":null,"rules":null},{"id":118,"parentId":106,"moduleType":100,"orderSort":0,"title":"通知公告","ruleType":0,"commonUse":1,"uri":"/toxic/tzgg","img":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg","createdTime":null,"updatedTime":null,"rules":null},{"id":120,"parentId":106,"moduleType":100,"orderSort":0,"title":"业务咨询","ruleType":0,"commonUse":1,"uri":"/toxic/ywzx","img":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg","createdTime":null,"updatedTime":null,"rules":null},{"id":124,"parentId":1,"moduleType":100,"orderSort":0,"title":"解锁申请审批","ruleType":0,"commonUse":1,"uri":"/toxic/jssqsp","img":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg","createdTime":null,"updatedTime":null,"rules":null},{"id":130,"parentId":135,"moduleType":100,"orderSort":0,"title":"待办事项","ruleType":0,"commonUse":1,"uri":"/toxic/todo","img":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg","createdTime":null,"updatedTime":null,"rules":null},{"id":131,"parentId":135,"moduleType":100,"orderSort":0,"title":"预警提醒","ruleType":0,"commonUse":1,"uri":"/toxic/yjtx","img":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg","createdTime":null,"updatedTime":null,"rules":null},{"id":133,"parentId":135,"moduleType":100,"orderSort":0,"title":"单位管理","ruleType":0,"commonUse":1,"uri":"/toxic/dwgl","img":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg","createdTime":null,"updatedTime":null,"rules":null},{"id":134,"parentId":135,"moduleType":100,"orderSort":0,"title":"人员管理","ruleType":0,"commonUse":1,"uri":"/toxic/rygl","img":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg","createdTime":null,"updatedTime":null,"rules":null}]
     */

    private int id;
    private int parentId;
    private int moduleType;
    private int orderSort;
    private String title;
    private int ruleType;
    private int commonUse;
    private String uri;
    private String img;
    private Object createdTime;
    private Object updatedTime;
    private List<RulesBean> rules;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getModuleType() {
        return moduleType;
    }

    public void setModuleType(int moduleType) {
        this.moduleType = moduleType;
    }

    public int getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(int orderSort) {
        this.orderSort = orderSort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuleType() {
        return ruleType;
    }

    public void setRuleType(int ruleType) {
        this.ruleType = ruleType;
    }

    public int getCommonUse() {
        return commonUse;
    }

    public void setCommonUse(int commonUse) {
        this.commonUse = commonUse;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Object getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Object createdTime) {
        this.createdTime = createdTime;
    }

    public Object getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Object updatedTime) {
        this.updatedTime = updatedTime;
    }

    public List<RulesBean> getRules() {
        return rules;
    }

    public void setRules(List<RulesBean> rules) {
        this.rules = rules;
    }

    public static class RulesBean {
        /**
         * id : 111
         * parentId : 3
         * moduleType : 100
         * orderSort : 0
         * title : 购买证
         * ruleType : 0
         * commonUse : 1
         * uri : /toxic/g
         * img : https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg
         * createdTime : null
         * updatedTime : null
         * rules : null
         */

        private int id;
        private int parentId;
        private int moduleType;
        private int orderSort;
        private String title;
        private int ruleType;
        private int commonUse;
        private String uri;
        private String img;
        private Object createdTime;
        private Object updatedTime;
        private Object rules;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getModuleType() {
            return moduleType;
        }

        public void setModuleType(int moduleType) {
            this.moduleType = moduleType;
        }

        public int getOrderSort() {
            return orderSort;
        }

        public void setOrderSort(int orderSort) {
            this.orderSort = orderSort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getRuleType() {
            return ruleType;
        }

        public void setRuleType(int ruleType) {
            this.ruleType = ruleType;
        }

        public int getCommonUse() {
            return commonUse;
        }

        public void setCommonUse(int commonUse) {
            this.commonUse = commonUse;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public Object getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(Object createdTime) {
            this.createdTime = createdTime;
        }

        public Object getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(Object updatedTime) {
            this.updatedTime = updatedTime;
        }

        public Object getRules() {
            return rules;
        }

        public void setRules(Object rules) {
            this.rules = rules;
        }
    }
}

