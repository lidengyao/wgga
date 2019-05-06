package com.bcxd.wgga.model.bean;


/**
 * Created by lidengyao on 2016-09-01 0001.
 */
public class CommonMessageBean<T> {
    private int status;
    private String msg;
    private T messagelist;

    private  int messagecount;

    public int getMessagecount() {
        return messagecount;
    }

    public void setMessagecount(int messagecount) {
        this.messagecount = messagecount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getMessagelist() {
        return messagelist;
    }

    public void setMessagelist(T messagelist) {
        this.messagelist = messagelist;
    }
}
