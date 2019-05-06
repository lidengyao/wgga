package com.bcxd.wgga.model.bean;

/**
 * Created by jinxh on 16/5/9.
 */

public class UserInfo {
    /**
     * user_account : test@hxsoft.net
     * user_id : 101
     * access_token : access_token-xxxx
     * refresh_token : refresh_token-xxx
     */

    private String user_account;
    private int user_id;
    private String access_token;
    private String refresh_token;

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
