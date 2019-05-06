package com.bcxd.wgga.model.info;

import java.io.Serializable;

/**
 * Created by lidengyao on 2016-09-04 0004.
 */


public class PengStatusInfo implements Serializable {

    private int index;
    private String sensorId;
    private int sensorStatus;
    private String sensorName;
    private String sensorValue;
    private int sensorType;
    private String sensorTime;
    private double alarmHigh;
    private double alarmLow;
    private int imgSrc;
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public int getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(int sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(String sensorValue) {
        this.sensorValue = sensorValue;
    }

    public int getSensorType() {
        return sensorType;
    }

    public void setSensorType(int sensorType) {
        this.sensorType = sensorType;
    }

    public String getSensorTime() {
        return sensorTime;
    }

    public void setSensorTime(String sensorTime) {
        this.sensorTime = sensorTime;
    }

    public double getAlarmHigh() {
        return alarmHigh;
    }

    public void setAlarmHigh(double alarmHigh) {
        this.alarmHigh = alarmHigh;
    }

    public double getAlarmLow() {
        return alarmLow;
    }

    public void setAlarmLow(double alarmLow) {
        this.alarmLow = alarmLow;
    }
}


