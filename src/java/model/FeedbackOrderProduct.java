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
public class FeedbackOrderProduct {
    private int feedbackId;
    private String userName;
    private int userId;
    private int roleId;
    private int starRate;
    private String feedbackDescription;
    private byte[] feedbackImage;
    private Date feedbackDate;
    private boolean feedbackStatus;
    private int orderId;
    private int productId;
    private boolean orderStatus;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productQuantity;
    private String productBrand;
    private String productImage;
    private boolean productStatus;
    private String createBy;
    private Date createDate;
    private String modifyBy;
    private Date modifyDate;

    public FeedbackOrderProduct() {
    }

    public FeedbackOrderProduct(int feedbackId, String userName, int userId, int roleId, int starRate, String feedbackDescription, byte[] feedbackImage, Date feedbackDate, boolean feedbackStatus, int orderId, int productId, boolean orderStatus, String productName, String productDescription, double productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, Date createDate, String modifyBy, Date modifyDate) {
        this.feedbackId = feedbackId;
        this.userName = userName;
        this.userId = userId;
        this.roleId = roleId;
        this.starRate = starRate;
        this.feedbackDescription = feedbackDescription;
        this.feedbackImage = feedbackImage;
        this.feedbackDate = feedbackDate;
        this.feedbackStatus = feedbackStatus;
        this.orderId = orderId;
        this.productId = productId;
        this.orderStatus = orderStatus;
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
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
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

    public int getStarRate() {
        return starRate;
    }

    public void setStarRate(int starRate) {
        this.starRate = starRate;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }

    public byte[] getFeedbackImage() {
        return feedbackImage;
    }

    public void setFeedbackImage(byte[] feedbackImage) {
        this.feedbackImage = feedbackImage;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public boolean isFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(boolean feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
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
    
    @Override
    public String toString() {
        return "FeedbackOrderProduct{" + "feedbackId=" + feedbackId + ", userId=" + userId + ", roleId=" + roleId + ", starRate=" + starRate + ", feedbackDescription=" + feedbackDescription + ", feedbackImage=" + feedbackImage + ", feedbackDate=" + feedbackDate + ", feedbackStatus=" + feedbackStatus + ", orderId=" + orderId + ", productId=" + productId + ", orderStatus=" + orderStatus + ", productName=" + productName + ", productDescription=" + productDescription + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", productBrand=" + productBrand + ", productImage=" + productImage + ", productStatus=" + productStatus + ", createBy=" + createBy + ", createDate=" + createDate + ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + '}';
    }
    
    public String getFormattedProductPrice() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(productPrice);
    }
}
