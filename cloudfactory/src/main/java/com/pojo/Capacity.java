package com.pojo;

public class Capacity {
    String deviceid;
    String productid;
    String capacity;
    String productname;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Capacity{" +
                "deviceid='" + deviceid + '\'' +
                ", productid='" + productid + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
