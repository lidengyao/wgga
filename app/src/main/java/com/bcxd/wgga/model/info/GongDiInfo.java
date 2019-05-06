package com.bcxd.wgga.model.info;


import java.io.Serializable;
import java.util.List;


public class GongDiInfo implements Serializable {
    /**
     * sid : 1
     * sname : 北片区工地1
     * lat : 31.993464
     * lon : 118.80805
     * paths : [["118.804892","31.998032"],["118.801201","31.991553"],["118.814419","31.994902"]]
     * pid : 7
     * projectname : 南片区
     */

    private int sid;
    private String sname;
    private double lat;
    private double lon;
    private int pid;
    private String projectname;
    private List<List<String>> paths;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public List<List<String>> getPaths() {
        return paths;
    }

    public void setPaths(List<List<String>> paths) {
        this.paths = paths;
    }
}

