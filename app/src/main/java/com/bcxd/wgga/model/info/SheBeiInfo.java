package com.bcxd.wgga.model.info;

import java.io.Serializable;
import java.util.List;


public class SheBeiInfo {
    /**
     * sid : 2
     * graphurl : http://app.hxsoft.net:10021/images/construction-site-1.jpg
     * width : 750
     * height : 500
     * devices : [{"gdid":0,"px":211,"py":121,"did":6,"devicesn":"JH00250000008788","devicename":"","dtype":2}]
     */

    private int sid;
    private String graphurl;
    private int width;
    private int height;
    private List<DevicesBean> devices;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getGraphurl() {
        return graphurl;
    }

    public void setGraphurl(String graphurl) {
        this.graphurl = graphurl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<DevicesBean> getDevices() {
        return devices;
    }

    public void setDevices(List<DevicesBean> devices) {
        this.devices = devices;
    }

    public static class DevicesBean implements Serializable {
        /**
         * gdid : 0
         * px : 211
         * py : 121
         * did : 6
         * devicesn : JH00250000008788
         * devicename :
         * dtype : 2
         */

        private int gdid;
        private int px;
        private int py;
        private int did;
        private String devicesn;
        private String devicename;
        private int dtype;
        private String videourl;

        public String getVideourl() {
            return videourl;
        }

        public void setVideourl(String videourl) {
            this.videourl = videourl;
        }

        public int getGdid() {
            return gdid;
        }

        public void setGdid(int gdid) {
            this.gdid = gdid;
        }

        public int getPx() {
            return px;
        }

        public void setPx(int px) {
            this.px = px;
        }

        public int getPy() {
            return py;
        }

        public void setPy(int py) {
            this.py = py;
        }

        public int getDid() {
            return did;
        }

        public void setDid(int did) {
            this.did = did;
        }

        public String getDevicesn() {
            return devicesn;
        }

        public void setDevicesn(String devicesn) {
            this.devicesn = devicesn;
        }

        public String getDevicename() {
            return devicename;
        }

        public void setDevicename(String devicename) {
            this.devicename = devicename;
        }

        public int getDtype() {
            return dtype;
        }

        public void setDtype(int dtype) {
            this.dtype = dtype;
        }
    }
}

