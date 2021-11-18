package com.pojo;

import java.util.Date;

public class Order {
    private String id;
    private String orderno;
    private String productid;
    private String ordernum;
    private Date deaddate;
    private Date deliverDate;
    private String orderstatus;
    private String receipt;
    private String userid;
    private String username;
    private String contact;
    private String address;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public Date getDeaddate() {
        return deaddate;
    }

    public void setDeaddate(Date deaddate) {
        this.deaddate = deaddate;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderno='" + orderno + '\'' +
                ", productid='" + productid + '\'' +
                ", ordernum='" + ordernum + '\'' +
                ", deaddate=" + deaddate +
                ", deliverDate=" + deliverDate +
                ", orderstatus='" + orderstatus + '\'' +
                ", receipt='" + receipt + '\'' +
                ", userid='" + userid + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
