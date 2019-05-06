package com.bcxd.wgga.model.info;


import java.io.Serializable;

public class RuleIndexInfo implements Serializable {
    /**
     * id : 100
     * parentId : 0
     * moduleType : 0
     * orderSort : 0
     * title : 剧毒化学品
     * ruleType : 0
     * uri : /toxic
     * img : /images/judu.png
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


