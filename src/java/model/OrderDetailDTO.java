/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hungp
 */
import java.text.DecimalFormat;
import java.util.Date;

public class OrderDetailDTO {

    private int orderHistoryId;
    private int quantity;
    private Date orderDate;
    private String name;
    private String phone;
    private String address;
    private int userId;
    private int roleId;
    private float totalMoney;
    private String isSuccess;
    private boolean orderHistoryStatus;
    private String paymentMethod;
    private int orderHistoryDetailId;
    private String serialNumber;
    private float price;
    private int productId;
    private String productName;
    private String productDescription;
    private float productPrice;
    private int productQuantity;
    private String productBrand;
    private String productImage;
    private boolean productStatus;
    private String createBy;
    private Date createDate;
    private String modifyBy;
    private Date modifyDate;
    private int categoryId;
    private int adminId;
    private String categoryName;
    private boolean categoryStatus;
    private int orderDetailId;
    private int orderId;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderHistoryId, int quantity, Date orderDate, String name, String phone, String address, int userId, int roleId, float totalMoney, String isSuccess, boolean orderHistoryStatus, String paymentMethod, int orderHistoryDetailId, String serialNumber, float price, int productId, String productName, String productDescription, float productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, Date createDate, String modifyBy, Date modifyDate, int categoryId, int adminId, String categoryName, boolean categoryStatus, int orderDetailId, int orderId) {
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
        this.orderHistoryDetailId = orderHistoryDetailId;
        this.serialNumber = serialNumber;
        this.price = price;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.categoryId = categoryId;
        this.adminId = adminId;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
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

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
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

    public int getOrderHistoryDetailId() {
        return orderHistoryDetailId;
    }

    public void setOrderHistoryDetailId(int orderHistoryDetailId) {
        this.orderHistoryDetailId = orderHistoryDetailId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "orderHistoryId=" + orderHistoryId + ", quantity=" + quantity + ", orderDate=" + orderDate + ", name=" + name + ", phone=" + phone + ", address=" + address + ", userId=" + userId + ", roleId=" + roleId + ", totalMoney=" + totalMoney + ", isSuccess=" + isSuccess + ", orderHistoryStatus=" + orderHistoryStatus + ", paymentMethod=" + paymentMethod + ", orderHistoryDetailId=" + orderHistoryDetailId + ", serialNumber=" + serialNumber + ", price=" + price + ", productId=" + productId + ", productName=" + productName + ", productDescription=" + productDescription + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", productBrand=" + productBrand + ", productImage=" + productImage + ", productStatus=" + productStatus + ", createBy=" + createBy + ", createDate=" + createDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + ", categoryId=" + categoryId + ", adminId=" + adminId + ", categoryName=" + categoryName + ", categoryStatus=" + categoryStatus + ", orderDetailId=" + orderDetailId + ", orderId=" + orderId + '}';
    }

    public String getFormattedTotalMoney() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(totalMoney);
    }

    public String getFormattedPrice() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(price);
    }
}
