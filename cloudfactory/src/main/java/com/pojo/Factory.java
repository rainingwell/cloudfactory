package com.pojo;

import com.sun.xml.internal.ws.api.server.SDDocument;

public class Factory {
    private String id;
    private String factoryname;
    private String introduction;
    private String userid;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactoryname() {
        return factoryname;
    }

    public void setFactoryname(String factoryname) {
        this.factoryname = factoryname;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Factory{" +
                "id='" + id + '\'' +
                ", factoryname='" + factoryname + '\'' +
                ", introduction='" + introduction + '\'' +
                ", userid='" + userid + '\'' +
                ", status=" + status +
                '}';
    }
}
