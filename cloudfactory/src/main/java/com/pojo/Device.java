package com.pojo;

public class Device {
    private String id;
    private String typeid;
    private String devicename;
    private String norms;
    private String describe;
    private String deviceno;
    private String devicestatus;
    private String rentstatus;
    private String factoryid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

    public String getDevicestatus() {
        return devicestatus;
    }

    public void setDevicestatus(String devicestatus) {
        this.devicestatus = devicestatus;
    }

    public String getRentstatus() {
        return rentstatus;
    }

    public void setRentstatus(String rentstatus) {
        this.rentstatus = rentstatus;
    }

    public String getFactoryid() {
        return factoryid;
    }

    public void setFactoryid(String factoryid) {
        this.factoryid = factoryid;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", typeid='" + typeid + '\'' +
                ", devicename='" + devicename + '\'' +
                ", norms='" + norms + '\'' +
                ", describe='" + describe + '\'' +
                ", deviceno='" + deviceno + '\'' +
                ", devicestatus=" + devicestatus +
                ", rentstatus=" + rentstatus +
                ", factoryid='" + factoryid + '\'' +
                '}';
    }
}
