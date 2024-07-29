/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;

/**
 *
 * @author DELL DN
 */
public class OrderHistory {

    private int orderHistoryId;
    private double quantity;
    private String orderDate, name, phone, address;
    private int userId, roleId;
    private double totalMoney;
    private String isSuccess;
    private String orderHisStatus;
    private String paymentMethod;

    public OrderHistory() {
    }

    public OrderHistory(int orderHistoryId, double quantity, String orderDate, String name, String phone, String address, int userId, int roleId, double totalMoney, String isSuccess, String orderHisStatus, String paymentMethod) {
        this.orderHistoryId = orderHistoryId;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.userId = userId;
        this.roleId = roleId;
        this.totalMoney = totalMoney;
        this.isSuccess = isSuccess;
        this.orderHisStatus = orderHisStatus;
        this.paymentMethod = paymentMethod;
    }

    public int getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getOrderHisStatus() {
        return orderHisStatus;
    }

    public void setOrderHisStatus(String orderHisStatus) {
        this.orderHisStatus = orderHisStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "OrderHistory{" + "orderHistoryId=" + orderHistoryId + ", quantity=" + quantity + ", orderDate=" + orderDate + ", name=" + name + ", phone=" + phone + ", address=" + address + ", userId=" + userId + ", roleId=" + roleId + ", totalMoney=" + totalMoney + ", isSuccess=" + isSuccess + ", orderHisStatus=" + orderHisStatus + ", paymentMethod=" + paymentMethod + '}';
    }

    public String getFormattedTotalMoney() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(totalMoney);
    }
}
