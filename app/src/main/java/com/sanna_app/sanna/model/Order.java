package com.sanna_app.sanna.model;

import com.google.type.LatLng;
import com.sanna_app.sanna.constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private String id, destName, providerId, clientId;
    private LatLng addressFrom, addressTo;
    private ArrayList<Product> products;
    private  int status;

    public Order() {
    }

    public Order(String id, String destName, String providerId, String clientId, LatLng addressFrom, LatLng addressTo, ArrayList<Product> products, int status) {
        this.id = id;
        this.destName = destName;
        this.providerId = providerId;
        this.clientId = clientId;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.products = products;
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

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static String statusToString(int status){
        return status == Constants.PENDING?"Pendiente":
               status == Constants.ACCEPTED?"ACEPTADA":
               status == Constants.REJECTED?"Rechazada":
               status == Constants.ON_THE_WAY?"En camino":
               "Entregada";
    }

}
