package com.sanna_app.sanna.model;

import com.google.type.LatLng;

import java.util.List;

public class Order {
    private String id,destName;
    private List<String> products;
    private LatLng addressFrom, addressTo;
    private  int status;

    public Order() {
    }

    public Order(String id, String destName, List<String> products, LatLng addressFrom, LatLng addressTo, int status) {
        this.id = id;
        this.destName = destName;
        this.products = products;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public LatLng getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(LatLng addressFrom) {
        this.addressFrom = addressFrom;
    }

    public LatLng getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(LatLng addressTo) {
        this.addressTo = addressTo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
