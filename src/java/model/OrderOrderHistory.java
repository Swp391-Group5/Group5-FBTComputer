/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author hungp
 */
public class OrderOrderHistory {

    private int orderId;
    private boolean orderStatus;
    private int orderHistoryId;
    private int quantity;
    private Date orderDate;
    private String name;
    private String phone;
    private String address;
    private int userId;
    private int roleId;
    private double totalMoney;
    private String isSuccess;
    private boolean orderHistoryStatus;
    private String paymentMethod;

    public OrderOrderHistory() {
    }

    public OrderOrderHistory(int orderId, boolean orderStatus, int orderHistoryId, int quantity, Date orderDate, String name, String phone, String address, int userId, int roleId, double totalMoney, String isSuccess, boolean orderHistoryStatus, String paymentMethod) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
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
        this.orderHistoryStatus = orderHistoryStatus;
        this.paymentMethod = paymentMethod;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderHistoryId() {
        return orderHistoryId;
    }

    public void setOrderHistoryId(int orderHistoryId) {
        this.orderHistoryId = orderHistoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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

    public boolean isOrderHistoryStatus() {
        return orderHistoryStatus;
    }

    public void setOrderHistoryStatus(boolean orderHistoryStatus) {
        this.orderHistoryStatus = orderHistoryStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "OrderOrderHistory{" + "orderId=" + orderId + ", orderStatus=" + orderStatus + ", orderHistoryId=" + orderHistoryId + ", quantity=" + quantity + ", orderDate=" + orderDate + ", name=" + name + ", phone=" + phone + ", address=" + address + ", userId=" + userId + ", roleId=" + roleId + ", totalMoney=" + totalMoney + ", isSuccess=" + isSuccess + ", orderHistoryStatus=" + orderHistoryStatus + ", paymentMethod=" + paymentMethod + '}';
    }

    public String getFormattedTotalMoney() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(totalMoney);
    }

}
