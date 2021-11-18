package com.pojo;

public class Product {
    private String id;
    private String typeid;
    private String productname;
    private String norms;//规格
    private String describe;
    private String productno;//产品编号

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

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
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

    public String getProductno() {
        return productno;
    }

    public void setProductno(String productno) {
        this.productno = productno;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", typeid='" + typeid + '\'' +
                ", productname='" + productname + '\'' +
                ", norms='" + norms + '\'' +
                ", describe='" + describe + '\'' +
                ", productno='" + productno + '\'' +
                '}';
    }
}
