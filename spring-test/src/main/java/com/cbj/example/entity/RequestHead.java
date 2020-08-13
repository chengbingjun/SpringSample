package com.cbj.example.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("head")
public class RequestHead {

    private String bizcode;

    private String partner;

    private String deviceid;

    private String ipaddress;

    public String getBizcode() {
        return bizcode;
    }

    public void setBizcode(String bizcode) {
        this.bizcode = bizcode;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    @Override
    public String toString() {
        return "RequestHead{" +
                "bizcode='" + bizcode + '\'' +
                ", partner='" + partner + '\'' +
                ", deviceid='" + deviceid + '\'' +
                ", ipaddress='" + ipaddress + '\'' +
                '}';
    }
}
