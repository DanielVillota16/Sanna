package com.sanna_app.sanna.model;

import com.google.type.LatLng;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private String id,destName;
    private List<String> products;
    private LatLng addressFrom, addressTo;
    private String providerId;
    private  int status;

    public Order() {
    }

    public Order(String id, String destName, List<String> products, LatLng addressFrom, LatLng addressTo, int status, String providerId) {
        this.id = id;
        this.destName = destName;
        this.products = products;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.status = status;
        this.providerId=providerId;
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

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
