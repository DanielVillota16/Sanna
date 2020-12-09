package com.sanna_app.sanna.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id,name,photo,description,provider;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(String id, String name, String photo, String description, String provider, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.provider = provider;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
