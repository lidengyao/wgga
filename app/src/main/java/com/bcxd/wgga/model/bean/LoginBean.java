package com.bcxd.wgga.model.bean;

/**
 * Created by lidengyao on 2016-09-29 0029.
 */
public class LoginBean {

    private String user_account;
    private String password;
    private String  deviceid;

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
