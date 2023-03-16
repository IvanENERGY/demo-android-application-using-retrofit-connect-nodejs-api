package com.example.testapiapplication;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("Id") //json attribute name (lib:gson)(need to match with api) // good practice:sync with db,api
    private String id;

    @SerializedName("Title")
    private String title;

    @SerializedName("Quantity")
    private int quantity;  //if u want to be nullable; set it Integer; also change the getter and constructor

    @SerializedName("Message")
    private String message;

    @SerializedName("City")
    private String city;

    public Order(String id, String title, int quantity, String message, String city) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.message = message;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMessage() {
        return message;
    }

    public String getCity() {
        return city;
    }
}
