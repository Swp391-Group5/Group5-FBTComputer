/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 *
 */
public class OrderHistoryDetail {

    private int orderHistoryDetailId;
    private String serialNum;
    private double price;
    private int orderHistoryId, productId;

    public OrderHistoryDetail() {
    }

    public OrderHistoryDetail(int orderHistoryDetailId, String serialNum, double price, int orderHistoryId, int productId) {
        this.orderHistoryDetailId = orderHistoryDetailId;
        this.serialNum = serialNum;
        this.price = price;
        this.orderHistoryId = orderHistoryId;
        this.productId = productId;
    }

    public OrderHistoryDetail(int orderHistoryDetailId, String serialNum, double price, int productId) {
        this.orderHistoryDetailId = orderHistoryDetailId;
        this.serialNum = serialNum;
        this.price = price;
        this.productId = productId;
    }

    public int getOrderHistoryDetailId() {
        return orderHistoryDetailId;
    }

    public void setOrderHistoryDetailId(int orderHistoryDetailId) {
        this.orderHistoryDetailId = orderHistoryDetailId;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderHistoryDetail{" + "orderHistoryDetailId=" + orderHistoryDetailId + ", serialNum=" + serialNum + ", price=" + price + ", orderHistoryId=" + orderHistoryId + ", productId=" + productId + '}';
    }

}
