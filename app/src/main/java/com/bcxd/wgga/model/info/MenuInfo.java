package com.bcxd.wgga.model.info;

import java.util.List;




    public  class MenuInfo {
        /**
         * id : 1
         * parentId : 0
         * moduleType : 100
         * orderSort : 0
         * title : 业务办理
         * ruleType : 0
         * uri : /index/test
         * img : https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg
         * createdTime : null
         * updatedTime : null
         * rules : [{"id":4,"parentId":1,"moduleType":100,"orderSort":0,"title":"锁定管理","ruleType":0,"uri":"/index/index2","img":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg","createdTime":null,"updatedTime":null,"rules":null},{"id":5,"parentId":1,"moduleType":100,"orderSort":0,"title":"经办人管理","ruleType":0,"uri":"/index/index3","img":"https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/s%3D500/sign=f60b66fd6e2762d0843ea4bf90ee0849/023b5bb5c9ea15ce693439d7bb003af33b87b212.jpg","createdTime":null,"updatedTime":null,"rules":null}]
         */

        private int id;
        private int parentId;
        private int moduleType;
        private int orderSort;
        private String title;
        private int ruleType;
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
             * id : 4
             * parentId : 1
             * moduleType : 100
             * orderSort : 0
             * title : 锁定管理
             * ruleType : 0
             * uri : /index/index2
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

