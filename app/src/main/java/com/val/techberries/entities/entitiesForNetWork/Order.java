package com.val.techberries.entities.entitiesForNetWork;

import java.util.List;

public class Order {
    private int price;
    private int paymentType;
    private String address;
    private int deliveryType;
    private List<Integer> productsIDs;

    public Order() {
    }

    public Order(int price, int paymentType, String address, int deliveryType) {
        this.price = price;
        this.paymentType = paymentType;
        this.address = address;
        this.deliveryType = deliveryType;
    }

    public List<Integer> getProductsID() {
        return productsIDs;
    }

    public void setProductsID(List<Integer> productsID) {
        this.productsIDs = productsID;
    }

    public int getPrice() {
        return price;
    }


    public int getPaymentType() {
        return paymentType;
    }

    public String getAddress() {
        return address;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }
}
