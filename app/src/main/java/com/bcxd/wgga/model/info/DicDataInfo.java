package com.bcxd.wgga.model.info;

public class DicDataInfo {
    private int id;
    private int pid;
    private int lbc_type;
    private String lby_type_name;
    private String create_time;
    private String update_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getLbc_type() {
        return lbc_type;
    }

    public void setLbc_type(int lbc_type) {
        this.lbc_type = lbc_type;
    }

    public String getLby_type_name() {
        return lby_type_name;
    }

    public void setLby_type_name(String lby_type_name) {
        this.lby_type_name = lby_type_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
