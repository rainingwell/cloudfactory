package com.pojo;

public class DeviceType {
    private String id;
    private String typename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public String toString() {
        return "DeviceType{" +
                "id='" + id + '\'' +
                ", typename='" + typename + '\'' +
                '}';
    }
}
