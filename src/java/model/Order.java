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
public class Order {

    private int orderId;
    private Date orderDate;
    private int quantity;
    private int customerId;
    private int adminId;
    private String name;
    private float totalMoney;

    public Order() {
    }

    public Order(int orderId, Date orderDate, int quantity, int customerId, int adminId, String name, float totalMoney) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.customerId = customerId;
        this.adminId = adminId;
        this.name = name;
        this.totalMoney = totalMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getFormattedTotalMoney() {
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(2);
        return df.format(totalMoney);
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", quantity=" + quantity + ", customerId=" + customerId + ", adminId=" + adminId + ", totalMoney=" + totalMoney + '}';
    }

}
