package com.bcxd.wgga.model.bean;

/**
 * Created by jinxh on 16/2/17.
 */
public class ResponseBean<T> {
    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return data;
    }

    public void setBody(T data) {
        this.data = data;
    }
}
