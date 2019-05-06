package com.bcxd.wgga.model.info;


public class Z_UserInfo {
    /**
     * uid : 1
     * usercode : admin@hxsoft.net
     * userpass : e10adc3949ba59abbe56e057f20f883e
     * username : administrator
     * selfieid : null
     * pictureurl : http://app.hxsoft.net:10020/images/firmino.jpg
     * position : 管理员
     */

    private int uid;
    private String usercode;
    private String userpass;
    private String username;
    private String selfieid;
    private String pictureurl;
    private String position;
    private Object createtime;
    private Object modifytime;
    private int usertype;
    private Object rolelist;
    private Object roledomainlist;
    private Object sitelist;

    public Object getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Object createtime) {
        this.createtime = createtime;
    }

    public Object getModifytime() {
        return modifytime;
    }

    public void setModifytime(Object modifytime) {
        this.modifytime = modifytime;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public Object getRolelist() {
        return rolelist;
    }

    public void setRolelist(Object rolelist) {
        this.rolelist = rolelist;
    }

    public Object getRoledomainlist() {
        return roledomainlist;
    }

    public void setRoledomainlist(Object roledomainlist) {
        this.roledomainlist = roledomainlist;
    }

    public Object getSitelist() {
        return sitelist;
    }

    public void setSitelist(Object sitelist) {
        this.sitelist = sitelist;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSelfieid() {
        return selfieid;
    }

    public void setSelfieid(String selfieid) {
        this.selfieid = selfieid;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

